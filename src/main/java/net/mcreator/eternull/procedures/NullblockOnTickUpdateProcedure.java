package net.mcreator.eternull.procedures;

import com.google.common.collect.UnmodifiableIterator;
import java.util.Map.Entry;
import net.mcreator.eternull.EternullConfig;
import net.mcreator.eternull.init.EternullModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;

public class NullblockOnTickUpdateProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z) {
      if (!EternullConfig.isCorruptionSpreadEnabled()) {
         return;
      }

      double randInt = 0.0;
      if (!(world instanceof Level _lvl0 && _lvl0.isDay() && world.canSeeSkyFromBelowWater(BlockPos.containing(x, y, z)))) {
         randInt = Mth.nextInt(RandomSource.create(), 1, 100);
         if (90.0 >= randInt) {
            if (world.getBlockState(BlockPos.containing(x + 1.0, y, z)).getBlock() != EternullModBlocks.DORMANT_NULL_BLOCK.get()
               && world.getBlockState(BlockPos.containing(x + 1.0, y, z)).getBlock() != EternullModBlocks.NULLBLOCK.get()
               && world.getBlockState(BlockPos.containing(x + 1.0, y + 1.0, z)).getBlock() == Blocks.AIR
               && world.getBlockState(BlockPos.containing(x + 1.0, y, z)).getBlock() != Blocks.AIR) {
               BlockPos _bp = BlockPos.containing(x + 1.0, y, z);
               BlockState _bs = ((Block)EternullModBlocks.NULLBLOCK.get()).defaultBlockState();
               BlockState _bso = world.getBlockState(_bp);
               UnmodifiableIterator var72 = _bso.getValues().entrySet().iterator();

               while (var72.hasNext()) {
                  Entry<Property<?>, Comparable<?>> entry = (Entry<Property<?>, Comparable<?>>)var72.next();
                  Property _property = _bs.getBlock().getStateDefinition().getProperty(entry.getKey().getName());
                  if (_property != null && _bs.getValue(_property) != null) {
                     try {
                        _bs = (BlockState)_bs.setValue((Property)_property, (Comparable)entry.getValue());
                     } catch (Exception var28) {
                     }
                  }
               }

               world.setBlock(_bp, _bs, 3);
            } else if (world.getBlockState(BlockPos.containing(x + 1.0, y + 1.0, z)).getBlock() != EternullModBlocks.DORMANT_NULL_BLOCK.get()
               && world.getBlockState(BlockPos.containing(x + 1.0, y, z)).getBlock() != EternullModBlocks.NULLBLOCK.get()
               && world.getBlockState(BlockPos.containing(x + 1.0, y + 1.0, z)).getBlock() != Blocks.AIR
               && world.getBlockState(BlockPos.containing(x + 1.0, y, z)).getBlock() != Blocks.AIR) {
               BlockPos _bp = BlockPos.containing(x + 1.0, y + 1.0, z);
               BlockState _bs = ((Block)EternullModBlocks.NULLBLOCK.get()).defaultBlockState();
               BlockState _bso = world.getBlockState(_bp);
               UnmodifiableIterator var71 = _bso.getValues().entrySet().iterator();

               while (var71.hasNext()) {
                  Entry<Property<?>, Comparable<?>> entry = (Entry<Property<?>, Comparable<?>>)var71.next();
                  Property _property = _bs.getBlock().getStateDefinition().getProperty(entry.getKey().getName());
                  if (_property != null && _bs.getValue(_property) != null) {
                     try {
                        _bs = (BlockState)_bs.setValue((Property)_property, (Comparable)entry.getValue());
                     } catch (Exception var27) {
                     }
                  }
               }

               world.setBlock(_bp, _bs, 3);
            } else if (world.getBlockState(BlockPos.containing(x - 1.0, y, z)).getBlock() != EternullModBlocks.DORMANT_NULL_BLOCK.get()
               && world.getBlockState(BlockPos.containing(x - 1.0, y, z)).getBlock() != EternullModBlocks.NULLBLOCK.get()
               && world.getBlockState(BlockPos.containing(x - 1.0, y + 1.0, z)).getBlock() == Blocks.AIR
               && world.getBlockState(BlockPos.containing(x - 1.0, y, z)).getBlock() != Blocks.AIR) {
               BlockPos _bp = BlockPos.containing(x - 1.0, y, z);
               BlockState _bs = ((Block)EternullModBlocks.NULLBLOCK.get()).defaultBlockState();
               BlockState _bso = world.getBlockState(_bp);
               UnmodifiableIterator var70 = _bso.getValues().entrySet().iterator();

               while (var70.hasNext()) {
                  Entry<Property<?>, Comparable<?>> entry = (Entry<Property<?>, Comparable<?>>)var70.next();
                  Property _property = _bs.getBlock().getStateDefinition().getProperty(entry.getKey().getName());
                  if (_property != null && _bs.getValue(_property) != null) {
                     try {
                        _bs = (BlockState)_bs.setValue((Property)_property, (Comparable)entry.getValue());
                     } catch (Exception var26) {
                     }
                  }
               }

               world.setBlock(_bp, _bs, 3);
            } else if (world.getBlockState(BlockPos.containing(x - 1.0, y + 1.0, z)).getBlock() != EternullModBlocks.DORMANT_NULL_BLOCK.get()
               && world.getBlockState(BlockPos.containing(x - 1.0, y, z)).getBlock() != EternullModBlocks.NULLBLOCK.get()
               && world.getBlockState(BlockPos.containing(x - 1.0, y + 1.0, z)).getBlock() != Blocks.AIR
               && world.getBlockState(BlockPos.containing(x - 1.0, y, z)).getBlock() != Blocks.AIR) {
               BlockPos _bp = BlockPos.containing(x - 1.0, y + 1.0, z);
               BlockState _bs = ((Block)EternullModBlocks.NULLBLOCK.get()).defaultBlockState();
               BlockState _bso = world.getBlockState(_bp);
               UnmodifiableIterator var69 = _bso.getValues().entrySet().iterator();

               while (var69.hasNext()) {
                  Entry<Property<?>, Comparable<?>> entry = (Entry<Property<?>, Comparable<?>>)var69.next();
                  Property _property = _bs.getBlock().getStateDefinition().getProperty(entry.getKey().getName());
                  if (_property != null && _bs.getValue(_property) != null) {
                     try {
                        _bs = (BlockState)_bs.setValue((Property)_property, (Comparable)entry.getValue());
                     } catch (Exception var25) {
                     }
                  }
               }

               world.setBlock(_bp, _bs, 3);
            } else if (world.getBlockState(BlockPos.containing(x, y, z - 1.0)).getBlock() != EternullModBlocks.DORMANT_NULL_BLOCK.get()
               && world.getBlockState(BlockPos.containing(x, y, z - 1.0)).getBlock() != EternullModBlocks.NULLBLOCK.get()
               && world.getBlockState(BlockPos.containing(x, y + 1.0, z - 1.0)).getBlock() == Blocks.AIR
               && world.getBlockState(BlockPos.containing(x, y, z - 1.0)).getBlock() != Blocks.AIR) {
               BlockPos _bp = BlockPos.containing(x, y, z - 1.0);
               BlockState _bs = ((Block)EternullModBlocks.NULLBLOCK.get()).defaultBlockState();
               BlockState _bso = world.getBlockState(_bp);
               UnmodifiableIterator var68 = _bso.getValues().entrySet().iterator();

               while (var68.hasNext()) {
                  Entry<Property<?>, Comparable<?>> entry = (Entry<Property<?>, Comparable<?>>)var68.next();
                  Property _property = _bs.getBlock().getStateDefinition().getProperty(entry.getKey().getName());
                  if (_property != null && _bs.getValue(_property) != null) {
                     try {
                        _bs = (BlockState)_bs.setValue((Property)_property, (Comparable)entry.getValue());
                     } catch (Exception var24) {
                     }
                  }
               }

               world.setBlock(_bp, _bs, 3);
            } else if (world.getBlockState(BlockPos.containing(x, y + 1.0, z - 1.0)).getBlock() != EternullModBlocks.DORMANT_NULL_BLOCK.get()
               && world.getBlockState(BlockPos.containing(x, y, z - 1.0)).getBlock() != EternullModBlocks.NULLBLOCK.get()
               && world.getBlockState(BlockPos.containing(x, y + 1.0, z - 1.0)).getBlock() != Blocks.AIR
               && world.getBlockState(BlockPos.containing(x, y, z - 1.0)).getBlock() != Blocks.AIR) {
               BlockPos _bp = BlockPos.containing(x, y + 1.0, z - 1.0);
               BlockState _bs = ((Block)EternullModBlocks.NULLBLOCK.get()).defaultBlockState();
               BlockState _bso = world.getBlockState(_bp);
               UnmodifiableIterator var67 = _bso.getValues().entrySet().iterator();

               while (var67.hasNext()) {
                  Entry<Property<?>, Comparable<?>> entry = (Entry<Property<?>, Comparable<?>>)var67.next();
                  Property _property = _bs.getBlock().getStateDefinition().getProperty(entry.getKey().getName());
                  if (_property != null && _bs.getValue(_property) != null) {
                     try {
                        _bs = (BlockState)_bs.setValue((Property)_property, (Comparable)entry.getValue());
                     } catch (Exception var23) {
                     }
                  }
               }

               world.setBlock(_bp, _bs, 3);
            } else if (world.getBlockState(BlockPos.containing(x, y, z + 1.0)).getBlock() != EternullModBlocks.DORMANT_NULL_BLOCK.get()
               && world.getBlockState(BlockPos.containing(x, y, z + 1.0)).getBlock() != EternullModBlocks.NULLBLOCK.get()
               && world.getBlockState(BlockPos.containing(x, y + 1.0, z + 1.0)).getBlock() == Blocks.AIR
               && world.getBlockState(BlockPos.containing(x, y, z + 1.0)).getBlock() != Blocks.AIR) {
               BlockPos _bp = BlockPos.containing(x, y, z + 1.0);
               BlockState _bs = ((Block)EternullModBlocks.NULLBLOCK.get()).defaultBlockState();
               BlockState _bso = world.getBlockState(_bp);
               UnmodifiableIterator var66 = _bso.getValues().entrySet().iterator();

               while (var66.hasNext()) {
                  Entry<Property<?>, Comparable<?>> entry = (Entry<Property<?>, Comparable<?>>)var66.next();
                  Property _property = _bs.getBlock().getStateDefinition().getProperty(entry.getKey().getName());
                  if (_property != null && _bs.getValue(_property) != null) {
                     try {
                        _bs = (BlockState)_bs.setValue((Property)_property, (Comparable)entry.getValue());
                     } catch (Exception var22) {
                     }
                  }
               }

               world.setBlock(_bp, _bs, 3);
            } else if (world.getBlockState(BlockPos.containing(x, y + 1.0, z + 1.0)).getBlock() != EternullModBlocks.DORMANT_NULL_BLOCK.get()
               && world.getBlockState(BlockPos.containing(x, y, z + 1.0)).getBlock() != EternullModBlocks.NULLBLOCK.get()
               && world.getBlockState(BlockPos.containing(x, y + 1.0, z + 1.0)).getBlock() != Blocks.AIR
               && world.getBlockState(BlockPos.containing(x, y, z + 1.0)).getBlock() != Blocks.AIR) {
               BlockPos _bp = BlockPos.containing(x, y + 1.0, z + 1.0);
               BlockState _bs = ((Block)EternullModBlocks.NULLBLOCK.get()).defaultBlockState();
               BlockState _bso = world.getBlockState(_bp);
               UnmodifiableIterator var65 = _bso.getValues().entrySet().iterator();

               while (var65.hasNext()) {
                  Entry<Property<?>, Comparable<?>> entry = (Entry<Property<?>, Comparable<?>>)var65.next();
                  Property _property = _bs.getBlock().getStateDefinition().getProperty(entry.getKey().getName());
                  if (_property != null && _bs.getValue(_property) != null) {
                     try {
                        _bs = (BlockState)_bs.setValue((Property)_property, (Comparable)entry.getValue());
                     } catch (Exception var21) {
                     }
                  }
               }

               world.setBlock(_bp, _bs, 3);
            } else if (world.getBlockState(BlockPos.containing(x, y - 1.0, z)).getBlock() != EternullModBlocks.DORMANT_NULL_BLOCK.get()
               && world.getBlockState(BlockPos.containing(x, y - 1.0, z)).getBlock() != EternullModBlocks.NULLBLOCK.get()
               && world.getBlockState(BlockPos.containing(x, y - 1.0, z)).getBlock() != Blocks.AIR) {
               BlockPos _bp = BlockPos.containing(x, y - 1.0, z);
               BlockState _bs = ((Block)EternullModBlocks.NULLBLOCK.get()).defaultBlockState();
               BlockState _bso = world.getBlockState(_bp);
               UnmodifiableIterator var64 = _bso.getValues().entrySet().iterator();

               while (var64.hasNext()) {
                  Entry<Property<?>, Comparable<?>> entry = (Entry<Property<?>, Comparable<?>>)var64.next();
                  Property _property = _bs.getBlock().getStateDefinition().getProperty(entry.getKey().getName());
                  if (_property != null && _bs.getValue(_property) != null) {
                     try {
                        _bs = (BlockState)_bs.setValue((Property)_property, (Comparable)entry.getValue());
                     } catch (Exception var20) {
                     }
                  }
               }

               world.setBlock(_bp, _bs, 3);
            } else if (world.getBlockState(BlockPos.containing(x, y + 1.0, z)).getBlock() != EternullModBlocks.DORMANT_NULL_BLOCK.get()
               && world.getBlockState(BlockPos.containing(x, y + 1.0, z)).getBlock() != EternullModBlocks.NULLBLOCK.get()
               && world.getBlockState(BlockPos.containing(x, y + 1.0, z)).getBlock() != Blocks.AIR) {
               BlockPos _bp = BlockPos.containing(x, y + 1.0, z);
               BlockState _bs = ((Block)EternullModBlocks.NULLBLOCK.get()).defaultBlockState();
               BlockState _bso = world.getBlockState(_bp);
               UnmodifiableIterator var63 = _bso.getValues().entrySet().iterator();

               while (var63.hasNext()) {
                  Entry<Property<?>, Comparable<?>> entry = (Entry<Property<?>, Comparable<?>>)var63.next();
                  Property _property = _bs.getBlock().getStateDefinition().getProperty(entry.getKey().getName());
                  if (_property != null && _bs.getValue(_property) != null) {
                     try {
                        _bs = (BlockState)_bs.setValue((Property)_property, (Comparable)entry.getValue());
                     } catch (Exception var19) {
                     }
                  }
               }

               world.setBlock(_bp, _bs, 3);
            } else {
               BlockPos _bp = BlockPos.containing(x, y, z);
               BlockState _bs = ((Block)EternullModBlocks.DORMANT_NULL_BLOCK.get()).defaultBlockState();
               BlockState _bso = world.getBlockState(_bp);
               UnmodifiableIterator var13 = _bso.getValues().entrySet().iterator();

               while (var13.hasNext()) {
                  Entry<Property<?>, Comparable<?>> entry = (Entry<Property<?>, Comparable<?>>)var13.next();
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

            if (50 >= Mth.nextInt(RandomSource.create(), 1, 100)) {
               execute(world, x, y, z);
            }
         } else {
            BlockPos _bp = BlockPos.containing(x, y, z);
            BlockState _bs = ((Block)EternullModBlocks.DORMANT_NULL_BLOCK.get()).defaultBlockState();
            BlockState _bso = world.getBlockState(_bp);
            UnmodifiableIterator var73 = _bso.getValues().entrySet().iterator();

            while (var73.hasNext()) {
               Entry<Property<?>, Comparable<?>> entry = (Entry<Property<?>, Comparable<?>>)var73.next();
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
      }
   }
}
