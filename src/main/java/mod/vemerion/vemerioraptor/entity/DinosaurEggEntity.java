package mod.vemerion.vemerioraptor.entity;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class DinosaurEggEntity extends AgeableEntity {

	private EntityType<? extends DinosaurEntity> birthType;
	private int maxBirth;

	public DinosaurEggEntity(EntityType<? extends DinosaurEggEntity> type, World worldIn,
			EntityType<? extends DinosaurEntity> birthType, int maxBirth) {
		super(type, worldIn);
		this.birthType = birthType;
		this.maxBirth = maxBirth;
		this.setChild(true);
	}

	public static AttributeModifierMap.MutableAttribute attributes() {
		return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH, 1)
				.createMutableAttribute(Attributes.MOVEMENT_SPEED, 0)
				.createMutableAttribute(Attributes.ATTACK_DAMAGE, 0).createMutableAttribute(Attributes.FOLLOW_RANGE, 0);
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
	public boolean canBreatheUnderwater() {
		return true;
	}

	@Override
	protected void onGrowingAdult() {
		if (!world.isRemote && !isChild()) {
			for (int i = 0; i < maxBirth; i++) {
				DinosaurEntity child = birthType.create(world);
				Vector3d pos = getPositionVec().add(rand.nextDouble() - 0.5, rand.nextDouble() - 0.5,
						rand.nextDouble() - 0.5);
				child.setLocationAndAngles(pos.x, pos.y, pos.z, rand.nextInt(360), 0);
				child.setChild(true);
				world.addEntity(child);
			}
			remove();
		}
	}
}
