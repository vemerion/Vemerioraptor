package mod.vemerion.vemerioraptor.entity;

import java.util.UUID;

import mod.vemerion.vemerioraptor.Main;
import net.minecraft.entity.AgeableEntity;
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
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.ResetAngerGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.RangedInteger;
import net.minecraft.util.TickRangeConverter;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class BrontosaurusEntity extends DinosaurEntity implements IAngerable {

	private static final RangedInteger ANGRY_RANGE = TickRangeConverter.convertRange(20, 39);

	private int angryTime;
	private UUID angerTarget;

	public BrontosaurusEntity(EntityType<? extends BrontosaurusEntity> type, World worldIn) {
		super(type, worldIn);
	}

	public BrontosaurusEntity(World worldIn) {
		this(Main.BRONTOSAURUS_ENTITY, worldIn);
	}

	public static AttributeModifierMap.MutableAttribute attributes() {
		return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH, 50.0D)
				.createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.15D)
				.createMutableAttribute(Attributes.ATTACK_DAMAGE, 2.0D);
	}

	@Override
	public void livingTick() {
		super.livingTick();

		if (!world.isRemote) {
			this.func_241359_a_((ServerWorld) world, true); // IAngerable tick
		}
	}

	@Override
	public AgeableEntity func_241840_a(ServerWorld world, AgeableEntity parent) {
		return new VemerioraptorEggEntity(world);
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
}
