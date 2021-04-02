package mod.vemerion.vemerioraptor.datagen;

import mod.vemerion.vemerioraptor.Main;
import mod.vemerion.vemerioraptor.init.ModEntities;
import mod.vemerion.vemerioraptor.init.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class ModLanguageProvider extends LanguageProvider {

	public ModLanguageProvider(DataGenerator gen) {
		super(gen, Main.MODID, "en_us");
	}

	@Override
	protected void addTranslations() {
		for (ModEntities.DinosaurBuilder<?> builder : ModEntities.getDinosaurBuilders()) {
			add(builder.getDinosaur(), builder.getEnglish());
			add(builder.getDinosaurEgg(), builder.getEggEnglish());
			add(builder.getDinosaurSpawnEgg(), builder.getSpawnEggEnglish());
			add(builder.getDinosaurEggSpawnEgg(), builder.getEggSpawnEggEnglish());
		}

		add(ModItems.BRONTOSAURUS_MEAT, "Brontosaurus Meat");
		add(ModItems.COOKED_BRONTOSAURUS_MEAT, "Cooked Brontosaurus Meat");
		add(ModItems.MANURE, "Manure");
		add(ModItems.VEMERIORAPTOR_CLAW, "Vemerioraptor Claw");
		add(ModItems.VEMERIORAPTOR_CLAW_WEAPON, "Vemerioraptor Claw Weapon");
		add(ModItems.PLESIOSAURUS_PADDLE, "Plesiosaurus Paddle");
		add("itemGroup.vemerioraptor", "Vemerioraptor");
	}

}
