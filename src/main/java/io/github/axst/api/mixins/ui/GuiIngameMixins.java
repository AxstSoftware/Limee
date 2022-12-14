package io.github.axst.api.mixins.ui;

import io.github.axst.Limee;
import io.github.axst.impl.awt.notifications.Notification;
import net.minecraft.client.gui.GuiIngame;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiIngame.class)
public class GuiIngameMixins {

    @Inject(method = "renderGameOverlay", at = @At("RETURN"))
    private void runTick(CallbackInfo info) {
        Limee.getInstance().getModuleManager().renderModules();
        Notification.Builder.renderInGameNotification();
    }

}
