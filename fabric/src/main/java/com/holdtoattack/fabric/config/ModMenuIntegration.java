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

            general.addEntry(entryBuilder.startBooleanToggle(Component.literal("Enabled"), HoldToAttackConfig.isEnabled())
                    .setDefaultValue(true)
                    .setTooltip(Component.literal("Enable or disable the auto-attack feature entirely"))
                    .setSaveConsumer(newValue -> HoldToAttackConfig.setEnabled(newValue))
                    .build());

            general.addEntry(entryBuilder.startBooleanToggle(Component.literal("Require Target"), HoldToAttackConfig.isRequireTarget())
                    .setDefaultValue(true)
                    .setTooltip(Component.literal("Only attack if you are actually looking at a mob/entity"))
                    .setSaveConsumer(newValue -> HoldToAttackConfig.setRequireTarget(newValue))
                    .build());

            general.addEntry(entryBuilder.startBooleanToggle(Component.literal("Keep Attack Focus"), HoldToAttackConfig.isKeepAttackFocus())
                    .setDefaultValue(true)
                    .setTooltip(Component.literal("After attacking an entity, ignore block mining/interaction until the attack key is released"))
                    .setSaveConsumer(newValue -> HoldToAttackConfig.setKeepAttackFocus(newValue))
                    .build());

            return builder.build();
        };
    }
}
