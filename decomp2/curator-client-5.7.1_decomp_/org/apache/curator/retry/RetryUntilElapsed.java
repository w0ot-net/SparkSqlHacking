package org.apache.curator.retry;

import org.apache.curator.RetrySleeper;

public class RetryUntilElapsed extends SleepingRetry {
   private final int maxElapsedTimeMs;
   private final int sleepMsBetweenRetries;

   public RetryUntilElapsed(int maxElapsedTimeMs, int sleepMsBetweenRetries) {
      super(Integer.MAX_VALUE);
      this.maxElapsedTimeMs = maxElapsedTimeMs;
      this.sleepMsBetweenRetries = sleepMsBetweenRetries;
   }

   public boolean allowRetry(int retryCount, long elapsedTimeMs, RetrySleeper sleeper) {
      return super.allowRetry(retryCount, elapsedTimeMs, sleeper) && elapsedTimeMs < (long)this.maxElapsedTimeMs;
   }

   protected long getSleepTimeMs(int retryCount, long elapsedTimeMs) {
      return (long)this.sleepMsBetweenRetries;
   }
}
