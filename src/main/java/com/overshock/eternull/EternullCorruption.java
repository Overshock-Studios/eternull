package com.overshock.eternull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import com.overshock.eternull.entity.CorruptedChargedCreeperEntity;
import com.overshock.eternull.entity.CorruptedCreeperEntity;
import com.overshock.eternull.entity.CorruptedSpiderEntity;
import com.overshock.eternull.entity.CorruptedZombieEntity;
import com.overshock.eternull.entity.NullmobEntity;
import com.overshock.eternull.init.EternullModBlocks;
import com.overshock.eternull.init.EternullModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.neoforged.neoforge.event.entity.living.LivingEvent;

public final class EternullCorruption {
   private static final String EXPOSURE_TAG = "eternullCorruptionExposure";
   private static final Map<String, Set<BlockPos>> HEART_POSITIONS = new ConcurrentHashMap<>();
   private static final BlockPos[] CARDINAL_OFFSETS = {
      new BlockPos(1, 0, 0),
      new BlockPos(-1, 0, 0),
      new BlockPos(0, 1, 0),
      new BlockPos(0, -1, 0),
      new BlockPos(0, 0, 1),
      new BlockPos(0, 0, -1)
   };
   private static final BlockPos[] SPREAD_OFFSETS = {
      new BlockPos(1, 0, 0),
      new BlockPos(-1, 0, 0),
      new BlockPos(0, 0, 1),
      new BlockPos(0, 0, -1),
      new BlockPos(0, -1, 0),
      new BlockPos(0, 1, 0),
      new BlockPos(1, 1, 0),
      new BlockPos(-1, 1, 0),
      new BlockPos(0, 1, 1),
      new BlockPos(0, 1, -1)
   };

   public static final ResourceKey<Biome> CORRUPTION_BIOME = ResourceKey.create(
      Registries.BIOME, new ResourceLocation("eternull", "the_corruption"));

   private EternullCorruption() {
   }

   public static boolean isInCorruptionBiome(LevelAccessor world, BlockPos pos) {
      if (!(world instanceof Level level)) return false;
      return level.getBiome(pos).is(CORRUPTION_BIOME);
   }

   public static void tickNullBlock(LevelAccessor world, BlockPos sourcePos) {
      if (!hasNonInertNeighbor(world, sourcePos)) {
         makeDormant(world, sourcePos);
         return;
      }
      if (!canSpreadFrom(world, sourcePos)) {
         return;
      }

      RandomSource random = random(world);
      int spreadChance = Math.min(100, EternullConfig.nullBlockSpreadChance()
         + (isWithinNullHeartInfluence(world, sourcePos) ? EternullConfig.nullHeartSpreadBonus() : 0));
      if (!roll(random, spreadChance)) {
         return;
      }

      List<BlockPos> targets = shuffledTargets(sourcePos, random);
      for (BlockPos target : targets) {
         if (corruptBlock(world, target)) {
            playBlockGlitch(world, target, random);
            return;
         }
      }
   }

   public static boolean hasNonInertNeighbor(LevelAccessor world, BlockPos pos) {
      BlockPos.MutableBlockPos m = new BlockPos.MutableBlockPos();
      for (BlockPos off : CARDINAL_OFFSETS) {
         m.set(pos.getX() + off.getX(), pos.getY() + off.getY(), pos.getZ() + off.getZ());
         BlockState s = world.getBlockState(m);
         if (!isInert(s)) return true;
      }
      return false;
   }

   public static boolean isInert(BlockState state) {
      Block b = state.getBlock();
      if (state.isAir()) return true;
      return b == EternullModBlocks.NULLBLOCK.get()
         || b == EternullModBlocks.DORMANT_NULL_BLOCK.get()
         || b == EternullModBlocks.NULL_HEART.get()
         || b == EternullModBlocks.NULL_WARD.get()
         || b == EternullModBlocks.DARK_LOG.get()
         || b == EternullModBlocks.DARK_LEAVES.get();
   }

   public static void onActiveNullNeighborUpdate(LevelAccessor world, BlockPos pos) {
      if (!hasNonInertNeighbor(world, pos)) {
         makeDormant(world, pos);
      }
   }

