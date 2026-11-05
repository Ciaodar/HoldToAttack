package com.holdtoattack.forge.config;

import com.holdtoattack.config.IConfigProxy;
import net.minecraftforge.common.ForgeConfigSpec;

public class ForgeConfigProxy implements IConfigProxy {
    public static final ForgeConfigSpec SPEC;
    public static final ForgeConfigProxy INSTANCE;

    public final ForgeConfigSpec.BooleanValue enabled;
    public final ForgeConfigSpec.BooleanValue requireTarget;
    public final ForgeConfigSpec.BooleanValue keepAttackFocus;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        INSTANCE = new ForgeConfigProxy(builder);
        SPEC = builder.build();
    }

    private ForgeConfigProxy(ForgeConfigSpec.Builder builder) {
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
