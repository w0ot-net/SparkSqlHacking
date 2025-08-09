package org.apache.spark.network.shuffle;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.MessageToMessageDecoder;
import java.nio.ByteBuffer;
import java.util.List;
import org.apache.spark.internal.SparkLogger;
import org.apache.spark.internal.SparkLoggerFactory;
import org.apache.spark.network.TransportContext;
import org.apache.spark.network.protocol.MessageDecoder;
import org.apache.spark.network.protocol.RpcRequest;
import org.apache.spark.network.server.RpcHandler;
import org.apache.spark.network.server.TransportChannelHandler;
import org.apache.spark.network.server.TransportRequestHandler;
import org.apache.spark.network.shuffle.protocol.BlockTransferMessage;
import org.apache.spark.network.util.IOMode;
import org.apache.spark.network.util.NettyUtils;
import org.apache.spark.network.util.TransportConf;

public class ShuffleTransportContext extends TransportContext {
   private static final SparkLogger logger = SparkLoggerFactory.getLogger(ShuffleTransportContext.class);
   private static final ShuffleMessageDecoder SHUFFLE_DECODER;
   private final EventLoopGroup finalizeWorkers;

   public ShuffleTransportContext(TransportConf conf, ExternalBlockHandler rpcHandler, boolean closeIdleConnections) {
      this(conf, rpcHandler, closeIdleConnections, false);
   }

   public ShuffleTransportContext(TransportConf conf, RpcHandler rpcHandler, boolean closeIdleConnections, boolean isClientOnly) {
      super(conf, rpcHandler, closeIdleConnections, isClientOnly);
      if ("shuffle".equalsIgnoreCase(conf.getModuleName()) && conf.separateFinalizeShuffleMerge()) {
         this.finalizeWorkers = NettyUtils.createEventLoop(IOMode.valueOf(conf.ioMode()), conf.finalizeShuffleMergeHandlerThreads(), "shuffle-finalize-merge-handler");
         logger.info("finalize shuffle merged workers created");
      } else {
         this.finalizeWorkers = null;
      }

   }

   public TransportChannelHandler initializePipeline(SocketChannel channel, boolean isClient) {
      TransportChannelHandler ch = super.initializePipeline(channel, isClient);
      this.addHandlerToPipeline(channel, ch);
      return ch;
   }

   public TransportChannelHandler initializePipeline(SocketChannel channel, RpcHandler channelRpcHandler, boolean isClient) {
      TransportChannelHandler ch = super.initializePipeline(channel, channelRpcHandler, isClient);
      this.addHandlerToPipeline(channel, ch);
      return ch;
   }

   private void addHandlerToPipeline(SocketChannel channel, TransportChannelHandler transportChannelHandler) {
      if (this.finalizeWorkers != null) {
         channel.pipeline().addLast(this.finalizeWorkers, "finalizeHandler", new FinalizedHandler(transportChannelHandler.getRequestHandler()));
      }

   }

   protected MessageToMessageDecoder getDecoder() {
      return (MessageToMessageDecoder)(this.finalizeWorkers == null ? super.getDecoder() : SHUFFLE_DECODER);
   }

   static {
      SHUFFLE_DECODER = new ShuffleMessageDecoder(MessageDecoder.INSTANCE);
   }

   @Sharable
   static class ShuffleMessageDecoder extends MessageToMessageDecoder {
      private final MessageDecoder delegate;

      ShuffleMessageDecoder(MessageDecoder delegate) {
         this.delegate = delegate;
      }

      protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List list) throws Exception {
         this.delegate.decode(channelHandlerContext, byteBuf, list);
         Object msg = list.get(list.size() - 1);
         if (msg instanceof RpcRequest req) {
            ByteBuffer buffer = req.body().nioByteBuffer();
            byte type = Unpooled.wrappedBuffer(buffer).readByte();
            if (type == BlockTransferMessage.Type.FINALIZE_SHUFFLE_MERGE.id()) {
               list.remove(list.size() - 1);
               RpcRequestInternal rpcRequestInternal = new RpcRequestInternal(BlockTransferMessage.Type.FINALIZE_SHUFFLE_MERGE, req);
               ShuffleTransportContext.logger.trace("Created internal rpc request msg with rpcId {} for finalize merge req", req.requestId);
               list.add(rpcRequestInternal);
            }
         }

      }
   }

   static record RpcRequestInternal(BlockTransferMessage.Type messageType, RpcRequest rpcRequest) {
   }

   static class FinalizedHandler extends SimpleChannelInboundHandler {
      private static final SparkLogger logger = SparkLoggerFactory.getLogger(FinalizedHandler.class);
      public static final String HANDLER_NAME = "finalizeHandler";
      private final TransportRequestHandler transportRequestHandler;

      public boolean acceptInboundMessage(Object msg) throws Exception {
         if (msg instanceof RpcRequestInternal rpcRequestInternal) {
            return rpcRequestInternal.messageType == BlockTransferMessage.Type.FINALIZE_SHUFFLE_MERGE;
         } else {
            return false;
         }
      }

      FinalizedHandler(TransportRequestHandler transportRequestHandler) {
         this.transportRequestHandler = transportRequestHandler;
      }

      protected void channelRead0(ChannelHandlerContext channelHandlerContext, RpcRequestInternal req) throws Exception {
         if (logger.isTraceEnabled()) {
            logger.trace("Finalize shuffle req from {} for rpc request {}", NettyUtils.getRemoteAddress(channelHandlerContext.channel()), req.rpcRequest.requestId);
         }

         this.transportRequestHandler.handle(req.rpcRequest);
      }
   }
}
