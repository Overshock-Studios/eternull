package com.overshock.eternull.entity;

import com.overshock.eternull.init.EternullModEntities;
import com.overshock.eternull.init.EternullModItems;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class NullGuardianEntity extends NullmobEntity {
   public NullGuardianEntity(EntityType<NullGuardianEntity> type, Level world) {
      super(toBase(type), world);
      this.xpReward = 60;
   }

   @SuppressWarnings("unchecked")
   private static EntityType<NullmobEntity> toBase(EntityType<NullGuardianEntity> t) {
      return (EntityType<NullmobEntity>) (EntityType<?>) t;
   }

   @Override
   public void baseTick() {
      super.baseTick();
      if (this.level() instanceof ServerLevel sl && this.tickCount % 4 == 0) {
         sl.sendParticles(ParticleTypes.SCULK_SOUL,
            this.getX(), this.getY() + this.getBbHeight() * 0.6, this.getZ(),
            3, 0.3, 0.4, 0.3, 0.02);
      }
   }

   @Override
   protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit) {
      this.spawnAtLocation(new ItemStack(EternullModItems.HEARTSHARD.get(), 1 + this.random.nextInt(2)));
      this.spawnAtLocation(new ItemStack(EternullModItems.NULLITE.get(), 2 + this.random.nextInt(3)));
   }

   public static void init() {
      SpawnPlacements.register(
         EternullModEntities.NULL_GUARDIAN.get(),
         SpawnPlacements.Type.ON_GROUND,
         Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
         (entityType, world, reason, pos, random) -> world.getDifficulty() != Difficulty.PEACEFUL
            && Monster.isDarkEnoughToSpawn(world, pos, random)
            && Mob.checkMobSpawnRules(entityType, world, reason, pos, random)
      );
   }

   public static AttributeSupplier.Builder createGuardianAttributes() {
      return NullmobEntity.createAttributes()
         .add(Attributes.MAX_HEALTH, 120.0)
         .add(Attributes.MOVEMENT_SPEED, 0.42)
         .add(Attributes.ATTACK_DAMAGE, 16.0)
         .add(Attributes.FOLLOW_RANGE, 48.0)
         .add(Attributes.KNOCKBACK_RESISTANCE, 4.0);
   }
}
