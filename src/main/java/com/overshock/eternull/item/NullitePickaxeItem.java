package com.overshock.eternull.item;

import com.overshock.eternull.init.EternullModItems;
import com.overshock.eternull.procedures.NulliteSwordLivingEntityIsHitWithToolProcedure;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

public class NullitePickaxeItem extends PickaxeItem {
   public NullitePickaxeItem() {
      super(new Tier() {
         public int getUses() {
            return 483;
         }

         public float getSpeed() {
            return 10.0F;
         }

         public float getAttackDamageBonus() {
            return 3.0F;
         }

         public int getLevel() {
            return 3;
         }

         public int getEnchantmentValue() {
            return 22;
         }

         public Ingredient getRepairIngredient() {
            return Ingredient.of(new ItemStack[]{new ItemStack((ItemLike)EternullModItems.NULLITE.get())});
         }
      }, 1, -2.6F, new Properties());
   }

   public boolean hurtEnemy(ItemStack itemstack, LivingEntity entity, LivingEntity sourceentity) {
      boolean retval = super.hurtEnemy(itemstack, entity, sourceentity);
      NulliteSwordLivingEntityIsHitWithToolProcedure.execute(entity);
      return retval;
   }
}
