package net.poley.shake.particle.custom.britishness;

import net.minecraft.client.particle.SpriteBillboardParticle;
import net.minecraft.client.particle.ParticleTextureSheet;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.joml.Vector3f;

// Define a custom particle class
public class BritishnessParticle extends SpriteBillboardParticle {

    public BritishnessParticle(ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, SpriteProvider spriteProvider) {
        super(world, x, y, z, velocityX, velocityY, velocityZ);
        this.scale = 0.2F; // Particle size
        this.maxAge = 40;  // Particle lifetime in ticks
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.velocityZ = velocityZ;

        // Optionally, set color (RGB values)
        this.setColor(1.0F, 1.0F, 1.0F);
        this.setSprite(spriteProvider); // Use the sprite provider to set the sprite
    }

    @Override
    public void buildGeometry(VertexConsumer vertexConsumer, Camera camera, float tickDelta) {
        Vec3d cameraPos = camera.getPos();
        float x = (float) (MathHelper.lerp(tickDelta, this.prevPosX, this.x) - cameraPos.getX());
        float y = (float) (MathHelper.lerp(tickDelta, this.prevPosY, this.y) - cameraPos.getY());
        float z = (float) (MathHelper.lerp(tickDelta, this.prevPosZ, this.z) - cameraPos.getZ());

        // Calculate the rotation angle
        float rotation = angle;

        // Calculate the scaling factor
        float scale = this.scale * age   /*(1.0f + 0.5f * MathHelper.sin(age + tickDelta))*/;

        Vector3f[] vertices = new Vector3f[]{
                new Vector3f(-0.1F, 0.0F, -0.1F),
                new Vector3f(-0.1F, 0.0F, 0.1F),
                new Vector3f(0.1F, 0.0F, 0.1F),
                new Vector3f(0.1F, 0.0F, -0.1F)
        };

        for (Vector3f vertex : vertices) {
            //vertex.rotateX(rotation);
            vertex.mul(scale);
            vertex.add(x, y, z);
        }

        float minU = this.getMinU();
        float maxU = this.getMaxU();
        float minV = this.getMinV();
        float maxV = this.getMaxV();
        int light = this.getBrightness(tickDelta);

        vertexConsumer.vertex(vertices[0].x(), vertices[0].y(), vertices[0].z()).texture(maxU, maxV).color(this.red, this.green, this.blue, this.alpha).light(light).next();
        vertexConsumer.vertex(vertices[1].x(), vertices[1].y(), vertices[1].z()).texture(maxU, minV).color(this.red, this.green, this.blue, this.alpha).light(light).next();
        vertexConsumer.vertex(vertices[2].x(), vertices[2].y(), vertices[2].z()).texture(minU, minV).color(this.red, this.green, this.blue, this.alpha).light(light).next();
        vertexConsumer.vertex(vertices[3].x(), vertices[3].y(), vertices[3].z()).texture(minU, maxV).color(this.red, this.green, this.blue, this.alpha).light(light).next();

        vertexConsumer.vertex(vertices[3].x(), vertices[3].y(), vertices[3].z()).texture(minU, maxV).color(this.red, this.green, this.blue, this.alpha).light(light).next();
        vertexConsumer.vertex(vertices[2].x(), vertices[2].y(), vertices[2].z()).texture(minU, minV).color(this.red, this.green, this.blue, this.alpha).light(light).next();
        vertexConsumer.vertex(vertices[1].x(), vertices[1].y(), vertices[1].z()).texture(maxU, minV).color(this.red, this.green, this.blue, this.alpha).light(light).next();
        vertexConsumer.vertex(vertices[0].x(), vertices[0].y(), vertices[0].z()).texture(maxU, maxV).color(this.red, this.green, this.blue, this.alpha).light(light).next();
    }

    @Override
    public void tick() {
        super.tick();

        // Custom behavior each tick
        // For example, reduce speed or change direction
        this.velocityY -= 0.01; // Gravity effect

        // Increase the rotation angle
        this.angle += 0.1f;

        // Adjust the scale over time
        float scaleFactor = 0.1f * MathHelper.sin(this.age / 10.0f);
        this.scale += scaleFactor;
    }

    @Override
    public ParticleTextureSheet getType() {
        // Return the type of texture sheet
        return ParticleTextureSheet.PARTICLE_SHEET_OPAQUE;
    }

    @Override
    protected int getBrightness(float tint) {
        // Set particle brightness
        return MathHelper.clamp(super.getBrightness(tint), 0, 240);
    }
}