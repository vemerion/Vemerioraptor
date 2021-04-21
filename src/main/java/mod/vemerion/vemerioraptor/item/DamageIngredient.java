package mod.vemerion.vemerioraptor.item;

import java.util.stream.Stream;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.IIngredientSerializer;

public class DamageIngredient extends Ingredient {

	private final ItemStack stack;

	public DamageIngredient(ItemStack stack) {
		super(Stream.of(new Ingredient.SingleItemList(stack)));
		this.stack = stack;
	}

	@Override
	public boolean test(ItemStack input) {
		if (input == null)
			return false;
		return stack.getItem() == input.getItem() && stack.getDamage() == input.getDamage();
	}

	@Override
	public boolean isSimple() {
		return false;
	}

	@Override
	public IIngredientSerializer<? extends Ingredient> getSerializer() {
		return Serializer.INSTANCE;
	}

	@Override
	public JsonElement serialize() {
		JsonObject json = new JsonObject();
		json.addProperty("type", CraftingHelper.getID(Serializer.INSTANCE).toString());
		json.addProperty("item", stack.getItem().getRegistryName().toString());
		json.addProperty("damage", stack.getDamage());
		return json;
	}

	public static class Serializer implements IIngredientSerializer<DamageIngredient> {
		public static final Serializer INSTANCE = new Serializer();

		@Override
		public DamageIngredient parse(PacketBuffer buffer) {
			return new DamageIngredient(buffer.readItemStack());
		}

		@Override
		public DamageIngredient parse(JsonObject json) {
			ItemStack stack = CraftingHelper.getItemStack(json, true);
			stack.setDamage(JSONUtils.getInt(json, "damage", 0));
			return new DamageIngredient(stack);
		}

		@Override
		public void write(PacketBuffer buffer, DamageIngredient ingredient) {
			buffer.writeItemStack(ingredient.stack);
		}
	}
}
