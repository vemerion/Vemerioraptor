package mod.vemerion.vemerioraptor.item;

import mod.vemerion.vemerioraptor.Main;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SwordItem;
import net.minecraft.item.crafting.Ingredient;

public class VemerioraptorClawWeaponItem extends SwordItem {

	public VemerioraptorClawWeaponItem() {
		super(new VemerioraptorClawTier(), 3, -2.4F, new Item.Properties().group(ItemGroup.SEARCH));
	}

	private static class VemerioraptorClawTier implements IItemTier {

		@Override
		public int getMaxUses() {
			return 131;
		}

		@Override
		public float getEfficiency() {
			return 8;
		}

		@Override
		public float getAttackDamage() {
			return 3;
		}

		@Override
		public int getHarvestLevel() {
			return 3;
		}

		@Override
		public int getEnchantability() {
			return 10;
		}

		@Override
		public Ingredient getRepairMaterial() {
			return Ingredient.fromItems(Main.VEMERIORAPTOR_CLAW_ITEM);
		}
		
	}
}
