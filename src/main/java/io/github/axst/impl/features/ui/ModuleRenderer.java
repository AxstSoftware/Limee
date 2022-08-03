package io.github.axst.impl.features.ui;

import io.github.axst.impl.awt.DrawOnScreen;
import io.github.axst.impl.features.Module;
import lombok.Getter;

import java.awt.*;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public abstract class ModuleRenderer extends Module {

    @Getter
    public int widthIn;
    @Getter
    public int heightIn;

    public DraggableComponent draggableComponent;

    /**
     * ModuleRenderer used for render mods.
     *
     * @param name        String name of mod.
     * @param description String description of mod.
     * @param x           int x of the mods position.
     * @param y           int y of the mods position.
     */
    public ModuleRenderer(String name, String description, int x, int y) {
        super(name, description);
        final ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(4);
        executor.schedule(() -> draggableComponent = new DraggableComponent(x, y, getWidthIn(), getHeightIn()), 3, TimeUnit.SECONDS);
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
            DrawOnScreen.render(e -> e.drawHollowRect(this.getX() - 2, this.getY() - 2, this.getWidthIn() + 3, this.getHeightIn() + 2, (new Color(0, 204, 255, 152)).getRGB()));

        DrawOnScreen.render(e -> e.drawHollowRect(this.getX() - 2, this.getY() - 2, this.getWidthIn() + 3, this.getHeightIn() + 2, (new Color(170, 170, 170, 100)).getRGB()));
    }

    public int getX() {
        return draggableComponent.getX();
    }

    public int getY() {
        return draggableComponent.getY();
    }
}
