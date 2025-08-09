package io.vertx.core.spi.metrics;

import io.vertx.core.net.SocketAddress;

public interface NetworkMetrics extends Metrics {
   default void bytesRead(Object socketMetric, SocketAddress remoteAddress, long numberOfBytes) {
   }

   default void bytesWritten(Object socketMetric, SocketAddress remoteAddress, long numberOfBytes) {
   }

   default void exceptionOccurred(Object socketMetric, SocketAddress remoteAddress, Throwable t) {
   }
}
