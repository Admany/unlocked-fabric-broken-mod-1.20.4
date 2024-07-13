package net.armand.tutorial;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import io.netty.buffer.Unpooled;

public class TutorialModClient implements ClientModInitializer {
	private static final Logger LOGGER = LogManager.getLogger();

	@Override
	public void onInitializeClient() {
		// Send authentication packet to the server when the client connects
		ClientPlayNetworking.registerGlobalReceiver(TutorialMod.AUTH_PACKET_ID, (client, handler, buf, responseSender) -> {
			// Here 'client' refers to the MinecraftClient parameter of the lambda
			// Receive any data from the server if needed
		});

		MinecraftClient minecraftClient = MinecraftClient.getInstance();
		minecraftClient.execute(() -> {
			PacketByteBuf packet = new PacketByteBuf(Unpooled.buffer());
			packet.writeString("minecraft-java"); // or "minecraft-xbox" for Xbox clients
			ClientPlayNetworking.send(TutorialMod.AUTH_PACKET_ID, packet);
		});

		LOGGER.info("LapiakalnioModClient has been initialized!");
	}
}
