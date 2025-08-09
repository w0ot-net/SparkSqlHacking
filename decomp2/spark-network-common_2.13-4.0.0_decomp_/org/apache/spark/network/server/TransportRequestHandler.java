package org.apache.spark.network.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.SparkLogger;
import org.apache.spark.internal.SparkLoggerFactory;
import org.apache.spark.internal.LogKeys.NUM_CHUNKS.;
import org.apache.spark.network.buffer.ManagedBuffer;
import org.apache.spark.network.buffer.NioManagedBuffer;
import org.apache.spark.network.client.MergedBlockMetaResponseCallback;
import org.apache.spark.network.client.RpcResponseCallback;
import org.apache.spark.network.client.StreamCallbackWithID;
import org.apache.spark.network.client.StreamInterceptor;
import org.apache.spark.network.client.TransportClient;
import org.apache.spark.network.protocol.ChunkFetchRequest;
import org.apache.spark.network.protocol.Encodable;
import org.apache.spark.network.protocol.MergedBlockMetaRequest;
import org.apache.spark.network.protocol.MergedBlockMetaSuccess;
import org.apache.spark.network.protocol.OneWayMessage;
import org.apache.spark.network.protocol.RequestMessage;
import org.apache.spark.network.protocol.RpcFailure;
import org.apache.spark.network.protocol.RpcRequest;
import org.apache.spark.network.protocol.RpcResponse;
import org.apache.spark.network.protocol.StreamFailure;
import org.apache.spark.network.protocol.StreamRequest;
import org.apache.spark.network.protocol.StreamResponse;
import org.apache.spark.network.protocol.UploadStream;
import org.apache.spark.network.util.NettyUtils;
import org.apache.spark.network.util.TransportFrameDecoder;
import org.sparkproject.guava.base.Throwables;

public class TransportRequestHandler extends MessageHandler {
   private static final SparkLogger logger = SparkLoggerFactory.getLogger(TransportRequestHandler.class);
   private final Channel channel;
   private final TransportClient reverseClient;
   private final RpcHandler rpcHandler;
   private final StreamManager streamManager;
   private final long maxChunksBeingTransferred;
   private final ChunkFetchRequestHandler chunkFetchRequestHandler;

   public TransportRequestHandler(Channel channel, TransportClient reverseClient, RpcHandler rpcHandler, Long maxChunksBeingTransferred, ChunkFetchRequestHandler chunkFetchRequestHandler) {
      this.channel = channel;
      this.reverseClient = reverseClient;
      this.rpcHandler = rpcHandler;
      this.streamManager = rpcHandler.getStreamManager();
      this.maxChunksBeingTransferred = maxChunksBeingTransferred;
      this.chunkFetchRequestHandler = chunkFetchRequestHandler;
   }

   public void exceptionCaught(Throwable cause) {
      this.rpcHandler.exceptionCaught(cause, this.reverseClient);
   }

   public void channelActive() {
      this.rpcHandler.channelActive(this.reverseClient);
   }

   public void channelInactive() {
      if (this.streamManager != null) {
         try {
            this.streamManager.connectionTerminated(this.channel);
         } catch (RuntimeException e) {
            logger.error("StreamManager connectionTerminated() callback failed.", e);
         }
      }

      this.rpcHandler.channelInactive(this.reverseClient);
   }

   public void handle(RequestMessage request) throws Exception {
      if (request instanceof ChunkFetchRequest chunkFetchRequest) {
         this.chunkFetchRequestHandler.processFetchRequest(this.channel, chunkFetchRequest);
      } else if (request instanceof RpcRequest rpcRequest) {
         this.processRpcRequest(rpcRequest);
      } else if (request instanceof OneWayMessage oneWayMessage) {
         this.processOneWayMessage(oneWayMessage);
      } else if (request instanceof StreamRequest streamRequest) {
         this.processStreamRequest(streamRequest);
      } else if (request instanceof UploadStream uploadStream) {
         this.processStreamUpload(uploadStream);
      } else {
         if (!(request instanceof MergedBlockMetaRequest)) {
            throw new IllegalArgumentException("Unknown request type: " + String.valueOf(request));
         }

         MergedBlockMetaRequest mergedBlockMetaRequest = (MergedBlockMetaRequest)request;
         this.processMergedBlockMetaRequest(mergedBlockMetaRequest);
      }

   }

