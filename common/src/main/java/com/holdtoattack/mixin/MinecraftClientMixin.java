package com.holdtoattack.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.Options;
import net.minecraft.world.phys.HitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public abstract class MinecraftClientMixin {

    @Shadow public LocalPlayer player;
    @Shadow public Options options;
    @Shadow public HitResult hitResult;

    @Shadow protected abstract boolean startAttack();

    @Inject(method = "tick", at = @At("HEAD"))
    private void onTick(CallbackInfo ci) {
        if (!com.holdtoattack.config.HoldToAttackConfig.get().enabled) return;

        if (this.player != null && this.options.keyAttack.isDown()) {
            if (this.player.getAttackStrengthScale(0.0F) >= 1.0F) {
                boolean hasTarget = this.hitResult != null && this.hitResult.getType() != HitResult.Type.MISS;
                if (!com.holdtoattack.config.HoldToAttackConfig.get().requireTarget || hasTarget) {
                    this.startAttack();
                }
            }
        }
    }
}
