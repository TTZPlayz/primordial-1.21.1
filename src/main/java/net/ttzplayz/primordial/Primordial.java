package net.ttzplayz.primordial;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
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
		ModEffects.registerEffects();
		ModEntities.registerModEntities();
		ModItemGroups.registerItemGroups();



		AttackEntityCallback.EVENT.register(((player, world, hand, entity, entityHitResult) -> {
			if(entity instanceof SheepEntity sheepEntity) {
				if(player.getMainHandStack().getItem() == Items.END_ROD) {
					player.sendMessage(Text.literal("YOU HIT A SHEEP WITH AN END ROD?!?!?! YOU SICK FRICK!!!"));
					player.getMainHandStack().decrement(1);
					player.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 6969, 69696969));
					player.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 69, 69696969));


				}
				return ActionResult.PASS;
			}

			return ActionResult.PASS;
		}));

		FabricDefaultAttributeRegistry.register(ModEntities.BLUB, BlubEntity.createAttributes());
	}
}