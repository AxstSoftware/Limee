package io.github.axst.api.mixins.client;

import io.github.axst.api.limee.LimeeNetHandlerPlayClient;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.play.server.S48PacketResourcePackSend;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(NetHandlerPlayClient.class)
public class NetHandlerPlayClientMixins<T extends NetHandlerPlayClient> {

    T network;

    @Inject(method = "handleResourcePack", at = @At(value = "INVOKE", target = "java/io/File.isFile ()Z", shift = At.Shift.AFTER))
    public void handleResourcePack(S48PacketResourcePackSend packetIn, CallbackInfo ci) {
        LimeeNetHandlerPlayClient.validateResourcePackUrl(network, packetIn.getURL(), packetIn.getHash());
    }

}
