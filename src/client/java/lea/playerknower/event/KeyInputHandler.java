package lea.playerknower.event;

import lea.playerknower.PlayerKnower;
import lea.playerknower.PlayerKnowerCommand;
import lea.playerknower.networking.ModMessages;
import lea.playerknower.util.DescData;
import lea.playerknower.util.IEntityDataSaver;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.hit.HitResult;
import org.lwjgl.glfw.GLFW;

public class KeyInputHandler {

    public static KeyBinding displayKey;

    public static void registerKeyInputs() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (displayKey.wasPressed()) {
                // This happens when our custom key is pressed
                //client.player.sendMessage(Text.of("Hello I pressed a Key"));
                //ClientPlayNetworking.send(ModMessages.DISPLAY_ID, PacketByteBufs.create());
                if (!(client.crosshairTarget.getType() == HitResult.Type.ENTITY)) {
                    return;
                }
                else {
                    if (!(DescData.readShortDesc((IEntityDataSaver) client.crosshairTarget) == "" || DescData.readShortDesc((IEntityDataSaver) client.crosshairTarget) == null )) {
                        client.player.sendMessage(Text.of(DescData.readShortDesc((IEntityDataSaver) client.crosshairTarget)));
                    }
                }
            }
        });
    }

    public static void register() {
        displayKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.peopleknower.display",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_N,
                "category.key.peopleknower"
        ));

        registerKeyInputs();
    }
}
