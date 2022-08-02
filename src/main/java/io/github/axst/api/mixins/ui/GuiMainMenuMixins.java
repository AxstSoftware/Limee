package io.github.axst.api.mixins.ui;

import net.minecraft.client.gui.GuiMainMenu;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(GuiMainMenu.class)
public class GuiMainMenuMixins {

    // @Inject(method = "initGui", at = @At("HEAD"))
    // public void injectInitGui(CallbackInfo ci) {
    //    Minecraft.getMinecraft().displayGuiScreen(new LimeeGuiMainMenu());
    // }

}
