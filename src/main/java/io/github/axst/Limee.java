package io.github.axst;

import io.github.axst.events.TickEvent;
import io.github.axst.module.ModuleManager;
import lombok.Getter;
import net.minecraft.client.Minecraft;
import org.lwjgl.Sys;

public final class Limee {

    @Getter
    private static final Limee instance = new Limee();
    @Getter
    private static final Minecraft mc = Minecraft.getMinecraft();
    @Getter
    private ModuleManager moduleManager;

    public void initializeClient() {
        moduleManager = new ModuleManager();
    }

    public void shutdown() {

    }

    @TickEvent
    public void test() {
        System.out.println("TESSTTTSETST");
    }
}
