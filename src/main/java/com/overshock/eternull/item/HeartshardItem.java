package com.overshock.eternull.item;

import java.util.List;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class HeartshardItem extends Item {
   public HeartshardItem() {
      super(new Properties().stacksTo(16).rarity(Rarity.EPIC).fireResistant());
   }

   @Override
   public void appendHoverText(ItemStack stack, Level level, List<Component> tooltip, TooltipFlag flag) {
      tooltip.add(Component.literal("A fragment of a slain Null Heart.").withStyle(ChatFormatting.DARK_PURPLE));
      tooltip.add(Component.literal("It still beats faintly.").withStyle(ChatFormatting.GRAY, ChatFormatting.ITALIC));
   }
}
