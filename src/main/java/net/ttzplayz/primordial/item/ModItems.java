package net.ttzplayz.primordial.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.ttzplayz.primordial.Primordial;
import net.ttzplayz.primordial.entity.ModEntities;
import net.ttzplayz.primordial.entity.custom.BlubEntity;
import net.ttzplayz.primordial.item.custom.BlubbleItem;

public class ModItems {
    public static final Item BLUBBLE = registerItem("blubble",
            new BlubbleItem(new Item.Settings().maxCount(16)));

    //SPAWN EGGS
    public static final Item BLUB_SPAWN_EGG = registerItem("blub_spawn_egg",
            new SpawnEggItem(ModEntities.BLUB, 0x1d6d8d, 0x21b5ff, new Item.Settings()));


    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(Primordial.MOD_ID, name), item);
    }

    public static void registerModItems() {
        Primordial.LOGGER.info("Registering Mod Items for " + Primordial.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(BLUBBLE);
        });
    }
}
