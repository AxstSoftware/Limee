package io.github.axst.mixins.entity;

import net.minecraft.client.entity.EntityPlayerSP;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityPlayerSP.class)
public class EntityPlayerSPMixins {

    @Inject(method = "onUpdate", at = @At("HEAD"))
    public void injectOnUpdate(CallbackInfo ci) {

    }

}