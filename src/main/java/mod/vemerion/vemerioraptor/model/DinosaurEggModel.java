package mod.vemerion.vemerioraptor.model;

import java.util.Random;

import mod.vemerion.vemerioraptor.entity.DinosaurEggEntity;
import mod.vemerion.vemerioraptor.helper.Helper;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public abstract class DinosaurEggModel extends EntityModel<DinosaurEggEntity> {

	private static final int SHAKE_INTERVAL = 20 * 30;
	private static final int SHAKE_DURATION = 5;

	public abstract ResourceLocation getTexture();

	protected abstract Iterable<ModModelRenderer> getEggs();

	protected abstract Iterable<ModModelRenderer> getGrass();

	@Override
	public void setRotationAngles(DinosaurEggEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks,
			float netHeadYaw, float headPitch) {
		Random random = new Random(0);
		float offset = 0;
		for (ModModelRenderer grass : getGrass()) {
			animateSeagrass(grass, ageInTicks, offset);
			offset += MathHelper.nextFloat(random, 3, 8);
		}

		offset = 0;
		for (ModModelRenderer egg : getEggs()) {
			animateEgg(egg, ageInTicks, offset);
			offset += MathHelper.nextFloat(random, 20 * 2, 20 * 5);
		}
	}

	private void animateEgg(ModModelRenderer egg, float ageInTicks, float tickOffset) {
		if (((int) ageInTicks + tickOffset) % SHAKE_INTERVAL < SHAKE_DURATION) {
			float progress = ageInTicks / SHAKE_DURATION * Helper.toRad(360);
			egg.rotateAngleX = egg.startRotX + MathHelper.sin(progress) * Helper.toRad(2);
			egg.rotateAngleY = egg.startRotY + MathHelper.sin(progress) * Helper.toRad(2);
			egg.rotateAngleZ = egg.startRotZ + MathHelper.sin(progress) * Helper.toRad(2);
		} else {
			egg.resetRot();
		}
	}

	private void animateSeagrass(ModModelRenderer seagrass, float ageInTicks, float tickOffset) {
		seagrass.rotateAngleX = seagrass.startRotX + MathHelper.cos((ageInTicks + tickOffset) / 20) * Helper.toRad(2);
		seagrass.rotateAngleZ = seagrass.startRotZ + MathHelper.sin((ageInTicks + tickOffset) / 20) * Helper.toRad(2);
	}
}
