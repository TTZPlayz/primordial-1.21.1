package net.ttzplayz.primordial;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.ttzplayz.primordial.datagen.ModBlockTagProvider;
import net.ttzplayz.primordial.datagen.ModItemTagProvider;
import net.ttzplayz.primordial.datagen.ModModelProvider;

public class PrimordialDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(ModBlockTagProvider::new);
		pack.addProvider(ModItemTagProvider::new);
	}
}
