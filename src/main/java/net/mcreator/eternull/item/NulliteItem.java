package net.mcreator.eternull.item;

import java.util.List;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.level.Level;

public class NulliteItem extends Item {
   public NulliteItem() {
      super(new Properties().stacksTo(64).fireResistant().rarity(Rarity.EPIC));
   }

   public void appendHoverText(ItemStack itemstack, Level level, List<Component> list, TooltipFlag flag) {
      super.appendHoverText(itemstack, level, list, flag);
      list.add(Component.literal("Use with Dark Core to upgrade Iron tools/armor to Nullite in smithing table!"));
   }
}
