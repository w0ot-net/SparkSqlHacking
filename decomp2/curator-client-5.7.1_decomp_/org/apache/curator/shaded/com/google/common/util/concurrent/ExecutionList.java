package org.apache.curator.shaded.com.google.common.util.concurrent;

import java.util.concurrent.Executor;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.CheckForNull;
import org.apache.curator.shaded.com.google.common.annotations.GwtIncompatible;
import org.apache.curator.shaded.com.google.common.annotations.J2ktIncompatible;
import org.apache.curator.shaded.com.google.common.base.Preconditions;
import org.apache.curator.shaded.com.google.errorprone.annotations.concurrent.GuardedBy;

@ElementTypesAreNonnullByDefault
@J2ktIncompatible
@GwtIncompatible
public final class ExecutionList {
   private static final Logger log = Logger.getLogger(ExecutionList.class.getName());
   @CheckForNull
   @GuardedBy("this")
   private RunnableExecutorPair runnables;
   @GuardedBy("this")
   private boolean executed;

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

   private static void executeListener(Runnable runnable, Executor executor) {
      try {
         executor.execute(runnable);
      } catch (RuntimeException e) {
         log.log(Level.SEVERE, "RuntimeException while executing runnable " + runnable + " with executor " + executor, e);
      }

   }

   private static final class RunnableExecutorPair {
      final Runnable runnable;
      final Executor executor;
      @CheckForNull
      RunnableExecutorPair next;

      RunnableExecutorPair(Runnable runnable, Executor executor, @CheckForNull RunnableExecutorPair next) {
         this.runnable = runnable;
         this.executor = executor;
         this.next = next;
      }
   }
}
