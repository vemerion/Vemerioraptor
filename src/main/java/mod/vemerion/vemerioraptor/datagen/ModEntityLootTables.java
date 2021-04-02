package mod.vemerion.vemerioraptor.datagen;

import java.util.ArrayList;
import java.util.List;

import mod.vemerion.vemerioraptor.Main;
import mod.vemerion.vemerioraptor.init.ModEntities;
import mod.vemerion.vemerioraptor.init.ModItems;
import net.minecraft.data.loot.EntityLootTables;
import net.minecraft.entity.EntityType;
import net.minecraft.loot.ConstantRange;
import net.minecraft.loot.ItemLootEntry;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.conditions.KilledByPlayer;
import net.minecraft.loot.conditions.RandomChanceWithLooting;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntityLootTables extends EntityLootTables {
	@Override
	protected void addTables() {
		registerLootTable(ModEntities.VEMERIORAPTOR,
				LootTable.builder()
						.addLootPool(LootPool.builder().rolls(ConstantRange.of(1))
								.addEntry(ItemLootEntry.builder(ModItems.VEMERIORAPTOR_CLAW))
								.acceptCondition(KilledByPlayer.builder())
								.acceptCondition(RandomChanceWithLooting.builder(0.25f, 0.05f))));
		registerLootTable(ModEntities.BRONTOSAURUS, LootTable.builder().addLootPool(LootPool.builder()
				.rolls(ConstantRange.of(1)).addEntry(ItemLootEntry.builder(ModItems.BRONTOSAURUS_MEAT))));
		registerLootTable(ModEntities.PLESIOSAURUS,
				LootTable.builder()
						.addLootPool(LootPool.builder().rolls(ConstantRange.of(1))
								.addEntry(ItemLootEntry.builder(ModItems.PLESIOSAURUS_PADDLE))
								.acceptCondition(KilledByPlayer.builder())
								.acceptCondition(RandomChanceWithLooting.builder(0.25f, 0.05f))));

		for (ModEntities.DinosaurBuilder<?> builder : ModEntities.getDinosaurBuilders()) {
			registerLootTable(builder.getDinosaurEgg(), LootTable.builder());

		}
	}

	@Override
	protected Iterable<EntityType<?>> getKnownEntities() {
		List<EntityType<?>> entities = new ArrayList<>();
		for (EntityType<?> type : ForgeRegistries.ENTITIES) {
			if (type != null && !isNonLiving(type))
				if (type.getRegistryName().getNamespace().equals(Main.MODID)) {
					entities.add(type);
				}
		}
		return entities;
	}
}