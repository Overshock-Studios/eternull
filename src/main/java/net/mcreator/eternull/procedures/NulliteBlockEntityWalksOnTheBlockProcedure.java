package net.mcreator.eternull.procedures;

import net.mcreator.eternull.entity.CorruptedChargedCreeperEntity;
import net.mcreator.eternull.entity.CorruptedCreeperEntity;
import net.mcreator.eternull.entity.CorruptedSpiderEntity;
import net.mcreator.eternull.entity.CorruptedZombieEntity;
import net.mcreator.eternull.init.EternullModEntities;
import net.mcreator.eternull.init.EternullModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

public class NulliteBlockEntityWalksOnTheBlockProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null) {
         if (entity instanceof Player
            && !(world instanceof Level _lvl1 && _lvl1.isDay() && world.canSeeSkyFromBelowWater(BlockPos.containing(x, y, z)))
            && (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY).getItem()
               != EternullModItems.NULLITE_ARMOR_BOOTS.get()) {
            if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
               _entity.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 60, 1, false, false));
            }
         } else if (entity instanceof Creeper && !(entity instanceof CorruptedCreeperEntity) && !(entity instanceof CorruptedChargedCreeperEntity)) {
            if (!entity.level().isClientSide()) {
               entity.discard();
            }

            if (95 >= Mth.nextInt(RandomSource.create(), 1, 100)) {
               if (world instanceof ServerLevel _level) {
                  Entity entityToSpawn = ((EntityType)EternullModEntities.CORRUPTED_CREEPER.get())
                     .spawn(_level, BlockPos.containing(x, y + 1.0, z), MobSpawnType.MOB_SUMMONED);
                  if (entityToSpawn != null) {
                     entityToSpawn.setYRot(world.getRandom().nextFloat() * 360.0F);
                  }
               }
            } else if (world instanceof ServerLevel _level) {
               Entity entityToSpawn = ((EntityType)EternullModEntities.NULLMOB.get())
                  .spawn(_level, BlockPos.containing(x, y + 1.0, z), MobSpawnType.MOB_SUMMONED);
               if (entityToSpawn != null) {
                  entityToSpawn.setYRot(world.getRandom().nextFloat() * 360.0F);
               }
            }
         } else if (entity instanceof Zombie && !(entity instanceof CorruptedZombieEntity)) {
            if (!entity.level().isClientSide()) {
               entity.discard();
            }

            if (95 >= Mth.nextInt(RandomSource.create(), 1, 100)) {
               if (world instanceof ServerLevel _level) {
                  Entity entityToSpawn = ((EntityType)EternullModEntities.CORRUPTED_ZOMBIE.get())
                     .spawn(_level, BlockPos.containing(x, y + 1.0, z), MobSpawnType.MOB_SUMMONED);
                  if (entityToSpawn != null) {
                     entityToSpawn.setYRot(world.getRandom().nextFloat() * 360.0F);
                  }
               }
            } else if (world instanceof ServerLevel _level) {
               Entity entityToSpawn = ((EntityType)EternullModEntities.NULLMOB.get())
                  .spawn(_level, BlockPos.containing(x, y + 1.0, z), MobSpawnType.MOB_SUMMONED);
               if (entityToSpawn != null) {
                  entityToSpawn.setYRot(world.getRandom().nextFloat() * 360.0F);
               }
            }
         } else if (entity instanceof Spider && !(entity instanceof CorruptedSpiderEntity)) {
            if (!entity.level().isClientSide()) {
               entity.discard();
            }

            if (95 >= Mth.nextInt(RandomSource.create(), 1, 100)) {
               if (world instanceof ServerLevel _level) {
                  Entity entityToSpawn = ((EntityType)EternullModEntities.CORRUPTED_SPIDER.get())
                     .spawn(_level, BlockPos.containing(x, y + 1.0, z), MobSpawnType.MOB_SUMMONED);
                  if (entityToSpawn != null) {
                     entityToSpawn.setYRot(world.getRandom().nextFloat() * 360.0F);
                  }
               }
            } else if (world instanceof ServerLevel _level) {
               Entity entityToSpawn = ((EntityType)EternullModEntities.NULLMOB.get())
                  .spawn(_level, BlockPos.containing(x, y + 1.0, z), MobSpawnType.MOB_SUMMONED);
               if (entityToSpawn != null) {
                  entityToSpawn.setYRot(world.getRandom().nextFloat() * 360.0F);
               }
            }
         } else if (!entity.level().isClientSide()) {
            entity.discard();
         }
      }
   }
}
