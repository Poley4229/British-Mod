package net.poley.shake;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.poley.shake.particle.custom.effect.EffectParticle;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

public class NetworkHandler {
    public static void registerServer() {
        ServerPlayNetworking.registerGlobalReceiver(NetworkConstants.SAVE_ITEM_IDEA_PACKET_ID, (server, player, handler, buf, responseSender) -> {
            ItemStack receivedItem = buf.readItemStack();
            String receivedIdea = buf.readString(32767);

            // Process the received item and idea on the server
            server.execute(() -> {
                // Example: Send a confirmation message to the player
                saveRequestToFile(player, receivedItem, receivedIdea);
                player.sendMessage(Text.literal("Received item and idea: " + receivedIdea), true);

            });
        });
    }

    private static void saveRequestToFile(ServerPlayerEntity player, ItemStack item, String idea) {
        String directoryPath = "requests/" + player.getName().getString();
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        String filename = directoryPath + "/" + UUID.randomUUID() + ".txt";
        File file = new File(filename);
        try (FileWriter writer = new FileWriter(file)) {
            writer.write("Player: " + player.getName().getString() + "\n");
            writer.write("Item: " + item.getName().getString() + "\n");
            writer.write("Amount: " + item.getCount() + "\n");
            NbtCompound nbt = item.getNbt();
            if (nbt != null) {
                writer.write("NBT Data: " + nbt.asString() + "\n");
            }
            writer.write("Idea: " + idea + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}