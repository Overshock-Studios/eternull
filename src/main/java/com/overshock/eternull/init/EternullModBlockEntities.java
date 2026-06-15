package com.overshock.eternull.init;

import com.overshock.eternull.block.NullHeartBlockEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class EternullModBlockEntities {
   public static final DeferredRegister<BlockEntityType<?>> REGISTRY = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, "eternull");

   public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<NullHeartBlockEntity>> NULL_HEART = REGISTRY.register(
      "null_heart",
      () -> BlockEntityType.Builder.of(NullHeartBlockEntity::new, (Block) EternullModBlocks.NULL_HEART.get()).build(null)
   );
}
