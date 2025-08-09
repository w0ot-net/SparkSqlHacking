package org.apache.spark.util;

import java.lang.invoke.SerializedLambda;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinWorkerThread;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.spark.SparkException;
import org.sparkproject.guava.util.concurrent.ThreadFactoryBuilder;
import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.collection.StringOps;
import scala.collection.immutable.Seq;
import scala.concurrent.Awaitable;
import scala.concurrent.CanAwait;
import scala.concurrent.ExecutionContextExecutor;
import scala.concurrent.ExecutionContextExecutorService;
import scala.concurrent.ExecutionContext.;
import scala.concurrent.duration.Duration;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.VolatileObjectRef;

public final class ThreadUtils$ {
   public static final ThreadUtils$ MODULE$ = new ThreadUtils$();
   private static final ExecutionContextExecutorService sameThreadExecutionContext;

   static {
      sameThreadExecutionContext = .MODULE$.fromExecutorService(MODULE$.sameThreadExecutorService());
   }

   private ExecutionContextExecutorService sameThreadExecutionContext() {
      return sameThreadExecutionContext;
   }

   public ExecutorService sameThreadExecutorService() {
      return new AbstractExecutorService() {
         private final ReentrantLock lock = new ReentrantLock();
         private final Condition termination = this.lock().newCondition();
         private int runningTasks = 0;
         private boolean serviceIsShutdown = false;

         private ReentrantLock lock() {
            return this.lock;
         }

         private Condition termination() {
            return this.termination;
         }

         private int runningTasks() {
            return this.runningTasks;
         }

         private void runningTasks_$eq(final int x$1) {
            this.runningTasks = x$1;
         }

         private boolean serviceIsShutdown() {
            return this.serviceIsShutdown;
         }

         private void serviceIsShutdown_$eq(final boolean x$1) {
            this.serviceIsShutdown = x$1;
         }

         public void shutdown() {
            this.lock().lock();

            try {
               this.serviceIsShutdown_$eq(true);
            } finally {
               this.lock().unlock();
            }

         }

         public List shutdownNow() {
            this.shutdown();
            return Collections.emptyList();
         }

         public boolean isShutdown() {
            this.lock().lock();

            boolean var10000;
            try {
               var10000 = this.serviceIsShutdown();
            } finally {
               this.lock().unlock();
            }

            return var10000;
         }

         public synchronized boolean isTerminated() {
            this.lock().lock();

            boolean var10000;
            try {
               var10000 = this.serviceIsShutdown() && this.runningTasks() == 0;
            } finally {
               this.lock().unlock();
            }

            return var10000;
         }

         public boolean awaitTermination(final long timeout, final TimeUnit unit) {
            long nanos = unit.toNanos(timeout);
            this.lock().lock();

            boolean var10000;
            try {
               while(nanos > 0L && !this.isTerminated()) {
                  nanos = this.termination().awaitNanos(nanos);
               }

               var10000 = this.isTerminated();
            } finally {
               this.lock().unlock();
            }

            return var10000;
         }

         public void execute(final Runnable command) {
            this.lock().lock();

            try {
               if (this.isShutdown()) {
                  throw new RejectedExecutionException("Executor already shutdown");
               }

               this.runningTasks_$eq(this.runningTasks() + 1);
            } finally {
               this.lock().unlock();
            }

            try {
               command.run();
            } finally {
               this.lock().lock();

               try {
                  this.runningTasks_$eq(this.runningTasks() - 1);
                  if (this.isTerminated()) {
                     this.termination().signalAll();
                  }
               } finally {
                  this.lock().unlock();
               }

            }

         }
      };
   }

   public ExecutionContextExecutor sameThread() {
      return this.sameThreadExecutionContext();
   }

   public ThreadFactory namedThreadFactory(final String prefix) {
      return (new ThreadFactoryBuilder()).setDaemon(true).setNameFormat(prefix + "-%d").build();
   }

   public ThreadPoolExecutor newDaemonCachedThreadPool(final String prefix) {
      ThreadFactory threadFactory = this.namedThreadFactory(prefix);
      return (ThreadPoolExecutor)Executors.newCachedThreadPool(threadFactory);
   }

   public ThreadPoolExecutor newDaemonCachedThreadPool(final String prefix, final int maxThreadNumber, final int keepAliveSeconds) {
      ThreadFactory threadFactory = this.namedThreadFactory(prefix);
      ThreadPoolExecutor threadPool = new ThreadPoolExecutor(maxThreadNumber, maxThreadNumber, scala.Int..MODULE$.int2long(keepAliveSeconds), TimeUnit.SECONDS, new LinkedBlockingQueue(), threadFactory);
      threadPool.allowCoreThreadTimeOut(true);
      return threadPool;
   }

   public int newDaemonCachedThreadPool$default$3() {
      return 60;
   }

   public ThreadPoolExecutor newDaemonFixedThreadPool(final int nThreads, final String prefix) {
      ThreadFactory threadFactory = this.namedThreadFactory(prefix);
      return (ThreadPoolExecutor)Executors.newFixedThreadPool(nThreads, threadFactory);
   }

