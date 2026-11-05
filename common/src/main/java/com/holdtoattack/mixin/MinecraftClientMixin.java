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
        if (!HoldToAttackConfig.isEnabled() || !HoldToAttackConfig.isKeepAttackFocus() || this.options == null || !this.options.keyAttack.isDown()) {
            this.holdtoattack$attackLocked = false;
        }

        if (HoldToAttackConfig.isEnabled() && this.player != null && this.options != null && this.options.keyAttack.isDown()) {
            if (this.player.getAttackStrengthScale(0.0F) >= 1.0F) {
                boolean canAttack;
                if (HoldToAttackConfig.isRequireTarget()) {
                    canAttack = this.hitResult != null && this.hitResult.getType() == HitResult.Type.ENTITY;
                } else {
                    canAttack = this.hitResult == null || this.hitResult.getType() != HitResult.Type.BLOCK;
                }

                if (canAttack) {
                    this.startAttack();
                }
            }
        }
    }

    @Inject(method = "startAttack", at = @At("HEAD"), cancellable = true)
    private void onStartAttack(CallbackInfoReturnable<Boolean> cir) {
        if (!HoldToAttackConfig.isEnabled() || !HoldToAttackConfig.isKeepAttackFocus()) {
            this.holdtoattack$attackLocked = false;
            return;
        }

        if (this.holdtoattack$attackLocked && this.hitResult != null && this.hitResult.getType() == HitResult.Type.BLOCK) {
            if (this.gameMode != null) {
                this.gameMode.stopDestroyBlock();
            }
            cir.setReturnValue(false);
            return;
        }

        if (this.options != null && this.options.keyAttack.isDown()) {
            if (this.hitResult == null || this.hitResult.getType() != HitResult.Type.BLOCK) {
                this.holdtoattack$attackLocked = true;
                if (this.gameMode != null) {
                    this.gameMode.stopDestroyBlock();
                }
            }
        }
    }

    @Inject(method = "continueAttack", at = @At("HEAD"), cancellable = true)
    private void onContinueAttack(boolean down, CallbackInfo ci) {
        if (!HoldToAttackConfig.isEnabled() || !HoldToAttackConfig.isKeepAttackFocus()) {
            this.holdtoattack$attackLocked = false;
            return;
        }

        if (this.options == null || !this.options.keyAttack.isDown()) {
            this.holdtoattack$attackLocked = false;
            return;
        }

        if (this.holdtoattack$attackLocked) {
            if (this.gameMode != null) {
                this.gameMode.stopDestroyBlock();
            }
            ci.cancel();
        }
    }
}
