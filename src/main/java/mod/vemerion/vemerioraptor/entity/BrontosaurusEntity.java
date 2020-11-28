package mod.vemerion.vemerioraptor.entity;

import mod.vemerion.vemerioraptor.Main;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class BrontosaurusEntity extends AnimalEntity {

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
	public AgeableEntity func_241840_a(ServerWorld world, AgeableEntity parent) {
		return new VemerioraptorEggEntity(world);
	}

	@Override
	protected void registerGoals() {

	}
	
	@Override
	public boolean isBreedingItem(ItemStack stack) {
		return ItemTags.LEAVES.contains(stack.getItem());
	}


	@Override
	public void tick() {
		super.tick();
		updateArmSwingProgress();
	}
}
