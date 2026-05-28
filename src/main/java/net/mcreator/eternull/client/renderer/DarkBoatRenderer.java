package net.mcreator.eternull.client.renderer;

import net.mcreator.eternull.client.model.Modelcustom_model;
import net.mcreator.eternull.entity.DarkBoatEntity;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;

public class DarkBoatRenderer extends MobRenderer<DarkBoatEntity, Modelcustom_model<DarkBoatEntity>> {
   public DarkBoatRenderer(Context context) {
      super(context, new Modelcustom_model(context.bakeLayer(Modelcustom_model.LAYER_LOCATION)), 0.5F);
   }

   public ResourceLocation getTextureLocation(DarkBoatEntity entity) {
      return new ResourceLocation("eternull:textures/entities/download_2.png");
   }
}
