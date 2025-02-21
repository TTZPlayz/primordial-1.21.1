package net.ttzplayz.primordial.entity.custom;

import net.minecraft.client.util.math.Vector2f;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.ttzplayz.primordial.entity.ModEntities;
import net.ttzplayz.primordial.item.ModItems;

public class BlubbleProjectileEntity extends PersistentProjectileEntity {
    private float rotation;
    public Vector2f groundedOffset;


    public BlubbleProjectileEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    public BlubbleProjectileEntity(World world, PlayerEntity player) {
        super(ModEntities.BLUBBLE, player, world, new ItemStack(ModItems.BLUBBLE), null);
    }

    public BlubbleProjectileEntity(World world, BlubEntity blubEntity) {
        super(ModEntities.BLUBBLE, world);
    }

    @Override
    protected ItemStack getDefaultItemStack() {
        return new ItemStack(ModItems.BLUBBLE);
    }

    public float getRenderingRotation() {
        rotation += 0.5f;
        if (rotation >= 360) {
            rotation = 0;
        }
        return rotation;
    }

    public boolean isGrounded() {
        return inGround;
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {

        super.onEntityHit(entityHitResult);
        Entity entity = entityHitResult.getEntity();
        entity.damage(this.getDamageSources().thrown(this, this.getOwner()), 4);
        ((LivingEntity) entity).addStatusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 100, 1));
        if (entity.isWet()) {
            int air = entity.getAir();
            entity.setAir(air - 40); // Drains oxygen faster underwater
        }
        if (entity.isOnFire()) {
            entity.extinguish(); // Extinguishes if on fire
        }
        if (!this.getWorld().isClient()) {
            this.getWorld().sendEntityStatus(this, (byte)3);
            World world = getWorld();
            ((ServerWorld) world).spawnParticles(ParticleTypes.BUBBLE_POP, getX() + 0.5, getY() + 1.0, getZ() + 0.5, 20, 0, 0, 0, 1);
            ((ServerWorld) world).spawnParticles(ParticleTypes.SPLASH, getX() + 0.5, getY() + 1.0, getZ() + 0.5, 5, 0, 0, 0, 1);
            world.playSound(null, getX(), getY(), getZ(), SoundEvents.ENTITY_GENERIC_SPLASH, SoundCategory.NEUTRAL, 0.5f, 0.4f / (world.getRandom().nextFloat() * 0.4F + 0.8F));
            this.discard();
        }
    }

    @Override
    protected void onBlockHit(BlockHitResult result) {
        super.onBlockHit(result);

        if (result.getSide() == Direction.SOUTH) {
            groundedOffset = new Vector2f(215f, 180f);
        }

        if (result.getSide() == Direction.NORTH) {
            groundedOffset = new Vector2f(215f, 0f);
        }

        if (result.getSide() == Direction.EAST) {
            groundedOffset = new Vector2f(215f, -90f);
        }

        if (result.getSide() == Direction.WEST) {
            groundedOffset = new Vector2f(215f, 90f);
        }

        if (result.getSide() == Direction.UP) {
            groundedOffset = new Vector2f(115f, 180f);
        }

        if (result.getSide() == Direction.DOWN) {
            groundedOffset = new Vector2f(285f, 180f);
        }

        if (!this.getWorld().isClient()) {
            World world = getWorld();
            ((ServerWorld) world).spawnParticles(ParticleTypes.BUBBLE_POP, getBlockX() + 0.5, getBlockY() + 1.0, getBlockZ() + 0.5, 20, 0, 0, 0, 1);
            ((ServerWorld) world).spawnParticles(ParticleTypes.SPLASH, getBlockX() + 0.5, getBlockY() + 1.0, getBlockZ() + 0.5, 5, 0, 0, 0, 1);
            world.playSound(null, getX(), getY(), getZ(), SoundEvents.ENTITY_GENERIC_SPLASH, SoundCategory.NEUTRAL, 0.5f, 0.4f / (world.getRandom().nextFloat() * 0.4F + 0.8F));
            this.discard();
        }
    }
}
