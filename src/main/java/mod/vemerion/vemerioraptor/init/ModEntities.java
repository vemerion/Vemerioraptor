package mod.vemerion.vemerioraptor.init;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import com.google.common.collect.ImmutableList;

import mod.vemerion.vemerioraptor.Main;
import mod.vemerion.vemerioraptor.entity.BrontosaurusEntity;
import mod.vemerion.vemerioraptor.entity.DinosaurEggEntity;
import mod.vemerion.vemerioraptor.entity.DinosaurEntity;
import mod.vemerion.vemerioraptor.entity.PlesiosaurusEntity;
import mod.vemerion.vemerioraptor.entity.VemerioraptorEntity;
import mod.vemerion.vemerioraptor.helper.Helper;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.ParallelDispatchEvent;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(value = Main.MODID)
@EventBusSubscriber(bus = Bus.MOD, modid = Main.MODID)
public class ModEntities {

	public static final EntityType<VemerioraptorEntity> VEMERIORAPTOR = null;
	public static final EntityType<BrontosaurusEntity> BRONTOSAURUS = null;
	public static final EntityType<PlesiosaurusEntity> PLESIOSAURUS = null;
	public static final EntityType<DinosaurEggEntity> VEMERIORAPTOR_EGG = null;
	public static final EntityType<DinosaurEggEntity> BRONTOSAURUS_EGG = null;
	public static final EntityType<DinosaurEggEntity> PLESIOSAURUS_EGG = null;

	private static List<DinosaurBuilder<? extends DinosaurEntity>> DINOSAUR_BUILDERS;

	public static List<SpawnEggItem> getSpawnEggs() {
		ImmutableList.Builder<SpawnEggItem> eggs = ImmutableList.builder();
		for (DinosaurBuilder<?> builder : DINOSAUR_BUILDERS) {
			eggs.add(builder.getDinosaurSpawnEgg());
			eggs.add(builder.getDinosaurEggSpawnEgg());
		}
		return eggs.build();
	}

	public static List<DinosaurBuilder<? extends DinosaurEntity>> getDinosaurBuilders() {
		return ImmutableList.copyOf(DINOSAUR_BUILDERS);
	}

	@SubscribeEvent
	public static void onRegisterEntity(RegistryEvent.Register<EntityType<?>> event) {
		IForgeRegistry<EntityType<?>> reg = event.getRegistry();
		for (DinosaurBuilder<?> builder : DINOSAUR_BUILDERS) {
			reg.register(builder.getDinosaur());
			reg.register(builder.getDinosaurEgg());
		}
	}

	@SubscribeEvent
	public static void onRegisterSpawnEggs(RegistryEvent.Register<Item> event) {
		initDinosaurs();
		event.getRegistry().registerAll(getSpawnEggs().toArray(new SpawnEggItem[0]));
	}

	private static void initDinosaurs() {
		DINOSAUR_BUILDERS = new ArrayList<>();

		DINOSAUR_BUILDERS.add(new DinosaurBuilder<VemerioraptorEntity>("vemerioraptor")
				.factory(VemerioraptorEntity::new).classification(EntityClassification.CREATURE).size(1.2f, 2f)
				.spawnCount(3).primaryColor(137, 115, 76).secondaryColor(217, 199, 139)
				.attributes(() -> VemerioraptorEntity.attributes()).english("Vemerioraptor"));

		DINOSAUR_BUILDERS.add(new DinosaurBuilder<BrontosaurusEntity>("brontosaurus").factory(BrontosaurusEntity::new)
				.classification(EntityClassification.CREATURE).size(1.5f, 2.2f).spawnCount(1).primaryColor(51, 107, 61)
				.secondaryColor(90, 110, 147).attributes(() -> BrontosaurusEntity.attributes())
				.english("Brontosaurus"));

		DINOSAUR_BUILDERS.add(new DinosaurBuilder<PlesiosaurusEntity>("plesiosaurus").factory(PlesiosaurusEntity::new)
				.classification(EntityClassification.WATER_CREATURE).size(1.3f, 1f).spawnCount(2)
				.primaryColor(44, 126, 129).attributes(() -> PlesiosaurusEntity.attributes()).english("Plesiosaurus")
				.secondaryColor(172, 228, 228));
	}

	@SubscribeEvent
	public static void onRegisterEntityAttributes(EntityAttributeCreationEvent event) {
		for (DinosaurBuilder<?> builder : DINOSAUR_BUILDERS) {
			event.put(builder.getDinosaur(), builder.attributes.get().create());
			event.put(builder.getDinosaurEgg(), DinosaurEggEntity.attributes().create());
		}
	}

