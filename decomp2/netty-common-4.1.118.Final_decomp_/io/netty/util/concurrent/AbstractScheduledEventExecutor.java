package io.netty.util.concurrent;

import io.netty.util.internal.DefaultPriorityQueue;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.PriorityQueue;
import java.util.Comparator;
import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public abstract class AbstractScheduledEventExecutor extends AbstractEventExecutor {
   private static final Comparator SCHEDULED_FUTURE_TASK_COMPARATOR = new Comparator() {
      public int compare(ScheduledFutureTask o1, ScheduledFutureTask o2) {
         return o1.compareTo((Delayed)o2);
      }
   };
   private static final long START_TIME = System.nanoTime();
   static final Runnable WAKEUP_TASK = new Runnable() {
      public void run() {
      }
   };
   PriorityQueue scheduledTaskQueue;
   long nextTaskId;

   protected AbstractScheduledEventExecutor() {
   }

   protected AbstractScheduledEventExecutor(EventExecutorGroup parent) {
      super(parent);
   }

   protected long getCurrentTimeNanos() {
      return defaultCurrentTimeNanos();
   }

   /** @deprecated */
   @Deprecated
   protected static long nanoTime() {
      return defaultCurrentTimeNanos();
   }

   static long defaultCurrentTimeNanos() {
      return System.nanoTime() - START_TIME;
   }

   static long deadlineNanos(long nanoTime, long delay) {
      long deadlineNanos = nanoTime + delay;
      return deadlineNanos < 0L ? Long.MAX_VALUE : deadlineNanos;
   }

   protected static long deadlineToDelayNanos(long deadlineNanos) {
      return ScheduledFutureTask.deadlineToDelayNanos(defaultCurrentTimeNanos(), deadlineNanos);
   }

   protected static long initialNanoTime() {
      return START_TIME;
   }

   PriorityQueue scheduledTaskQueue() {
      if (this.scheduledTaskQueue == null) {
         this.scheduledTaskQueue = new DefaultPriorityQueue(SCHEDULED_FUTURE_TASK_COMPARATOR, 11);
      }

      return this.scheduledTaskQueue;
   }

   private static boolean isNullOrEmpty(Queue queue) {
      return queue == null || queue.isEmpty();
   }

   protected void cancelScheduledTasks() {
      assert this.inEventLoop();

      PriorityQueue<ScheduledFutureTask<?>> scheduledTaskQueue = this.scheduledTaskQueue;
      if (!isNullOrEmpty(scheduledTaskQueue)) {
         ScheduledFutureTask<?>[] scheduledTasks = (ScheduledFutureTask[])scheduledTaskQueue.toArray(new ScheduledFutureTask[0]);

         for(ScheduledFutureTask task : scheduledTasks) {
            task.cancelWithoutRemove(false);
         }

         scheduledTaskQueue.clearIgnoringIndexes();
      }
   }

   protected final Runnable pollScheduledTask() {
      return this.pollScheduledTask(this.getCurrentTimeNanos());
   }

   protected final Runnable pollScheduledTask(long nanoTime) {
      assert this.inEventLoop();

      ScheduledFutureTask<?> scheduledTask = this.peekScheduledTask();
      if (scheduledTask != null && scheduledTask.deadlineNanos() - nanoTime <= 0L) {
         this.scheduledTaskQueue.remove();
         scheduledTask.setConsumed();
         return scheduledTask;
      } else {
         return null;
      }
   }

   protected final long nextScheduledTaskNano() {
      ScheduledFutureTask<?> scheduledTask = this.peekScheduledTask();
      return scheduledTask != null ? scheduledTask.delayNanos() : -1L;
   }

   protected final long nextScheduledTaskDeadlineNanos() {
      ScheduledFutureTask<?> scheduledTask = this.peekScheduledTask();
      return scheduledTask != null ? scheduledTask.deadlineNanos() : -1L;
   }

   final ScheduledFutureTask peekScheduledTask() {
      Queue<ScheduledFutureTask<?>> scheduledTaskQueue = this.scheduledTaskQueue;
      return scheduledTaskQueue != null ? (ScheduledFutureTask)scheduledTaskQueue.peek() : null;
   }

   protected final boolean hasScheduledTasks() {
      ScheduledFutureTask<?> scheduledTask = this.peekScheduledTask();
      return scheduledTask != null && scheduledTask.deadlineNanos() <= this.getCurrentTimeNanos();
   }

   public ScheduledFuture schedule(Runnable command, long delay, TimeUnit unit) {
      ObjectUtil.checkNotNull(command, "command");
      ObjectUtil.checkNotNull(unit, "unit");
      if (delay < 0L) {
         delay = 0L;
      }

      this.validateScheduled0(delay, unit);
      return this.schedule(new ScheduledFutureTask(this, command, deadlineNanos(this.getCurrentTimeNanos(), unit.toNanos(delay))));
   }

   public ScheduledFuture schedule(Callable callable, long delay, TimeUnit unit) {
      ObjectUtil.checkNotNull(callable, "callable");
      ObjectUtil.checkNotNull(unit, "unit");
      if (delay < 0L) {
         delay = 0L;
      }

      this.validateScheduled0(delay, unit);
      return this.schedule(new ScheduledFutureTask(this, callable, deadlineNanos(this.getCurrentTimeNanos(), unit.toNanos(delay))));
   }

   public ScheduledFuture scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit) {
      ObjectUtil.checkNotNull(command, "command");
      ObjectUtil.checkNotNull(unit, "unit");
      if (initialDelay < 0L) {
         throw new IllegalArgumentException(String.format("initialDelay: %d (expected: >= 0)", initialDelay));
      } else if (period <= 0L) {
         throw new IllegalArgumentException(String.format("period: %d (expected: > 0)", period));
      } else {
         this.validateScheduled0(initialDelay, unit);
         this.validateScheduled0(period, unit);
         return this.schedule(new ScheduledFutureTask(this, command, deadlineNanos(this.getCurrentTimeNanos(), unit.toNanos(initialDelay)), unit.toNanos(period)));
      }
   }

   public ScheduledFuture scheduleWithFixedDelay(Runnable command, long initialDelay, long delay, TimeUnit unit) {
      ObjectUtil.checkNotNull(command, "command");
      ObjectUtil.checkNotNull(unit, "unit");
      if (initialDelay < 0L) {
         throw new IllegalArgumentException(String.format("initialDelay: %d (expected: >= 0)", initialDelay));
      } else if (delay <= 0L) {
         throw new IllegalArgumentException(String.format("delay: %d (expected: > 0)", delay));
      } else {
         this.validateScheduled0(initialDelay, unit);
         this.validateScheduled0(delay, unit);
         return this.schedule(new ScheduledFutureTask(this, command, deadlineNanos(this.getCurrentTimeNanos(), unit.toNanos(initialDelay)), -unit.toNanos(delay)));
      }
   }

   private void validateScheduled0(long amount, TimeUnit unit) {
      this.validateScheduled(amount, unit);
   }

   /** @deprecated */
   @Deprecated
   protected void validateScheduled(long amount, TimeUnit unit) {
   }

   final void scheduleFromEventLoop(ScheduledFutureTask task) {
      this.scheduledTaskQueue().add(task.setId(++this.nextTaskId));
   }

   private ScheduledFuture schedule(ScheduledFutureTask task) {
      if (this.inEventLoop()) {
         this.scheduleFromEventLoop(task);
      } else {
         long deadlineNanos = task.deadlineNanos();
         if (this.beforeScheduledTaskSubmitted(deadlineNanos)) {
            this.execute(task);
         } else {
            this.lazyExecute(task);
            if (this.afterScheduledTaskSubmitted(deadlineNanos)) {
               this.execute(WAKEUP_TASK);
            }
         }
      }

      return task;
   }

   final void removeScheduled(ScheduledFutureTask task) {
      assert task.isCancelled();

      if (this.inEventLoop()) {
         this.scheduledTaskQueue().removeTyped(task);
      } else {
         this.lazyExecute(task);
      }

   }

   protected boolean beforeScheduledTaskSubmitted(long deadlineNanos) {
      return true;
   }

   protected boolean afterScheduledTaskSubmitted(long deadlineNanos) {
      return true;
   }
}
