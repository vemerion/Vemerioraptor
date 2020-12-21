package mod.vemerion.vemerioraptor.model;

import java.util.List;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import mod.vemerion.vemerioraptor.Main;
import mod.vemerion.vemerioraptor.entity.BrontosaurusEntity;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

/**
 * Created using Tabula 8.0.0
 */
public class BrontosaurusModel extends DinosaurModel<BrontosaurusEntity> {

	private static final ResourceLocation TEXTURES = new ResourceLocation(Main.MODID,
			"textures/entity/brontosaurus.png");

	public ModelRenderer body;
	public ModelRenderer backLeftLeg;
	public ModelRenderer frontLeftLeg;
	public ModelRenderer frontRightLeg;
	public ModelRenderer backRightLeg;
	public ModelRenderer neck1;
	public ModelRenderer tail1;
	public ModelRenderer neck2;
	public ModelRenderer neck3;
	public ModelRenderer head;
	public ModelRenderer tail2;
	public ModelRenderer tail3;
	public ModelRenderer tail4;
	public ModelRenderer mouth1;
	public ModelRenderer mouth2;

	public BrontosaurusModel() {
		this.textureWidth = 128;
		this.textureHeight = 128;
		this.backLeftLeg = new ModelRenderer(this, 0, 52);
		this.backLeftLeg.setRotationPoint(6.0F, -4.0F, 0.0F);
		this.backLeftLeg.addBox(0.0F, 0.0F, -3.5F, 7.0F, 28.0F, 7.0F, 0.0F, 0.0F, 0.0F);
		this.frontLeftLeg = new ModelRenderer(this, 0, 52);
		this.frontLeftLeg.setRotationPoint(6.0F, -4.0F, -20.0F);
		this.frontLeftLeg.addBox(0.0F, 0.0F, -3.5F, 7.0F, 28.0F, 7.0F, 0.0F, 0.0F, 0.0F);
		this.neck3 = new ModelRenderer(this, 0, 87);
		this.neck3.setRotationPoint(0.0F, 0.0F, -16.0F);
		this.neck3.addBox(-3.0F, -3.0F, -14.0F, 6.0F, 6.0F, 14.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(neck3, -0.23457224414434488F, 0.0F, 0.0F);
		this.neck1 = new ModelRenderer(this, 28, 52);
		this.neck1.setRotationPoint(0.0F, -1.0F, -23.0F);
		this.neck1.addBox(-5.0F, -5.0F, -18.0F, 10.0F, 10.0F, 18.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(neck1, -0.46914448828868976F, 0.0F, 0.0F);
		this.tail2 = new ModelRenderer(this, 70, 68);
		this.tail2.setRotationPoint(0.0F, 0.0F, 15.0F);
		this.tail2.addBox(-5.0F, -5.0F, 0.0F, 10.0F, 10.0F, 14.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(tail2, 0.11728612207217244F, 0.0F, 0.0F);
		this.head = new ModelRenderer(this, 0, 0);
		this.head.setRotationPoint(0.0F, -3.0F, -12.0F);
        this.head.addBox(-4.0F, -1.0F, -8.0F, 8.0F, 12.0F, 8.0F, 0.0F, 0.0F, 0.0F); 
		this.backRightLeg = new ModelRenderer(this, 0, 52);
		this.backRightLeg.mirror = true;
		this.backRightLeg.setRotationPoint(-5.0F, -4.0F, 0.0F);
		this.backRightLeg.addBox(-8.0F, 0.0F, -3.5F, 7.0F, 28.0F, 7.0F, 0.0F, 0.0F, 0.0F);
		this.body = new ModelRenderer(this, 0, 0);
		this.body.setRotationPoint(0.0F, 0.0F, 10.0F);
		this.body.addBox(-10.0F, -10.0F, -26.0F, 20.0F, 20.0F, 32.0F, 0.0F, 0.0F, 0.0F);
		this.tail4 = new ModelRenderer(this, 98, 52);
		this.tail4.setRotationPoint(0.0F, 0.0F, 9.0F);
		this.tail4.addBox(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 10.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(tail4, 0.11728612207217244F, 0.0F, 0.0F);
		this.tail3 = new ModelRenderer(this, 66, 52);
		this.tail3.setRotationPoint(0.0F, 0.0F, 13.0F);
		this.tail3.addBox(-3.0F, -3.0F, 0.0F, 6.0F, 6.0F, 10.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(tail3, 0.1563815016444822F, 0.0F, 0.0F);
		this.frontRightLeg = new ModelRenderer(this, 0, 52);
		this.frontRightLeg.mirror = true;
		this.frontRightLeg.setRotationPoint(-5.0F, -4.0F, -20.0F);
		this.frontRightLeg.addBox(-8.0F, 0.0F, -3.5F, 7.0F, 28.0F, 7.0F, 0.0F, 0.0F, 0.0F);
		this.tail1 = new ModelRenderer(this, 24, 98);
		this.tail1.setRotationPoint(0.0F, -2.0F, 4.0F);
		this.tail1.addBox(-7.0F, -7.0F, 0.0F, 14.0F, 14.0F, 16.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(tail1, -0.11728612207217244F, 0.0F, 0.0F);
		this.neck2 = new ModelRenderer(this, 72, 0);
		this.neck2.setRotationPoint(0.0F, 0.0F, -15.0F);
		this.neck2.addBox(-4.0F, -4.0F, -18.0F, 8.0F, 8.0F, 18.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(neck2, -0.5864306020384839F, 0.0F, 0.0F);
		this.mouth1 = new ModelRenderer(this, 68, 92);
		this.mouth1.setRotationPoint(0.0F, 11.0F, -5.5F);
		this.mouth1.addBox(-4.0F, 0.0F, -2.5F, 8.0F, 4.0F, 5.0F, 0.0F, 0.0F, 0.0F);
		this.mouth2 = new ModelRenderer(this, 94, 92);
		this.mouth2.setRotationPoint(0.0F, 0.0F, 3.5F);
		this.mouth2.addBox(-4.0F, 0.0F, 0.0F, 8.0F, 4.0F, 2.0F, 0.0F, 0.0F, 0.0F);
		this.body.addChild(this.backLeftLeg);
		this.body.addChild(this.frontLeftLeg);
		this.neck2.addChild(this.neck3);
		this.body.addChild(this.neck1);
		this.tail1.addChild(this.tail2);
		this.neck3.addChild(this.head);
		this.body.addChild(this.backRightLeg);
		this.tail3.addChild(this.tail4);
		this.tail2.addChild(this.tail3);
		this.body.addChild(this.frontRightLeg);
		this.body.addChild(this.tail1);
		this.neck1.addChild(this.neck2);
		this.head.addChild(this.mouth1);
		this.mouth1.addChild(this.mouth2);
	}

	@Override
	public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn,
			float red, float green, float blue, float alpha) {
		ImmutableList.of(this.body).forEach((modelRenderer) -> {
			modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
		});
	}

	@Override
	public void setRotationAngles(BrontosaurusEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks,
			float netHeadYaw, float headPitch) {
		head.rotateAngleY = (float) Math.toRadians(netHeadYaw) * 0.25f;
		head.rotateAngleZ = (float) Math.toRadians(netHeadYaw) * 0.25f;

		rotateTail(limbSwing, limbSwingAmount, ageInTicks);
	}

	@Override
	public void setLivingAnimations(BrontosaurusEntity entityIn, float limbSwing, float limbSwingAmount,
			float partialTick) {
		float attackingProgress = entityIn.getBrontosaurusAttackingProgress(partialTick);
		float eatingProgress = entityIn.getEatingProgress(partialTick);
		if (entityIn.isBrontosaurusAttacking()) {
			body.rotateAngleX = -MathHelper.sin(MathHelper.lerp(attackingProgress, 0, (float) Math.PI))
					* (float) Math.toRadians(35);
			frontLeftLeg.rotateAngleX = MathHelper.cos(MathHelper.lerp(attackingProgress, 0, 4 * (float) Math.PI))
					* (float) Math.toRadians(30) - (float) Math.toRadians(30);
			frontRightLeg.rotateAngleX = MathHelper
					.cos(MathHelper.lerp(attackingProgress, 0, 4 * (float) Math.PI) + (float) Math.PI)
					* (float) Math.toRadians(30) - (float) Math.toRadians(30);
			backLeftLeg.rotateAngleX = MathHelper.sin(MathHelper.lerp(attackingProgress, 0, (float) Math.PI))
					* (float) Math.toRadians(35);
			backRightLeg.rotateAngleX = MathHelper.sin(MathHelper.lerp(attackingProgress, 0, (float) Math.PI))
					* (float) Math.toRadians(35);
		} else {
			body.rotateAngleX = 0;
			frontLeftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f + (float) Math.PI) * 2f * limbSwingAmount
					* 0.4f;
			frontRightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f) * 2f * limbSwingAmount * 0.4f;
			backRightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f + (float) Math.PI) * 2f * limbSwingAmount
					* 0.4f;
			backLeftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f) * 2f * limbSwingAmount * 0.4f;
		}

		if (entityIn.isEating()) {
			eatingAnimation(eatingProgress);
		} else {
			rotateNeck(limbSwing, limbSwingAmount);
		}
	}

	private void eatingAnimation(float eatingProgress) {
		float neckRotProgress = Math.min(1, eatingProgress * 2.5f);
		List<ModelRenderer> neckParts = getNeckParts();
		List<Float> neckStartRots = getNeckStartRots();
		neckParts.get(0).rotateAngleX = -MathHelper.lerp(neckRotProgress, 0, (float) Math.toRadians(35))
				+ neckStartRots.get(0);
		neckParts.get(1).rotateAngleX = MathHelper.lerp(neckRotProgress, 0, (float) Math.toRadians(40))
				+ neckStartRots.get(1);
		neckParts.get(2).rotateAngleX = MathHelper.lerp(neckRotProgress, 0, (float) Math.toRadians(40))
				+ neckStartRots.get(2);
		head.rotateAngleX = MathHelper.cos(eatingProgress * (float) Math.PI * 7) * (float) Math.toRadians(15)
				- (float) Math.toRadians(20);
	}

	private void rotateNeck(float limbSwing, float limbSwingAmount) {
		int signum = -1;
		List<ModelRenderer> neckParts = getNeckParts();
		List<Float> neckStartRots = getNeckStartRots();
		for (int i = 0; i < neckParts.size(); i++) {
			neckParts.get(i).rotateAngleX = signum * MathHelper.cos(limbSwing * 0.6662f) * 2f * limbSwingAmount * 0.35f
					+ neckStartRots.get(i);
			signum = -signum;
		}
	}

	private void rotateTail(float limbSwing, float limbSwingAmount, float ageInTicks) {
		for (ModelRenderer tail : getTailParts()) {
			tail.rotateAngleX = MathHelper.cos(ageInTicks * 0.05f + limbSwing * 0.18f)
					* (float) Math.toRadians(5 + limbSwingAmount * 5);
		}
	}

	private Iterable<ModelRenderer> getTailParts() {
		return ImmutableList.of(tail1, tail2, tail3, tail4);
	}

	private List<ModelRenderer> getNeckParts() {
		return ImmutableList.of(neck1, neck2, neck3, head);
	}

	private List<Float> getNeckStartRots() {
		return ImmutableList.of(-0.47f, -0.59f, -0.23f, 0f);
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
