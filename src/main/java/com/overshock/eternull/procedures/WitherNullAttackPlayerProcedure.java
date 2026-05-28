package com.overshock.eternull.procedures;

import javax.annotation.Nullable;
import com.overshock.eternull.entity.CorruptedChargedCreeperEntity;
import com.overshock.eternull.entity.CorruptedCreeperEntity;
import com.overshock.eternull.entity.CorruptedSpiderEntity;
import com.overshock.eternull.entity.CorruptedZombieEntity;
import com.overshock.eternull.entity.NullmobEntity;
import net.minecraft.world.Difficulty;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.LevelAccessor;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingAttackEvent;

@EventBusSubscriber
public class WitherNullAttackPlayerProcedure {
   @SubscribeEvent
   public static void onEntityAttacked(LivingAttackEvent event) {
      if (event != null && event.getEntity() != null) {
         execute(event, event.getEntity().level(), event.getEntity(), event.getSource().getEntity());
      }
   }

   public static void execute(LevelAccessor world, Entity entity, Entity sourceentity) {
      execute(null, world, entity, sourceentity);
   }

   private static void execute(@Nullable Event event, LevelAccessor world, Entity entity, Entity sourceentity) {
      if (entity != null && sourceentity != null) {
         if (sourceentity instanceof NullmobEntity && world.getDifficulty() == Difficulty.NORMAL) {
            if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
               _entity.addEffect(new MobEffectInstance(MobEffects.WITHER, 20, 2, false, false));
            }
         } else if (sourceentity instanceof NullmobEntity && world.getDifficulty() == Difficulty.HARD) {
            if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
               _entity.addEffect(new MobEffectInstance(MobEffects.WITHER, 40, 2, false, false));
            }
         } else if ((
               sourceentity instanceof CorruptedChargedCreeperEntity
                  || sourceentity instanceof CorruptedCreeperEntity
                  || sourceentity instanceof CorruptedSpiderEntity
                  || sourceentity instanceof CorruptedZombieEntity
            )
            && world.getDifficulty() == Difficulty.NORMAL) {
            if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
               _entity.addEffect(new MobEffectInstance(MobEffects.WITHER, 20, 1, false, false));
            }
         } else if ((
               sourceentity instanceof CorruptedChargedCreeperEntity
                  || sourceentity instanceof CorruptedCreeperEntity
                  || sourceentity instanceof CorruptedSpiderEntity
                  || sourceentity instanceof CorruptedZombieEntity
            )
            && world.getDifficulty() == Difficulty.HARD
            && entity instanceof LivingEntity _entity
            && !_entity.level().isClientSide()) {
            _entity.addEffect(new MobEffectInstance(MobEffects.WITHER, 40, 1, false, false));
         }
      }
   }
}
