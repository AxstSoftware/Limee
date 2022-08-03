package io.github.axst.impl.features;

import io.github.axst.Limee;
import io.github.axst.api.settings.Settings;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;

public class Module {

    @Getter
    public final ArrayList<Settings> settings = new ArrayList<>();
    @Getter
    public String name;
    @Getter
    public String description;
    @Getter
    public double version;
    @Getter
    public Boolean enabled;

    public Module(String name, String description) {
        this.name = name;
        this.description = description;
        this.version = 0;
        this.enabled = true;
    }

    public void addSettings(Settings... addSettings) {
        this.getSettings().addAll(Arrays.asList(addSettings));
    }

    public void onModuleEnable() {
        Limee.getInstance().getBus().subscribe(this);
    }

    public void onModuleDisable() {
        Limee.getInstance().getBus().unsubscribe(this);
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        if (enabled) onModuleEnable();
        else onModuleDisable();
    }
}
