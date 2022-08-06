package io.github.axst.api.discord;

import net.arikia.dev.drpc.DiscordEventHandlers;
import net.arikia.dev.drpc.DiscordRPC;
import net.arikia.dev.drpc.DiscordRichPresence;

public class Discord {

    protected long created;
    protected boolean running;

    public Discord(Builder builder) {
        this.running = builder.running;
        this.created = builder.created;
    }

    public void close() {
        this.running = false;
        DiscordRPC.discordShutdown();
    }

    public static class Update {

        private static DiscordRichPresence.Builder b;

        public Update(String state) {
            b = new DiscordRichPresence.Builder(state);
        }

        public void setDetails(String details) {
            b.setDetails(details);
        }

    }

    public static class Builder {

        private final DiscordRichPresence.Builder b = Update.b;
        private final DiscordEventHandlers.Builder builder = new DiscordEventHandlers.Builder();
        private boolean running;
        private long created;

        public Builder setApplicationId(String id) {
            DiscordEventHandlers handlers = builder.build();
            DiscordRPC.discordInitialize(id, handlers, true);
            return this;
        }

        public Builder setImage(String key, String text) {
            b.setBigImage(key, text);
            return this;
        }

        public Builder setSmallImage(String key, String text) {
            b.setSmallImage(key, text);
            return this;
        }

        public Discord build() {
            this.created = System.currentTimeMillis();
            new Thread(() -> {
                this.running = true;
                while (running) DiscordRPC.discordRunCallbacks();
            }).start();
            Discord discord = new Discord(this);
            b.setStartTimestamps(this.created);
            DiscordRPC.discordUpdatePresence(b.build());
            return discord;
        }

    }
}
