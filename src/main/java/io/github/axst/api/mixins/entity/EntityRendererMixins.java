package io.github.axst.api.mixins.entity;

import io.github.axst.Limee;
import io.github.axst.impl.awt.notifications.Notification;
import io.github.axst.impl.events.Render3DEvent;
import net.minecraft.client.renderer.EntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityRenderer.class)
public class EntityRendererMixins {

    @Inject(method = "renderWorldPass", at = @At(value = "FIELD", target = "Lnet/minecraft/client/renderer/EntityRenderer;renderHand:Z", shift = At.Shift.BEFORE))
    private void renderWorldPass(int pass, float partialTicks, long finishTimeNano, CallbackInfo callbackInfo) {
        Render3DEvent event = new Render3DEvent(partialTicks);
        Limee.getInstance().getBus().post(event);
    }

    @Inject(method = "updateCameraAndRender", at = @At(value = "INVOKE", target = "net/minecraft/client/renderer/GlStateManager.clear(I)V", shift = At.Shift.AFTER))
    private void updateCameraAndRender(float partialTicks, long nanoTime, CallbackInfo callbackInfo) {
        Notification.Builder.renderInGameNotification();
    }

}
