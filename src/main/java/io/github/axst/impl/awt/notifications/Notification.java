package io.github.axst.impl.awt.notifications;

import io.github.axst.Limee;
import io.github.axst.api.logger.Logger;
import lombok.Getter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Notification extends GuiScreen {

    @Getter
    public static List<Notification> livingNotifications;
    @Getter
    public String name;
    @Getter
    public String description;
    @Getter
    public NotificationType notifications;
    @Getter
    public Color color;
    @Getter
    public String prefix;
    @Getter
    private int time;

    public Notification(Builder builder) {
        livingNotifications = Builder.livingNotifications;
        this.time = builder.time;
        this.name = builder.name;
        this.description = builder.description;
        this.notifications = builder.notifications;
    }

    protected void renderNotification() {
        if (getNotifications() == NotificationType.INFO) {
            color = new Color(62, 222, 179, 150);
            prefix = "INFO: ";
        } else if (getNotifications() == NotificationType.WARN) {
            color = new Color(255, 223, 126, 150);
            prefix = "WARN: ";
        }
        ScaledResolution res = new ScaledResolution(Minecraft.getMinecraft());
        Limee.getMc().fontRendererObj.drawString(prefix + this.name, res.getScaledWidth() / 2, 23, -1);
        --time;
    }

    public boolean isLiving() {
        return this.time > 0;
    }

    public enum NotificationType {
        INFO, WARN
    }

    public static class Builder {
        public static List<Notification> livingNotifications;
        private String name;
        private String description;
        private NotificationType notifications;
        private int time;

        public Builder() {
            livingNotifications = new ArrayList<>();
        }

        public static void renderInGameNotification() {
            if (!livingNotifications.isEmpty()) {
                Iterator<Notification> iter = livingNotifications.iterator();
                while (iter.hasNext()) {
                    Notification notification = iter.next();
                    notification.renderNotification();
                    if (!notification.isLiving()) iter.remove();
                }
            }
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setNotifications(NotificationType notificationType) {
            this.notifications = notificationType;
            return this;
        }

        public Builder setTime(int time) {
            this.time = time;
            if (time != 0) this.time = 150;
            return this;
        }

        public void build() {
            Notification notifications = new Notification(this);
            validateNotifications(notifications);
            livingNotifications.add(notifications);
        }

        public void validateNotifications(Notification notifications) {
            if (notifications.getColor() == null && notifications.getName() == null && notifications.getPrefix() == null && notifications.getDescription() == null) {
                Limee.getInstance().getLogger().sendLog("Wrong Notifications Build.", Logger.LogLevel.WARN);
            }
        }
    }

}
