package mod.vemerion.vemerioraptor.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

/**
 * Created using Tabula 8.0.0
 */
public class PlesiosaurusSwimfinsModel<T extends LivingEntity> extends BipedModel<T> {
    public ModelRenderer swimfinRight;
    public ModelRenderer swimfinLeft;

    public PlesiosaurusSwimfinsModel() {
		super(0, 0, 64, 128);
        this.textureWidth = 64;
        this.textureHeight = 128;
        this.swimfinRight = new ModelRenderer(this, 0, 64);
        this.swimfinRight.mirror = true;
        this.swimfinRight.setRotationPoint(0.0F, 12.0F, 3.0F);
        this.swimfinRight.addBox(-3.0F, 0.0F, -10.0F, 6.0F, 0.0F, 10.0F, 0.0F, 0.0F, 0.0F);
        this.swimfinLeft = new ModelRenderer(this, 0, 64);
        this.swimfinLeft.setRotationPoint(0.0F, 12.0F, 3.0F);
        this.swimfinLeft.addBox(-3.0F, 0.0F, -10.0F, 6.0F, 0.0F, 10.0F, 0.0F, 0.0F, 0.0F);
        this.bipedRightLeg.addChild(this.swimfinRight);
        this.bipedLeftLeg.addChild(this.swimfinLeft);
    }

    @Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) { 
        ImmutableList.of(this.bipedRightLeg, this.bipedLeftLeg).forEach((modelRenderer) -> { 
            modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        });
    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
