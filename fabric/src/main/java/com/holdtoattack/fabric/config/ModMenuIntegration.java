package com.holdtoattack.fabric.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import com.holdtoattack.config.HoldToAttackConfig;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.network.chat.Component;

public class ModMenuIntegration implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> {
            ConfigBuilder builder = ConfigBuilder.create()
                    .setParentScreen(parent)
                    .setTitle(Component.literal("Hold To Attack Config"));

            builder.setSavingRunnable(() -> {
                HoldToAttackConfig.save();
            });

            ConfigCategory general = builder.getOrCreateCategory(Component.literal("General"));
            ConfigEntryBuilder entryBuilder = builder.entryBuilder();

            general.addEntry(entryBuilder.startBooleanToggle(Component.literal("Enabled"), HoldToAttackConfig.get().enabled)
                    .setDefaultValue(true)
                    .setTooltip(Component.literal("Enable or disable the auto-attack feature entirely"))
                    .setSaveConsumer(newValue -> {
                        HoldToAttackConfig.get().enabled = newValue;
                        HoldToAttackConfig.setEnabled(newValue);
                    })
                    .build());

            general.addEntry(entryBuilder.startBooleanToggle(Component.literal("Require Target"), HoldToAttackConfig.get().requireTarget)
                    .setDefaultValue(true)
                    .setTooltip(Component.literal("Only attack if you are actually looking at a mob/entity"))
                    .setSaveConsumer(newValue -> {
                        HoldToAttackConfig.get().requireTarget = newValue;
                        HoldToAttackConfig.setRequireTarget(newValue);
                    })
                    .build());

            general.addEntry(entryBuilder.startBooleanToggle(Component.literal("Keep Attack Focus"), HoldToAttackConfig.get().keepAttackFocus)
                    .setDefaultValue(true)
                    .setTooltip(Component.literal("If true, keeps attack focus and ignores blocks while holding attack"))
                    .setSaveConsumer(newValue -> {
                        HoldToAttackConfig.get().keepAttackFocus = newValue;
                        HoldToAttackConfig.setKeepAttackFocus(newValue);
                    })
                    .build());

            return builder.build();
        };
    }
}
