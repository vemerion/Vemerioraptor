package mod.vemerion.vemerioraptor.model;

import mod.vemerion.vemerioraptor.entity.DinosaurEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public abstract class DinosaurModel<T extends DinosaurEntity> extends EntityModel<T> {
	
	public abstract ResourceLocation getTexture();
	
	protected abstract Iterable<ModelRenderer> getTailParts();
	
	protected void rotateTail(float limbSwing, float limbSwingAmount, float ageInTicks) {
		for (ModelRenderer tail : getTailParts()) {
			tail.rotateAngleX = MathHelper.cos(ageInTicks * 0.05f + limbSwing * 0.18f)
					* (float) Math.toRadians(5 + limbSwingAmount * 5);
		}
	}
	
	@Override
	public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks,
			float netHeadYaw, float headPitch) {
		rotateTail(limbSwing, limbSwingAmount, ageInTicks);		
	}
}
