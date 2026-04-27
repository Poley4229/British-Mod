package net.poley.shake.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.poley.shake.ShakeMod;
import net.poley.shake.block.custom.RotatableBlock;

public class ModBlocks {

    //public static final Block CUSTOM_ROTATED_BLOCK = new RotatableBlock(FabricBlockSettings.copyOf(Blocks.STONE));
    public static final Block CUSTOM_ROTATED_BLOCK = registerBlock("rotatable_block",
            new RotatableBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)));

    public static void registerBlocks() {
        //Registry.register(Registries.BLOCK, new Identifier(ShakeMod.MOD_ID, "rotated_block"), CUSTOM_ROTATED_BLOCK);
    }

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(ShakeMod.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(ShakeMod.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }


}
