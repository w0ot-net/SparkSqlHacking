package io.vertx.core.spi.metrics;

import io.vertx.core.net.SocketAddress;

public interface TCPMetrics extends NetworkMetrics {
   default Object connected(SocketAddress remoteAddress, String remoteName) {
      return null;
   }

   default void disconnected(Object socketMetric, SocketAddress remoteAddress) {
   }
}
