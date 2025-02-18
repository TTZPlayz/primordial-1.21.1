package net.ttzplayz.primordial.entity.custom;

import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

import java.util.EnumSet;

public class BlubEntity extends HostileEntity {
    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new BlubAttackGoal(this, 1.0D, 20, 40));
        this.goalSelector.add(2, new WanderAroundGoal(this, 0.8D, 100));
        this.goalSelector.add(3, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.add(4, new LookAroundGoal(this));

        this.targetSelector.add(1, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
    }

    public class BlubAttackGoal extends Goal {
        private final BlubEntity blub;
        private int updateCountdownTicks;
        private final double speed;
        private int attackIntervalMin;
        private int attackIntervalMax;
        private int seeTime;

        public BlubAttackGoal(BlubEntity blub, double speed, int attackIntervalMin, int attackIntervalMax) {
            this.blub = blub;
            this.speed = speed;
            this.attackIntervalMin = attackIntervalMin;
            this.attackIntervalMax = attackIntervalMax;
            this.setControls(EnumSet.of(Control.MOVE, Control.LOOK));
        }

        @Override
        public boolean canStart() {
            LivingEntity target = this.blub.getTarget();
            return target != null && target.isAlive();
        }

        @Override
        public void start() {
            this.updateCountdownTicks = 0;
            this.seeTime = 0;
        }

        @Override
        public void stop() {
            this.blub.setAttacking(false);
        }
        @Override
        public void tick() {
            LivingEntity target = this.blub.getTarget();
            if (target == null) return;

            double distanceSq = this.blub.squaredDistanceTo(target.getX(), target.getY(), target.getZ());
            boolean canSeeTarget = this.blub.getVisibilityCache().canSee(target);

            if (canSeeTarget) {
                this.seeTime++;
            } else {
                this.seeTime = 0;
            }

            if (distanceSq <= 64.0D && this.seeTime >= 5) {
                this.blub.getNavigation().stop();
                this.blub.getLookControl().lookAt(target, 30.0F, 30.0F);
                if (--this.updateCountdownTicks <= 0) {
                    this.updateCountdownTicks = this.attackIntervalMin + this.blub.getRandom().nextInt(this.attackIntervalMax - this.attackIntervalMin + 1);
                    this.blub.performRangedAttack(target, 1.0F);
                }
            } else {
                this.blub.getNavigation().startMovingTo(target, this.speed);
                this.updateCountdownTicks = -1;
            }

            super.tick();
        }
    }

    private void performRangedAttack(LivingEntity target, float pullProgress) {
        BlubbleEntity blubble = new BlubbleEntity(this.getWorld(), this);
        double dX = target.getX() - this.getX();
        double dY = target.getBodyY(0.5D) - blubble.getY();
        double dZ = target.getZ() - this.getZ();
        blubble.setVelocity(dX, dY, dZ, 1.5F, 1.0F);
        this.getWorld().spawnEntity(blubble);

        this.playSound(SoundEvents.ENTITY_GENERIC_SPLASH, 1.0F, 1.0F);
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 20.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.4)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 4.0)
                .add(EntityAttributes.GENERIC_WATER_MOVEMENT_EFFICIENCY, 1)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 20.0);
    }

    private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = 80;
            this.idleAnimationState.start(this.age);
        } else {
            --this.idleAnimationTimeout;
        }
    }

    @Override
    public void tick() {
        super.tick();

        if (this.getWorld().isClient()) {
            this.setupAnimationStates();
        }
    }

    public BlubEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }
}
