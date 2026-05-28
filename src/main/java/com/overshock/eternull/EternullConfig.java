package com.overshock.eternull;

import net.neoforged.neoforge.common.ModConfigSpec;

public final class EternullConfig {
   public static final ModConfigSpec SERVER_SPEC;
   private static final ModConfigSpec.BooleanValue ENABLE_CORRUPTION_SPREAD;
   private static final ModConfigSpec.BooleanValue CORRUPTION_SPREADS_ONLY_AT_NIGHT;
   private static final ModConfigSpec.IntValue NULL_BLOCK_SPREAD_CHANCE;
   private static final ModConfigSpec.IntValue NULL_BLOCK_DORMANCY_CHANCE;
   private static final ModConfigSpec.IntValue NULL_MOB_FOOTPRINT_CHANCE;
   private static final ModConfigSpec.IntValue NULL_MOB_FOOTPRINT_MAX_BLOCKS;
   private static final ModConfigSpec.BooleanValue MOB_CORRUPTION_CONVERSION;
   private static final ModConfigSpec.IntValue MOB_CORRUPTION_EXPOSURE_TICKS;
   private static final ModConfigSpec.IntValue MOB_CORRUPTION_CONVERSION_CHANCE;
   private static final ModConfigSpec.BooleanValue AMBIENT_GLITCHES;
   private static final ModConfigSpec.IntValue PLAYER_CORRUPTION_GLITCH_CHANCE;

   static {
      ModConfigSpec.Builder builder = new ModConfigSpec.Builder();
      builder.push("corruption");
      ENABLE_CORRUPTION_SPREAD = builder
         .comment("Allow Null blocks and Null mobs to spread corruption by converting nearby blocks.")
         .define("enableCorruptionSpread", true);
      CORRUPTION_SPREADS_ONLY_AT_NIGHT = builder
         .comment("When true, active corruption spreads only at night or when the block cannot see the sky.")
         .define("corruptionSpreadsOnlyAtNight", true);
      NULL_BLOCK_SPREAD_CHANCE = builder
         .comment("Percent chance for an active Null block random tick to spread into one nearby valid block.")
         .defineInRange("nullBlockSpreadChance", 30, 0, 100);
      NULL_BLOCK_DORMANCY_CHANCE = builder
         .comment("Percent chance for an active Null block to become dormant when it fails to spread.")
         .defineInRange("nullBlockDormancyChance", 8, 0, 100);
      NULL_MOB_FOOTPRINT_CHANCE = builder
         .comment("Percent chance per Null mob tick to corrupt nearby floor blocks.")
         .defineInRange("nullMobFootprintChance", 10, 0, 100);
      NULL_MOB_FOOTPRINT_MAX_BLOCKS = builder
         .comment("Maximum nearby blocks a Null mob can corrupt during one footprint tick.")
         .defineInRange("nullMobFootprintMaxBlocks", 2, 0, 9);
      builder.pop();

      builder.push("entities");
      MOB_CORRUPTION_CONVERSION = builder
         .comment("Allow vanilla mobs standing in active corruption to convert into corrupted variants when one exists.")
         .define("mobCorruptionConversion", true);
      MOB_CORRUPTION_EXPOSURE_TICKS = builder
         .comment("Ticks a vanilla mob must stay exposed to active corruption before it can convert.")
         .defineInRange("mobCorruptionExposureTicks", 80, 20, 1200);
      MOB_CORRUPTION_CONVERSION_CHANCE = builder
         .comment("Percent chance for exposed eligible mobs to convert on each conversion check.")
         .defineInRange("mobCorruptionConversionChance", 35, 0, 100);
      builder.pop();

      builder.push("horror");
      AMBIENT_GLITCHES = builder
         .comment("Allow subtle particles and sound stutters near active corruption.")
         .define("ambientGlitches", true);
      PLAYER_CORRUPTION_GLITCH_CHANCE = builder
         .comment("Percent chance per player check for a small corruption glitch when standing on or near active corruption.")
         .defineInRange("playerCorruptionGlitchChance", 3, 0, 100);
      builder.pop();
      SERVER_SPEC = builder.build();
   }

   private EternullConfig() {
   }

   public static boolean isCorruptionSpreadEnabled() {
      return ENABLE_CORRUPTION_SPREAD.getAsBoolean();
   }

   public static boolean corruptionSpreadsOnlyAtNight() {
      return CORRUPTION_SPREADS_ONLY_AT_NIGHT.getAsBoolean();
   }

   public static int nullBlockSpreadChance() {
      return NULL_BLOCK_SPREAD_CHANCE.get();
   }

   public static int nullBlockDormancyChance() {
      return NULL_BLOCK_DORMANCY_CHANCE.get();
   }

   public static int nullMobFootprintChance() {
      return NULL_MOB_FOOTPRINT_CHANCE.get();
   }

   public static int nullMobFootprintMaxBlocks() {
      return NULL_MOB_FOOTPRINT_MAX_BLOCKS.get();
   }

   public static boolean mobCorruptionConversionEnabled() {
      return MOB_CORRUPTION_CONVERSION.getAsBoolean();
   }

   public static int mobCorruptionExposureTicks() {
      return MOB_CORRUPTION_EXPOSURE_TICKS.get();
   }

   public static int mobCorruptionConversionChance() {
      return MOB_CORRUPTION_CONVERSION_CHANCE.get();
   }

   public static boolean ambientGlitchesEnabled() {
      return AMBIENT_GLITCHES.getAsBoolean();
   }

   public static int playerCorruptionGlitchChance() {
      return PLAYER_CORRUPTION_GLITCH_CHANCE.get();
   }
}
