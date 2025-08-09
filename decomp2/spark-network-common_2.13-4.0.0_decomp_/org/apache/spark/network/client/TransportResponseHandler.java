package org.apache.spark.network.client;

import io.netty.channel.Channel;
import java.io.IOException;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.SparkLogger;
import org.apache.spark.internal.SparkLoggerFactory;
import org.apache.spark.internal.LogKeys.COUNT.;
import org.apache.spark.network.protocol.ChunkFetchFailure;
import org.apache.spark.network.protocol.ChunkFetchSuccess;
import org.apache.spark.network.protocol.MergedBlockMetaSuccess;
import org.apache.spark.network.protocol.ResponseMessage;
import org.apache.spark.network.protocol.RpcFailure;
import org.apache.spark.network.protocol.RpcResponse;
import org.apache.spark.network.protocol.StreamChunkId;
import org.apache.spark.network.protocol.StreamFailure;
import org.apache.spark.network.protocol.StreamResponse;
import org.apache.spark.network.server.MessageHandler;
import org.apache.spark.network.util.NettyUtils;
import org.apache.spark.network.util.TransportFrameDecoder;
import org.sparkproject.guava.annotations.VisibleForTesting;

public class TransportResponseHandler extends MessageHandler {
   private static final SparkLogger logger = SparkLoggerFactory.getLogger(TransportResponseHandler.class);
   private final Channel channel;
   private final Map outstandingFetches;
   private final Map outstandingRpcs;
   private final Queue streamCallbacks;
   private volatile boolean streamActive;
   private final AtomicLong timeOfLastRequestNs;

   public TransportResponseHandler(Channel channel) {
      this.channel = channel;
      this.outstandingFetches = new ConcurrentHashMap();
      this.outstandingRpcs = new ConcurrentHashMap();
      this.streamCallbacks = new ConcurrentLinkedQueue();
      this.timeOfLastRequestNs = new AtomicLong(0L);
   }

   public void addFetchRequest(StreamChunkId streamChunkId, ChunkReceivedCallback callback) {
      this.updateTimeOfLastRequest();
      this.outstandingFetches.put(streamChunkId, callback);
   }

   public void removeFetchRequest(StreamChunkId streamChunkId) {
      this.outstandingFetches.remove(streamChunkId);
   }

   public void addRpcRequest(long requestId, BaseResponseCallback callback) {
      this.updateTimeOfLastRequest();
      this.outstandingRpcs.put(requestId, callback);
   }

   public void removeRpcRequest(long requestId) {
      this.outstandingRpcs.remove(requestId);
   }

   public void addStreamCallback(String streamId, StreamCallback callback) {
      this.updateTimeOfLastRequest();
      this.streamCallbacks.offer(ImmutablePair.of(streamId, callback));
   }

   @VisibleForTesting
   public void deactivateStream() {
      this.streamActive = false;
   }

   private void failOutstandingRequests(Throwable cause) {
      for(Map.Entry entry : this.outstandingFetches.entrySet()) {
         try {
            ((ChunkReceivedCallback)entry.getValue()).onFailure(((StreamChunkId)entry.getKey()).chunkIndex(), cause);
         } catch (Exception e) {
            logger.warn("ChunkReceivedCallback.onFailure throws exception", e);
         }
      }

      for(BaseResponseCallback callback : this.outstandingRpcs.values()) {
         try {
            callback.onFailure(cause);
         } catch (Exception e) {
            logger.warn("RpcResponseCallback.onFailure throws exception", e);
         }
      }

      for(Pair entry : this.streamCallbacks) {
         try {
            ((StreamCallback)entry.getValue()).onFailure((String)entry.getKey(), cause);
         } catch (Exception e) {
            logger.warn("StreamCallback.onFailure throws exception", e);
         }
      }

      this.outstandingFetches.clear();
      this.outstandingRpcs.clear();
      this.streamCallbacks.clear();
   }

   public void channelActive() {
   }

