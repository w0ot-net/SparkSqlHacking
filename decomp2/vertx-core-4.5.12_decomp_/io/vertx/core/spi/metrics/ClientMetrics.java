package io.vertx.core.spi.metrics;

public interface ClientMetrics extends Metrics {
   default Object enqueueRequest() {
      return null;
   }

   default void dequeueRequest(Object taskMetric) {
   }

   default Object requestBegin(String uri, Object request) {
      return null;
   }

   default void requestEnd(Object requestMetric) {
      this.requestEnd(requestMetric, -1L);
   }

   default void requestEnd(Object requestMetric, long bytesWritten) {
   }

   default void responseBegin(Object requestMetric, Object response) {
   }

   default void requestReset(Object requestMetric) {
   }

   default void responseEnd(Object requestMetric) {
      this.responseEnd(requestMetric, -1L);
   }

   default void responseEnd(Object requestMetric, long bytesRead) {
   }
}
