package com.overshock.eternull.block;

import com.overshock.eternull.EternullConfig;
import com.overshock.eternull.EternullCorruption;
import com.overshock.eternull.entity.NullGuardianEntity;
import com.overshock.eternull.init.EternullModBlockEntities;
import com.overshock.eternull.init.EternullModBlocks;
import com.overshock.eternull.init.EternullModEntities;
import com.overshock.eternull.init.EternullModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class NullHeartBlockEntity extends BlockEntity {
   public static final int MAX_HP = 30;
   private static final int TICK_INTERVAL = 4;
   private static final int HIT_COOLDOWN = 8;
   private static final int ALERT_RADIUS = 64;
   private static final int DEFENDING_RADIUS = 24;
   private static final int ENRAGED_RADIUS = 8;
   private static final int WAVE_COOLDOWN = 220;
   private static final int TENDRIL_COOLDOWN_DORMANT = 80;
   private static final int TENDRIL_COOLDOWN_ACTIVE = 25;
   private static final int IMMUNITY_INTERVAL = 380;
   private static final int IMMUNITY_DURATION = 80;
   private static final int TENDRIL_SURFACE_RANGE = 80;

   private int hp = MAX_HP;
   private int phase = 0;
   private int immunityTicks = 0;
   private long lastHitTick = -100L;
   private long lastWaveTick = 0L;
   private long lastTendrilTick = 0L;
   private long lastImmunityStart = 0L;
   private BlockPos tendrilCursor = null;
   private boolean tendrilReachedSurface = false;

   public NullHeartBlockEntity(BlockPos pos, BlockState state) {
      super(EternullModBlockEntities.NULL_HEART.get(), pos, state);
   }

   @Override
   public void onLoad() {
      super.onLoad();
      if (this.level != null && !this.level.isClientSide) {
         EternullCorruption.registerHeart(this.level, this.worldPosition);
      }
   }

   @Override
   public void setRemoved() {
      super.setRemoved();
      if (this.level != null && !this.level.isClientSide) {
         EternullCorruption.unregisterHeart(this.level, this.worldPosition);
      }
   }

   public int getHp() {
      return this.hp;
   }

   public int getPhase() {
      return this.phase;
   }

   public boolean isImmune() {
      return this.immunityTicks > 0;
   }

   public boolean isDefended() {
      return this.phase >= 1;
   }

   public static void serverTick(Level level, BlockPos pos, BlockState state, NullHeartBlockEntity be) {
      if (!(level instanceof ServerLevel sl)) return;
      if ((sl.getGameTime() + (pos.hashCode() & 3)) % TICK_INTERVAL != 0) return;

      RandomSource rng = sl.getRandom();
      Player nearest = sl.getNearestPlayer(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, ALERT_RADIUS, false);
      int newPhase = 0;
      double dist = Double.MAX_VALUE;
      if (nearest != null && !nearest.isCreative() && !nearest.isSpectator()) {
         dist = Math.sqrt(nearest.distanceToSqr(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5));
         if (dist <= ENRAGED_RADIUS) newPhase = 3;
         else if (dist <= DEFENDING_RADIUS) newPhase = 2;
         else if (dist <= ALERT_RADIUS) newPhase = 1;
      }

      if (newPhase != be.phase) {
         be.phase = newPhase;
         if (newPhase >= 2) {
            sl.playSound(null, pos, SoundEvents.SCULK_SHRIEKER_SHRIEK, SoundSource.HOSTILE, 1.4F, 0.5F);
         }
         be.setChanged();
      }

      int tendrilCd = be.phase >= 1 ? TENDRIL_COOLDOWN_ACTIVE : TENDRIL_COOLDOWN_DORMANT;
      if (sl.getGameTime() - be.lastTendrilTick >= tendrilCd) {
         be.lastTendrilTick = sl.getGameTime();
         advanceTendril(sl, pos, be, rng);
      }

      if (be.immunityTicks > 0) {
         be.immunityTicks -= TICK_INTERVAL;
         if (be.immunityTicks < 0) be.immunityTicks = 0;
         emitShield(sl, pos, rng);
      }

      if (be.phase == 0) {
         if (be.hp < MAX_HP && sl.getGameTime() - be.lastHitTick > 200) {
            be.hp = Math.min(MAX_HP, be.hp + 1);
            be.setChanged();
         }
         return;
      }

      emitAura(sl, pos, rng, be.phase);

      if (be.phase >= 2) {
         long waveCd = be.phase == 3 ? WAVE_COOLDOWN * 2L / 3L : WAVE_COOLDOWN;
         if (sl.getGameTime() - be.lastWaveTick >= waveCd) {
            be.lastWaveTick = sl.getGameTime();
            spawnWave(sl, pos, be.phase, rng);
         }
      }

      if (be.phase == 3 && be.immunityTicks == 0
         && sl.getGameTime() - be.lastImmunityStart >= IMMUNITY_INTERVAL) {
         be.lastImmunityStart = sl.getGameTime();
         be.immunityTicks = IMMUNITY_DURATION;
         sl.playSound(null, pos, SoundEvents.WARDEN_SONIC_BOOM, SoundSource.HOSTILE, 1.2F, 0.6F);
         be.setChanged();
      }
   }

   public boolean handlePunch(ServerLevel level, BlockPos pos, Player player) {
      if (this.phase == 0) return false;
      long now = level.getGameTime();
      if (this.isImmune()) {
         level.sendParticles(ParticleTypes.SOUL_FIRE_FLAME,
            pos.getX() + 0.5, pos.getY() + 0.9, pos.getZ() + 0.5, 12, 0.4, 0.4, 0.4, 0.01);
         level.playSound(null, pos, SoundEvents.SHIELD_BLOCK, SoundSource.BLOCKS, 0.8F, 0.4F);
         return true;
      }
      if (now - this.lastHitTick < HIT_COOLDOWN) return true;
      this.lastHitTick = now;
      this.hp = Math.max(0, this.hp - 1);
      this.setChanged();
      level.playSound(null, pos, SoundEvents.SCULK_SHRIEKER_BREAK, SoundSource.BLOCKS, 0.8F, 1.4F - (float) this.hp / MAX_HP * 0.6F);
      level.sendParticles(ParticleTypes.DAMAGE_INDICATOR,
         pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 0.5, 6, 0.3, 0.3, 0.3, 0.1);
      if (this.hp <= 0) {
         this.die(level, pos, player);
      }
      return true;
   }

   private void die(ServerLevel level, BlockPos pos, Player killer) {
      level.destroyBlock(pos, true, killer);
      Block.popResource(level, pos, new ItemStack(EternullModItems.HEARTSHARD.get()));
      level.sendParticles(ParticleTypes.SCULK_SOUL,
         pos.getX() + 0.5, pos.getY() + 0.7, pos.getZ() + 0.5, 60, 0.6, 0.6, 0.6, 0.05);
      level.playSound(null, pos, SoundEvents.WARDEN_DEATH, SoundSource.HOSTILE, 2.0F, 0.5F);
      NullGuardianEntity g = new NullGuardianEntity(EternullModEntities.NULL_GUARDIAN.get(), level);
      g.moveTo(pos.getX() + 0.5, pos.getY() + 0.1, pos.getZ() + 0.5, level.getRandom().nextFloat() * 360F, 0F);
      g.finalizeSpawn(level, level.getCurrentDifficultyAt(pos), MobSpawnType.TRIGGERED, null, null);
      level.addFreshEntity(g);
   }

   private static void emitAura(ServerLevel level, BlockPos pos, RandomSource rng, int phase) {
      int count = 2 + phase * 2;
      level.sendParticles(ParticleTypes.SCULK_CHARGE_POP,
         pos.getX() + 0.5, pos.getY() + 0.8, pos.getZ() + 0.5,
         count, 0.4, 0.4, 0.4, 0.02);
      if (phase >= 3 && rng.nextInt(3) == 0) {
         level.playSound(null, pos, SoundEvents.WARDEN_HEARTBEAT, SoundSource.HOSTILE, 1.0F, 0.5F);
      }
   }

   private static void emitShield(ServerLevel level, BlockPos pos, RandomSource rng) {
      for (int i = 0; i < 12; i++) {
         double angle = rng.nextDouble() * Math.PI * 2;
         double yo = rng.nextDouble() * 2 - 0.5;
         double r = 1.2;
         level.sendParticles(ParticleTypes.END_ROD,
            pos.getX() + 0.5 + Math.cos(angle) * r,
            pos.getY() + 0.5 + yo,
            pos.getZ() + 0.5 + Math.sin(angle) * r,
            1, 0, 0, 0, 0);
      }
   }

   private static void advanceTendril(ServerLevel level, BlockPos pos, NullHeartBlockEntity be, RandomSource rng) {
      BlockPos cursor = be.tendrilCursor;
      if (cursor == null) {
         cursor = pos.above();
         be.tendrilReachedSurface = false;
      }

      if (!be.tendrilReachedSurface) {
         BlockPos next = cursor.above();
         tryCarve(level, next);
         if (level.canSeeSky(next.above())) {
            be.tendrilReachedSurface = true;
            level.sendParticles(ParticleTypes.SCULK_SOUL,
               next.getX() + 0.5, next.getY() + 1.0, next.getZ() + 0.5,
               20, 0.4, 0.4, 0.4, 0.05);
         }
         be.tendrilCursor = next;
         return;
      }

      if (cursor.distSqr(pos) > (long) TENDRIL_SURFACE_RANGE * TENDRIL_SURFACE_RANGE) {
         be.tendrilCursor = null;
         be.tendrilReachedSurface = false;
         return;
      }

      int dx = rng.nextInt(3) - 1;
      int dz = rng.nextInt(3) - 1;
      if (dx == 0 && dz == 0) dx = rng.nextBoolean() ? 1 : -1;
      BlockPos lateral = cursor.offset(dx, 0, dz);
      BlockPos surface = findSurface(level, lateral);
      if (surface == null) return;
      tryCarve(level, surface);
      if (rng.nextInt(4) == 0) {
         tryCarve(level, surface.below());
      }
      level.sendParticles(ParticleTypes.SCULK_CHARGE_POP,
         surface.getX() + 0.5, surface.getY() + 0.8, surface.getZ() + 0.5,
         6, 0.4, 0.4, 0.4, 0.02);
      be.tendrilCursor = surface;
   }

   private static BlockPos findSurface(ServerLevel level, BlockPos approx) {
      int top = level.getHeight(net.minecraft.world.level.levelgen.Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
         approx.getX(), approx.getZ());
      BlockPos surface = new BlockPos(approx.getX(), top - 1, approx.getZ());
      if (surface.getY() < level.getMinBuildHeight() + 1) return null;
      return surface;
   }

   private static void tryCarve(ServerLevel level, BlockPos pos) {
      BlockState old = level.getBlockState(pos);
      Block b = old.getBlock();
      if (old.isAir()) return;
      if (b == EternullModBlocks.NULLBLOCK.get() || b == EternullModBlocks.DORMANT_NULL_BLOCK.get()
         || b == EternullModBlocks.NULL_HEART.get() || b == EternullModBlocks.NULL_WARD.get()) return;
      if (old.getDestroySpeed(level, pos) < 0) return;
      if (level.getBlockEntity(pos) != null) return;
      if (EternullCorruption.isProtectedByWard(level, pos)) return;
      if (b == net.minecraft.world.level.block.Blocks.BEDROCK) return;
      if (old.liquid()) return;
      level.setBlock(pos, ((Block) EternullModBlocks.NULLBLOCK.get()).defaultBlockState(), 3);
      level.sendParticles(ParticleTypes.SCULK_SOUL,
         pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 4, 0.3, 0.3, 0.3, 0.01);
   }

   private static void spawnWave(ServerLevel level, BlockPos pos, int phase, RandomSource rng) {
      int count = phase == 3 ? 4 : 2;
      for (int i = 0; i < count; i++) {
         BlockPos spawnPos = findSpawnSpot(level, pos, rng);
         if (spawnPos == null) continue;
         EntityType<? extends Mob> type = pickWaveMob(phase, rng);
         if (type == null) continue;
         Mob mob = type.create(level);
         if (mob == null) continue;
         mob.moveTo(spawnPos.getX() + 0.5, spawnPos.getY(), spawnPos.getZ() + 0.5,
            rng.nextFloat() * 360F, 0F);
         mob.finalizeSpawn(level, level.getCurrentDifficultyAt(spawnPos), MobSpawnType.TRIGGERED, null, null);
         level.addFreshEntity(mob);
         level.sendParticles(ParticleTypes.SCULK_SOUL,
            spawnPos.getX() + 0.5, spawnPos.getY() + 0.5, spawnPos.getZ() + 0.5,
            14, 0.4, 0.6, 0.4, 0.02);
      }
      level.playSound(null, pos, SoundEvents.SCULK_CATALYST_BLOOM, SoundSource.HOSTILE, 1.4F, 0.6F);
   }

   private static EntityType<? extends Mob> pickWaveMob(int phase, RandomSource rng) {
      if (phase == 3) {
         int r = rng.nextInt(10);
         if (r < 1) return EternullModEntities.NULLMOB.get();
         if (r < 4) return EternullModEntities.CORRUPTED_ZOMBIE.get();
         if (r < 7) return EternullModEntities.CORRUPTED_SPIDER.get();
         return EternullModEntities.CORRUPTED_CREEPER.get();
      }
      int r = rng.nextInt(10);
      if (r < 5) return EternullModEntities.CORRUPTED_ZOMBIE.get();
      if (r < 8) return EternullModEntities.CORRUPTED_SPIDER.get();
      return EternullModEntities.CORRUPTED_CREEPER.get();
   }

   private static BlockPos findSpawnSpot(ServerLevel level, BlockPos heart, RandomSource rng) {
      for (int attempt = 0; attempt < 10; attempt++) {
         int dx = rng.nextInt(13) - 6;
         int dz = rng.nextInt(13) - 6;
         int dy = rng.nextInt(5) - 2;
         BlockPos p = heart.offset(dx, dy, dz);
         BlockPos floor = p;
         for (int s = 0; s < 4; s++) {
            if (level.getBlockState(floor.below()).isSolid()
               && level.getBlockState(floor).isAir()
               && level.getBlockState(floor.above()).isAir()) {
               return floor;
            }
            floor = floor.below();
         }
      }
      return null;
   }

   @Override
   protected void saveAdditional(CompoundTag tag) {
      super.saveAdditional(tag);
      tag.putInt("hp", this.hp);
      tag.putInt("phase", this.phase);
      tag.putInt("immunity", this.immunityTicks);
      tag.putLong("lastHit", this.lastHitTick);
      tag.putLong("lastWave", this.lastWaveTick);
      tag.putLong("lastTendril", this.lastTendrilTick);
      tag.putLong("lastImmunityStart", this.lastImmunityStart);
      tag.putBoolean("tendrilSurface", this.tendrilReachedSurface);
   }

   @Override
   public void load(CompoundTag tag) {
      super.load(tag);
      this.hp = tag.contains("hp") ? tag.getInt("hp") : MAX_HP;
      this.phase = tag.getInt("phase");
      this.immunityTicks = tag.getInt("immunity");
      this.lastHitTick = tag.getLong("lastHit");
      this.lastWaveTick = tag.getLong("lastWave");
      this.lastTendrilTick = tag.getLong("lastTendril");
      this.lastImmunityStart = tag.getLong("lastImmunityStart");
      this.tendrilReachedSurface = tag.getBoolean("tendrilSurface");
   }

}
