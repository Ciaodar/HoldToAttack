package com.holdtoattack.neoforge.config;

import com.holdtoattack.config.IConfigProxy;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.ModConfigSpec.BooleanValue;
import net.neoforged.neoforge.common.ModConfigSpec.Builder;

public class NeoForgeConfigProxy implements IConfigProxy {
    public static final ModConfigSpec SPEC;
    public static final NeoForgeConfigProxy INSTANCE;
    public final BooleanValue enabled;
    public final BooleanValue requireTarget;

    private NeoForgeConfigProxy(Builder builder) {
        builder.push("general");
        this.enabled = builder.comment("Enable or disable Hold To Attack").define("enabled", true);
        this.requireTarget = builder.comment("If true, requires looking at a target to attack").define("requireTarget", true);
        builder.pop();
    }

    @Override
    public boolean isEnabled() {
        return this.enabled.get();
    }

    @Override
    public boolean isRequireTarget() {
        return this.requireTarget.get();
    }

    static {
        Builder builder = new Builder();
        INSTANCE = new NeoForgeConfigProxy(builder);
        SPEC = builder.build();
    }
}
