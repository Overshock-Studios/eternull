package net.mcreator.eternull.init;

import net.mcreator.eternull.client.model.Modelcustom_model;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod.EventBusSubscriber;
import net.neoforged.fml.common.Mod.EventBusSubscriber.Bus;
import net.neoforged.neoforge.client.event.EntityRenderersEvent.RegisterLayerDefinitions;

@EventBusSubscriber(bus = Bus.MOD, value = Dist.CLIENT)
public class EternullModModels {
   @SubscribeEvent
   public static void registerLayerDefinitions(RegisterLayerDefinitions event) {
      event.registerLayerDefinition(Modelcustom_model.LAYER_LOCATION, Modelcustom_model::createBodyLayer);
   }
}