	@SubscribeEvent
	public static void setEntitySpawns(ParallelDispatchEvent event) {
		event.enqueueWork(() -> setEntitySpawnPlacements());
	}

	private static void setEntitySpawnPlacements() {
		EntitySpawnPlacementRegistry.register(BRONTOSAURUS, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND,
				Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::canAnimalSpawn);
		EntitySpawnPlacementRegistry.register(PLESIOSAURUS, EntitySpawnPlacementRegistry.PlacementType.IN_WATER,
				Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, PlesiosaurusEntity::canSpawn);
	}

	public static class DinosaurBuilder<T extends DinosaurEntity> {
		private EntityType.IFactory<T> factory;
		private EntityClassification classification;
		private float width;
		private float height;
		private int spawnCount;
		private int primaryRed;
		private int primaryGreen;
		private int primaryBlue;
		private int secondaryRed;
		private int secondaryGreen;
		private int secondaryBlue;
		private String name;
		private String english;
		private Supplier<AttributeModifierMap.MutableAttribute> attributes;

		EntityType<T> dinosaur;
		EntityType<DinosaurEggEntity> dinosaurEgg;
		SpawnEggItem dinosaurSpawnEgg;
		SpawnEggItem dinosaurEggSpawnEgg;

		private DinosaurBuilder(String name) {
			this.name = name;
		}

		private DinosaurBuilder<T> factory(EntityType.IFactory<T> factory) {
			this.factory = factory;
			return this;
		}

		private DinosaurBuilder<T> classification(EntityClassification classification) {
			this.classification = classification;
			return this;
		}

		private DinosaurBuilder<T> size(float width, float height) {
			this.width = width;
			this.height = height;
			return this;
		}

		private DinosaurBuilder<T> spawnCount(int spawnCount) {
			this.spawnCount = spawnCount;
			return this;
		}

		private DinosaurBuilder<T> primaryColor(int r, int g, int b) {
			this.primaryRed = r;
			this.primaryGreen = g;
			this.primaryBlue = b;
			return this;
		}

		private DinosaurBuilder<T> secondaryColor(int r, int g, int b) {
			this.secondaryRed = r;
			this.secondaryGreen = g;
			this.secondaryBlue = b;
			return this;
		}

		private DinosaurBuilder<T> attributes(Supplier<AttributeModifierMap.MutableAttribute> attributes) {
			this.attributes = attributes;
			return this;
		}

		private DinosaurBuilder<T> english(String s) {
			this.english = s;
			return this;
		}

		public EntityType<T> getDinosaur() {
			if (dinosaur == null)
				dinosaur = Init.setup(
						EntityType.Builder.<T>create(factory, classification).size(width, height).build(""), name);
			return dinosaur;
		}

		public EntityType<DinosaurEggEntity> getDinosaurEgg() {
			if (dinosaurEgg == null) {
				dinosaurEgg = Init.setup(EntityType.Builder
						.<DinosaurEggEntity>create((t, w) -> new DinosaurEggEntity(t, w, getDinosaur(), spawnCount),
								EntityClassification.CREATURE)
						.size(1, 1).build(""), name + "_egg");
			}
			return dinosaurEgg;
		}

		public SpawnEggItem getDinosaurSpawnEgg() {
			if (dinosaurSpawnEgg == null) {
				dinosaurSpawnEgg = Init
						.setup(new SpawnEggItem(getDinosaur(), Helper.color(primaryRed, primaryGreen, primaryBlue, 255),
								Helper.color(secondaryRed, secondaryGreen, secondaryBlue, 255),
								new Item.Properties().group(ItemGroup.SEARCH)), name + "_spawn_egg");
			}
			return dinosaurSpawnEgg;
		}

		public SpawnEggItem getDinosaurEggSpawnEgg() {
			if (dinosaurEggSpawnEgg == null) {
				dinosaurEggSpawnEgg = Init.setup(new SpawnEggItem(getDinosaurEgg(),
						Helper.color(secondaryRed, secondaryGreen, secondaryBlue, 255),
						Helper.color(primaryRed, primaryGreen, primaryBlue, 255),
						new Item.Properties().group(ItemGroup.SEARCH)), name + "_egg_spawn_egg");
			}
			return dinosaurEggSpawnEgg;
		}

		public String getEnglish() {
			return english;
		}

		public String getEggEnglish() {
			return english + " Egg";
		}

		public String getSpawnEggEnglish() {
			return english + " Spawn Egg";
		}

		public String getEggSpawnEggEnglish() {
			return english + " Egg Spawn Egg";
		}
	}

}
