package uz.kristina.mayasdragons;
import uz.kristina.mayasdragons.block.ModBlocks;
import uz.kristina.mayasdragons.entity.ModEntityTypes;
import uz.kristina.mayasdragons.item.ModItems;
import uz.kristina.mayasdragons.block.entity.ModBlockEntities;

import net.fabricmc.api.ModInitializer;

import net.minecraft.resources.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uz.kristina.mayasdragons.item.ModItems;

public class MayaSDragons implements ModInitializer {
	public static final String MOD_ID = "mayas_dragons";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		ModBlocks.registerModBlocks();
		ModBlockEntities.registerModBlockEntities();
		ModItems.registerModItems();
		ModEntityTypes.registerModEntityTypes();
		ModEntityTypes.registerAttributes();

		LOGGER.info("Maya's Dragons loaded!>>>");

	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
	
}
