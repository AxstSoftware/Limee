package io.github.axst.impl.features.misc;

import io.github.axst.Limee;
import io.github.axst.api.settings.misc.ColorSettings;
import io.github.axst.impl.features.ui.ModuleRenderer;

public class LimeeModule extends ModuleRenderer {

    public ColorSettings settings = new ColorSettings("Test", 1.0, 20, 30, 40, 100);

    public LimeeModule() {
        super("Limee", "Test Module", 100, 50);
        this.version = 2;
        addSettings(settings);
    }

    @Override
    public void drawModule() {
        Limee.getMc().fontRendererObj.drawString(getName(), getX(), getY(), settings.getColor());
    }
}
