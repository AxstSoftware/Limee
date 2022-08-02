package io.github.axst.launch;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.Mixins;
import org.spongepowered.asm.mixin.MixinEnvironment.Side;

import net.minecraft.launchwrapper.ITweaker;
import net.minecraft.launchwrapper.LaunchClassLoader;

public class LimeeTweaker implements ITweaker {

    private static final List<String> args = new ArrayList<>();

    @Override
    public void acceptOptions(List<String> args, File gameDir, File assetsDir, String profile) {
        LimeeTweaker.args.addAll(args);
        if (gameDir != null) {
            LimeeTweaker.args.add("--gameDir");
            LimeeTweaker.args.add(gameDir.getAbsolutePath());
        }
        if (assetsDir != null) {
            LimeeTweaker.args.add("--assetsDir");
            LimeeTweaker.args.add(assetsDir.getAbsolutePath());
        }
        if (profile != null) {
            LimeeTweaker.args.add("--version");
            LimeeTweaker.args.add(profile);
        }
    }

    @Override
    public void injectIntoClassLoader(LaunchClassLoader classLoader) {
        MixinBootstrap.init();
        Mixins.addConfiguration("mixins.limee.json");

        MixinEnvironment environment = MixinEnvironment.getDefaultEnvironment();

        if (environment.getObfuscationContext() == null) {
            environment.setObfuscationContext("notch");
        }

        environment.setSide(Side.CLIENT);
    }

    @Override
    public String getLaunchTarget() {
        return MixinBootstrap.getPlatform().getLaunchTarget();
    }

    @Override
    public String[] getLaunchArguments() {
        return LimeeTweaker.args.toArray(new String[0]);
    }

}
