package mod.vemerion.vemerioraptor.item;

import java.util.UUID;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;

import mod.vemerion.vemerioraptor.Main;
import mod.vemerion.vemerioraptor.init.ModItems;
import mod.vemerion.vemerioraptor.model.PlesiosaurusSwimfinsModel;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ForgeMod;

public class PlesiosaurusSwimfinsItem extends ArmorItem {

	private static final Material MATERIAL = new Material();
	private static UUID ID = UUID.fromString("b16053fe-dd14-45df-8e3d-da66c27348cf");

	@OnlyIn(Dist.CLIENT)
	private PlesiosaurusSwimfinsModel<?> model;

	public PlesiosaurusSwimfinsItem() {
		super(MATERIAL, EquipmentSlotType.FEET, new Item.Properties().group(ItemGroup.SEARCH));
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
		return Main.MODID + ":textures/armor/plesiosaurus_swimfins.png";
	}

	@Override
	public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlotType slot, ItemStack stack) {
		return slot == EquipmentSlotType.FEET
				? ImmutableMultimap.of(ForgeMod.SWIM_SPEED.get(),
						new AttributeModifier(ID, MATERIAL.getName(), 0.5, AttributeModifier.Operation.ADDITION))
				: super.getAttributeModifiers(slot, stack);
	}

	@SuppressWarnings("unchecked")
	@OnlyIn(Dist.CLIENT)
	@Override
	public <A extends BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack,
			EquipmentSlotType armorSlot, A _default) {

		if (model == null)
			model = new PlesiosaurusSwimfinsModel<>();

		model.bipedLeftLeg.showModel = true;
		model.bipedRightLeg.showModel = true;

		model.isSitting = _default.isSitting;
		model.isSneak = _default.isSneak;
		model.isChild = _default.isChild;
		model.rightArmPose = _default.rightArmPose;
		model.leftArmPose = _default.leftArmPose;

		return (A) model;
	}

	private static class Material implements IArmorMaterial {
		@Override
		public int getDurability(EquipmentSlotType slotIn) {
			return 64;
		}

		@Override
		public int getDamageReductionAmount(EquipmentSlotType slotIn) {
			return 1;
		}

		@Override
		public int getEnchantability() {
			return 15;
		}

		@Override
		public SoundEvent getSoundEvent() {
			return SoundEvents.ITEM_ARMOR_EQUIP_LEATHER;
		}

		@Override
		public float getToughness() {
			return 0;
		}

		@Override
		public float getKnockbackResistance() {
			return 0;
		}

		@Override
		public Ingredient getRepairMaterial() {
			return Ingredient.fromItems(ModItems.PLESIOSAURUS_SWIMFINS);
		}

		@Override
		public String getName() {
			return Main.MODID + ":plesiosaurus_swimfins";
		}
	}
}
