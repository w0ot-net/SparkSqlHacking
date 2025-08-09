package org.apache.spark.network.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.SparkLogger;
import org.apache.spark.internal.SparkLoggerFactory;
import org.apache.spark.internal.LogKeys.HOST_PORT.;
import org.apache.spark.network.TransportContext;
import org.apache.spark.network.client.TransportClient;
import org.apache.spark.network.client.TransportResponseHandler;
import org.apache.spark.network.protocol.ChunkFetchRequest;
import org.apache.spark.network.protocol.Message;
import org.apache.spark.network.protocol.RequestMessage;
import org.apache.spark.network.protocol.ResponseMessage;
import org.apache.spark.network.util.NettyUtils;

public class TransportChannelHandler extends SimpleChannelInboundHandler {
   private static final SparkLogger logger = SparkLoggerFactory.getLogger(TransportChannelHandler.class);
   private final TransportClient client;
   private final TransportResponseHandler responseHandler;
   private final TransportRequestHandler requestHandler;
   private final long requestTimeoutNs;
   private final boolean closeIdleConnections;
   private final boolean skipChunkFetchRequest;
   private final TransportContext transportContext;

   public TransportChannelHandler(TransportClient client, TransportResponseHandler responseHandler, TransportRequestHandler requestHandler, long requestTimeoutMs, boolean skipChunkFetchRequest, boolean closeIdleConnections, TransportContext transportContext) {
      this.client = client;
      this.responseHandler = responseHandler;
      this.requestHandler = requestHandler;
      this.requestTimeoutNs = requestTimeoutMs * 1000L * 1000L;
      this.skipChunkFetchRequest = skipChunkFetchRequest;
      this.closeIdleConnections = closeIdleConnections;
      this.transportContext = transportContext;
   }

   public TransportClient getClient() {
      return this.client;
   }

   public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
      logger.warn("Exception in connection from {}", cause, new MDC[]{MDC.of(.MODULE$, NettyUtils.getRemoteAddress(ctx.channel()))});
      this.requestHandler.exceptionCaught(cause);
      this.responseHandler.exceptionCaught(cause);
      ctx.close();
   }

   public void channelActive(ChannelHandlerContext ctx) throws Exception {
      try {
         this.requestHandler.channelActive();
      } catch (RuntimeException e) {
         logger.error("Exception from request handler while channel is active", e);
      }

      try {
         this.responseHandler.channelActive();
      } catch (RuntimeException e) {
         logger.error("Exception from response handler while channel is active", e);
      }

      super.channelActive(ctx);
   }

   public void channelInactive(ChannelHandlerContext ctx) throws Exception {
      try {
         this.requestHandler.channelInactive();
      } catch (RuntimeException e) {
         logger.error("Exception from request handler while channel is inactive", e);
      }

      try {
         this.responseHandler.channelInactive();
      } catch (RuntimeException e) {
         logger.error("Exception from response handler while channel is inactive", e);
      }

      super.channelInactive(ctx);
   }

   public boolean acceptInboundMessage(Object msg) throws Exception {
      return this.skipChunkFetchRequest && msg instanceof ChunkFetchRequest ? false : super.acceptInboundMessage(msg);
   }

   public void channelRead0(ChannelHandlerContext ctx, Message request) throws Exception {
      if (request instanceof RequestMessage msg) {
         this.requestHandler.handle(msg);
      } else if (request instanceof ResponseMessage msg) {
         this.responseHandler.handle(msg);
      } else {
         ctx.fireChannelRead(request);
      }

   }

   public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
      if (evt instanceof IdleStateEvent) {
         IdleStateEvent e = (IdleStateEvent)evt;
         synchronized(this) {
            boolean isActuallyOverdue = System.nanoTime() - this.responseHandler.getTimeOfLastRequestNs() > this.requestTimeoutNs;
            if (e.state() == IdleState.ALL_IDLE && isActuallyOverdue) {
               if (this.responseHandler.hasOutstandingRequests()) {
                  String address = NettyUtils.getRemoteAddress(ctx.channel());
                  logger.error("Connection to {} has been quiet for {} ms while there are outstanding requests. Assuming connection is dead; please adjust spark.{}.io.connectionTimeout if this is wrong.", new MDC[]{MDC.of(.MODULE$, address), MDC.of(org.apache.spark.internal.LogKeys.TIMEOUT..MODULE$, this.requestTimeoutNs / 1000L / 1000L), MDC.of(org.apache.spark.internal.LogKeys.MODULE_NAME..MODULE$, this.transportContext.getConf().getModuleName())});
                  this.client.timeOut();
                  ctx.close();
               } else if (this.closeIdleConnections) {
                  this.client.timeOut();
                  ctx.close();
               }
            }
         }
      }

      ctx.fireUserEventTriggered(evt);
   }

   public TransportResponseHandler getResponseHandler() {
      return this.responseHandler;
   }

   public TransportRequestHandler getRequestHandler() {
      return this.requestHandler;
   }

   public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
      this.transportContext.getRegisteredConnections().inc();
      super.channelRegistered(ctx);
   }

   public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
      this.transportContext.getRegisteredConnections().dec();
      super.channelUnregistered(ctx);
   }
}