   public static void onDormantNeighborUpdate(LevelAccessor world, BlockPos pos) {
      if (hasNonInertNeighbor(world, pos)) {
         makeActive(world, pos);
      }
   }

   private static void makeDormant(LevelAccessor world, BlockPos pos) {
      BlockState old = world.getBlockState(pos);
      if (old.getBlock() != EternullModBlocks.NULLBLOCK.get()) return;
      world.setBlock(pos, copySharedProperties(old,
         ((Block) EternullModBlocks.DORMANT_NULL_BLOCK.get()).defaultBlockState()), 3);
   }

   private static void makeActive(LevelAccessor world, BlockPos pos) {
      BlockState old = world.getBlockState(pos);
      if (old.getBlock() != EternullModBlocks.DORMANT_NULL_BLOCK.get()) return;
      world.setBlock(pos, copySharedProperties(old,
         ((Block) EternullModBlocks.NULLBLOCK.get()).defaultBlockState()), 3);
   }

   public static void registerHeart(Level level, BlockPos pos) {
      HEART_POSITIONS.computeIfAbsent(level.dimension().location().toString(),
         k -> ConcurrentHashMap.newKeySet()).add(pos.immutable());
   }

   public static void unregisterHeart(Level level, BlockPos pos) {
      Set<BlockPos> set = HEART_POSITIONS.get(level.dimension().location().toString());
      if (set != null) set.remove(pos);
   }

   public static BlockPos nearestHeart(Level level, BlockPos pos, int maxRadius) {
      Set<BlockPos> set = HEART_POSITIONS.get(level.dimension().location().toString());
      if (set == null || set.isEmpty()) return null;
      double bestSq = (double) maxRadius * maxRadius;
      BlockPos best = null;
      for (BlockPos h : set) {
         double d = h.distSqr(pos);
         if (d <= bestSq) {
            bestSq = d;
            best = h;
         }
      }
      return best;
   }

   public static void tickNullMobFootprint(LevelAccessor world, BlockPos sourcePos) {
      if (!canSpreadFrom(world, sourcePos) || EternullConfig.nullMobFootprintMaxBlocks() <= 0) {
         return;
      }

      RandomSource random = random(world);
      if (!roll(random, EternullConfig.nullMobFootprintChance())) {
         return;
      }

      int converted = 0;
      for (BlockPos target : shuffledFootprintTargets(sourcePos, random)) {
         if (corruptBlock(world, target)) {
            converted++;
            playBlockGlitch(world, target, random);
            if (converted >= EternullConfig.nullMobFootprintMaxBlocks()) {
               return;
            }
         }
      }
   }

   public static void onLivingTick(LivingEvent.LivingTickEvent event) {
      LivingEntity entity = event.getEntity();
      if (entity.level().isClientSide || entity.tickCount % 10 != 0) {
         return;
      }

      boolean exposed = isTouchingActiveCorruption(entity);
      boolean inBiome = isInCorruptionBiome(entity.level(), entity.blockPosition());
      if (entity instanceof Player player) {
         maybeGlitchPlayer(player, exposed);
         maybeAuditoryMimicry(player);
         return;
      }

      if (!EternullConfig.mobCorruptionConversionEnabled()
         || !(entity instanceof Mob mob)
         || mob instanceof NullmobEntity
         || (!inBiome && !isWithinNullHeartInfluence(entity.level(), entity.blockPosition()))) {
         clearExposure(entity);
         return;
      }

      EntityType<? extends Mob> targetType = corruptedVariantFor(mob);
      if (targetType == null) {
         clearExposure(entity);
         return;
      }

      if (!exposed && !inBiome) {
         reduceExposure(entity);
         return;
      }

      CompoundTag data = entity.getPersistentData();
      int gain = exposed && inBiome ? 20 : 10;
      int exposure = data.getInt(EXPOSURE_TAG) + gain;
      data.putInt(EXPOSURE_TAG, exposure);
      if (exposure >= EternullConfig.mobCorruptionExposureTicks()
         && roll(entity.getRandom(), EternullConfig.mobCorruptionConversionChance())) {
         convertMob(mob, targetType);
      }
   }

