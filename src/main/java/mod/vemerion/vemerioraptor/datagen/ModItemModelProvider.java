package mod.vemerion.vemerioraptor.datagen;

import mod.vemerion.vemerioraptor.Main;
import mod.vemerion.vemerioraptor.init.ModEntities;
import mod.vemerion.vemerioraptor.init.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {

	public ModItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
		super(generator, Main.MODID, existingFileHelper);
	}

	@Override
	protected void registerModels() {
		for (Item egg : ModEntities.getSpawnEggs())
			egg(egg);
		simpleItem(ModItems.MANURE);
		simpleItem(ModItems.VEMERIORAPTOR_CLAW);
		simpleItem(ModItems.BRONTOSAURUS_MEAT);
		simpleItem(ModItems.COOKED_BRONTOSAURUS_MEAT);
		simpleItem(ModItems.VEMERIORAPTOR_CLAW_WEAPON);
		simpleItem(ModItems.PLESIOSAURUS_PADDLE);
	}

	private void egg(Item item) {
		withExistingParent(item.getRegistryName().getPath(), mcLoc(ITEM_FOLDER + "/template_spawn_egg"));
	}

	private void simpleItem(Item item) {
		String name = item.getRegistryName().getPath();
		singleTexture(name, mcLoc(ITEM_FOLDER + "/generated"), "layer0", modLoc(ITEM_FOLDER + "/" + name));
	}
}
