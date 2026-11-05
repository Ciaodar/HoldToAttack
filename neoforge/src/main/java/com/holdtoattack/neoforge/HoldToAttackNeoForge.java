package com.holdtoattack.neoforge;

import com.holdtoattack.config.HoldToAttackConfig;
import com.holdtoattack.neoforge.config.NeoForgeConfigProxy;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;

@Mod(value = "holdtoattack", dist = Dist.CLIENT)
public class HoldToAttackNeoForge {

    public HoldToAttackNeoForge(IEventBus modEventBus, ModContainer modContainer) {
        HoldToAttackConfig.setProxy(NeoForgeConfigProxy.INSTANCE);
        modContainer.registerConfig(ModConfig.Type.CLIENT, NeoForgeConfigProxy.SPEC);

        System.out.println("[HoldToAttack] NeoForge initialized!");
    }
}
