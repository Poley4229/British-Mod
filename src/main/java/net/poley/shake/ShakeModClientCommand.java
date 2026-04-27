package net.poley.shake;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.text.Text;

import java.util.HashSet;
import java.util.Set;

public class ShakeModClientCommand {
    private static final Set<Item> ALLOWED_ITEMS = new HashSet<>();

    static {
        // Add block forms and tools to allowed items
        ALLOWED_ITEMS.add(Items.IRON_BLOCK);
        ALLOWED_ITEMS.add(Items.GOLD_BLOCK);
        ALLOWED_ITEMS.add(Items.DIAMOND_BLOCK);
        ALLOWED_ITEMS.add(Items.NETHERITE_BLOCK);
        ALLOWED_ITEMS.add(Items.SHULKER_BOX);
        ALLOWED_ITEMS.add(Items.WHITE_SHULKER_BOX);
        ALLOWED_ITEMS.add(Items.ORANGE_SHULKER_BOX);
        ALLOWED_ITEMS.add(Items.MAGENTA_SHULKER_BOX);
        ALLOWED_ITEMS.add(Items.LIGHT_BLUE_SHULKER_BOX);
        ALLOWED_ITEMS.add(Items.YELLOW_SHULKER_BOX);
        ALLOWED_ITEMS.add(Items.LIME_SHULKER_BOX);
        ALLOWED_ITEMS.add(Items.PINK_SHULKER_BOX);
        ALLOWED_ITEMS.add(Items.GRAY_SHULKER_BOX);
        ALLOWED_ITEMS.add(Items.LIGHT_GRAY_SHULKER_BOX);
        ALLOWED_ITEMS.add(Items.CYAN_SHULKER_BOX);
        ALLOWED_ITEMS.add(Items.PURPLE_SHULKER_BOX);
        ALLOWED_ITEMS.add(Items.BLUE_SHULKER_BOX);
        ALLOWED_ITEMS.add(Items.BROWN_SHULKER_BOX);
        ALLOWED_ITEMS.add(Items.GREEN_SHULKER_BOX);
        ALLOWED_ITEMS.add(Items.RED_SHULKER_BOX);
        ALLOWED_ITEMS.add(Items.BLACK_SHULKER_BOX);

        // Add tools and armor
        ALLOWED_ITEMS.add(Items.DIAMOND_PICKAXE);
        ALLOWED_ITEMS.add(Items.DIAMOND_AXE);
        ALLOWED_ITEMS.add(Items.DIAMOND_SHOVEL);
        ALLOWED_ITEMS.add(Items.DIAMOND_HOE);
        ALLOWED_ITEMS.add(Items.DIAMOND_SWORD);
        ALLOWED_ITEMS.add(Items.DIAMOND_HELMET);
        ALLOWED_ITEMS.add(Items.DIAMOND_CHESTPLATE);
        ALLOWED_ITEMS.add(Items.DIAMOND_LEGGINGS);
        ALLOWED_ITEMS.add(Items.DIAMOND_BOOTS);

        ALLOWED_ITEMS.add(Items.NETHERITE_PICKAXE);
        ALLOWED_ITEMS.add(Items.NETHERITE_AXE);
        ALLOWED_ITEMS.add(Items.NETHERITE_SHOVEL);
        ALLOWED_ITEMS.add(Items.NETHERITE_HOE);
        ALLOWED_ITEMS.add(Items.NETHERITE_SWORD);
        ALLOWED_ITEMS.add(Items.NETHERITE_HELMET);
        ALLOWED_ITEMS.add(Items.NETHERITE_CHESTPLATE);
        ALLOWED_ITEMS.add(Items.NETHERITE_LEGGINGS);
        ALLOWED_ITEMS.add(Items.NETHERITE_BOOTS);
    }

    private static ItemStack savedItem;
    private static String savedIdea;

    public static void register() {
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            registerCommands(dispatcher);
        });
    }

    private static void registerCommands(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        dispatcher.register(ClientCommandManager.literal("shake")
                .then(ClientCommandManager.argument("duration", IntegerArgumentType.integer())
                        .then(ClientCommandManager.argument("intensity", FloatArgumentType.floatArg())
                                .then(ClientCommandManager.argument("positional", BoolArgumentType.bool())
                                        .executes(context -> {
                                            int duration = IntegerArgumentType.getInteger(context, "duration");
                                            float intensity = FloatArgumentType.getFloat(context, "intensity");
                                            boolean positional = BoolArgumentType.getBool(context, "positional");
                                            ShakeModClient.startScreenShake(duration, intensity, positional);
                                            context.getSource().sendFeedback(Text.literal("Screen shake started for duration: " + duration + " with intensity: " + intensity + " and positional: " + positional));
                                            return 1;
                                        })))));

        // Add the new command to save and remove the item
        dispatcher.register(ClientCommandManager.literal("saveitem")
                .then(ClientCommandManager.argument("idea", StringArgumentType.string())
                        .executes(context -> {
                            FabricClientCommandSource source = context.getSource();
                            MinecraftClient client = MinecraftClient.getInstance();

                            if (client.player != null) {
                                ItemStack itemStack = client.player.getMainHandStack();

                                if (!isAllowedItem(itemStack.getItem())) {
                                    client.player.sendMessage(Text.literal("You can only save specific items!"), true);
                                    return 0;
                                }

                                if (itemStack.isEmpty()) {
                                    assert MinecraftClient.getInstance().player != null;
                                    client.player.sendMessage(Text.literal("Slot is empty"),true);
                                    return 0;
                                }

                                String idea = StringArgumentType.getString(context, "idea");

                                // Create a packet to send the item and idea to the server
                                PacketByteBuf packetData = new PacketByteBuf(Unpooled.buffer());
                                packetData.writeItemStack(itemStack);
                                packetData.writeString(idea);

                                // Send the packet to the server
                                ClientPlayNetworking.send(NetworkConstants.SAVE_ITEM_IDEA_PACKET_ID, packetData);

                                // Remove the item from the player's inventory
                                client.player.getInventory().removeOne(itemStack);

                                client.player.sendMessage(Text.literal("Item and idea sent to server!"), true);
                            }

                            source.sendFeedback(Text.literal("Player not found!"));
                            return 0;
                        })));

    }

    private static boolean isAllowedItem(Item item) {
        return ALLOWED_ITEMS.contains(item);
    }
}
