package net.mcreator.eternull.procedures;

import net.mcreator.eternull.EternullCorruption;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;

public class NullblockOnTickUpdateProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z) {
      EternullCorruption.tickNullBlock(world, BlockPos.containing(x, y, z));
   }
}