   private void processStreamRequest(StreamRequest req) {
      if (logger.isTraceEnabled()) {
         logger.trace("Received req from {} to fetch stream {}", NettyUtils.getRemoteAddress(this.channel), req.streamId);
      }

      if (this.maxChunksBeingTransferred < Long.MAX_VALUE) {
         long chunksBeingTransferred = this.streamManager.chunksBeingTransferred();
         if (chunksBeingTransferred >= this.maxChunksBeingTransferred) {
            logger.warn("The number of chunks being transferred {} is above {}, close the connection.", new MDC[]{MDC.of(.MODULE$, chunksBeingTransferred), MDC.of(org.apache.spark.internal.LogKeys.MAX_NUM_CHUNKS..MODULE$, this.maxChunksBeingTransferred)});
            this.channel.close();
            return;
         }
      }

      ManagedBuffer buf;
      try {
         buf = this.streamManager.openStream(req.streamId);
      } catch (Exception e) {
         logger.error("Error opening stream {} for request from {}", e, new MDC[]{MDC.of(org.apache.spark.internal.LogKeys.STREAM_ID..MODULE$, req.streamId), MDC.of(org.apache.spark.internal.LogKeys.HOST_PORT..MODULE$, NettyUtils.getRemoteAddress(this.channel))});
         this.respond(new StreamFailure(req.streamId, Throwables.getStackTraceAsString(e)));
         return;
      }

      if (buf != null) {
         this.streamManager.streamBeingSent(req.streamId);
         this.respond(new StreamResponse(req.streamId, buf.size(), buf)).addListener((future) -> this.streamManager.streamSent(req.streamId));
      } else {
         this.respond(new StreamFailure(req.streamId, String.format("Stream '%s' was not found.", req.streamId)));
      }

   }

   private void processRpcRequest(final RpcRequest req) {
      try {
         this.rpcHandler.receive(this.reverseClient, req.body().nioByteBuffer(), new RpcResponseCallback() {
            public void onSuccess(ByteBuffer response) {
               TransportRequestHandler.this.respond(new RpcResponse(req.requestId, new NioManagedBuffer(response)));
            }

            public void onFailure(Throwable e) {
               TransportRequestHandler.this.respond(new RpcFailure(req.requestId, Throwables.getStackTraceAsString(e)));
            }
         });
      } catch (Exception e) {
         logger.error("Error while invoking RpcHandler#receive() on RPC id {}", e, new MDC[]{MDC.of(org.apache.spark.internal.LogKeys.REQUEST_ID..MODULE$, req.requestId)});
         this.respond(new RpcFailure(req.requestId, Throwables.getStackTraceAsString(e)));
      } finally {
         req.body().release();
      }

   }

