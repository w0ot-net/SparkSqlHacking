package io.vertx.core.spi.metrics;

import io.vertx.core.metrics.Measured;

public interface MetricsProvider extends Measured {
   Metrics getMetrics();
}
