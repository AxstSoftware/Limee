package io.github.axst.mixins.ui;

import io.github.axst.limeemc.LimeeGuiMainMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiMainMenu.class)
public class GuiMainMenuMixins {

    @Inject(method = "initGui", at = @At("HEAD"))
    public void injectInitGui(CallbackInfo ci) {
        Minecraft.getMinecraft().displayGuiScreen(new LimeeGuiMainMenu());
    }

}
