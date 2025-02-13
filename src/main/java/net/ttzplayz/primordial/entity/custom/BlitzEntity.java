//// BlitzEntity.java
//package net.ttzplayz.primordial.entity.custom;
//
//import net.minecraft.entity.EntityType;
//import net.minecraft.entity.attribute.DefaultAttributeContainer;
//import net.minecraft.entity.attribute.EntityAttributes;
//import net.minecraft.entity.mob.HostileEntity;
//import net.minecraft.entity.effect.StatusEffectInstance;
//import net.minecraft.world.World;
//import net.minecraft.sound.SoundEvents;
//
//public class BlitzEntity extends HostileEntity {
//
//    private int chargeTime;
//
//    public BlitzEntity(EntityType<? extends HostileEntity> entityType, World world) {
//        super(entityType, world);
//        this.experiencePoints = 10; // Experience dropped on death
//    }
//
//    // Attribute registration
//    public static DefaultAttributeContainer.Builder createBlitzAttributes() {
//        return HostileEntity.createHostileAttributes()
//                .add(EntityAttributes.GENERIC_MAX_HEALTH, 30.0D)
//                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25D)
//                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 6.0D);
//    }
//
//    @Override
//    public void tick() {
//        super.tick();
//        // Handle charging behavior
//        if (this.getWorld().isClient) return;
//
//        if (this.chargeTime > 0) {
//            this.chargeTime--;
//            if (this.chargeTime == 0) {
//                // Perform dash attack
//                this.performDashAttack();
//            }
//        } else {
//            // Begin charging if target is present
//            if (this.getTarget() != null && this.canSee(this.getTarget())) {
//                this.beginCharging();
//            }
//        }
//    }
//
//    private void beginCharging() {
//        this.chargeTime = 60; // 3 seconds charge time
//        this.getWorld().sendEntityStatus(this, (byte) 10); // Custom entity status for client-side effects
//    }
//
//    private void performDashAttack() {
//        if (this.getTarget() != null) {
//            double dx = this.getTarget().getX() - this.getX();
//            double dz = this.getTarget().getZ() - this.getZ();
//            double distance = Math.sqrt(dx * dx + dz * dz);
//            if (distance > 0) {
//                // Normalize and set velocity towards target
//                this.setVelocity(dx / distance * 1.5, this.getVelocity().y, dz / distance * 1.5);
//                this.velocityDirty = true;
//            }
//            // Apply paralysis to entities hit (handled in collision code)
//        }
//    }
//
//    @Override
//    public void onEntityCollision(net.minecraft.entity.Entity entity) {
//        super.onEntityCollision(entity);
//        if (entity instanceof net.minecraft.entity.LivingEntity && entity != this) {
//            // Apply paralysis effect
//            ((net.minecraft.entity.LivingEntity) entity).addStatusEffect(new StatusEffectInstance(ModEffects.PARALYSIS, 600, 0));
//            // Play sound
//            this.getWorld().playSound(null, this.getBlockPos(), SoundEvents.ENTITY_GENERIC_HURT, this.getSoundCategory(), 1.0F, 1.0F);
//        }
//    }
//}
