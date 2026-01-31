package com.floopowder.api;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.Nullable;

/**
 * Platform-agnostic packet context for handling network messages.
 * Abstracts Forge's NetworkEvent.Context and Fabric's direct player/server access.
 */
public interface IFlooPacketContext {

  /**
   * Gets the player associated with this context.
   * For server-bound packets, this is the sender.
   * For client-bound packets, this may be the local player or null.
   */
  @Nullable
  Player getPlayer();

  /**
   * Gets the server player associated with this context.
   * Only valid for server-bound packets.
   */
  @Nullable
  ServerPlayer getServerPlayer();

  /**
   * Returns true if this packet is being handled on the client side.
   */
  boolean isClientSide();

  /**
   * Enqueues work to be executed on the main thread.
   * Network handlers run on netty threads - use this for game logic.
   */
  void enqueueWork(Runnable work);
}
