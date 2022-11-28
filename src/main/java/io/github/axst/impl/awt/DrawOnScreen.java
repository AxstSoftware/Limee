package io.github.axst.impl.awt;

import io.github.axst.api.utils.MathUtil;
import lombok.experimental.UtilityClass;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;

import static net.minecraft.client.gui.Gui.drawRect;

@UtilityClass
public class DrawOnScreen {

    public void setColor(int color) {
        float a = (color >> 24 & 0xFF) / 255.0F;
        float r = (color >> 16 & 0xFF) / 255.0F;
        float g = (color >> 8 & 0xFF) / 255.0F;
        float b = (color & 0xFF) / 255.0F;
        GL11.glColor4f(r, g, b, a);
    }

    public void setGlColor(final int color) {
        final float red = (color >> 16 & 0xFF) / 255.0f;
        final float green = (color >> 8 & 0xFF) / 255.0f;
        final float blue = (color & 0xFF) / 255.0f;
        GlStateManager.color(red, green, blue);
    }

    public void drawRoundRect(double x, double y, double x1, double y1, double radius, int color) {
        GL11.glPushAttrib(0);
        GL11.glScaled(0.5D, 0.5D, 0.5D);
        x *= 2.0D;
        y *= 2.0D;
        x1 *= 2.0D;
        y1 *= 2.0D;
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        setColor(color);
        GL11.glEnable(2848);
        GL11.glBegin(9);
        int i;
        for (i = 0; i <= 90; i += 3) {
            GL11.glVertex2d(x + radius + Math.sin(i * Math.PI / 180.0D) * radius * -1.0D, y + radius + Math.cos(i * Math.PI / 180.0D) * radius * -1.0D);
        }
        for (i = 90; i <= 180; i += 3) {
            GL11.glVertex2d(x + radius + Math.sin(i * Math.PI / 180.0D) * radius * -1.0D, y1 - radius + Math.cos(i * Math.PI / 180.0D) * radius * -1.0D);
        }
        for (i = 0; i <= 90; i += 3) {
            GL11.glVertex2d(x1 - radius + Math.sin(i * Math.PI / 180.0D) * radius, y1 - radius + Math.cos(i * Math.PI / 180.0D) * radius);
        }
        for (i = 90; i <= 180; i += 3) {
            GL11.glVertex2d(x1 - radius + Math.sin(i * Math.PI / 180.0D) * radius, y + radius + Math.cos(i * Math.PI / 180.0D) * radius);
        }

        GL11.glEnd();

        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
        GL11.glDisable(3042);
        GL11.glEnable(3553);

        GL11.glScaled(2.0D, 2.0D, 2.0D);

        GL11.glPopAttrib();
    }

    public void drawRoundOutline(final int x, final int y, final int x2, final int y2, final float radius, final float width, final int color) {
        setGlColor(color);
        drawRoundOutline(x, y, x2, y2, radius, width);
    }

    public void drawRoundOutline(final float x, final float y, final float x2, final float y2, final float radius, final float width) {
        final int i = 18;
        final int j = 90 / i;
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableCull();
        GlStateManager.enableColorMaterial();
        GlStateManager.blendFunc(770, 771);
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GL11.glEnable(2848);
        if (width != 1.0f) {
            GL11.glLineWidth(width);
        }
        GL11.glBegin(3);
        GL11.glVertex2f(x + radius, y);
        GL11.glVertex2f(x2 - radius, y);
        GL11.glEnd();
        GL11.glBegin(3);
        GL11.glVertex2f(x2, y + radius);
        GL11.glVertex2f(x2, y2 - radius);
        GL11.glEnd();
        GL11.glBegin(3);
        GL11.glVertex2f(x2 - radius, y2 - 0.1f);
        GL11.glVertex2f(x + radius, y2 - 0.1f);
        GL11.glEnd();
        GL11.glBegin(3);
        GL11.glVertex2f(x + 0.1f, y2 - radius);
        GL11.glVertex2f(x + 0.1f, y + radius);
        GL11.glEnd();
        float f1 = x2 - radius;
        float f2 = y + radius;
        GL11.glBegin(3);
        for (int k = 0; k <= i; ++k) {
            final int m = 90 - k * j;
            GL11.glVertex2f((float) (f1 + radius * MathUtil.getRightAngle(m)), (float) (f2 - radius * MathUtil.getAngle(m)));
        }
        GL11.glEnd();
        f1 = x2 - radius;
        f2 = y2 - radius;
        GL11.glBegin(3);
        for (int k = 0; k <= i; ++k) {
            final int m = k * j + 270;
            GL11.glVertex2f((float) (f1 + radius * MathUtil.getRightAngle(m)), (float) (f2 - radius * MathUtil.getAngle(m)));
        }
        GL11.glEnd();
        GL11.glBegin(3);
        f1 = x + radius;
        f2 = y2 - radius;
        for (int k = 0; k <= i; ++k) {
            final int m = k * j + 90;
            GL11.glVertex2f((float) (f1 + radius * MathUtil.getRightAngle(m)), (float) (f2 + radius * MathUtil.getAngle(m)));
        }
        GL11.glEnd();
        GL11.glBegin(3);
        f1 = x + radius;
        f2 = y + radius;
        for (int k = 0; k <= i; ++k) {
            final int m = 270 - k * j;
            GL11.glVertex2f((float) (f1 + radius * MathUtil.getRightAngle(m)), (float) (f2 + radius * MathUtil.getAngle(m)));
        }
        GL11.glEnd();
        GL11.glDisable(2848);
        if (width != 1.0f) {
            GL11.glLineWidth(1.0f);
        }
        GlStateManager.enableCull();
        GlStateManager.disableBlend();
        GlStateManager.disableColorMaterial();
        GlStateManager.enableTexture2D();
    }

