package net.mcreator.eternull;

import net.neoforged.neoforge.common.ModConfigSpec;

public final class EternullConfig {
   public static final ModConfigSpec SERVER_SPEC;
   private static final ModConfigSpec.BooleanValue ENABLE_CORRUPTION_SPREAD;

   static {
      ModConfigSpec.Builder builder = new ModConfigSpec.Builder();
      ENABLE_CORRUPTION_SPREAD = builder
         .comment("Allow Null blocks and Null mobs to spread corruption by converting nearby blocks.")
         .define("enableCorruptionSpread", true);
      SERVER_SPEC = builder.build();
   }

   private EternullConfig() {
   }

   public static boolean isCorruptionSpreadEnabled() {
      return ENABLE_CORRUPTION_SPREAD.getAsBoolean();
   }
}
