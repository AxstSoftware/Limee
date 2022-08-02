package io.github.axst.impl.features;

import io.github.axst.Limee;
import lombok.Getter;

public class Module {

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
