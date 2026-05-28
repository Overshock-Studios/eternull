package net.mcreator.eternull.block;

import net.mcreator.eternull.procedures.NullblockOnTickUpdateProcedure;
import net.mcreator.eternull.procedures.NulliteBlockEntityWalksOnTheBlockProcedure;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class NullblockBlock extends Block {
   public NullblockBlock() {
      super(
         Properties.of()
            .sound(SoundType.SCULK)
            .strength(1.2F, 10.0F)
            .requiresCorrectToolForDrops()
            .randomTicks()
            .hasPostProcess((bs, br, bp) -> true)
            .emissiveRendering((bs, br, bp) -> true)
      );
   }

   public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
      return 15;
   }

   public void randomTick(BlockState blockstate, ServerLevel world, BlockPos pos, RandomSource random) {
      super.randomTick(blockstate, world, pos, random);
      NullblockOnTickUpdateProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ());
   }

   public void stepOn(Level world, BlockPos pos, BlockState blockstate, Entity entity) {
      super.stepOn(world, pos, blockstate, entity);
      NulliteBlockEntityWalksOnTheBlockProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ(), entity);
   }
}
