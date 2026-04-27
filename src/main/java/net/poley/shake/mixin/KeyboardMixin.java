package net.poley.shake.mixin;

import net.minecraft.client.Keyboard;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.poley.shake.entity.custom.PlaneEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Keyboard.class)
public class KeyboardMixin {

    @Inject(method = "onKey", at = @At("HEAD"), cancellable = true)
    public void onKey(long window, int key, int scancode, int action, int modifiers, CallbackInfo ci) {
        MinecraftClient client = MinecraftClient.getInstance();
        ClientPlayerEntity player = client.player;
        KeyBinding inventoryKey = client.options.inventoryKey;

        // Check if the key pressed is the inventory key and if the player is riding an Ender Dragon
        if (player != null && player.getVehicle() instanceof PlaneEntity && key == inventoryKey.getDefaultKey().getCode()) {
            // Cancel the key press event if the player is riding the Ender Dragon
            ci.cancel();
        }
    }
}