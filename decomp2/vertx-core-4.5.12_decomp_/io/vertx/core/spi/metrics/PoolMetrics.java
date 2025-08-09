package io.vertx.core.spi.metrics;

public interface PoolMetrics extends Metrics {
   default Object submitted() {
      return null;
   }

   default Object begin(Object t) {
      return null;
   }

   default void rejected(Object t) {
   }

   default void end(Object t, boolean succeeded) {
   }
}
