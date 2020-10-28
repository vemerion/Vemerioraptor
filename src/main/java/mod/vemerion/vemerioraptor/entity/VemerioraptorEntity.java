package mod.vemerion.vemerioraptor.entity;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRideable;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class VemerioraptorEntity extends AnimalEntity implements IRideable {

	public VemerioraptorEntity(EntityType<? extends VemerioraptorEntity> type, World worldIn) {
		super(type, worldIn);
	}

	public static AttributeModifierMap.MutableAttribute attributes() {
		return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH, 10.0D)
				.createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.25D);
	}

	@Override
	public boolean boost() {
		return false;
	}

	@Override
	public void travelTowards(Vector3d travelVec) {

	}

	@Override
	public float getMountedSpeed() {
		return 0;
	}

	@Override
	public AgeableEntity func_241840_a(ServerWorld p_241840_1_, AgeableEntity p_241840_2_) { // Breed
		return null;
	}

}
