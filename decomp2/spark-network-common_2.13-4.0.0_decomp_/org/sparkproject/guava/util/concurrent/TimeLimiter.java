package org.sparkproject.guava.util.concurrent;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.DoNotMock;
import java.time.Duration;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.sparkproject.guava.annotations.GwtIncompatible;
import org.sparkproject.guava.annotations.J2ktIncompatible;

@DoNotMock("Use FakeTimeLimiter")
@ElementTypesAreNonnullByDefault
@J2ktIncompatible
@GwtIncompatible
public interface TimeLimiter {
   Object newProxy(Object target, Class interfaceType, long timeoutDuration, TimeUnit timeoutUnit);

   default Object newProxy(Object target, Class interfaceType, Duration timeout) {
      return this.newProxy(target, interfaceType, Internal.toNanosSaturated(timeout), TimeUnit.NANOSECONDS);
   }

   @ParametricNullness
   @CanIgnoreReturnValue
   Object callWithTimeout(Callable callable, long timeoutDuration, TimeUnit timeoutUnit) throws TimeoutException, InterruptedException, ExecutionException;

   @ParametricNullness
   @CanIgnoreReturnValue
   default Object callWithTimeout(Callable callable, Duration timeout) throws TimeoutException, InterruptedException, ExecutionException {
      return this.callWithTimeout(callable, Internal.toNanosSaturated(timeout), TimeUnit.NANOSECONDS);
   }

   @ParametricNullness
   @CanIgnoreReturnValue
   Object callUninterruptiblyWithTimeout(Callable callable, long timeoutDuration, TimeUnit timeoutUnit) throws TimeoutException, ExecutionException;

   @ParametricNullness
   @CanIgnoreReturnValue
   default Object callUninterruptiblyWithTimeout(Callable callable, Duration timeout) throws TimeoutException, ExecutionException {
      return this.callUninterruptiblyWithTimeout(callable, Internal.toNanosSaturated(timeout), TimeUnit.NANOSECONDS);
   }

   void runWithTimeout(Runnable runnable, long timeoutDuration, TimeUnit timeoutUnit) throws TimeoutException, InterruptedException;

   default void runWithTimeout(Runnable runnable, Duration timeout) throws TimeoutException, InterruptedException {
      this.runWithTimeout(runnable, Internal.toNanosSaturated(timeout), TimeUnit.NANOSECONDS);
   }

   void runUninterruptiblyWithTimeout(Runnable runnable, long timeoutDuration, TimeUnit timeoutUnit) throws TimeoutException;

   default void runUninterruptiblyWithTimeout(Runnable runnable, Duration timeout) throws TimeoutException {
      this.runUninterruptiblyWithTimeout(runnable, Internal.toNanosSaturated(timeout), TimeUnit.NANOSECONDS);
   }
}
