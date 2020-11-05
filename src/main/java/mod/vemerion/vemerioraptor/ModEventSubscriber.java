package mod.vemerion.vemerioraptor;

import java.awt.Color;

import mod.vemerion.vemerioraptor.entity.VemerioraptorEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.ParallelDispatchEvent;
import net.minecraftforge.registries.IForgeRegistryEntry;

@EventBusSubscriber(modid = Main.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModEventSubscriber {

	private static EntityType<VemerioraptorEntity> vemerioraptorType;

	@SubscribeEvent
	public static void registerEntity(RegistryEvent.Register<EntityType<?>> event) {
		event.getRegistry().register(setup(vemerioraptorType, "vemerioraptor_entity"));
	}

	@SubscribeEvent
	public static void registerItem(RegistryEvent.Register<Item> event) {
		vemerioraptorType = EntityType.Builder
				.<VemerioraptorEntity>create(VemerioraptorEntity::new, EntityClassification.CREATURE).size(1.2f, 2f)
				.build("vemerioraptor_entity");

		Item vemerioraptorSpawnEgg = new SpawnEggItem(vemerioraptorType, new Color(137, 115, 76).getRGB(),
				new Color(217, 199, 139).getRGB(), new Item.Properties().group(ItemGroup.MISC));
		event.getRegistry().register(setup(vemerioraptorSpawnEgg, "vemerioraptor_spawn_egg"));
	}

	@SubscribeEvent
	public static void registerAttributes(ParallelDispatchEvent event) {
		event.enqueueWork(() -> GlobalEntityTypeAttributes.put(Main.VEMERIORAPTOR_ENTITY,
				VemerioraptorEntity.attributes().create()));
	}

	public static <T extends IForgeRegistryEntry<T>> T setup(final T entry, final String name) {
		return setup(entry, new ResourceLocation(Main.MODID, name));
	}

	public static <T extends IForgeRegistryEntry<T>> T setup(final T entry, final ResourceLocation registryName) {
		entry.setRegistryName(registryName);
		return entry;
	}
}
