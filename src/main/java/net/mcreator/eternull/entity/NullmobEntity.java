package net.mcreator.eternull.entity;

import net.mcreator.eternull.init.EternullModEntities;
import net.mcreator.eternull.init.EternullModItems;
import net.mcreator.eternull.procedures.NullmobEntityIsHurtProcedure;
import net.mcreator.eternull.procedures.NullmobOnEntityTickUpdateProcedure;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.SpawnPlacements.Type;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier.Builder;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.Heightmap.Types;
import net.neoforged.neoforge.common.NeoForgeMod;

public class NullmobEntity extends Monster {
   public NullmobEntity(EntityType<NullmobEntity> type, Level world) {
      super(type, world);
      this.setMaxUpStep(0.6F);
      this.xpReward = 25;
      this.setNoAi(false);
   }

   protected void registerGoals() {
      super.registerGoals();
      this.goalSelector
         .addGoal(
            1,
            new MeleeAttackGoal(this, 1.0, true) {
               protected boolean canPerformAttack(LivingEntity entity) {
                  return this.isTimeToAttack()
                     && this.mob.distanceToSqr(entity) < this.mob.getBbWidth() * this.mob.getBbWidth() + entity.getBbWidth()
                     && this.mob.getSensing().hasLineOfSight(entity);
               }
            }
         );
      this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 0.8));
      this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));
      this.targetSelector.addGoal(4, new NearestAttackableTargetGoal(this, Player.class, false, false));
   }

   public MobType getMobType() {
      return MobType.UNDEFINED;
   }

   protected float ridingOffset(Entity entity) {
      return -0.35F;
   }

   protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHitIn) {
      super.dropCustomDeathLoot(source, looting, recentlyHitIn);
      this.spawnAtLocation(new ItemStack((ItemLike)EternullModItems.NULLITE.get()));
   }

   public SoundEvent getAmbientSound() {
      return (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(new ResourceLocation("entity.warden.ambient"));
   }

   public SoundEvent getHurtSound(DamageSource ds) {
      return (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(new ResourceLocation("entity.generic.hurt"));
   }

   public SoundEvent getDeathSound() {
      return (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(new ResourceLocation("entity.generic.death"));
   }

   public boolean hurt(DamageSource damagesource, float amount) {
      double x = this.getX();
      double y = this.getY();
      double z = this.getZ();
      Level world = this.level();
      Entity entity = this;
      Entity sourceentity = damagesource.getEntity();
      Entity immediatesourceentity = damagesource.getDirectEntity();
      NullmobEntityIsHurtProcedure.execute(world, x, y, z, entity, sourceentity);
      if (damagesource.is(DamageTypes.IN_FIRE)) {
         return false;
      } else if (damagesource.getDirectEntity() instanceof AbstractArrow) {
         return false;
      } else if (damagesource.getDirectEntity() instanceof ThrownPotion
         || damagesource.getDirectEntity() instanceof AreaEffectCloud
         || damagesource.typeHolder().is(NeoForgeMod.POISON_DAMAGE)) {
         return false;
      } else if (damagesource.is(DamageTypes.FALL)) {
         return false;
      } else if (damagesource.is(DamageTypes.CACTUS)) {
         return false;
      } else if (damagesource.is(DamageTypes.DROWN)) {
         return false;
      } else if (damagesource.is(DamageTypes.LIGHTNING_BOLT)) {
         return false;
      } else if (damagesource.is(DamageTypes.EXPLOSION) || damagesource.is(DamageTypes.PLAYER_EXPLOSION)) {
         return false;
      } else if (damagesource.is(DamageTypes.TRIDENT)) {
         return false;
      } else if (damagesource.is(DamageTypes.FALLING_ANVIL)) {
         return false;
      } else if (damagesource.is(DamageTypes.DRAGON_BREATH)) {
         return false;
      } else {
         return !damagesource.is(DamageTypes.WITHER) && !damagesource.is(DamageTypes.WITHER_SKULL) ? super.hurt(damagesource, amount) : false;
      }
   }

   public boolean ignoreExplosion(Explosion explosion) {
      return true;
   }

   public boolean fireImmune() {
      return true;
   }

   public void baseTick() {
      super.baseTick();
      NullmobOnEntityTickUpdateProcedure.execute(this.level(), this.getX(), this.getY(), this.getZ());
   }

   public static void init() {
      SpawnPlacements.register(
         (EntityType)EternullModEntities.NULLMOB.get(),
         Type.ON_GROUND,
         Types.MOTION_BLOCKING_NO_LEAVES,
         (entityType, world, reason, pos, random) -> world.getDifficulty() != Difficulty.PEACEFUL
            && Monster.isDarkEnoughToSpawn(world, pos, random)
            && Mob.checkMobSpawnRules(entityType, world, reason, pos, random)
      );
   }

   public static Builder createAttributes() {
      Builder builder = Mob.createMobAttributes();
      builder = builder.add(Attributes.MOVEMENT_SPEED, 0.3);
      builder = builder.add(Attributes.MAX_HEALTH, 80.0);
      builder = builder.add(Attributes.ARMOR, 2.5);
      builder = builder.add(Attributes.ATTACK_DAMAGE, 12.0);
      builder = builder.add(Attributes.FOLLOW_RANGE, 25.0);
      builder = builder.add(Attributes.KNOCKBACK_RESISTANCE, 2.5);
      return builder.add(Attributes.ATTACK_KNOCKBACK, 1.0);
   }
}
