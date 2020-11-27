package mod.vemerion.vemerioraptor;

import java.awt.Color;

import mod.vemerion.vemerioraptor.entity.VemerioraptorEggEntity;
import mod.vemerion.vemerioraptor.entity.VemerioraptorEntity;
import mod.vemerion.vemerioraptor.item.VemerioraptorClawWeaponItem;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
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

		EntityType<VemerioraptorEggEntity> vemerioraptorEggType = EntityType.Builder
				.<VemerioraptorEggEntity>create(VemerioraptorEggEntity::new, EntityClassification.CREATURE).size(1, 1)
				.build("vemerioraptor_egg_entity");
		event.getRegistry().register(setup(vemerioraptorEggType, "vemerioraptor_egg_entity"));
	}

	@SubscribeEvent
	public static void registerItem(RegistryEvent.Register<Item> event) {
		vemerioraptorType = EntityType.Builder
				.<VemerioraptorEntity>create(VemerioraptorEntity::new, EntityClassification.CREATURE).size(1.2f, 2f)
				.build("vemerioraptor_entity");

		event.getRegistry()
				.register(setup(new Item(new Item.Properties().group(ItemGroup.SEARCH)), "vemerioraptor_claw_item"));
		event.getRegistry().register(setup(new VemerioraptorClawWeaponItem(), "vemerioraptor_claw_weapon_item"));

		Item vemerioraptorSpawnEgg = new SpawnEggItem(vemerioraptorType, new Color(137, 115, 76).getRGB(),
				new Color(217, 199, 139).getRGB(), new Item.Properties().group(ItemGroup.MISC));
		event.getRegistry().register(setup(vemerioraptorSpawnEgg, "vemerioraptor_spawn_egg"));
	}

	@SubscribeEvent
	public static void onRegisterSound(RegistryEvent.Register<SoundEvent> event) {
		SoundEvent raptor_ambient_sound = new SoundEvent(new ResourceLocation(Main.MODID, "raptor_ambient_sound"));
		event.getRegistry().register(setup(raptor_ambient_sound, "raptor_ambient_sound"));
		SoundEvent raptor_death_sound = new SoundEvent(new ResourceLocation(Main.MODID, "raptor_death_sound"));
		event.getRegistry().register(setup(raptor_death_sound, "raptor_death_sound"));
		SoundEvent raptor_hurt_sound = new SoundEvent(new ResourceLocation(Main.MODID, "raptor_hurt_sound"));
		event.getRegistry().register(setup(raptor_hurt_sound, "raptor_hurt_sound"));

	}

	@SubscribeEvent
	public static void registerAttributes(ParallelDispatchEvent event) {
		event.enqueueWork(() -> setEntityAttributes());
	}

	private static void setEntityAttributes() {
		GlobalEntityTypeAttributes.put(Main.VEMERIORAPTOR_ENTITY, VemerioraptorEntity.attributes().create());
		GlobalEntityTypeAttributes.put(Main.VEMERIORAPTOR_EGG_ENTITY, VemerioraptorEggEntity.attributes().create());
	}

	public static <T extends IForgeRegistryEntry<T>> T setup(final T entry, final String name) {
		return setup(entry, new ResourceLocation(Main.MODID, name));
	}

	public static <T extends IForgeRegistryEntry<T>> T setup(final T entry, final ResourceLocation registryName) {
		entry.setRegistryName(registryName);
		return entry;
	}
}
