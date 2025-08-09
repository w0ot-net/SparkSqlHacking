package com.codahale.metrics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicLong;

public class InstrumentedExecutorService implements ExecutorService {
   private static final AtomicLong NAME_COUNTER = new AtomicLong();
   private final ExecutorService delegate;
   private final MetricRegistry registry;
   private final String name;
   private final Meter submitted;
   private final Counter running;
   private final Meter completed;
   private final Counter rejected;
   private final Timer idle;
   private final Timer duration;

   public InstrumentedExecutorService(ExecutorService delegate, MetricRegistry registry) {
      this(delegate, registry, "instrumented-delegate-" + NAME_COUNTER.incrementAndGet());
   }

   public InstrumentedExecutorService(ExecutorService delegate, MetricRegistry registry, String name) {
      this.delegate = delegate;
      this.registry = registry;
      this.name = name;
      this.submitted = registry.meter(MetricRegistry.name(name, "submitted"));
      this.running = registry.counter(MetricRegistry.name(name, "running"));
      this.completed = registry.meter(MetricRegistry.name(name, "completed"));
      this.rejected = registry.counter(MetricRegistry.name(name, "rejected"));
      this.idle = registry.timer(MetricRegistry.name(name, "idle"));
      this.duration = registry.timer(MetricRegistry.name(name, "duration"));
      this.registerInternalMetrics();
   }

   private void registerInternalMetrics() {
      if (this.delegate instanceof ThreadPoolExecutor) {
         ThreadPoolExecutor executor = (ThreadPoolExecutor)this.delegate;
         MetricRegistry var10000 = this.registry;
         String var10001 = MetricRegistry.name(this.name, "pool.size");
         Objects.requireNonNull(executor);
         var10000.registerGauge(var10001, executor::getPoolSize);
         var10000 = this.registry;
         var10001 = MetricRegistry.name(this.name, "pool.core");
         Objects.requireNonNull(executor);
         var10000.registerGauge(var10001, executor::getCorePoolSize);
         var10000 = this.registry;
         var10001 = MetricRegistry.name(this.name, "pool.max");
         Objects.requireNonNull(executor);
         var10000.registerGauge(var10001, executor::getMaximumPoolSize);
         BlockingQueue<Runnable> queue = executor.getQueue();
         var10000 = this.registry;
         var10001 = MetricRegistry.name(this.name, "tasks.active");
         Objects.requireNonNull(executor);
         var10000.registerGauge(var10001, executor::getActiveCount);
         var10000 = this.registry;
         var10001 = MetricRegistry.name(this.name, "tasks.completed");
         Objects.requireNonNull(executor);
         var10000.registerGauge(var10001, executor::getCompletedTaskCount);
         var10000 = this.registry;
         var10001 = MetricRegistry.name(this.name, "tasks.queued");
         Objects.requireNonNull(queue);
         var10000.registerGauge(var10001, queue::size);
         var10000 = this.registry;
         var10001 = MetricRegistry.name(this.name, "tasks.capacity");
         Objects.requireNonNull(queue);
         var10000.registerGauge(var10001, queue::remainingCapacity);
         RejectedExecutionHandler delegateHandler = executor.getRejectedExecutionHandler();
         executor.setRejectedExecutionHandler(new InstrumentedRejectedExecutionHandler(delegateHandler));
      } else if (this.delegate instanceof ForkJoinPool) {
         ForkJoinPool forkJoinPool = (ForkJoinPool)this.delegate;
         MetricRegistry var11 = this.registry;
         String var21 = MetricRegistry.name(this.name, "tasks.stolen");
         Objects.requireNonNull(forkJoinPool);
         var11.registerGauge(var21, forkJoinPool::getStealCount);
         var11 = this.registry;
         var21 = MetricRegistry.name(this.name, "tasks.queued");
         Objects.requireNonNull(forkJoinPool);
         var11.registerGauge(var21, forkJoinPool::getQueuedTaskCount);
         var11 = this.registry;
         var21 = MetricRegistry.name(this.name, "threads.active");
         Objects.requireNonNull(forkJoinPool);
         var11.registerGauge(var21, forkJoinPool::getActiveThreadCount);
         var11 = this.registry;
         var21 = MetricRegistry.name(this.name, "threads.running");
         Objects.requireNonNull(forkJoinPool);
         var11.registerGauge(var21, forkJoinPool::getRunningThreadCount);
      }

   }

   private void removeInternalMetrics() {
      if (this.delegate instanceof ThreadPoolExecutor) {
         this.registry.remove(MetricRegistry.name(this.name, "pool.size"));
         this.registry.remove(MetricRegistry.name(this.name, "pool.core"));
         this.registry.remove(MetricRegistry.name(this.name, "pool.max"));
         this.registry.remove(MetricRegistry.name(this.name, "tasks.active"));
         this.registry.remove(MetricRegistry.name(this.name, "tasks.completed"));
         this.registry.remove(MetricRegistry.name(this.name, "tasks.queued"));
         this.registry.remove(MetricRegistry.name(this.name, "tasks.capacity"));
      } else if (this.delegate instanceof ForkJoinPool) {
         this.registry.remove(MetricRegistry.name(this.name, "tasks.stolen"));
         this.registry.remove(MetricRegistry.name(this.name, "tasks.queued"));
         this.registry.remove(MetricRegistry.name(this.name, "threads.active"));
         this.registry.remove(MetricRegistry.name(this.name, "threads.running"));
      }

   }

