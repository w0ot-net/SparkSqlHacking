package io.vertx.core.net.impl;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.ssl.SniCompletionEvent;
import io.netty.handler.ssl.SslHandshakeCompletionEvent;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.Promise;

public class SslHandshakeCompletionHandler extends ChannelInboundHandlerAdapter {
   static AttributeKey SERVER_NAME_ATTR = AttributeKey.valueOf("sniServerName");
   private final Promise promise;

   public SslHandshakeCompletionHandler(Promise promise) {
      this.promise = promise;
   }

   public void userEventTriggered(ChannelHandlerContext ctx, Object evt) {
      if (evt instanceof SniCompletionEvent) {
         SniCompletionEvent completion = (SniCompletionEvent)evt;
         if (completion.isSuccess()) {
            Attribute<String> val = ctx.channel().attr(SERVER_NAME_ATTR);
            val.set(completion.hostname());
         } else {
            this.promise.tryFailure(completion.cause());
         }
      } else if (evt instanceof SslHandshakeCompletionEvent) {
         SslHandshakeCompletionEvent completion = (SslHandshakeCompletionEvent)evt;
         if (completion.isSuccess()) {
            ctx.pipeline().remove(this);
            this.promise.setSuccess((Object)null);
         } else {
            this.promise.tryFailure(completion.cause());
         }
      } else {
         ctx.fireUserEventTriggered(evt);
      }

   }

   public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
   }
}
