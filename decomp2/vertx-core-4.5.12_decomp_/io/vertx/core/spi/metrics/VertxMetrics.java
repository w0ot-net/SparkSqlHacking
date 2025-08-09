package io.vertx.core.spi.metrics;

import io.vertx.core.Vertx;
import io.vertx.core.datagram.DatagramSocketOptions;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.metrics.Measured;
import io.vertx.core.net.NetClientOptions;
import io.vertx.core.net.NetServerOptions;
import io.vertx.core.net.SocketAddress;

public interface VertxMetrics extends Metrics, Measured {
   default EventBusMetrics createEventBusMetrics() {
      return null;
   }

   default HttpServerMetrics createHttpServerMetrics(HttpServerOptions options, SocketAddress localAddress) {
      return null;
   }

   default ClientMetrics createClientMetrics(SocketAddress remoteAddress, String type, String namespace) {
      return null;
   }

   default HttpClientMetrics createHttpClientMetrics(HttpClientOptions options) {
      return null;
   }

   default TCPMetrics createNetServerMetrics(NetServerOptions options, SocketAddress localAddress) {
      return null;
   }

   default TCPMetrics createNetClientMetrics(NetClientOptions options) {
      return null;
   }

   default DatagramSocketMetrics createDatagramSocketMetrics(DatagramSocketOptions options) {
      return null;
   }

   default PoolMetrics createPoolMetrics(String poolType, String poolName, int maxPoolSize) {
      return null;
   }

   default void vertxCreated(Vertx vertx) {
   }
}
