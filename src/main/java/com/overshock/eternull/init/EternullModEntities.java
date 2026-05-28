package com.overshock.eternull.init;

import com.overshock.eternull.entity.CorruptedChargedCreeperEntity;
import com.overshock.eternull.entity.CorruptedCreeperEntity;
import com.overshock.eternull.entity.CorruptedSpiderEntity;
import com.overshock.eternull.entity.CorruptedZombieEntity;
import com.overshock.eternull.entity.DarkBoatEntity;
import com.overshock.eternull.entity.NullmobEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.EntityType.Builder;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod.EventBusSubscriber;
import net.neoforged.fml.common.Mod.EventBusSubscriber.Bus;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

@EventBusSubscriber(bus = Bus.MOD)
public class EternullModEntities {
   public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(Registries.ENTITY_TYPE, "eternull");
   public static final DeferredHolder<EntityType<?>, EntityType<NullmobEntity>> NULLMOB = register(
      "nullmob",
      Builder.of(NullmobEntity::new, MobCategory.MONSTER)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(64)
         .setUpdateInterval(3)
         .fireImmune()
         .sized(0.6F, 1.8F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<CorruptedCreeperEntity>> CORRUPTED_CREEPER = register(
      "corrupted_creeper",
      Builder.of(CorruptedCreeperEntity::new, MobCategory.MONSTER)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(64)
         .setUpdateInterval(3)
         .fireImmune()
         .sized(0.6F, 1.7F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<CorruptedChargedCreeperEntity>> CORRUPTED_CHARGED_CREEPER = register(
      "corrupted_charged_creeper",
      Builder.of(CorruptedChargedCreeperEntity::new, MobCategory.MONSTER)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(64)
         .setUpdateInterval(3)
         .fireImmune()
         .sized(0.6F, 1.7F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<CorruptedZombieEntity>> CORRUPTED_ZOMBIE = register(
      "corrupted_zombie",
      Builder.of(CorruptedZombieEntity::new, MobCategory.MONSTER)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(64)
         .setUpdateInterval(3)
         .fireImmune()
         .sized(0.6F, 1.8F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<CorruptedSpiderEntity>> CORRUPTED_SPIDER = register(
      "corrupted_spider",
      Builder.of(CorruptedSpiderEntity::new, MobCategory.MONSTER)
         .setShouldReceiveVelocityUpdates(true)
         .setTrackingRange(64)
         .setUpdateInterval(3)
         .fireImmune()
         .sized(1.4F, 0.9F)
   );
   public static final DeferredHolder<EntityType<?>, EntityType<DarkBoatEntity>> DARK_BOAT = register(
      "dark_boat",
      Builder.of(DarkBoatEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).sized(0.5F, 0.5F)
   );

   private static <T extends Entity> DeferredHolder<EntityType<?>, EntityType<T>> register(String registryname, Builder<T> entityTypeBuilder) {
      return REGISTRY.register(registryname, () -> entityTypeBuilder.build(registryname));
   }

   @SubscribeEvent
   public static void init(FMLCommonSetupEvent event) {
      event.enqueueWork(() -> {
         NullmobEntity.init();
         CorruptedCreeperEntity.init();
         CorruptedChargedCreeperEntity.init();
         CorruptedZombieEntity.init();
         CorruptedSpiderEntity.init();
         DarkBoatEntity.init();
      });
   }

   @SubscribeEvent
   public static void registerAttributes(EntityAttributeCreationEvent event) {
      event.put((EntityType)NULLMOB.get(), NullmobEntity.createAttributes().build());
      event.put((EntityType)CORRUPTED_CREEPER.get(), CorruptedCreeperEntity.createAttributes().build());
      event.put((EntityType)CORRUPTED_CHARGED_CREEPER.get(), CorruptedChargedCreeperEntity.createAttributes().build());
      event.put((EntityType)CORRUPTED_ZOMBIE.get(), CorruptedZombieEntity.createAttributes().build());
      event.put((EntityType)CORRUPTED_SPIDER.get(), CorruptedSpiderEntity.createAttributes().build());
      event.put((EntityType)DARK_BOAT.get(), DarkBoatEntity.createAttributes().build());
   }
}
