package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.DoNotMock;
import java.time.Duration;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@DoNotMock("Create an AbstractIdleService")
@ElementTypesAreNonnullByDefault
@J2ktIncompatible
@GwtIncompatible
public interface Service {
   @CanIgnoreReturnValue
   Service startAsync();

   boolean isRunning();

   State state();

   @CanIgnoreReturnValue
   Service stopAsync();

   void awaitRunning();

   default void awaitRunning(Duration timeout) throws TimeoutException {
      this.awaitRunning(Internal.toNanosSaturated(timeout), TimeUnit.NANOSECONDS);
   }

   void awaitRunning(long timeout, TimeUnit unit) throws TimeoutException;

   void awaitTerminated();

   default void awaitTerminated(Duration timeout) throws TimeoutException {
      this.awaitTerminated(Internal.toNanosSaturated(timeout), TimeUnit.NANOSECONDS);
   }

   void awaitTerminated(long timeout, TimeUnit unit) throws TimeoutException;

   Throwable failureCause();

   void addListener(Listener listener, Executor executor);

   public static enum State {
      NEW,
      STARTING,
      RUNNING,
      STOPPING,
      TERMINATED,
      FAILED;

      // $FF: synthetic method
      private static State[] $values() {
         return new State[]{NEW, STARTING, RUNNING, STOPPING, TERMINATED, FAILED};
      }
   }

   public abstract static class Listener {
      public void starting() {
      }

      public void running() {
      }

      public void stopping(State from) {
      }

      public void terminated(State from) {
      }

      public void failed(State from, Throwable failure) {
      }
   }
}