   private void processStreamUpload(final UploadStream req) {
      assert req.body() == null;

      try {
         final RpcResponseCallback callback = new RpcResponseCallback() {
            public void onSuccess(ByteBuffer response) {
               TransportRequestHandler.this.respond(new RpcResponse(req.requestId, new NioManagedBuffer(response)));
            }

            public void onFailure(Throwable e) {
               TransportRequestHandler.this.respond(new RpcFailure(req.requestId, Throwables.getStackTraceAsString(e)));
            }
         };
         TransportFrameDecoder frameDecoder = (TransportFrameDecoder)this.channel.pipeline().get("frameDecoder");
         ByteBuffer meta = req.meta.nioByteBuffer();
         final StreamCallbackWithID streamHandler = this.rpcHandler.receiveStream(this.reverseClient, meta, callback);
         if (streamHandler == null) {
            throw new NullPointerException("rpcHandler returned a null streamHandler");
         }

         StreamCallbackWithID wrappedCallback = new StreamCallbackWithID() {
            public void onData(String streamId, ByteBuffer buf) throws IOException {
               streamHandler.onData(streamId, buf);
            }

            public void onComplete(String streamId) throws IOException {
               try {
                  streamHandler.onComplete(streamId);
                  callback.onSuccess(streamHandler.getCompletionResponse());
               } catch (BlockPushNonFatalFailure ex) {
                  callback.onSuccess(ex.getResponse());
                  streamHandler.onFailure(streamId, ex);
               } catch (Exception ex) {
                  IOException ioExc = new IOException("Failure post-processing complete stream; failing this rpc and leaving channel active", ex);
                  callback.onFailure(ioExc);
                  streamHandler.onFailure(streamId, ioExc);
               }

            }

            public void onFailure(String streamId, Throwable cause) throws IOException {
               callback.onFailure(new IOException("Destination failed while reading stream", cause));
               streamHandler.onFailure(streamId, cause);
            }

            public String getID() {
               return streamHandler.getID();
            }
         };
         if (req.bodyByteCount > 0L) {
            StreamInterceptor<RequestMessage> interceptor = new StreamInterceptor(this, wrappedCallback.getID(), req.bodyByteCount, wrappedCallback);
            frameDecoder.setInterceptor(interceptor);
         } else {
            wrappedCallback.onComplete(wrappedCallback.getID());
         }
      } catch (Exception var11) {
         if (var11 instanceof BlockPushNonFatalFailure) {
            BlockPushNonFatalFailure blockPushNonFatalFailure = (BlockPushNonFatalFailure)var11;
            this.respond(new RpcResponse(req.requestId, new NioManagedBuffer(blockPushNonFatalFailure.getResponse())));
         } else {
            logger.error("Error while invoking RpcHandler#receive() on RPC id {}", var11, new MDC[]{MDC.of(org.apache.spark.internal.LogKeys.REQUEST_ID..MODULE$, req.requestId)});
            this.respond(new RpcFailure(req.requestId, Throwables.getStackTraceAsString(var11)));
         }

         this.channel.pipeline().fireExceptionCaught(var11);
      } finally {
         req.meta.release();
      }

   }

   private void processOneWayMessage(OneWayMessage req) {
      try {
         this.rpcHandler.receive(this.reverseClient, req.body().nioByteBuffer());
      } catch (Exception e) {
         logger.error("Error while invoking RpcHandler#receive() for one-way message.", e);
      } finally {
         req.body().release();
      }

   }

   private void processMergedBlockMetaRequest(final MergedBlockMetaRequest req) {
      try {
         this.rpcHandler.getMergedBlockMetaReqHandler().receiveMergeBlockMetaReq(this.reverseClient, req, new MergedBlockMetaResponseCallback() {
            public void onSuccess(int numChunks, ManagedBuffer buffer) {
               TransportRequestHandler.logger.trace("Sending meta for request {} numChunks {}", req, numChunks);
               TransportRequestHandler.this.respond(new MergedBlockMetaSuccess(req.requestId, numChunks, buffer));
            }

            public void onFailure(Throwable e) {
               TransportRequestHandler.logger.trace("Failed to send meta for {}", req);
               TransportRequestHandler.this.respond(new RpcFailure(req.requestId, Throwables.getStackTraceAsString(e)));
            }
         });
      } catch (Exception e) {
         logger.error("Error while invoking receiveMergeBlockMetaReq() for appId {} shuffleId {} reduceId {}", e, new MDC[]{MDC.of(org.apache.spark.internal.LogKeys.APP_ID..MODULE$, req.appId), MDC.of(org.apache.spark.internal.LogKeys.SHUFFLE_ID..MODULE$, req.shuffleId), MDC.of(org.apache.spark.internal.LogKeys.REDUCE_ID..MODULE$, req.reduceId)});
         this.respond(new RpcFailure(req.requestId, Throwables.getStackTraceAsString(e)));
      }

   }

   private ChannelFuture respond(Encodable result) {
      SocketAddress remoteAddress = this.channel.remoteAddress();
      return this.channel.writeAndFlush(result).addListener((future) -> {
         if (future.isSuccess()) {
            logger.trace("Sent result {} to client {}", result, remoteAddress);
         } else {
            logger.error("Error sending result {} to {}; closing connection", future.cause(), new MDC[]{MDC.of(org.apache.spark.internal.LogKeys.RESULT..MODULE$, result), MDC.of(org.apache.spark.internal.LogKeys.HOST_PORT..MODULE$, remoteAddress)});
            this.channel.close();
         }

      });
   }
}
