package io.github.axst.impl.features;

import io.github.axst.Limee;
import io.github.axst.impl.features.misc.FPS;
import io.github.axst.impl.features.misc.LimeeModule;
import io.github.axst.impl.features.ui.ModuleRenderer;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ModuleManager {

    @Getter
    private final List<Module> modules = new ArrayList<>();

    public ModuleManager() {
        addModule(
                new LimeeModule(),
                new FPS(),
                new LimeeModule()
        );
    }

    /**
     * Add module
     *
     * @param module Module to add.
     */
    public void addModule(Module... module) {
        modules.addAll(Arrays.asList(module));
        Limee.getInstance().getBus().subscribe(module);
    }

    public void renderModules() {
        modules.forEach(mod -> {
            if (mod.getEnabled() && mod instanceof ModuleRenderer) ((ModuleRenderer) mod).drawModule();
        });
    }

}
