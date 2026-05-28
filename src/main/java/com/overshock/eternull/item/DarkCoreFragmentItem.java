package com.overshock.eternull.item;

import java.util.List;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.level.Level;

public class DarkCoreFragmentItem extends Item {
   public DarkCoreFragmentItem() {
      super(new Properties().stacksTo(64).rarity(Rarity.UNCOMMON));
   }

   public void appendHoverText(ItemStack itemstack, Level level, List<Component> list, TooltipFlag flag) {
      super.appendHoverText(itemstack, level, list, flag);
      list.add(Component.literal("Combine 9 to craft a Dark Core!"));
   }
}
