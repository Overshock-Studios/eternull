package com.overshock.eternull.client.renderer;

import com.overshock.eternull.entity.NullGuardianEntity;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;

public class NullGuardianRenderer extends HumanoidMobRenderer<NullGuardianEntity, HumanoidModel<NullGuardianEntity>> {
   public NullGuardianRenderer(Context context) {
      super(context, new HumanoidModel<>(context.bakeLayer(ModelLayers.PLAYER)), 0.6F);
   }

   @Override
   public ResourceLocation getTextureLocation(NullGuardianEntity entity) {
      return new ResourceLocation("eternull:textures/entities/null.png");
   }
}
