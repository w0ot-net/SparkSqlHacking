package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@ElementTypesAreNonnullByDefault
@J2ktIncompatible
@GwtIncompatible
public final class FakeTimeLimiter implements TimeLimiter {
   @CanIgnoreReturnValue
   public Object newProxy(Object target, Class interfaceType, long timeoutDuration, TimeUnit timeoutUnit) {
      Preconditions.checkNotNull(target);
      Preconditions.checkNotNull(interfaceType);
      Preconditions.checkNotNull(timeoutUnit);
      return target;
   }

   @ParametricNullness
   @CanIgnoreReturnValue
   public Object callWithTimeout(Callable callable, long timeoutDuration, TimeUnit timeoutUnit) throws ExecutionException {
      Preconditions.checkNotNull(callable);
      Preconditions.checkNotNull(timeoutUnit);

      try {
         return callable.call();
      } catch (RuntimeException e) {
         throw new UncheckedExecutionException(e);
      } catch (Exception e) {
         Platform.restoreInterruptIfIsInterruptedException(e);
         throw new ExecutionException(e);
      } catch (Error e) {
         throw new ExecutionError(e);
      }
   }

   @ParametricNullness
   @CanIgnoreReturnValue
   public Object callUninterruptiblyWithTimeout(Callable callable, long timeoutDuration, TimeUnit timeoutUnit) throws ExecutionException {
      return this.callWithTimeout(callable, timeoutDuration, timeoutUnit);
   }

   public void runWithTimeout(Runnable runnable, long timeoutDuration, TimeUnit timeoutUnit) {
      Preconditions.checkNotNull(runnable);
      Preconditions.checkNotNull(timeoutUnit);

      try {
         runnable.run();
      } catch (Exception e) {
         throw new UncheckedExecutionException(e);
      } catch (Error e) {
         throw new ExecutionError(e);
      }
   }

   public void runUninterruptiblyWithTimeout(Runnable runnable, long timeoutDuration, TimeUnit timeoutUnit) {
      this.runWithTimeout(runnable, timeoutDuration, timeoutUnit);
   }
}
