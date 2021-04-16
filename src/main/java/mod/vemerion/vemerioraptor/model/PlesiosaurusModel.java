package mod.vemerion.vemerioraptor.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import mod.vemerion.vemerioraptor.Main;
import mod.vemerion.vemerioraptor.entity.PlesiosaurusEntity;
import mod.vemerion.vemerioraptor.helper.Helper;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

/**
 * Created using Tabula 8.0.0
 */
public class PlesiosaurusModel extends DinosaurModel<PlesiosaurusEntity> {

	private static final ResourceLocation TEXTURES = new ResourceLocation(Main.MODID,
			"textures/entity/plesiosaurus.png");

	public ModModelRenderer body1;
	public ModModelRenderer body2;
	public ModModelRenderer body4;
	public ModModelRenderer frontLeftLeg1;
	public ModModelRenderer backLeftLeg1;
	public ModModelRenderer backRightLeg1;
	public ModModelRenderer frontRightLeg1;
	public ModModelRenderer body3;
	public ModModelRenderer tail1;
	public ModModelRenderer tail2;
	public ModModelRenderer neck1;
	public ModModelRenderer neck2;
	public ModModelRenderer neck3;
	public ModModelRenderer head1;
	public ModModelRenderer head2;
	public ModModelRenderer teeth1;
	public ModModelRenderer teeth2;
	public ModModelRenderer frontLeftLeg2;
	public ModModelRenderer backLeftLeg2;
	public ModModelRenderer backRightLeg2;
	public ModModelRenderer frontRightLeg2;

