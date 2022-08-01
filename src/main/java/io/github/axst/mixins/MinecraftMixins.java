package io.github.axst.mixins;

import io.github.axst.IReferences;
import io.github.axst.Limee;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MinecraftMixins {

    @Inject(method = "startGame", at = @At(value = "RETURN", shift = At.Shift.AFTER))
    public void injectStartGame(CallbackInfo ci) {
        Limee.getInstance().initializeClient();
    }

    @Inject(method = "shutdown", at = @At(value = "RETURN", shift = At.Shift.AFTER))
    public void injectShutdown(CallbackInfo ci) {
        Limee.getInstance().shutdown();
    }

    @ModifyConstant(method = "createDisplay()V", constant = @Constant(stringValue = "Minecraft 1.8.9"))
    public String createDisplay(String constant) {
        return IReferences.NAME + " | " + IReferences.VERSION + " - Build: " + IReferences.BUILD + " | Made by " + IReferences.AUTHOR;
    }

    @Inject(method = "runTick", at = @At("HEAD"))
    public void injectRunTick(CallbackInfo ci) {

    }
}
