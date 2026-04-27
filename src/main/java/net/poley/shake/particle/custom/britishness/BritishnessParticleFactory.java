package net.poley.shake.particle.custom.britishness;

import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;

// Define a particle factory class
public class BritishnessParticleFactory implements ParticleFactory<DefaultParticleType> {
    private final SpriteProvider spriteProvider;

    public BritishnessParticleFactory(SpriteProvider spriteProvider) {
        this.spriteProvider = spriteProvider;
    }

    @Override
    public Particle createParticle(DefaultParticleType type, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
        // Create a new instance of the custom particle
        BritishnessParticle particle = new BritishnessParticle(world, x, y, z, velocityX, velocityY, velocityZ, this.spriteProvider);
        return particle;
    }
}