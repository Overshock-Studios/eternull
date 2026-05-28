package com.overshock.eternull.procedures;

import com.overshock.eternull.init.EternullModEntities;
import com.overshock.eternull.init.EternullModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.LevelAccessor;

public class DarkBoatItemRightclickedProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null) {
         if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == EternullModItems.DARK_BOAT_ITEM.get()) {
            if (world instanceof ServerLevel _level) {
               Entity entityToSpawn = ((EntityType)EternullModEntities.DARK_BOAT.get()).spawn(_level, BlockPos.containing(x, y, z), MobSpawnType.MOB_SUMMONED);
               if (entityToSpawn != null) {
                  entityToSpawn.setDeltaMovement(0.0, 0.0, 0.0);
               }
            }

            if (entity instanceof Player _player) {
               ItemStack _stktoremove = new ItemStack((ItemLike)EternullModItems.DARK_BOAT_ITEM.get());
               _player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 1, _player.inventoryMenu.getCraftSlots());
            }
         }
      }
   }
}
