package io.github.axst;

import io.github.axst.event.EventManager;
import io.github.axst.module.ModuleManager;
import io.github.axst.utils.KeyBinds;
import lombok.Getter;
import net.minecraft.client.Minecraft;

public final class Limee {

    @Getter
    private static final Limee instance = new Limee();
    @Getter
    private static final Minecraft mc = Minecraft.getMinecraft();
    @Getter
    private ModuleManager moduleManager;

    public void initializeClient() {
        moduleManager = new ModuleManager();
        new KeyBinds();
        EventManager.register(this);
    }

    public void shutdownClient() {
        EventManager.unregister(this);
    }
}
