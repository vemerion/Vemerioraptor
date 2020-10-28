package mod.vemerion.vemerioraptor;

import mod.vemerion.vemerioraptor.entity.VemerioraptorEntity;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@Mod(Main.MODID)
public class Main {
	public static final String MODID = "vemerioraptor";
	
	@ObjectHolder(Main.MODID + ":vemerioraptor_entity")
	public static final EntityType<VemerioraptorEntity> VEMERIORAPTOR_ENTITY = null;
}
