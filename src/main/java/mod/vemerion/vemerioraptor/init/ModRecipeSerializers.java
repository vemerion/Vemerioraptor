package mod.vemerion.vemerioraptor.init;

import mod.vemerion.vemerioraptor.Main;
import mod.vemerion.vemerioraptor.item.DamageIngredient;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(bus = Bus.MOD, modid = Main.MODID)
public class ModRecipeSerializers {

	@SubscribeEvent
	public static void onRegisterRecipeSerialzer(RegistryEvent.Register<IRecipeSerializer<?>> event) {
        CraftingHelper.register(new ResourceLocation(Main.MODID, "damage"), DamageIngredient.Serializer.INSTANCE);
	}
}
