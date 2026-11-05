package com.holdtoattack.neoforge.config;

import com.holdtoattack.config.IConfigProxy;
import net.neoforged.neoforge.common.ModConfigSpec;

public class NeoForgeConfigProxy implements IConfigProxy {
    public static final ModConfigSpec SPEC;
    public static final NeoForgeConfigProxy INSTANCE;

    public final ModConfigSpec.BooleanValue enabled;
    public final ModConfigSpec.BooleanValue requireTarget;
    public final ModConfigSpec.BooleanValue keepAttackFocus;

    static {
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();
        INSTANCE = new NeoForgeConfigProxy(builder);
        SPEC = builder.build();
    }

    private NeoForgeConfigProxy(ModConfigSpec.Builder builder) {
        builder.push("general");
        enabled = builder
                .comment("Enable or disable Hold To Attack")
                .define("enabled", true);
        requireTarget = builder
                .comment("If true, requires looking at a target to attack")
                .define("requireTarget", true);
        keepAttackFocus = builder
                .comment("If true, keeps attack focus and ignores blocks while holding attack")
                .define("keepAttackFocus", true);
        builder.pop();
    }

    @Override
    public boolean isEnabled() {
        return enabled.get();
    }

    @Override
    public boolean isRequireTarget() {
        return requireTarget.get();
    }

    @Override
    public boolean isKeepAttackFocus() {
        return keepAttackFocus.get();
    }
}
