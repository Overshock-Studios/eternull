package com.overshock.eternull.block;

import com.overshock.eternull.EternullConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class NullHeartBlock extends Block {
   public NullHeartBlock() {
      super(
         Properties.of()
            .sound(SoundType.SCULK_SHRIEKER)
            .strength(30.0F, 1200.0F)
            .requiresCorrectToolForDrops()
            .randomTicks()
            .lightLevel(state -> 15)
            .hasPostProcess((bs, br, bp) -> true)
            .emissiveRendering((bs, br, bp) -> true)
      );
   }

   public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
      return 0;
   }

   public void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
      super.randomTick(state, world, pos, random);
      if (EternullConfig.ambientGlitchesEnabled()) {
         world.sendParticles(ParticleTypes.SCULK_SOUL, pos.getX() + 0.5, pos.getY() + 0.8, pos.getZ() + 0.5, 10, 0.35, 0.35, 0.35, 0.02);
         world.playSound(null, pos, SoundEvents.SCULK_SHRIEKER_SHRIEK, SoundSource.BLOCKS, 0.35F, 0.45F + random.nextFloat() * 0.25F);
      }
   }
}
