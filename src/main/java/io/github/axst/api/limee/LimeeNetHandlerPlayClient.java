package io.github.axst.api.limee;

import io.github.axst.Limee;
import lombok.SneakyThrows;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.play.client.C19PacketResourcePackStatus;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

/**
 * Resource Exploit Fix
 *
 * @author Sk1er, sdxqw
 */
public class LimeeNetHandlerPlayClient {

    @SneakyThrows
    public static void validateResourcePackUrl(NetHandlerPlayClient client, String url, String hash) {
        URI uri = new URI(url);
        String scheme = uri.getScheme();
        boolean isLevelProtocol = "level".equals(scheme);
        if (!"http".equals(scheme) && !"https".equals(scheme) && !isLevelProtocol) {
            client.getNetworkManager().sendPacket(new C19PacketResourcePackStatus(hash, C19PacketResourcePackStatus.Action.FAILED_DOWNLOAD));
            throw new URISyntaxException(url, "Wrong protocol");
        } else {
            url = URLDecoder.decode(url.substring("level://".length()), StandardCharsets.UTF_8.toString());
            if (isLevelProtocol && (url.contains("..") || !url.endsWith("/resources.zip"))) {
                System.out.println("Malicious server tried to access " + url);
                EntityPlayerSP player = Limee.getMc().thePlayer;
                if (player != null) {
                    player.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + EnumChatFormatting.BOLD.toString() + "[WARNING] The current server has attempted to be malicious but we have stopped them."));
                }
                throw new URISyntaxException(url, "Invalid level-storage resource pack path");
            }
        }
    }
    
}
