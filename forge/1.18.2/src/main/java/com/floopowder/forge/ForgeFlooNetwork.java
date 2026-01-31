package com.floopowder.forge;

import com.floopowder.api.IFlooNetwork;
import com.floopowder.api.IFlooPacketContext;
import io.netty.buffer.Unpooled;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Forge 1.18.2 implementation of IFlooNetwork.
 * Uses SimpleChannel for packet registration and handling.
 */
public class ForgeFlooNetwork implements IFlooNetwork {

  private final SimpleChannel channel;
  private int packetId = 0;
  private final Map<Class<?>, PacketInfo<?>> packetRegistry = new HashMap<>();

  public ForgeFlooNetwork(ResourceLocation channelId, String protocolVersion) {
    this.channel = NetworkRegistry.newSimpleChannel(
        channelId,
        () -> protocolVersion,
        protocolVersion::equals,
        protocolVersion::equals
    );
  }

  @Override
  public <T> void registerServerbound(
      ResourceLocation id,
      Class<T> type,
      BiConsumer<T, FriendlyByteBuf> encoder,
      Function<FriendlyByteBuf, T> decoder,
      BiConsumer<T, IFlooPacketContext> handler
  ) {
    int currentId = packetId++;
    channel.registerMessage(
        currentId,
        type,
        encoder,
        decoder,
        wrapHandler(handler),
        Optional.of(NetworkDirection.PLAY_TO_SERVER)
    );
    packetRegistry.put(type, new PacketInfo<>(currentId, encoder));
  }

  @Override
  public <T> void registerClientbound(
      ResourceLocation id,
      Class<T> type,
      BiConsumer<T, FriendlyByteBuf> encoder,
      Function<FriendlyByteBuf, T> decoder,
      BiConsumer<T, IFlooPacketContext> handler
  ) {
    int currentId = packetId++;
    channel.registerMessage(
        currentId,
        type,
        encoder,
        decoder,
        wrapHandler(handler),
        Optional.of(NetworkDirection.PLAY_TO_CLIENT)
    );
    packetRegistry.put(type, new PacketInfo<>(currentId, encoder));
  }

  private <T> BiConsumer<T, Supplier<NetworkEvent.Context>> wrapHandler(
      BiConsumer<T, IFlooPacketContext> handler
  ) {
    return (packet, ctxSupplier) -> {
      NetworkEvent.Context ctx = ctxSupplier.get();
      handler.accept(packet, new ForgePacketContext(ctx));
      ctx.setPacketHandled(true);
    };
  }

  @Override
  public void sendToServer(Object packet) {
    channel.sendToServer(packet);
  }

  @Override
  public void sendToPlayer(Object packet, ServerPlayer player) {
    channel.send(PacketDistributor.PLAYER.with(() -> player), packet);
  }

  @Override
  public void sendToAll(Object packet) {
    channel.send(PacketDistributor.ALL.noArg(), packet);
  }

  @Override
  public void sendToTracking(Object packet, ServerPlayer trackedPlayer) {
    channel.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> trackedPlayer), packet);
  }

  @Override
  public void sendToTrackingBlock(Object packet, ServerLevel level, BlockPos pos) {
    LevelChunk chunk = level.getChunkAt(pos);
    channel.send(PacketDistributor.TRACKING_CHUNK.with(() -> chunk), packet);
  }

  @Override
  public void sendToTrackingChunk(Object packet, LevelChunk chunk) {
    channel.send(PacketDistributor.TRACKING_CHUNK.with(() -> chunk), packet);
  }

  /**
   * Gets the underlying SimpleChannel for advanced usage.
   */
  public SimpleChannel getChannel() {
    return channel;
  }

  private record PacketInfo<T>(int id, BiConsumer<T, FriendlyByteBuf> encoder) {}

  /**
   * Forge 1.18.2 packet context implementation.
   */
  private static class ForgePacketContext implements IFlooPacketContext {
    private final NetworkEvent.Context ctx;

    ForgePacketContext(NetworkEvent.Context ctx) {
      this.ctx = ctx;
    }

    @Override
    public Player getPlayer() {
      return ctx.getSender();
    }

    @Override
    public ServerPlayer getServerPlayer() {
      return ctx.getSender();
    }

    @Override
    public boolean isClientSide() {
      return ctx.getDirection().getReceptionSide().isClient();
    }

    @Override
    public void enqueueWork(Runnable work) {
      ctx.enqueueWork(work);
    }
  }
}
