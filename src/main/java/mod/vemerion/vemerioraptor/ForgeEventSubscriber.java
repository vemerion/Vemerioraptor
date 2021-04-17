package mod.vemerion.vemerioraptor;

import java.util.Set;

import com.google.common.collect.ImmutableSet;

import mod.vemerion.vemerioraptor.init.ModEntities;
import net.minecraft.entity.EntityClassification;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = Main.MODID, bus = Bus.FORGE)
public class ForgeEventSubscriber {
	private static final Set<Biome.Category> RAPTOR_BIOMES = ImmutableSet.of(Biome.Category.DESERT,
			Biome.Category.FOREST, Biome.Category.JUNGLE, Biome.Category.MESA, Biome.Category.PLAINS,
			Biome.Category.SAVANNA, Biome.Category.SWAMP);

	private static final Set<Biome.Category> BRONTOSAURUS_BIOMES = ImmutableSet.of(Biome.Category.FOREST,
			Biome.Category.JUNGLE, Biome.Category.SAVANNA);

	private static final Set<Biome.Category> PLESIOSAURUS_BIOMES = ImmutableSet.of(Biome.Category.OCEAN,
			Biome.Category.RIVER);

	@SubscribeEvent
	public static void onBiomeLoad(BiomeLoadingEvent event) {
		if (RAPTOR_BIOMES.contains(event.getCategory())) {
			event.getSpawns().withSpawner(EntityClassification.CREATURE,
					new MobSpawnInfo.Spawners(ModEntities.VEMERIORAPTOR, 4, 1, 1));
		}
		if (BRONTOSAURUS_BIOMES.contains(event.getCategory())) {
			event.getSpawns().withSpawner(EntityClassification.CREATURE,
					new MobSpawnInfo.Spawners(ModEntities.BRONTOSAURUS, 5, 1, 2));
		}
		if (PLESIOSAURUS_BIOMES.contains(event.getCategory())) {
			event.getSpawns().withSpawner(EntityClassification.WATER_CREATURE,
					new MobSpawnInfo.Spawners(ModEntities.PLESIOSAURUS, 6, 1, 2));
		}
	}
}
