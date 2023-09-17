package net.lemontree;

import net.fabricmc.api.ModInitializer;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SomeTools implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("sometools");

	@Override
	public void onInitialize() {
		Commands.regClsCommand();

	}

	public static void SomeToolsMessage(ServerPlayerEntity player, Text text) {
		player.sendMessage(
				Text.literal("[")
						.append(Text.literal("SomeTools").formatted(Formatting.BLUE))
						.append("] ").formatted(Formatting.WHITE)
						.append(text)
		);
	}
}