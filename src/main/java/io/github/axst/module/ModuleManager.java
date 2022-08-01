package io.github.axst.module;

import io.github.axst.module.misc.LimeeModule;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class ModuleManager {

    @Getter
    private final List<Module> modules = new ArrayList<>();

    public ModuleManager() {
        addModule(new LimeeModule());
    }

    /**
     * Add module
     * @param module Module to add.
     */
    public void addModule(Module module) {
        modules.add(module);
        if(module.getVersion() == 1) {
            module.setEnabled(false);
        }
    }
}
