package mod.vemerion.vemerioraptor.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import mod.vemerion.vemerioraptor.Main;
import mod.vemerion.vemerioraptor.entity.VemerioraptorEntity;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

/**
 * Created using Tabula 8.0.0
 */
public class VemerioraptorModel extends DinosaurModel<VemerioraptorEntity> {
	
	private static final ResourceLocation TEXTURES = new ResourceLocation(Main.MODID, "textures/entity/vemerioraptor.png");
	
	public ModelRenderer body;
	public ModelRenderer leftLeg1;
	public ModelRenderer neck1;
	public ModelRenderer tail1;
	public ModelRenderer leftArm1;
	public ModelRenderer rightArm1;
	public ModelRenderer rightLeg1;
	public ModelRenderer leftLeg2;
	public ModelRenderer leftLeg3;
	public ModelRenderer leftLeg4;
	public ModelRenderer head1;
	public ModelRenderer head2;
	public ModelRenderer tail2;
	public ModelRenderer tail3;
	public ModelRenderer tail4;
	public ModelRenderer leftArm2;
	public ModelRenderer leftArm3;
	public ModelRenderer rightArm2;
	public ModelRenderer rightArm3;
	public ModelRenderer rightLeg2;
	public ModelRenderer rightLeg3;
	public ModelRenderer rightLeg4;

	private ModelRenderer[] leftArms;
	private ModelRenderer[] rightArms;
	private float[] armStartRotations;

