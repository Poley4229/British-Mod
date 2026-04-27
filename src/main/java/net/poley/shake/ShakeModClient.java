package net.poley.shake;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.Vec3d;
import net.poley.shake.entity.ModEntities;
import net.poley.shake.entity.client.*;
import net.poley.shake.particle.ModParticles;

public class ShakeModClient implements ClientModInitializer {
    private static final MinecraftClient CLIENT = MinecraftClient.getInstance();
    private static int shakeDuration = 0;
    private static float shakeIntensity = 0.0f;
    private static boolean positionalShake = false;
    private static Vec3d originalPos;

    @Override
    public void onInitializeClient() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (shakeDuration > 0) {
                shakeScreen(shakeIntensity);
                shakeDuration--;
                if (shakeDuration == 0 && positionalShake) {
                    resetPosition();
                }
            }
        });

        ShakeModClientCommand.register();

        ModParticles.registerParticleFactories();

        EntityRendererRegistry.register(ModEntities.PLANE, PlaneRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.PLANE, PlaneModel::getTexturedModelData);

        KeyBindings.register();
    }

    public static void startScreenShake(int duration, float intensity, boolean positional) {
        shakeDuration = duration;
        shakeIntensity = intensity;
        positionalShake = positional;
        if (positionalShake && CLIENT.player != null) {
            originalPos = CLIENT.player.getPos();
        }
    }

    private static void shakeScreen(float intensity) {
        if (CLIENT.player != null) {
            if (positionalShake) {
                CLIENT.player.setPos(
                        originalPos.x + (Math.random() - 0.5) * intensity,
                        originalPos.y + (Math.random() - 0.5) * intensity,
                        originalPos.z + (Math.random() - 0.5) * intensity
                );
            } else {
                CLIENT.player.setYaw(CLIENT.player.getYaw() + (float) (Math.random() - 0.5) * intensity);
                CLIENT.player.setPitch(CLIENT.player.getPitch() + (float) (Math.random() - 0.5) * intensity);
            }
        }
    }

    private static void resetPosition() {
        if (CLIENT.player != null) {
            CLIENT.player.setPos(originalPos.x, originalPos.y, originalPos.z);
        }
    }
}