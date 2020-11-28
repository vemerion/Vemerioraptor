package mod.vemerion.vemerioraptor.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;

import mod.vemerion.vemerioraptor.entity.DinosaurEntity;
import mod.vemerion.vemerioraptor.model.DinosaurModel;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class DinosaurRenderer<E extends DinosaurEntity, M extends DinosaurModel<E>> extends MobRenderer<E, M> {
	
	private static final float CHILD_SCALE = 0.65f;
	
	public DinosaurRenderer(EntityRendererManager renderManagerIn, M model, float shadowSize) {
		super(renderManagerIn, model, shadowSize);
	}
	
	@Override
	public void render(E entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn,
			IRenderTypeBuffer bufferIn, int packedLightIn) {
		matrixStackIn.push();
		if (entityIn.isChild()) {
			matrixStackIn.scale(CHILD_SCALE, CHILD_SCALE, CHILD_SCALE);
		}
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
		matrixStackIn.pop();
	}

	@Override
	public ResourceLocation getEntityTexture(E entity) {
		return entityModel.getTexture();
	}

}
