package org.glassfish.jersey.internal.guava;

import java.util.concurrent.Executor;
import java.util.logging.Level;
import java.util.logging.Logger;

final class ExecutionList {
   private static final Logger log = Logger.getLogger(ExecutionList.class.getName());
   private RunnableExecutorPair runnables;
   private boolean executed;

   public ExecutionList() {
   }

   private static void executeListener(Runnable runnable, Executor executor) {
      try {
         executor.execute(runnable);
      } catch (RuntimeException e) {
         log.log(Level.SEVERE, "RuntimeException while executing runnable " + runnable + " with executor " + executor, e);
      }

   }

   public void add(Runnable runnable, Executor executor) {
      Preconditions.checkNotNull(runnable, "Runnable was null.");
      Preconditions.checkNotNull(executor, "Executor was null.");
      synchronized(this) {
         if (!this.executed) {
            this.runnables = new RunnableExecutorPair(runnable, executor, this.runnables);
            return;
         }
      }

      executeListener(runnable, executor);
   }

   public void execute() {
      RunnableExecutorPair list;
      synchronized(this) {
         if (this.executed) {
            return;
         }

         this.executed = true;
         list = this.runnables;
         this.runnables = null;
      }

      RunnableExecutorPair reversedList;
      RunnableExecutorPair tmp;
      for(reversedList = null; list != null; reversedList = tmp) {
         tmp = list;
         list = list.next;
         tmp.next = reversedList;
      }

      while(reversedList != null) {
         executeListener(reversedList.runnable, reversedList.executor);
         reversedList = reversedList.next;
      }

   }

   private static final class RunnableExecutorPair {
      final Runnable runnable;
      final Executor executor;
      RunnableExecutorPair next;

      RunnableExecutorPair(Runnable runnable, Executor executor, RunnableExecutorPair next) {
         this.runnable = runnable;
         this.executor = executor;
         this.next = next;
      }
   }
}
