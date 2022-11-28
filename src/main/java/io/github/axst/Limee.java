package io.github.axst;

import io.github.axst.api.discord.Discord;
import io.github.axst.api.limee.LimeeKey;
import io.github.axst.api.logger.Logger;
import io.github.axst.api.utils.VersionChecker;
import io.github.axst.impl.awt.HudScreen;
import io.github.axst.impl.awt.notifications.Notification;
import io.github.axst.impl.events.EventUpdate;
import io.github.axst.impl.events.IEvent;
import io.github.axst.impl.features.ModuleManager;
import io.github.nevalackin.homoBus.Listener;
import io.github.nevalackin.homoBus.annotations.EventLink;
import io.github.nevalackin.homoBus.bus.Bus;
import io.github.nevalackin.homoBus.bus.impl.EventBus;
import lombok.Getter;
import net.minecraft.client.Minecraft;

@Getter
public final class Limee {

    @Getter
    private static final Limee instance = new Limee();

    private final Bus<IEvent> bus = new EventBus<>();

    @EventLink
    public Listener<EventUpdate> someTest = event -> {
        if (LimeeKey.HUD_SCREEN.isKeyDown()) {
            Minecraft.getMinecraft().displayGuiScreen(new HudScreen());
        }
    };

    private ModuleManager moduleManager;

    public void initializeClient() {
        moduleManager = new ModuleManager();
        Logger.register("Limee");
        new Discord.Builder()
                .setApplicationId("962295944366411836")
                .setImage("logo", "Playing Limee")
                .setSmallImage("dev", "SRC on Github").build();
        new Notification.Builder()
                .setName("Test")
                .setDescription("Some Test")
                .setNotifications(Notification.NotificationType.INFO)
                .setTime(250)
                .build();
        new LimeeKey();
        new VersionChecker().check();
        bus.subscribe(instance);
        Logger.sendLog("Client initialized", Logger.LogLevel.INFO);
    }

    public void shutdownClient() {
        bus.unsubscribe(instance);
        new Discord.Builder().build().close();
    }

}
