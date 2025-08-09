package io.netty.util.concurrent;

import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.SystemPropertyUtil;
import io.netty.util.internal.ThreadExecutorMap;
import io.netty.util.internal.ThrowableUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import org.jetbrains.annotations.Async.Schedule;

public final class GlobalEventExecutor extends AbstractScheduledEventExecutor implements OrderedEventExecutor {
   private static final InternalLogger logger = InternalLoggerFactory.getInstance(GlobalEventExecutor.class);
   private static final long SCHEDULE_QUIET_PERIOD_INTERVAL;
   public static final GlobalEventExecutor INSTANCE;
   final BlockingQueue taskQueue = new LinkedBlockingQueue();
   final ScheduledFutureTask quietPeriodTask;
   final ThreadFactory threadFactory;
   private final TaskRunner taskRunner;
   private final AtomicBoolean started;
   volatile Thread thread;
   private final Future terminationFuture;

   private GlobalEventExecutor() {
      this.quietPeriodTask = new ScheduledFutureTask(this, Executors.callable(new Runnable() {
         public void run() {
         }
      }, (Object)null), deadlineNanos(this.getCurrentTimeNanos(), SCHEDULE_QUIET_PERIOD_INTERVAL), -SCHEDULE_QUIET_PERIOD_INTERVAL);
      this.taskRunner = new TaskRunner();
      this.started = new AtomicBoolean();
      this.scheduledTaskQueue().add(this.quietPeriodTask);
      this.threadFactory = ThreadExecutorMap.apply((ThreadFactory)(new DefaultThreadFactory(DefaultThreadFactory.toPoolName(this.getClass()), false, 5, (ThreadGroup)null)), this);
      UnsupportedOperationException terminationFailure = new UnsupportedOperationException();
      ThrowableUtil.unknownStackTrace(terminationFailure, GlobalEventExecutor.class, "terminationFuture");
      this.terminationFuture = new FailedFuture(this, terminationFailure);
   }

   Runnable takeTask() {
      BlockingQueue<Runnable> taskQueue = this.taskQueue;

      Runnable task;
      do {
         ScheduledFutureTask<?> scheduledTask = this.peekScheduledTask();
         if (scheduledTask == null) {
            Runnable task = null;

            try {
               task = (Runnable)taskQueue.take();
            } catch (InterruptedException var7) {
            }

            return task;
         }

         long delayNanos = scheduledTask.delayNanos();
         task = null;
         if (delayNanos > 0L) {
            try {
               task = (Runnable)taskQueue.poll(delayNanos, TimeUnit.NANOSECONDS);
            } catch (InterruptedException var8) {
               return null;
            }
         }

         if (task == null) {
            this.fetchFromScheduledTaskQueue();
            task = (Runnable)taskQueue.poll();
         }
      } while(task == null);

      return task;
   }

   private void fetchFromScheduledTaskQueue() {
      long nanoTime = this.getCurrentTimeNanos();

      for(Runnable scheduledTask = this.pollScheduledTask(nanoTime); scheduledTask != null; scheduledTask = this.pollScheduledTask(nanoTime)) {
         this.taskQueue.add(scheduledTask);
      }

   }

   public int pendingTasks() {
      return this.taskQueue.size();
   }

   private void addTask(Runnable task) {
      this.taskQueue.add(ObjectUtil.checkNotNull(task, "task"));
   }

   public boolean inEventLoop(Thread thread) {
      return thread == this.thread;
   }

   public Future shutdownGracefully(long quietPeriod, long timeout, TimeUnit unit) {
      return this.terminationFuture();
   }

   public Future terminationFuture() {
      return this.terminationFuture;
   }

   /** @deprecated */
   @Deprecated
   public void shutdown() {
      throw new UnsupportedOperationException();
   }

   public boolean isShuttingDown() {
      return false;
   }

   public boolean isShutdown() {
      return false;
   }

   public boolean isTerminated() {
      return false;
   }

   public boolean awaitTermination(long timeout, TimeUnit unit) {
      return false;
   }

   public boolean awaitInactivity(long timeout, TimeUnit unit) throws InterruptedException {
      ObjectUtil.checkNotNull(unit, "unit");
      Thread thread = this.thread;
      if (thread == null) {
         throw new IllegalStateException("thread was not started");
      } else {
         thread.join(unit.toMillis(timeout));
         return !thread.isAlive();
      }
   }

   public void execute(Runnable task) {
      this.execute0(task);
   }

   private void execute0(@Schedule Runnable task) {
      this.addTask((Runnable)ObjectUtil.checkNotNull(task, "task"));
      if (!this.inEventLoop()) {
         this.startThread();
      }

   }

   private void startThread() {
      if (this.started.compareAndSet(false, true)) {
         final Thread callingThread = Thread.currentThread();
         ClassLoader parentCCL = (ClassLoader)AccessController.doPrivileged(new PrivilegedAction() {
            public ClassLoader run() {
               return callingThread.getContextClassLoader();
            }
         });
         setContextClassLoader(callingThread, (ClassLoader)null);

         try {
            Thread t = this.threadFactory.newThread(this.taskRunner);
            setContextClassLoader(t, (ClassLoader)null);
            this.thread = t;
            t.start();
         } finally {
            setContextClassLoader(callingThread, parentCCL);
         }
      }

   }

   private static void setContextClassLoader(final Thread t, final ClassLoader cl) {
      AccessController.doPrivileged(new PrivilegedAction() {
         public Void run() {
            t.setContextClassLoader(cl);
            return null;
         }
      });
   }

   static {
      int quietPeriod = SystemPropertyUtil.getInt("io.netty.globalEventExecutor.quietPeriodSeconds", 1);
      if (quietPeriod <= 0) {
         quietPeriod = 1;
      }

      logger.debug("-Dio.netty.globalEventExecutor.quietPeriodSeconds: {}", (Object)quietPeriod);
      SCHEDULE_QUIET_PERIOD_INTERVAL = TimeUnit.SECONDS.toNanos((long)quietPeriod);
      INSTANCE = new GlobalEventExecutor();
   }

   final class TaskRunner implements Runnable {
      public void run() {
         while(true) {
            Runnable task = GlobalEventExecutor.this.takeTask();
            if (task != null) {
               try {
                  AbstractEventExecutor.runTask(task);
               } catch (Throwable t) {
                  GlobalEventExecutor.logger.warn("Unexpected exception from the global event executor: ", t);
               }

               if (task != GlobalEventExecutor.this.quietPeriodTask) {
                  continue;
               }
            }

            Queue<ScheduledFutureTask<?>> scheduledTaskQueue = GlobalEventExecutor.this.scheduledTaskQueue;
            if (GlobalEventExecutor.this.taskQueue.isEmpty() && (scheduledTaskQueue == null || scheduledTaskQueue.size() == 1)) {
               boolean stopped = GlobalEventExecutor.this.started.compareAndSet(true, false);

               assert stopped;

               if (GlobalEventExecutor.this.taskQueue.isEmpty() || !GlobalEventExecutor.this.started.compareAndSet(false, true)) {
                  return;
               }
            }
         }
      }
   }
}
