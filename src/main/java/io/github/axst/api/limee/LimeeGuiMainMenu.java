package io.github.axst.api.limee;

import io.github.axst.impl.awt.DrawOnScreen;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Mouse;

public class LimeeGuiMainMenu extends GuiScreen {

    private final ResourceLocation BACKGROUND = new ResourceLocation("limee/background.png");

    @Override
    public void initGui() {
        buttonList.add(new LimeeGuiButton(1, this.width / 2 - 70, this.height / 2 - 10, 140, 18, "teest"));
        buttonList.add(new GuiButton(1, this.width / 2 - 70, this.height / 2 - 10, 140, 18, "teest"));
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        mc.getTextureManager().bindTexture(BACKGROUND);
        Gui.drawModalRectWithCustomSizedTexture(-22 + (Mouse.getX() / 90), ((Mouse.getY() * -1 / 90)), 0, 0, width + 20, height + 20, width + 21, height + 20);
        DrawOnScreen.render(e -> e.drawRoundOutline(100, 100, 100, 100, 8, -1));
    }

}