   public static boolean canSpreadFrom(LevelAccessor world, BlockPos pos) {
      if (!EternullConfig.isCorruptionSpreadEnabled()) {
         return false;
      }

      if (!isWithinNullHeartInfluence(world, pos)) {
         return false;
      }

      if (!EternullConfig.corruptionSpreadsOnlyAtNight()) {
         return true;
      }

      return !(world instanceof Level level) || !level.isDay() || !world.canSeeSkyFromBelowWater(pos);
   }

   public static boolean isActiveCorruption(BlockState state) {
      return state.getBlock() == EternullModBlocks.NULLBLOCK.get();
   }

   public static boolean isWithinNullHeartInfluence(LevelAccessor world, BlockPos pos) {
      if (!EternullConfig.requireNullHeartForSpread()) {
         return true;
      }

      if (!(world instanceof Level level)) {
         return false;
      }

      if (isInCorruptionBiome(level, pos)) {
         return true;
      }

      return nearestHeart(level, pos, EternullConfig.nullHeartRadius()) != null;
   }

   public static boolean isProtectedByWard(LevelAccessor world, BlockPos pos) {
      int radius = EternullConfig.nullWardRadius();
      if (radius <= 0) {
         return false;
      }

      int radiusSquared = radius * radius;
      BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
      for (int dx = -radius; dx <= radius; dx++) {
         for (int dy = -radius; dy <= radius; dy++) {
            for (int dz = -radius; dz <= radius; dz++) {
               if (dx * dx + dy * dy + dz * dz <= radiusSquared) {
                  mutable.set(pos.getX() + dx, pos.getY() + dy, pos.getZ() + dz);
                  if (world.getBlockState(mutable).getBlock() == EternullModBlocks.NULL_WARD.get()) {
                     return true;
                  }
               }
            }
         }
      }
      return false;
   }

   private static final SoundEvent[] MIMICRY_SOUNDS = new SoundEvent[] {
      SoundEvents.CHEST_OPEN,
      SoundEvents.WOODEN_DOOR_OPEN,
      SoundEvents.GRAVEL_BREAK,
      SoundEvents.STONE_BREAK,
      SoundEvents.PLAYER_ATTACK_SWEEP,
      SoundEvents.WOOD_BREAK,
      SoundEvents.STONE_STEP,
      SoundEvents.SCULK_CLICKING,
      SoundEvents.ENDERMAN_AMBIENT
   };

   private static void maybeAuditoryMimicry(Player player) {
      if (!EternullConfig.auditoryMimicryEnabled()) return;
      if (!(player.level() instanceof ServerLevel sl)) return;
      RandomSource rng = player.getRandom();
      if (!roll(rng, EternullConfig.auditoryMimicryChance())) return;
      if (nearestHeart(sl, player.blockPosition(), EternullConfig.auditoryMimicryRadius()) == null) return;
      double angle = rng.nextDouble() * Math.PI * 2;
      double dist = 6 + rng.nextDouble() * 14;
      double x = player.getX() + Math.cos(angle) * dist;
      double z = player.getZ() + Math.sin(angle) * dist;
      double y = player.getY() + (rng.nextDouble() - 0.5) * 4;
      SoundEvent sound = MIMICRY_SOUNDS[rng.nextInt(MIMICRY_SOUNDS.length)];
      sl.playSound(null, x, y, z, sound, SoundSource.AMBIENT, 0.6F + rng.nextFloat() * 0.4F, 0.7F + rng.nextFloat() * 0.5F);
   }

