package com.robertx22.mine_and_slash.mmorpg.registers.common;

import com.robertx22.mine_and_slash.mmorpg.Ref;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class ModSounds {

    public static DeferredRegister<SoundEvent> REG = new DeferredRegister<>(ForgeRegistries.SOUND_EVENTS, Ref.MODID);

    public static RegistryObject<SoundEvent> FREEZE = REG.register("freeze", reg("freeze"));
    public static RegistryObject<SoundEvent> DASH = REG.register("dash", reg("dash"));
    public static RegistryObject<SoundEvent> SPLASH = REG.register("splash", reg("splash"));
    public static RegistryObject<SoundEvent> STONE_CRACK = REG.register("stone_crack", reg("stone_crack"));
    public static RegistryObject<SoundEvent> FIREBALL = REG.register("fireball", reg("fireball"));
    public static RegistryObject<SoundEvent> THUNDER = REG.register("thunder", reg("thunder"));

    private static Supplier<SoundEvent> reg(String id) {
        ResourceLocation loc = new ResourceLocation(Ref.MODID, id);
        return () -> new SoundEvent(loc);
    }

}
