package net.mcreator.eternull.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class Modelcustom_model<T extends Entity> extends EntityModel<T> {
   public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("eternull", "modelcustom_model"), "main");
   public final ModelPart bottom;
   public final ModelPart front;
   public final ModelPart back;
   public final ModelPart right;
   public final ModelPart left;
   public final ModelPart paddle_left;
   public final ModelPart paddle_right;

   public Modelcustom_model(ModelPart root) {
      this.bottom = root.getChild("bottom");
      this.front = root.getChild("front");
      this.back = root.getChild("back");
      this.right = root.getChild("right");
      this.left = root.getChild("left");
      this.paddle_left = root.getChild("paddle_left");
      this.paddle_right = root.getChild("paddle_right");
   }

   public static LayerDefinition createBodyLayer() {
      MeshDefinition meshdefinition = new MeshDefinition();
      PartDefinition partdefinition = meshdefinition.getRoot();
      PartDefinition bottom = partdefinition.addOrReplaceChild(
         "bottom", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 6.0F, 0.0F, 1.5708F, 0.0F, 0.0F)
      );
      PartDefinition bottom_r1 = bottom.addOrReplaceChild(
         "bottom_r1",
         CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-5.125F, -16.0F, -22.0F, 28.0F, 16.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false),
         PartPose.offsetAndRotation(-8.0F, -12.875F, 8.0F, 0.0F, 0.0F, 1.5708F)
      );
      PartDefinition front = partdefinition.addOrReplaceChild(
         "front", CubeListBuilder.create(), PartPose.offsetAndRotation(15.0F, 0.0F, 0.0F, 0.0F, 1.5708F, 0.0F)
      );
      PartDefinition front_r1 = front.addOrReplaceChild(
         "front_r1",
         CubeListBuilder.create().texOffs(0, 27).mirror().addBox(-31.0F, 17.875F, -13.0F, 16.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false),
         PartPose.offsetAndRotation(-23.0F, -6.875F, 8.0F, 0.0F, -1.5708F, 0.0F)
      );
      PartDefinition back = partdefinition.addOrReplaceChild(
         "back", CubeListBuilder.create(), PartPose.offsetAndRotation(-15.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F)
      );
      PartDefinition back_r1 = back.addOrReplaceChild(
         "back_r1",
         CubeListBuilder.create().texOffs(0, 19).mirror().addBox(-32.0F, 17.875F, 25.0F, 18.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false),
         PartPose.offsetAndRotation(7.0F, -6.875F, 8.0F, 0.0F, -1.5708F, 0.0F)
      );
      PartDefinition right = partdefinition.addOrReplaceChild(
         "right", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, -9.0F, 0.0F, -3.1416F, 0.0F)
      );
      PartDefinition right_r1 = right.addOrReplaceChild(
         "right_r1",
         CubeListBuilder.create().texOffs(0, 35).mirror().addBox(-36.0F, 17.875F, 0.0F, 28.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false),
         PartPose.offsetAndRotation(-8.0F, -6.875F, 17.0F, 0.0F, -1.5708F, 0.0F)
      );
      PartDefinition left = partdefinition.addOrReplaceChild("left", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 9.0F));
      PartDefinition left_r1 = left.addOrReplaceChild(
         "left_r1",
         CubeListBuilder.create().texOffs(0, 43).mirror().addBox(-22.0F, 3.875F, 0.0F, 28.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false),
         PartPose.offsetAndRotation(-8.0F, 7.125F, -5.0F, 0.0F, -1.5708F, 0.0F)
      );
      PartDefinition paddle_left = partdefinition.addOrReplaceChild(
         "paddle_left", CubeListBuilder.create(), PartPose.offsetAndRotation(-15.9617F, -0.558F, -2.255F, -0.5236F, 0.0F, 0.0F)
      );
      PartDefinition paddle_left_r1 = paddle_left.addOrReplaceChild(
         "paddle_left_r1",
         CubeListBuilder.create()
            .texOffs(62, 0)
            .mirror()
            .addBox(-16.2946F, 4.9407F, 14.8166F, 1.0F, 6.0F, 7.0F, new CubeDeformation(0.0F))
            .mirror(false)
            .texOffs(62, 0)
            .mirror()
            .addBox(-17.2846F, 7.9407F, 0.8166F, 2.0F, 2.0F, 18.0F, new CubeDeformation(0.0F))
            .mirror(false),
         PartPose.offsetAndRotation(7.9617F, -6.317F, 10.255F, 1.0472F, -1.0472F, -1.5708F)
      );
      PartDefinition paddle_right = partdefinition.addOrReplaceChild(
         "paddle_right", CubeListBuilder.create(), PartPose.offsetAndRotation(-2.5F, -4.0F, -9.0F, -0.5236F, 3.1416F, 0.0F)
      );
      PartDefinition paddle_right_r1 = paddle_right.addOrReplaceChild(
         "paddle_right_r1",
         CubeListBuilder.create()
            .texOffs(62, 20)
            .mirror()
            .addBox(-19.6499F, -0.0806F, 17.6911F, 1.0F, 6.0F, 7.0F, new CubeDeformation(0.0F))
            .mirror(false)
            .texOffs(62, 20)
            .mirror()
            .addBox(-19.6599F, 2.9194F, 3.6911F, 2.0F, 2.0F, 18.0F, new CubeDeformation(0.0F))
            .mirror(false),
         PartPose.offsetAndRotation(-5.5F, -2.875F, 17.0F, 1.0472F, -1.0472F, -1.5708F)
      );
      return LayerDefinition.create(meshdefinition, 128, 64);
   }

   public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
   }

   public void renderToBuffer(
      PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha
   ) {
      this.bottom.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
      this.front.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
      this.back.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
      this.right.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
      this.left.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
      this.paddle_left.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
      this.paddle_right.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
   }
}