   private static void maybeGlitchPlayer(Player player, boolean exposed) {
      if (!exposed || !EternullConfig.ambientGlitchesEnabled() || !(player.level() instanceof ServerLevel serverLevel)) {
         return;
      }

      RandomSource random = player.getRandom();
      if (!roll(random, EternullConfig.playerCorruptionGlitchChance())) {
         return;
      }

      serverLevel.sendParticles(
         ParticleTypes.REVERSE_PORTAL,
         player.getX() + (random.nextDouble() - 0.5) * 1.5,
         player.getY() + 0.2 + random.nextDouble() * 1.5,
         player.getZ() + (random.nextDouble() - 0.5) * 1.5,
         8,
         0.25,
         0.35,
         0.25,
         0.01
      );
      serverLevel.playSound(null, player.blockPosition(), SoundEvents.SCULK_CLICKING, SoundSource.AMBIENT, 0.25F, 0.45F + random.nextFloat() * 0.35F);
   }

   private static boolean isTouchingActiveCorruption(LivingEntity entity) {
      BlockPos feet = entity.blockPosition();
      Level level = entity.level();
      return isActiveCorruption(level.getBlockState(feet))
         || isActiveCorruption(level.getBlockState(feet.below()))
         || isActiveCorruption(level.getBlockState(BlockPos.containing(entity.getX(), entity.getY() + 0.5, entity.getZ())));
   }

   private static EntityType<? extends Mob> corruptedVariantFor(Mob mob) {
      if (mob instanceof CorruptedZombieEntity
         || mob instanceof CorruptedCreeperEntity
         || mob instanceof CorruptedChargedCreeperEntity
         || mob instanceof CorruptedSpiderEntity) {
         return null;
      }

      if (mob instanceof Zombie) {
         return EternullModEntities.CORRUPTED_ZOMBIE.get();
      }

      if (mob instanceof Creeper creeper) {
         return creeper.isPowered() ? EternullModEntities.CORRUPTED_CHARGED_CREEPER.get() : EternullModEntities.CORRUPTED_CREEPER.get();
      }

      if (mob instanceof Spider) {
         return EternullModEntities.CORRUPTED_SPIDER.get();
      }

      return null;
   }

   private static void convertMob(Mob mob, EntityType<? extends Mob> targetType) {
      Mob converted = mob.convertTo((EntityType)targetType, true);
      if (converted == null) {
         return;
      }

      converted.setHealth(Math.min(converted.getMaxHealth(), Math.max(1.0F, mob.getHealth())));
      converted.setDeltaMovement(mob.getDeltaMovement());
      if (mob.isPersistenceRequired()) {
         converted.setPersistenceRequired();
      }

      Level level = converted.level();
      if (level instanceof ServerLevel serverLevel) {
         RandomSource random = converted.getRandom();
         serverLevel.sendParticles(ParticleTypes.SCULK_SOUL, converted.getX(), converted.getY() + converted.getBbHeight() * 0.5, converted.getZ(), 16, 0.25, 0.35, 0.25, 0.02);
         serverLevel.playSound(null, converted.blockPosition(), SoundEvents.SCULK_CATALYST_BLOOM, SoundSource.HOSTILE, 0.7F, 0.65F + random.nextFloat() * 0.25F);
      }
   }

   private static boolean corruptBlock(LevelAccessor world, BlockPos pos) {
      BlockState oldState = world.getBlockState(pos);
      if (!canCorrupt(world, pos, oldState)) {
         return false;
      }

      world.setBlock(pos, copySharedProperties(oldState, corruptionReplacementFor(oldState)), 3);
      return true;
   }

   private static BlockState corruptionReplacementFor(BlockState oldState) {
      if (oldState.is(BlockTags.LEAVES)) {
         return ((Block)EternullModBlocks.DARK_LEAVES.get()).defaultBlockState();
      }

      if (oldState.is(BlockTags.LOGS)) {
         return ((Block)EternullModBlocks.DARK_LOG.get()).defaultBlockState();
      }

      return ((Block)EternullModBlocks.NULLBLOCK.get()).defaultBlockState();
   }

