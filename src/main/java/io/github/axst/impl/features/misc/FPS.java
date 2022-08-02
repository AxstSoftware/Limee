package io.github.axst.impl.features.misc;

import io.github.axst.Limee;
import io.github.axst.impl.features.ui.ModuleRenderer;
import net.minecraft.client.Minecraft;

public class FPS extends ModuleRenderer {
    
    public FPS() {
        super("FPS", "Show your mc FPS", 100, 100);
    }

    @Override
    public void drawModule() {
        Limee.getMc().fontRendererObj.drawString("FPS : " + Minecraft.getDebugFPS(), getX(), getY(), -1);
    }

    @Override
    public void renderModule(int mouseX, int mouseY) {
        super.renderModule(mouseX, mouseY);
        Limee.getMc().fontRendererObj.drawString("FPS : " + Minecraft.getDebugFPS(), getX(), getY(), -1);
    }

    @Override
    public int getWidthIn() {
        return Limee.getMc().fontRendererObj.getStringWidth("FPS : " + Minecraft.getDebugFPS());
    }

    @Override
    public int getHeightIn() {
        return Limee.getMc().fontRendererObj.FONT_HEIGHT;
    }

}
