package com.holdtoattack.fabric;

import com.holdtoattack.config.HoldToAttackConfig;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;

public class HoldToAttackClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        me.shedaniel.autoconfig.AutoConfig.register(
            com.holdtoattack.fabric.config.FabricConfig.class, 
            me.shedaniel.autoconfig.serializer.GsonConfigSerializer::new
        );
        HoldToAttackConfig.setProxy(new com.holdtoattack.fabric.config.FabricConfigProxy());
        System.out.println("[HoldToAttack] initialized!");
    }
}
