package org.apache.curator.retry;

public class RetryNTimes extends SleepingRetry {
   private final int sleepMsBetweenRetries;

   public RetryNTimes(int n, int sleepMsBetweenRetries) {
      super(n);
      this.sleepMsBetweenRetries = sleepMsBetweenRetries;
   }

   protected long getSleepTimeMs(int retryCount, long elapsedTimeMs) {
      return (long)this.sleepMsBetweenRetries;
   }
}
