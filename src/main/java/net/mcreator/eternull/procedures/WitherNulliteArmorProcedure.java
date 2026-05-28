package net.mcreator.eternull.procedures;

import javax.annotation.Nullable;
import net.mcreator.eternull.init.EternullModItems;
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

         if (sourceentity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
            _entity.addEffect(new MobEffectInstance(MobEffects.WITHER, (int)ticks, 2, false, true));
         }
      }
   }
}
