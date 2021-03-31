package mod.vemerion.vemerioraptor.entity;

import mod.vemerion.vemerioraptor.Main;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.FindWaterGoal;
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
import net.minecraft.util.math.MathHelper;
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
		this(Main.PLESIOSAURUS_ENTITY, worldIn);
	}

	public static AttributeModifierMap.MutableAttribute attributes() {
		return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH, 20)
				.createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.5)
				.createMutableAttribute(Attributes.ATTACK_DAMAGE, 2);
	}

	@Override
	public AgeableEntity func_241840_a(ServerWorld world, AgeableEntity parent) {
		return Main.BRONTOSAURUS_EGG_ENTITY.create(world);
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
		goalSelector.addGoal(0, new FindWaterGoal(this));
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
	public CreatureAttribute getCreatureAttribute() {
		return CreatureAttribute.WATER;
	}

	@Override
	public boolean isNotColliding(IWorldReader worldIn) {
		return worldIn.checkNoEntityCollision(this);
	}

	protected void updateAir(int air) {
		if (this.isAlive() && !this.isInWaterOrBubbleColumn()) {
			this.setAir(air - 1);
			if (this.getAir() == -20) {
				this.setAir(0);
				this.attackEntityFrom(DamageSource.DROWN, 2.0F);
			}
		} else {
			this.setAir(300);
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
}
