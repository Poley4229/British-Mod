package net.poley.shake.particle.custom.effect;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import net.poley.shake.particle.ModParticles;

public class EffectManager {

    private int currrentTick = 0;
    World worldT;
    Vec3i posT;

    public void startCoolEffect(World world, Vec3i pos) {
        this.currrentTick = 0;
        this.worldT = world;
        this.posT = pos;
    }

    public void stopCoolEffect() {
        this.currrentTick = -1;
    }

    public void updateCoolEffect() {
        if (this.currrentTick != -1) {
            if (this.currrentTick == 0) {
                createRotateParticlesCoolEffect(this.worldT, this.posT);
            } else if (this.currrentTick == 20) {
                /*this.worldT.getPlayers().get(0).sendMessage(Text.literal("Spawning lightning..."), false);
                LightningEntity lightning = new LightningEntity(EntityType.LIGHTNING_BOLT, this.worldT);
                lightning.updatePosition(posT.getX(), posT.getY(), posT.getZ());
                lightning.setPos(posT.getX(), posT.getY(), posT.getZ());
                if (this.worldT.spawnEntity(lightning)) {
                    this.worldT.getPlayers().get(0).sendMessage(Text.literal("Lightning spawned successfully!"), false);
                } else {
                    this.worldT.getPlayers().get(0).sendMessage(Text.literal("Failed to spawn lightning."), false);
                }*/
                //EntityType.LIGHTNING_BOLT.create(worldT).setPos(this.posT.getX(), this.posT.getY(), this.posT.getZ());
            }
            currrentTick++;
        }
    }

    public void createRotateParticlesCoolEffect(World world, Vec3i pos) {
        world.addParticle(
                ModParticles.EFFECT_PARTICLE,
                pos.getX(),
                pos.getY(),
                pos.getZ(),
                0, 0, 0
        );
        world.addParticle(
                ModParticles.EFFECT_PARTICLE,
                pos.getX(),
                pos.getY(),
                pos.getZ(),
                0, 0, 0
        );
        world.addParticle(
                ModParticles.EFFECT_PARTICLE,
                pos.getX(),
                pos.getY(),
                pos.getZ(),
                0, 0, 0
        );
    }
}
