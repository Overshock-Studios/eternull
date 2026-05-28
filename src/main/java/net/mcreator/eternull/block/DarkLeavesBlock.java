package net.mcreator.eternull.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class DarkLeavesBlock extends LeavesBlock {
   public DarkLeavesBlock() {
      super(Properties.of().ignitedByLava().sound(SoundType.GRASS).strength(0.2F).noOcclusion());
   }

   public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
      return 1;
   }

   public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
      return 30;
   }
}
