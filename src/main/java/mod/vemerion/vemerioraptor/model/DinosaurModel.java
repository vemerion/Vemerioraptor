package mod.vemerion.vemerioraptor.model;

import mod.vemerion.vemerioraptor.entity.DinosaurEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.util.ResourceLocation;

public abstract class DinosaurModel<T extends DinosaurEntity> extends EntityModel<T> {
	
	public abstract ResourceLocation getTexture();
}
