package net.ttzplayz.primordial.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class ParalysisStatusEffect extends StatusEffect {

    public ParalysisStatusEffect(StatusEffectCategory category, int color) {
        super(StatusEffectCategory.HARMFUL, 0x999999); // Gray color
    }

    @Override
    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (entity.getWorld().isClient) {
            return true;
        }
        // 50% chance each tick to prevent movement
        if (entity.getWorld().random.nextFloat() < 0.5) {
            entity.setVelocity(0, entity.getVelocity().y, 0);
            entity.velocityDirty = true;
        }
        return false;
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        // Update effect each tick
        return true;
    }
}
