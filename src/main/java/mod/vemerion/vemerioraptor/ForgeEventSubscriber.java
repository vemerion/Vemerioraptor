package mod.vemerion.vemerioraptor;

import java.util.Set;

import com.google.common.collect.ImmutableSet;

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

	@SubscribeEvent
	public static void onBiomeLoad(BiomeLoadingEvent event) {
			if (RAPTOR_BIOMES.contains(event.getCategory())) {
				event.getSpawns().withSpawner(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(Main.VEMERIORAPTOR_ENTITY, 4, 1, 1));
			}
	}
}
