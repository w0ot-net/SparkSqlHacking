package org.apache.hive.common.guava;

import com.google.common.util.concurrent.AbstractListeningExecutorService;
import com.google.common.util.concurrent.ListeningExecutorService;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public final class SameThreadExecutorUtil {
   private SameThreadExecutorUtil() {
   }

   public static ListeningExecutorService sameThreadExecutor() {
      return new SameThreadExecutorService();
   }

   private static class SameThreadExecutorService extends AbstractListeningExecutorService {
      private final Lock lock;
      private final Condition termination;
      private int runningTasks;
      private boolean shutdown;

      private SameThreadExecutorService() {
         this.lock = new ReentrantLock();
         this.termination = this.lock.newCondition();
         this.runningTasks = 0;
         this.shutdown = false;
      }

      public void execute(Runnable command) {
         this.startTask();

         try {
            command.run();
         } finally {
            this.endTask();
         }

      }

      public boolean isShutdown() {
         this.lock.lock();

         boolean var1;
         try {
            var1 = this.shutdown;
         } finally {
            this.lock.unlock();
         }

         return var1;
      }

      public void shutdown() {
         this.lock.lock();

         try {
            this.shutdown = true;
         } finally {
            this.lock.unlock();
         }

      }

      public List shutdownNow() {
         this.shutdown();
         return Collections.emptyList();
      }

      public boolean isTerminated() {
         this.lock.lock();

         boolean var1;
         try {
            var1 = this.shutdown && this.runningTasks == 0;
         } finally {
            this.lock.unlock();
         }

         return var1;
      }

      public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
         long nanos = unit.toNanos(timeout);
         this.lock.lock();

         boolean var10;
         try {
            while(!this.isTerminated()) {
               if (nanos <= 0L) {
                  var10 = false;
                  return var10;
               }

               nanos = this.termination.awaitNanos(nanos);
            }

            var10 = true;
         } finally {
            this.lock.unlock();
         }

         return var10;
      }

      private void startTask() {
         this.lock.lock();

         try {
            if (this.isShutdown()) {
               throw new RejectedExecutionException("Executor already shutdown");
            }

            ++this.runningTasks;
         } finally {
            this.lock.unlock();
         }

      }

      private void endTask() {
         this.lock.lock();

         try {
            --this.runningTasks;
            if (this.isTerminated()) {
               this.termination.signalAll();
            }
         } finally {
            this.lock.unlock();
         }

      }
   }
}
