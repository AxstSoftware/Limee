package io.github.axst.api.limee;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import org.apache.commons.lang3.ArrayUtils;
import org.lwjgl.input.Keyboard;

public class LimeeKey {

    public static KeyBinding HUD_SCREEN = new KeyBinding("Hud Screen", Keyboard.KEY_RSHIFT, "Limee");
    public static KeyBinding CUSTOM_ZOM = new KeyBinding("Custom Zoom", Keyboard.KEY_C, "Limee");

    public LimeeKey() {
        registerKeyBindings(HUD_SCREEN);
        registerKeyBindings(CUSTOM_ZOM);
    }

    public void registerKeyBindings(final KeyBinding keyBinds) {
        Minecraft.getMinecraft().gameSettings.keyBindings = ArrayUtils.add(Minecraft.getMinecraft().gameSettings.keyBindings, keyBinds);
    }

}
