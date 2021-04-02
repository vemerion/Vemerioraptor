package mod.vemerion.vemerioraptor.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import mod.vemerion.vemerioraptor.Main;
import mod.vemerion.vemerioraptor.entity.DinosaurEggEntity;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.ResourceLocation;

/**
 * Created using Tabula 8.0.0
 */
public class PlesiosaurusEggModel extends DinosaurEggModel {
	
	private static final ResourceLocation TEXTURES = new ResourceLocation(Main.MODID, "textures/entity/plesiosaurus_egg.png");

	
	public ModelRenderer egg1Middle;
	public ModelRenderer egg2Middle;
	public ModelRenderer seagrass1;
	public ModelRenderer seagrass2;
	public ModelRenderer egg1Bottom;
	public ModelRenderer egg1Top;
	public ModelRenderer egg2Bottom;
	public ModelRenderer egg2Top;

	public PlesiosaurusEggModel() {
		this.textureWidth = 64;
		this.textureHeight = 32;
		this.egg1Middle = new ModelRenderer(this, 16, 0);
		this.egg1Middle.setRotationPoint(-4.0F, 19.0F, -4.0F);
		this.egg1Middle.addBox(-3.0F, -4.0F, -3.0F, 6.0F, 8.0F, 6.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(egg1Middle, 0.23457224414434488F, 0.8600982340775168F, -0.1563815016444822F);
		this.egg2Bottom = new ModelRenderer(this, 34, 0);
		this.egg2Bottom.setRotationPoint(0.0F, 7.0F, 0.0F);
		this.egg2Bottom.addBox(-2.5F, -3.0F, -2.5F, 5.0F, 1.0F, 5.0F, 0.0F, 0.0F, 0.0F);
		this.seagrass1 = new ModelRenderer(this, 0, 0);
		this.seagrass1.setRotationPoint(0.0F, 8.0F, -1.0F);
		this.seagrass1.addBox(0.0F, 0.0F, 0.0F, 8.0F, 16.0F, 0.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(seagrass1, 0.0F, 0.6255260065779288F, 0.0F);
		this.egg1Top = new ModelRenderer(this, 40, 6);
		this.egg1Top.setRotationPoint(0.0F, -2.0F, 0.0F);
		this.egg1Top.addBox(-2.0F, -3.0F, -2.0F, 4.0F, 1.0F, 4.0F, 0.0F, 0.0F, 0.0F);
		this.seagrass2 = new ModelRenderer(this, 0, 0);
		this.seagrass2.setRotationPoint(-7.0F, 8.0F, 4.0F);
		this.seagrass2.addBox(0.0F, 0.0F, 0.0F, 8.0F, 16.0F, 0.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(seagrass2, 0.0F, -0.5473352640780661F, 0.0F);
		this.egg1Bottom = new ModelRenderer(this, 34, 0);
		this.egg1Bottom.setRotationPoint(0.0F, 7.0F, 0.0F);
		this.egg1Bottom.addBox(-2.5F, -3.0F, -2.5F, 5.0F, 1.0F, 5.0F, 0.0F, 0.0F, 0.0F);
		this.egg2Middle = new ModelRenderer(this, 16, 0);
		this.egg2Middle.setRotationPoint(4.0F, 20.0F, 3.0F);
		this.egg2Middle.addBox(-3.0F, -4.0F, -3.0F, 6.0F, 8.0F, 6.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(egg2Middle, 0.5473352640780661F, -1.13376586611655F, -0.19547687289441354F);
		this.egg2Top = new ModelRenderer(this, 40, 6);
		this.egg2Top.setRotationPoint(0.0F, -2.0F, 0.0F);
		this.egg2Top.addBox(-2.0F, -3.0F, -2.0F, 4.0F, 1.0F, 4.0F, 0.0F, 0.0F, 0.0F);
		this.egg2Middle.addChild(this.egg2Bottom);
		this.egg1Middle.addChild(this.egg1Top);
		this.egg1Middle.addChild(this.egg1Bottom);
		this.egg2Middle.addChild(this.egg2Top);
	}

	@Override
	public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn,
			float red, float green, float blue, float alpha) {
		ImmutableList.of(this.egg1Middle, this.seagrass1, this.seagrass2, this.egg2Middle).forEach((modelRenderer) -> {
			modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
		});
	}

	@Override
	public void setRotationAngles(DinosaurEggEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks,
			float netHeadYaw, float headPitch) {
	}

	/**
	 * This is a helper function from Tabula to set the rotation of model parts
	 */
	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	@Override
	public ResourceLocation getTexture() {
		return TEXTURES;
	}

}
