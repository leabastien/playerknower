package lea.playerknower;

import lea.playerknower.event.KeyInputHandler;
import lea.playerknower.networking.ModMessages;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;


public class PlayerKnowerClient implements ClientModInitializer {
	private static KeyBinding keyBinding;
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.

		KeyInputHandler.register();
		ModMessages.registerS2CPackets();
	}
}