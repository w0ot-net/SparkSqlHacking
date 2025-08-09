package org.apache.curator.retry;

import java.util.Random;
import org.apache.curator.shaded.com.google.common.annotations.VisibleForTesting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExponentialBackoffRetry extends SleepingRetry {
   private static final Logger log = LoggerFactory.getLogger(ExponentialBackoffRetry.class);
   private static final int MAX_RETRIES_LIMIT = 29;
   private static final int DEFAULT_MAX_SLEEP_MS = Integer.MAX_VALUE;
   private final Random random;
   private final int baseSleepTimeMs;
   private final int maxSleepMs;

   public ExponentialBackoffRetry(int baseSleepTimeMs, int maxRetries) {
      this(baseSleepTimeMs, maxRetries, Integer.MAX_VALUE);
   }

   public ExponentialBackoffRetry(int baseSleepTimeMs, int maxRetries, int maxSleepMs) {
      super(validateMaxRetries(maxRetries));
      this.random = new Random();
      this.baseSleepTimeMs = baseSleepTimeMs;
      this.maxSleepMs = maxSleepMs;
   }

   @VisibleForTesting
   public int getBaseSleepTimeMs() {
      return this.baseSleepTimeMs;
   }

   protected long getSleepTimeMs(int retryCount, long elapsedTimeMs) {
      long sleepMs = (long)this.baseSleepTimeMs * (long)Math.max(1, this.random.nextInt(1 << retryCount + 1));
      if (sleepMs > (long)this.maxSleepMs) {
         log.warn(String.format("Sleep extension too large (%d). Pinning to %d", sleepMs, this.maxSleepMs));
         sleepMs = (long)this.maxSleepMs;
      }

      return sleepMs;
   }

   private static int validateMaxRetries(int maxRetries) {
      if (maxRetries > 29) {
         log.warn(String.format("maxRetries too large (%d). Pinning to %d", maxRetries, 29));
         maxRetries = 29;
      }

      return maxRetries;
   }
}
