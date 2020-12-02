package mod.vemerion.vemerioraptor;

import mod.vemerion.vemerioraptor.model.BrontosaurusEggModel;
import mod.vemerion.vemerioraptor.model.BrontosaurusModel;
import mod.vemerion.vemerioraptor.model.VemerioraptorEggModel;
import mod.vemerion.vemerioraptor.model.VemerioraptorModel;
import mod.vemerion.vemerioraptor.renderer.DinosaurRenderer;
import mod.vemerion.vemerioraptor.renderer.DinosaurEggRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(modid = Main.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEventSubscriber {

	@SubscribeEvent
	public static void clientSetup(FMLClientSetupEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(Main.VEMERIORAPTOR_ENTITY,
				(manager) -> new DinosaurRenderer<>(manager, new VemerioraptorModel(), 0.4f));
		RenderingRegistry.registerEntityRenderingHandler(Main.BRONTOSAURUS_ENTITY,
				(manager) -> new DinosaurRenderer<>(manager, new BrontosaurusModel(), 1f));
		
		RenderingRegistry.registerEntityRenderingHandler(Main.VEMERIORAPTOR_EGG_ENTITY,
				manager -> new DinosaurEggRenderer(manager, new VemerioraptorEggModel()));
		RenderingRegistry.registerEntityRenderingHandler(Main.BRONTOSAURUS_EGG_ENTITY,
				manager -> new DinosaurEggRenderer(manager, new BrontosaurusEggModel()));
	}
}
