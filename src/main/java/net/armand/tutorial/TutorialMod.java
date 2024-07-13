package net.armand.tutorial;

import net.armand.tutorial.item.ModItemGroups;
import net.armand.tutorial.item.ModItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

public class TutorialMod implements ModInitializer {
	public static final String MOD_ID = "tutorialmod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final Identifier AUTH_PACKET_ID = new Identifier(MOD_ID, "auth_packet");

	// Set of allowed client identifiers
	private static final Set<String> ALLOWED_CLIENTS = new HashSet<>();

	static {
		// Add allowed clients' identifiers here
		ALLOWED_CLIENTS.add("minecraft-java");
		ALLOWED_CLIENTS.add("minecraft-xbox");
	}

	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();
		ModItems.registerModItems();

		// Register server-side packet handler
		ServerPlayNetworking.registerGlobalReceiver(AUTH_PACKET_ID, (server, player, handler, buf, responseSender) -> {
			String clientIdentifier = buf.readString(32767);
			server.execute(() -> handleClientAuth(player, clientIdentifier));
		});

		// Register the /status_client command
		CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
			dispatcher.register(CommandManager.literal("status_client")
					.requires(source -> source.hasPermissionLevel(2)) // Only allow operators
					.executes(context -> executeStatusClient(context.getSource()))
			);
		});

		LOGGER.info("{} has been initialized!", MOD_ID);
	}

	private void handleClientAuth(ServerPlayerEntity player, String clientIdentifier) {
		if (!ALLOWED_CLIENTS.contains(clientIdentifier)) {
			player.networkHandler.disconnect(Text.literal("Bet kokie 3-iosios šalies Minecraft klientai yra neleidžiami,\n" +
					"norėdami prisijungti prie lapiakalnio, prašome naudoti Minecraft Launcher."));
			// Optionally ban the player
			// player.getServer().getPlayerManager().getUserBanList().add(new BannedPlayerEntry(player.getGameProfile(), null, "TutorialMod", null, "Unauthorized client"));
		}
	}

	private int executeStatusClient(ServerCommandSource source) {
		ServerPlayerEntity player = source.getPlayer();
		if (player != null && player.interactionManager.isCreative()) {
			source.sendFeedback(Text.literal("The mod is working properly."), false);
			return 1;
		} else {
			source.sendError(Text.literal("You must be in creative mode to use this command."));
			return 0;
		}
	}
}
