package mod.vemerion.vemerioraptor.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.world.World;

public abstract class DinosaurEntity extends AnimalEntity {

	protected DinosaurEntity(EntityType<? extends DinosaurEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	public void tick() {
		super.tick();
		updateArmSwingProgress();
	}

}
