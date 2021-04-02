package mod.vemerion.vemerioraptor.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class PlesiosaurusPaddleItem extends Item {

	public PlesiosaurusPaddleItem() {
		super(new Item.Properties().group(ItemGroup.SEARCH).maxDamage(64));
	}

	@Override
	public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		Entity riding = entityIn.getRidingEntity();
		if (isSelected && riding instanceof BoatEntity && entityIn instanceof LivingEntity
				&& riding.getControllingPassenger() == entityIn) {
			Vector3d motion = riding.getMotion();
			riding.setMotion(motion.scale(1.05));
			if (random.nextDouble() < 1 / 20d && isPaddling((BoatEntity) riding))
				stack.damageItem(1, (LivingEntity) entityIn, e -> e.sendBreakAnimation(EquipmentSlotType.MAINHAND));
		}
		super.inventoryTick(stack, worldIn, entityIn, itemSlot, isSelected);
	}

	private boolean isPaddling(BoatEntity boat) {
		return boat.getPaddleState(0) || boat.getPaddleState(1);
	}
}
