package org.sparkproject.guava.util.concurrent;

import com.google.errorprone.annotations.concurrent.LazyInit;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.annotation.CheckForNull;
import org.sparkproject.guava.annotations.GwtIncompatible;
import org.sparkproject.guava.annotations.J2ktIncompatible;
import org.sparkproject.guava.base.Preconditions;

@ElementTypesAreNonnullByDefault
@J2ktIncompatible
@GwtIncompatible
final class TimeoutFuture extends FluentFuture.TrustedFuture {
   @CheckForNull
   @LazyInit
   private ListenableFuture delegateRef;
   @CheckForNull
   @LazyInit
   private ScheduledFuture timer;

   static ListenableFuture create(ListenableFuture delegate, long time, TimeUnit unit, ScheduledExecutorService scheduledExecutor) {
      TimeoutFuture<V> result = new TimeoutFuture(delegate);
      Fire<V> fire = new Fire(result);
      result.timer = scheduledExecutor.schedule(fire, time, unit);
      delegate.addListener(fire, MoreExecutors.directExecutor());
      return result;
   }

   private TimeoutFuture(ListenableFuture delegate) {
      this.delegateRef = (ListenableFuture)Preconditions.checkNotNull(delegate);
   }

   @CheckForNull
   protected String pendingToString() {
      ListenableFuture<? extends V> localInputFuture = this.delegateRef;
      ScheduledFuture<?> localTimer = this.timer;
      if (localInputFuture != null) {
         String message = "inputFuture=[" + localInputFuture + "]";
         if (localTimer != null) {
            long delay = localTimer.getDelay(TimeUnit.MILLISECONDS);
            if (delay > 0L) {
               message = message + ", remaining delay=[" + delay + " ms]";
            }
         }

         return message;
      } else {
         return null;
      }
   }

   protected void afterDone() {
      ListenableFuture<? extends V> delegate = this.delegateRef;
      this.maybePropagateCancellationTo(delegate);
      Future<?> localTimer = this.timer;
      if (localTimer != null) {
         localTimer.cancel(false);
      }

      this.delegateRef = null;
      this.timer = null;
   }

   private static final class Fire implements Runnable {
      @CheckForNull
      @LazyInit
      TimeoutFuture timeoutFutureRef;

      Fire(TimeoutFuture timeoutFuture) {
         this.timeoutFutureRef = timeoutFuture;
      }

      public void run() {
         TimeoutFuture<V> timeoutFuture = this.timeoutFutureRef;
         if (timeoutFuture != null) {
            ListenableFuture<V> delegate = timeoutFuture.delegateRef;
            if (delegate != null) {
               this.timeoutFutureRef = null;
               if (delegate.isDone()) {
                  timeoutFuture.setFuture(delegate);
               } else {
                  try {
                     ScheduledFuture<?> timer = timeoutFuture.timer;
                     timeoutFuture.timer = null;
                     String message = "Timed out";

                     try {
                        if (timer != null) {
                           long overDelayMs = Math.abs(timer.getDelay(TimeUnit.MILLISECONDS));
                           if (overDelayMs > 10L) {
                              message = message + " (timeout delayed by " + overDelayMs + " ms after scheduled time)";
                           }
                        }

                        message = message + ": " + delegate;
                     } finally {
                        timeoutFuture.setException(new TimeoutFutureException(message));
                     }
                  } finally {
                     delegate.cancel(true);
                  }
               }

            }
         }
      }
   }

   private static final class TimeoutFutureException extends TimeoutException {
      private TimeoutFutureException(String message) {
         super(message);
      }

      public synchronized Throwable fillInStackTrace() {
         this.setStackTrace(new StackTraceElement[0]);
         return this;
      }
   }
}
