package mod.vemerion.vemerioraptor.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;

import mod.vemerion.vemerioraptor.Main;
import mod.vemerion.vemerioraptor.entity.VemerioraptorEntity;
import mod.vemerion.vemerioraptor.model.VemerioraptorModel;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class VemerioraptorRenderer extends MobRenderer<VemerioraptorEntity, VemerioraptorModel> {
	
	private static final float CHILD_SCALE = 0.65f;
	
	private static final ResourceLocation TEXTURES = new ResourceLocation(Main.MODID, "textures/entity/vemerioraptor.png");

	public VemerioraptorRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new VemerioraptorModel(), 0.7f);
	}
	
	@Override
	public void render(VemerioraptorEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn,
			IRenderTypeBuffer bufferIn, int packedLightIn) {
		matrixStackIn.push();
		if (entityIn.isChild()) {
			matrixStackIn.scale(CHILD_SCALE, CHILD_SCALE, CHILD_SCALE);
		}
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
		matrixStackIn.pop();
	}

	@Override
	public ResourceLocation getEntityTexture(VemerioraptorEntity entity) {
		return TEXTURES;
	}

}
