package mod.vemerion.vemerioraptor.entity;

import java.lang.reflect.Field;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

import com.google.common.collect.ImmutableSet;

import net.minecraft.entity.BoostHelper;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRideable;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

public class VemerioraptorEntity extends CreatureEntity implements IRideable {
	private static final DataParameter<Boolean> SADDLED = EntityDataManager.createKey(VemerioraptorEntity.class,
			DataSerializers.BOOLEAN);
	private static final DataParameter<Integer> BOOST_TIME = EntityDataManager.createKey(VemerioraptorEntity.class,
			DataSerializers.VARINT);

	private static final Field isRiderJumping = ObfuscationReflectionHelper.findField(LivingEntity.class,
			"field_70703_bu");

	private BoostHelper boostHelper = new BoostHelper(this.dataManager, BOOST_TIME, SADDLED);

	public VemerioraptorEntity(EntityType<? extends VemerioraptorEntity> type, World worldIn) {
		super(type, worldIn);
	}

	public static AttributeModifierMap.MutableAttribute attributes() {
		return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH, 10.0D)
				.createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.25D)
				.createMutableAttribute(Attributes.ATTACK_DAMAGE, 4.0D);
	}

	protected void registerData() {
		super.registerData();
		this.dataManager.register(SADDLED, true);
		this.dataManager.register(BOOST_TIME, 0);
	}

	@Override
	protected void registerGoals() {
		goalSelector.addGoal(0, new SwimGoal(this));
		goalSelector.addGoal(1, new FindMeatGoal(this));
		goalSelector.addGoal(5, new MeleeAttackGoal(this, 1.0D, true));
		goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 0.7D));
		goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 6.0F));
		goalSelector.addGoal(8, new LookRandomlyGoal(this));
		targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, AnimalEntity.class, 10, true, false, null));

	}

	@Override
	public void tick() {
		super.tick();

		ifRider(rider -> {
			if (rider.isSwingInProgress) {
				if (!isSwingInProgress) {
					swingArm(Hand.MAIN_HAND);
				}

				if (!world.isRemote) {
					AxisAlignedBB attackBox = getBoundingBox().offset(Vector3d.fromPitchYaw(getPitchYaw()).scale(2));
					for (Entity e : world.getEntitiesInAABBexcluding(this, attackBox, e -> e != rider)) {
						attackEntityAsMob(e);
					}
				}
			}
		});

		updateArmSwingProgress();
	}

	private boolean ifRider(Consumer<PlayerEntity> consumer) {
		if (isBeingRidden() && canBeSteered() && getControllingPassenger() instanceof PlayerEntity) {
			PlayerEntity rider = (PlayerEntity) getControllingPassenger();
			consumer.accept(rider);
			return true;
		}
		return false;
	}

	@Override
	public void travel(Vector3d travelVector) {
		ride(this, boostHelper, travelVector);
	}

	@Override
	public double getMountedYOffset() {
		return super.getMountedYOffset() * 0.9;
	}

	@Override
	public boolean boost() {
		return boostHelper.boost(getRNG());
	}

	@Override
	protected float getJumpUpwardsMotion() {
		return super.getJumpUpwardsMotion() * 1.3f;
	}

	@Override
	public void travelTowards(Vector3d travelVec) {
		if (!ifRider(rider -> {
			float forward = rider.moveForward;
			float strafe = rider.moveStrafing * 0.5f;
			try {
				if (isRiderJumping.getBoolean(rider) && onGround) {
					jump();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (forward < 0)
				forward *= 0.25f;
			super.travel(new Vector3d(strafe, 0, forward));
		})) {
			super.travel(travelVec);
		}
	}

	@Override
	public float getMountedSpeed() {
		return (float) getAttributeValue(Attributes.MOVEMENT_SPEED);
	}

	@Override
	public Entity getControllingPassenger() {
		return getPassengers().isEmpty() ? null : getPassengers().get(0);
	}

	@Override
	public boolean canBeSteered() {
		return true;
	}

	// Interact with entity
	@Override
	public ActionResultType func_230254_b_(PlayerEntity player, Hand hand) {
		if (!isBeingRidden() && !player.isSecondaryUseActive()) {
			if (!this.world.isRemote) {
				player.startRiding(this);
			}

			return ActionResultType.func_233537_a_(world.isRemote);
		}
		return super.func_230254_b_(player, hand);
	}

	private static class FindMeatGoal extends Goal {
		private static final Set<Item> MEATS = ImmutableSet.of(Items.PORKCHOP, Items.BEEF, Items.MUTTON, Items.CHICKEN,
				Items.RABBIT);

		private VemerioraptorEntity raptor;

		public FindMeatGoal(VemerioraptorEntity raptor) {
			this.raptor = raptor;
			this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE));
		}

		@Override
		public boolean shouldExecute() {
			return !getNearbyMeat().isEmpty();
		}

		@Override
		public void startExecuting() {
			List<ItemEntity> meats = getNearbyMeat();
			if (!meats.isEmpty()) {
				raptor.getNavigator().tryMoveToEntityLiving(meats.get(0), (double) 1.2F);
			}
		}

		@Override
		public void tick() {
			List<ItemEntity> meats = getNearbyMeat();
			if (!meats.isEmpty()) {
				raptor.getNavigator().tryMoveToEntityLiving(meats.get(0), (double) 1.2F);
			}
		}

		private List<ItemEntity> getNearbyMeat() {
			return raptor.world.getEntitiesWithinAABB(ItemEntity.class, raptor.getBoundingBox().grow(8.0D, 8.0D, 8.0D),
					e -> MEATS.contains(e.getItem().getItem()));

		}

	}

}
