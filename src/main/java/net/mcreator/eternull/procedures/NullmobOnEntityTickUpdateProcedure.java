package net.mcreator.eternull.procedures;

import com.google.common.collect.UnmodifiableIterator;
import java.util.Map.Entry;
import net.mcreator.eternull.EternullConfig;
import net.mcreator.eternull.init.EternullModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;

public class NullmobOnEntityTickUpdateProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z) {
      if (!EternullConfig.isCorruptionSpreadEnabled()) {
         return;
      }

      if (world.getBlockState(BlockPos.containing(x, y - 1.0, z)).getBlock() != Blocks.AIR
         && world.getBlockState(BlockPos.containing(x, y - 1.0, z)).getBlock() != Blocks.WATER
         && world.getBlockState(BlockPos.containing(x, y - 1.0, z)).getBlock() != Blocks.WATER
         && world.getBlockState(BlockPos.containing(x, y - 1.0, z)).getBlock() != Blocks.LAVA
         && world.getBlockState(BlockPos.containing(x, y - 1.0, z)).getBlock() != Blocks.LAVA
         && world.getBlockState(BlockPos.containing(x, y - 1.0, z)).getBlock() != Blocks.BEDROCK) {
         BlockPos _bp = BlockPos.containing(x, y - 1.0, z);
         BlockState _bs = ((Block)EternullModBlocks.NULLBLOCK.get()).defaultBlockState();
         BlockState _bso = world.getBlockState(_bp);
         UnmodifiableIterator var10 = _bso.getValues().entrySet().iterator();

         while (var10.hasNext()) {
            Entry<Property<?>, Comparable<?>> entry = (Entry<Property<?>, Comparable<?>>)var10.next();
            Property _property = _bs.getBlock().getStateDefinition().getProperty(entry.getKey().getName());
            if (_property != null && _bs.getValue(_property) != null) {
               try {
                  _bs = (BlockState)_bs.setValue((Property)_property, (Comparable)entry.getValue());
               } catch (Exception var22) {
               }
            }
         }

         world.setBlock(_bp, _bs, 3);
         if (world.getBlockState(BlockPos.containing(x + 1.0, y - 1.0, z + 0.0)).getBlock() != Blocks.AIR
            && world.getBlockState(BlockPos.containing(x + 1.0, y - 1.0, z + 0.0)).getBlock() != Blocks.WATER
            && world.getBlockState(BlockPos.containing(x + 1.0, y - 1.0, z + 0.0)).getBlock() != Blocks.WATER
            && world.getBlockState(BlockPos.containing(x + 1.0, y - 1.0, z + 0.0)).getBlock() != Blocks.LAVA
            && world.getBlockState(BlockPos.containing(x + 1.0, y - 1.0, z + 0.0)).getBlock() != Blocks.LAVA) {
            _bp = BlockPos.containing(x + 1.0, y - 1.0, z + 0.0);
            _bs = ((Block)EternullModBlocks.NULLBLOCK.get()).defaultBlockState();
            _bso = world.getBlockState(_bp);
            var10 = _bso.getValues().entrySet().iterator();

            while (var10.hasNext()) {
               Entry<Property<?>, Comparable<?>> entry = (Entry<Property<?>, Comparable<?>>)var10.next();
               Property _property = _bs.getBlock().getStateDefinition().getProperty(entry.getKey().getName());
               if (_property != null && _bs.getValue(_property) != null) {
                  try {
                     _bs = (BlockState)_bs.setValue((Property)_property, (Comparable)entry.getValue());
                  } catch (Exception var21) {
                  }
               }
            }

            world.setBlock(_bp, _bs, 3);
         }

         if (world.getBlockState(BlockPos.containing(x + 0.0, y - 1.0, z + 1.0)).getBlock() != Blocks.AIR
            && world.getBlockState(BlockPos.containing(x + 0.0, y - 1.0, z + 1.0)).getBlock() != Blocks.WATER
            && world.getBlockState(BlockPos.containing(x + 0.0, y - 1.0, z + 1.0)).getBlock() != Blocks.WATER
            && world.getBlockState(BlockPos.containing(x + 0.0, y - 1.0, z + 1.0)).getBlock() != Blocks.LAVA
            && world.getBlockState(BlockPos.containing(x + 0.0, y - 1.0, z + 1.0)).getBlock() != Blocks.LAVA) {
            _bp = BlockPos.containing(x + 0.0, y - 1.0, z + 1.0);
            _bs = ((Block)EternullModBlocks.NULLBLOCK.get()).defaultBlockState();
            _bso = world.getBlockState(_bp);
            var10 = _bso.getValues().entrySet().iterator();

            while (var10.hasNext()) {
               Entry<Property<?>, Comparable<?>> entry = (Entry<Property<?>, Comparable<?>>)var10.next();
               Property _property = _bs.getBlock().getStateDefinition().getProperty(entry.getKey().getName());
               if (_property != null && _bs.getValue(_property) != null) {
                  try {
                     _bs = (BlockState)_bs.setValue((Property)_property, (Comparable)entry.getValue());
                  } catch (Exception var20) {
                  }
               }
            }

            world.setBlock(_bp, _bs, 3);
         }

         if (world.getBlockState(BlockPos.containing(x + 1.0, y - 1.0, z + 1.0)).getBlock() != Blocks.AIR
            && world.getBlockState(BlockPos.containing(x + 1.0, y - 1.0, z + 1.0)).getBlock() != Blocks.WATER
            && world.getBlockState(BlockPos.containing(x + 1.0, y - 1.0, z + 1.0)).getBlock() != Blocks.WATER
            && world.getBlockState(BlockPos.containing(x + 1.0, y - 1.0, z + 1.0)).getBlock() != Blocks.LAVA
            && world.getBlockState(BlockPos.containing(x + 1.0, y - 1.0, z + 1.0)).getBlock() != Blocks.LAVA) {
            _bp = BlockPos.containing(x + 1.0, y - 1.0, z + 1.0);
            _bs = ((Block)EternullModBlocks.NULLBLOCK.get()).defaultBlockState();
            _bso = world.getBlockState(_bp);
            var10 = _bso.getValues().entrySet().iterator();

            while (var10.hasNext()) {
               Entry<Property<?>, Comparable<?>> entry = (Entry<Property<?>, Comparable<?>>)var10.next();
               Property _property = _bs.getBlock().getStateDefinition().getProperty(entry.getKey().getName());
               if (_property != null && _bs.getValue(_property) != null) {
                  try {
                     _bs = (BlockState)_bs.setValue((Property)_property, (Comparable)entry.getValue());
                  } catch (Exception var19) {
                  }
               }
            }

            world.setBlock(_bp, _bs, 3);
         }

         if (world.getBlockState(BlockPos.containing(x - 1.0, y - 1.0, z - 1.0)).getBlock() != Blocks.AIR
            && world.getBlockState(BlockPos.containing(x - 1.0, y - 1.0, z - 1.0)).getBlock() != Blocks.WATER
            && world.getBlockState(BlockPos.containing(x - 1.0, y - 1.0, z - 1.0)).getBlock() != Blocks.WATER
            && world.getBlockState(BlockPos.containing(x - 1.0, y - 1.0, z - 1.0)).getBlock() != Blocks.LAVA
            && world.getBlockState(BlockPos.containing(x - 1.0, y - 1.0, z - 1.0)).getBlock() != Blocks.LAVA) {
            _bp = BlockPos.containing(x - 1.0, y - 1.0, z - 1.0);
            _bs = ((Block)EternullModBlocks.NULLBLOCK.get()).defaultBlockState();
            _bso = world.getBlockState(_bp);
            var10 = _bso.getValues().entrySet().iterator();

            while (var10.hasNext()) {
               Entry<Property<?>, Comparable<?>> entry = (Entry<Property<?>, Comparable<?>>)var10.next();
               Property _property = _bs.getBlock().getStateDefinition().getProperty(entry.getKey().getName());
               if (_property != null && _bs.getValue(_property) != null) {
                  try {
                     _bs = (BlockState)_bs.setValue((Property)_property, (Comparable)entry.getValue());
                  } catch (Exception var18) {
                  }
               }
            }

            world.setBlock(_bp, _bs, 3);
         }

         if (world.getBlockState(BlockPos.containing(x - 0.0, y - 1.0, z - 1.0)).getBlock() != Blocks.AIR
            && world.getBlockState(BlockPos.containing(x - 0.0, y - 1.0, z - 1.0)).getBlock() != Blocks.WATER
            && world.getBlockState(BlockPos.containing(x - 0.0, y - 1.0, z - 1.0)).getBlock() != Blocks.WATER
            && world.getBlockState(BlockPos.containing(x - 0.0, y - 1.0, z - 1.0)).getBlock() != Blocks.LAVA
            && world.getBlockState(BlockPos.containing(x - 0.0, y - 1.0, z - 1.0)).getBlock() != Blocks.LAVA) {
            _bp = BlockPos.containing(x - 0.0, y - 1.0, z - 1.0);
            _bs = ((Block)EternullModBlocks.NULLBLOCK.get()).defaultBlockState();
            _bso = world.getBlockState(_bp);
            var10 = _bso.getValues().entrySet().iterator();

            while (var10.hasNext()) {
               Entry<Property<?>, Comparable<?>> entry = (Entry<Property<?>, Comparable<?>>)var10.next();
               Property _property = _bs.getBlock().getStateDefinition().getProperty(entry.getKey().getName());
               if (_property != null && _bs.getValue(_property) != null) {
                  try {
                     _bs = (BlockState)_bs.setValue((Property)_property, (Comparable)entry.getValue());
                  } catch (Exception var17) {
                  }
               }
            }

            world.setBlock(_bp, _bs, 3);
         }

         if (world.getBlockState(BlockPos.containing(x - 1.0, y - 1.0, z - 0.0)).getBlock() != Blocks.AIR
            && world.getBlockState(BlockPos.containing(x - 1.0, y - 1.0, z - 0.0)).getBlock() != Blocks.WATER
            && world.getBlockState(BlockPos.containing(x - 1.0, y - 1.0, z - 0.0)).getBlock() != Blocks.WATER
            && world.getBlockState(BlockPos.containing(x - 1.0, y - 1.0, z - 0.0)).getBlock() != Blocks.LAVA
            && world.getBlockState(BlockPos.containing(x - 1.0, y - 1.0, z - 0.0)).getBlock() != Blocks.LAVA) {
            _bp = BlockPos.containing(x - 1.0, y - 1.0, z - 0.0);
            _bs = ((Block)EternullModBlocks.NULLBLOCK.get()).defaultBlockState();
            _bso = world.getBlockState(_bp);
            var10 = _bso.getValues().entrySet().iterator();

            while (var10.hasNext()) {
               Entry<Property<?>, Comparable<?>> entry = (Entry<Property<?>, Comparable<?>>)var10.next();
               Property _property = _bs.getBlock().getStateDefinition().getProperty(entry.getKey().getName());
               if (_property != null && _bs.getValue(_property) != null) {
                  try {
                     _bs = (BlockState)_bs.setValue((Property)_property, (Comparable)entry.getValue());
                  } catch (Exception var16) {
                  }
               }
            }

            world.setBlock(_bp, _bs, 3);
         }

         if (world.getBlockState(BlockPos.containing(x + 1.0, y - 1.0, z - 1.0)).getBlock() != Blocks.AIR
            && world.getBlockState(BlockPos.containing(x + 1.0, y - 1.0, z - 1.0)).getBlock() != Blocks.WATER
            && world.getBlockState(BlockPos.containing(x + 1.0, y - 1.0, z - 1.0)).getBlock() != Blocks.WATER
            && world.getBlockState(BlockPos.containing(x + 1.0, y - 1.0, z - 1.0)).getBlock() != Blocks.LAVA
            && world.getBlockState(BlockPos.containing(x + 1.0, y - 1.0, z - 1.0)).getBlock() != Blocks.LAVA) {
            _bp = BlockPos.containing(x + 1.0, y - 1.0, z - 1.0);
            _bs = ((Block)EternullModBlocks.NULLBLOCK.get()).defaultBlockState();
            _bso = world.getBlockState(_bp);
            var10 = _bso.getValues().entrySet().iterator();

            while (var10.hasNext()) {
               Entry<Property<?>, Comparable<?>> entry = (Entry<Property<?>, Comparable<?>>)var10.next();
               Property _property = _bs.getBlock().getStateDefinition().getProperty(entry.getKey().getName());
               if (_property != null && _bs.getValue(_property) != null) {
                  try {
                     _bs = (BlockState)_bs.setValue((Property)_property, (Comparable)entry.getValue());
                  } catch (Exception var15) {
                  }
               }
            }

            world.setBlock(_bp, _bs, 3);
         }

         if (world.getBlockState(BlockPos.containing(x - 1.0, y - 1.0, z + 1.0)).getBlock() != Blocks.AIR
            && world.getBlockState(BlockPos.containing(x - 1.0, y - 1.0, z + 1.0)).getBlock() != Blocks.WATER
            && world.getBlockState(BlockPos.containing(x - 1.0, y - 1.0, z + 1.0)).getBlock() != Blocks.WATER
            && world.getBlockState(BlockPos.containing(x - 1.0, y - 1.0, z + 1.0)).getBlock() != Blocks.LAVA
            && world.getBlockState(BlockPos.containing(x - 1.0, y - 1.0, z + 1.0)).getBlock() != Blocks.LAVA) {
            _bp = BlockPos.containing(x - 1.0, y - 1.0, z + 1.0);
            _bs = ((Block)EternullModBlocks.NULLBLOCK.get()).defaultBlockState();
            _bso = world.getBlockState(_bp);
            var10 = _bso.getValues().entrySet().iterator();

            while (var10.hasNext()) {
               Entry<Property<?>, Comparable<?>> entry = (Entry<Property<?>, Comparable<?>>)var10.next();
               Property _property = _bs.getBlock().getStateDefinition().getProperty(entry.getKey().getName());
               if (_property != null && _bs.getValue(_property) != null) {
                  try {
                     _bs = (BlockState)_bs.setValue((Property)_property, (Comparable)entry.getValue());
                  } catch (Exception var14) {
                  }
               }
            }

            world.setBlock(_bp, _bs, 3);
         }
      }
   }
}
