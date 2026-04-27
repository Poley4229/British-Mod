package net.poley.shake.entity.client;

import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import net.poley.shake.ShakeMod;

public class ModModelLayers {
    public static final EntityModelLayer PLANE =
            new EntityModelLayer(new Identifier(ShakeMod.MOD_ID, "plane"), "main");
}
