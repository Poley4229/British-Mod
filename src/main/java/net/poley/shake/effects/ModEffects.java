package net.poley.shake.effects;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.poley.shake.ShakeMod;
import net.poley.shake.effects.custom.BritishnessEffect;

public class ModEffects {
    public static final StatusEffect BRITISHNESS = new BritishnessEffect();

    public static void registerEffects() {
        // Register the status effect with a unique identifier
        Registry.register(Registries.STATUS_EFFECT, new Identifier(ShakeMod.MOD_ID, "britishness"), BRITISHNESS);
    }
}