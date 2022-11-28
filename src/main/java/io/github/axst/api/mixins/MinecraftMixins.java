package io.github.axst.api.mixins;

import io.github.axst.Limee;
import io.github.axst.api.utils.IReferences;
import io.github.axst.impl.events.EventTick;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MinecraftMixins {

    @Shadow
    public GameSettings gameSettings;

    @Inject(method = "startGame", at = @At("RETURN"))
    public void injectStartGame(CallbackInfo ci) {
        Limee.getInstance().initializeClient();
        gameSettings.guiScale = 2;
    }

    @Inject(method = "shutdown", at = @At("RETURN"))
    public void injectShutdown(CallbackInfo ci) {
        Limee.getInstance().shutdownClient();
    }

    @ModifyConstant(method = "createDisplay()V", constant = @Constant(stringValue = "Minecraft 1.8.9"))
    public String createDisplay(String constant) {
        return IReferences.NAME + " | " + IReferences.VERSION + " - Build: " + IReferences.BUILD + " | Made by " + IReferences.AUTHOR;
    }

    @Inject(method = "runTick", at = @At("HEAD"))
    public void injectClientTick(CallbackInfo ci) {
        EventTick event = new EventTick();
        Limee.getInstance().getBus().post(event);
    }

    @ModifyConstant(method = "getLimitFramerate", constant = @Constant(intValue = 30))
    public int modifyLimitFramer(int constant) {
        return 240;
    }

}