package mod.vemerion.vemerioraptor;

import java.awt.Color;

import mod.vemerion.vemerioraptor.entity.BrontosaurusEntity;
import mod.vemerion.vemerioraptor.entity.DinosaurEggEntity;
import mod.vemerion.vemerioraptor.entity.VemerioraptorEntity;
import mod.vemerion.vemerioraptor.item.VemerioraptorClawWeaponItem;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.item.BoneMealItem;
import net.minecraft.item.Food;
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
	private static EntityType<BrontosaurusEntity> brontosaurusType;

	private static EntityType<DinosaurEggEntity> vemerioraptorEggType;
	private static EntityType<DinosaurEggEntity> brontosaurusEggType;

	@SubscribeEvent
	public static void registerEntity(RegistryEvent.Register<EntityType<?>> event) {
		event.getRegistry().register(setup(vemerioraptorType, "vemerioraptor_entity"));
		event.getRegistry().register(setup(brontosaurusType, "brontosaurus_entity"));

		event.getRegistry().register(setup(vemerioraptorEggType, "vemerioraptor_egg_entity"));
		event.getRegistry().register(setup(brontosaurusEggType, "brontosaurus_egg_entity"));
	}

	@SubscribeEvent
	public static void registerItem(RegistryEvent.Register<Item> event) {
		initEntityTypes();

		event.getRegistry()
				.register(setup(new Item(new Item.Properties().group(ItemGroup.SEARCH)), "vemerioraptor_claw_item"));
		event.getRegistry().register(setup(new VemerioraptorClawWeaponItem(), "vemerioraptor_claw_weapon_item"));

		Food brontosaurusMeat = new Food.Builder().hunger(10).saturation(1).meat().build();
		Food cookedBrontosaurusMeat = new Food.Builder().hunger(20).saturation(1).meat().build();
		Item brontosaurusMeatItem = new Item((new Item.Properties()).group(ItemGroup.SEARCH).food(brontosaurusMeat));
		Item cookedBrontosaurusMeatItem = new Item(
				(new Item.Properties()).group(ItemGroup.SEARCH).food(cookedBrontosaurusMeat));
		Item manureItem = new BoneMealItem(new Item.Properties().group(ItemGroup.SEARCH));
		event.getRegistry().register(setup(brontosaurusMeatItem, "brontosaurus_meat_item"));
		event.getRegistry().register(setup(cookedBrontosaurusMeatItem, "cooked_brontosaurus_meat_item"));
		event.getRegistry().register(setup(manureItem, "manure_item"));

		Item vemerioraptorSpawnEgg = new SpawnEggItem(vemerioraptorType, new Color(137, 115, 76).getRGB(),
				new Color(217, 199, 139).getRGB(), new Item.Properties().group(ItemGroup.SEARCH));
		Item brontosaurusSpawnEgg = new SpawnEggItem(brontosaurusType, new Color(51, 107, 61).getRGB(),
				new Color(90, 110, 147).getRGB(), new Item.Properties().group(ItemGroup.SEARCH));

		Item vemerioraptorEggSpawnEgg = new SpawnEggItem(vemerioraptorEggType, new Color(217, 199, 139).getRGB(),
				new Color(137, 115, 76).getRGB(), new Item.Properties().group(ItemGroup.SEARCH));
		Item brontosaurusEggSpawnEgg = new SpawnEggItem(brontosaurusEggType, new Color(90, 110, 147).getRGB(),
				new Color(51, 107, 61).getRGB(), new Item.Properties().group(ItemGroup.SEARCH));

		event.getRegistry().registerAll(setup(vemerioraptorSpawnEgg, "vemerioraptor_spawn_egg"),
				setup(brontosaurusSpawnEgg, "brontosaurus_spawn_egg"),
				setup(vemerioraptorEggSpawnEgg, "vemerioraptor_egg_spawn_egg"),
				setup(brontosaurusEggSpawnEgg, "brontosaurus_egg_spawn_egg"));
	}

	private static void initEntityTypes() {
		vemerioraptorType = EntityType.Builder
				.<VemerioraptorEntity>create(VemerioraptorEntity::new, EntityClassification.CREATURE).size(1.2f, 2f)
				.build("vemerioraptor_entity");
		brontosaurusType = EntityType.Builder
				.<BrontosaurusEntity>create(BrontosaurusEntity::new, EntityClassification.CREATURE).size(1.5f, 2.2f)
				.build("brontosaurus_entity");

		vemerioraptorEggType = EntityType.Builder
				.<DinosaurEggEntity>create((t, w) -> new DinosaurEggEntity(t, w, vemerioraptorType, 3),
						EntityClassification.CREATURE)
				.size(1, 1).build("vemerioraptor_egg_entity");
		brontosaurusEggType = EntityType.Builder
				.<DinosaurEggEntity>create((t, w) -> new DinosaurEggEntity(t, w, brontosaurusType, 1),
						EntityClassification.CREATURE)
				.size(1, 1).build("brontosaurus_egg_entity");
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
		GlobalEntityTypeAttributes.put(vemerioraptorType, VemerioraptorEntity.attributes().create());
		GlobalEntityTypeAttributes.put(brontosaurusType, BrontosaurusEntity.attributes().create());

		GlobalEntityTypeAttributes.put(vemerioraptorEggType, DinosaurEggEntity.attributes().create());
		GlobalEntityTypeAttributes.put(brontosaurusEggType, DinosaurEggEntity.attributes().create());
	}

	public static <T extends IForgeRegistryEntry<T>> T setup(final T entry, final String name) {
		return setup(entry, new ResourceLocation(Main.MODID, name));
	}

	public static <T extends IForgeRegistryEntry<T>> T setup(final T entry, final ResourceLocation registryName) {
		entry.setRegistryName(registryName);
		return entry;
	}
}
