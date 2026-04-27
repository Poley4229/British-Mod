package net.poley.shake.particle.custom.effect;

import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import net.poley.shake.particle.ModParticles;

// Define a particle factory class
public class EffectParticleFactory implements ParticleFactory<DefaultParticleType> {
    private final SpriteProvider spriteProvider;

    public EffectParticleFactory(SpriteProvider spriteProvider) {
        this.spriteProvider = spriteProvider;
    }

    @Override
    public Particle createParticle(DefaultParticleType type, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
        // Create a new instance of the custom particle
        EffectParticle particle = new EffectParticle(world, x, y, z, velocityX, velocityY, velocityZ, this.spriteProvider);
        return particle;
    }
}