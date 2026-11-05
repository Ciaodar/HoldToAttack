package com.holdtoattack.mixin;

import com.holdtoattack.config.HoldToAttackConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.phys.HitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Minecraft.class)
public abstract class MinecraftClientMixin {

    @Shadow public LocalPlayer player;
    @Shadow public Options options;
    @Shadow public HitResult hitResult;
    @Shadow public MultiPlayerGameMode gameMode;

    @Shadow protected abstract boolean startAttack();

    @Unique
    private boolean holdtoattack$attackLocked = false;

    @Inject(method = "tick", at = @At("HEAD"))
    private void onTick(CallbackInfo ci) {
        if (!HoldToAttackConfig.isEnabled() || !HoldToAttackConfig.isKeepAttackFocus() || this.player == null || this.options == null || !this.options.keyAttack.isDown()) {
            this.holdtoattack$attackLocked = false;
        }

        if (!HoldToAttackConfig.isEnabled()) return;

        if (this.player != null && this.options != null && this.options.keyAttack.isDown()) {
            if (this.player.getAttackStrengthScale(0.0F) >= 1.0F) {
                boolean hasTarget = this.hitResult != null && this.hitResult.getType() != HitResult.Type.MISS;
                if (this.holdtoattack$attackLocked) {
                    if (this.hitResult != null && this.hitResult.getType() == HitResult.Type.ENTITY) {
                        this.startAttack();
                    }
                } else if (!HoldToAttackConfig.isRequireTarget() || hasTarget) {
                    this.startAttack();
                }
            }
        }
    }

    @Inject(method = "startAttack", at = @At("HEAD"), cancellable = true)
    private void onStartAttack(CallbackInfoReturnable<Boolean> cir) {
        if (HoldToAttackConfig.isEnabled() && HoldToAttackConfig.isKeepAttackFocus()) {
            if (this.hitResult != null && this.hitResult.getType() == HitResult.Type.ENTITY) {
                this.holdtoattack$attackLocked = true;
            } else if (this.holdtoattack$attackLocked && this.hitResult != null && this.hitResult.getType() == HitResult.Type.BLOCK) {
                cir.setReturnValue(false);
            }
        }
    }

    @Inject(method = "continueAttack", at = @At("HEAD"), cancellable = true)
    private void onContinueAttack(boolean down, CallbackInfo ci) {
        if (this.holdtoattack$attackLocked && HoldToAttackConfig.isEnabled() && HoldToAttackConfig.isKeepAttackFocus()) {
            if (this.gameMode != null && this.gameMode.isDestroying()) {
                this.gameMode.stopDestroyBlock();
            }
            ci.cancel();
        }
    }
}
