package org.sparkproject.guava.util.concurrent;

import com.google.errorprone.annotations.concurrent.GuardedBy;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import org.sparkproject.guava.annotations.GwtIncompatible;
import org.sparkproject.guava.annotations.J2ktIncompatible;

@ElementTypesAreNonnullByDefault
@J2ktIncompatible
@GwtIncompatible
final class DirectExecutorService extends AbstractListeningExecutorService {
   private final Object lock = new Object();
   @GuardedBy("lock")
   private int runningTasks = 0;
   @GuardedBy("lock")
   private boolean shutdown = false;

   public void execute(Runnable command) {
      this.startTask();

      try {
         command.run();
      } finally {
         this.endTask();
      }

   }

   public boolean isShutdown() {
      synchronized(this.lock) {
         return this.shutdown;
      }
   }

   public void shutdown() {
      synchronized(this.lock) {
         this.shutdown = true;
         if (this.runningTasks == 0) {
            this.lock.notifyAll();
         }

      }
   }

   public List shutdownNow() {
      this.shutdown();
      return Collections.emptyList();
   }

   public boolean isTerminated() {
      synchronized(this.lock) {
         return this.shutdown && this.runningTasks == 0;
      }
   }

   public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
      long nanos = unit.toNanos(timeout);
      synchronized(this.lock) {
         while(!this.shutdown || this.runningTasks != 0) {
            if (nanos <= 0L) {
               return false;
            }

            long now = System.nanoTime();
            TimeUnit.NANOSECONDS.timedWait(this.lock, nanos);
            nanos -= System.nanoTime() - now;
         }

         return true;
      }
   }

   private void startTask() {
      synchronized(this.lock) {
         if (this.shutdown) {
            throw new RejectedExecutionException("Executor already shutdown");
         } else {
            ++this.runningTasks;
         }
      }
   }

   private void endTask() {
      synchronized(this.lock) {
         int numRunning = --this.runningTasks;
         if (numRunning == 0) {
            this.lock.notifyAll();
         }

      }
   }
}
