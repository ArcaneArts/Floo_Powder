package com.floopowder.api;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.chunk.LevelChunk;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Platform-agnostic network abstraction.
 * Handles packet registration and sending across Forge, Fabric, and NeoForge.
 *
 * Packets should be defined as records with static encode/decode/handle methods:
 * <pre>{@code
 * public record MyPacket(int value, String text) {
 *   public static void encode(MyPacket packet, FriendlyByteBuf buf) {
 *     buf.writeInt(packet.value);
 *     buf.writeUtf(packet.text);
 *   }
 *
 *   public static MyPacket decode(FriendlyByteBuf buf) {
 *     return new MyPacket(buf.readInt(), buf.readUtf());
 *   }
 *
 *   public static void handle(MyPacket packet, IFlooPacketContext ctx) {
 *     ctx.enqueueWork(() -> {
 *       // Handle packet on main thread
 *     });
 *   }
 * }
 * }</pre>
 */
public interface IFlooNetwork {

  /**
   * Registers a client-to-server packet.
   *
   * @param id       Unique packet identifier
   * @param type     Packet class
   * @param encoder  Serializes packet to buffer
   * @param decoder  Deserializes packet from buffer
   * @param handler  Handles packet with context
   */
  <T> void registerServerbound(
      ResourceLocation id,
      Class<T> type,
      BiConsumer<T, net.minecraft.network.FriendlyByteBuf> encoder,
      Function<net.minecraft.network.FriendlyByteBuf, T> decoder,
      BiConsumer<T, IFlooPacketContext> handler
  );

  /**
   * Registers a server-to-client packet.
   *
   * @param id       Unique packet identifier
   * @param type     Packet class
   * @param encoder  Serializes packet to buffer
   * @param decoder  Deserializes packet from buffer
   * @param handler  Handles packet with context
   */
  <T> void registerClientbound(
      ResourceLocation id,
      Class<T> type,
      BiConsumer<T, net.minecraft.network.FriendlyByteBuf> encoder,
      Function<net.minecraft.network.FriendlyByteBuf, T> decoder,
      BiConsumer<T, IFlooPacketContext> handler
  );

  /**
   * Sends a packet to the server. Must be called from client.
   */
  void sendToServer(Object packet);

  /**
   * Sends a packet to a specific player.
   */
  void sendToPlayer(Object packet, ServerPlayer player);

  /**
   * Sends a packet to all connected players.
   */
  void sendToAll(Object packet);

  /**
   * Sends a packet to all players tracking an entity.
   */
  void sendToTracking(Object packet, ServerPlayer trackedPlayer);

  /**
   * Sends a packet to all players tracking a block position.
   */
  void sendToTrackingBlock(Object packet, ServerLevel level, BlockPos pos);

  /**
   * Sends a packet to all players tracking a chunk.
   */
  void sendToTrackingChunk(Object packet, LevelChunk chunk);
}
