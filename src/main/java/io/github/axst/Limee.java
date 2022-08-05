package io.github.axst;

import io.github.axst.api.discord.Discord;
import io.github.axst.api.limee.LimeeKey;
import io.github.axst.api.logger.Logger;
import io.github.axst.api.utils.VersionChecker;
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

    public void initializeClient() {
        notificationHandler = new Notification.Builder();
        moduleManager = new ModuleManager();
        logger = new Logger.BuilderLogger("Limee")
                .dateFormat(null)
                .build();
        new Discord.Builder()
                .setApplicationId("962295944366411836")
                .setImage("logo", "Playing Limee")
                .setSmallImage("dev", "SRC on Github").build().create();
        new Notification.Builder()
                .setName("Test")
                .setDescription("Some Test")
                .setNotifications(Notification.NotificationType.INFO)
                .setTime(150)
                .build();
        new LimeeKey();
        new VersionChecker().check();
        bus.subscribe(instance);
        logger.sendLog("Client initialized", Logger.LogLevel.INFO);
    }

    public void shutdownClient() {
        bus.unsubscribe(instance);
        new Discord.Builder().build().close();
    }

}
