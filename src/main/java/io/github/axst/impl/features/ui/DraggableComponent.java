package io.github.axst.impl.features.ui;

import lombok.Getter;
import lombok.Setter;
import org.lwjgl.input.Mouse;

public class DraggableComponent {

    @Getter
    public final int widthIn;
    @Getter
    public final int heightIn;
    @Setter
    @Getter
    public int x;
    @Setter
    @Getter
    public int y;
    public int lastX;
    public int lastY;

    public boolean draggingModule;

    public DraggableComponent(int x, int y, int widthIn, int heightIn) {
        this.x = x;
        this.y = y;
        this.widthIn = widthIn;
        this.heightIn = heightIn;
    }

    public boolean isDraggingModule(int mouseX, int mouseY) {
        if (this.draggingModule) {
            this.x = mouseX + this.lastX;
            this.y = mouseY + this.lastY;
            if (!Mouse.isButtonDown(0)) this.draggingModule = false;
        }
        boolean mouseOverX = (mouseX >= this.getX() && mouseX <= this.getX() + this.getWidthIn());
        boolean mouseOverY = (mouseY >= this.getY() && mouseY <= this.getY() + this.getHeightIn());
        if (mouseOverX && mouseOverY) {
            if (Mouse.isButtonDown(0)) {
                if (!this.draggingModule) {
                    this.lastX = x - mouseX;
                    this.lastY = y - mouseY;
                    this.draggingModule = true;
                }
            }
        }
        return draggingModule;
    }
}
