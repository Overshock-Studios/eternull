package com.overshock.eternull.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.overshock.eternull.entity.CorruptedChargedCreeperEntity;
import net.minecraft.client.model.CreeperModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;

public class CorruptedChargedCreeperRenderer extends MobRenderer<CorruptedChargedCreeperEntity, CreeperModel<CorruptedChargedCreeperEntity>> {
   public CorruptedChargedCreeperRenderer(Context context) {
      super(context, new CreeperModel(context.bakeLayer(ModelLayers.CREEPER)), 0.5F);
      this.addLayer(
         new RenderLayer<CorruptedChargedCreeperEntity, CreeperModel<CorruptedChargedCreeperEntity>>(this) {
            final ResourceLocation LAYER_TEXTURE = new ResourceLocation("eternull:textures/entities/download_1.png");

            public void render(
               PoseStack poseStack,
               MultiBufferSource bufferSource,
               int light,
               CorruptedChargedCreeperEntity entity,
               float limbSwing,
               float limbSwingAmount,
               float partialTicks,
               float ageInTicks,
               float netHeadYaw,
               float headPitch
            ) {
               VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.eyes(this.LAYER_TEXTURE));
               ((CreeperModel)this.getParentModel())
                  .renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0.0F), 1.0F, 1.0F, 1.0F, 1.0F);
            }
         }
      );
   }

   public ResourceLocation getTextureLocation(CorruptedChargedCreeperEntity entity) {
      return new ResourceLocation("eternull:textures/entities/creeper_skin.png");
   }
}
