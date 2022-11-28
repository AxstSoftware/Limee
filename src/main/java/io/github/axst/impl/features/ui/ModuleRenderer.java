package io.github.axst.impl.features.ui;

import io.github.axst.impl.awt.DrawOnScreen;
import io.github.axst.impl.features.Module;
import lombok.Getter;

import java.awt.*;

@Getter
public abstract class ModuleRenderer extends Module {

    public int widthIn;
    public int heightIn;

    public DraggableComponent draggableComponent;

    /**
     * ModuleRenderer used for render mods.
     * // TODO: remove this shitty delay and make some good checker
     *
     * @param name        String name of mod.
     * @param description String description of mod.
     */
    public ModuleRenderer(String name, String description) {
        super(name, description);
    }

    public void drawComponent(int x, int y) {
        draggableComponent = new DraggableComponent(x, y, getWidthIn(), getHeightIn());
    }

    /**
     * Draw module on screen.
     */
    public abstract void drawModule();

    /**
     * Render module on HUD Screen.
     *
     * @param mouseX int mouse x of the screen.
     * @param mouseY int mouse y of the screen.
     */
    public void renderModule(int mouseX, int mouseY) {
        boolean hovered = mouseX >= this.getX() && mouseX <= this.getX() + this.getWidthIn() && mouseY >= this.getY() && mouseY <= this.getY() + this.getHeightIn();
        if (hovered)
            DrawOnScreen.drawHollowRect(this.getX() - 2, this.getY() - 2, this.getWidthIn() + 3, this.getHeightIn() + 2, (new Color(0, 204, 255, 152)).getRGB());

        DrawOnScreen.drawHollowRect(this.getX() - 2, this.getY() - 2, this.getWidthIn() + 3, this.getHeightIn() + 2, (new Color(170, 170, 170, 100)).getRGB());
    }

    public int getX() {
        return draggableComponent.getX();
    }

    public int getY() {
        return draggableComponent.getY();
    }
}
