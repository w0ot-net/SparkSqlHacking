package io.netty.handler.codec.http2;

import io.netty.util.internal.ObjectUtil;

public abstract class AbstractInboundHttp2ToHttpAdapterBuilder {
   private final Http2Connection connection;
   private int maxContentLength;
   private boolean validateHttpHeaders;
   private boolean propagateSettings;

   protected AbstractInboundHttp2ToHttpAdapterBuilder(Http2Connection connection) {
      this.connection = (Http2Connection)ObjectUtil.checkNotNull(connection, "connection");
   }

   protected final AbstractInboundHttp2ToHttpAdapterBuilder self() {
      return this;
   }

   protected Http2Connection connection() {
      return this.connection;
   }

   protected int maxContentLength() {
      return this.maxContentLength;
   }

   protected AbstractInboundHttp2ToHttpAdapterBuilder maxContentLength(int maxContentLength) {
      this.maxContentLength = maxContentLength;
      return this.self();
   }

   protected boolean isValidateHttpHeaders() {
      return this.validateHttpHeaders;
   }

   protected AbstractInboundHttp2ToHttpAdapterBuilder validateHttpHeaders(boolean validate) {
      this.validateHttpHeaders = validate;
      return this.self();
   }

   protected boolean isPropagateSettings() {
      return this.propagateSettings;
   }

   protected AbstractInboundHttp2ToHttpAdapterBuilder propagateSettings(boolean propagate) {
      this.propagateSettings = propagate;
      return this.self();
   }

   protected InboundHttp2ToHttpAdapter build() {
      T instance;
      try {
         instance = (T)this.build(this.connection(), this.maxContentLength(), this.isValidateHttpHeaders(), this.isPropagateSettings());
      } catch (Throwable t) {
         throw new IllegalStateException("failed to create a new InboundHttp2ToHttpAdapter", t);
      }

      this.connection.addListener(instance);
      return instance;
   }

   protected abstract InboundHttp2ToHttpAdapter build(Http2Connection var1, int var2, boolean var3, boolean var4) throws Exception;
}
