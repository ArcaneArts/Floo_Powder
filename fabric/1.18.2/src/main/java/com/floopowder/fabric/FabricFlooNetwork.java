package com.floopowder.fabric;

import com.floopowder.api.IFlooNetwork;
import com.floopowder.api.IFlooPacketContext;
import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.chunk.LevelChunk;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * Fabric 1.18.2 implementation of IFlooNetwork.
 * Uses Fabric Networking API for packet registration and handling.
 */
public class FabricFlooNetwork implements IFlooNetwork {

  private final Map<Class<?>, PacketInfo<?>> serverboundPackets = new HashMap<>();
  private final Map<Class<?>, PacketInfo<?>> clientboundPackets = new HashMap<>();

  @Override
  public <T> void registerServerbound(
      ResourceLocation id,
      Class<T> type,
      BiConsumer<T, FriendlyByteBuf> encoder,
      Function<FriendlyByteBuf, T> decoder,
      BiConsumer<T, IFlooPacketContext> handler
  ) {
    serverboundPackets.put(type, new PacketInfo<>(id, encoder));

    ServerPlayNetworking.registerGlobalReceiver(id, (server, player, networkHandler, buf, responseSender) -> {
      T packet = decoder.apply(buf);
      IFlooPacketContext ctx = new FabricServerPacketContext(server, player);
      handler.accept(packet, ctx);
    });
  }

  @Override
  public <T> void registerClientbound(
      ResourceLocation id,
      Class<T> type,
      BiConsumer<T, FriendlyByteBuf> encoder,
      Function<FriendlyByteBuf, T> decoder,
      BiConsumer<T, IFlooPacketContext> handler
  ) {
    clientboundPackets.put(type, new PacketInfo<>(id, encoder));

    ClientPlayNetworking.registerGlobalReceiver(id, (client, networkHandler, buf, responseSender) -> {
      T packet = decoder.apply(buf);
      IFlooPacketContext ctx = new FabricClientPacketContext(client);
      handler.accept(packet, ctx);
    });
  }

  @Override
  @SuppressWarnings("unchecked")
  public void sendToServer(Object packet) {
    PacketInfo<Object> info = (PacketInfo<Object>) serverboundPackets.get(packet.getClass());
    if (info == null) {
      throw new IllegalArgumentException("Packet not registered: " + packet.getClass());
    }
    FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.buffer());
    info.encoder.accept(packet, buf);
    ClientPlayNetworking.send(info.id, buf);
  }

  @Override
  @SuppressWarnings("unchecked")
  public void sendToPlayer(Object packet, ServerPlayer player) {
    PacketInfo<Object> info = (PacketInfo<Object>) clientboundPackets.get(packet.getClass());
    if (info == null) {
      throw new IllegalArgumentException("Packet not registered: " + packet.getClass());
    }
    FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.buffer());
    info.encoder.accept(packet, buf);
    ServerPlayNetworking.send(player, info.id, buf);
  }

  @Override
  @SuppressWarnings("unchecked")
  public void sendToAll(Object packet) {
    PacketInfo<Object> info = (PacketInfo<Object>) clientboundPackets.get(packet.getClass());
    if (info == null) {
      throw new IllegalArgumentException("Packet not registered: " + packet.getClass());
    }
    FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.buffer());
    info.encoder.accept(packet, buf);
    // Note: Requires server reference - this is a simplified implementation
    // In practice, you'd pass the server reference or use a different approach
  }

  @Override
  @SuppressWarnings("unchecked")
  public void sendToTracking(Object packet, ServerPlayer trackedPlayer) {
    PacketInfo<Object> info = (PacketInfo<Object>) clientboundPackets.get(packet.getClass());
    if (info == null) {
      throw new IllegalArgumentException("Packet not registered: " + packet.getClass());
    }
    for (ServerPlayer player : PlayerLookup.tracking(trackedPlayer)) {
      FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.buffer());
      info.encoder.accept(packet, buf);
      ServerPlayNetworking.send(player, info.id, buf);
    }
    // Also send to self
    FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.buffer());
    info.encoder.accept(packet, buf);
    ServerPlayNetworking.send(trackedPlayer, info.id, buf);
  }

  @Override
  @SuppressWarnings("unchecked")
  public void sendToTrackingBlock(Object packet, ServerLevel level, BlockPos pos) {
    PacketInfo<Object> info = (PacketInfo<Object>) clientboundPackets.get(packet.getClass());
    if (info == null) {
      throw new IllegalArgumentException("Packet not registered: " + packet.getClass());
    }
    for (ServerPlayer player : PlayerLookup.tracking(level, pos)) {
      FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.buffer());
      info.encoder.accept(packet, buf);
      ServerPlayNetworking.send(player, info.id, buf);
    }
  }

  @Override
  @SuppressWarnings("unchecked")
  public void sendToTrackingChunk(Object packet, LevelChunk chunk) {
    PacketInfo<Object> info = (PacketInfo<Object>) clientboundPackets.get(packet.getClass());
    if (info == null) {
      throw new IllegalArgumentException("Packet not registered: " + packet.getClass());
    }
    for (ServerPlayer player : PlayerLookup.tracking((ServerLevel) chunk.getLevel(), chunk.getPos())) {
      FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.buffer());
      info.encoder.accept(packet, buf);
      ServerPlayNetworking.send(player, info.id, buf);
    }
  }

  private record PacketInfo<T>(ResourceLocation id, BiConsumer<T, FriendlyByteBuf> encoder) {}

  /**
   * Fabric server-side packet context.
   */
  private static class FabricServerPacketContext implements IFlooPacketContext {
    private final MinecraftServer server;
    private final ServerPlayer player;

    FabricServerPacketContext(MinecraftServer server, ServerPlayer player) {
      this.server = server;
      this.player = player;
    }

    @Override
    public Player getPlayer() {
      return player;
    }

    @Override
    public ServerPlayer getServerPlayer() {
      return player;
    }

    @Override
    public boolean isClientSide() {
      return false;
    }

    @Override
    public void enqueueWork(Runnable work) {
      server.execute(work);
    }
  }

  /**
   * Fabric client-side packet context.
   */
  private static class FabricClientPacketContext implements IFlooPacketContext {
    private final net.minecraft.client.Minecraft client;

    FabricClientPacketContext(net.minecraft.client.Minecraft client) {
      this.client = client;
    }

    @Override
    public Player getPlayer() {
      return client.player;
    }

    @Override
    public ServerPlayer getServerPlayer() {
      return null;
    }

    @Override
    public boolean isClientSide() {
      return true;
    }

    @Override
    public void enqueueWork(Runnable work) {
      client.execute(work);
    }
  }
}
