package net.ttzplayz.primordial.block;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.ttzplayz.primordial.Primordial;

public class ModBlocks {


    public static final Block TEST_BLOCK = registerBlock("test_block",
            new Block(AbstractBlock.Settings.create().strength(10f)));
    public static final Block TEST_BLOCK_TWO = registerBlock("test_block_two",
            new Block(AbstractBlock.Settings.create()));




    public static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(Primordial.MOD_ID, name), block);
    }

    public static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(Primordial.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerModBlocks() {
        Primordial.LOGGER.info("Registering Blocks for " + Primordial.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(ModBlocks.TEST_BLOCK);
            entries.addAfter(ModBlocks.TEST_BLOCK, ModBlocks.TEST_BLOCK_TWO);
        });
    }
}
