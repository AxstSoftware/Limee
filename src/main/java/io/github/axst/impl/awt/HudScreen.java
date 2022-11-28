package io.github.axst.impl.awt;

import io.github.axst.Limee;
import io.github.axst.impl.features.Module;
import io.github.axst.impl.features.ui.ModuleRenderer;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.input.Mouse;

import java.awt.*;

public class HudScreen extends GuiScreen {

    private int lastDraggedMod = 0;

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawBackground();

        boolean doDrag = true;

        for (Module module : Limee.getInstance().getModuleManager().getModules()) {
            if (module.getEnabled() && module instanceof ModuleRenderer) {
                ((ModuleRenderer) module).renderModule(mouseX, mouseY);
                if (module.hashCode() == this.lastDraggedMod && ((ModuleRenderer) module).draggableComponent.isDraggingModule(mouseX, mouseY))
                    doDrag = false;
            }
        }

        for (Module module : Limee.getInstance().getModuleManager().getModules()) {
            if (doDrag && module.getEnabled() && module instanceof ModuleRenderer && ((ModuleRenderer) module).draggableComponent.isDraggingModule(mouseX, mouseY)) {
                doDrag = false;
                this.lastDraggedMod = module.hashCode();
            }
        }

        if (!doDrag) {
            if (!Mouse.isButtonDown(1)) {
                DrawOnScreen.drawRectLine(2, 0.0, 2.916666637692187 * 0.8571428656578064, this.height, 0.0, new Color(0, 84, 255).getRGB());
                DrawOnScreen.drawRectLine((float) this.width - 5.0f * 0.5f, 0.0, this.width - 2, this.height, 0.0, new Color(0, 84, 255).getRGB());
                DrawOnScreen.drawRectLine(0.0, 2, this.width, 1.1547619104385376 * 2.164948442965692, 0.0, new Color(0, 84, 255).getRGB());
                DrawOnScreen.drawRectLine(0.0, (float) this.height - 1.3529412f * 2.5869565f, this.width, this.height - 3, 0.0, new Color(0, 84, 255).getRGB());
            }
        }
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    public void drawBackground() {
        this.drawRectBackground(0);
    }

    public void drawRectBackground(int tint) {
        if (this.mc.theWorld != null) {
            this.drawGradientRect(0, 0, this.width, this.height, (new Color(0, 0, 0, 120)).getRGB(), (new Color(0, 0, 0, 105)).getRGB());
        } else {
            this.drawBackground(tint);
        }
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

}
