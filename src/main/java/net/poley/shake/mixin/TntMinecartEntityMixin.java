package net.poley.shake.mixin;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.entity.vehicle.MinecartEntity;
import net.minecraft.entity.vehicle.TntMinecartEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TntMinecartEntity.class)
public abstract class TntMinecartEntityMixin {

    @Inject(method = "explode", at = @At("HEAD"), cancellable = true)
    private void onExplode(CallbackInfo ci) {
        TntMinecartEntity minecart = (TntMinecartEntity) (Object) this;
        minecart.getWorld().createExplosion(minecart, null, null, minecart.getX(), minecart.getY(), minecart.getZ(), 2f, false, World.ExplosionSourceType.TNT);
        minecart.discard();
        ci.cancel();
    }
}

