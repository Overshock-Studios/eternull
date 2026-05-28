package com.overshock.eternull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import com.overshock.eternull.init.EternullModBlocks;
import com.overshock.eternull.init.EternullModEntities;
import com.overshock.eternull.init.EternullModItems;
import com.overshock.eternull.init.EternullModTabs;
import net.minecraft.network.FriendlyByteBuf.Reader;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Tuple;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.util.thread.SidedThreadGroups;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.TickEvent.Phase;
import net.neoforged.neoforge.event.TickEvent.ServerTickEvent;
import net.neoforged.neoforge.event.entity.living.LivingEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlerEvent;
import net.neoforged.neoforge.network.handling.IPlayPayloadHandler;
import net.neoforged.neoforge.network.registration.IPayloadRegistrar;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("eternull")
public class EternullMod {
   public static final Logger LOGGER = LogManager.getLogger(EternullMod.class);
   public static final String MODID = "eternull";
   private static boolean networkingRegistered = false;
   private static final Map<ResourceLocation, EternullMod.NetworkMessage<?>> MESSAGES = new HashMap<>();
   private static final Collection<Tuple<Runnable, Integer>> workQueue = new ConcurrentLinkedQueue<>();

   public EternullMod(IEventBus modEventBus) {
      ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, EternullConfig.SERVER_SPEC);
      NeoForge.EVENT_BUS.register(this);
      modEventBus.addListener(this::registerNetworking);
      EternullModBlocks.REGISTRY.register(modEventBus);
      EternullModItems.register(modEventBus);
      EternullModEntities.REGISTRY.register(modEventBus);
      EternullModTabs.REGISTRY.register(modEventBus);
   }

   public static <T extends CustomPacketPayload> void addNetworkMessage(ResourceLocation id, Reader<T> reader, IPlayPayloadHandler<T> handler) {
      if (networkingRegistered) {
         throw new IllegalStateException("Cannot register new network messages after networking has been registered");
      }

      MESSAGES.put(id, new EternullMod.NetworkMessage(reader, handler));
   }

   private void registerNetworking(RegisterPayloadHandlerEvent event) {
      IPayloadRegistrar registrar = event.registrar("eternull");
      MESSAGES.forEach((id, networkMessage) -> registrar.play(id, (Reader)networkMessage.reader(), (IPlayPayloadHandler)networkMessage.handler()));
      networkingRegistered = true;
   }

   public static void queueServerWork(int tick, Runnable action) {
      if (Thread.currentThread().getThreadGroup() == SidedThreadGroups.SERVER) {
         workQueue.add(new Tuple(action, tick));
      }
   }

   @SubscribeEvent
   public void livingTick(LivingEvent.LivingTickEvent event) {
      EternullCorruption.onLivingTick(event);
   }

   public void tick(ServerTickEvent event) {
      if (event.phase == Phase.END) {
         List<Tuple<Runnable, Integer>> actions = new ArrayList<>();
         workQueue.forEach(work -> {
            work.setB((Integer)work.getB() - 1);
            if ((Integer)work.getB() == 0) {
               actions.add((Tuple<Runnable, Integer>)work);
            }
         });
         actions.forEach(e -> ((Runnable)e.getA()).run());
         workQueue.removeAll(actions);
      }
   }

   private record NetworkMessage<T extends CustomPacketPayload>(Reader<T> reader, IPlayPayloadHandler<T> handler) {
   }
}
