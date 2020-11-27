package mod.vemerion.vemerioraptor.entity;

import java.lang.reflect.Field;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

import com.google.common.collect.ImmutableSet;

import mod.vemerion.vemerioraptor.Main;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.BoostHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRideable;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.BreedGoal;
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
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

public class VemerioraptorEntity extends AnimalEntity implements IRideable {
	private static final Set<Item> MEATS = ImmutableSet.of(Items.PORKCHOP, Items.BEEF, Items.MUTTON, Items.CHICKEN,
			Items.RABBIT);
	
	private static final DataParameter<Boolean> SADDLED = EntityDataManager.createKey(VemerioraptorEntity.class,
			DataSerializers.BOOLEAN);
	private static final DataParameter<Integer> BOOST_TIME = EntityDataManager.createKey(VemerioraptorEntity.class,
			DataSerializers.VARINT);
	private static final DataParameter<Boolean> EATING = EntityDataManager.createKey(VemerioraptorEntity.class,
			DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> FRIENDLY = EntityDataManager.createKey(VemerioraptorEntity.class,
			DataSerializers.BOOLEAN);

	private static final int FRIENDLY_DURATION = 20 * 60;

	private static final Field isRiderJumping = ObfuscationReflectionHelper.findField(LivingEntity.class,
			"field_70703_bu");

	private BoostHelper boostHelper = new BoostHelper(this.dataManager, BOOST_TIME, SADDLED);
	private int friendlyTimer;

	public VemerioraptorEntity(EntityType<? extends VemerioraptorEntity> type, World worldIn) {
		super(type, worldIn);
	}

	public VemerioraptorEntity(World worldIn) {
		this(Main.VEMERIORAPTOR_ENTITY, worldIn);
	}

	public static AttributeModifierMap.MutableAttribute attributes() {
		return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH, 25.0D)
				.createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.25D)
				.createMutableAttribute(Attributes.ATTACK_DAMAGE, 3.0D);
	}

	protected void registerData() {
		super.registerData();
		this.dataManager.register(SADDLED, true);
		this.dataManager.register(BOOST_TIME, 0);
		this.dataManager.register(EATING, false);
		this.dataManager.register(FRIENDLY, false);
	}

	public boolean isEating() {
		return dataManager.get(EATING);
	}

	private void setEating(boolean eating) {
		dataManager.set(EATING, eating);
	}

	private boolean isFriendly() {
		return dataManager.get(FRIENDLY);
	}

	private void makeHappy() {
		dataManager.set(FRIENDLY, true);
		friendlyTimer = FRIENDLY_DURATION;
		for (int i = 0; i < 10; i++) {
			((ServerWorld) world).spawnParticle(ParticleTypes.HEART, this.getPosXRandom(1.0D),
					this.getPosYRandom() + 0.5D, this.getPosZRandom(1.0D), 1, 0, 0, 0, 1);
		}
		setAttackTarget(null);
		setRevengeTarget(null);
	}

	private void setFriendly(Boolean friendly) {
		dataManager.set(FRIENDLY, friendly);
	}

	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putInt("friendlyTimer", friendlyTimer);
	}

	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		friendlyTimer = compound.getInt("friendlyTimer");
		if (friendlyTimer > 0)
			setFriendly(true);
	}

	@Override
	public AgeableEntity func_241840_a(ServerWorld world, AgeableEntity parent) {
		return new VemerioraptorEggEntity(world);
	}

	@Override
	protected void registerGoals() {
		goalSelector.addGoal(0, new SwimGoal(this));
		goalSelector.addGoal(1, new FindMeatGoal(this));
		goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.4, true) {
			@Override
			public boolean shouldExecute() {
				return super.shouldExecute() && !isFriendly();
			}
		});
		goalSelector.addGoal(3, new BreedGoal(this, 1));
		goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 0.7D));
		goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 6.0F));
		goalSelector.addGoal(8, new LookRandomlyGoal(this));
		targetSelector.addGoal(3,
				new NearestAttackableTargetGoal<>(this, PlayerEntity.class, 10, true, false, e -> !isFriendly()));
		targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, AnimalEntity.class, 10, true, false, e -> !(e instanceof VemerioraptorEntity)));

	}
	
	@Override
	public boolean isBreedingItem(ItemStack stack) {
		return MEATS.contains(stack.getItem());
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return Main.RAPTOR_AMBIENT_SOUND;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return Main.RAPTOR_HURT_SOUND;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return Main.RAPTOR_DEATH_SOUND;
	}

	@Override
	protected float getSoundVolume() {
		return super.getSoundVolume() * 0.8f;
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

		if (!world.isRemote) {
			if (friendlyTimer-- < 0) {
				setFriendly(false);
				getPassengers().forEach(e -> e.dismount());
			}
		}

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
		return super.getMountedYOffset() * 0.75;
	}
	
	@Override
	public double getYOffset() {
		return 0;
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
		return isFriendly();
	}

	// Interact with entity
	@Override
	public ActionResultType func_230254_b_(PlayerEntity player, Hand hand) {
		if (!isBeingRidden() && !player.isSecondaryUseActive() && isFriendly()) {
			if (!this.world.isRemote) {
				player.startRiding(this);
			}

			return ActionResultType.func_233537_a_(world.isRemote);
		}
		return super.func_230254_b_(player, hand);
	}
	
	@Override
	public boolean canFallInLove() {
		return super.canFallInLove() && isFriendly();
	}

	private static class FindMeatGoal extends Goal {


		private VemerioraptorEntity raptor;
		private ItemEntity target;
		private int eatingTimer;

		public FindMeatGoal(VemerioraptorEntity raptor) {
			this.raptor = raptor;
			this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE));
		}

		@Override
		public boolean shouldExecute() {
			return !getNearbyMeat().isEmpty() && !raptor.isBeingRidden();
		}

		@Override
		public void startExecuting() {
			raptor.setEating(false);
			List<ItemEntity> meats = getNearbyMeat();
			if (!meats.isEmpty()) {
				raptor.getNavigator().tryMoveToEntityLiving(meats.get(0), (double) 1.2F);
			}
		}

		@Override
		public void resetTask() {
			raptor.setEating(false);
		}

		@Override
		public void tick() {
			List<ItemEntity> meats = getNearbyMeat();
			if (!meats.isEmpty()) {
				ItemEntity meat = meats.get(0);
				raptor.getNavigator().tryMoveToEntityLiving(meat, (double) 1.2F);
				if (meat.getDistanceSq(raptor) < 4) {
					if (meat != target) {
						eatingTimer = 40;
					} else {
						if (eatingTimer-- < 0) {
							raptor.setEating(false);
							target.remove();
							raptor.makeHappy();
						} else {
							raptor.setEating(true);
							if (eatingTimer % 4 == 0)
								raptor.playSound(SoundEvents.ENTITY_GENERIC_EAT, 1, raptor.getSoundPitch());
						}
					}
					target = meat;
				}
			}
		}

		private List<ItemEntity> getNearbyMeat() {
			return raptor.world.getEntitiesWithinAABB(ItemEntity.class, raptor.getBoundingBox().grow(8.0D, 8.0D, 8.0D),
					e -> MEATS.contains(e.getItem().getItem()));

		}

	}
}
