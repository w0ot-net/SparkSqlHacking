package io.vertx.core.metrics;

import io.vertx.codegen.annotations.VertxGen;

@VertxGen(
   concrete = false
)
public interface Measured {
   default boolean isMetricsEnabled() {
      return false;
   }
}