   private static boolean canCorrupt(LevelAccessor world, BlockPos pos, BlockState state) {
      Block block = state.getBlock();
      return block != EternullModBlocks.NULLBLOCK.get()
         && block != EternullModBlocks.DORMANT_NULL_BLOCK.get()
         && block != EternullModBlocks.DARK_LOG.get()
         && block != EternullModBlocks.DARK_LEAVES.get()
         && block != EternullModBlocks.NULL_WARD.get()
         && !isProtectedByWard(world, pos)
         && block != Blocks.AIR
         && block != Blocks.CAVE_AIR
         && block != Blocks.VOID_AIR
         && block != Blocks.WATER
         && block != Blocks.LAVA
         && block != Blocks.BEDROCK
         && block != Blocks.BARRIER
         && block != Blocks.END_PORTAL
         && block != Blocks.END_PORTAL_FRAME
         && block != Blocks.COMMAND_BLOCK
         && block != Blocks.CHAIN_COMMAND_BLOCK
         && block != Blocks.REPEATING_COMMAND_BLOCK
         && block != Blocks.STRUCTURE_BLOCK
         && block != Blocks.JIGSAW
         && world.getBlockEntity(pos) == null;
   }

   private static BlockState copySharedProperties(BlockState oldState, BlockState newState) {
      BlockState result = newState;
      for (Property<?> oldProperty : oldState.getProperties()) {
         Property<?> newProperty = result.getBlock().getStateDefinition().getProperty(oldProperty.getName());
         if (newProperty != null) {
            result = copyProperty(oldState, result, oldProperty, newProperty);
         }
      }
      return result;
   }

   private static <T extends Comparable<T>> BlockState copyProperty(BlockState oldState, BlockState newState, Property<T> oldProperty, Property<?> newProperty) {
      T value = oldState.getValue(oldProperty);
      if (newProperty.getValue(value.toString()).isPresent()) {
         return newState.setValue((Property)newProperty, value);
      }
      return newState;
   }

   private static void playBlockGlitch(LevelAccessor world, BlockPos pos, RandomSource random) {
      if (!EternullConfig.ambientGlitchesEnabled() || !(world instanceof ServerLevel serverLevel)) {
         return;
      }

      serverLevel.sendParticles(ParticleTypes.SCULK_SOUL, pos.getX() + 0.5, pos.getY() + 0.7, pos.getZ() + 0.5, 3, 0.2, 0.2, 0.2, 0.01);
      if (roll(random, 20)) {
         serverLevel.playSound(null, pos, SoundEvents.SCULK_BLOCK_SPREAD, SoundSource.BLOCKS, 0.35F, 0.75F + random.nextFloat() * 0.3F);
      }
   }

   private static List<BlockPos> shuffledTargets(BlockPos sourcePos, RandomSource random) {
      List<BlockPos> targets = new ArrayList<>(SPREAD_OFFSETS.length);
      for (BlockPos offset : SPREAD_OFFSETS) {
         targets.add(sourcePos.offset(offset));
      }
      shuffle(targets, random);
      return targets;
   }

   private static List<BlockPos> shuffledFootprintTargets(BlockPos sourcePos, RandomSource random) {
      List<BlockPos> targets = new ArrayList<>(9);
      BlockPos floor = sourcePos.below();
      for (int dx = -1; dx <= 1; dx++) {
         for (int dz = -1; dz <= 1; dz++) {
            targets.add(floor.offset(dx, 0, dz));
         }
      }
      shuffle(targets, random);
      return targets;
   }

   private static void shuffle(List<BlockPos> positions, RandomSource random) {
      for (int i = positions.size() - 1; i > 0; i--) {
         int j = random.nextInt(i + 1);
         BlockPos value = positions.get(i);
         positions.set(i, positions.get(j));
         positions.set(j, value);
      }
   }

   private static void reduceExposure(LivingEntity entity) {
      CompoundTag data = entity.getPersistentData();
      int exposure = data.getInt(EXPOSURE_TAG);
      if (exposure <= 10) {
         data.remove(EXPOSURE_TAG);
      } else {
         data.putInt(EXPOSURE_TAG, exposure - 10);
      }
   }

   private static void clearExposure(LivingEntity entity) {
      entity.getPersistentData().remove(EXPOSURE_TAG);
   }

   private static RandomSource random(LevelAccessor world) {
      return world instanceof Level level ? level.getRandom() : RandomSource.create();
   }

   private static boolean roll(RandomSource random, int chance) {
      return chance >= 100 || chance > 0 && random.nextInt(100) < chance;
   }

}
