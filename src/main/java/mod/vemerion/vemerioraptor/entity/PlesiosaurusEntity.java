package mod.vemerion.vemerioraptor.entity;

import java.util.EnumSet;

import mod.vemerion.vemerioraptor.init.ModEntities;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.entity.passive.fish.AbstractGroupFishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.pathfinding.SwimmerPathNavigator;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class PlesiosaurusEntity extends DinosaurEntity {

	public PlesiosaurusEntity(EntityType<? extends PlesiosaurusEntity> type, World worldIn) {
		super(type, worldIn);
		this.setPathPriority(PathNodeType.WATER, 0);
		this.moveController = new MoveHelperController(this);
	}

	public PlesiosaurusEntity(World worldIn) {
		this(ModEntities.PLESIOSAURUS, worldIn);
	}

	public static AttributeModifierMap.MutableAttribute attributes() {
		return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH, 20)
				.createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.5)
				.createMutableAttribute(Attributes.ATTACK_DAMAGE, 2);
	}

	@Override
	public ILivingEntityData onInitialSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason,
			ILivingEntityData spawnDataIn, CompoundNBT dataTag) {
		setAir(getMaxAir());
		return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}

	@Override
	public AgeableEntity func_241840_a(ServerWorld world, AgeableEntity parent) {
		return ModEntities.BRONTOSAURUS_EGG.create(world);
	}

	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
	}

	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
	}

	@Override
	protected void registerGoals() {
		goalSelector.addGoal(0, new MoveToWaterGoal(this));
		goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.6, true));
		goalSelector.addGoal(4, new RandomSwimmingGoal(this, 1.0D, 10));
		goalSelector.addGoal(4, new LookRandomlyGoal(this));
		targetSelector.addGoal(4,
				new NearestAttackableTargetGoal<>(this, AbstractGroupFishEntity.class, 10, true, false, null));
	}

	@Override
	protected PathNavigator createNavigator(World worldIn) {
		return new Navigator(this, worldIn);
	}

	@Override
	public boolean canBreatheUnderwater() {
		return true;
	}

	@Override
	public int getMaxAir() {
		return 600;
	}

	@Override
	public CreatureAttribute getCreatureAttribute() {
		return CreatureAttribute.WATER;
	}

	@Override
	public boolean isNotColliding(IWorldReader worldIn) {
		return worldIn.checkNoEntityCollision(this);
	}

	protected void updateAir(int air) {
		if (isAlive() && !isInWaterOrBubbleColumn()) {
			setAir(air - 1);
			if (getAir() == -20) {
				setAir(0);
				attackEntityFrom(DamageSource.DROWN, 2.0F);
			}
		} else {
			setAir(getMaxAir());
		}
	}

	@Override
	public void baseTick() {
		int air = getAir();
		super.baseTick();
		updateAir(air);
	}

	@Override
	public boolean isPushedByWater() {
		return false;
	}

	@Override
	public boolean canBeLeashedTo(PlayerEntity player) {
		return false;
	}

	private static class MoveHelperController extends MovementController {
		private final PlesiosaurusEntity plesiosaurus;

		MoveHelperController(PlesiosaurusEntity plesiosaurus) {
			super(plesiosaurus);
			this.plesiosaurus = plesiosaurus;
		}

		@Override
		public void tick() {
			boolean inWater = plesiosaurus.areEyesInFluid(FluidTags.WATER);
			if (inWater)
				plesiosaurus.setMotion(plesiosaurus.getMotion().add(0, 0.005, 0));
			if (action == MovementController.Action.MOVE_TO) {
				float maxSpeed = (float) (speed * plesiosaurus.getAttributeValue(Attributes.MOVEMENT_SPEED));
				if (!inWater)
					maxSpeed *= 0.4;
				plesiosaurus.setAIMoveSpeed(MathHelper.lerp(0.125f, plesiosaurus.getAIMoveSpeed(), maxSpeed));
				double dx = posX - plesiosaurus.getPosX();
				double dy = posY - plesiosaurus.getPosY();
				double dz = posZ - plesiosaurus.getPosZ();
				double distance = MathHelper.sqrt(dx * dx + dy * dy + dz * dz);

				if (distance < 0.1) {
					plesiosaurus.setMoveForward(0);
					return;
				}

				if (Math.abs(dy) > 0.0001) {
					plesiosaurus.setMotion(
							plesiosaurus.getMotion().add(0, plesiosaurus.getAIMoveSpeed() * (dy / distance) * 0.1, 0));
				}

				if (Math.abs(dx) > 0.0001 || Math.abs(dz) > 0.0001) {
					float rotate = (float) (MathHelper.atan2(dz, dx) * (double) (180 / Math.PI)) - 90f;
					plesiosaurus.rotationYaw = limitAngle(plesiosaurus.rotationYaw, rotate, 8);
					plesiosaurus.renderYawOffset = plesiosaurus.rotationYaw;
				}

			} else {
				plesiosaurus.setAIMoveSpeed(0);
			}
		}
	}

	private static class Navigator extends SwimmerPathNavigator {

		private Navigator(MobEntity entitylivingIn, World worldIn) {
			super(entitylivingIn, worldIn);

		}

		@Override
		protected boolean canNavigate() {
			return entity.isOnGround() || isInLiquid();
		}

	}

	private static class MoveToWaterGoal extends Goal {

		private static final int RANGE = 4;
		private static final BlockPos POS_RANGE = new BlockPos(RANGE, RANGE, RANGE);

		private PlesiosaurusEntity plesiosaurus;
		private Vector3d target = null;

		private MoveToWaterGoal(PlesiosaurusEntity plesiosaurus) {
			this.plesiosaurus = plesiosaurus;
			setMutexFlags(EnumSet.of(Flag.MOVE));
		}

		@Override
		public boolean shouldExecute() {
			return plesiosaurus.isOnGround()
					&& !plesiosaurus.world.getFluidState(plesiosaurus.getPosition()).isTagged(FluidTags.WATER);
		}
		
		@Override
		public void resetTask() {
			target = null;
		}

		@Override
		public void tick() {
			if (target == null) {
				BlockPos center = plesiosaurus.getPosition();
				for (BlockPos p : BlockPos.getAllInBoxMutable(center.subtract(POS_RANGE), center.add(POS_RANGE))) {
					if (plesiosaurus.world.getFluidState(p).isTagged(FluidTags.WATER)) {
						target = Vector3d.copyCenteredHorizontally(p);
						break;
					}
				}
				
				if (target == null)
					target = RandomPositionGenerator.findRandomTarget(plesiosaurus, 2, 0);
				
				if (target != null)
					plesiosaurus.getMoveHelper().setMoveTo(target.getX(), target.getY(), target.getZ(), 1);
			} else if (plesiosaurus.getDistanceSq(target) < 1)
				target = null;
		}
	}
}
