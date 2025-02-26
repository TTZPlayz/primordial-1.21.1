package net.ttzplayz.primordial;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.ttzplayz.primordial.block.ModBlocks;
import net.ttzplayz.primordial.effect.ModEffects;
import net.ttzplayz.primordial.entity.ModEntities;
import net.ttzplayz.primordial.entity.custom.BlubEntity;
import net.ttzplayz.primordial.item.ModItemGroups;
import net.ttzplayz.primordial.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Primordial implements ModInitializer {
	public static final String MOD_ID = "primordial";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
// test commit comment
	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModEffects.registerEffects();
		ModEntities.registerModEntities();
		ModItemGroups.registerItemGroups();

		FabricDefaultAttributeRegistry.register(ModEntities.BLUB, BlubEntity.createAttributes());
	}
}