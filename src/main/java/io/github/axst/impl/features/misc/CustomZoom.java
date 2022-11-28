package io.github.axst.impl.features.misc;

import io.github.axst.api.limee.LimeeKey;
import io.github.axst.api.settings.misc.ModeSettings;
import io.github.axst.impl.events.EventTick;
import io.github.axst.impl.features.Module;
import io.github.nevalackin.homoBus.Listener;
import io.github.nevalackin.homoBus.annotations.EventLink;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Mouse;

public class CustomZoom extends Module {

    public boolean zoomed;
    public float prevFov = Minecraft.getMinecraft().gameSettings.fovSetting;
    public ModeSettings settings = new ModeSettings("Test", 1.0, 1, "test", "test2");
    private int scrollAmount;
    @EventLink
    public Listener<EventTick> customZoom = event -> {
        if (Minecraft.getMinecraft().theWorld != null) {
            if (LimeeKey.CUSTOM_ZOM.isPressed()) {
                if (settings.modes.equals("test")) {
                    int eventDWheel = Mouse.getDWheel();
                    if (eventDWheel < 0) {
                        scrollAmount += 12;
                    } else if (eventDWheel > 0) {
                        scrollAmount -= 2;
                    }
                    zoomed = false;
                    Minecraft.getMinecraft().gameSettings.fovSetting = (int) (Minecraft.getMinecraft().gameSettings.mouseSensitivity * 100 + scrollAmount);
                } else if (settings.modes.equals("test2")) {
                    int eventDWheel = Mouse.getDWheel();
                    if (eventDWheel < 0) {
                        scrollAmount += 1;
                    } else if (eventDWheel > 0) {
                        scrollAmount -= 2;
                    }
                    zoomed = false;
                    Minecraft.getMinecraft().gameSettings.fovSetting = (int) (Minecraft.getMinecraft().gameSettings.mouseSensitivity * 100 + scrollAmount);
                }
            } else if (!zoomed) {
                zoomed = true;
                Minecraft.getMinecraft().gameSettings.fovSetting = prevFov;
            }
        }
    };

    public CustomZoom() {
        super("Custom Zoom", "Use this mod for your custom zoom");
        this.version = 1.2;
        addSettings(settings);
    }

    @Override
    public void onModuleEnable() {
        super.onModuleEnable();
    }
}
