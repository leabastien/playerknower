package lea.playerknower.networking;


import lea.playerknower.networking.packet.DisplayC2SPacket;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;

public class ModMessages {
    public static final Identifier DISPLAY_ID = new Identifier("playerknower", "display");

    public static void registerC2SPackets() {
        ServerPlayNetworking.registerGlobalReceiver(DISPLAY_ID, DisplayC2SPacket::receive);
    }

    public static void registerS2CPackets() {

    }
}
