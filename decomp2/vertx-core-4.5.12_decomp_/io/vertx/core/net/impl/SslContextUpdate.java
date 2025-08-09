package io.vertx.core.net.impl;

public class SslContextUpdate {
   private final SslChannelProvider sslChannelProvider;
   private final boolean updated;
   private final Throwable error;

   SslContextUpdate(SslChannelProvider sslChannelProvider, boolean updated, Throwable error) {
      this.sslChannelProvider = sslChannelProvider;
      this.updated = updated;
      this.error = error;
   }

   public SslChannelProvider sslChannelProvider() {
      return this.sslChannelProvider;
   }

   public boolean isUpdated() {
      return this.updated;
   }

   public Throwable error() {
      return this.error;
   }
}
