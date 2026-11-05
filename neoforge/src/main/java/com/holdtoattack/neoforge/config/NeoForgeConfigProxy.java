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
    public final BooleanValue keepAttackFocus;

    private NeoForgeConfigProxy(Builder builder) {
        builder.push("general");
        this.enabled = builder.comment("Enable or disable Hold To Attack").define("enabled", true);
        this.requireTarget = builder.comment("If true, requires looking at a target to attack").define("requireTarget", true);
        this.keepAttackFocus = builder.comment("If true, ignores block mining/interaction while holding attack after hitting an entity").define("keepAttackFocus", true);
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

    @Override
    public boolean isKeepAttackFocus() {
        return this.keepAttackFocus.get();
    }

    static {
        Builder builder = new Builder();
        INSTANCE = new NeoForgeConfigProxy(builder);
        SPEC = builder.build();
    }
}
