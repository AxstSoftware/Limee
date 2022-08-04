package io.github.axst.api.mixins.ui;

import io.github.axst.api.discord.Discord;
import net.minecraft.client.gui.GuiMainMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiMainMenu.class)
public class GuiMainMenuMixins {

    @Inject(method = "initGui", at = @At("HEAD"))
    public void injectInitGui(CallbackInfo ci) {
        new Discord.Update("Idle").setDetails("Main Menu");
    }

}
