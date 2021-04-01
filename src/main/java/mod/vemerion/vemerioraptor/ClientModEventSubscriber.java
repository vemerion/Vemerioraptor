package mod.vemerion.vemerioraptor;

import mod.vemerion.vemerioraptor.init.ModEntities;
import mod.vemerion.vemerioraptor.model.BrontosaurusEggModel;
import mod.vemerion.vemerioraptor.model.BrontosaurusModel;
import mod.vemerion.vemerioraptor.model.PlesiosaurusModel;
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
		RenderingRegistry.registerEntityRenderingHandler(ModEntities.VEMERIORAPTOR,
				(manager) -> new DinosaurRenderer<>(manager, new VemerioraptorModel(), 0.4f));
		RenderingRegistry.registerEntityRenderingHandler(ModEntities.BRONTOSAURUS,
				(manager) -> new DinosaurRenderer<>(manager, new BrontosaurusModel(), 1f));
		RenderingRegistry.registerEntityRenderingHandler(ModEntities.PLESIOSAURUS,
				(manager) -> new DinosaurRenderer<>(manager, new PlesiosaurusModel(), 1f));
		
		RenderingRegistry.registerEntityRenderingHandler(ModEntities.VEMERIORAPTOR_EGG,
				manager -> new DinosaurEggRenderer(manager, new VemerioraptorEggModel()));
		RenderingRegistry.registerEntityRenderingHandler(ModEntities.BRONTOSAURUS_EGG,
				manager -> new DinosaurEggRenderer(manager, new BrontosaurusEggModel()));
		RenderingRegistry.registerEntityRenderingHandler(ModEntities.PLESIOSAURUS_EGG, // TODO: Update model
				manager -> new DinosaurEggRenderer(manager, new BrontosaurusEggModel()));
	}
}
