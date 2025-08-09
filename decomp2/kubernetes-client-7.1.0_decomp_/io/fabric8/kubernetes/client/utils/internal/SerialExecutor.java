package io.fabric8.kubernetes.client.utils.internal;

import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionException;

public class SerialExecutor implements Executor {
   final Queue tasks = new LinkedBlockingDeque();
   final Executor executor;
   Runnable active;
   private volatile boolean shutdown;
   private Thread thread;
   private final Object threadLock = new Object();

   public SerialExecutor(Executor executor) {
      this.executor = executor;
   }

   public synchronized void execute(Runnable r) {
      if (this.shutdown) {
         throw new RejectedExecutionException();
      } else {
         this.tasks.offer((Runnable)() -> {
            try {
               if (!this.shutdown) {
                  synchronized(this.threadLock) {
                     this.thread = Thread.currentThread();
                  }

                  r.run();
                  return;
               }
            } catch (Throwable t) {
               this.thread.getUncaughtExceptionHandler().uncaughtException(this.thread, t);
               return;
            } finally {
               synchronized(this.threadLock) {
                  this.thread = null;
               }

               Thread.interrupted();
               this.scheduleNext();
            }

         });
         if (this.active == null) {
            this.scheduleNext();
         }

      }
   }

   protected synchronized void scheduleNext() {
      if ((this.active = (Runnable)this.tasks.poll()) != null) {
         this.executor.execute(this.active);
      }

   }

   public void shutdownNow() {
      this.shutdown = true;
      this.tasks.clear();
      synchronized(this.threadLock) {
         if (this.thread != null && this.thread != Thread.currentThread()) {
            this.thread.interrupt();
         }

      }
   }

   public boolean isShutdown() {
      return this.shutdown;
   }
}
