package io.github.axst.mixins;

import io.github.axst.Limee;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MinecraftMixins {

    @Inject(method = "startGame", at = @At(value = "HEAD", shift = At.Shift.AFTER))
    public void injectStartGame(CallbackInfo ci) {
        Limee.getInstance().initializeClient();
    }
}
