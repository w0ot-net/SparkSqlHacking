package org.apache.curator.retry;

import org.apache.curator.shaded.com.google.common.annotations.VisibleForTesting;

public class BoundedExponentialBackoffRetry extends ExponentialBackoffRetry {
   private final int maxSleepTimeMs;

   public BoundedExponentialBackoffRetry(int baseSleepTimeMs, int maxSleepTimeMs, int maxRetries) {
      super(baseSleepTimeMs, maxRetries);
      this.maxSleepTimeMs = maxSleepTimeMs;
   }

   @VisibleForTesting
   public int getMaxSleepTimeMs() {
      return this.maxSleepTimeMs;
   }

   protected long getSleepTimeMs(int retryCount, long elapsedTimeMs) {
      return Math.min((long)this.maxSleepTimeMs, super.getSleepTimeMs(retryCount, elapsedTimeMs));
   }
}
