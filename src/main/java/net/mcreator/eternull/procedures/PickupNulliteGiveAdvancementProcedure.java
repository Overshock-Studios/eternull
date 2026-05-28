package net.mcreator.eternull.procedures;

import javax.annotation.Nullable;
import net.mcreator.eternull.init.EternullModItems;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.EntityItemPickupEvent;

@EventBusSubscriber
public class PickupNulliteGiveAdvancementProcedure {
   @SubscribeEvent
   public static void onPickup(EntityItemPickupEvent event) {
      execute(event, event.getEntity(), event.getItem().getItem());
   }

   public static void execute(Entity entity, ItemStack itemstack) {
      execute(null, entity, itemstack);
   }

   private static void execute(@Nullable Event event, Entity entity, ItemStack itemstack) {
      if (entity != null) {
         if (entity instanceof Player && itemstack.getItem() == EternullModItems.NULLITE.get() && entity instanceof ServerPlayer _player) {
            AdvancementHolder _adv = _player.server.getAdvancements().get(new ResourceLocation("eternull:nullite_achievement"));
            if (_adv != null) {
               AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
               if (!_ap.isDone()) {
                  for (String criteria : _ap.getRemainingCriteria()) {
                     _player.getAdvancements().award(_adv, criteria);
                  }
               }
            }
         }
      }
   }
}
