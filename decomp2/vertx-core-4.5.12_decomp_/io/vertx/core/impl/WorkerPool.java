package io.vertx.core.impl;

import io.vertx.core.spi.metrics.PoolMetrics;
import java.util.concurrent.ExecutorService;

public class WorkerPool {
   private final ExecutorService pool;
   private final PoolMetrics metrics;

   public WorkerPool(ExecutorService pool, PoolMetrics metrics) {
      this.pool = pool;
      this.metrics = metrics;
   }

   public ExecutorService executor() {
      return this.pool;
   }

   public PoolMetrics metrics() {
      return this.metrics;
   }

   void close() {
      if (this.metrics != null) {
         this.metrics.close();
      }

      this.pool.shutdownNow();
   }
}
