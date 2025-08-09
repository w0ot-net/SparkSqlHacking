package io.vertx.core.spi.metrics;

public interface Metrics {
   String DISABLE_METRICS_PROPERTY_NAME = "vertx.disableMetrics";
   boolean METRICS_ENABLED = !Boolean.getBoolean("vertx.disableMetrics");

   default void close() {
   }
}
