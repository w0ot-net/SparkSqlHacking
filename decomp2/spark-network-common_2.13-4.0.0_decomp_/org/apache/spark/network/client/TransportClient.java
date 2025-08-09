package org.apache.spark.network.client;

import io.netty.channel.Channel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import java.io.Closeable;
import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.SparkLogger;
import org.apache.spark.internal.SparkLoggerFactory;
import org.apache.spark.internal.LogKeys.REQUEST_ID.;
import org.apache.spark.network.buffer.ManagedBuffer;
import org.apache.spark.network.buffer.NioManagedBuffer;
import org.apache.spark.network.protocol.ChunkFetchRequest;
import org.apache.spark.network.protocol.MergedBlockMetaRequest;
import org.apache.spark.network.protocol.OneWayMessage;
import org.apache.spark.network.protocol.RpcRequest;
import org.apache.spark.network.protocol.StreamChunkId;
import org.apache.spark.network.protocol.StreamRequest;
import org.apache.spark.network.protocol.UploadStream;
import org.apache.spark.network.util.NettyUtils;
import org.sparkproject.guava.annotations.VisibleForTesting;
import org.sparkproject.guava.base.Preconditions;
import org.sparkproject.guava.base.Throwables;
import org.sparkproject.guava.util.concurrent.SettableFuture;

public class TransportClient implements Closeable {
   private static final SparkLogger logger = SparkLoggerFactory.getLogger(TransportClient.class);
   private final Channel channel;
   private final TransportResponseHandler handler;
   @Nullable
   private String clientId;
   private volatile boolean timedOut;

   public TransportClient(Channel channel, TransportResponseHandler handler) {
      this.channel = (Channel)Preconditions.checkNotNull(channel);
      this.handler = (TransportResponseHandler)Preconditions.checkNotNull(handler);
      this.timedOut = false;
   }

   public Channel getChannel() {
      return this.channel;
   }

   public boolean isActive() {
      return !this.timedOut && (this.channel.isOpen() || this.channel.isActive());
   }

   public SocketAddress getSocketAddress() {
      return this.channel.remoteAddress();
   }

   public String getClientId() {
      return this.clientId;
   }

   public void setClientId(String id) {
      Preconditions.checkState(this.clientId == null, "Client ID has already been set.");
      this.clientId = id;
   }

   public void fetchChunk(long streamId, final int chunkIndex, final ChunkReceivedCallback callback) {
      if (logger.isDebugEnabled()) {
         logger.debug("Sending fetch chunk request {} to {}", chunkIndex, NettyUtils.getRemoteAddress(this.channel));
      }

      final StreamChunkId streamChunkId = new StreamChunkId(streamId, chunkIndex);
      StdChannelListener listener = new StdChannelListener(streamChunkId) {
         void handleFailure(String errorMsg, Throwable cause) {
            TransportClient.this.handler.removeFetchRequest(streamChunkId);
            callback.onFailure(chunkIndex, new IOException(errorMsg, cause));
         }
      };
      this.handler.addFetchRequest(streamChunkId, callback);
      this.channel.writeAndFlush(new ChunkFetchRequest(streamChunkId)).addListener(listener);
   }

   public void stream(final String streamId, final StreamCallback callback) {
      StdChannelListener listener = new StdChannelListener(streamId) {
         void handleFailure(String errorMsg, Throwable cause) throws Exception {
            callback.onFailure(streamId, new IOException(errorMsg, cause));
         }
      };
      if (logger.isDebugEnabled()) {
         logger.debug("Sending stream request for {} to {}", streamId, NettyUtils.getRemoteAddress(this.channel));
      }

      synchronized(this) {
         this.handler.addStreamCallback(streamId, callback);
         this.channel.writeAndFlush(new StreamRequest(streamId)).addListener(listener);
      }
   }

   public long sendRpc(ByteBuffer message, RpcResponseCallback callback) {
      if (logger.isTraceEnabled()) {
         logger.trace("Sending RPC to {}", NettyUtils.getRemoteAddress(this.channel));
      }

      long requestId = requestId();
      this.handler.addRpcRequest(requestId, callback);
      RpcChannelListener listener = new RpcChannelListener(requestId, callback);
      this.channel.writeAndFlush(new RpcRequest(requestId, new NioManagedBuffer(message))).addListener(listener);
      return requestId;
   }

   public void sendMergedBlockMetaReq(String appId, int shuffleId, int shuffleMergeId, int reduceId, MergedBlockMetaResponseCallback callback) {
      long requestId = requestId();
      if (logger.isTraceEnabled()) {
         logger.trace("Sending RPC {} to fetch merged block meta to {}", requestId, NettyUtils.getRemoteAddress(this.channel));
      }

      this.handler.addRpcRequest(requestId, callback);
      RpcChannelListener listener = new RpcChannelListener(requestId, callback);
      this.channel.writeAndFlush(new MergedBlockMetaRequest(requestId, appId, shuffleId, shuffleMergeId, reduceId)).addListener(listener);
   }

