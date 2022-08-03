package io.github.axst.api.discord;

import io.github.axst.Limee;
import net.arikia.dev.drpc.DiscordEventHandlers;
import net.arikia.dev.drpc.DiscordRPC;
import net.arikia.dev.drpc.DiscordRichPresence;

public class Discord {

    public boolean running;
    public long created;

    public Discord(Builder builder) {
        this.running = builder.running;
        this.created = builder.created;
    }

    public void create() {
        this.created = System.currentTimeMillis();
        DiscordEventHandlers.Builder builder = new DiscordEventHandlers.Builder();
        DiscordEventHandlers handlers = builder.build();
        DiscordRPC.discordInitialize("962295944366411836", handlers, true);
        new Thread(() -> {
            while (running) DiscordRPC.discordRunCallbacks();
        }).start();
    }

    public void close() {
        this.running = false;
        DiscordRPC.discordShutdown();
    }

    public static class Builder {
        public boolean running = true;
        public long created = 0;
        String state = "Idle";
        DiscordRichPresence.Builder b = new DiscordRichPresence.Builder(state);

        public Builder setState(String state) {
            this.state = state;
            return this;
        }

        public Builder setDetails(String details) {
            b.setDetails(details);
            return this;
        }

        public Discord build() {
            Discord discord = new Discord(this);
            discord.create();
            b.setBigImage("logo", "Limee Client - Playing");
            if (Limee.getInstance().isDev()) b.setSmallImage("dev", "SRC on GitHub.");
            b.setStartTimestamps(this.created);
            DiscordRPC.discordUpdatePresence(b.build());
            return discord;
        }
    }
}
