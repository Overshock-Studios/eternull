package net.mcreator.eternull.init;

import net.mcreator.eternull.client.renderer.CorruptedChargedCreeperRenderer;
import net.mcreator.eternull.client.renderer.CorruptedCreeperRenderer;
import net.mcreator.eternull.client.renderer.CorruptedSpiderRenderer;
import net.mcreator.eternull.client.renderer.CorruptedZombieRenderer;
import net.mcreator.eternull.client.renderer.DarkBoatRenderer;
import net.mcreator.eternull.client.renderer.NullmobRenderer;
import net.minecraft.world.entity.EntityType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod.EventBusSubscriber;
import net.neoforged.fml.common.Mod.EventBusSubscriber.Bus;
import net.neoforged.neoforge.client.event.EntityRenderersEvent.RegisterRenderers;

@EventBusSubscriber(bus = Bus.MOD, value = Dist.CLIENT)
public class EternullModEntityRenderers {
   @SubscribeEvent
   public static void registerEntityRenderers(RegisterRenderers event) {
      event.registerEntityRenderer((EntityType)EternullModEntities.NULLMOB.get(), NullmobRenderer::new);
      event.registerEntityRenderer((EntityType)EternullModEntities.CORRUPTED_CREEPER.get(), CorruptedCreeperRenderer::new);
      event.registerEntityRenderer((EntityType)EternullModEntities.CORRUPTED_CHARGED_CREEPER.get(), CorruptedChargedCreeperRenderer::new);
      event.registerEntityRenderer((EntityType)EternullModEntities.CORRUPTED_ZOMBIE.get(), CorruptedZombieRenderer::new);
      event.registerEntityRenderer((EntityType)EternullModEntities.CORRUPTED_SPIDER.get(), CorruptedSpiderRenderer::new);
      event.registerEntityRenderer((EntityType)EternullModEntities.DARK_BOAT.get(), DarkBoatRenderer::new);
   }
}
