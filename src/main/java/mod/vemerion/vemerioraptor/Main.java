package mod.vemerion.vemerioraptor;

import mod.vemerion.vemerioraptor.entity.VemerioraptorEggEntity;
import mod.vemerion.vemerioraptor.entity.VemerioraptorEntity;
import mod.vemerion.vemerioraptor.item.VemerioraptorItemGroup;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@Mod(Main.MODID)
public class Main {
	public static final String MODID = "vemerioraptor";
	
	@ObjectHolder(Main.MODID + ":vemerioraptor_entity")
	public static final EntityType<VemerioraptorEntity> VEMERIORAPTOR_ENTITY = null;

	@ObjectHolder(Main.MODID + ":vemerioraptor_egg_entity")
	public static final EntityType<VemerioraptorEggEntity> VEMERIORAPTOR_EGG_ENTITY = null; 
	
	@ObjectHolder(Main.MODID + ":vemerioraptor_claw_item")
	public static final Item VEMERIORAPTOR_CLAW_ITEM = null;
	
	@ObjectHolder(Main.MODID + ":raptor_ambient_sound")
	public static final SoundEvent RAPTOR_AMBIENT_SOUND = null;

	@ObjectHolder(Main.MODID + ":raptor_death_sound")
	public static final SoundEvent RAPTOR_DEATH_SOUND = null;

	@ObjectHolder(Main.MODID + ":raptor_hurt_sound")
	public static final SoundEvent RAPTOR_HURT_SOUND = null;
	
	public static final VemerioraptorItemGroup VEMERIORAPTOR_ITEM_GROUP = new VemerioraptorItemGroup();
}
