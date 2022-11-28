package io.github.axst.impl.features.misc;

import io.github.axst.api.settings.misc.ColorSettings;
import io.github.axst.impl.features.ui.ModuleRenderer;
import net.minecraft.client.Minecraft;

public class LimeeModule extends ModuleRenderer {

    public ColorSettings settings = new ColorSettings("Test", 1.0, 20, 30, 40, 100);

    public LimeeModule() {
        super("Limee", "Test Module");
        this.version = 2;
        drawComponent(100, 100);
        addSettings(settings);
    }

    @Override
    public void drawModule() {
        Minecraft.getMinecraft().fontRendererObj.drawString(getName(), getX(), getY(), settings.getColor());
    }
}
