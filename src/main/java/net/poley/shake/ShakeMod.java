package net.poley.shake;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.poley.shake.block.ModBlocks;
import net.poley.shake.effects.ModEffects;
import net.poley.shake.entity.ModEntities;
import net.poley.shake.entity.custom.PlaneEntity;
import net.poley.shake.item.ModItems;
import net.poley.shake.particle.ModParticles;
import net.minecraft.registry.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ShakeMod implements ModInitializer {
	public static final String MOD_ID = "shake-mod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");
		ModItems.registerModItems();
		ModEffects.registerEffects();
		ModParticles.registerParticles();
		ModBlocks.registerBlocks();

		FabricDefaultAttributeRegistry.register(ModEntities.PLANE, PlaneEntity.createPlaneAttributes());

		//ServerPlayConnectionEvents.JOIN.register(this::onPlayerJoin);
	}

	// Event handler method
	/*private void onPlayerJoin(MinecraftServer server, ServerPlayerEntity player) {
		String username = String.valueOf(player.getName());

		// Construct the path to the player's requests folder
		File playerRequestDir = new File("requests", username);

		if (playerRequestDir.exists() && playerRequestDir.isDirectory()) {
			// Get a list of all .txt files in the directory
			List<File> txtFiles = Arrays.stream(playerRequestDir.listFiles())
					.filter(file -> file.isFile() && file.getName().endsWith(".txt"))
					.collect(Collectors.toList());

			for (File txtFile : txtFiles) {
				try (BufferedReader reader = new BufferedReader(new FileReader(txtFile))) {
					String firstLine = reader.readLine();
					if ("CANCELED".equalsIgnoreCase(firstLine.trim())) {
						// Parse the file and refund the item
						refundItemToPlayer(reader, player);
						// Delete the file after processing
						if (txtFile.delete()) {
							player.sendMessage(Text.literal("Refund processed and request file deleted."), false);
						} else {
							player.sendMessage(Text.literal("Refund processed but failed to delete request file."), false);
						}
					}
				} catch (IOException e) {
					player.sendMessage(Text.literal("An error occurred while processing your request files."), false);
					e.printStackTrace();
				}
			}
		}
	}

	private void refundItemToPlayer(BufferedReader reader, ServerPlayerEntity player) throws IOException {
		String itemName = null;
		int amount = 1;
		NbtCompound nbtData = null;

		String line;
		while ((line = reader.readLine()) != null) {
			if (line.startsWith("Item: ")) {
				itemName = line.substring(6).trim();
			} else if (line.startsWith("Amount: ")) {
				amount = Integer.parseInt(line.substring(8).trim());
			} else if (line.startsWith("NBT Data: ")) {
				nbtData = NbtCompound.fromString(line.substring(10).trim());
			}
		}

		if (itemName != null) {
			// Find the item from the registry
			ItemStack itemStack = new ItemStack(Registry.ITEM.get(new Identifier(itemName)), amount);

			// Set NBT data if it exists
			if (nbtData != null) {
				itemStack.setNbt(nbtData);
			}

			// Add the item to the player's inventory
			boolean added = player.getInventory().insertStack(itemStack);
			if (added) {
				player.sendMessage(Text.literal("Item refunded: " + itemName + " x" + amount), false);
			} else {
				player.sendMessage(Text.literal("Failed to refund item: Inventory full"), false);
			}
		} else {
			player.sendMessage(Text.literal("Invalid item information in request file."), false);
		}
	}*/
}