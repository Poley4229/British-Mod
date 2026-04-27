package net.poley.shake.particle;

import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registry;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.minecraft.particle.DefaultParticleType;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.poley.shake.ShakeMod;
import net.poley.shake.particle.custom.britishness.BritishnessParticleFactory;
import net.poley.shake.particle.custom.effect.EffectParticleFactory;

// Register particles in your mod's initialization class
public class ModParticles {
    public static final DefaultParticleType BRITISHNESS_PARTICLE = FabricParticleTypes.simple();
    public static final DefaultParticleType EFFECT_PARTICLE = FabricParticleTypes.simple();

    public static void registerParticles() {
        // Register the particle type with a unique identifier
        Registry.register(Registries.PARTICLE_TYPE, new Identifier(ShakeMod.MOD_ID, "britishness_particle"), BRITISHNESS_PARTICLE);
        Registry.register(Registries.PARTICLE_TYPE, new Identifier(ShakeMod.MOD_ID, "effect_particle"), EFFECT_PARTICLE);
    }

    public static void registerParticleFactories() {
        // Register particle factories with their respective texture sprite
        ParticleFactoryRegistry.getInstance().register(BRITISHNESS_PARTICLE, BritishnessParticleFactory::new);
        ParticleFactoryRegistry.getInstance().register(EFFECT_PARTICLE, EffectParticleFactory::new);
    }
}