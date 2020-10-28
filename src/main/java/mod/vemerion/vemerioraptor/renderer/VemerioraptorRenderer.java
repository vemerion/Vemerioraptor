package mod.vemerion.vemerioraptor.renderer;

import mod.vemerion.vemerioraptor.Main;
import mod.vemerion.vemerioraptor.entity.VemerioraptorEntity;
import mod.vemerion.vemerioraptor.model.VemerioraptorModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class VemerioraptorRenderer extends MobRenderer<VemerioraptorEntity, VemerioraptorModel> {
	private static final ResourceLocation TEXTURES = new ResourceLocation(Main.MODID, "textures/entity/vemerioraptor.png");

	public VemerioraptorRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new VemerioraptorModel(), 0.7f);
	}

	@Override
	public ResourceLocation getEntityTexture(VemerioraptorEntity entity) {
		return TEXTURES;
	}

}
