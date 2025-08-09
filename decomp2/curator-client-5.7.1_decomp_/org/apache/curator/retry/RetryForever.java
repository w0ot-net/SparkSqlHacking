package org.apache.curator.retry;

import java.util.concurrent.TimeUnit;
import org.apache.curator.RetryPolicy;
import org.apache.curator.RetrySleeper;
import org.apache.curator.shaded.com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RetryForever implements RetryPolicy {
   private static final Logger log = LoggerFactory.getLogger(RetryForever.class);
   private final int retryIntervalMs;

   public RetryForever(int retryIntervalMs) {
      Preconditions.checkArgument(retryIntervalMs > 0);
      this.retryIntervalMs = retryIntervalMs;
   }

   public boolean allowRetry(int retryCount, long elapsedTimeMs, RetrySleeper sleeper) {
      try {
         sleeper.sleepFor((long)this.retryIntervalMs, TimeUnit.MILLISECONDS);
         return true;
      } catch (InterruptedException e) {
         Thread.currentThread().interrupt();
         log.warn("Error occurred while sleeping", e);
         return false;
      }
   }
}
