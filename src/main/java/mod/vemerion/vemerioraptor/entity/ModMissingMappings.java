package mod.vemerion.vemerioraptor.entity;

import mod.vemerion.vemerioraptor.Main;
import mod.vemerion.vemerioraptor.init.ModEntities;
import mod.vemerion.vemerioraptor.init.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.RegistryEvent.MissingMappings.Mapping;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(bus = Bus.FORGE, modid = Main.MODID)
public class ModMissingMappings {

	@SubscribeEvent
	public static void onMissingEntity(RegistryEvent.MissingMappings<EntityType<?>> event) {
		for (Mapping<EntityType<?>> mapping : event.getMappings(Main.MODID)) {
			if (mapping.key.getPath().equals("vemerioraptor_entity"))
				mapping.remap(ModEntities.VEMERIORAPTOR);
			else if (mapping.key.getPath().equals("brontosaurus_entity"))
				mapping.remap(ModEntities.BRONTOSAURUS);
			else if (mapping.key.getPath().equals("vemerioraptor_egg_entity"))
				mapping.remap(ModEntities.VEMERIORAPTOR_EGG);
			else if (mapping.key.getPath().equals("brontosaurus_egg_entity"))
				mapping.remap(ModEntities.BRONTOSAURUS_EGG);
		}
	}

	@SubscribeEvent
	public static void onMissingItem(RegistryEvent.MissingMappings<Item> event) {
		for (Mapping<Item> mapping : event.getMappings(Main.MODID)) {
			if (mapping.key.getPath().equals("vemerioraptor_claw_item"))
				mapping.remap(ModItems.VEMERIORAPTOR_CLAW);
			else if (mapping.key.getPath().equals("manure_item"))
				mapping.remap(ModItems.MANURE);
			else if (mapping.key.getPath().equals("vemerioraptor_claw_weapon_item"))
				mapping.remap(ModItems.VEMERIORAPTOR_CLAW_WEAPON);
			else if (mapping.key.getPath().equals("brontosaurus_meat_item"))
				mapping.remap(ModItems.BRONTOSAURUS_MEAT);
			else if (mapping.key.getPath().equals("cooked_brontosaurus_meat_item"))
				mapping.remap(ModItems.COOKED_BRONTOSAURUS_MEAT);

			for (SpawnEggItem spawnEgg : ModEntities.getSpawnEggs())
				if (mapping.key.getPath().equals(spawnEgg.getRegistryName().getPath() + "_item"))
					mapping.remap(spawnEgg);
		}
	}
}
