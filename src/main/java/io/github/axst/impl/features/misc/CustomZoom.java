package io.github.axst.impl.features.misc;

import io.github.axst.Limee;
import io.github.axst.api.limee.LimeeKey;
import io.github.axst.impl.awt.notifications.Notification;
import io.github.axst.impl.events.EventTick;
import io.github.axst.impl.features.Module;
import io.github.nevalackin.homoBus.Listener;
import io.github.nevalackin.homoBus.annotations.EventLink;
import org.lwjgl.input.Mouse;

public class CustomZoom extends Module {

    public boolean zoomed;
    public float prevFov = Limee.getMc().gameSettings.fovSetting;
    private int scrollAmount;

    @EventLink
    public Listener<EventTick> customZoom = event -> {
        if (Limee.getMc().theWorld != null) {
            if (LimeeKey.CUSTOM_ZOM.isPressed()) {
                new Notification.Builder()
                        .setName("Test")
                        .setDescription("Some Test")
                        .setNotifications(Notification.NotificationType.INFO)
                        .setTime(150)
                        .build();
                int eventDWheel = Mouse.getDWheel();
                if (eventDWheel < 0) {
                    scrollAmount += 2;
                } else if (eventDWheel > 0) {
                    scrollAmount -= 2;
                }
                zoomed = false;
                Limee.getMc().gameSettings.fovSetting = (int) (Limee.getMc().gameSettings.mouseSensitivity * 100 + scrollAmount);
            } else if (!zoomed) {
                zoomed = true;
                Limee.getMc().gameSettings.fovSetting = prevFov;
            }
        }
    };

    public CustomZoom() {
        super("Custom Zoom", "Use this mod for your custom zoom");
        this.version = 1.2;
    }

    @Override
    public void onModuleEnable() {
        super.onModuleEnable();
    }
}
