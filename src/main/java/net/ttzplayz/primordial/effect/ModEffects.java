package net.ttzplayz.primordial.effect;

import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.ttzplayz.primordial.Primordial;

public class ModEffects {
    public static final RegistryEntry<StatusEffect> PARALYSIS = registerStatusEffect("paralysis",
            new ParalysisStatusEffect(StatusEffectCategory.NEUTRAL, 0x36ebab));


    private static RegistryEntry<StatusEffect> registerStatusEffect(String name, StatusEffect statusEffect) {
        return Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(Primordial.MOD_ID, name), statusEffect);
    }

    public static void registerEffects() {
        Primordial.LOGGER.info("Registering Mod Effects for " + Primordial.MOD_ID);
    }
}
