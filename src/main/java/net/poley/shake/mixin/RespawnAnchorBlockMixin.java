package net.poley.shake.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.RespawnAnchorBlock;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.TippedArrowItem;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Position;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.explosion.ExplosionBehavior;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(RespawnAnchorBlock.class)
public class RespawnAnchorBlockMixin extends Block {
    public RespawnAnchorBlockMixin(Settings settings) {
        super(settings);
    }

    @Inject(method = "explode", at = @At("HEAD"), cancellable = true)
    private void onExplode(BlockState state, World world, BlockPos explodedPos, CallbackInfo ci) {
        RespawnAnchorBlock respawnAnchor = (RespawnAnchorBlock) (Object) this;
        world.removeBlock(explodedPos, false);
        Vec3d vec3d = explodedPos.toCenterPos();
        world.createExplosion(null, world.getDamageSources().badRespawnPoint(vec3d), null, vec3d, 2F, false, World.ExplosionSourceType.BLOCK);
        ci.cancel();
    }
}
