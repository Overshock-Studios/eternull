package net.mcreator.eternull.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod.EventBusSubscriber;
import net.neoforged.fml.common.Mod.EventBusSubscriber.Bus;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

@EventBusSubscriber(bus = Bus.MOD)
public class EternullModTabs {
   public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, "eternull");

   @SubscribeEvent
   public static void buildTabContentsVanilla(BuildCreativeModeTabContentsEvent tabData) {
      if (tabData.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
         tabData.accept(((Block)EternullModBlocks.NULLBLOCK.get()).asItem());
         tabData.accept(((Block)EternullModBlocks.DARK_WOOD.get()).asItem());
         tabData.accept(((Block)EternullModBlocks.DARK_LOG.get()).asItem());
         tabData.accept(((Block)EternullModBlocks.DARK_PLANKS.get()).asItem());
         tabData.accept(((Block)EternullModBlocks.DARK_STAIRS.get()).asItem());
         tabData.accept(((Block)EternullModBlocks.DARK_SLAB.get()).asItem());
         tabData.accept(((Block)EternullModBlocks.DARK_BUTTON.get()).asItem());
         tabData.accept(((Block)EternullModBlocks.NULLITE_BLOCK.get()).asItem());
         tabData.accept(((Block)EternullModBlocks.DORMANT_NULL_BLOCK.get()).asItem());
      } else if (tabData.getTabKey() == CreativeModeTabs.REDSTONE_BLOCKS) {
         tabData.accept(((Block)EternullModBlocks.DARK_FENCE_GATE.get()).asItem());
         tabData.accept(((Block)EternullModBlocks.DARK_PRESSURE_PLATE.get()).asItem());
      } else if (tabData.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
         tabData.accept((ItemLike)EternullModItems.DARK_BOAT_ITEM.get());
      } else if (tabData.getTabKey() == CreativeModeTabs.COMBAT) {
         tabData.accept((ItemLike)EternullModItems.NULLITE_SWORD.get());
         tabData.accept((ItemLike)EternullModItems.NULLITE_ARMOR_HELMET.get());
         tabData.accept((ItemLike)EternullModItems.NULLITE_ARMOR_CHESTPLATE.get());
         tabData.accept((ItemLike)EternullModItems.NULLITE_ARMOR_LEGGINGS.get());
         tabData.accept((ItemLike)EternullModItems.NULLITE_ARMOR_BOOTS.get());
      } else if (tabData.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {
         tabData.accept((ItemLike)EternullModItems.NULLMOB_SPAWN_EGG.get());
         tabData.accept((ItemLike)EternullModItems.NULLITE.get());
         tabData.accept((ItemLike)EternullModItems.CORRUPTED_CREEPER_SPAWN_EGG.get());
         tabData.accept((ItemLike)EternullModItems.CORRUPTED_CHARGED_CREEPER_SPAWN_EGG.get());
         tabData.accept((ItemLike)EternullModItems.CORRUPTED_ZOMBIE_SPAWN_EGG.get());
         tabData.accept((ItemLike)EternullModItems.CORRUPTED_SPIDER_SPAWN_EGG.get());
      } else if (tabData.getTabKey() == CreativeModeTabs.INGREDIENTS) {
         tabData.accept((ItemLike)EternullModItems.DARK_CORE_FRAGMENT.get());
         tabData.accept((ItemLike)EternullModItems.DARK_CORE.get());
      } else if (tabData.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS) {
         tabData.accept(((Block)EternullModBlocks.DARK_LEAVES.get()).asItem());
         tabData.accept(((Block)EternullModBlocks.DARK_FENCE.get()).asItem());
      } else if (tabData.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
         tabData.accept((ItemLike)EternullModItems.NULLITE_PICKAXE.get());
         tabData.accept((ItemLike)EternullModItems.NULLITE_AXE.get());
         tabData.accept((ItemLike)EternullModItems.NULLITE_SHOVEL.get());
         tabData.accept((ItemLike)EternullModItems.NULLITE_HOE.get());
      }
   }
}
