package com.overshock.eternull.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.overshock.eternull.entity.CorruptedSpiderEntity;
import net.minecraft.client.model.SpiderModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;

public class CorruptedSpiderRenderer extends MobRenderer<CorruptedSpiderEntity, SpiderModel<CorruptedSpiderEntity>> {
   public CorruptedSpiderRenderer(Context context) {
      super(context, new SpiderModel(context.bakeLayer(ModelLayers.SPIDER)), 0.5F);
      this.addLayer(
         new RenderLayer<CorruptedSpiderEntity, SpiderModel<CorruptedSpiderEntity>>(this) {
            final ResourceLocation LAYER_TEXTURE = new ResourceLocation("eternull:textures/entities/spodereyes.png");

            public void render(
               PoseStack poseStack,
               MultiBufferSource bufferSource,
               int light,
               CorruptedSpiderEntity entity,
               float limbSwing,
               float limbSwingAmount,
               float partialTicks,
               float ageInTicks,
               float netHeadYaw,
               float headPitch
            ) {
               VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.eyes(this.LAYER_TEXTURE));
               ((SpiderModel)this.getParentModel())
                  .renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0.0F), 1.0F, 1.0F, 1.0F, 1.0F);
            }
         }
      );
   }

   public ResourceLocation getTextureLocation(CorruptedSpiderEntity entity) {
      return new ResourceLocation("eternull:textures/entities/spider_skin.png");
   }
}
