package io.vertx.core.spi.metrics;

import io.vertx.core.net.SocketAddress;

public interface DatagramSocketMetrics extends NetworkMetrics {
   default void listening(String localName, SocketAddress localAddress) {
   }
}
