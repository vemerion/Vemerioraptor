package mod.vemerion.vemerioraptor.entity;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import com.google.common.collect.ImmutableList;

import mod.vemerion.vemerioraptor.init.ModEntities;
import mod.vemerion.vemerioraptor.init.ModItems;
import mod.vemerion.vemerioraptor.init.ModSounds;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IAngerable;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.BreedGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.ResetAngerGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.RangedInteger;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.TickRangeConverter;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class BrontosaurusEntity extends DinosaurEntity implements IAngerable {

	private static final RangedInteger ANGRY_RANGE = TickRangeConverter.convertRange(20, 39);
	private static final DataParameter<Boolean> EATING = EntityDataManager.createKey(BrontosaurusEntity.class,
			DataSerializers.BOOLEAN);

	private static final List<Item> FART_ITEMS = ImmutableList.of(Items.ACACIA_SAPLING, Items.BIRCH_SAPLING,
			Items.DARK_OAK_SAPLING, Items.JUNGLE_SAPLING, Items.OAK_SAPLING, Items.SPRUCE_SAPLING, ModItems.MANURE);

	private static final int EATING_TIME = 60;

	private int angryTime;
	private UUID angerTarget;
	private boolean isBrontosaurusAttacking;
	private float brontosaurusAttackingProgress;
	private float prevBrontosaurusAttackingProgress;
	private int eatingProgress;

	public BrontosaurusEntity(EntityType<? extends BrontosaurusEntity> type, World worldIn) {
		super(type, worldIn);
	}

	public BrontosaurusEntity(World worldIn) {
		this(ModEntities.BRONTOSAURUS, worldIn);
	}

	public static AttributeModifierMap.MutableAttribute attributes() {
		return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH, 50.0D)
				.createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.2D)
				.createMutableAttribute(Attributes.ATTACK_DAMAGE, 2.0D);
	}

	@Override
	protected void registerData() {
		super.registerData();
		dataManager.register(EATING, false);
	}
	
	@Override
	protected SoundEvent getAmbientSound() {
		return ModSounds.BRONTOSAURUS_AMBIENT;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return ModSounds.BRONTOSAURUS_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return ModSounds.BRONTOSAURUS_DEATH;
	}

	public boolean isEating() {
		return dataManager.get(EATING);
	}

	private void setEating(boolean b) {
		dataManager.set(EATING, b);
	}

	public float getEatingProgress(float partialTicks) {
		return MathHelper.lerp(partialTicks, (float) Math.max(0, eatingProgress - 1) / EATING_TIME,
				(float) eatingProgress / EATING_TIME);
	}

	@Override
	public void livingTick() {
		super.livingTick();

		if (!world.isRemote) {
			this.func_241359_a_((ServerWorld) world, true); // IAngerable tick
		}
	}

	@Override
	public void tick() {
		super.tick();

		if (isBrontosaurusAttacking) {
			prevBrontosaurusAttackingProgress = brontosaurusAttackingProgress;
			brontosaurusAttackingProgress += 0.05;
			if (brontosaurusAttackingProgress > 1) {
				isBrontosaurusAttacking = false;
				brontosaurusAttackingProgress = 0;
				prevBrontosaurusAttackingProgress = 0;
			}
		}

		if (isEating()) {
			eatingProgress++;
		} else {
			eatingProgress = 0;
		}

	}

	public float getBrontosaurusAttackingProgress(float partialTicks) {
		return MathHelper.lerp(partialTicks, prevBrontosaurusAttackingProgress, brontosaurusAttackingProgress);
	}

	public boolean isBrontosaurusAttacking() {
		return isBrontosaurusAttacking;
	}

	@Override
	public void swing(Hand handIn, boolean updateSelf) {
		super.swing(handIn, updateSelf);
		isBrontosaurusAttacking = isSwingInProgress;
		brontosaurusAttackingProgress = swingProgress;
		prevBrontosaurusAttackingProgress = swingProgress;
	}

	@Override
	public AgeableEntity func_241840_a(ServerWorld world, AgeableEntity parent) {
		return ModEntities.BRONTOSAURUS_EGG.create(world);
	}

	@Override
	protected void registerGoals() {
		goalSelector.addGoal(0, new SwimGoal(this));
		goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, true) {
			@Override
			protected double getAttackReachSqr(LivingEntity attackTarget) {
				return super.getAttackReachSqr(attackTarget) * 0.5;
			}
		});
		goalSelector.addGoal(3, new BreedGoal(this, 1));
		goalSelector.addGoal(4, new EatLeavesGoal(this, 1, 12, 5));
		goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 0.7D));
		goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 6.0F));
		goalSelector.addGoal(8, new LookRandomlyGoal(this));
		targetSelector.addGoal(3, new HurtByTargetGoal(this));
		targetSelector.addGoal(4,
				new NearestAttackableTargetGoal<>(this, PlayerEntity.class, 10, true, false, this::func_233680_b_));
		targetSelector.addGoal(8, new ResetAngerGoal<>(this, true));

	}

	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		writeAngerNBT(compound);
	}

	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		readAngerNBT((ServerWorld) world, compound);
	}

	@Override
	public boolean isBreedingItem(ItemStack stack) {
		return ItemTags.LEAVES.contains(stack.getItem());
	}

	@Override
	public int getAngerTime() {
		return angryTime;
	}

	@Override
	public void setAngerTime(int time) {
		angryTime = time;
	}

	@Override
	public UUID getAngerTarget() {
		return angerTarget;
	}

	@Override
	public void setAngerTarget(UUID target) {
		angerTarget = target;
	}

	@Override
	public void func_230258_H__() {
		setAngerTime(ANGRY_RANGE.getRandomWithinRange(rand));
	}

	private static class EatLeavesGoal extends MoveToBlockGoal {

		private BrontosaurusEntity brontosaurus;

		public EatLeavesGoal(BrontosaurusEntity creatureIn, double speed, int length, int height) {
			super(creatureIn, speed, length, height);
			this.brontosaurus = creatureIn;
		}
		
		@Override
		protected int getRunDelay(CreatureEntity creatureIn) {
			return super.getRunDelay(creatureIn) * 3;
		}

		@Override
		protected boolean shouldMoveTo(IWorldReader worldIn, BlockPos pos) {
			return BlockTags.LEAVES.contains(worldIn.getBlockState(pos).getBlock());
		}

		// Destination block
		@Override
		protected BlockPos func_241846_j() {
			return destinationBlock;
		}

		@Override
		public double getTargetDistanceSq() {
			return 6;
		}

		@Override
		public void resetTask() {
			super.resetTask();
			brontosaurus.setEating(false);
		}

		@Override
		public void tick() {
			if (getIsAboveDestination()) {
				if (brontosaurus.eatingProgress < EATING_TIME)
					brontosaurus.setEating(true);
			}

			if (brontosaurus.eatingProgress >= EATING_TIME) {
				creature.world.destroyBlock(destinationBlock, false);
				brontosaurus.setEating(false);
				fart();
			}

			super.tick();
		}

		private void fart() {
			Random rand = brontosaurus.getRNG();

			if (rand.nextDouble() < 0.5)
				return;
			
			Vector3d pos = brontosaurus.getPositionVec().add(Vector3d.fromPitchYaw(brontosaurus.getPitchYaw()).scale(-1.5));
			Item item = FART_ITEMS.get(rand.nextInt(FART_ITEMS.size()));
			ItemEntity itemEntity = new ItemEntity(brontosaurus.world, pos.x, pos.y, pos.z, new ItemStack(item, rand.nextInt(5) + 1));
			brontosaurus.world.addEntity(itemEntity);
		}

	}
}
