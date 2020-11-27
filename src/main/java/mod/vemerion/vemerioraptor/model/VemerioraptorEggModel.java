package mod.vemerion.vemerioraptor.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import mod.vemerion.vemerioraptor.entity.VemerioraptorEggEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

/**
 * Created using Tabula 8.0.0
 */
public class VemerioraptorEggModel extends EntityModel<VemerioraptorEggEntity> {
	public ModelRenderer egg1;
	public ModelRenderer egg2;
	public ModelRenderer egg3;
	public ModelRenderer grass1;
	public ModelRenderer grass2;
	public ModelRenderer grass3;

	public VemerioraptorEggModel() {
		this.textureWidth = 32;
		this.textureHeight = 32;
		this.egg1 = new ModelRenderer(this, 0, 0);
		this.egg1.setRotationPoint(0.0F, 20.0F, 2.0F);
		this.egg1.addBox(0.0F, 0.0F, 0.0F, 7.0F, 4.0F, 4.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(egg1, 0.0F, 0.35185837453889574F, 0.0F);
		this.egg2 = new ModelRenderer(this, 0, 0);
		this.egg2.setRotationPoint(-1.0F, 20.0F, -2.0F);
		this.egg2.addBox(0.0F, 0.0F, 0.0F, 7.0F, 4.0F, 4.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(egg2, 0.0F, -1.876577953154759F, 0.0F);
		this.egg3 = new ModelRenderer(this, 0, 0);
		this.egg3.setRotationPoint(2.0F, 20.0F, -2.0F);
		this.egg3.addBox(0.0F, 0.0F, 0.0F, 7.0F, 4.0F, 4.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(egg3, 0.0F, 2.1893410563122644F, 0.0F);
		this.grass1 = new ModelRenderer(this, 0, 8);
		this.grass1.setRotationPoint(0.0F, 19.0F, 0.0F);
		this.grass1.addBox(0.0F, 0.0F, 0.0F, 9.0F, 5.0F, 0.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(grass1, 0.0F, 0.3127630032889644F, 0.0F);
		this.grass3 = new ModelRenderer(this, 0, 8);
		this.grass3.setRotationPoint(-7.0F, 19.0F, -2.0F);
		this.grass3.addBox(0.0F, 0.0F, 0.0F, 9.0F, 5.0F, 0.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(grass3, 0.0F, 1.080707840876956F, 0.0F);
		this.grass2 = new ModelRenderer(this, 0, 8);
		this.grass2.setRotationPoint(-3.0F, 19.0F, 7.0F);
		this.grass2.addBox(0.0F, 0.0F, 0.0F, 9.0F, 5.0F, 0.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(grass2, 0.0F, -0.19547687289441354F, 0.0F);
	}

	@Override
	public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn,
			float red, float green, float blue, float alpha) {
		ImmutableList.of(this.egg1, this.egg2, this.egg3, this.grass1, this.grass3, this.grass2)
				.forEach((modelRenderer) -> {
					modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue,
							alpha);
				});
	}

	@Override
	public void setRotationAngles(VemerioraptorEggEntity entityIn, float limbSwing, float limbSwingAmount,
			float ageInTicks, float netHeadYaw, float headPitch) {
	}

	/**
	 * This is a helper function from Tabula to set the rotation of model parts
	 */
	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
