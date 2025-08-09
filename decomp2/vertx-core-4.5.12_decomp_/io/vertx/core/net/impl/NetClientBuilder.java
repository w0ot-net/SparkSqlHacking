package io.vertx.core.net.impl;

import io.vertx.core.impl.CloseFuture;
import io.vertx.core.impl.VertxInternal;
import io.vertx.core.net.NetClient;
import io.vertx.core.net.NetClientOptions;
import io.vertx.core.spi.metrics.TCPMetrics;

public class NetClientBuilder {
   private VertxInternal vertx;
   private CloseFuture closeFuture;
   private NetClientOptions options;
   private TCPMetrics metrics;

   public NetClientBuilder(VertxInternal vertx, NetClientOptions options) {
      this.vertx = vertx;
      this.options = options;
   }

   public NetClientBuilder closeFuture(CloseFuture closeFuture) {
      this.closeFuture = closeFuture;
      return this;
   }

   public NetClientBuilder metrics(TCPMetrics metrics) {
      this.metrics = metrics;
      return this;
   }

   public NetClient build() {
      NetClientImpl client = new NetClientImpl(this.vertx, this.metrics, this.options, this.closeFuture);
      this.closeFuture.add(client);
      return client;
   }
}
