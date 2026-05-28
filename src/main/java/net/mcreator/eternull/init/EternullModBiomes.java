package net.mcreator.eternull.init;

import com.google.common.base.Suppliers;
import com.mojang.datafixers.util.Pair;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.Holder.Direct;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.FeatureSorter;
import net.minecraft.world.level.biome.MultiNoiseBiomeSource;
import net.minecraft.world.level.biome.Climate.Parameter;
import net.minecraft.world.level.biome.Climate.ParameterList;
import net.minecraft.world.level.biome.Climate.ParameterPoint;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.SurfaceRules.RuleSource;
import net.minecraft.world.level.levelgen.SurfaceRules.SequenceRuleSource;
import net.minecraft.world.level.levelgen.placement.CaveSurface;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod.EventBusSubscriber;
import net.neoforged.neoforge.event.server.ServerAboutToStartEvent;

@EventBusSubscriber
public class EternullModBiomes {
   @SubscribeEvent
   public static void onServerAboutToStart(ServerAboutToStartEvent event) {
      MinecraftServer server = event.getServer();
      Registry<DimensionType> dimensionTypeRegistry = server.registryAccess().registryOrThrow(Registries.DIMENSION_TYPE);
      Registry<LevelStem> levelStemTypeRegistry = server.registryAccess().registryOrThrow(Registries.LEVEL_STEM);
      Registry<Biome> biomeRegistry = server.registryAccess().registryOrThrow(Registries.BIOME);

      for (LevelStem levelStem : levelStemTypeRegistry.stream().toList()) {
         DimensionType dimensionType = (DimensionType)levelStem.type().value();
         if (dimensionType == dimensionTypeRegistry.getOrThrow(BuiltinDimensionTypes.OVERWORLD)) {
            ChunkGenerator chunkGenerator = levelStem.generator();
            if (chunkGenerator.getBiomeSource() instanceof MultiNoiseBiomeSource noiseSource) {
               List<Pair<ParameterPoint, Holder<Biome>>> parameters = new ArrayList<>(noiseSource.parameters().values());
               addParameterPoint(
                  parameters,
                  new Pair(
                     new ParameterPoint(
                        Parameter.span(-0.4F, 2.0F),
                        Parameter.span(-2.0F, 0.4F),
                        Parameter.span(0.1F, 2.0F),
                        Parameter.span(-0.9F, 1.9F),
                        Parameter.point(0.0F),
                        Parameter.span(-0.5797293F, 2.0F),
                        0L
                     ),
                     biomeRegistry.getHolderOrThrow(ResourceKey.create(Registries.BIOME, new ResourceLocation("eternull", "the_corruption")))
                  )
               );
               addParameterPoint(
                  parameters,
                  new Pair(
                     new ParameterPoint(
                        Parameter.span(-0.4F, 2.0F),
                        Parameter.span(-2.0F, 0.4F),
                        Parameter.span(0.1F, 2.0F),
                        Parameter.span(-0.9F, 1.9F),
                        Parameter.point(1.0F),
                        Parameter.span(-0.5797293F, 2.0F),
                        0L
                     ),
                     biomeRegistry.getHolderOrThrow(ResourceKey.create(Registries.BIOME, new ResourceLocation("eternull", "the_corruption")))
                  )
               );
               chunkGenerator.biomeSource = MultiNoiseBiomeSource.createFromList(new ParameterList(parameters));
               chunkGenerator.featuresPerStep = Suppliers.memoize(
                  () -> FeatureSorter.buildFeaturesPerStep(
                     List.copyOf(chunkGenerator.biomeSource.possibleBiomes()),
                     biome -> ((BiomeGenerationSettings)chunkGenerator.generationSettingsGetter.apply(biome)).features(),
                     true
                  )
               );
            }

            if (chunkGenerator instanceof NoiseBasedChunkGenerator noiseGenerator) {
               NoiseGeneratorSettings noiseGeneratorSettings = (NoiseGeneratorSettings)noiseGenerator.settings.value();
               if (noiseGeneratorSettings.surfaceRule() instanceof SequenceRuleSource sequenceRuleSource) {
                  List<RuleSource> surfaceRules = new ArrayList<>(sequenceRuleSource.sequence());
                  addSurfaceRule(
                     surfaceRules,
                     1,
                     preliminarySurfaceRule(
                        ResourceKey.create(Registries.BIOME, new ResourceLocation("eternull", "the_corruption")),
                        ((Block)EternullModBlocks.NULLBLOCK.get()).defaultBlockState(),
                        ((Block)EternullModBlocks.NULLBLOCK.get()).defaultBlockState(),
                        ((Block)EternullModBlocks.NULLBLOCK.get()).defaultBlockState()
                     )
                  );
                  NoiseGeneratorSettings moddedNoiseGeneratorSettings = new NoiseGeneratorSettings(
                     noiseGeneratorSettings.noiseSettings(),
                     noiseGeneratorSettings.defaultBlock(),
                     noiseGeneratorSettings.defaultFluid(),
                     noiseGeneratorSettings.noiseRouter(),
                     SurfaceRules.sequence(surfaceRules.toArray(RuleSource[]::new)),
                     noiseGeneratorSettings.spawnTarget(),
                     noiseGeneratorSettings.seaLevel(),
                     noiseGeneratorSettings.disableMobGeneration(),
                     noiseGeneratorSettings.aquifersEnabled(),
                     noiseGeneratorSettings.oreVeinsEnabled(),
                     noiseGeneratorSettings.useLegacyRandomSource()
                  );
                  noiseGenerator.settings = new Direct(moddedNoiseGeneratorSettings);
               }
            }
         }
      }
   }

   private static RuleSource preliminarySurfaceRule(
      ResourceKey<Biome> biomeKey, BlockState groundBlock, BlockState undergroundBlock, BlockState underwaterBlock
   ) {
      return SurfaceRules.ifTrue(
         SurfaceRules.isBiome(new ResourceKey[]{biomeKey}),
         SurfaceRules.ifTrue(
            SurfaceRules.abovePreliminarySurface(),
            SurfaceRules.sequence(
               new RuleSource[]{
                  SurfaceRules.ifTrue(
                     SurfaceRules.stoneDepthCheck(0, false, 0, CaveSurface.FLOOR),
                     SurfaceRules.sequence(
                        new RuleSource[]{
                           SurfaceRules.ifTrue(SurfaceRules.waterBlockCheck(-1, 0), SurfaceRules.state(groundBlock)), SurfaceRules.state(underwaterBlock)
                        }
                     )
                  ),
                  SurfaceRules.ifTrue(SurfaceRules.stoneDepthCheck(0, true, 0, CaveSurface.FLOOR), SurfaceRules.state(undergroundBlock))
               }
            )
         )
      );
   }

   private static void addParameterPoint(List<Pair<ParameterPoint, Holder<Biome>>> parameters, Pair<ParameterPoint, Holder<Biome>> point) {
      if (!parameters.contains(point)) {
         parameters.add(point);
      }
   }

   private static void addSurfaceRule(List<RuleSource> surfaceRules, int index, RuleSource rule) {
      if (!surfaceRules.contains(rule)) {
         surfaceRules.add(index, rule);
      }
   }
}
