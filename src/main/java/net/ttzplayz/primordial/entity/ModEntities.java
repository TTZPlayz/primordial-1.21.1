package net.ttzplayz.primordial.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.ttzplayz.primordial.Primordial;
import net.ttzplayz.primordial.entity.custom.BlubEntity;

public class ModEntities {

    public static final EntityType<BlubEntity> BLUB = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(Primordial.MOD_ID, "blub"),
            EntityType.Builder.create(BlubEntity::new, SpawnGroup.MONSTER)
                    .dimensions(1f,1.5f)
                    .makeFireImmune()
                    .build());

    public static void registerModEntities() {
        Primordial.LOGGER.info("Registering Mod Entities for" + Primordial.MOD_ID);
    }
}
