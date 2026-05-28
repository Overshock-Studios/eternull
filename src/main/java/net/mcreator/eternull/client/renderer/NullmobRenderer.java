package net.mcreator.eternull.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.mcreator.eternull.entity.NullmobEntity;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;

public class NullmobRenderer extends HumanoidMobRenderer<NullmobEntity, HumanoidModel<NullmobEntity>> {
   public NullmobRenderer(Context context) {
      super(context, new HumanoidModel(context.bakeLayer(ModelLayers.PLAYER)), 0.5F);
      this.addLayer(
         new HumanoidArmorLayer(
            this,
            new HumanoidModel(context.bakeLayer(ModelLayers.PLAYER_INNER_ARMOR)),
            new HumanoidModel(context.bakeLayer(ModelLayers.PLAYER_OUTER_ARMOR)),
            context.getModelManager()
         )
      );
      this.addLayer(
         new RenderLayer<NullmobEntity, HumanoidModel<NullmobEntity>>(this) {
            final ResourceLocation LAYER_TEXTURE = new ResourceLocation("eternull:textures/entities/null.png");

            public void render(
               PoseStack poseStack,
               MultiBufferSource bufferSource,
               int light,
               NullmobEntity entity,
               float limbSwing,
               float limbSwingAmount,
               float partialTicks,
               float ageInTicks,
               float netHeadYaw,
               float headPitch
            ) {
               VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.eyes(this.LAYER_TEXTURE));
               ((HumanoidModel)this.getParentModel())
                  .renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0.0F), 1.0F, 1.0F, 1.0F, 1.0F);
            }
         }
      );
   }

   public ResourceLocation getTextureLocation(NullmobEntity entity) {
      return new ResourceLocation("eternull:textures/entities/null.png");
   }
}
