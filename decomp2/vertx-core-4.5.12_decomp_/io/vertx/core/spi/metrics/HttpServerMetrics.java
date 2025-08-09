package io.vertx.core.spi.metrics;

import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.ServerWebSocket;
import io.vertx.core.spi.observability.HttpRequest;
import io.vertx.core.spi.observability.HttpResponse;

public interface HttpServerMetrics extends TCPMetrics {
   default Object requestBegin(Object socketMetric, HttpRequest request) {
      return null;
   }

   default void requestEnd(Object requestMetric, HttpRequest request, long bytesRead) {
   }

   default void requestReset(Object requestMetric) {
   }

   default void responseBegin(Object requestMetric, HttpResponse response) {
   }

   default Object responsePushed(Object socketMetric, HttpMethod method, String uri, HttpResponse response) {
      return null;
   }

   default void responseEnd(Object requestMetric, HttpResponse response, long bytesWritten) {
   }

   default Object connected(Object socketMetric, Object requestMetric, ServerWebSocket serverWebSocket) {
      return null;
   }

   default void disconnected(Object serverWebSocketMetric) {
   }

   default void requestRouted(Object requestMetric, String route) {
   }
}
