package org.apache.curator.retry;

import java.util.concurrent.TimeUnit;
import org.apache.curator.RetryPolicy;
import org.apache.curator.RetrySleeper;

abstract class SleepingRetry implements RetryPolicy {
   private final int n;

   protected SleepingRetry(int n) {
      this.n = n;
   }

   public int getN() {
      return this.n;
   }

   public boolean allowRetry(int retryCount, long elapsedTimeMs, RetrySleeper sleeper) {
      if (retryCount < this.n) {
         try {
            sleeper.sleepFor(this.getSleepTimeMs(retryCount, elapsedTimeMs), TimeUnit.MILLISECONDS);
            return true;
         } catch (InterruptedException var6) {
            Thread.currentThread().interrupt();
            return false;
         }
      } else {
         return false;
      }
   }

   protected abstract long getSleepTimeMs(int var1, long var2);
}
