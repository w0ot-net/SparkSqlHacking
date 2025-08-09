package io.fabric8.kubernetes.client.utils;

import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.RequestConfig;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class ExponentialBackoffIntervalCalculator {
   private static final int MAX_RETRY_INTERVAL_EXPONENT = 5;
   public static final int UNLIMITED_RETRIES = -1;
   private final int initialInterval;
   private final int maxRetryIntervalExponent;
   private final int maxRetries;
   final AtomicInteger currentReconnectAttempt = new AtomicInteger();

   public ExponentialBackoffIntervalCalculator(int initialInterval, int maxRetries) {
      this.initialInterval = initialInterval;
      this.maxRetries = maxRetries;
      this.maxRetryIntervalExponent = 5;
   }

   public long getInterval(int retryIndex) {
      int exponentOfTwo = retryIndex;
      if (retryIndex > this.maxRetryIntervalExponent) {
         exponentOfTwo = this.maxRetryIntervalExponent;
      }

      return (long)this.initialInterval * (long)(1 << exponentOfTwo);
   }

   public void resetReconnectAttempts() {
      this.currentReconnectAttempt.set(0);
   }

   public final long nextReconnectInterval() {
      int exponentOfTwo = this.currentReconnectAttempt.getAndIncrement();
      return this.getInterval(exponentOfTwo);
   }

   public int getCurrentReconnectAttempt() {
      return this.currentReconnectAttempt.get();
   }

   public boolean shouldRetry() {
      return this.maxRetries < 0 || this.currentReconnectAttempt.get() < this.maxRetries;
   }

   public static ExponentialBackoffIntervalCalculator from(RequestConfig requestConfig) {
      int requestRetryBackoffInterval = (Integer)Optional.ofNullable(requestConfig).map(RequestConfig::getRequestRetryBackoffInterval).orElse(Config.DEFAULT_REQUEST_RETRY_BACKOFFINTERVAL);
      int requestRetryBackoffLimit = (Integer)Optional.ofNullable(requestConfig).map(RequestConfig::getRequestRetryBackoffLimit).orElse(Config.DEFAULT_REQUEST_RETRY_BACKOFFLIMIT);
      return new ExponentialBackoffIntervalCalculator(requestRetryBackoffInterval, requestRetryBackoffLimit);
   }
}
