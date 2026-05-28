package net.mcreator.eternull.procedures;

import net.mcreator.eternull.EternullCorruption;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;

public class NullmobOnEntityTickUpdateProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z) {
      EternullCorruption.tickNullMobFootprint(world, BlockPos.containing(x, y, z));
   }
}
