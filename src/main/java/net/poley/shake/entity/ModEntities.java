package net.poley.shake.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.poley.shake.ShakeMod;
import net.poley.shake.entity.custom.PlaneEntity;

public class ModEntities {
    public static final EntityType<PlaneEntity> PLANE = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(ShakeMod.MOD_ID, "plane"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, PlaneEntity::new)
                    .dimensions(EntityDimensions.fixed(3f, 1f)).build());
}