	public VemerioraptorModel() {
		this.textureWidth = 128;
		this.textureHeight = 128;
		this.leftLeg3 = new ModelRenderer(this, 52, 0);
		this.leftLeg3.setRotationPoint(0.0F, 5.0F, 0.0F);
		this.leftLeg3.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(leftLeg3, -1.0555751236166873F, 0.0F, 0.0F);
		this.rightArm3 = new ModelRenderer(this, 60, 8);
		this.rightArm3.mirror = true;
		this.rightArm3.setRotationPoint(-1.0F, 5.7F, 0.0F);
		this.rightArm3.addBox(-1.5F, 0.0F, -0.5F, 3.0F, 4.0F, 1.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(rightArm3, 0.46914448828868976F, 0.0F, 0.0F);
		this.head2 = new ModelRenderer(this, 92, 0);
		this.head2.setRotationPoint(0.0F, -3.0F, -7.0F);
		this.head2.addBox(-3.0F, -2.0F, -8.0F, 6.0F, 4.0F, 7.0F, 0.0F, 0.0F, 0.0F);
		this.leftArm3 = new ModelRenderer(this, 60, 8);
		this.leftArm3.setRotationPoint(1.0F, 5.7F, 0.0F);
		this.leftArm3.addBox(-1.5F, 0.0F, -0.5F, 3.0F, 4.0F, 1.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(leftArm3, 0.46914448828868976F, 0.0F, 0.0F);
		this.tail2 = new ModelRenderer(this, 0, 30);
		this.tail2.setRotationPoint(0.0F, 0.0F, 9.0F);
		this.tail2.addBox(-3.0F, -3.0F, 0.0F, 6.0F, 6.0F, 8.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(tail2, 0.23457224414434488F, 0.0F, 0.0F);
		this.rightLeg2 = new ModelRenderer(this, 40, 0);
		this.rightLeg2.mirror = true;
		this.rightLeg2.setRotationPoint(0.0F, 6.0F, 0.0F);
		this.rightLeg2.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 6.0F, 3.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(rightLeg2, 0.9382889765773795F, 0.0F, 0.0F);
		this.tail3 = new ModelRenderer(this, 46, 8);
		this.tail3.setRotationPoint(0.0F, 0.0F, 7.0F);
		this.tail3.addBox(-2.0F, -2.0F, 0.0F, 4.0F, 4.0F, 6.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(tail3, 0.03909537541112055F, 0.0F, 0.0F);
		this.rightLeg1 = new ModelRenderer(this, 0, 0);
		this.rightLeg1.mirror = true;
		this.rightLeg1.setRotationPoint(-5.0F, 0.0F, 3.0F);
		this.rightLeg1.addBox(-1.5F, 0.0F, -2.0F, 3.0F, 8.0F, 4.0F, 0.0F, 0.0F, 0.0F);
		this.leftLeg1 = new ModelRenderer(this, 0, 0);
		this.leftLeg1.setRotationPoint(5.0F, 0.0F, 3.0F);
		this.leftLeg1.addBox(-1.5F, 0.0F, -2.0F, 3.0F, 8.0F, 4.0F, 0.0F, 0.0F, 0.0F);
		this.neck1 = new ModelRenderer(this, 68, 0);
		this.neck1.setRotationPoint(0.0F, 0.0F, -10.0F);
		this.neck1.addBox(-3.0F, -3.0F, -12.0F, 6.0F, 6.0F, 12.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(neck1, -0.5026548112585615F, 0.0F, 0.0F);
		this.tail4 = new ModelRenderer(this, 111, 0);
		this.tail4.setRotationPoint(0.0F, 0.0F, 5.1F);
		this.tail4.addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 5.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(tail4, -0.19547687289441354F, 0.0F, 0.0F);
		this.rightArm1 = new ModelRenderer(this, 115, 8);
		this.rightArm1.mirror = true;
		this.rightArm1.setRotationPoint(-4.0F, 1.0F, -11.0F);
		this.rightArm1.addBox(-2.0F, 0.0F, -1.5F, 2.0F, 7.0F, 3.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(rightArm1, 0.46914448828868976F, 0.0F, 0.0F);
		this.leftLeg2 = new ModelRenderer(this, 40, 0);
		this.leftLeg2.setRotationPoint(0.0F, 6.0F, 0.0F);
		this.leftLeg2.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 6.0F, 3.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(leftLeg2, 0.9382889765773795F, 0.0F, 0.0F);
		this.tail1 = new ModelRenderer(this, 82, 22);
		this.tail1.setRotationPoint(0.0F, 1.0F, 4.0F);
		this.tail1.addBox(-4.0F, -4.0F, 0.0F, 8.0F, 8.0F, 10.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(tail1, 0.46914448828868976F, 0.0F, 0.0F);
		this.rightArm2 = new ModelRenderer(this, 12, 10);
		this.rightArm2.mirror = true;
		this.rightArm2.setRotationPoint(0.0F, 6.0F, 0.0F);
		this.rightArm2.addBox(-2.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(rightArm2, -1.0946705281561322F, 0.0F, 0.0F);
		this.rightLeg4 = new ModelRenderer(this, 60, 0);
		this.rightLeg4.mirror = true;
		this.rightLeg4.setRotationPoint(0.0F, 4.5F, 0.0F);
		this.rightLeg4.addBox(-2.0F, 0.0F, -6.0F, 4.0F, 2.0F, 6.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(rightLeg4, 0.35185837453889574F, 0.0F, 0.0F);
		this.rightLeg3 = new ModelRenderer(this, 52, 0);
		this.rightLeg3.mirror = true;
		this.rightLeg3.setRotationPoint(0.0F, 5.0F, 0.0F);
		this.rightLeg3.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(rightLeg3, -1.0555751236166873F, 0.0F, 0.0F);
		this.leftArm1 = new ModelRenderer(this, 115, 8);
		this.leftArm1.setRotationPoint(4.0F, 1.0F, -11.0F);
		this.leftArm1.addBox(0.0F, 0.0F, -1.5F, 2.0F, 7.0F, 3.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(leftArm1, 0.46914448828868976F, 0.0F, 0.0F);
		this.body = new ModelRenderer(this, 0, 0);
		this.body.setRotationPoint(0.0F, 7.4F, 0.0F);
		this.body.addBox(-5.0F, -5.0F, -14.0F, 10.0F, 10.0F, 20.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(body, -0.23457224414434488F, 0.0F, 0.0F);
		this.head1 = new ModelRenderer(this, 60, 18);
		this.head1.setRotationPoint(0.0F, -1.0F, -9.0F);
		this.head1.addBox(-4.0F, -6.0F, -8.0F, 8.0F, 6.0F, 8.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(head1, 0.9717993328367648F, 0.0F, 0.0F);
		this.leftLeg4 = new ModelRenderer(this, 60, 0);
		this.leftLeg4.setRotationPoint(0.0F, 4.5F, 0.0F);
		this.leftLeg4.addBox(-2.0F, 0.0F, -6.0F, 4.0F, 2.0F, 6.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(leftLeg4, 0.35185837453889574F, 0.0F, 0.0F);
		this.leftArm2 = new ModelRenderer(this, 12, 10);
		this.leftArm2.setRotationPoint(0.0F, 6.0F, 0.0F);
		this.leftArm2.addBox(0.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(leftArm2, -1.0946705281561322F, 0.0F, 0.0F);
		this.leftLeg2.addChild(this.leftLeg3);
		this.rightArm2.addChild(this.rightArm3);
		this.head1.addChild(this.head2);
		this.leftArm2.addChild(this.leftArm3);
		this.tail1.addChild(this.tail2);
		this.rightLeg1.addChild(this.rightLeg2);
		this.tail2.addChild(this.tail3);
		this.body.addChild(this.rightLeg1);
		this.body.addChild(this.leftLeg1);
		this.body.addChild(this.neck1);
		this.tail3.addChild(this.tail4);
		this.body.addChild(this.rightArm1);
		this.leftLeg1.addChild(this.leftLeg2);
		this.body.addChild(this.tail1);
		this.rightArm1.addChild(this.rightArm2);
		this.rightLeg3.addChild(this.rightLeg4);
		this.rightLeg2.addChild(this.rightLeg3);
		this.body.addChild(this.leftArm1);
		this.neck1.addChild(this.head1);
		this.leftLeg3.addChild(this.leftLeg4);
		this.leftArm1.addChild(this.leftArm2);

		this.leftArms = new ModelRenderer[] { leftArm1, leftArm2, leftArm3 };
		this.rightArms = new ModelRenderer[] { rightArm1, rightArm2, rightArm3 };
		this.armStartRotations = new float[] { 0.45f, -1.1f, 0.47f };
	}

	@Override
	public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn,
			float red, float green, float blue, float alpha) {
		ImmutableList.of(this.body).forEach((modelRenderer) -> {
			modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
		});
	}

	@Override
	public void setRotationAngles(VemerioraptorEntity entityIn, float limbSwing, float limbSwingAmount,
			float ageInTicks, float netHeadYaw, float headPitch) {
		super.setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		leftLeg1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f + (float) Math.PI) * 2f * limbSwingAmount * 0.25f;
		rightLeg1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f) * 2f * limbSwingAmount * 0.25f;
		if (entityIn.isEating()) {
			neck1.rotateAngleX = MathHelper.cos(ageInTicks * 0.6662f + (float) Math.PI) * 2f * 0.05f
					+ 0.2f;
		} else {
			neck1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f + (float) Math.PI) * 2f * limbSwingAmount * 0.05f
					- 0.503f;
		}
	}

	@Override
	public void setLivingAnimations(VemerioraptorEntity entityIn, float limbSwing, float limbSwingAmount,
			float partialTick) {
		armRotations(leftArms, armStartRotations, 0, entityIn, limbSwing, limbSwingAmount, partialTick);
		armRotations(rightArms, armStartRotations, (float) Math.PI, entityIn, limbSwing, limbSwingAmount, partialTick);
	}

	private void armRotations(ModelRenderer[] arms, float[] armStartRotations, float rotOffset,
			VemerioraptorEntity entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
		swingProgress = entityIn.getSwingProgress(partialTick);

		if (!entityIn.isSwingInProgress) {
			for (int i = 0; i < arms.length; i++) {
				arms[i].rotateAngleX = MathHelper.cos(limbSwing * 0.6662f + rotOffset) * 2f * limbSwingAmount * 0.25f
						* (float) Math.pow(0.9f, i) + armStartRotations[i];

			}
		} else {
			arms[0].rotateAngleX = -MathHelper.sin(swingProgress * (float) Math.PI * 2f) * (float) Math.toRadians(50)
					+ armStartRotations[0] + (float) Math.toRadians(-50);
			arms[1].rotateAngleX = armStartRotations[1];
			arms[2].rotateAngleX = armStartRotations[2];
		}
	}

	protected Iterable<ModelRenderer> getTailParts() {
		return ImmutableList.of(tail1, tail2, tail3, tail4);
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
