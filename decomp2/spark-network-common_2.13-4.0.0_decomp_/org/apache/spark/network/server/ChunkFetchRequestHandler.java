package org.apache.spark.network.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import java.net.SocketAddress;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.SparkLogger;
import org.apache.spark.internal.SparkLoggerFactory;
import org.apache.spark.internal.LogKeys.HOST_PORT.;
import org.apache.spark.network.buffer.ManagedBuffer;
import org.apache.spark.network.client.TransportClient;
import org.apache.spark.network.protocol.ChunkFetchFailure;
import org.apache.spark.network.protocol.ChunkFetchRequest;
import org.apache.spark.network.protocol.ChunkFetchSuccess;
import org.apache.spark.network.protocol.Encodable;
import org.apache.spark.network.util.NettyUtils;
import org.sparkproject.guava.base.Throwables;

public class ChunkFetchRequestHandler extends SimpleChannelInboundHandler {
   private static final SparkLogger logger = SparkLoggerFactory.getLogger(ChunkFetchRequestHandler.class);
   private final TransportClient client;
   private final StreamManager streamManager;
   private final long maxChunksBeingTransferred;
   private final boolean syncModeEnabled;

   public ChunkFetchRequestHandler(TransportClient client, StreamManager streamManager, Long maxChunksBeingTransferred, boolean syncModeEnabled) {
      this.client = client;
      this.streamManager = streamManager;
      this.maxChunksBeingTransferred = maxChunksBeingTransferred;
      this.syncModeEnabled = syncModeEnabled;
   }

   public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
      logger.warn("Exception in connection from {}", cause, new MDC[]{MDC.of(.MODULE$, NettyUtils.getRemoteAddress(ctx.channel()))});
      ctx.close();
   }

   protected void channelRead0(ChannelHandlerContext ctx, ChunkFetchRequest msg) throws Exception {
      Channel channel = ctx.channel();
      this.processFetchRequest(channel, msg);
   }

   public void processFetchRequest(Channel channel, ChunkFetchRequest msg) throws Exception {
      if (logger.isTraceEnabled()) {
         logger.trace("Received req from {} to fetch block {}", NettyUtils.getRemoteAddress(channel), msg.streamChunkId);
      }

      if (this.maxChunksBeingTransferred < Long.MAX_VALUE) {
         long chunksBeingTransferred = this.streamManager.chunksBeingTransferred();
         if (chunksBeingTransferred >= this.maxChunksBeingTransferred) {
            logger.warn("The number of chunks being transferred {} is above {}, close the connection.", new MDC[]{MDC.of(org.apache.spark.internal.LogKeys.NUM_CHUNKS..MODULE$, chunksBeingTransferred), MDC.of(org.apache.spark.internal.LogKeys.MAX_NUM_CHUNKS..MODULE$, this.maxChunksBeingTransferred)});
            channel.close();
            return;
         }
      }

      ManagedBuffer buf;
      try {
         this.streamManager.checkAuthorization(this.client, msg.streamChunkId.streamId());
         buf = this.streamManager.getChunk(msg.streamChunkId.streamId(), msg.streamChunkId.chunkIndex());
         if (buf == null) {
            throw new IllegalStateException("Chunk was not found");
         }
      } catch (Exception e) {
         logger.error("Error opening block {} for request from {}", e, new MDC[]{MDC.of(org.apache.spark.internal.LogKeys.STREAM_CHUNK_ID..MODULE$, msg.streamChunkId), MDC.of(.MODULE$, NettyUtils.getRemoteAddress(channel))});
         this.respond(channel, new ChunkFetchFailure(msg.streamChunkId, Throwables.getStackTraceAsString(e)));
         return;
      }

      this.streamManager.chunkBeingSent(msg.streamChunkId.streamId());
      this.respond(channel, new ChunkFetchSuccess(msg.streamChunkId, buf)).addListener((ChannelFutureListener)(future) -> this.streamManager.chunkSent(msg.streamChunkId.streamId()));
   }

   private ChannelFuture respond(Channel channel, Encodable result) throws InterruptedException {
      SocketAddress remoteAddress = channel.remoteAddress();
      ChannelFuture channelFuture;
      if (this.syncModeEnabled) {
         channelFuture = channel.writeAndFlush(result).await();
      } else {
         channelFuture = channel.writeAndFlush(result);
      }

      return channelFuture.addListener((ChannelFutureListener)(future) -> {
         if (future.isSuccess()) {
            logger.trace("Sent result {} to client {}", result, remoteAddress);
         } else {
            logger.error("Error sending result {} to {}; closing connection", future.cause(), new MDC[]{MDC.of(org.apache.spark.internal.LogKeys.RESULT..MODULE$, result), MDC.of(.MODULE$, remoteAddress)});
            channel.close();
         }

      });
   }
}
