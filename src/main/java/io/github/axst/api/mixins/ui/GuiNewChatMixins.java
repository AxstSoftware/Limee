package io.github.axst.api.mixins.ui;

import net.minecraft.client.gui.GuiNewChat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(GuiNewChat.class)
public abstract class GuiNewChatMixins {

    @Redirect(method = "drawChat", at = @At(value = "INVOKE", target = "net/minecraft/client/gui/GuiNewChat.drawRect (IIIII)V"))
    public void drawChat(int i1, int i2, int i3, int i4, int i5) {
    }

}
