package com.overshock.eternull.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class NullWardBlock extends Block {
   public NullWardBlock() {
      super(Properties.of().sound(SoundType.AMETHYST).strength(1.5F, 8.0F).lightLevel(state -> 7));
   }

   public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
      return 0;
   }
}
