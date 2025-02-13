package net.ttzplayz.primordial.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.ttzplayz.primordial.Primordial;

public class ModItemGroups {
    public static final ItemGroup PRIMORDIAL = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(Primordial.MOD_ID, "primordial"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.BLUBBLE))
            .displayName(Text.translatable("itemgroup.primordial.primordial"))
            .entries((displayContext, entries) -> {
                entries.add(ModItems.BLUBBLE);
            }).build());

    public static void registerItemGroups() {
        Primordial.LOGGER.info("Registering Item Groups for " + Primordial.MOD_ID);
    }







}