   public long uploadStream(ManagedBuffer meta, ManagedBuffer data, RpcResponseCallback callback) {
      if (logger.isTraceEnabled()) {
         logger.trace("Sending RPC to {}", NettyUtils.getRemoteAddress(this.channel));
      }

      long requestId = requestId();
      this.handler.addRpcRequest(requestId, callback);
      RpcChannelListener listener = new RpcChannelListener(requestId, callback);
      this.channel.writeAndFlush(new UploadStream(requestId, meta, data)).addListener(listener);
      return requestId;
   }

   public ByteBuffer sendRpcSync(ByteBuffer message, long timeoutMs) {
      final SettableFuture<ByteBuffer> result = SettableFuture.create();
      this.sendRpc(message, new RpcResponseCallback() {
         public void onSuccess(ByteBuffer response) {
            try {
               ByteBuffer copy = ByteBuffer.allocate(response.remaining());
               copy.put(response);
               copy.flip();
               result.set(copy);
            } catch (Throwable t) {
               TransportClient.logger.warn("Error in responding RPC callback", t);
               result.setException(t);
            }

         }

         public void onFailure(Throwable e) {
            result.setException(e);
         }
      });

      try {
         return (ByteBuffer)result.get(timeoutMs, TimeUnit.MILLISECONDS);
      } catch (ExecutionException e) {
         Throwables.throwIfUnchecked(e.getCause());
         throw new RuntimeException(e.getCause());
      } catch (Exception e) {
         Throwables.throwIfUnchecked(e);
         throw new RuntimeException(e);
      }
   }

   public void send(ByteBuffer message) {
      this.channel.writeAndFlush(new OneWayMessage(new NioManagedBuffer(message)));
   }

   public void removeRpcRequest(long requestId) {
      this.handler.removeRpcRequest(requestId);
   }

   public void timeOut() {
      this.timedOut = true;
   }

   @VisibleForTesting
   public TransportResponseHandler getHandler() {
      return this.handler;
   }

   public void close() {
      this.timedOut = true;
      this.channel.close().awaitUninterruptibly(10L, TimeUnit.SECONDS);
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)).append("remoteAddress", this.channel.remoteAddress()).append("clientId", this.clientId).append("isActive", this.isActive()).toString();
   }

   private static long requestId() {
      return Math.abs(UUID.randomUUID().getLeastSignificantBits());
   }

   private class StdChannelListener implements GenericFutureListener {
      final long startTime = System.currentTimeMillis();
      final Object requestId;

      StdChannelListener(Object requestId) {
         this.requestId = requestId;
      }

      public void operationComplete(Future future) throws Exception {
         if (future.isSuccess()) {
            if (TransportClient.logger.isTraceEnabled()) {
               long timeTaken = System.currentTimeMillis() - this.startTime;
               TransportClient.logger.trace("Sending request {} to {} took {} ms", new Object[]{this.requestId, NettyUtils.getRemoteAddress(TransportClient.this.channel), timeTaken});
            }
         } else {
            TransportClient.logger.error("Failed to send RPC {} to {}", future.cause(), new MDC[]{MDC.of(.MODULE$, this.requestId), MDC.of(org.apache.spark.internal.LogKeys.HOST_PORT..MODULE$, NettyUtils.getRemoteAddress(TransportClient.this.channel))});
            TransportClient.this.channel.close();

            try {
               String errorMsg = String.format("Failed to send RPC %s to %s: %s", this.requestId, NettyUtils.getRemoteAddress(TransportClient.this.channel), future.cause());
               this.handleFailure(errorMsg, future.cause());
            } catch (Exception e) {
               TransportClient.logger.error("Uncaught exception in RPC response callback handler!", e);
            }
         }

      }

      void handleFailure(String errorMsg, Throwable cause) throws Exception {
      }
   }

   private class RpcChannelListener extends StdChannelListener {
      final long rpcRequestId;
      final BaseResponseCallback callback;

      RpcChannelListener(long rpcRequestId, BaseResponseCallback callback) {
         super("RPC " + rpcRequestId);
         this.rpcRequestId = rpcRequestId;
         this.callback = callback;
      }

      void handleFailure(String errorMsg, Throwable cause) {
         TransportClient.this.handler.removeRpcRequest(this.rpcRequestId);
         this.callback.onFailure(new IOException(errorMsg, cause));
      }
   }
}
