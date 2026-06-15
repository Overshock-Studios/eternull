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
   private static final ModConfigSpec.IntValue NULL_WARD_RADIUS;
   private static final ModConfigSpec.BooleanValue REQUIRE_NULL_HEART_FOR_SPREAD;
   private static final ModConfigSpec.IntValue NULL_HEART_RADIUS;
   private static final ModConfigSpec.IntValue NULL_HEART_SPREAD_BONUS;
   private static final ModConfigSpec.BooleanValue MOB_CORRUPTION_CONVERSION;
   private static final ModConfigSpec.IntValue MOB_CORRUPTION_EXPOSURE_TICKS;
   private static final ModConfigSpec.IntValue MOB_CORRUPTION_CONVERSION_CHANCE;
   private static final ModConfigSpec.IntValue NULLITE_ARMOR_REFLECTION_COOLDOWN;
   private static final ModConfigSpec.BooleanValue AMBIENT_GLITCHES;
   private static final ModConfigSpec.IntValue PLAYER_CORRUPTION_GLITCH_CHANCE;
   private static final ModConfigSpec.BooleanValue AUDITORY_MIMICRY;
   private static final ModConfigSpec.IntValue AUDITORY_MIMICRY_CHANCE;
   private static final ModConfigSpec.IntValue AUDITORY_MIMICRY_RADIUS;

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
         .defineInRange("nullBlockSpreadChance", 55, 0, 100);
      NULL_BLOCK_DORMANCY_CHANCE = builder
         .comment("Percent chance for an active Null block to become dormant when it fails to spread.")
         .defineInRange("nullBlockDormancyChance", 8, 0, 100);
      NULL_MOB_FOOTPRINT_CHANCE = builder
         .comment("Percent chance per Null mob tick to corrupt nearby floor blocks.")
         .defineInRange("nullMobFootprintChance", 10, 0, 100);
      NULL_MOB_FOOTPRINT_MAX_BLOCKS = builder
         .comment("Maximum nearby blocks a Null mob can corrupt during one footprint tick.")
         .defineInRange("nullMobFootprintMaxBlocks", 2, 0, 9);
      NULL_WARD_RADIUS = builder
         .comment("Radius in blocks where a Null Ward prevents new corruption spread.")
         .defineInRange("nullWardRadius", 5, 0, 32);
      REQUIRE_NULL_HEART_FOR_SPREAD = builder
         .comment("When true, active corruption must be within a Null Heart radius to spread or apply major corruption effects.")
         .define("requireNullHeartForSpread", true);
      NULL_HEART_RADIUS = builder
         .comment("Radius in blocks where a Null Heart empowers corruption.")
         .defineInRange("nullHeartRadius", 160, 1, 512);
      NULL_HEART_SPREAD_BONUS = builder
         .comment("Extra spread chance added to active Null blocks within a Null Heart radius.")
         .defineInRange("nullHeartSpreadBonus", 40, 0, 100);
      builder.pop();

      builder.push("entities");
      MOB_CORRUPTION_CONVERSION = builder
         .comment("Allow vanilla mobs standing in active corruption to convert into corrupted variants when one exists.")
         .define("mobCorruptionConversion", true);
      MOB_CORRUPTION_EXPOSURE_TICKS = builder
         .comment("Ticks a vanilla mob must stay exposed to active corruption before it can convert.")
         .defineInRange("mobCorruptionExposureTicks", 60, 20, 1200);
      MOB_CORRUPTION_CONVERSION_CHANCE = builder
         .comment("Percent chance for exposed eligible mobs to convert on each conversion check.")
         .defineInRange("mobCorruptionConversionChance", 70, 0, 100);
      NULLITE_ARMOR_REFLECTION_COOLDOWN = builder
         .comment("Ticks between Nullite armor Wither reflection triggers per wearer.")
         .defineInRange("nulliteArmorReflectionCooldown", 40, 0, 1200);
      builder.pop();

      builder.push("horror");
      AMBIENT_GLITCHES = builder
         .comment("Allow subtle particles and sound stutters near active corruption.")
         .define("ambientGlitches", true);
      PLAYER_CORRUPTION_GLITCH_CHANCE = builder
         .comment("Percent chance per player check for a small corruption glitch when standing on or near active corruption.")
         .defineInRange("playerCorruptionGlitchChance", 3, 0, 100);
      AUDITORY_MIMICRY = builder
         .comment("Allow false ambient sounds (chest, pickaxe, footsteps) to play near active Null Hearts.")
         .define("auditoryMimicry", true);
      AUDITORY_MIMICRY_CHANCE = builder
         .comment("Percent chance per player check for an auditory mimicry sound near a Null Heart.")
         .defineInRange("auditoryMimicryChance", 2, 0, 100);
      AUDITORY_MIMICRY_RADIUS = builder
         .comment("Radius in blocks around a Null Heart where auditory mimicry can occur.")
         .defineInRange("auditoryMimicryRadius", 96, 8, 256);
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

   public static int nullWardRadius() {
      return NULL_WARD_RADIUS.get();
   }

   public static boolean requireNullHeartForSpread() {
      return REQUIRE_NULL_HEART_FOR_SPREAD.getAsBoolean();
   }

   public static int nullHeartRadius() {
      return NULL_HEART_RADIUS.get();
   }

   public static int nullHeartSpreadBonus() {
      return NULL_HEART_SPREAD_BONUS.get();
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

   public static int nulliteArmorReflectionCooldown() {
      return NULLITE_ARMOR_REFLECTION_COOLDOWN.get();
   }

   public static boolean ambientGlitchesEnabled() {
      return AMBIENT_GLITCHES.getAsBoolean();
   }

   public static int playerCorruptionGlitchChance() {
      return PLAYER_CORRUPTION_GLITCH_CHANCE.get();
   }

   public static boolean auditoryMimicryEnabled() {
      return AUDITORY_MIMICRY.getAsBoolean();
   }

   public static int auditoryMimicryChance() {
      return AUDITORY_MIMICRY_CHANCE.get();
   }

   public static int auditoryMimicryRadius() {
      return AUDITORY_MIMICRY_RADIUS.get();
   }
}
