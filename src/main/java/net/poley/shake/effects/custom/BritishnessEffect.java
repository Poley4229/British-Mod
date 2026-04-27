package net.poley.shake.effects.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.poley.shake.ShakeMod;
import net.poley.shake.particle.ModParticles;

public class BritishnessEffect extends StatusEffect {
    public BritishnessEffect() {
        // StatusEffectCategory can be BENEFICIAL, HARMFUL, or NEUTRAL
        super(StatusEffectCategory.BENEFICIAL, 0x4B0082); // Purple color for the effect
    }

    // Override this method if you want to add custom behavior when the effect is active
    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        //This is called every tick the effect is active
        if (entity.getWorld() instanceof ServerWorld) {
            ServerWorld world = (ServerWorld) entity.getWorld();

            // Spawn particles around the entity
            world.spawnParticles(ModParticles.BRITISHNESS_PARTICLE,
                    entity.getX(), entity.getY() + 1.0, entity.getZ(),
                    1, // Number of particles
                    0.5, 0.5, 0.5, // Spread in each axis
                    0.1); // Particle speed
        }
    }

    // Determine whether the effect should continue or not
    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        // Apply every tick (1 tick = 50ms in real-time)
        return true;
    }
}