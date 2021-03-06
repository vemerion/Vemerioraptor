package mod.vemerion.vemerioraptor.entity;

import java.util.EnumSet;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import mod.vemerion.vemerioraptor.init.ModEntities;
import mod.vemerion.vemerioraptor.init.ModSounds;
import net.minecraft.block.Blocks;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.ai.goal.BreedGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.entity.ai.goal.TargetGoal;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.passive.fish.AbstractGroupFishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.pathfinding.SwimmerPathNavigator;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ForgeMod;

public class PlesiosaurusEntity extends DinosaurEntity {
	private UUID egg;

	public PlesiosaurusEntity(EntityType<? extends PlesiosaurusEntity> type, World worldIn) {
		super(type, worldIn);
		this.setPathPriority(PathNodeType.WATER, 0);
		this.moveController = new MoveHelperController(this);
	}

	public PlesiosaurusEntity(World worldIn) {
		this(ModEntities.PLESIOSAURUS, worldIn);
	}

	public static AttributeModifierMap.MutableAttribute attributes() {
		return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH, 23)
				.createMutableAttribute(ForgeMod.SWIM_SPEED.get(), 1.6)
				.createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.5)
				.createMutableAttribute(Attributes.ATTACK_DAMAGE, 3);
	}

	public static boolean canSpawn(EntityType<? extends PlesiosaurusEntity> type, IWorld worldIn, SpawnReason reason,
			BlockPos p_223363_3_, Random randomIn) {
		return worldIn.getBlockState(p_223363_3_).isIn(Blocks.WATER)
				&& worldIn.getBlockState(p_223363_3_.up()).isIn(Blocks.WATER);
	}

	@Override
	public boolean isBreedingItem(ItemStack stack) {
		return ItemTags.FISHES.contains(stack.getItem());
	}

	@Override
	public ILivingEntityData onInitialSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason,
			ILivingEntityData spawnDataIn, CompoundNBT dataTag) {
		setAir(getMaxAir());
		return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}

	@Override
	public AgeableEntity func_241840_a(ServerWorld world, AgeableEntity parent) {
		DinosaurEggEntity entity = ModEntities.PLESIOSAURUS_EGG.create(world);
		egg = entity.getUniqueID();
		return entity;
	}

	public Entity getEgg() {
		if (egg != null && !world.isRemote)
			return ((ServerWorld) world).getEntityByUuid(egg);
		return null;
	}

	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		if (egg != null)
			compound.putUniqueId("egg", egg);
	}

	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		if (compound.hasUniqueId("egg"))
			egg = compound.getUniqueId("egg");
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return isInWaterOrBubbleColumn() ? ModSounds.PLESIOSAURUS_AMBIENT : ModSounds.PLESIOSAURUS_HURT;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return ModSounds.PLESIOSAURUS_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return ModSounds.PLESIOSAURUS_DEATH;
	}

	@Override
	protected void registerGoals() {
		goalSelector.addGoal(0, new MoveToWaterGoal(this));
		goalSelector.addGoal(0, new EatFishGoal(this));
		goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.6, true));
		goalSelector.addGoal(2, new MoveToEggGoal(this));
		goalSelector.addGoal(3, new BreedGoal(this, 1));
		goalSelector.addGoal(4, new AvoidEntityGoal<PlayerEntity>(this, PlayerEntity.class, 10, 1, 1.2));
		goalSelector.addGoal(5, new RandomSwimmingGoal(this, 1.0D, 10));
		goalSelector.addGoal(6, new LookRandomlyGoal(this));
		targetSelector.addGoal(0, new HurtByTargetGoal(this));
		targetSelector.addGoal(1, new DefendEggGoal(this));
		targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, AbstractGroupFishEntity.class, 10, true,
				false, e -> getEgg() == null));
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
	public void tick() {
		super.tick();
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

	private static class MoveToEggGoal extends Goal {

		private PlesiosaurusEntity plesiosaurus;

		public MoveToEggGoal(PlesiosaurusEntity plesiosaurus) {
			this.plesiosaurus = plesiosaurus;
			this.setMutexFlags(EnumSet.of(Flag.MOVE));
		}

		@Override
		public boolean shouldExecute() {
			Entity egg = plesiosaurus.getEgg();
			if (egg != null) {
				double distance = plesiosaurus.getDistanceSq(egg);
				return plesiosaurus.getRNG().nextInt(100) == 0 && distance > 100 && distance < 2500;
			}
			return false;
		}

		@Override
		public boolean shouldContinueExecuting() {
			Entity egg = plesiosaurus.getEgg();
			if (egg != null) {
				double distance = plesiosaurus.getDistanceSq(egg);
				return distance > 12 && distance < 2500;
			}
			return false;
		}

		@Override
		public void startExecuting() {
			Entity egg = plesiosaurus.getEgg();
			if (egg != null) {
				plesiosaurus.getNavigator().tryMoveToEntityLiving(egg, 1);
			}
		}

	}

	private static class DefendEggGoal extends TargetGoal {

		private PlesiosaurusEntity plesiosaurus;
		private LivingEntity target;

		public DefendEggGoal(PlesiosaurusEntity plesiosaurus) {
			super(plesiosaurus, false);
			this.plesiosaurus = plesiosaurus;
			this.setMutexFlags(EnumSet.of(Goal.Flag.TARGET));
		}

		@Override
		public boolean shouldExecute() {
			Entity egg = plesiosaurus.getEgg();
			if (egg != null) {
				List<LivingEntity> targets = plesiosaurus.world.getEntitiesWithinAABB(LivingEntity.class,
						egg.getBoundingBox().grow(4), e -> shouldAttack(egg, e));
				if (!targets.isEmpty())
					target = targets.get(plesiosaurus.getRNG().nextInt(targets.size()));

			}

			if (target == null) {
				return false;
			} else {
				return !(target instanceof PlayerEntity)
						|| !target.isSpectator() && !((PlayerEntity) target).isCreative();
			}
		}

		private boolean shouldAttack(Entity egg, LivingEntity target) {
			return egg != target && !(target instanceof PlesiosaurusEntity) && !(target instanceof DinosaurEggEntity);
		}

		@Override
		public void startExecuting() {
			plesiosaurus.setAttackTarget(target);
			super.startExecuting();
		}
	}

	private static class EatFishGoal extends Goal {

		private static final int EAT_TIME = 20 * 2;

		private PlesiosaurusEntity plesiosaurus;
		private ItemEntity fish;
		private int eatTimer;

		// No mutex flags: Eat while doing other tasks
		private EatFishGoal(PlesiosaurusEntity plesiosaurus) {
			this.plesiosaurus = plesiosaurus;
		}

		@Override
		public boolean shouldExecute() {
			return !nearbyFish().isEmpty();
		}

		@Override
		public boolean shouldContinueExecuting() {
			return fish != null;
		}

		private List<ItemEntity> nearbyFish() {
			return plesiosaurus.world.getEntitiesWithinAABB(ItemEntity.class, plesiosaurus.getBoundingBox().grow(2),
					e -> e.getItem().getItem().isIn(ItemTags.FISHES));
		}

		@Override
		public void startExecuting() {
			List<ItemEntity> fishes = nearbyFish();
			if (!fishes.isEmpty()) {
				fish = fishes.get(plesiosaurus.getRNG().nextInt(fishes.size()));
				eatTimer = 0;
			}
		}

		@Override
		public void resetTask() {
			eatTimer = 0;
			fish = null;
		}

		@Override
		public void tick() {
			if (eatTimer++ > EAT_TIME) {
				fish.remove();
				fish = null;
			}

			if (eatTimer % 4 == 0)
				plesiosaurus.playSound(SoundEvents.ENTITY_GENERIC_EAT, 1, plesiosaurus.getSoundPitch());
		}
	}
}
