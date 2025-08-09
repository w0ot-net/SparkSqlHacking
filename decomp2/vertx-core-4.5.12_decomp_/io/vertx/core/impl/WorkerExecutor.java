package io.vertx.core.impl;

import io.vertx.core.ThreadingModel;
import io.vertx.core.spi.metrics.PoolMetrics;
import java.util.concurrent.CountDownLatch;
import java.util.function.Consumer;

public class WorkerExecutor implements EventExecutor {
   private final WorkerPool workerPool;
   private final WorkerTaskQueue orderedTasks;
   private final ThreadLocal inThread = new ThreadLocal();

   public static WorkerExecutor unwrapWorkerExecutor() {
      Thread thread = Thread.currentThread();
      if (thread instanceof VertxThread) {
         VertxThread vertxThread = (VertxThread)thread;
         String msg = vertxThread.isWorker() ? "Cannot be called on a Vert.x worker thread" : "Cannot be called on a Vert.x event-loop thread";
         throw new IllegalStateException(msg);
      } else {
         ContextInternal ctx = VertxImpl.currentContext(thread);
         return ctx != null && ctx.threadingModel() == ThreadingModel.VIRTUAL_THREAD ? (WorkerExecutor)ctx.executor() : null;
      }
   }

   public WorkerExecutor(WorkerPool workerPool, WorkerTaskQueue orderedTasks) {
      this.workerPool = workerPool;
      this.orderedTasks = orderedTasks;
   }

   public boolean inThread() {
      return this.inThread.get() == Boolean.TRUE;
   }

   public void execute(final Runnable command) {
      PoolMetrics metrics = this.workerPool.metrics();
      Object queueMetric = metrics != null ? metrics.submitted() : null;
      WorkerTask task = new WorkerTask(metrics, queueMetric) {
         protected void execute() {
            WorkerExecutor.this.inThread.set(true);

            try {
               command.run();
            } finally {
               WorkerExecutor.this.inThread.remove();
            }

         }
      };
      this.orderedTasks.execute(task, this.workerPool.executor());
   }

   WorkerTaskQueue taskQueue() {
      return this.orderedTasks;
   }

   public CountDownLatch suspend(Consumer resumeAcceptor) {
      return this.orderedTasks.suspend(resumeAcceptor);
   }

   public interface Continuation {
      void resume(Runnable var1);

      default void resume() {
         this.resume(() -> {
         });
      }
   }
}
