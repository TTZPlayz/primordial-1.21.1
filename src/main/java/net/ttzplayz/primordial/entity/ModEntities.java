package net.ttzplayz.primordial.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.ttzplayz.primordial.Primordial;
import net.ttzplayz.primordial.entity.custom.BlubEntity;
import net.ttzplayz.primordial.entity.custom.BlubbleProjectileEntity;

public class ModEntities {
// ELEMENTALS
    public static final EntityType<BlubEntity> BLUB = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(Primordial.MOD_ID, "blub"),
            EntityType.Builder.create(BlubEntity::new, SpawnGroup.MONSTER)
                    .dimensions(1f,1.5f)
                    .makeFireImmune()
                    .build());
// PROJECTILES
    public static final EntityType<BlubbleProjectileEntity> BLUBBLE = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(Primordial.MOD_ID, "blubble"),
            EntityType.Builder.<BlubbleProjectileEntity>create(BlubbleProjectileEntity::new, SpawnGroup.MISC)
                    .dimensions(0.5f, 0.5f).build());

    public static void registerModEntities() {
        Primordial.LOGGER.info("Registering Mod Entities for" + Primordial.MOD_ID);
    }
}
