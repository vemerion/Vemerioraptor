package mod.vemerion.vemerioraptor.init;

import mod.vemerion.vemerioraptor.Main;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(value = Main.MODID)
@EventBusSubscriber(bus = Bus.MOD, modid = Main.MODID)
public class ModSounds {

	public static final SoundEvent RAPTOR_AMBIENT = null;
	public static final SoundEvent RAPTOR_DEATH = null;
	public static final SoundEvent RAPTOR_HURT = null;
	public static final SoundEvent BRONTOSAURUS_AMBIENT = null;
	public static final SoundEvent BRONTOSAURUS_DEATH = null;
	public static final SoundEvent BRONTOSAURUS_HURT = null;

	@SubscribeEvent
	public static void onRegisterSound(RegistryEvent.Register<SoundEvent> event) {
		SoundEvent raptor_ambient_sound = new SoundEvent(new ResourceLocation(Main.MODID, "raptor_ambient"));
		event.getRegistry().register(Init.setup(raptor_ambient_sound, "raptor_ambient"));
		SoundEvent raptor_death_sound = new SoundEvent(new ResourceLocation(Main.MODID, "raptor_death"));
		event.getRegistry().register(Init.setup(raptor_death_sound, "raptor_death"));
		SoundEvent raptor_hurt_sound = new SoundEvent(new ResourceLocation(Main.MODID, "raptor_hurt"));
		event.getRegistry().register(Init.setup(raptor_hurt_sound, "raptor_hurt"));
		SoundEvent brontosaurus_ambient_sound = new SoundEvent(new ResourceLocation(Main.MODID, "brontosaurus_ambient"));
		event.getRegistry().register(Init.setup(brontosaurus_ambient_sound, "brontosaurus_ambient"));
		SoundEvent brontosaurus_death_sound = new SoundEvent(new ResourceLocation(Main.MODID, "brontosaurus_death"));
		event.getRegistry().register(Init.setup(brontosaurus_death_sound, "brontosaurus_death"));
		SoundEvent brontosaurus_hurt_sound = new SoundEvent(new ResourceLocation(Main.MODID, "brontosaurus_hurt"));
		event.getRegistry().register(Init.setup(brontosaurus_hurt_sound, "brontosaurus_hurt"));   

	}
}
