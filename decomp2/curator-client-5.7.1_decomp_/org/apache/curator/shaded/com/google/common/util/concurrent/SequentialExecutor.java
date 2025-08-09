package org.apache.curator.shaded.com.google.common.util.concurrent;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.CheckForNull;
import org.apache.curator.shaded.com.google.common.annotations.GwtIncompatible;
import org.apache.curator.shaded.com.google.common.annotations.J2ktIncompatible;
import org.apache.curator.shaded.com.google.common.base.Preconditions;
import org.apache.curator.shaded.com.google.errorprone.annotations.concurrent.GuardedBy;
import org.apache.curator.shaded.com.google.j2objc.annotations.RetainedWith;

@ElementTypesAreNonnullByDefault
@J2ktIncompatible
@GwtIncompatible
final class SequentialExecutor implements Executor {
   private static final Logger log = Logger.getLogger(SequentialExecutor.class.getName());
   private final Executor executor;
   @GuardedBy("queue")
   private final Deque queue = new ArrayDeque();
   @GuardedBy("queue")
   private WorkerRunningState workerRunningState;
   @GuardedBy("queue")
   private long workerRunCount;
   @RetainedWith
   private final QueueWorker worker;

   SequentialExecutor(Executor executor) {
      this.workerRunningState = SequentialExecutor.WorkerRunningState.IDLE;
      this.workerRunCount = 0L;
      this.worker = new QueueWorker();
      this.executor = (Executor)Preconditions.checkNotNull(executor);
   }

   public void execute(final Runnable task) {
      Preconditions.checkNotNull(task);
      Runnable submittedTask;
      long oldRunCount;
      synchronized(this.queue) {
         if (this.workerRunningState == SequentialExecutor.WorkerRunningState.RUNNING || this.workerRunningState == SequentialExecutor.WorkerRunningState.QUEUED) {
            this.queue.add(task);
            return;
         }

         oldRunCount = this.workerRunCount;
         submittedTask = new Runnable() {
            public void run() {
               task.run();
            }

            public String toString() {
               return task.toString();
            }
         };
         this.queue.add(submittedTask);
         this.workerRunningState = SequentialExecutor.WorkerRunningState.QUEUING;
      }

      try {
         this.executor.execute(this.worker);
      } catch (Error | RuntimeException var12) {
         Throwable t = var12;
         synchronized(this.queue) {
            boolean removed = (this.workerRunningState == SequentialExecutor.WorkerRunningState.IDLE || this.workerRunningState == SequentialExecutor.WorkerRunningState.QUEUING) && this.queue.removeLastOccurrence(submittedTask);
            if (t instanceof RejectedExecutionException && !removed) {
               return;
            }

            throw t;
         }
      }

      boolean alreadyMarkedQueued = this.workerRunningState != SequentialExecutor.WorkerRunningState.QUEUING;
      if (!alreadyMarkedQueued) {
         synchronized(this.queue) {
            if (this.workerRunCount == oldRunCount && this.workerRunningState == SequentialExecutor.WorkerRunningState.QUEUING) {
               this.workerRunningState = SequentialExecutor.WorkerRunningState.QUEUED;
            }

         }
      }
   }

   public String toString() {
      return "SequentialExecutor@" + System.identityHashCode(this) + "{" + this.executor + "}";
   }

   static enum WorkerRunningState {
      IDLE,
      QUEUING,
      QUEUED,
      RUNNING;

      // $FF: synthetic method
      private static WorkerRunningState[] $values() {
         return new WorkerRunningState[]{IDLE, QUEUING, QUEUED, RUNNING};
      }
   }

   private final class QueueWorker implements Runnable {
      @CheckForNull
      Runnable task;

      private QueueWorker() {
      }

      public void run() {
         try {
            this.workOnQueue();
         } catch (Error e) {
            synchronized(SequentialExecutor.this.queue) {
               SequentialExecutor.this.workerRunningState = SequentialExecutor.WorkerRunningState.IDLE;
            }

            throw e;
         }
      }

      private void workOnQueue() {
         boolean interruptedDuringTask = false;
         boolean hasSetRunning = false;

         try {
            while(true) {
               synchronized(SequentialExecutor.this.queue) {
                  if (!hasSetRunning) {
                     if (SequentialExecutor.this.workerRunningState == SequentialExecutor.WorkerRunningState.RUNNING) {
                        return;
                     }

                     SequentialExecutor.this.workerRunCount++;
                     SequentialExecutor.this.workerRunningState = SequentialExecutor.WorkerRunningState.RUNNING;
                     hasSetRunning = true;
                  }

                  this.task = (Runnable)SequentialExecutor.this.queue.poll();
                  if (this.task == null) {
                     SequentialExecutor.this.workerRunningState = SequentialExecutor.WorkerRunningState.IDLE;
                     return;
                  }
               }

               interruptedDuringTask |= Thread.interrupted();

               try {
                  this.task.run();
               } catch (RuntimeException e) {
                  SequentialExecutor.log.log(Level.SEVERE, "Exception while executing runnable " + this.task, e);
               } finally {
                  this.task = null;
               }
            }
         } finally {
            if (interruptedDuringTask) {
               Thread.currentThread().interrupt();
            }

         }
      }

      public String toString() {
         Runnable currentlyRunning = this.task;
         return currentlyRunning != null ? "SequentialExecutorWorker{running=" + currentlyRunning + "}" : "SequentialExecutorWorker{state=" + SequentialExecutor.this.workerRunningState + "}";
      }
   }
}
