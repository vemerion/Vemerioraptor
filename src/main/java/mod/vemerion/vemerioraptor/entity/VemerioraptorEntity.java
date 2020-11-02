package mod.vemerion.vemerioraptor.entity;

import java.lang.reflect.Field;
import java.util.function.Consumer;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.BoostHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRideable;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

public class VemerioraptorEntity extends AnimalEntity implements IRideable {
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
				.createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.25D);
	}

	protected void registerData() {
		super.registerData();
		this.dataManager.register(SADDLED, true);
		this.dataManager.register(BOOST_TIME, 0);
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
						e.attackEntityFrom(DamageSource.causeMobDamage(this), 4);
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
		ride(this, boostHelper, Vector3d.ZERO);
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
	public AgeableEntity func_241840_a(ServerWorld p_241840_1_, AgeableEntity p_241840_2_) { // Breed
		return null;
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

}
