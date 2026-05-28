package com.overshock.eternull.procedures;

import javax.annotation.Nullable;
import com.overshock.eternull.EternullConfig;
import com.overshock.eternull.init.EternullModItems;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingAttackEvent;

@EventBusSubscriber
public class WitherNulliteArmorProcedure {
   private static final String REFLECTION_COOLDOWN_TAG = "eternullNulliteArmorReflectionAt";

   @SubscribeEvent
   public static void onEntityAttacked(LivingAttackEvent event) {
      if (event != null && event.getEntity() != null) {
         execute(event, event.getEntity(), event.getSource().getEntity());
      }
   }

   public static void execute(Entity entity, Entity sourceentity) {
      execute(null, entity, sourceentity);
   }

   private static void execute(@Nullable Event event, Entity entity, Entity sourceentity) {
      if (entity != null && sourceentity != null) {
         double ticks = 0.0;
         ticks = 0.0;
         if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY).getItem()
            == EternullModItems.NULLITE_ARMOR_HELMET.get()) {
            ticks += 20.0;
         }

         if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY).getItem()
            == EternullModItems.NULLITE_ARMOR_CHESTPLATE.get()) {
            ticks += 20.0;
         }

         if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY).getItem()
            == EternullModItems.NULLITE_ARMOR_LEGGINGS.get()) {
            ticks += 20.0;
         }

         if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY).getItem()
            == EternullModItems.NULLITE_ARMOR_BOOTS.get()) {
            ticks += 20.0;
         }

         if (ticks > 0.0 && sourceentity instanceof LivingEntity _entity && !_entity.level().isClientSide() && canReflect(entity)) {
            _entity.addEffect(new MobEffectInstance(MobEffects.WITHER, (int)ticks, 2, false, true));
         }
      }
   }

   private static boolean canReflect(Entity entity) {
      int cooldown = EternullConfig.nulliteArmorReflectionCooldown();
      if (cooldown <= 0) {
         return true;
      }

      long gameTime = entity.level().getGameTime();
      long nextAllowed = entity.getPersistentData().getLong(REFLECTION_COOLDOWN_TAG);
      if (gameTime < nextAllowed) {
         return false;
      }

      entity.getPersistentData().putLong(REFLECTION_COOLDOWN_TAG, gameTime + cooldown);
      return true;
   }
}
