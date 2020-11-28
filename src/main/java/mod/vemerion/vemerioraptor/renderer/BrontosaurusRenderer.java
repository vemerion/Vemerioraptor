package mod.vemerion.vemerioraptor.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;

import mod.vemerion.vemerioraptor.Main;
import mod.vemerion.vemerioraptor.entity.BrontosaurusEntity;
import mod.vemerion.vemerioraptor.model.BrontosaurusModel;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class BrontosaurusRenderer extends MobRenderer<BrontosaurusEntity, BrontosaurusModel> {
	
	private static final float CHILD_SCALE = 0.65f;
	
	private static final ResourceLocation TEXTURES = new ResourceLocation(Main.MODID, "textures/entity/brontosaurus.png");

	public BrontosaurusRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new BrontosaurusModel(), 1f);
	}
	
	@Override
	public void render(BrontosaurusEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn,
			IRenderTypeBuffer bufferIn, int packedLightIn) {
		matrixStackIn.push();
		if (entityIn.isChild()) {
			matrixStackIn.scale(CHILD_SCALE, CHILD_SCALE, CHILD_SCALE);
		}
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
		matrixStackIn.pop();
	}

	@Override
	public ResourceLocation getEntityTexture(BrontosaurusEntity entity) {
		return TEXTURES;
	}

}
