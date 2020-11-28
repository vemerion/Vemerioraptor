package mod.vemerion.vemerioraptor;

import mod.vemerion.vemerioraptor.renderer.BrontosaurusRenderer;
import mod.vemerion.vemerioraptor.renderer.VemerioraptorEggRenderer;
import mod.vemerion.vemerioraptor.renderer.VemerioraptorRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(modid = Main.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEventSubscriber {

	@SubscribeEvent
	public static void clientSetup(FMLClientSetupEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(Main.VEMERIORAPTOR_ENTITY, VemerioraptorRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(Main.BRONTOSAURUS_ENTITY, BrontosaurusRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(Main.VEMERIORAPTOR_EGG_ENTITY, VemerioraptorEggRenderer::new);
	}
}
