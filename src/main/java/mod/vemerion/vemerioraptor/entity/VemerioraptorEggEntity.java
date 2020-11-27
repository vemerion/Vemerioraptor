package mod.vemerion.vemerioraptor.entity;

import mod.vemerion.vemerioraptor.Main;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class VemerioraptorEggEntity extends AgeableEntity {

	private static final int MAX_RAPTORS = 3;

	public VemerioraptorEggEntity(EntityType<? extends VemerioraptorEggEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	public VemerioraptorEggEntity(World worldIn) {
		super(Main.VEMERIORAPTOR_EGG_ENTITY, worldIn);
	}
	
	public static AttributeModifierMap.MutableAttribute attributes() {
		return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH, 1)
				.createMutableAttribute(Attributes.MOVEMENT_SPEED, 0)
				.createMutableAttribute(Attributes.ATTACK_DAMAGE, 0)
				.createMutableAttribute(Attributes.FOLLOW_RANGE, 0);
	}
	
	@Override
	public void livingTick() {
		super.livingTick();
	}

	@Override
	public AgeableEntity func_241840_a(ServerWorld world, AgeableEntity parent) {
		return null;
	}

	@Override
	protected void onGrowingAdult() {
		if (!world.isRemote && !isChild()) {
			for (int i = 0; i < MAX_RAPTORS; i++) {
				VemerioraptorEntity raptor = new VemerioraptorEntity(world);
				Vector3d pos = getPositionVec().add(rand.nextDouble() - 0.5, rand.nextDouble() - 0.5,
						rand.nextDouble() - 0.5);
				raptor.setLocationAndAngles(pos.x, pos.y, pos.z, rand.nextInt(360), 0);
				world.addEntity(raptor);
			}
			remove();
		}
	}
}
