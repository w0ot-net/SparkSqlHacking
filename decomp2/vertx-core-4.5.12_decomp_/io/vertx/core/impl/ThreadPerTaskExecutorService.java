package io.vertx.core.impl;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPerTaskExecutorService extends AbstractExecutorService {
   private static final int ST_RUNNING = 0;
   private static final int ST_SHUTTING_DOWN = 1;
   private static final int ST_TERMINATED = 2;
   private final AtomicInteger state = new AtomicInteger();
   private final Set threads = ConcurrentHashMap.newKeySet();
   private final CountDownLatch terminated = new CountDownLatch(1);
   private final ThreadFactory threadFactory;

   public ThreadPerTaskExecutorService(ThreadFactory threadFactory) {
      this.threadFactory = (ThreadFactory)Objects.requireNonNull(threadFactory);
   }

   public void shutdown() {
      this.shutdown(false);
   }

   public List shutdownNow() {
      this.shutdown(true);
      return Collections.emptyList();
   }

   private void shutdown(boolean now) {
      if (this.state.get() == 0 && this.state.compareAndSet(0, 1)) {
         if (this.threads.isEmpty()) {
            this.state.set(2);
            this.terminated.countDown();
         } else if (now) {
            for(Thread thread : this.threads) {
               thread.interrupt();
            }
         }
      }

   }

   public boolean isShutdown() {
      return this.state.get() != 0;
   }

   public boolean isTerminated() {
      return this.state.get() == 2;
   }

   public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
      return this.terminated.await(timeout, unit);
   }

   public void execute(Runnable command) {
      Objects.requireNonNull(command);
      if (this.state.get() == 0) {
         Thread thread = this.threadFactory.newThread(() -> {
            try {
               command.run();
            } finally {
               this.threads.remove(Thread.currentThread());
               if (this.state.get() == 1 && this.threads.isEmpty() && this.state.compareAndSet(1, 2)) {
                  this.terminated.countDown();
               }

            }

         });
         this.threads.add(thread);
         thread.start();
      } else {
         throw new RejectedExecutionException();
      }
   }
}
