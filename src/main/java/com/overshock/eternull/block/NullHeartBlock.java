package com.overshock.eternull.block;

import com.overshock.eternull.EternullConfig;
import com.overshock.eternull.init.EternullModBlockEntities;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class NullHeartBlock extends Block implements EntityBlock {
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

   @Override
   public float getDestroyProgress(BlockState state, Player player, BlockGetter world, BlockPos pos) {
      BlockEntity be = world.getBlockEntity(pos);
      if (be instanceof NullHeartBlockEntity heart && heart.isDefended()) {
         return 0F;
      }
      return super.getDestroyProgress(state, player, world, pos);
   }

   @Override
   public void attack(BlockState state, Level world, BlockPos pos, Player player) {
      if (!(world instanceof ServerLevel sl)) return;
      BlockEntity be = sl.getBlockEntity(pos);
      if (be instanceof NullHeartBlockEntity heart) {
         heart.handlePunch(sl, pos, player);
      }
   }

   @Override
   public void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
      super.randomTick(state, world, pos, random);
      if (EternullConfig.ambientGlitchesEnabled()) {
         world.sendParticles(ParticleTypes.SCULK_SOUL, pos.getX() + 0.5, pos.getY() + 0.8, pos.getZ() + 0.5, 10, 0.35, 0.35, 0.35, 0.02);
         world.playSound(null, pos, SoundEvents.SCULK_SHRIEKER_SHRIEK, SoundSource.BLOCKS, 0.35F, 0.45F + random.nextFloat() * 0.25F);
      }
   }

   @Nullable
   @Override
   public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
      return new NullHeartBlockEntity(pos, state);
   }

   @Nullable
   @Override
   public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
      if (level.isClientSide) return null;
      if (type != EternullModBlockEntities.NULL_HEART.get()) return null;
      return (lvl, pos, st, be) -> NullHeartBlockEntity.serverTick(lvl, pos, st, (NullHeartBlockEntity) be);
   }
}
