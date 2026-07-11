package com.holdtoattack.fabric.config;

import com.holdtoattack.config.IConfigProxy;
import me.shedaniel.autoconfig.AutoConfig;

public class FabricConfigProxy implements IConfigProxy {
    
    private final FabricConfig config;

    public FabricConfigProxy() {
        this.config = AutoConfig.getConfigHolder(FabricConfig.class).getConfig();
    }

    @Override
    public boolean isEnabled() {
        return config.enabled;
    }

    @Override
    public boolean isRequireTarget() {
        return config.requireTarget;
    }
}
