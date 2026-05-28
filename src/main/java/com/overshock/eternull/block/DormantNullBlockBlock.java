package com.overshock.eternull.block;

import com.overshock.eternull.procedures.NulliteBlockEntityWalksOnTheBlockProcedure;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class DormantNullBlockBlock extends Block {
   public DormantNullBlockBlock() {
      super(Properties.of().sound(SoundType.SCULK).strength(1.2F, 10.0F).hasPostProcess((bs, br, bp) -> true).emissiveRendering((bs, br, bp) -> true));
   }

   public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
      return 15;
   }

   public void stepOn(Level world, BlockPos pos, BlockState blockstate, Entity entity) {
      super.stepOn(world, pos, blockstate, entity);
      NulliteBlockEntityWalksOnTheBlockProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ(), entity);
   }
}
