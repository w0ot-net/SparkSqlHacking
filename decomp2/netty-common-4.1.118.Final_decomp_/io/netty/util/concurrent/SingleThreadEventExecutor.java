package io.netty.util.concurrent;

import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.SystemPropertyUtil;
import io.netty.util.internal.ThreadExecutorMap;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import org.jetbrains.annotations.Async.Schedule;

public abstract class SingleThreadEventExecutor extends AbstractScheduledEventExecutor implements OrderedEventExecutor {
   static final int DEFAULT_MAX_PENDING_EXECUTOR_TASKS = Math.max(16, SystemPropertyUtil.getInt("io.netty.eventexecutor.maxPendingTasks", Integer.MAX_VALUE));
   private static final InternalLogger logger = InternalLoggerFactory.getInstance(SingleThreadEventExecutor.class);
   private static final int ST_NOT_STARTED = 1;
   private static final int ST_STARTED = 2;
   private static final int ST_SHUTTING_DOWN = 3;
   private static final int ST_SHUTDOWN = 4;
   private static final int ST_TERMINATED = 5;
   private static final Runnable NOOP_TASK = new Runnable() {
      public void run() {
      }
   };
   private static final AtomicIntegerFieldUpdater STATE_UPDATER = AtomicIntegerFieldUpdater.newUpdater(SingleThreadEventExecutor.class, "state");
   private static final AtomicReferenceFieldUpdater PROPERTIES_UPDATER = AtomicReferenceFieldUpdater.newUpdater(SingleThreadEventExecutor.class, ThreadProperties.class, "threadProperties");
   private final Queue taskQueue;
   private volatile Thread thread;
   private volatile ThreadProperties threadProperties;
   private final Executor executor;
   private volatile boolean interrupted;
   private final CountDownLatch threadLock;
   private final Set shutdownHooks;
   private final boolean addTaskWakesUp;
   private final int maxPendingTasks;
   private final RejectedExecutionHandler rejectedExecutionHandler;
   private long lastExecutionTime;
   private volatile int state;
   private volatile long gracefulShutdownQuietPeriod;
   private volatile long gracefulShutdownTimeout;
   private long gracefulShutdownStartTime;
   private final Promise terminationFuture;
   private static final long SCHEDULE_PURGE_INTERVAL;

   protected SingleThreadEventExecutor(EventExecutorGroup parent, ThreadFactory threadFactory, boolean addTaskWakesUp) {
      this(parent, (Executor)(new ThreadPerTaskExecutor(threadFactory)), addTaskWakesUp);
   }

   protected SingleThreadEventExecutor(EventExecutorGroup parent, ThreadFactory threadFactory, boolean addTaskWakesUp, int maxPendingTasks, RejectedExecutionHandler rejectedHandler) {
      this(parent, (Executor)(new ThreadPerTaskExecutor(threadFactory)), addTaskWakesUp, maxPendingTasks, rejectedHandler);
   }

   protected SingleThreadEventExecutor(EventExecutorGroup parent, Executor executor, boolean addTaskWakesUp) {
      this(parent, executor, addTaskWakesUp, DEFAULT_MAX_PENDING_EXECUTOR_TASKS, RejectedExecutionHandlers.reject());
   }

   protected SingleThreadEventExecutor(EventExecutorGroup parent, Executor executor, boolean addTaskWakesUp, int maxPendingTasks, RejectedExecutionHandler rejectedHandler) {
      super(parent);
      this.threadLock = new CountDownLatch(1);
      this.shutdownHooks = new LinkedHashSet();
      this.state = 1;
      this.terminationFuture = new DefaultPromise(GlobalEventExecutor.INSTANCE);
      this.addTaskWakesUp = addTaskWakesUp;
      this.maxPendingTasks = Math.max(16, maxPendingTasks);
      this.executor = ThreadExecutorMap.apply((Executor)executor, this);
      this.taskQueue = this.newTaskQueue(this.maxPendingTasks);
      this.rejectedExecutionHandler = (RejectedExecutionHandler)ObjectUtil.checkNotNull(rejectedHandler, "rejectedHandler");
   }

   protected SingleThreadEventExecutor(EventExecutorGroup parent, Executor executor, boolean addTaskWakesUp, Queue taskQueue, RejectedExecutionHandler rejectedHandler) {
      super(parent);
      this.threadLock = new CountDownLatch(1);
      this.shutdownHooks = new LinkedHashSet();
      this.state = 1;
      this.terminationFuture = new DefaultPromise(GlobalEventExecutor.INSTANCE);
      this.addTaskWakesUp = addTaskWakesUp;
      this.maxPendingTasks = DEFAULT_MAX_PENDING_EXECUTOR_TASKS;
      this.executor = ThreadExecutorMap.apply((Executor)executor, this);
      this.taskQueue = (Queue)ObjectUtil.checkNotNull(taskQueue, "taskQueue");
      this.rejectedExecutionHandler = (RejectedExecutionHandler)ObjectUtil.checkNotNull(rejectedHandler, "rejectedHandler");
   }

