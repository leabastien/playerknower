package lea.playerknower.networking;


import lea.playerknower.networking.packet.ExampleC2SPacket;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;

public class ModMessages {
    public static final Identifier EXAMPLE_ID = new Identifier("playerknower", "example");

    public static void registerC2SPackets() {
        ServerPlayNetworking.registerGlobalReceiver(EXAMPLE_ID, ExampleC2SPacket::receive);
    }

    public static void registerS2CPackets() {

    }
}
