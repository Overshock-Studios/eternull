package net.mcreator.eternull.init;

import net.mcreator.eternull.block.DarkButtonBlock;
import net.mcreator.eternull.block.DarkFenceBlock;
import net.mcreator.eternull.block.DarkFenceGateBlock;
import net.mcreator.eternull.block.DarkLeavesBlock;
import net.mcreator.eternull.block.DarkLogBlock;
import net.mcreator.eternull.block.DarkPlanksBlock;
import net.mcreator.eternull.block.DarkPressurePlateBlock;
import net.mcreator.eternull.block.DarkSlabBlock;
import net.mcreator.eternull.block.DarkStairsBlock;
import net.mcreator.eternull.block.DarkWoodBlock;
import net.mcreator.eternull.block.DormantNullBlockBlock;
import net.mcreator.eternull.block.NullblockBlock;
import net.mcreator.eternull.block.NulliteBlockBlock;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class EternullModBlocks {
   public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(BuiltInRegistries.BLOCK, "eternull");
   public static final DeferredHolder<Block, Block> NULLBLOCK = REGISTRY.register("nullblock", () -> new NullblockBlock());
   public static final DeferredHolder<Block, Block> DARK_WOOD = REGISTRY.register("dark_wood", () -> new DarkWoodBlock());
   public static final DeferredHolder<Block, Block> DARK_LEAVES = REGISTRY.register("dark_leaves", () -> new DarkLeavesBlock());
   public static final DeferredHolder<Block, Block> DARK_LOG = REGISTRY.register("dark_log", () -> new DarkLogBlock());
   public static final DeferredHolder<Block, Block> DARK_PLANKS = REGISTRY.register("dark_planks", () -> new DarkPlanksBlock());
   public static final DeferredHolder<Block, Block> DARK_STAIRS = REGISTRY.register("dark_stairs", () -> new DarkStairsBlock());
   public static final DeferredHolder<Block, Block> DARK_SLAB = REGISTRY.register("dark_slab", () -> new DarkSlabBlock());
   public static final DeferredHolder<Block, Block> DARK_FENCE = REGISTRY.register("dark_fence", () -> new DarkFenceBlock());
   public static final DeferredHolder<Block, Block> DARK_FENCE_GATE = REGISTRY.register("dark_fence_gate", () -> new DarkFenceGateBlock());
   public static final DeferredHolder<Block, Block> DARK_PRESSURE_PLATE = REGISTRY.register("dark_pressure_plate", () -> new DarkPressurePlateBlock());
   public static final DeferredHolder<Block, Block> DARK_BUTTON = REGISTRY.register("dark_button", () -> new DarkButtonBlock());
   public static final DeferredHolder<Block, Block> NULLITE_BLOCK = REGISTRY.register("nullite_block", () -> new NulliteBlockBlock());
   public static final DeferredHolder<Block, Block> DORMANT_NULL_BLOCK = REGISTRY.register("dormant_null_block", () -> new DormantNullBlockBlock());
}
