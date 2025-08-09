package org.sparkproject.guava.util.concurrent;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.DoNotMock;
import java.time.Duration;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.sparkproject.guava.annotations.GwtCompatible;
import org.sparkproject.guava.annotations.GwtIncompatible;
import org.sparkproject.guava.annotations.J2ktIncompatible;
import org.sparkproject.guava.base.Function;
import org.sparkproject.guava.base.Preconditions;

@DoNotMock("Use FluentFuture.from(Futures.immediate*Future) or SettableFuture")
@ElementTypesAreNonnullByDefault
@GwtCompatible(
   emulated = true
)
public abstract class FluentFuture extends GwtFluentFutureCatchingSpecialization {
   FluentFuture() {
   }

   public static FluentFuture from(ListenableFuture future) {
      return (FluentFuture)(future instanceof FluentFuture ? (FluentFuture)future : new ForwardingFluentFuture(future));
   }

   /** @deprecated */
   @Deprecated
   public static FluentFuture from(FluentFuture future) {
      return (FluentFuture)Preconditions.checkNotNull(future);
   }

   @J2ktIncompatible
   @Partially.GwtIncompatible("AVAILABLE but requires exceptionType to be Throwable.class")
   public final FluentFuture catching(Class exceptionType, Function fallback, Executor executor) {
      return (FluentFuture)Futures.catching(this, exceptionType, fallback, executor);
   }

   @J2ktIncompatible
   @Partially.GwtIncompatible("AVAILABLE but requires exceptionType to be Throwable.class")
   public final FluentFuture catchingAsync(Class exceptionType, AsyncFunction fallback, Executor executor) {
      return (FluentFuture)Futures.catchingAsync(this, exceptionType, fallback, executor);
   }

   @J2ktIncompatible
   @GwtIncompatible
   public final FluentFuture withTimeout(Duration timeout, ScheduledExecutorService scheduledExecutor) {
      return this.withTimeout(Internal.toNanosSaturated(timeout), TimeUnit.NANOSECONDS, scheduledExecutor);
   }

   @J2ktIncompatible
   @GwtIncompatible
   public final FluentFuture withTimeout(long timeout, TimeUnit unit, ScheduledExecutorService scheduledExecutor) {
      return (FluentFuture)Futures.withTimeout(this, timeout, unit, scheduledExecutor);
   }

   public final FluentFuture transformAsync(AsyncFunction function, Executor executor) {
      return (FluentFuture)Futures.transformAsync(this, function, executor);
   }

   public final FluentFuture transform(Function function, Executor executor) {
      return (FluentFuture)Futures.transform(this, function, executor);
   }

   public final void addCallback(FutureCallback callback, Executor executor) {
      Futures.addCallback(this, callback, executor);
   }

   abstract static class TrustedFuture extends FluentFuture implements AbstractFuture.Trusted {
      @ParametricNullness
      @CanIgnoreReturnValue
      public final Object get() throws InterruptedException, ExecutionException {
         return super.get();
      }

      @ParametricNullness
      @CanIgnoreReturnValue
      public final Object get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
         return super.get(timeout, unit);
      }

      public final boolean isDone() {
         return super.isDone();
      }

      public final boolean isCancelled() {
         return super.isCancelled();
      }

      public final void addListener(Runnable listener, Executor executor) {
         super.addListener(listener, executor);
      }

      @CanIgnoreReturnValue
      public final boolean cancel(boolean mayInterruptIfRunning) {
         return super.cancel(mayInterruptIfRunning);
      }
   }
}
