package io.github.axst;

import io.github.axst.api.discord.Discord;
import io.github.axst.api.limee.LimeeKey;
import io.github.axst.api.logger.Logger;
import io.github.axst.impl.awt.HudScreen;
import io.github.axst.impl.awt.notifications.Notification;
import io.github.axst.impl.events.Event;
import io.github.axst.impl.events.EventUpdate;
import io.github.axst.impl.features.ModuleManager;
import io.github.nevalackin.homoBus.Listener;
import io.github.nevalackin.homoBus.annotations.EventLink;
import io.github.nevalackin.homoBus.bus.Bus;
import io.github.nevalackin.homoBus.bus.impl.EventBus;
import lombok.Getter;
import net.minecraft.client.Minecraft;

public final class Limee {

    @Getter
    private static final Limee instance = new Limee();
    @Getter
    private static final Minecraft mc = Minecraft.getMinecraft();
    @Getter
    private final Bus<Event> bus = new EventBus<>();

    @Getter
    private final boolean dev = true;

    @EventLink
    public Listener<EventUpdate> someTest = event -> {
        if (LimeeKey.HUD_SCREEN.isKeyDown()) {
            Limee.getMc().displayGuiScreen(new HudScreen());
        }
    };

    @Getter
    private ModuleManager moduleManager;
    @Getter
    private Logger logger;
    @Getter
    private Notification.Builder notificationHandler;
    @Getter
    private Discord discord;

    public void initializeClient() {
        bus.subscribe(instance);
        logger = new Logger.BuilderLogger("Limee").dateFormat(null).build();
        discord = new Discord.Builder().setDetails("Playing Minecraft 1.8.9").setState("test").build();
        moduleManager = new ModuleManager();
        new LimeeKey();
        notificationHandler = new Notification.Builder();
        new Notification.Builder()
                .setName("Test")
                .setDescription("Some Test")
                .setNotifications(Notification.NotificationType.INFO)
                .setTime(150)
                .build();
        logger.sendLog("Client initialized", Logger.LogLevel.INFO);
    }

    public void shutdownClient() {
        bus.unsubscribe(instance);
        discord.close();
    }

}