   public void channelInactive() {
      if (this.hasOutstandingRequests()) {
         String remoteAddress = NettyUtils.getRemoteAddress(this.channel);
         logger.error("Still have {} requests outstanding when connection from {} is closed", new MDC[]{MDC.of(.MODULE$, this.numOutstandingRequests()), MDC.of(org.apache.spark.internal.LogKeys.HOST_PORT..MODULE$, remoteAddress)});
         this.failOutstandingRequests(new IOException("Connection from " + remoteAddress + " closed"));
      }

   }

   public void exceptionCaught(Throwable cause) {
      if (this.hasOutstandingRequests()) {
         String remoteAddress = NettyUtils.getRemoteAddress(this.channel);
         logger.error("Still have {} requests outstanding when connection from {} is closed", new MDC[]{MDC.of(.MODULE$, this.numOutstandingRequests()), MDC.of(org.apache.spark.internal.LogKeys.HOST_PORT..MODULE$, remoteAddress)});
         this.failOutstandingRequests(cause);
      }

   }

   public void handle(ResponseMessage message) throws Exception {
      if (message instanceof ChunkFetchSuccess resp) {
         ChunkReceivedCallback listener = (ChunkReceivedCallback)this.outstandingFetches.get(resp.streamChunkId);
         if (listener == null) {
            logger.warn("Ignoring response for block {} from {} since it is not outstanding", new MDC[]{MDC.of(org.apache.spark.internal.LogKeys.STREAM_CHUNK_ID..MODULE$, resp.streamChunkId), MDC.of(org.apache.spark.internal.LogKeys.HOST_PORT..MODULE$, NettyUtils.getRemoteAddress(this.channel))});
            resp.body().release();
         } else {
            this.outstandingFetches.remove(resp.streamChunkId);
            listener.onSuccess(resp.streamChunkId.chunkIndex(), resp.body());
            resp.body().release();
         }
      } else if (message instanceof ChunkFetchFailure resp) {
         ChunkReceivedCallback listener = (ChunkReceivedCallback)this.outstandingFetches.get(resp.streamChunkId);
         if (listener == null) {
            logger.warn("Ignoring response for block {} from {} ({}) since it is not outstanding", new MDC[]{MDC.of(org.apache.spark.internal.LogKeys.STREAM_CHUNK_ID..MODULE$, resp.streamChunkId), MDC.of(org.apache.spark.internal.LogKeys.HOST_PORT..MODULE$, NettyUtils.getRemoteAddress(this.channel)), MDC.of(org.apache.spark.internal.LogKeys.ERROR..MODULE$, resp.errorString)});
         } else {
            this.outstandingFetches.remove(resp.streamChunkId);
            int var10001 = resp.streamChunkId.chunkIndex();
            String var10004 = String.valueOf(resp.streamChunkId);
            listener.onFailure(var10001, new ChunkFetchFailureException("Failure while fetching " + var10004 + ": " + resp.errorString));
         }
      } else if (message instanceof RpcResponse resp) {
         RpcResponseCallback listener = (RpcResponseCallback)this.outstandingRpcs.get(resp.requestId);
         if (listener == null) {
            logger.warn("Ignoring response for RPC {} from {} ({} bytes) since it is not outstanding", new MDC[]{MDC.of(org.apache.spark.internal.LogKeys.REQUEST_ID..MODULE$, resp.requestId), MDC.of(org.apache.spark.internal.LogKeys.HOST_PORT..MODULE$, NettyUtils.getRemoteAddress(this.channel)), MDC.of(org.apache.spark.internal.LogKeys.RESPONSE_BODY_SIZE..MODULE$, resp.body().size())});
            resp.body().release();
         } else {
            this.outstandingRpcs.remove(resp.requestId);

            try {
               listener.onSuccess(resp.body().nioByteBuffer());
            } finally {
               resp.body().release();
            }
         }
      } else if (message instanceof RpcFailure resp) {
         BaseResponseCallback listener = (BaseResponseCallback)this.outstandingRpcs.get(resp.requestId);
         if (listener == null) {
            logger.warn("Ignoring response for RPC {} from {} ({}) since it is not outstanding", new MDC[]{MDC.of(org.apache.spark.internal.LogKeys.REQUEST_ID..MODULE$, resp.requestId), MDC.of(org.apache.spark.internal.LogKeys.HOST_PORT..MODULE$, NettyUtils.getRemoteAddress(this.channel)), MDC.of(org.apache.spark.internal.LogKeys.ERROR..MODULE$, resp.errorString)});
         } else {
            this.outstandingRpcs.remove(resp.requestId);
            listener.onFailure(new RuntimeException(resp.errorString));
         }
      } else if (message instanceof MergedBlockMetaSuccess resp) {
         try {
            MergedBlockMetaResponseCallback listener = (MergedBlockMetaResponseCallback)this.outstandingRpcs.get(resp.requestId);
            if (listener == null) {
               logger.warn("Ignoring response for MergedBlockMetaRequest {} from {} ({} bytes) since it is not outstanding", new MDC[]{MDC.of(org.apache.spark.internal.LogKeys.REQUEST_ID..MODULE$, resp.requestId), MDC.of(org.apache.spark.internal.LogKeys.HOST_PORT..MODULE$, NettyUtils.getRemoteAddress(this.channel)), MDC.of(org.apache.spark.internal.LogKeys.RESPONSE_BODY_SIZE..MODULE$, resp.body().size())});
            } else {
               this.outstandingRpcs.remove(resp.requestId);
               listener.onSuccess(resp.getNumChunks(), resp.body());
            }
         } finally {
            resp.body().release();
         }
      } else if (message instanceof StreamResponse resp) {
         Pair<String, StreamCallback> entry = (Pair)this.streamCallbacks.poll();
         if (entry != null) {
            StreamCallback callback = (StreamCallback)entry.getValue();
            if (resp.byteCount > 0L) {
               StreamInterceptor<ResponseMessage> interceptor = new StreamInterceptor(this, resp.streamId, resp.byteCount, callback);

               try {
                  TransportFrameDecoder frameDecoder = (TransportFrameDecoder)this.channel.pipeline().get("frameDecoder");
                  frameDecoder.setInterceptor(interceptor);
                  this.streamActive = true;
               } catch (Exception e) {
                  logger.error("Error installing stream handler.", e);
                  this.deactivateStream();
               }
            } else {
               try {
                  callback.onComplete(resp.streamId);
               } catch (Exception e) {
                  logger.warn("Error in stream handler onComplete().", e);
               }
            }
         } else {
            logger.error("Could not find callback for StreamResponse.");
         }
      } else {
         if (!(message instanceof StreamFailure)) {
            throw new IllegalStateException("Unknown response type: " + String.valueOf(message.type()));
         }

         StreamFailure resp = (StreamFailure)message;
         Pair<String, StreamCallback> entry = (Pair)this.streamCallbacks.poll();
         if (entry != null) {
            StreamCallback callback = (StreamCallback)entry.getValue();

            try {
               callback.onFailure(resp.streamId, new RuntimeException(resp.error));
            } catch (IOException ioe) {
               logger.warn("Error in stream failure handler.", ioe);
            }
         } else {
            logger.warn("Stream failure with unknown callback: {}", new MDC[]{MDC.of(org.apache.spark.internal.LogKeys.ERROR..MODULE$, resp.error)});
         }
      }

   }

   public int numOutstandingRequests() {
      return this.outstandingFetches.size() + this.outstandingRpcs.size() + this.streamCallbacks.size() + (this.streamActive ? 1 : 0);
   }

   public Boolean hasOutstandingRequests() {
      return this.streamActive || !this.outstandingFetches.isEmpty() || !this.outstandingRpcs.isEmpty() || !this.streamCallbacks.isEmpty();
   }

   public long getTimeOfLastRequestNs() {
      return this.timeOfLastRequestNs.get();
   }

   public void updateTimeOfLastRequest() {
      this.timeOfLastRequestNs.set(System.nanoTime());
   }
}
