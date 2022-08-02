package io.github.axst;

import io.github.axst.limeemc.LimeeKeybindings;
import io.github.axst.logger.Logger;
import io.github.axst.module.ModuleManager;
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
    private final Bus<Object> bus = new EventBus<>();

    @Getter
    private final boolean dev = true;

    @Getter
    private ModuleManager moduleManager;

    @Getter
    private Logger logger;

    public void initializeClient() {
        logger = new Logger.BuilderLogger("Limee").dateFormat(null).build();
        moduleManager = new ModuleManager();
        new LimeeKeybindings();
        bus.subscribe(this);
        logger.sendLog("Client initialized", Logger.LogLevel.INFO);
    }

    public void shutdownClient() {
        bus.subscribe(this);
    }

}
