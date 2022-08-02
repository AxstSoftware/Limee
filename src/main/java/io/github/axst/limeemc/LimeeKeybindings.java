package io.github.axst.limeemc;

import io.github.axst.Limee;
import net.minecraft.client.settings.KeyBinding;
import org.apache.commons.lang3.ArrayUtils;
import org.lwjgl.input.Keyboard;

public class LimeeKeybindings {

    public static KeyBinding TEST = new KeyBinding("Test", Keyboard.KEY_RSHIFT, "Limee");

    public LimeeKeybindings() {
        registerKeyBindings(TEST);
    }

    public void registerKeyBindings(final KeyBinding keyBinds) {
        Limee.getMc().gameSettings.keyBindings = ArrayUtils.add(Limee.getMc().gameSettings.keyBindings, keyBinds);
    }

}
