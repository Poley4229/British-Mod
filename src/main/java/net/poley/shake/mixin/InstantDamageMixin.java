package net.poley.shake.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class InstantDamageMixin {

    // Targeting the method where instant effects are applied
    @Inject(method = "applyDamage", at = @At("HEAD"), cancellable = true)
    private void onApplyInstantDamage(StatusEffectInstance effectInstance, CallbackInfo ci) {
        // Check if the effect is Instant Damage
        if (effectInstance.getEffectType() == StatusEffects.INSTANT_DAMAGE) {
            // Example modification: reduce the damage by half
            LivingEntity entity = (LivingEntity) (Object) this;
            float damageMultiplier = 0.5f; // Reduce damage by 50%
            float originalDamage = effectInstance.getAmplifier() + 1; // Calculate original damage
            float modifiedDamage = originalDamage * damageMultiplier;

            // Apply the modified damage
            entity.damage(entity.getDamageSources().magic(), modifiedDamage);

            // Cancel further damage application if needed
            ci.cancel();
        }
    }
}