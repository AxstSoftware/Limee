package io.github.axst.api.settings.misc;

import io.github.axst.api.settings.Settings;

public class ModeSettings extends Settings {

    public String modes;

    public ModeSettings(String name, double version, int current, String... modes) {
        super(name, version);
        this.modes = modes[current];
    }
}
