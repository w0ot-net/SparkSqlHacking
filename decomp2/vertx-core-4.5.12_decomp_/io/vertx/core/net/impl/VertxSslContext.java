package io.vertx.core.net.impl;

import io.netty.handler.ssl.DelegatingSslContext;
import io.netty.handler.ssl.SslContext;

public abstract class VertxSslContext extends DelegatingSslContext {
   private final SslContext wrapped;

   public VertxSslContext(SslContext ctx) {
      super(ctx);
      this.wrapped = ctx;
   }

   public final SslContext unwrap() {
      return this.wrapped;
   }
}
