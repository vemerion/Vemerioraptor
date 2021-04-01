package mod.vemerion.vemerioraptor.datagen;

import java.util.function.Consumer;

import mod.vemerion.vemerioraptor.Main;
import mod.vemerion.vemerioraptor.init.ModItems;
import net.minecraft.data.CookingRecipeBuilder;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;

public class ModRecipeProvider extends RecipeProvider {

	public ModRecipeProvider(DataGenerator generatorIn) {
		super(generatorIn);
	}

	@Override
	protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
		CookingRecipeBuilder
				.smeltingRecipe(Ingredient.fromItems(ModItems.BRONTOSAURUS_MEAT), ModItems.COOKED_BRONTOSAURUS_MEAT,
						0.35f, 200)
				.addCriterion("has_brontosaurus_meat", hasItem(ModItems.BRONTOSAURUS_MEAT))
				.build(consumer, new ResourceLocation(Main.MODID, "cooked_brontosaurus_meat"));

		ShapelessRecipeBuilder.shapelessRecipe(Items.GUNPOWDER).addIngredient(ModItems.VEMERIORAPTOR_CLAW)
				.addCriterion("has_vemerioraptor_claw", hasItem(ModItems.VEMERIORAPTOR_CLAW))
				.build(consumer, new ResourceLocation(Main.MODID, "vemerioraptor_claw_to_gunpowder"));

		ShapedRecipeBuilder.shapedRecipe(ModItems.VEMERIORAPTOR_CLAW_WEAPON).key('X', ModItems.VEMERIORAPTOR_CLAW)
				.key('#', Tags.Items.RODS_WOODEN).patternLine("X").patternLine("#").patternLine("#")
				.addCriterion("has_vemerioraptor_claw", hasItem(ModItems.VEMERIORAPTOR_CLAW)).build(consumer);
	}

}
