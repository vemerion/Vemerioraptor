package mod.vemerion.vemerioraptor.model;

import mod.vemerion.vemerioraptor.entity.DinosaurEggEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.util.ResourceLocation;

public abstract class DinosaurEggModel extends EntityModel<DinosaurEggEntity> {
	public abstract ResourceLocation getTexture();
}