   /** @deprecated */
   @Deprecated
   protected Queue newTaskQueue() {
      return this.newTaskQueue(this.maxPendingTasks);
   }

   protected Queue newTaskQueue(int maxPendingTasks) {
      return new LinkedBlockingQueue(maxPendingTasks);
   }

   protected void interruptThread() {
      Thread currentThread = this.thread;
      if (currentThread == null) {
         this.interrupted = true;
      } else {
         currentThread.interrupt();
      }

   }

   protected Runnable pollTask() {
      assert this.inEventLoop();

      return pollTaskFrom(this.taskQueue);
   }

   protected static Runnable pollTaskFrom(Queue taskQueue) {
      Runnable task;
      do {
         task = (Runnable)taskQueue.poll();
      } while(task == WAKEUP_TASK);

      return task;
   }

   protected Runnable takeTask() {
      assert this.inEventLoop();

      if (!(this.taskQueue instanceof BlockingQueue)) {
         throw new UnsupportedOperationException();
      } else {
         BlockingQueue<Runnable> taskQueue = (BlockingQueue)this.taskQueue;

         Runnable task;
         do {
            ScheduledFutureTask<?> scheduledTask = this.peekScheduledTask();
            if (scheduledTask == null) {
               Runnable task = null;

               try {
                  task = (Runnable)taskQueue.take();
                  if (task == WAKEUP_TASK) {
                     task = null;
                  }
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

         if (task == WAKEUP_TASK) {
            return null;
         } else {
            return task;
         }
      }
   }

   private boolean fetchFromScheduledTaskQueue() {
      if (this.scheduledTaskQueue != null && !this.scheduledTaskQueue.isEmpty()) {
         long nanoTime = this.getCurrentTimeNanos();

         Runnable scheduledTask;
         do {
            scheduledTask = this.pollScheduledTask(nanoTime);
            if (scheduledTask == null) {
               return true;
            }
         } while(this.taskQueue.offer(scheduledTask));

         this.scheduledTaskQueue.add((ScheduledFutureTask)scheduledTask);
         return false;
      } else {
         return true;
      }
   }

   private boolean executeExpiredScheduledTasks() {
      if (this.scheduledTaskQueue != null && !this.scheduledTaskQueue.isEmpty()) {
         long nanoTime = this.getCurrentTimeNanos();
         Runnable scheduledTask = this.pollScheduledTask(nanoTime);
         if (scheduledTask == null) {
            return false;
         } else {
            do {
               safeExecute(scheduledTask);
            } while((scheduledTask = this.pollScheduledTask(nanoTime)) != null);

            return true;
         }
      } else {
         return false;
      }
   }

   protected Runnable peekTask() {
      assert this.inEventLoop();

      return (Runnable)this.taskQueue.peek();
   }

   protected boolean hasTasks() {
      assert this.inEventLoop();

      return !this.taskQueue.isEmpty();
   }

   public int pendingTasks() {
      return this.taskQueue.size();
   }

   protected void addTask(Runnable task) {
      ObjectUtil.checkNotNull(task, "task");
      if (!this.offerTask(task)) {
         this.reject(task);
      }

   }

   final boolean offerTask(Runnable task) {
      if (this.isShutdown()) {
         reject();
      }

      return this.taskQueue.offer(task);
   }

   protected boolean removeTask(Runnable task) {
      return this.taskQueue.remove(ObjectUtil.checkNotNull(task, "task"));
   }

   protected boolean runAllTasks() {
      assert this.inEventLoop();

      boolean ranAtLeastOne = false;

      boolean fetchedAll;
      do {
         fetchedAll = this.fetchFromScheduledTaskQueue();
         if (this.runAllTasksFrom(this.taskQueue)) {
            ranAtLeastOne = true;
         }
      } while(!fetchedAll);

      if (ranAtLeastOne) {
         this.lastExecutionTime = this.getCurrentTimeNanos();
      }

      this.afterRunningAllTasks();
      return ranAtLeastOne;
   }

   protected final boolean runScheduledAndExecutorTasks(int maxDrainAttempts) {
      assert this.inEventLoop();

      int drainAttempt = 0;

      do {
         boolean ranAtLeastOneTask = this.runExistingTasksFrom(this.taskQueue) | this.executeExpiredScheduledTasks();
         if (!ranAtLeastOneTask) {
            break;
         }

         ++drainAttempt;
      } while(drainAttempt < maxDrainAttempts);

      if (drainAttempt > 0) {
         this.lastExecutionTime = this.getCurrentTimeNanos();
      }

      this.afterRunningAllTasks();
      return drainAttempt > 0;
   }

   protected final boolean runAllTasksFrom(Queue taskQueue) {
      Runnable task = pollTaskFrom(taskQueue);
      if (task == null) {
         return false;
      } else {
         do {
            safeExecute(task);
            task = pollTaskFrom(taskQueue);
         } while(task != null);

         return true;
      }
   }

   private boolean runExistingTasksFrom(Queue taskQueue) {
      Runnable task = pollTaskFrom(taskQueue);
      if (task == null) {
         return false;
      } else {
         int remaining = Math.min(this.maxPendingTasks, taskQueue.size());
         safeExecute(task);

         while(remaining-- > 0 && (task = (Runnable)taskQueue.poll()) != null) {
            safeExecute(task);
         }

         return true;
      }
   }

   protected boolean runAllTasks(long timeoutNanos) {
      this.fetchFromScheduledTaskQueue();
      Runnable task = this.pollTask();
      if (task == null) {
         this.afterRunningAllTasks();
         return false;
      } else {
         long deadline = timeoutNanos > 0L ? this.getCurrentTimeNanos() + timeoutNanos : 0L;
         long runTasks = 0L;

         long lastExecutionTime;
         while(true) {
            safeExecute(task);
            ++runTasks;
            if ((runTasks & 63L) == 0L) {
               lastExecutionTime = this.getCurrentTimeNanos();
               if (lastExecutionTime >= deadline) {
                  break;
               }
            }

            task = this.pollTask();
            if (task == null) {
               lastExecutionTime = this.getCurrentTimeNanos();
               break;
            }
         }

         this.afterRunningAllTasks();
         this.lastExecutionTime = lastExecutionTime;
         return true;
      }
   }

   protected void afterRunningAllTasks() {
   }

   protected long delayNanos(long currentTimeNanos) {
      currentTimeNanos -= initialNanoTime();
      ScheduledFutureTask<?> scheduledTask = this.peekScheduledTask();
      return scheduledTask == null ? SCHEDULE_PURGE_INTERVAL : scheduledTask.delayNanos(currentTimeNanos);
   }

   protected long deadlineNanos() {
      ScheduledFutureTask<?> scheduledTask = this.peekScheduledTask();
      return scheduledTask == null ? this.getCurrentTimeNanos() + SCHEDULE_PURGE_INTERVAL : scheduledTask.deadlineNanos();
   }

   protected void updateLastExecutionTime() {
      this.lastExecutionTime = this.getCurrentTimeNanos();
   }

   protected abstract void run();

   protected void cleanup() {
   }

   protected void wakeup(boolean inEventLoop) {
      if (!inEventLoop) {
         this.taskQueue.offer(WAKEUP_TASK);
      }

   }

   public boolean inEventLoop(Thread thread) {
      return thread == this.thread;
   }

   public void addShutdownHook(final Runnable task) {
      if (this.inEventLoop()) {
         this.shutdownHooks.add(task);
      } else {
         this.execute(new Runnable() {
            public void run() {
               SingleThreadEventExecutor.this.shutdownHooks.add(task);
            }
         });
      }

   }

   public void removeShutdownHook(final Runnable task) {
      if (this.inEventLoop()) {
         this.shutdownHooks.remove(task);
      } else {
         this.execute(new Runnable() {
            public void run() {
               SingleThreadEventExecutor.this.shutdownHooks.remove(task);
            }
         });
      }

   }

   private boolean runShutdownHooks() {
      boolean ran = false;

      while(!this.shutdownHooks.isEmpty()) {
         List<Runnable> copy = new ArrayList(this.shutdownHooks);
         this.shutdownHooks.clear();

         for(Runnable task : copy) {
            try {
               runTask(task);
            } catch (Throwable t) {
               logger.warn("Shutdown hook raised an exception.", t);
            } finally {
               ran = true;
            }
         }
      }

      if (ran) {
         this.lastExecutionTime = this.getCurrentTimeNanos();
      }

      return ran;
   }

   public Future shutdownGracefully(long quietPeriod, long timeout, TimeUnit unit) {
      ObjectUtil.checkPositiveOrZero(quietPeriod, "quietPeriod");
      if (timeout < quietPeriod) {
         throw new IllegalArgumentException("timeout: " + timeout + " (expected >= quietPeriod (" + quietPeriod + "))");
      } else {
         ObjectUtil.checkNotNull(unit, "unit");
         if (this.isShuttingDown()) {
            return this.terminationFuture();
         } else {
            boolean inEventLoop = this.inEventLoop();

            while(!this.isShuttingDown()) {
               boolean wakeup = true;
               int oldState = this.state;
               int newState;
               if (inEventLoop) {
                  newState = 3;
               } else {
                  switch (oldState) {
                     case 1:
                     case 2:
                        newState = 3;
                        break;
                     default:
                        newState = oldState;
                        wakeup = false;
                  }
               }

               if (STATE_UPDATER.compareAndSet(this, oldState, newState)) {
                  this.gracefulShutdownQuietPeriod = unit.toNanos(quietPeriod);
                  this.gracefulShutdownTimeout = unit.toNanos(timeout);
                  if (this.ensureThreadStarted(oldState)) {
                     return this.terminationFuture;
                  }

                  if (wakeup) {
                     this.taskQueue.offer(WAKEUP_TASK);
                     if (!this.addTaskWakesUp) {
                        this.wakeup(inEventLoop);
                     }
                  }

                  return this.terminationFuture();
               }
            }

            return this.terminationFuture();
         }
      }
   }

   public Future terminationFuture() {
      return this.terminationFuture;
   }

   /** @deprecated */
   @Deprecated
   public void shutdown() {
      if (!this.isShutdown()) {
         boolean inEventLoop = this.inEventLoop();

         while(!this.isShuttingDown()) {
            boolean wakeup = true;
            int oldState = this.state;
            int newState;
            if (inEventLoop) {
               newState = 4;
            } else {
               switch (oldState) {
                  case 1:
                  case 2:
                  case 3:
                     newState = 4;
                     break;
                  default:
                     newState = oldState;
                     wakeup = false;
               }
            }

            if (STATE_UPDATER.compareAndSet(this, oldState, newState)) {
               if (this.ensureThreadStarted(oldState)) {
                  return;
               }

               if (wakeup) {
                  this.taskQueue.offer(WAKEUP_TASK);
                  if (!this.addTaskWakesUp) {
                     this.wakeup(inEventLoop);
                  }
               }

               return;
            }
         }

      }
   }

   public boolean isShuttingDown() {
      return this.state >= 3;
   }

   public boolean isShutdown() {
      return this.state >= 4;
   }

   public boolean isTerminated() {
      return this.state == 5;
   }

   protected boolean confirmShutdown() {
      if (!this.isShuttingDown()) {
         return false;
      } else if (!this.inEventLoop()) {
         throw new IllegalStateException("must be invoked from an event loop");
      } else {
         this.cancelScheduledTasks();
         if (this.gracefulShutdownStartTime == 0L) {
            this.gracefulShutdownStartTime = this.getCurrentTimeNanos();
         }

         if (!this.runAllTasks() && !this.runShutdownHooks()) {
            long nanoTime = this.getCurrentTimeNanos();
            if (!this.isShutdown() && nanoTime - this.gracefulShutdownStartTime <= this.gracefulShutdownTimeout) {
               if (nanoTime - this.lastExecutionTime <= this.gracefulShutdownQuietPeriod) {
                  this.taskQueue.offer(WAKEUP_TASK);

                  try {
                     Thread.sleep(100L);
                  } catch (InterruptedException var4) {
                  }

                  return false;
               } else {
                  return true;
               }
            } else {
               return true;
            }
         } else if (this.isShutdown()) {
            return true;
         } else if (this.gracefulShutdownQuietPeriod == 0L) {
            return true;
         } else {
            this.taskQueue.offer(WAKEUP_TASK);
            return false;
         }
      }
   }

   public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
      ObjectUtil.checkNotNull(unit, "unit");
      if (this.inEventLoop()) {
         throw new IllegalStateException("cannot await termination of the current thread");
      } else {
         this.threadLock.await(timeout, unit);
         return this.isTerminated();
      }
   }

   public void execute(Runnable task) {
      this.execute0(task);
   }

   public void lazyExecute(Runnable task) {
      this.lazyExecute0(task);
   }

   private void execute0(@Schedule Runnable task) {
      ObjectUtil.checkNotNull(task, "task");
      this.execute(task, this.wakesUpForTask(task));
   }

   private void lazyExecute0(@Schedule Runnable task) {
      this.execute((Runnable)ObjectUtil.checkNotNull(task, "task"), false);
   }

   private void execute(Runnable task, boolean immediate) {
      boolean inEventLoop = this.inEventLoop();
      this.addTask(task);
      if (!inEventLoop) {
         this.startThread();
         if (this.isShutdown()) {
            boolean reject = false;

            try {
               if (this.removeTask(task)) {
                  reject = true;
               }
            } catch (UnsupportedOperationException var6) {
            }

            if (reject) {
               reject();
            }
         }
      }

      if (!this.addTaskWakesUp && immediate) {
         this.wakeup(inEventLoop);
      }

   }

   public Object invokeAny(Collection tasks) throws InterruptedException, ExecutionException {
      this.throwIfInEventLoop("invokeAny");
      return super.invokeAny(tasks);
   }

   public Object invokeAny(Collection tasks, long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
      this.throwIfInEventLoop("invokeAny");
      return super.invokeAny(tasks, timeout, unit);
   }

   public List invokeAll(Collection tasks) throws InterruptedException {
      this.throwIfInEventLoop("invokeAll");
      return super.invokeAll(tasks);
   }

   public List invokeAll(Collection tasks, long timeout, TimeUnit unit) throws InterruptedException {
      this.throwIfInEventLoop("invokeAll");
      return super.invokeAll(tasks, timeout, unit);
   }

   private void throwIfInEventLoop(String method) {
      if (this.inEventLoop()) {
         throw new RejectedExecutionException("Calling " + method + " from within the EventLoop is not allowed");
      }
   }

   public final ThreadProperties threadProperties() {
      ThreadProperties threadProperties = this.threadProperties;
      if (threadProperties == null) {
         Thread thread = this.thread;
         if (thread == null) {
            assert !this.inEventLoop();

            this.submit(NOOP_TASK).syncUninterruptibly();
            thread = this.thread;

            assert thread != null;
         }

         threadProperties = new DefaultThreadProperties(thread);
         if (!PROPERTIES_UPDATER.compareAndSet(this, (Object)null, threadProperties)) {
            threadProperties = this.threadProperties;
         }
      }

      return threadProperties;
   }

   protected boolean wakesUpForTask(Runnable task) {
      return true;
   }

   protected static void reject() {
      throw new RejectedExecutionException("event executor terminated");
   }

   protected final void reject(Runnable task) {
      this.rejectedExecutionHandler.rejected(task, this);
   }

   private void startThread() {
      if (this.state == 1 && STATE_UPDATER.compareAndSet(this, 1, 2)) {
         boolean success = false;

         try {
            this.doStartThread();
            success = true;
         } finally {
            if (!success) {
               STATE_UPDATER.compareAndSet(this, 2, 1);
            }

         }
      }

   }

   private boolean ensureThreadStarted(int oldState) {
      if (oldState == 1) {
         try {
            this.doStartThread();
         } catch (Throwable cause) {
            STATE_UPDATER.set(this, 5);
            this.terminationFuture.tryFailure(cause);
            if (!(cause instanceof Exception)) {
               PlatformDependent.throwException(cause);
            }

            return true;
         }
      }

      return false;
   }

   private void doStartThread() {
      assert this.thread == null;

      this.executor.execute(new Runnable() {
         public void run() {
            SingleThreadEventExecutor.this.thread = Thread.currentThread();
            if (SingleThreadEventExecutor.this.interrupted) {
               SingleThreadEventExecutor.this.thread.interrupt();
            }

            boolean success = false;
            Throwable unexpectedException = null;
            SingleThreadEventExecutor.this.updateLastExecutionTime();
            boolean var143 = false;

            label3568: {
               try {
                  var143 = true;
                  SingleThreadEventExecutor.this.run();
                  success = true;
                  var143 = false;
                  break label3568;
               } catch (Throwable t) {
                  unexpectedException = t;
                  SingleThreadEventExecutor.logger.warn("Unexpected exception from an event executor: ", t);
                  var143 = false;
               } finally {
                  if (var143) {
                     int oldState;
                     do {
                        oldState = SingleThreadEventExecutor.this.state;
                     } while(oldState < 3 && !SingleThreadEventExecutor.STATE_UPDATER.compareAndSet(SingleThreadEventExecutor.this, oldState, 3));

                     if (success && SingleThreadEventExecutor.this.gracefulShutdownStartTime == 0L && SingleThreadEventExecutor.logger.isErrorEnabled()) {
                        SingleThreadEventExecutor.logger.error("Buggy " + EventExecutor.class.getSimpleName() + " implementation; " + SingleThreadEventExecutor.class.getSimpleName() + ".confirmShutdown() must be called before run() implementation terminates.");
                     }

                     while(true) {
                        boolean var131 = false;

                        try {
                           var131 = true;
                           if (SingleThreadEventExecutor.this.confirmShutdown()) {
                              do {
                                 oldState = SingleThreadEventExecutor.this.state;
                              } while(oldState < 4 && !SingleThreadEventExecutor.STATE_UPDATER.compareAndSet(SingleThreadEventExecutor.this, oldState, 4));

                              SingleThreadEventExecutor.this.confirmShutdown();
                              var131 = false;
                              break;
                           }
                        } finally {
                           if (var131) {
                              boolean var119 = false;

                              try {
                                 var119 = true;
                                 SingleThreadEventExecutor.this.cleanup();
                                 var119 = false;
                              } finally {
                                 if (var119) {
                                    FastThreadLocal.removeAll();
                                    SingleThreadEventExecutor.STATE_UPDATER.set(SingleThreadEventExecutor.this, 5);
                                    SingleThreadEventExecutor.this.threadLock.countDown();
                                    int numUserTasks = SingleThreadEventExecutor.this.drainTasks();
                                    if (numUserTasks > 0 && SingleThreadEventExecutor.logger.isWarnEnabled()) {
                                       SingleThreadEventExecutor.logger.warn("An event executor terminated with non-empty task queue (" + numUserTasks + ')');
                                    }

                                    if (unexpectedException == null) {
                                       SingleThreadEventExecutor.this.terminationFuture.setSuccess((Object)null);
                                    } else {
                                       SingleThreadEventExecutor.this.terminationFuture.setFailure(unexpectedException);
                                    }

                                 }
                              }

                              FastThreadLocal.removeAll();
                              SingleThreadEventExecutor.STATE_UPDATER.set(SingleThreadEventExecutor.this, 5);
                              SingleThreadEventExecutor.this.threadLock.countDown();
                              int numUserTasks = SingleThreadEventExecutor.this.drainTasks();
                              if (numUserTasks > 0 && SingleThreadEventExecutor.logger.isWarnEnabled()) {
                                 SingleThreadEventExecutor.logger.warn("An event executor terminated with non-empty task queue (" + numUserTasks + ')');
                              }

                              if (unexpectedException == null) {
                                 SingleThreadEventExecutor.this.terminationFuture.setSuccess((Object)null);
                              } else {
                                 SingleThreadEventExecutor.this.terminationFuture.setFailure(unexpectedException);
                              }

                           }
                        }
                     }

                     boolean var107 = false;

                     try {
                        var107 = true;
                        SingleThreadEventExecutor.this.cleanup();
                        var107 = false;
                     } finally {
                        if (var107) {
                           FastThreadLocal.removeAll();
                           SingleThreadEventExecutor.STATE_UPDATER.set(SingleThreadEventExecutor.this, 5);
                           SingleThreadEventExecutor.this.threadLock.countDown();
                           int numUserTasks = SingleThreadEventExecutor.this.drainTasks();
                           if (numUserTasks > 0 && SingleThreadEventExecutor.logger.isWarnEnabled()) {
                              SingleThreadEventExecutor.logger.warn("An event executor terminated with non-empty task queue (" + numUserTasks + ')');
                           }

                           if (unexpectedException == null) {
                              SingleThreadEventExecutor.this.terminationFuture.setSuccess((Object)null);
                           } else {
                              SingleThreadEventExecutor.this.terminationFuture.setFailure(unexpectedException);
                           }

                        }
                     }

                     FastThreadLocal.removeAll();
                     SingleThreadEventExecutor.STATE_UPDATER.set(SingleThreadEventExecutor.this, 5);
                     SingleThreadEventExecutor.this.threadLock.countDown();
                     oldState = SingleThreadEventExecutor.this.drainTasks();
                     if (oldState > 0 && SingleThreadEventExecutor.logger.isWarnEnabled()) {
                        SingleThreadEventExecutor.logger.warn("An event executor terminated with non-empty task queue (" + oldState + ')');
                     }

                     if (unexpectedException == null) {
                        SingleThreadEventExecutor.this.terminationFuture.setSuccess((Object)null);
                     } else {
                        SingleThreadEventExecutor.this.terminationFuture.setFailure(unexpectedException);
                     }

                  }
               }

               int oldState;
               do {
                  oldState = SingleThreadEventExecutor.this.state;
               } while(oldState < 3 && !SingleThreadEventExecutor.STATE_UPDATER.compareAndSet(SingleThreadEventExecutor.this, oldState, 3));

               if (success && SingleThreadEventExecutor.this.gracefulShutdownStartTime == 0L && SingleThreadEventExecutor.logger.isErrorEnabled()) {
                  SingleThreadEventExecutor.logger.error("Buggy " + EventExecutor.class.getSimpleName() + " implementation; " + SingleThreadEventExecutor.class.getSimpleName() + ".confirmShutdown() must be called before run() implementation terminates.");
               }

               while(true) {
                  boolean var95 = false;

                  try {
                     var95 = true;
                     if (SingleThreadEventExecutor.this.confirmShutdown()) {
                        do {
                           oldState = SingleThreadEventExecutor.this.state;
                        } while(oldState < 4 && !SingleThreadEventExecutor.STATE_UPDATER.compareAndSet(SingleThreadEventExecutor.this, oldState, 4));

                        SingleThreadEventExecutor.this.confirmShutdown();
                        var95 = false;
                        break;
                     }
                  } finally {
                     if (var95) {
                        boolean var83 = false;

                        try {
                           var83 = true;
                           SingleThreadEventExecutor.this.cleanup();
                           var83 = false;
                        } finally {
                           if (var83) {
                              FastThreadLocal.removeAll();
                              SingleThreadEventExecutor.STATE_UPDATER.set(SingleThreadEventExecutor.this, 5);
                              SingleThreadEventExecutor.this.threadLock.countDown();
                              int numUserTasks = SingleThreadEventExecutor.this.drainTasks();
                              if (numUserTasks > 0 && SingleThreadEventExecutor.logger.isWarnEnabled()) {
                                 SingleThreadEventExecutor.logger.warn("An event executor terminated with non-empty task queue (" + numUserTasks + ')');
                              }

                              if (unexpectedException == null) {
                                 SingleThreadEventExecutor.this.terminationFuture.setSuccess((Object)null);
                              } else {
                                 SingleThreadEventExecutor.this.terminationFuture.setFailure(unexpectedException);
                              }

                           }
                        }

                        FastThreadLocal.removeAll();
                        SingleThreadEventExecutor.STATE_UPDATER.set(SingleThreadEventExecutor.this, 5);
                        SingleThreadEventExecutor.this.threadLock.countDown();
                        int numUserTasks = SingleThreadEventExecutor.this.drainTasks();
                        if (numUserTasks > 0 && SingleThreadEventExecutor.logger.isWarnEnabled()) {
                           SingleThreadEventExecutor.logger.warn("An event executor terminated with non-empty task queue (" + numUserTasks + ')');
                        }

                        if (unexpectedException == null) {
                           SingleThreadEventExecutor.this.terminationFuture.setSuccess((Object)null);
                        } else {
                           SingleThreadEventExecutor.this.terminationFuture.setFailure(unexpectedException);
                        }

                     }
                  }
               }

               boolean var71 = false;

               try {
                  var71 = true;
                  SingleThreadEventExecutor.this.cleanup();
                  var71 = false;
               } finally {
                  if (var71) {
                     FastThreadLocal.removeAll();
                     SingleThreadEventExecutor.STATE_UPDATER.set(SingleThreadEventExecutor.this, 5);
                     SingleThreadEventExecutor.this.threadLock.countDown();
                     int numUserTasks = SingleThreadEventExecutor.this.drainTasks();
                     if (numUserTasks > 0 && SingleThreadEventExecutor.logger.isWarnEnabled()) {
                        SingleThreadEventExecutor.logger.warn("An event executor terminated with non-empty task queue (" + numUserTasks + ')');
                     }

                     if (unexpectedException == null) {
                        SingleThreadEventExecutor.this.terminationFuture.setSuccess((Object)null);
                     } else {
                        SingleThreadEventExecutor.this.terminationFuture.setFailure(unexpectedException);
                     }

                  }
               }

               FastThreadLocal.removeAll();
               SingleThreadEventExecutor.STATE_UPDATER.set(SingleThreadEventExecutor.this, 5);
               SingleThreadEventExecutor.this.threadLock.countDown();
               oldState = SingleThreadEventExecutor.this.drainTasks();
               if (oldState > 0 && SingleThreadEventExecutor.logger.isWarnEnabled()) {
                  SingleThreadEventExecutor.logger.warn("An event executor terminated with non-empty task queue (" + oldState + ')');
               }

               if (unexpectedException == null) {
                  SingleThreadEventExecutor.this.terminationFuture.setSuccess((Object)null);
               } else {
                  SingleThreadEventExecutor.this.terminationFuture.setFailure(unexpectedException);
               }

               return;
            }

            int oldState;
            do {
               oldState = SingleThreadEventExecutor.this.state;
            } while(oldState < 3 && !SingleThreadEventExecutor.STATE_UPDATER.compareAndSet(SingleThreadEventExecutor.this, oldState, 3));

            if (success && SingleThreadEventExecutor.this.gracefulShutdownStartTime == 0L && SingleThreadEventExecutor.logger.isErrorEnabled()) {
               SingleThreadEventExecutor.logger.error("Buggy " + EventExecutor.class.getSimpleName() + " implementation; " + SingleThreadEventExecutor.class.getSimpleName() + ".confirmShutdown() must be called before run() implementation terminates.");
            }

            while(true) {
               boolean var59 = false;

               try {
                  var59 = true;
                  if (SingleThreadEventExecutor.this.confirmShutdown()) {
                     do {
                        oldState = SingleThreadEventExecutor.this.state;
                     } while(oldState < 4 && !SingleThreadEventExecutor.STATE_UPDATER.compareAndSet(SingleThreadEventExecutor.this, oldState, 4));

                     SingleThreadEventExecutor.this.confirmShutdown();
                     var59 = false;
                     break;
                  }
               } finally {
                  if (var59) {
                     boolean var47 = false;

                     try {
                        var47 = true;
                        SingleThreadEventExecutor.this.cleanup();
                        var47 = false;
                     } finally {
                        if (var47) {
                           FastThreadLocal.removeAll();
                           SingleThreadEventExecutor.STATE_UPDATER.set(SingleThreadEventExecutor.this, 5);
                           SingleThreadEventExecutor.this.threadLock.countDown();
                           int numUserTasks = SingleThreadEventExecutor.this.drainTasks();
                           if (numUserTasks > 0 && SingleThreadEventExecutor.logger.isWarnEnabled()) {
                              SingleThreadEventExecutor.logger.warn("An event executor terminated with non-empty task queue (" + numUserTasks + ')');
                           }

                           if (unexpectedException == null) {
                              SingleThreadEventExecutor.this.terminationFuture.setSuccess((Object)null);
                           } else {
                              SingleThreadEventExecutor.this.terminationFuture.setFailure(unexpectedException);
                           }

                        }
                     }

                     FastThreadLocal.removeAll();
                     SingleThreadEventExecutor.STATE_UPDATER.set(SingleThreadEventExecutor.this, 5);
                     SingleThreadEventExecutor.this.threadLock.countDown();
                     int numUserTasks = SingleThreadEventExecutor.this.drainTasks();
                     if (numUserTasks > 0 && SingleThreadEventExecutor.logger.isWarnEnabled()) {
                        SingleThreadEventExecutor.logger.warn("An event executor terminated with non-empty task queue (" + numUserTasks + ')');
                     }

                     if (unexpectedException == null) {
                        SingleThreadEventExecutor.this.terminationFuture.setSuccess((Object)null);
                     } else {
                        SingleThreadEventExecutor.this.terminationFuture.setFailure(unexpectedException);
                     }

                  }
               }
            }

            boolean var35 = false;

            try {
               var35 = true;
               SingleThreadEventExecutor.this.cleanup();
               var35 = false;
            } finally {
               if (var35) {
                  FastThreadLocal.removeAll();
                  SingleThreadEventExecutor.STATE_UPDATER.set(SingleThreadEventExecutor.this, 5);
                  SingleThreadEventExecutor.this.threadLock.countDown();
                  int numUserTasks = SingleThreadEventExecutor.this.drainTasks();
                  if (numUserTasks > 0 && SingleThreadEventExecutor.logger.isWarnEnabled()) {
                     SingleThreadEventExecutor.logger.warn("An event executor terminated with non-empty task queue (" + numUserTasks + ')');
                  }

                  if (unexpectedException == null) {
                     SingleThreadEventExecutor.this.terminationFuture.setSuccess((Object)null);
                  } else {
                     SingleThreadEventExecutor.this.terminationFuture.setFailure(unexpectedException);
                  }

               }
            }

            FastThreadLocal.removeAll();
            SingleThreadEventExecutor.STATE_UPDATER.set(SingleThreadEventExecutor.this, 5);
            SingleThreadEventExecutor.this.threadLock.countDown();
            oldState = SingleThreadEventExecutor.this.drainTasks();
            if (oldState > 0 && SingleThreadEventExecutor.logger.isWarnEnabled()) {
               SingleThreadEventExecutor.logger.warn("An event executor terminated with non-empty task queue (" + oldState + ')');
            }

            if (unexpectedException == null) {
               SingleThreadEventExecutor.this.terminationFuture.setSuccess((Object)null);
            } else {
               SingleThreadEventExecutor.this.terminationFuture.setFailure(unexpectedException);
            }

         }
      });
   }

   final int drainTasks() {
      int numTasks = 0;

      while(true) {
         Runnable runnable = (Runnable)this.taskQueue.poll();
         if (runnable == null) {
            return numTasks;
         }

         if (WAKEUP_TASK != runnable) {
            ++numTasks;
         }
      }
   }

   static {
      SCHEDULE_PURGE_INTERVAL = TimeUnit.SECONDS.toNanos(1L);
   }

   private static final class DefaultThreadProperties implements ThreadProperties {
      private final Thread t;

      DefaultThreadProperties(Thread t) {
         this.t = t;
      }

      public Thread.State state() {
         return this.t.getState();
      }

      public int priority() {
         return this.t.getPriority();
      }

      public boolean isInterrupted() {
         return this.t.isInterrupted();
      }

      public boolean isDaemon() {
         return this.t.isDaemon();
      }

      public String name() {
         return this.t.getName();
      }

      public long id() {
         return this.t.getId();
      }

      public StackTraceElement[] stackTrace() {
         return this.t.getStackTrace();
      }

      public boolean isAlive() {
         return this.t.isAlive();
      }
   }

   /** @deprecated */
   @Deprecated
   protected interface NonWakeupRunnable extends AbstractEventExecutor.LazyRunnable {
   }
}
