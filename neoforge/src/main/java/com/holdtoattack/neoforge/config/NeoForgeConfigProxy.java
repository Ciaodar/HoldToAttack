package com.holdtoattack.neoforge.config;

import com.holdtoattack.config.IConfigProxy;
import net.neoforged.neoforge.common.ModConfigSpec;

public class NeoForgeConfigProxy implements IConfigProxy {
    public static final NeoForgeConfigProxy INSTANCE = new NeoForgeConfigProxy();
    public static final ModConfigSpec SPEC;

    private static final ModConfigSpec.BooleanValue ENABLED;
    private static final ModConfigSpec.BooleanValue REQUIRE_TARGET;
    private static final ModConfigSpec.BooleanValue KEEP_ATTACK_FOCUS;

    static {
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();
        builder.push("general");
        ENABLED = builder
                .comment("Enable or disable the auto-attack feature entirely")
                .define("enabled", true);
        REQUIRE_TARGET = builder
                .comment("Only attack if you are actually looking at a mob/entity")
                .define("requireTarget", true);
        KEEP_ATTACK_FOCUS = builder
                .comment("After attacking an entity, ignore block mining/interaction until the attack key is released")
                .define("keepAttackFocus", true);
        builder.pop();
        SPEC = builder.build();
    }

    @Override
    public boolean isEnabled() {
        return ENABLED.get();
    }

    @Override
    public boolean isRequireTarget() {
        return REQUIRE_TARGET.get();
    }

    @Override
    public boolean isKeepAttackFocus() {
        return KEEP_ATTACK_FOCUS.get();
    }
}
