package net.lemontree;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SomeTools implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("sometools");

	@Override
	public void onInitialize() {
		Commands.regClsCommand();

	}
}