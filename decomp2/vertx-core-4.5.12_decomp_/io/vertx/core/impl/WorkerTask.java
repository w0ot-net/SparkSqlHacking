package io.vertx.core.impl;

import io.vertx.core.spi.metrics.PoolMetrics;
import java.util.concurrent.atomic.AtomicInteger;

abstract class WorkerTask extends AtomicInteger implements Runnable {
   private final PoolMetrics metrics;
   private final Object queueMetric;
   private Runnable onComplete;

   WorkerTask(PoolMetrics metrics, Object queueMetric) {
      this.metrics = metrics;
      this.queueMetric = queueMetric;
   }

   void onCompletion(Runnable continuation) {
      this.onComplete = continuation;
      if (this.addAndGet(1) > 1) {
         continuation.run();
      }

   }

   public void run() {
      Object execMetric = null;
      if (this.metrics != null) {
         execMetric = this.metrics.begin(this.queueMetric);
      }

      try {
         try {
            this.execute();
         } finally {
            if (this.addAndGet(1) > 1) {
               Runnable cont = this.onComplete;
               cont.run();
            }

         }
      } finally {
         if (this.metrics != null) {
            this.metrics.end(execMetric, true);
         }

      }

   }

   void reject() {
   }

   protected abstract void execute();
}
