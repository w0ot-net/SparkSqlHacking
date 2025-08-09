package io.vertx.core.spi.metrics;

import io.vertx.core.http.WebSocket;
import io.vertx.core.net.SocketAddress;

public interface HttpClientMetrics extends TCPMetrics {
   default ClientMetrics createEndpointMetrics(SocketAddress remoteAddress, int maxPoolSize) {
      return null;
   }

   default void endpointConnected(ClientMetrics endpointMetric) {
   }

   default void endpointDisconnected(ClientMetrics endpointMetric) {
   }

   default Object connected(WebSocket webSocket) {
      return null;
   }

   default void disconnected(Object webSocketMetric) {
   }
}
