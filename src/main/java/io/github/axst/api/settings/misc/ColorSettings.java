package io.github.axst.api.settings.misc;

import io.github.axst.api.settings.Settings;
import lombok.Getter;

import java.awt.*;

public class ColorSettings extends Settings {

    @Getter
    public int color;

    public ColorSettings(String name, double version, int r, int g, int b, int a) {
        super(name, version);
        color = new Color(r, g, b, a).getRGB();
    }
}
