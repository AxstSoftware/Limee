package io.github.axst.impl.features.misc;

import io.github.axst.Limee;
import io.github.axst.api.settings.misc.BooleanSettings;
import io.github.axst.impl.features.ui.ModuleRenderer;
import net.minecraft.client.Minecraft;

public class FPS extends ModuleRenderer {

    public BooleanSettings test = new BooleanSettings("Test", 1, true);

    public FPS() {
        super("FPS", "Show your mc FPS", 100, 100);
        addSettings(test);
    }

    @Override
    public void drawModule() {
        if (test.isEnabled())
            Limee.getMc().fontRendererObj.getStringWidth("[ENABLED] FPS : " + Minecraft.getDebugFPS());
        else Limee.getMc().fontRendererObj.getStringWidth("[DISABLE] FPS : " + Minecraft.getDebugFPS());
    }

    @Override
    public void renderModule(int mouseX, int mouseY) {
        super.renderModule(mouseX, mouseY);
        if (test.isEnabled())
            Limee.getMc().fontRendererObj.getStringWidth("[ENABLED] FPS : " + Minecraft.getDebugFPS());
        else Limee.getMc().fontRendererObj.getStringWidth("[DISABLE] FPS : " + Minecraft.getDebugFPS());
    }

    @Override
    public int getWidthIn() {
        if (test.isEnabled())
            return Limee.getMc().fontRendererObj.getStringWidth("[ENABLED] FPS : " + Minecraft.getDebugFPS());
        else return Limee.getMc().fontRendererObj.getStringWidth("[DISABLE] FPS : " + Minecraft.getDebugFPS());
    }

    @Override
    public int getHeightIn() {
        return Limee.getMc().fontRendererObj.FONT_HEIGHT;
    }

}
