package org.apache.curator.framework.state;

import java.time.Duration;
import java.util.Objects;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.apache.curator.RetryPolicy;
import org.apache.curator.RetrySleeper;
import org.apache.curator.utils.ThreadUtils;

class CircuitBreaker {
   private final RetryPolicy retryPolicy;
   private final ScheduledExecutorService service;
   private boolean isOpen = false;
   private int retryCount = 0;
   private long startNanos = 0L;

   static CircuitBreaker build(RetryPolicy retryPolicy) {
      return new CircuitBreaker(retryPolicy, ThreadUtils.newSingleThreadScheduledExecutor("CircuitBreakingConnectionStateListener"));
   }

   static CircuitBreaker build(RetryPolicy retryPolicy, ScheduledExecutorService service) {
      return new CircuitBreaker(retryPolicy, service);
   }

   boolean isOpen() {
      return this.isOpen;
   }

   int getRetryCount() {
      return this.retryCount;
   }

   boolean tryToOpen(Runnable completion) {
      if (this.isOpen) {
         return false;
      } else {
         this.isOpen = true;
         this.retryCount = 0;
         this.startNanos = System.nanoTime();
         if (this.tryToRetry(completion)) {
            return true;
         } else {
            this.close();
            return false;
         }
      }
   }

   boolean tryToRetry(Runnable completion) {
      if (!this.isOpen) {
         return false;
      } else {
         long[] sleepTimeNanos = new long[]{0L};
         RetrySleeper retrySleeper = (time, unit) -> sleepTimeNanos[0] = unit.toNanos(time);
         Duration elapsedTime = Duration.ofNanos(System.nanoTime() - this.startNanos);
         if (this.retryPolicy.allowRetry(this.retryCount, elapsedTime.toMillis(), retrySleeper)) {
            ++this.retryCount;
            this.service.schedule(completion, sleepTimeNanos[0], TimeUnit.NANOSECONDS);
            return true;
         } else {
            return false;
         }
      }
   }

   boolean close() {
      boolean wasOpen = this.isOpen;
      this.retryCount = 0;
      this.isOpen = false;
      this.startNanos = 0L;
      return wasOpen;
   }

   private CircuitBreaker(RetryPolicy retryPolicy, ScheduledExecutorService service) {
      this.retryPolicy = (RetryPolicy)Objects.requireNonNull(retryPolicy, "retryPolicy cannot be null");
      this.service = (ScheduledExecutorService)Objects.requireNonNull(service, "service cannot be null");
   }
}
