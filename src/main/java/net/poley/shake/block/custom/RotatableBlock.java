package net.poley.shake.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class RotatableBlock extends Block {

    public RotatableBlock(Settings settings) {
        super(settings);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        // Start with an empty shape
        VoxelShape pyramid = VoxelShapes.empty();

        // Define layers of the pyramid
        VoxelShape baseLayer = VoxelShapes.cuboid(0.0, 0.0, 0.0, 1.0, 0.2, 1.0);   // Bottom layer
        VoxelShape middleLayer = VoxelShapes.cuboid(0.2, 0.2, 0.2, 0.8, 0.6, 0.8); // Middle layer
        VoxelShape topLayer = VoxelShapes.cuboid(0.4, 0.6, 0.4, 0.6, 1.0, 0.6);    // Top layer

        // Combine all layers to form a pyramid-like shape
        pyramid = VoxelShapes.union(pyramid, baseLayer, middleLayer, topLayer);

        return pyramid;
    }
}