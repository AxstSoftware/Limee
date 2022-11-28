package io.github.axst.api.settings.misc;

import io.github.axst.api.settings.Settings;
import lombok.Getter;

@Getter
public class BooleanSettings extends Settings {

    public boolean enabled;

    public BooleanSettings(String name, double version, boolean enabled) {
        super(name, version);
        this.enabled = enabled;
    }
}
