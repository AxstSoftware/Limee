package io.github.axst.api.discord;

import io.github.axst.Limee;
import lombok.Getter;
import net.arikia.dev.drpc.DiscordEventHandlers;
import net.arikia.dev.drpc.DiscordRPC;
import net.arikia.dev.drpc.DiscordRichPresence;

public class DiscordRP {

    @Getter
    private static final DiscordRP instance = new DiscordRP();

    private long created = 0;

    private boolean running = true;

    public void startDiscordRPC() {
        this.created = System.currentTimeMillis();
        DiscordEventHandlers.Builder builder = new DiscordEventHandlers.Builder();
        builder.setReadyEventHandler(user -> DiscordRP.getInstance().update("Playing Minecraft 1.8.9", user.username));
        DiscordEventHandlers handlers = builder.build();
        DiscordRPC.discordInitialize("962295944366411836", handlers, true);
        new Thread(() -> {
            while (running) DiscordRPC.discordRunCallbacks();
        }).start();
    }

    public void shutdownDiscordRPC() {
        this.running = false;
        DiscordRPC.discordShutdown();
    }

    /**
     * Discord Update method.
     *
     * @param firstLine  String first line of the RPC
     * @param secondLine String second line of the RPC
     */
    public void update(String firstLine, String secondLine) {
        DiscordRichPresence.Builder b = new DiscordRichPresence.Builder(secondLine);
        b.setBigImage("logo", "Limee Client - Playing");
        if (Limee.getInstance().isDev()) b.setSmallImage("dev", "SRC on GitHub.");
        b.setDetails(firstLine);
        b.setStartTimestamps(this.created);
        DiscordRPC.discordUpdatePresence(b.build());
    }
}
