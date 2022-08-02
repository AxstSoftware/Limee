package io.github.axst.module.misc;

import io.github.axst.Limee;
import io.github.axst.module.Module;
import io.github.axst.limeemc.LimeeKeybindings;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class LimeeModule extends Module {
    public LimeeModule() {
        super("Limee", "Test Module");
        this.version = 2;
    }

    @Override
    public void onModuleEnable() {
        super.onModuleEnable();
        if (LimeeKeybindings.TEST.isPressed())
            Limee.getMc().thePlayer.addPotionEffect(new PotionEffect(Potion.nightVision.getId(), 5201, 1));
    }

}
