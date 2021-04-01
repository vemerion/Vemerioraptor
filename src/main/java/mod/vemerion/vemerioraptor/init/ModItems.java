package mod.vemerion.vemerioraptor.init;

import mod.vemerion.vemerioraptor.Main;
import mod.vemerion.vemerioraptor.item.VemerioraptorClawWeaponItem;
import mod.vemerion.vemerioraptor.item.VemerioraptorItemGroup;
import net.minecraft.item.BoneMealItem;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(value = Main.MODID)
@EventBusSubscriber(bus = Bus.MOD, modid = Main.MODID)
public class ModItems {

	public static final Item VEMERIORAPTOR_CLAW = null;
	public static final Item MANURE = null;

	@SubscribeEvent
	public static void onRegisterItem(RegistryEvent.Register<Item> event) {
		IForgeRegistry<Item> reg = event.getRegistry();

		reg.register(Init.setup(new Item(new Item.Properties().group(ItemGroup.SEARCH)), "vemerioraptor_claw"));
		reg.register(Init.setup(new VemerioraptorClawWeaponItem(), "vemerioraptor_claw_weapon"));

		Food brontosaurusMeat = new Food.Builder().hunger(10).saturation(1).meat().build();
		Food cookedBrontosaurusMeat = new Food.Builder().hunger(20).saturation(1).meat().build();
		Item brontosaurusMeatItem = new Item(new Item.Properties().group(ItemGroup.SEARCH).food(brontosaurusMeat));
		Item cookedBrontosaurusMeatItem = new Item(
				new Item.Properties().group(ItemGroup.SEARCH).food(cookedBrontosaurusMeat));
		reg.register(Init.setup(brontosaurusMeatItem, "brontosaurus_meat"));
		reg.register(Init.setup(cookedBrontosaurusMeatItem, "cooked_brontosaurus_meat"));
		reg.register(Init.setup(new BoneMealItem(new Item.Properties().group(ItemGroup.SEARCH)), "manure"));
	}

	public static VemerioraptorItemGroup VEMERIORAPTOR_ITEM_GROUP = new VemerioraptorItemGroup();

}
