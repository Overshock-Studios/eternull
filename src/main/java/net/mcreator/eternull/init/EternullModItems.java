package net.mcreator.eternull.init;

import net.mcreator.eternull.item.DarkBoatItemItem;
import net.mcreator.eternull.item.DarkCoreFragmentItem;
import net.mcreator.eternull.item.DarkCoreItem;
import net.mcreator.eternull.item.NulliteArmorItem;
import net.mcreator.eternull.item.NulliteAxeItem;
import net.mcreator.eternull.item.NulliteHoeItem;
import net.mcreator.eternull.item.NulliteItem;
import net.mcreator.eternull.item.NullitePickaxeItem;
import net.mcreator.eternull.item.NulliteShovelItem;
import net.mcreator.eternull.item.NulliteSwordItem;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class EternullModItems {
   public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(BuiltInRegistries.ITEM, "eternull");
   public static final DeferredHolder<Item, Item> NULLBLOCK = block(EternullModBlocks.NULLBLOCK);
   public static final DeferredHolder<Item, Item> NULLMOB_SPAWN_EGG = REGISTRY.register(
      "nullmob_spawn_egg", () -> new DeferredSpawnEggItem(EternullModEntities.NULLMOB, -16777216, -13421773, new Properties())
   );
   public static final DeferredHolder<Item, Item> DARK_WOOD = block(EternullModBlocks.DARK_WOOD);
   public static final DeferredHolder<Item, Item> DARK_LEAVES = block(EternullModBlocks.DARK_LEAVES);
   public static final DeferredHolder<Item, Item> DARK_LOG = block(EternullModBlocks.DARK_LOG);
   public static final DeferredHolder<Item, Item> DARK_PLANKS = block(EternullModBlocks.DARK_PLANKS);
   public static final DeferredHolder<Item, Item> DARK_STAIRS = block(EternullModBlocks.DARK_STAIRS);
   public static final DeferredHolder<Item, Item> DARK_SLAB = block(EternullModBlocks.DARK_SLAB);
   public static final DeferredHolder<Item, Item> DARK_FENCE = block(EternullModBlocks.DARK_FENCE);
   public static final DeferredHolder<Item, Item> DARK_FENCE_GATE = block(EternullModBlocks.DARK_FENCE_GATE);
   public static final DeferredHolder<Item, Item> DARK_PRESSURE_PLATE = block(EternullModBlocks.DARK_PRESSURE_PLATE);
   public static final DeferredHolder<Item, Item> DARK_BUTTON = block(EternullModBlocks.DARK_BUTTON);
   public static final DeferredHolder<Item, Item> NULLITE = REGISTRY.register("nullite", () -> new NulliteItem());
   public static final DeferredHolder<Item, Item> NULLITE_BLOCK = block(EternullModBlocks.NULLITE_BLOCK);
   public static final DeferredHolder<Item, Item> NULLITE_PICKAXE = REGISTRY.register("nullite_pickaxe", () -> new NullitePickaxeItem());
   public static final DeferredHolder<Item, Item> NULLITE_AXE = REGISTRY.register("nullite_axe", () -> new NulliteAxeItem());
   public static final DeferredHolder<Item, Item> NULLITE_SWORD = REGISTRY.register("nullite_sword", () -> new NulliteSwordItem());
   public static final DeferredHolder<Item, Item> NULLITE_SHOVEL = REGISTRY.register("nullite_shovel", () -> new NulliteShovelItem());
   public static final DeferredHolder<Item, Item> NULLITE_HOE = REGISTRY.register("nullite_hoe", () -> new NulliteHoeItem());
   public static final DeferredHolder<Item, Item> NULLITE_ARMOR_HELMET = REGISTRY.register("nullite_armor_helmet", () -> new NulliteArmorItem.Helmet());
   public static final DeferredHolder<Item, Item> NULLITE_ARMOR_CHESTPLATE = REGISTRY.register(
      "nullite_armor_chestplate", () -> new NulliteArmorItem.Chestplate()
   );
   public static final DeferredHolder<Item, Item> NULLITE_ARMOR_LEGGINGS = REGISTRY.register("nullite_armor_leggings", () -> new NulliteArmorItem.Leggings());
   public static final DeferredHolder<Item, Item> NULLITE_ARMOR_BOOTS = REGISTRY.register("nullite_armor_boots", () -> new NulliteArmorItem.Boots());
   public static final DeferredHolder<Item, Item> CORRUPTED_CREEPER_SPAWN_EGG = REGISTRY.register(
      "corrupted_creeper_spawn_egg", () -> new DeferredSpawnEggItem(EternullModEntities.CORRUPTED_CREEPER, -16751104, -1, new Properties())
   );
   public static final DeferredHolder<Item, Item> CORRUPTED_CHARGED_CREEPER_SPAWN_EGG = REGISTRY.register(
      "corrupted_charged_creeper_spawn_egg", () -> new DeferredSpawnEggItem(EternullModEntities.CORRUPTED_CHARGED_CREEPER, -16751104, -1, new Properties())
   );
   public static final DeferredHolder<Item, Item> CORRUPTED_ZOMBIE_SPAWN_EGG = REGISTRY.register(
      "corrupted_zombie_spawn_egg", () -> new DeferredSpawnEggItem(EternullModEntities.CORRUPTED_ZOMBIE, -16761600, -6710887, new Properties())
   );
   public static final DeferredHolder<Item, Item> CORRUPTED_SPIDER_SPAWN_EGG = REGISTRY.register(
      "corrupted_spider_spawn_egg", () -> new DeferredSpawnEggItem(EternullModEntities.CORRUPTED_SPIDER, -16777216, -6710887, new Properties())
   );
   public static final DeferredHolder<Item, Item> DARK_BOAT_ITEM = REGISTRY.register("dark_boat_item", () -> new DarkBoatItemItem());
   public static final DeferredHolder<Item, Item> DORMANT_NULL_BLOCK = block(EternullModBlocks.DORMANT_NULL_BLOCK);
   public static final DeferredHolder<Item, Item> DARK_CORE_FRAGMENT = REGISTRY.register("dark_core_fragment", () -> new DarkCoreFragmentItem());
   public static final DeferredHolder<Item, Item> DARK_CORE = REGISTRY.register("dark_core", () -> new DarkCoreItem());

   public static void register(IEventBus bus) {
      REGISTRY.register(bus);
   }

   private static DeferredHolder<Item, Item> block(DeferredHolder<Block, Block> block) {
      return REGISTRY.register(block.getId().getPath(), () -> new BlockItem((Block)block.get(), new Properties()));
   }
}
