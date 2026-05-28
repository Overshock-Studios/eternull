package com.overshock.eternull.procedures;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;

public class DarkBoatOnEntityTickUpdateProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null) {
         if (world.getBlockState(BlockPos.containing(x, y, z)).getBlock() == Blocks.WATER) {
            if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
               _entity.addEffect(new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 1, 1, false, false));
            }
         } else if (world.getBlockState(BlockPos.containing(x, y, z)).getBlock() == Blocks.WATER) {
            if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
               _entity.addEffect(new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 1, 1, false, false));
            }
         } else if (world.getBlockState(BlockPos.containing(x, y, z)).getBlock() == Blocks.BUBBLE_COLUMN) {
            if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
               _entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1, 1, false, false));
            }
         } else if (world.getBlockState(BlockPos.containing(x, y, z)).getBlock() == Blocks.KELP_PLANT) {
            if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
               _entity.addEffect(new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 1, 1, false, false));
            }
         } else if (world.getBlockState(BlockPos.containing(x, y, z)).getBlock() == Blocks.TALL_SEAGRASS) {
            if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
               _entity.addEffect(new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 1, 1, false, false));
            }
         } else if (world.getBlockState(BlockPos.containing(x, y, z)).getBlock() == Blocks.KELP) {
            if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
               _entity.addEffect(new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 1, 1, false, false));
            }
         } else if (world.getBlockState(BlockPos.containing(x, y, z)).getBlock() == Blocks.SEAGRASS) {
            if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
               _entity.addEffect(new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 1, 1, false, false));
            }
         } else {
            entity.makeStuckInBlock(Blocks.AIR.defaultBlockState(), new Vec3(0.25, 0.05, 0.25));
         }
      }
   }
}
