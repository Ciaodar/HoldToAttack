package com.holdtoattack.fabric;

import com.holdtoattack.config.HoldToAttackConfig;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;

public class HoldToAttackClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HoldToAttackConfig.init(FabricLoader.getInstance().getConfigDir().toFile());
        System.out.println("[HoldToAttack] initialized!");
    }
}
