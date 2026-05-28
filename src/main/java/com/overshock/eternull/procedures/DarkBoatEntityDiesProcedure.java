package com.overshock.eternull.procedures;

import com.overshock.eternull.init.EternullModItems;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.LevelAccessor;

public class DarkBoatEntityDiesProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z) {
      if (world instanceof ServerLevel _level) {
         ItemEntity entityToSpawn = new ItemEntity(_level, x + 0.5, y + 0.5, z + 0.5, new ItemStack((ItemLike)EternullModItems.DARK_BOAT_ITEM.get()));
         entityToSpawn.setPickUpDelay(10);
         _level.addFreshEntity(entityToSpawn);
      }
   }
}
