package io.github.axst.api.limee;

import io.github.axst.impl.awt.DrawOnScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;

import java.awt.*;

public class LimeeGuiButton extends GuiButton {

    public LimeeGuiButton(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText) {
        super(buttonId, x, y, widthIn, heightIn, buttonText);
    }

    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        GlStateManager.disableBlend();
        FontRenderer fontrenderer = mc.fontRendererObj;
        this.hovered = (mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height);
        GL11.glEnable(GL13.GL_MULTISAMPLE);
        DrawOnScreen.render(e -> e.drawRoundOutline(this.xPosition, this.yPosition, this.xPosition + this.width, this.yPosition + this.height, 9, 2, this.hovered ? new Color(100, 0, 0, 170).getRGB() : new Color(80, 31, 31, 220).getRGB()));
        DrawOnScreen.render(e -> e.drawRoundRect(this.xPosition, this.yPosition, this.xPosition + this.width, this.yPosition + this.height, 9, this.hovered ? new Color(0, 100, 0, 100).getRGB() : new Color(30, 80, 30, 100).getRGB()));
        this.mouseDragged(mc, mouseX, mouseY);
        this.drawCenteredString(fontrenderer, this.displayString, this.xPosition + this.width / 2, this.yPosition + (this.height - 8) / 2, -1);
    }

}