	public PlesiosaurusModel() {
		this.textureWidth = 128;
		this.textureHeight = 128;
		this.body3 = new ModModelRenderer(this, 60, 22);
		this.body3.setRotationPoint(0.0F, 0.0F, 7.0F);
		this.body3.addBox(-6.0F, -6.0F, -3.0F, 12.0F, 12.0F, 6.0F, 0.0F, 0.0F, 0.0F);
		this.backLeftLeg2 = new ModModelRenderer(this, 32, 80);
		this.backLeftLeg2.setRotationPoint(3.0F, 0.0F, 0.0F);
		this.backLeftLeg2.addBox(0.0F, -1.0F, -4.0F, 12.0F, 2.0F, 8.0F, 0.0F, 0.0F, 0.0F);
		this.backRightLeg1 = new ModModelRenderer(this, 46, 0);
		this.backRightLeg1.mirror = true;
		this.backRightLeg1.setRotationPoint(-6.0F, 5.0F, 10.0F);
		this.backRightLeg1.addBox(-4.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(backRightLeg1, 0.0F, 0.19093901284448667F, -0.35185837453889574F);
		this.backLeftLeg1 = new ModModelRenderer(this, 46, 0);
		this.backLeftLeg1.setRotationPoint(6.0F, 5.0F, 10.0F);
		this.backLeftLeg1.addBox(0.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(backLeftLeg1, 0.0F, -0.19093901284448667F, 0.35185837453889574F);
		this.teeth1 = new ModModelRenderer(this, 72, 81);
		this.teeth1.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.teeth1.addBox(-4.0F, 0.0F, -12.0F, 8.0F, 1.0F, 12.0F, 0.0F, 0.0F, 0.0F);
		this.body2 = new ModModelRenderer(this, 60, 0);
		this.body2.setRotationPoint(0.0F, 0.0F, 11.0F);
		this.body2.addBox(-7.0F, -7.0F, -4.0F, 14.0F, 14.0F, 8.0F, 0.0F, 0.0F, 0.0F);
		this.neck3 = new ModModelRenderer(this, 36, 62);
		this.neck3.setRotationPoint(0.0F, 0.0F, -10.0F);
		this.neck3.addBox(-3.0F, -3.0F, -12.0F, 6.0F, 6.0F, 12.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(neck3, 0.11728612207217244F, 0.0F, 0.0F);
		this.frontLeftLeg1 = new ModModelRenderer(this, 46, 0);
		this.frontLeftLeg1.setRotationPoint(6.0F, 5.0F, -10.0F);
		this.frontLeftLeg1.addBox(0.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(frontLeftLeg1, 0.0F, 0.19547687289441354F, 0.35185837453889574F);
		this.head1 = new ModModelRenderer(this, 72, 66);
		this.head1.setRotationPoint(0.0F, -1.0F, -10.0F);
		this.head1.addBox(-4.0F, -3.0F, -12.0F, 8.0F, 3.0F, 12.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(head1, 0.46914448828868976F, 0.0F, 0.0F);
		this.neck1 = new ModModelRenderer(this, 0, 48);
		this.neck1.setRotationPoint(0.0F, 0.0F, -2.0F);
		this.neck1.addBox(-5.0F, -5.0F, -14.0F, 10.0F, 10.0F, 14.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(neck1, -0.23457224414434488F, 0.0F, 0.0F);
		this.body1 = new ModModelRenderer(this, 0, 0);
		this.body1.setRotationPoint(0.0F, 16.0F, 0.0F);
		this.body1.addBox(-8.0F, -8.0F, -7.0F, 16.0F, 16.0F, 14.0F, 0.0F, 0.0F, 0.0F);
		this.tail2 = new ModModelRenderer(this, 0, 30);
		this.tail2.setRotationPoint(0.0F, 0.0F, 11.0F);
		this.tail2.addBox(-5.0F, -1.5F, 0.0F, 10.0F, 3.0F, 12.0F, 0.0F, 0.0F, 0.0F);
		this.tail1 = new ModModelRenderer(this, 84, 28);
		this.tail1.setRotationPoint(0.0F, 0.0F, 2.0F);
		this.tail1.addBox(-4.0F, -3.0F, 0.0F, 8.0F, 6.0F, 12.0F, 0.0F, 0.0F, 0.0F);
		this.teeth2 = new ModModelRenderer(this, 72, 94);
		this.teeth2.setRotationPoint(0.0F, -1.0F, 0.0F);
		this.teeth2.addBox(-4.0F, 0.0F, -12.0F, 8.0F, 1.0F, 12.0F, 0.0F, 0.0F, 0.0F);
		this.head2 = new ModModelRenderer(this, 0, 72);
		this.head2.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.head2.addBox(-4.0F, 0.0F, -12.0F, 8.0F, 2.0F, 12.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(head2, 0.11728612207217244F, 0.0F, 0.0F);
		this.neck2 = new ModModelRenderer(this, 80, 46);
		this.neck2.setRotationPoint(0.0F, 0.0F, -12.0F);
		this.neck2.addBox(-4.0F, -4.0F, -12.0F, 8.0F, 8.0F, 12.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(neck2, -0.23457224414434488F, 0.0F, 0.0F);
		this.frontLeftLeg2 = new ModModelRenderer(this, 32, 80);
		this.frontLeftLeg2.setRotationPoint(3.0F, 0.0F, 0.0F);
		this.frontLeftLeg2.addBox(0.0F, -1.0F, -4.0F, 12.0F, 2.0F, 8.0F, 0.0F, 0.0F, 0.0F);
		this.frontRightLeg1 = new ModModelRenderer(this, 46, 0);
		this.frontRightLeg1.mirror = true;
		this.frontRightLeg1.setRotationPoint(-6.0F, 5.0F, -10.0F);
		this.frontRightLeg1.addBox(-4.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(frontRightLeg1, 0.0F, -0.19093901284448667F, -0.35185837453889574F);
		this.body4 = new ModModelRenderer(this, 36, 40);
		this.body4.setRotationPoint(0.0F, 0.0F, -11.0F);
		this.body4.addBox(-7.0F, -7.0F, -4.0F, 14.0F, 14.0F, 8.0F, 0.0F, 0.0F, 0.0F);
		this.backRightLeg2 = new ModModelRenderer(this, 32, 80);
		this.backRightLeg2.mirror = true;
		this.backRightLeg2.setRotationPoint(-3.0F, 0.0F, 0.0F);
		this.backRightLeg2.addBox(-12.0F, -1.0F, -4.0F, 12.0F, 2.0F, 8.0F, 0.0F, 0.0F, 0.0F);
		this.frontRightLeg2 = new ModModelRenderer(this, 32, 80);
		this.frontRightLeg2.mirror = true;
		this.frontRightLeg2.setRotationPoint(-3.0F, 0.0F, 0.0F);
		this.frontRightLeg2.addBox(-12.0F, -1.0F, -4.0F, 12.0F, 2.0F, 8.0F, 0.0F, 0.0F, 0.0F);
		this.body2.addChild(this.body3);
		this.backLeftLeg1.addChild(this.backLeftLeg2);
		this.body1.addChild(this.backRightLeg1);
		this.body1.addChild(this.backLeftLeg1);
		this.head1.addChild(this.teeth1);
		this.body1.addChild(this.body2);
		this.neck2.addChild(this.neck3);
		this.body1.addChild(this.frontLeftLeg1);
		this.neck3.addChild(this.head1);
		this.body4.addChild(this.neck1);
		this.tail1.addChild(this.tail2);
		this.body3.addChild(this.tail1);
		this.head2.addChild(this.teeth2);
		this.head1.addChild(this.head2);
		this.neck1.addChild(this.neck2);
		this.frontLeftLeg1.addChild(this.frontLeftLeg2);
		this.body1.addChild(this.frontRightLeg1);
		this.body1.addChild(this.body4);
		this.backRightLeg1.addChild(this.backRightLeg2);
		this.frontRightLeg1.addChild(this.frontRightLeg2);
	}

	@Override
	public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn,
			float red, float green, float blue, float alpha) {
		ImmutableList.of(this.body1).forEach((model) -> {
			model.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
		});
	}

	@Override
	public void setRotationAngles(PlesiosaurusEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks,
			float netHeadYaw, float headPitch) {
		super.setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

		// Look
		ImmutableList.of(neck1, neck2, neck3, head1).forEach(model -> {
			model.rotateAngleY = model.startRotY + Helper.toRad(netHeadYaw) * 0.3f;
			model.rotateAngleX = model.startRotX + Helper.toRad(headPitch) * 0.3f;
		});

		// Jaw
		if (!entityIn.isSwingInProgress) {
			head2.rotateAngleX = head2.startRotX - MathHelper.cos(ageInTicks / 20) * Helper.toRad(5);
		} else {
			head2.rotateAngleX = head2.startRotX + MathHelper.sin(swingProgress * Helper.PI * 2) * Helper.toRad(10);
		}

		// Paddles
		rotatePaddles(entityIn, limbSwing, limbSwingAmount);
	}

	private void rotatePaddles(PlesiosaurusEntity entityIn, float limbSwing, float limbSwingAmount) {
		frontRightLeg1.rotateAngleZ = frontRightLeg1.startRotZ
				+ MathHelper.cos(limbSwing * 0.5f) * limbSwingAmount * 0.5f;
		backRightLeg1.rotateAngleZ = backRightLeg1.startRotZ
				+ MathHelper.cos(limbSwing * 0.5f) * limbSwingAmount * 0.5f;
		frontLeftLeg1.rotateAngleZ = frontLeftLeg1.startRotZ
				- MathHelper.cos(limbSwing * 0.5f) * limbSwingAmount * 0.5f;
		backLeftLeg1.rotateAngleZ = backLeftLeg1.startRotZ - MathHelper.cos(limbSwing * 0.5f) * limbSwingAmount * 0.5f;
	}

	/**
	 * This is a helper function from Tabula to set the rotation of model parts
	 */
	public void setRotateAngle(ModModelRenderer renderer, float x, float y, float z) {
		renderer.initRot(x, y, z);
	}

	@Override
	public ResourceLocation getTexture() {
		return TEXTURES;
	}

	@Override
	protected Iterable<ModelRenderer> getTailParts() {
		return ImmutableList.of(tail1, tail2);
	}
}
