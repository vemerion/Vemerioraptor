package mod.vemerion.vemerioraptor.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import mod.vemerion.vemerioraptor.Main;
import mod.vemerion.vemerioraptor.entity.PlesiosaurusEntity;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.ResourceLocation;

/**
 * Created using Tabula 8.0.0
 */
public class PlesiosaurusModel extends DinosaurModel<PlesiosaurusEntity> {
	
	private static final ResourceLocation TEXTURES = new ResourceLocation(Main.MODID,
			"textures/entity/brontosaurus.png");

	
    public ModelRenderer body1;
    public ModelRenderer body2;
    public ModelRenderer body4;
    public ModelRenderer frontLeftLeg1;
    public ModelRenderer body3;
    public ModelRenderer tail1;
    public ModelRenderer tail2;
    public ModelRenderer neck1;
    public ModelRenderer neck2;
    public ModelRenderer neck3;
    public ModelRenderer head1;
    public ModelRenderer head2;
    public ModelRenderer frontLeftLeg2;

    public PlesiosaurusModel() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.body3 = new ModelRenderer(this, 0, 0);
        this.body3.setRotationPoint(0.0F, 0.0F, 7.0F);
        this.body3.addBox(-6.0F, -6.0F, -3.0F, 12.0F, 12.0F, 6.0F, 0.0F, 0.0F, 0.0F);
        this.frontLeftLeg2 = new ModelRenderer(this, 0, 0);
        this.frontLeftLeg2.setRotationPoint(3.0F, 0.0F, 0.0F);
        this.frontLeftLeg2.addBox(0.0F, -1.0F, -4.0F, 12.0F, 2.0F, 8.0F, 0.0F, 0.0F, 0.0F);
        this.body1 = new ModelRenderer(this, 0, 0);
        this.body1.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.body1.addBox(-8.0F, -8.0F, -7.0F, 16.0F, 16.0F, 14.0F, 0.0F, 0.0F, 0.0F);
        this.body2 = new ModelRenderer(this, 0, 0);
        this.body2.setRotationPoint(0.0F, 0.0F, 11.0F);
        this.body2.addBox(-7.0F, -7.0F, -4.0F, 14.0F, 14.0F, 8.0F, 0.0F, 0.0F, 0.0F);
        this.tail2 = new ModelRenderer(this, 0, 0);
        this.tail2.setRotationPoint(0.0F, 0.0F, 11.0F);
        this.tail2.addBox(-5.0F, -1.5F, 0.0F, 10.0F, 3.0F, 12.0F, 0.0F, 0.0F, 0.0F);
        this.head2 = new ModelRenderer(this, 0, 0);
        this.head2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.head2.addBox(-4.0F, 0.0F, -12.0F, 8.0F, 2.0F, 12.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(head2, 0.11728612207217244F, 0.0F, 0.0F);
        this.frontLeftLeg1 = new ModelRenderer(this, 0, 0);
        this.frontLeftLeg1.setRotationPoint(6.0F, 5.0F, -10.0F);
        this.frontLeftLeg1.addBox(0.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(frontLeftLeg1, 0.0F, 0.19547687289441354F, 0.35185837453889574F);
        this.neck1 = new ModelRenderer(this, 0, 0);
        this.neck1.setRotationPoint(0.0F, 0.0F, -2.0F);
        this.neck1.addBox(-5.0F, -5.0F, -14.0F, 10.0F, 10.0F, 14.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(neck1, -0.23457224414434488F, 0.0F, 0.0F);
        this.head1 = new ModelRenderer(this, 0, 0);
        this.head1.setRotationPoint(0.0F, -1.0F, -10.0F);
        this.head1.addBox(-4.0F, -3.0F, -12.0F, 8.0F, 3.0F, 12.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(head1, 0.4300491170387584F, 0.0F, 0.0F);
        this.body4 = new ModelRenderer(this, 0, 0);
        this.body4.setRotationPoint(0.0F, 0.0F, -11.0F);
        this.body4.addBox(-7.0F, -7.0F, -4.0F, 14.0F, 14.0F, 8.0F, 0.0F, 0.0F, 0.0F);
        this.tail1 = new ModelRenderer(this, 0, 0);
        this.tail1.setRotationPoint(0.0F, 0.0F, 2.0F);
        this.tail1.addBox(-4.0F, -3.0F, 0.0F, 8.0F, 6.0F, 12.0F, 0.0F, 0.0F, 0.0F);
        this.neck2 = new ModelRenderer(this, 0, 0);
        this.neck2.setRotationPoint(0.0F, 0.0F, -12.0F);
        this.neck2.addBox(-4.0F, -4.0F, -12.0F, 8.0F, 8.0F, 12.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(neck2, -0.23457224414434488F, 0.0F, 0.0F);
        this.neck3 = new ModelRenderer(this, 0, 0);
        this.neck3.setRotationPoint(0.0F, 0.0F, -10.0F);
        this.neck3.addBox(-3.0F, -3.0F, -12.0F, 6.0F, 6.0F, 12.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(neck3, 0.11728612207217244F, 0.0F, 0.0F);
        this.body2.addChild(this.body3);
        this.frontLeftLeg1.addChild(this.frontLeftLeg2);
        this.body1.addChild(this.body2);
        this.tail1.addChild(this.tail2);
        this.head1.addChild(this.head2);
        this.body1.addChild(this.frontLeftLeg1);
        this.body4.addChild(this.neck1);
        this.neck3.addChild(this.head1);
        this.body1.addChild(this.body4);
        this.body3.addChild(this.tail1);
        this.neck1.addChild(this.neck2);
        this.neck2.addChild(this.neck3);  
    }

    @Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) { 
        ImmutableList.of(this.body1).forEach((modelRenderer) -> { 
            modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        });
    }

    @Override
    public void setRotationAngles(PlesiosaurusEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}

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