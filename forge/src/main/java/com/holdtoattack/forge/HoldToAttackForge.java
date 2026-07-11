package com.holdtoattack.forge;

import com.holdtoattack.config.HoldToAttackConfig;
import com.holdtoattack.forge.config.ForgeConfigProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("holdtoattack")
public class HoldToAttackForge {

    public HoldToAttackForge(FMLJavaModLoadingContext context) {
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ForgeConfigProxy.SPEC);
        HoldToAttackConfig.setProxy(ForgeConfigProxy.INSTANCE);
        System.out.println("[HoldToAttack] Forge initialized!");
    }
}
