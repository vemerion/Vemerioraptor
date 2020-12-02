package mod.vemerion.vemerioraptor.renderer;

import mod.vemerion.vemerioraptor.entity.DinosaurEggEntity;
import mod.vemerion.vemerioraptor.model.DinosaurEggModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class DinosaurEggRenderer extends MobRenderer<DinosaurEggEntity, DinosaurEggModel> {

	public DinosaurEggRenderer(EntityRendererManager renderManagerIn, DinosaurEggModel model) {
		super(renderManagerIn, model, 0);
	}

	@Override
	public ResourceLocation getEntityTexture(DinosaurEggEntity entity) {
		return entityModel.getTexture();
	}

}
