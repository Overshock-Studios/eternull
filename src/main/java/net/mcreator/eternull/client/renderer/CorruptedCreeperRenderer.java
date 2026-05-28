package net.mcreator.eternull.client.renderer;

import net.mcreator.eternull.entity.CorruptedCreeperEntity;
import net.minecraft.client.model.CreeperModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;

public class CorruptedCreeperRenderer extends MobRenderer<CorruptedCreeperEntity, CreeperModel<CorruptedCreeperEntity>> {
   public CorruptedCreeperRenderer(Context context) {
      super(context, new CreeperModel(context.bakeLayer(ModelLayers.CREEPER)), 0.5F);
   }

   public ResourceLocation getTextureLocation(CorruptedCreeperEntity entity) {
      return new ResourceLocation("eternull:textures/entities/creeper_skin.png");
   }
}
