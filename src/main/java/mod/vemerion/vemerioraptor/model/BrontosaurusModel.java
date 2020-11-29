package mod.vemerion.vemerioraptor.model;

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
    public ModelRenderer tail1;
    public ModelRenderer backLeftLeg;
    public ModelRenderer frontLeftLeg;
    public ModelRenderer neck1;
    public ModelRenderer frontRightLeg;
    public ModelRenderer backRightLeg;
    public ModelRenderer tail2;
    public ModelRenderer tail3;
    public ModelRenderer tail4;
    public ModelRenderer tail5;
    public ModelRenderer neck2;
    public ModelRenderer neck3;
    public ModelRenderer neck4;
    public ModelRenderer head;

    public BrontosaurusModel() {
        this.textureWidth = 256;
        this.textureHeight = 256;
        this.frontRightLeg = new ModelRenderer(this, 64, 32);
        this.frontRightLeg.setRotationPoint(-8.0F, -2.0F, -20.0F);
        this.frontRightLeg.setTextureOffset(116, 8).addBox(-8.0F, 0.0F, -4.0F, 8.0F, 28.0F, 8.0F, 0.0F, 0.0F, 0.0F);
        this.body = new ModelRenderer(this, 0, 0);
        this.body.setRotationPoint(0.0F, -2.0F, 10.0F);
        this.body.addBox(-12.0F, -12.0F, -28.0F, 24.0F, 24.0F, 36.0F, 0.0F, 0.0F, 0.0F);
        this.neck1 = new ModelRenderer(this, 64, 32);
        this.neck1.setRotationPoint(0.0F, -4.0F, -26.0F);
        this.neck1.setTextureOffset(38, 16).addBox(-6.0F, -6.0F, -18.0F, 12.0F, 12.0F, 18.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(neck1, 0.0781907508222411F, 0.0F, 0.0F);
        this.tail5 = new ModelRenderer(this, 64, 0);
        this.tail5.setRotationPoint(0.0F, 0.0F, 9.0F);
        this.tail5.setTextureOffset(74, 0).addBox(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 10.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(tail5, 0.11728612207217244F, 0.0F, 0.0F);
        this.tail3 = new ModelRenderer(this, 64, 16);
        this.tail3.setRotationPoint(0.0F, 0.0F, 15.0F);
        this.tail3.setTextureOffset(138, 0).addBox(-5.0F, -5.0F, 0.0F, 10.0F, 10.0F, 14.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(tail3, 0.11728612207217244F, 0.0F, 0.0F);
        this.tail4 = new ModelRenderer(this, 0, 0);
        this.tail4.setRotationPoint(0.0F, 0.0F, 13.0F);
        this.tail4.addBox(-3.0F, -3.0F, 0.0F, 6.0F, 6.0F, 10.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(tail4, 0.1563815016444822F, 0.0F, 0.0F);
        this.head = new ModelRenderer(this, 64, 32);
        this.head.setRotationPoint(0.0F, -3.0F, -18.0F);
        this.head.setTextureOffset(84, 34).addBox(-4.0F, -4.0F, -14.0F, 8.0F, 8.0F, 14.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(head, 1.1728612040769677F, 0.0F, 0.0F);
        this.backRightLeg = new ModelRenderer(this, 64, 32);
        this.backRightLeg.setRotationPoint(-8.0F, -2.0F, 0.0F);
        this.backRightLeg.setTextureOffset(116, 8).addBox(-8.0F, 0.0F, -4.0F, 8.0F, 28.0F, 8.0F, 0.0F, 0.0F, 0.0F);
        this.tail2 = new ModelRenderer(this, 64, 0);
        this.tail2.setRotationPoint(0.0F, 0.0F, 16.0F);
        this.tail2.setTextureOffset(92, 0).addBox(-7.0F, -7.0F, 0.0F, 14.0F, 14.0F, 16.0F, 0.0F, 0.0F, 0.0F);
        this.neck2 = new ModelRenderer(this, 64, 32);
        this.neck2.setRotationPoint(0.0F, 0.0F, -16.0F);
        this.neck2.setTextureOffset(130, 26).addBox(-5.0F, -5.0F, -18.0F, 10.0F, 10.0F, 18.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(neck2, -0.27366763203903305F, 0.0F, 0.0F);
        this.frontLeftLeg = new ModelRenderer(this, 64, 32);
        this.frontLeftLeg.setRotationPoint(8.0F, -2.0F, -20.0F);
        this.frontLeftLeg.setTextureOffset(116, 8).addBox(0.0F, 0.0F, -4.0F, 8.0F, 28.0F, 8.0F, 0.0F, 0.0F, 0.0F);
        this.neck4 = new ModelRenderer(this, 52, 32);
        this.neck4.setRotationPoint(0.0F, 0.0F, -16.0F);
        this.neck4.setTextureOffset(0, 28).addBox(-3.0F, -3.0F, -18.0F, 6.0F, 6.0F, 18.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(neck4, -0.19547687289441354F, 0.0F, 0.0F);
        this.backLeftLeg = new ModelRenderer(this, 64, 30);
        this.backLeftLeg.setRotationPoint(8.0F, -2.0F, 0.0F);
        this.backLeftLeg.setTextureOffset(84, 0).addBox(0.0F, 0.0F, -4.0F, 8.0F, 28.0F, 8.0F, 0.0F, 0.0F, 0.0F);
        this.tail1 = new ModelRenderer(this, 64, 0);
        this.tail1.setRotationPoint(0.0F, 0.0F, 5.0F);
        this.tail1.setTextureOffset(20, 0).addBox(-9.0F, -9.0F, 0.0F, 18.0F, 18.0F, 18.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(tail1, -0.19547687289441354F, 0.0F, 0.0F);
        this.neck3 = new ModelRenderer(this, 0, 32);
        this.neck3.setRotationPoint(0.0F, 0.0F, -15.0F);
        this.neck3.setTextureOffset(0, 28).addBox(-4.0F, -4.0F, -18.0F, 8.0F, 8.0F, 18.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(neck3, -0.4300491170387584F, 0.0F, 0.0F);
        this.body.addChild(this.frontRightLeg);
        this.body.addChild(this.neck1);
        this.tail4.addChild(this.tail5);
        this.tail2.addChild(this.tail3);
        this.tail3.addChild(this.tail4);
        this.neck4.addChild(this.head);
        this.body.addChild(this.backRightLeg);
        this.tail1.addChild(this.tail2);
        this.neck1.addChild(this.neck2);
        this.body.addChild(this.frontLeftLeg);
        this.neck3.addChild(this.neck4);
        this.body.addChild(this.backLeftLeg);
        this.body.addChild(this.tail1);
        this.neck2.addChild(this.neck3);
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
		head.rotateAngleY = (float) Math.toRadians(netHeadYaw) * 0.3f;
		head.rotateAngleZ = (float) Math.toRadians(netHeadYaw) * 0.3f;
	}

	@Override
	public void setLivingAnimations(BrontosaurusEntity entityIn, float limbSwing, float limbSwingAmount,
			float partialTick) {
		float attackingProgress = entityIn.getBrontosaurusAttackingProgress(partialTick);
		if (entityIn.isBrontosaurusAttacking()) {
			body.rotateAngleX = -MathHelper.sin(MathHelper.lerp(attackingProgress, 0, (float) Math.PI))
					* (float) Math.toRadians(35);
			tail1.rotateAngleX = -0.2f + MathHelper.sin(MathHelper.lerp(attackingProgress, 0, (float) Math.PI))
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
			tail1.rotateAngleX = -0.2f;
			frontLeftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f + (float) Math.PI) * 2f * limbSwingAmount
					* 0.4f;
			frontRightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f) * 2f * limbSwingAmount * 0.4f;
			backRightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f + (float) Math.PI) * 2f * limbSwingAmount
					* 0.4f;
			backLeftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f) * 2f * limbSwingAmount * 0.4f;
		}
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
