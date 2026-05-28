package net.mcreator.eternull.procedures;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

public class NullmobEntityIsHurtProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, Entity sourceentity) {
      if (entity != null && sourceentity != null) {
         if (sourceentity instanceof Player && 25 >= Mth.nextInt(RandomSource.create(), 1, 100)) {
            Entity _ent = entity;
            _ent.teleportTo(sourceentity.getX(), sourceentity.getY(), sourceentity.getZ());
            if (_ent instanceof ServerPlayer _serverPlayer) {
               _serverPlayer.connection.teleport(sourceentity.getX(), sourceentity.getY(), sourceentity.getZ(), _ent.getYRot(), _ent.getXRot());
            }

            if (world instanceof Level _level) {
               if (!_level.isClientSide()) {
                  _level.playSound(
                     null,
                     BlockPos.containing(x, y, z),
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(new ResourceLocation("item.chorus_fruit.teleport")),
                     SoundSource.HOSTILE,
                     1.0F,
                     1.0F
                  );
               } else {
                  _level.playLocalSound(
                     x,
                     y,
                     z,
                     (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(new ResourceLocation("item.chorus_fruit.teleport")),
                     SoundSource.HOSTILE,
                     1.0F,
                     1.0F,
                     false
                  );
               }
            }
         }
      }
   }
}
