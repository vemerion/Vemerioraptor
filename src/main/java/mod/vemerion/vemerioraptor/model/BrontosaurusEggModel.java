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
public class BrontosaurusEggModel extends DinosaurEggModel {
	
	private static final ResourceLocation TEXTURES = new ResourceLocation(Main.MODID, "textures/entity/brontosaurus_egg.png");
	
    public ModelRenderer egg;
    public ModelRenderer grass1;
    public ModelRenderer grass2;

    public BrontosaurusEggModel() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.egg = new ModelRenderer(this, 0, 0);
        this.egg.setRotationPoint(0.0F, 20.0F, 0.0F);
        this.egg.addBox(-6.0F, -4.0F, -4.0F, 12.0F, 8.0F, 8.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(egg, 0.0F, 0.4300491170387584F, 0.0F);
        this.grass1 = new ModelRenderer(this, 32, 0);
        this.grass1.setRotationPoint(-1.0F, 18.0F, 7.0F);
        this.grass1.addBox(0.0F, 0.0F, 0.0F, 10.0F, 6.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(grass1, 0.0F, 0.3127630032889644F, 0.0F);
        this.grass2 = new ModelRenderer(this, 32, 0);
        this.grass2.setRotationPoint(-5.0F, 18.0F, -6.0F);
        this.grass2.addBox(0.0F, 0.0F, 0.0F, 10.0F, 6.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(grass2, 0.0F, 0.3127630032889644F, 0.0F);
    }

    @Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) { 
        ImmutableList.of(this.egg, this.grass1, this.grass2).forEach((modelRenderer) -> { 
            modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        });
    }

    @Override
    public void setRotationAngles(DinosaurEggEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}

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
