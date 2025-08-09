package io.vertx.core.http.impl;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpContentDecompressor;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.LastHttpContent;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketHandshakeException;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.UpgradeRejectedException;
import io.vertx.core.http.impl.headers.HeadersAdaptor;

class WebSocketHandshakeInboundHandler extends ChannelInboundHandlerAdapter {
   private final Handler wsHandler;
   private final WebSocketClientHandshaker handshaker;
   private ChannelHandlerContext chctx;
   private FullHttpResponse response;
   private ChannelFuture fut;

   WebSocketHandshakeInboundHandler(WebSocketClientHandshaker handshaker, Handler wsHandler) {
      this.handshaker = handshaker;
      this.wsHandler = wsHandler;
   }

   public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
      super.handlerAdded(ctx);
      this.chctx = ctx;
      this.fut = this.handshaker.handshake(ctx.channel());
   }

   public void channelInactive(ChannelHandlerContext ctx) throws Exception {
      super.channelInactive(ctx);
      this.wsHandler.handle(Future.failedFuture((Throwable)(new WebSocketHandshakeException("Connection closed while handshake in process"))));
   }

   public void channelRead(ChannelHandlerContext ctx, Object msg) {
      if (msg instanceof HttpResponse) {
         HttpResponse resp = (HttpResponse)msg;
         this.response = new DefaultFullHttpResponse(resp.protocolVersion(), resp.status());
         this.response.headers().add(resp.headers());
      }

      if (msg instanceof HttpContent) {
         HttpContent content = (HttpContent)msg;

         try {
            if (this.response != null) {
               this.response.content().writeBytes(content.content());
               if (msg instanceof LastHttpContent) {
                  this.response.trailingHeaders().add(((LastHttpContent)msg).trailingHeaders());
                  ChannelPipeline pipeline = this.chctx.pipeline();
                  pipeline.remove(this);
                  ChannelHandler handler = pipeline.get(HttpContentDecompressor.class);
                  if (handler != null) {
                     ctx.pipeline().remove(handler);
                  }

                  this.handshakeComplete(this.response);
               }
            }
         } finally {
            content.release();
         }
      }

   }

   private void handshakeComplete(FullHttpResponse response) {
      int sc = response.status().code();
      if (sc != 101) {
         String msg = "WebSocket upgrade failure: " + sc;
         ByteBuf content = response.content();
         UpgradeRejectedException failure = new UpgradeRejectedException(msg, sc, new HeadersAdaptor(response.headers()), content != null ? Buffer.buffer(content) : null);
         this.wsHandler.handle(Future.failedFuture((Throwable)failure));
      } else {
         this.fut.addListener((future) -> {
            Future<HeadersAdaptor> res;
            if (future.isSuccess()) {
               try {
                  this.handshaker.finishHandshake(this.chctx.channel(), response);
                  res = Future.succeededFuture(new HeadersAdaptor(response.headers()));
               } catch (WebSocketHandshakeException e) {
                  res = Future.failedFuture((Throwable)e);
               }
            } else {
               res = Future.failedFuture(future.cause());
            }

            this.wsHandler.handle(res);
         });
      }

   }
}
