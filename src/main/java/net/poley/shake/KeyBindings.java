package net.poley.shake;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3i;
import net.poley.shake.entity.custom.PlaneEntity;
import net.poley.shake.particle.custom.effect.EffectManager;
import org.lwjgl.glfw.GLFW;

public class KeyBindings {
    public static EffectManager effectManager = new EffectManager();
    public static boolean playing = false;

    public static KeyBinding throttleUp;
    public static KeyBinding debug;
    public static KeyBinding throttleDown;
    //public static KeyBinding speedUp;
    //public static KeyBinding torqueBack;

    public static void register() {

        throttleUp = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.Shake.throttleUp", // The translation key of the keybinding's name
                InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard
                GLFW.GLFW_KEY_E, // The keycode of the key
                "category.Shake.flying" // The translation key of the keybinding's category
        ));

        debug = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.Shake.up", // The translation key of the keybinding's name
                InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard
                GLFW.GLFW_KEY_SPACE, // The keycode of the key
                "category.Shake.flying" // The translation key of the keybinding's category
        ));

        throttleDown = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.Shake.throttleDown", // The translation key of the keybinding's name
                InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard
                GLFW.GLFW_KEY_Q, // The keycode of the key
                "category.Shake.flying" // The translation key of the keybinding's category
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player != null && client.world != null) {
                onTick(client);
            }
        });
    }

    private static void onTick(MinecraftClient client) {
        if (debug.wasPressed()) {
            effectManager.startCoolEffect(client.player.getWorld(), new Vec3i(
                    (int)client.player.getX(),
                    (int)client.player.getY(),
                    (int)client.player.getZ()
            ));
            playing = true;
        }
        if (playing == true) {
            effectManager.updateCoolEffect();
        }
        if (client.player.getVehicle() instanceof PlaneEntity) {
            if (throttleUp.wasPressed()) {
                if (((PlaneEntity) client.player.getVehicle()).throttle < 240) {
                    ((PlaneEntity) client.player.getVehicle()).throttle += 1;
                    client.player.getWorld().getPlayers().get(0).sendMessage(Text.of("throttle up done"));
                }
                client.player.getWorld().getPlayers().get(0).sendMessage(Text.of("throttle up"));
            }
            if (throttleDown.wasPressed()) {
                if (((PlaneEntity) client.player.getVehicle()).throttle > 0) {
                    ((PlaneEntity) client.player.getVehicle()).throttle -= 1;
                }
            }
        }
    }
}