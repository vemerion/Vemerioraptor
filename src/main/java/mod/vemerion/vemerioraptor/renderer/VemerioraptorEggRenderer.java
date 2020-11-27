package mod.vemerion.vemerioraptor.renderer;

import mod.vemerion.vemerioraptor.Main;
import mod.vemerion.vemerioraptor.entity.VemerioraptorEggEntity;
import mod.vemerion.vemerioraptor.model.VemerioraptorEggModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class VemerioraptorEggRenderer extends MobRenderer<VemerioraptorEggEntity, VemerioraptorEggModel> {
	private static final ResourceLocation TEXTURES = new ResourceLocation(Main.MODID, "textures/entity/vemerioraptor_egg.png");

	public VemerioraptorEggRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new VemerioraptorEggModel(), 0);
	}

	@Override
	public ResourceLocation getEntityTexture(VemerioraptorEggEntity entity) {
		return TEXTURES;
	}

}
