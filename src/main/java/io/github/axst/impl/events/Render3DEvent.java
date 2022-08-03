package io.github.axst.impl.events;

public class Render3DEvent extends Event {

    public final float partialTicks;

    public Render3DEvent(float partialTicks) {
        this.partialTicks = partialTicks;
    }
}
