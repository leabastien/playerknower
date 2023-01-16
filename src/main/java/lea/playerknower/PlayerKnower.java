package lea.playerknower;


import net.fabricmc.api.ModInitializer;



import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.ServerCommandSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.minecraft.server.command.DebugPathCommand.register;


public class PlayerKnower implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("playerknower");
	@Override
	public void onInitialize() {
		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> PlayerKnowerCommand.register(dispatcher));
		LOGGER.info("Hello Fabric world!");
	}

}