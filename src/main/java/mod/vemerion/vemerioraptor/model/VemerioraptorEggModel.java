package mod.vemerion.vemerioraptor.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import mod.vemerion.vemerioraptor.Main;
import net.minecraft.util.ResourceLocation;

/**
 * Created using Tabula 8.0.0
 */
public class VemerioraptorEggModel extends DinosaurEggModel {
	
	private static final ResourceLocation TEXTURES = new ResourceLocation(Main.MODID, "textures/entity/vemerioraptor_egg.png");
	
	public ModModelRenderer egg1;
	public ModModelRenderer egg2;
	public ModModelRenderer egg3;
	public ModModelRenderer grass1;
	public ModModelRenderer grass2;
	public ModModelRenderer grass3;

	public VemerioraptorEggModel() {
        this.textureWidth = 32;
        this.textureHeight = 32;
        this.grass1 = new ModModelRenderer(this, 0, 8);
        this.grass1.setRotationPoint(3.0F, 24.0F, -1.0F);
        this.grass1.addBox(-4.5F, -5.0F, 0.0F, 9.0F, 5.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(grass1, 0.0F, 0.3127630032889644F, 0.0F);
        this.egg1 = new ModModelRenderer(this, 0, 0);
        this.egg1.setRotationPoint(3.0F, 22.0F, 4.0F);
        this.egg1.addBox(-3.5F, -2.0F, -2.0F, 7.0F, 4.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(egg1, 0.0F, 0.35185837453889574F, 0.0F);
        this.grass3 = new ModModelRenderer(this, 0, 8);
        this.grass3.setRotationPoint(-6.0F, 24.0F, -5.0F);
        this.grass3.addBox(-4.5F, -5.0F, 0.0F, 9.0F, 5.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(grass3, 0.0F, 1.080707840876956F, 0.0F);
        this.egg2 = new ModModelRenderer(this, 0, 0);
        this.egg2.setRotationPoint(-4.0F, 22.0F, 1.0F);
        this.egg2.addBox(-4.5F, -2.0F, -2.0F, 7.0F, 4.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(egg2, 0.0F, -1.876577953154759F, 0.0F);
        this.egg3 = new ModModelRenderer(this, 0, 0);
        this.egg3.setRotationPoint(0.8F, 22.0F, -5.4F);
        this.egg3.addBox(-3.5F, -2.0F, -2.0F, 7.0F, 4.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(egg3, 0.0F, 2.5411994974301875F, 0.0F);
        this.grass2 = new ModModelRenderer(this, 0, 8);
        this.grass2.setRotationPoint(-4.0F, 24.0F, 7.0F);
        this.grass2.addBox(-4.5F, -5.0F, 0.0F, 9.0F, 5.0F, 0.0F, 0.0F, 0.0F, 0.0F);
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

	/**
	 * This is a helper function from Tabula to set the rotation of model parts
	 */
	public void setRotateAngle(ModModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.initRot(x, y, z);
	}

	@Override
	public ResourceLocation getTexture() {
		return TEXTURES;
	}

	@Override
	protected Iterable<ModModelRenderer> getEggs() {
		return ImmutableList.of(egg1, egg2, egg3);
	}

	@Override
	protected Iterable<ModModelRenderer> getGrass() {
		return ImmutableList.of(grass1, grass2, grass3);
	}
}
