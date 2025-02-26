package net.ttzplayz.primordial.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;

public class ParalysisStatusEffect extends StatusEffect {

    public ParalysisStatusEffect(StatusEffectCategory category, int color) {
        super(StatusEffectCategory.HARMFUL, 0x999999); // Gray color
    }

    @Override
    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (entity.age % 20 == 0) { // Applies every second
            entity.setVelocity(0, entity.getVelocity().y, 0); // Stops horizontal movement
            entity.velocityModified = true;
            if (!entity.getWorld().isClient) {
                ((ServerWorld) entity.getWorld()).spawnParticles(
                        ParticleTypes.ELECTRIC_SPARK,
                        entity.getX(), entity.getY() + entity.getHeight() / 2, entity.getZ(),
                        5, 0.2, 0.5, 0.2, 0.0
                );
            }
        }
        return true;
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true; // Allows the effect to update every tick
    }
}
