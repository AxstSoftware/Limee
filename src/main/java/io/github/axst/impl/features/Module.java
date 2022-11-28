package io.github.axst.impl.features;

import io.github.axst.Limee;
import io.github.axst.api.settings.Settings;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;

@Getter
public class Module {

    public final ArrayList<Settings> settings = new ArrayList<>();
    public String name;
    public String description;
    public double version;
    @Setter
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

    public void toggle() {
        this.setEnabled(!this.getEnabled());
        if (enabled) onModuleEnable();
        else onModuleDisable();
    }
}