   public ThreadPoolExecutor newDaemonSingleThreadExecutor(final String threadName) {
      ThreadFactory threadFactory = (new ThreadFactoryBuilder()).setDaemon(true).setNameFormat(threadName).build();
      return (ThreadPoolExecutor)Executors.newFixedThreadPool(1, threadFactory);
   }

   public ThreadPoolExecutor newDaemonSingleThreadExecutorWithRejectedExecutionHandler(final String threadName, final int taskQueueCapacity, final RejectedExecutionHandler rejectedExecutionHandler) {
      ThreadFactory threadFactory = (new ThreadFactoryBuilder()).setDaemon(true).setNameFormat(threadName).build();
      return new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue(taskQueueCapacity), threadFactory, rejectedExecutionHandler);
   }

   public ScheduledExecutorService newDaemonSingleThreadScheduledExecutor(final String threadName) {
      ThreadFactory threadFactory = (new ThreadFactoryBuilder()).setDaemon(true).setNameFormat(threadName).build();
      ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1, threadFactory);
      executor.setRemoveOnCancelPolicy(true);
      return executor;
   }

   public ScheduledThreadPoolExecutor newSingleThreadScheduledExecutor(final String threadName) {
      ThreadFactory threadFactory = (new ThreadFactoryBuilder()).setNameFormat(threadName).build();
      ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1, threadFactory);
      executor.setRemoveOnCancelPolicy(true);
      return executor;
   }

   public ScheduledExecutorService newDaemonThreadPoolScheduledExecutor(final String threadNamePrefix, final int numThreads) {
      ThreadFactory threadFactory = (new ThreadFactoryBuilder()).setDaemon(true).setNameFormat(threadNamePrefix + "-%d").build();
      ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(numThreads, threadFactory);
      executor.setRemoveOnCancelPolicy(true);
      return executor;
   }

   public Object runInNewThread(final String threadName, final boolean isDaemon, final Function0 body) {
      VolatileObjectRef exception = VolatileObjectRef.create(scala.None..MODULE$);
      VolatileObjectRef result = VolatileObjectRef.create((Object)null);
      Thread thread = new Thread(threadName, result, body, exception) {
         private final VolatileObjectRef result$1;
         private final Function0 body$1;
         private final VolatileObjectRef exception$1;

         public void run() {
            try {
               this.result$1.elem = this.body$1.apply();
            } catch (Throwable var5) {
               if (var5 == null || !scala.util.control.NonFatal..MODULE$.apply(var5)) {
                  throw var5;
               }

               this.exception$1.elem = new Some(var5);
               BoxedUnit var10000 = BoxedUnit.UNIT;
            }

         }

         public {
            this.result$1 = result$1;
            this.body$1 = body$1;
            this.exception$1 = exception$1;
         }
      };
      thread.setDaemon(isDaemon);
      thread.start();
      thread.join();
      Option var8 = (Option)exception.elem;
      if (var8 instanceof Some var9) {
         Throwable realException = (Throwable)var9.value();
         int x$2 = 2;
         String x$3 = this.wrapCallerStacktrace$default$2();
         throw this.wrapCallerStacktrace(realException, x$3, 2);
      } else if (scala.None..MODULE$.equals(var8)) {
         return result.elem;
      } else {
         throw new MatchError(var8);
      }
   }

   public boolean runInNewThread$default$2() {
      return true;
   }

   public Throwable wrapCallerStacktrace(final Throwable realException, final String combineMessage, final int dropStacks) {
      scala.Predef..MODULE$.require(dropStacks >= 0, () -> "dropStacks must be zero or positive");
      String simpleName = this.getClass().getSimpleName();
      StackTraceElement[] baseStackTrace = (StackTraceElement[])scala.collection.ArrayOps..MODULE$.drop$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.dropWhile$extension(scala.Predef..MODULE$.refArrayOps((Object[])Thread.currentThread().getStackTrace()), (x$1) -> BoxesRunTime.boxToBoolean($anonfun$wrapCallerStacktrace$2(simpleName, x$1)))), dropStacks);
      StackTraceElement[] extraStackTrace = (StackTraceElement[])scala.collection.ArrayOps..MODULE$.takeWhile$extension(scala.Predef..MODULE$.refArrayOps((Object[])realException.getStackTrace()), (x$2) -> BoxesRunTime.boxToBoolean($anonfun$wrapCallerStacktrace$3(simpleName, x$2)));
      StackTraceElement placeHolderStackElem = new StackTraceElement("... " + combineMessage + " ..", " ", "", -1);
      StackTraceElement[] finalStackTrace = (StackTraceElement[])scala.collection.ArrayOps..MODULE$.$plus$plus$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.$plus$plus$extension(scala.Predef..MODULE$.refArrayOps((Object[])extraStackTrace), new scala.collection.immutable..colon.colon(placeHolderStackElem, scala.collection.immutable.Nil..MODULE$), scala.reflect.ClassTag..MODULE$.apply(StackTraceElement.class))), baseStackTrace, scala.reflect.ClassTag..MODULE$.apply(StackTraceElement.class));
      realException.setStackTrace(finalStackTrace);
      return realException;
   }

   public String wrapCallerStacktrace$default$2() {
      StringOps var10000 = scala.collection.StringOps..MODULE$;
      String var10001 = scala.Predef..MODULE$.augmentString(this.getClass().getName());
      return "run in separate thread using " + var10000.stripSuffix$extension(var10001, "$");
   }

   public int wrapCallerStacktrace$default$3() {
      return 1;
   }

   public ForkJoinPool newForkJoinPool(final String prefix, final int maxThreadNumber) {
      ForkJoinPool.ForkJoinWorkerThreadFactory factory = new ForkJoinPool.ForkJoinWorkerThreadFactory(prefix) {
         public final String prefix$1;

         public ForkJoinWorkerThread newThread(final ForkJoinPool pool) {
            return new ForkJoinWorkerThread(pool) {
               public {
                  String var10001 = prefix$1;
                  this.setName(var10001 + "-" + super.getName());
               }
            };
         }

         public {
            this.prefix$1 = prefix$1;
         }
      };
      return new ForkJoinPool(maxThreadNumber, factory, (Thread.UncaughtExceptionHandler)null, false);
   }

   public Object awaitResult(final Awaitable awaitable, final Duration atMost) throws SparkException {
      return org.apache.spark.util.SparkThreadUtils..MODULE$.awaitResult(awaitable, atMost);
   }

   public Object awaitResult(final Future future, final Duration atMost) throws SparkException {
      try {
         Object var13;
         label48: {
            Duration.Infinite var10000 = scala.concurrent.duration.Duration..MODULE$.Inf();
            if (var10000 == null) {
               if (atMost == null) {
                  break label48;
               }
            } else if (var10000.equals(atMost)) {
               break label48;
            }

            var13 = future.get(scala.concurrent.duration.package..MODULE$.durationToPair(atMost)._1$mcJ$sp(), (TimeUnit)scala.concurrent.duration.package..MODULE$.durationToPair(atMost)._2());
            return var13;
         }

         var13 = future.get();
         return var13;
      } catch (Throwable var12) {
         if (var12 instanceof SparkFatalException var9) {
            throw var9.throwable();
         } else {
            if (var12 != null) {
               Option var10 = scala.util.control.NonFatal..MODULE$.unapply(var12);
               if (!var10.isEmpty()) {
                  Throwable t = (Throwable)var10.get();
                  if (!(t instanceof TimeoutException)) {
                     throw new SparkException("Exception thrown in awaitResult: ", t);
                  }
               }
            }

            throw var12;
         }
      }
   }

   public Awaitable awaitReady(final Awaitable awaitable, final Duration atMost) throws SparkException {
      try {
         CanAwait awaitPermission = null;
         return awaitable.ready(atMost, awaitPermission);
      } catch (Throwable var9) {
         if (var9 != null) {
            Option var7 = scala.util.control.NonFatal..MODULE$.unapply(var9);
            if (!var7.isEmpty()) {
               Throwable t = (Throwable)var7.get();
               if (!(t instanceof TimeoutException)) {
                  throw new SparkException("Exception thrown in awaitResult: ", t);
               }
            }
         }

         throw var9;
      }
   }

   public void shutdown(final ExecutorService executor, final Duration gracePeriod) {
      executor.shutdown();
      executor.awaitTermination(gracePeriod.toMillis(), TimeUnit.MILLISECONDS);
      if (!executor.isShutdown()) {
         executor.shutdownNow();
      }
   }

   public Duration shutdown$default$2() {
      return scala.concurrent.duration.FiniteDuration..MODULE$.apply(30L, TimeUnit.SECONDS);
   }

   public Seq parmap(final Seq in, final String prefix, final int maxThreads, final Function1 f) {
      ForkJoinPool pool = this.newForkJoinPool(prefix, maxThreads);

      Seq var10000;
      try {
         ExecutionContextExecutor ec = .MODULE$.fromExecutor(pool);
         Seq futures = (Seq)in.map((x) -> scala.concurrent.Future..MODULE$.apply(() -> f.apply(x), ec));
         scala.concurrent.Future futureSeq = scala.concurrent.Future..MODULE$.sequence(futures, scala.collection.BuildFrom..MODULE$.buildFromIterableOps(), ec);
         var10000 = (Seq)this.awaitResult((Awaitable)futureSeq, scala.concurrent.duration.Duration..MODULE$.Inf());
      } finally {
         pool.shutdownNow();
      }

      return var10000;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$wrapCallerStacktrace$2(final String simpleName$1, final StackTraceElement x$1) {
      return !x$1.getClassName().contains(simpleName$1);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$wrapCallerStacktrace$3(final String simpleName$1, final StackTraceElement x$2) {
      return !x$2.getClassName().contains(simpleName$1);
   }

   private ThreadUtils$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
