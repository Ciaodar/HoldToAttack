package com.holdtoattack.neoforge;

import com.holdtoattack.config.HoldToAttackConfig;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLPaths;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.fml.ModLoadingContext;

@Mod(value = "holdtoattack", dist = Dist.CLIENT)
public class HoldToAttackNeoForge {

    public HoldToAttackNeoForge() {
        HoldToAttackConfig.init(FMLPaths.CONFIGDIR.get().toFile());

        ModLoadingContext.get().registerExtensionPoint(IConfigScreenFactory.class, () -> (client, parent) -> {
            me.shedaniel.clothconfig2.api.ConfigBuilder builder = me.shedaniel.clothconfig2.api.ConfigBuilder.create()
                    .setParentScreen(parent)
                    .setTitle(net.minecraft.network.chat.Component.literal("Hold To Attack Config"));

            builder.setSavingRunnable(() -> {
                HoldToAttackConfig.save();
            });

            me.shedaniel.clothconfig2.api.ConfigCategory general = builder.getOrCreateCategory(net.minecraft.network.chat.Component.literal("General"));
            me.shedaniel.clothconfig2.api.ConfigEntryBuilder entryBuilder = builder.entryBuilder();

            general.addEntry(entryBuilder.startBooleanToggle(net.minecraft.network.chat.Component.literal("Enabled"), HoldToAttackConfig.get().enabled)
                    .setDefaultValue(true)
                    .setTooltip(net.minecraft.network.chat.Component.literal("Enable or disable the auto-attack feature entirely"))
                    .setSaveConsumer(newValue -> HoldToAttackConfig.get().enabled = newValue)
                    .build());

            general.addEntry(entryBuilder.startBooleanToggle(net.minecraft.network.chat.Component.literal("Require Target"), HoldToAttackConfig.get().requireTarget)
                    .setDefaultValue(true)
                    .setTooltip(net.minecraft.network.chat.Component.literal("Only attack if you are actually looking at a mob/entity"))
                    .setSaveConsumer(newValue -> HoldToAttackConfig.get().requireTarget = newValue)
                    .build());

            return builder.build();
        });
        
        System.out.println("[HoldToAttack] NeoForge initialized!");
    }
}
