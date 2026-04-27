package net.poley.shake.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.boss.dragon.EnderDragonFight;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.TntMinecartEntity;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EndCrystalEntity.class)
public abstract class EndCrystalEntityMixin {

    public EndCrystalEntityMixin() {
        super();
    }

    @Inject(method = "damage", at = @At("HEAD"), cancellable = true)
    private void onDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        EndCrystalEntity endCrystal = (EndCrystalEntity) (Object) this;
        // Example: Cancel damage if the source is a player
        /*if (source.getAttacker() instanceof PlayerEntity) {
            cir.setReturnValue(false); // Cancels the damage
        } else {
            // Allow normal damage for other sources
            endCrystal.getWorld().createExplosion(endCrystal, null, null, endCrystal.getX(), endCrystal.getY(), endCrystal.getZ(), 6.0F, false, World.ExplosionSourceType.BLOCK);
        }*/

        endCrystal.getWorld().createExplosion(endCrystal, null, null, endCrystal.getX(), endCrystal.getY(), endCrystal.getZ(), 1.0F, false, World.ExplosionSourceType.BLOCK);
        endCrystal.discard();
        cir.setReturnValue(true);
    }
}
