package io.github.axst.module;

import io.github.axst.event.EventManager;
import lombok.Getter;

public class Module {
    @Getter
    public String name;
    @Getter
    public String description;
    @Getter
    public int version;
    @Getter
    public Boolean enabled;

    public Module(String name, String description) {
        this.name = name;
        this.description = description;
        this.version = 0;
        this.enabled = true;
    }

    public void onModuleEnable() {
        EventManager.register(this);
    }

    public void onModuleDisable() {
        EventManager.unregister(this);
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        if(enabled) onModuleEnable();
        else onModuleDisable();
    }

    public void toggleModule() {
        setEnabled(!getEnabled());
    }
}