    public void drawHorizontalLine(int startX, int endX, int y, int color) {
        if (endX < startX) {
            int i = startX;
            startX = endX;
            endX = i;
        }

        drawRect(startX, y, endX + 1, y + 1, color);
    }

    public void drawVerticalLine(int x, int startY, int endY, int color) {
        if (endY < startY) {
            int i = startY;
            startY = endY;
            endY = i;
        }

        drawRect(x, startY + 1, x + 1, endY, color);
    }

    public void drawHollowRect(int x, int y, int w, int h, int color) {
        drawHorizontalLine(x, x + w, y, color);
        drawHorizontalLine(x, x + w, y + h, color);
        drawVerticalLine(x, y + h, y, color);
        drawVerticalLine(x + w, y + h, y, color);
    }

    public void drawRectLine(double d, double d2, double d3, double d4, double d5, int n) {
        int n2;
        float f = (float) (n >> 24 & 0xFF) / (float) 255;
        float f2 = (float) (n >> 16 & 0xFF) / (float) 255;
        float f3 = (float) (n >> 8 & 0xFF) / (float) 255;
        float f4 = (float) (n & 0xFF) / (float) 255;
        GL11.glPushAttrib(0);
        GL11.glScaled(1.476190447807312 * 0.33870968393182915, 0.46794872796120124 * 1.068493127822876, 1.0533332824707031 * 0.47468356722498867);
        d *= 2;
        d2 *= 2;
        d3 *= 2;
        d4 *= 2;
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glColor4f(f2, f3, f4, f);
        GL11.glEnable(2848);
        GL11.glBegin(9);
        for (n2 = 0; n2 <= 90; n2 += 3) {
            GL11.glVertex2d(d + d5 + Math.sin((double) n2 * (6.5973445528769465 * 0.4761904776096344) / (double) 180) * (d5 * (double) -1), d2 + d5 + Math.cos((double) n2 * (42.5 * 0.07391982714328925) / (double) 180) * (d5 * (double) -1));
        }
        for (n2 = 90; n2 <= 180; n2 += 3) {
            GL11.glVertex2d(d + d5 + Math.sin((double) n2 * (0.5711986642890533 * 5.5) / (double) 180) * (d5 * (double) -1), d4 - d5 + Math.cos((double) n2 * (0.21052631735801697 * 14.922564993369743) / (double) 180) * (d5 * (double) -1));
        }
        for (n2 = 0; n2 <= 90; n2 += 3) {
            GL11.glVertex2d(d3 - d5 + Math.sin((double) n2 * (4.466951941998311 * 0.7032967209815979) / (double) 180) * d5, d4 - d5 + Math.cos((double) n2 * (28.33333396911621 * 0.11087973822685955) / (double) 180) * d5);
        }
        for (n2 = 90; n2 <= 180; n2 += 3) {
            GL11.glVertex2d(d3 - d5 + Math.sin((double) n2 * ((double) 0.6f * 5.2359875479235365) / (double) 180) * d5, d2 + d5 + Math.cos((double) n2 * (2.8529412746429443 * 1.1011767685204017) / (double) 180) * d5);
        }
        GL11.glEnd();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
        GL11.glDisable(3042);
        GL11.glEnable(3553);
        GL11.glScaled(2, 2, 2);
        GL11.glPopAttrib();
    }
}
