package org.apache.curator;

import org.apache.zookeeper.KeeperException;

public class SessionFailedRetryPolicy implements RetryPolicy {
   private final RetryPolicy delegatePolicy;

   public SessionFailedRetryPolicy(RetryPolicy delegatePolicy) {
      this.delegatePolicy = delegatePolicy;
   }

   public boolean allowRetry(int retryCount, long elapsedTimeMs, RetrySleeper sleeper) {
      return this.delegatePolicy.allowRetry(retryCount, elapsedTimeMs, sleeper);
   }

   public boolean allowRetry(Throwable exception) {
      return exception instanceof KeeperException.SessionExpiredException ? false : this.delegatePolicy.allowRetry(exception);
   }
}