   public void execute(Runnable runnable) {
      this.submitted.mark();
      this.delegate.execute(new InstrumentedRunnable(runnable));
   }

   public Future submit(Runnable runnable) {
      this.submitted.mark();
      return this.delegate.submit(new InstrumentedRunnable(runnable));
   }

   public Future submit(Runnable runnable, Object result) {
      this.submitted.mark();
      return this.delegate.submit(new InstrumentedRunnable(runnable), result);
   }

   public Future submit(Callable task) {
      this.submitted.mark();
      return this.delegate.submit(new InstrumentedCallable(task));
   }

   public List invokeAll(Collection tasks) throws InterruptedException {
      this.submitted.mark((long)tasks.size());
      Collection<? extends Callable<T>> instrumented = this.instrument(tasks);
      return this.delegate.invokeAll(instrumented);
   }

   public List invokeAll(Collection tasks, long timeout, TimeUnit unit) throws InterruptedException {
      this.submitted.mark((long)tasks.size());
      Collection<? extends Callable<T>> instrumented = this.instrument(tasks);
      return this.delegate.invokeAll(instrumented, timeout, unit);
   }

   public Object invokeAny(Collection tasks) throws ExecutionException, InterruptedException {
      this.submitted.mark((long)tasks.size());
      Collection<? extends Callable<T>> instrumented = this.instrument(tasks);
      return this.delegate.invokeAny(instrumented);
   }

   public Object invokeAny(Collection tasks, long timeout, TimeUnit unit) throws ExecutionException, InterruptedException, TimeoutException {
      this.submitted.mark((long)tasks.size());
      Collection<? extends Callable<T>> instrumented = this.instrument(tasks);
      return this.delegate.invokeAny(instrumented, timeout, unit);
   }

   private Collection instrument(Collection tasks) {
      List<InstrumentedCallable<T>> instrumented = new ArrayList(tasks.size());

      for(Callable task : tasks) {
         instrumented.add(new InstrumentedCallable(task));
      }

      return instrumented;
   }

   public void shutdown() {
      this.delegate.shutdown();
      this.removeInternalMetrics();
   }

   public List shutdownNow() {
      List<Runnable> remainingTasks = this.delegate.shutdownNow();
      this.removeInternalMetrics();
      return remainingTasks;
   }

   public boolean isShutdown() {
      return this.delegate.isShutdown();
   }

   public boolean isTerminated() {
      return this.delegate.isTerminated();
   }

   public boolean awaitTermination(long l, TimeUnit timeUnit) throws InterruptedException {
      return this.delegate.awaitTermination(l, timeUnit);
   }

   private class InstrumentedRejectedExecutionHandler implements RejectedExecutionHandler {
      private final RejectedExecutionHandler delegateHandler;

      public InstrumentedRejectedExecutionHandler(RejectedExecutionHandler delegateHandler) {
         this.delegateHandler = delegateHandler;
      }

      public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
         InstrumentedExecutorService.this.rejected.inc();
         this.delegateHandler.rejectedExecution(r, executor);
      }
   }

   private class InstrumentedRunnable implements Runnable {
      private final Runnable task;
      private final Timer.Context idleContext;

      InstrumentedRunnable(Runnable task) {
         this.task = task;
         this.idleContext = InstrumentedExecutorService.this.idle.time();
      }

      public void run() {
         this.idleContext.stop();
         InstrumentedExecutorService.this.running.inc();

         try {
            Timer.Context durationContext = InstrumentedExecutorService.this.duration.time();

            try {
               this.task.run();
            } catch (Throwable var9) {
               if (durationContext != null) {
                  try {
                     durationContext.close();
                  } catch (Throwable var8) {
                     var9.addSuppressed(var8);
                  }
               }

               throw var9;
            }

            if (durationContext != null) {
               durationContext.close();
            }
         } finally {
            InstrumentedExecutorService.this.running.dec();
            InstrumentedExecutorService.this.completed.mark();
         }

      }
   }

   private class InstrumentedCallable implements Callable {
      private final Callable callable;
      private final Timer.Context idleContext;

      InstrumentedCallable(Callable callable) {
         this.callable = callable;
         this.idleContext = InstrumentedExecutorService.this.idle.time();
      }

      public Object call() throws Exception {
         this.idleContext.stop();
         InstrumentedExecutorService.this.running.inc();

         Object var2;
         try {
            Timer.Context context = InstrumentedExecutorService.this.duration.time();

            try {
               var2 = this.callable.call();
            } catch (Throwable var9) {
               if (context != null) {
                  try {
                     context.close();
                  } catch (Throwable var8) {
                     var9.addSuppressed(var8);
                  }
               }

               throw var9;
            }

            if (context != null) {
               context.close();
            }
         } finally {
            InstrumentedExecutorService.this.running.dec();
            InstrumentedExecutorService.this.completed.mark();
         }

         return var2;
      }
   }
}
