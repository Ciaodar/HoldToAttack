package com.holdtoattack.neoforge;

import com.holdtoattack.config.HoldToAttackConfig;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLPaths;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.fml.ModLoadingContext;

import net.neoforged.fml.config.ModConfig;
import com.holdtoattack.neoforge.config.NeoForgeConfigProxy;
import net.neoforged.fml.ModContainer;

@Mod(value = "holdtoattack", dist = Dist.CLIENT)
public class HoldToAttackNeoForge {

    public HoldToAttackNeoForge(net.neoforged.bus.api.IEventBus modEventBus, ModContainer modContainer) {
        HoldToAttackConfig.setProxy(NeoForgeConfigProxy.INSTANCE);
        modContainer.registerConfig(ModConfig.Type.CLIENT, NeoForgeConfigProxy.SPEC);
        
        System.out.println("[HoldToAttack] NeoForge initialized!");
    }
}
