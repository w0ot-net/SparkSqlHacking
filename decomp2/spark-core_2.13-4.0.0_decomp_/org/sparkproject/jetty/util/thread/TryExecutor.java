package org.sparkproject.jetty.util.thread;

import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;

public interface TryExecutor extends Executor {
   TryExecutor NO_TRY = new TryExecutor() {
      public boolean tryExecute(Runnable task) {
         return false;
      }

      public String toString() {
         return "NO_TRY";
      }
   };

   boolean tryExecute(Runnable var1);

   default void execute(Runnable task) {
      if (!this.tryExecute(task)) {
         throw new RejectedExecutionException();
      }
   }

   static TryExecutor asTryExecutor(Executor executor) {
      return (TryExecutor)(executor instanceof TryExecutor ? (TryExecutor)executor : new NoTryExecutor(executor));
   }

   public static class NoTryExecutor implements TryExecutor {
      private final Executor executor;

      public NoTryExecutor(Executor executor) {
         this.executor = executor;
      }

      public void execute(Runnable task) {
         this.executor.execute(task);
      }

      public boolean tryExecute(Runnable task) {
         return false;
      }

      public String toString() {
         return String.format("%s@%x[%s]", this.getClass().getSimpleName(), this.hashCode(), this.executor);
      }
   }
}
