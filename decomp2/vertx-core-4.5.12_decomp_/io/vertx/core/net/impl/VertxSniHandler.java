package io.vertx.core.net.impl;

import io.netty.buffer.ByteBufAllocator;
import io.netty.handler.ssl.SniHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslHandler;
import io.netty.util.AsyncMapping;
import io.vertx.core.net.HostAndPort;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

class VertxSniHandler extends SniHandler {
   private final Executor delegatedTaskExec;
   private final HostAndPort remoteAddress;

   public VertxSniHandler(AsyncMapping mapping, long handshakeTimeoutMillis, Executor delegatedTaskExec, HostAndPort remoteAddress) {
      super(mapping, handshakeTimeoutMillis);
      this.delegatedTaskExec = delegatedTaskExec;
      this.remoteAddress = remoteAddress;
   }

   protected SslHandler newSslHandler(SslContext context, ByteBufAllocator allocator) {
      SslHandler sslHandler;
      if (this.remoteAddress != null) {
         sslHandler = context.newHandler(allocator, this.remoteAddress.host(), this.remoteAddress.port(), this.delegatedTaskExec);
      } else {
         sslHandler = context.newHandler(allocator, this.delegatedTaskExec);
      }

      sslHandler.setHandshakeTimeout(this.handshakeTimeoutMillis, TimeUnit.MILLISECONDS);
      return sslHandler;
   }
}
