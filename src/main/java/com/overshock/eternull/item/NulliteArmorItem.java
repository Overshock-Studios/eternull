package com.overshock.eternull.item;

import com.overshock.eternull.init.EternullModItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ArmorItem.Type;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

public abstract class NulliteArmorItem extends ArmorItem {
   public NulliteArmorItem(Type type, Properties properties) {
      super(new ArmorMaterial() {
         public int getDurabilityForType(Type type) {
            return new int[]{13, 15, 16, 11}[type.getSlot().getIndex()] * 24;
         }

         public int getDefenseForType(Type type) {
            return new int[]{3, 7, 8, 3}[type.getSlot().getIndex()];
         }

         public int getEnchantmentValue() {
            return 14;
         }

         public SoundEvent getEquipSound() {
            return (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(new ResourceLocation("item.armor.equip_netherite"));
         }

         public Ingredient getRepairIngredient() {
            return Ingredient.of(new ItemStack[]{new ItemStack((ItemLike)EternullModItems.NULLITE.get())});
         }

         public String getName() {
            return "nullite_armor";
         }

         public float getToughness() {
            return 1.0F;
         }

         public float getKnockbackResistance() {
            return 0.0F;
         }
      }, type, properties);
   }

   public static class Boots extends NulliteArmorItem {
      public Boots() {
         super(Type.BOOTS, new Properties().fireResistant());
      }

      public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
         return "eternull:textures/models/armor/nullite_layer_1.png";
      }
   }

   public static class Chestplate extends NulliteArmorItem {
      public Chestplate() {
         super(Type.CHESTPLATE, new Properties().fireResistant());
      }

      public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
         return "eternull:textures/models/armor/nullite_layer_1.png";
      }
   }

   public static class Helmet extends NulliteArmorItem {
      public Helmet() {
         super(Type.HELMET, new Properties().fireResistant());
      }

      public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
         return "eternull:textures/models/armor/nullite_layer_1.png";
      }
   }

   public static class Leggings extends NulliteArmorItem {
      public Leggings() {
         super(Type.LEGGINGS, new Properties().fireResistant());
      }

      public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
         return "eternull:textures/models/armor/nullite_layer_2.png";
      }
   }
}
