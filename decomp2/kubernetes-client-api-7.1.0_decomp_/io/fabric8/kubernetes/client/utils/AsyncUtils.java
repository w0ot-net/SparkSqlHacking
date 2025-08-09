package io.fabric8.kubernetes.client.utils;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class AsyncUtils {
   private AsyncUtils() {
   }

   public static CompletableFuture withTimeout(CompletableFuture future, Duration timeout) {
      if (timeout != null && timeout.toMillis() > 0L) {
         Future<?> scheduled = Utils.schedule(Runnable::run, () -> future.completeExceptionally(new TimeoutException()), timeout.toMillis(), TimeUnit.MILLISECONDS);
         future.whenComplete((v, t) -> scheduled.cancel(true));
      }

      return future;
   }

   public static CompletableFuture retryWithExponentialBackoff(Supplier action, Consumer onCancel, Duration timeout, ExponentialBackoffIntervalCalculator retryIntervalCalculator, ShouldRetry shouldRetry) {
      CompletableFuture<T> result = new CompletableFuture();
      retryWithExponentialBackoff(result, action, onCancel, timeout, retryIntervalCalculator, shouldRetry);
      return result;
   }

   private static void retryWithExponentialBackoff(CompletableFuture result, Supplier action, Consumer onCancel, Duration timeout, ExponentialBackoffIntervalCalculator retryIntervalCalculator, ShouldRetry shouldRetry) {
      withTimeout((CompletableFuture)action.get(), timeout).whenComplete((r, t) -> {
         if (retryIntervalCalculator.shouldRetry() && !result.isDone()) {
            long retryInterval = retryIntervalCalculator.nextReconnectInterval();
            long retryValue = shouldRetry.shouldRetry(r, t, retryInterval);
            if (retryValue >= 0L) {
               if (r != null) {
                  onCancel.accept(r);
               }

               Utils.schedule(Runnable::run, () -> retryWithExponentialBackoff(result, action, onCancel, timeout, retryIntervalCalculator, shouldRetry), retryInterval, TimeUnit.MILLISECONDS);
               return;
            }
         }

         if (t != null) {
            result.completeExceptionally(t);
         } else if (!result.complete(r)) {
            onCancel.accept(r);
         }

      });
   }

   @FunctionalInterface
   public interface ShouldRetry {
      long shouldRetry(Object var1, Throwable var2, long var3);
   }
}
