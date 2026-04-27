package net.poley.shake.entity.custom;

import net.minecraft.client.render.Camera;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

import java.util.logging.Logger;

public class PlaneEntity extends AnimalEntity {

    private final AnimationState wheelsUpState = new AnimationState();
    private final AnimationState wheelsDownState = new AnimationState();
    private boolean wheelsUp = true;
    public float throttle = 0F;

    @Override
    protected void updateLimbs(float posDelta) {
        float f;
        if (this.getPose() == EntityPose.STANDING) {
            f = Math.min(posDelta * 6.0F, 1.0F);
        } else {
            f = 0.0F;
        }

        this.limbAnimator.updateLimbs(f, 0.2F);
    }

    private void updateAnimations() {
        wheelsUpState.update(20, 1);
    }

    @Override
    public int getMaxLookYawChange() {
        return 359;
    }

    @Override
    public void tick() {
        super.tick();
        if (this.getWorld().isClient()) {
            this.updateAnimations();
        }
        if (this.getControllingPassenger() != null) {
            this.setYaw(this.getControllingPassenger().getHeadYaw());
        }
        this.moveForward(throttle * 0.01);
    }

    public PlaneEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
    }

    public static DefaultAttributeContainer.Builder createPlaneAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 15)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2f);
    }

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return null;
    }

    private void moveForward(double m) {
        Vec3d forward = this.getRotationVec(1.0F).multiply(m);
        this.setVelocity(forward.x, this.getVelocity().y, forward.z);
    }

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        //player.hasVehicle();
        if (player.isSneaking()) {
            this.wheelsUp = !this.wheelsUp;
        } else {
            player.startRiding(this);
        }
        return ActionResult.SUCCESS;
    }

    public boolean isWheelsUp() {
        return wheelsUp;
    }

    public void setWheelsUp(boolean wheelsUp) {
        this.wheelsUp = wheelsUp;
    }

    @Override
    protected Vector3f getPassengerAttachmentPos(Entity passenger, EntityDimensions dimensions, float scaleFactor) {
        return new Vector3f(0.0F, 0.5F, 4F);
    }
}