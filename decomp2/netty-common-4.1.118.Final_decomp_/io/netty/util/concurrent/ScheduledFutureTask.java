package io.netty.util.concurrent;

import io.netty.util.internal.DefaultPriorityQueue;
import io.netty.util.internal.PriorityQueueNode;
import java.util.concurrent.Callable;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

final class ScheduledFutureTask extends PromiseTask implements ScheduledFuture, PriorityQueueNode {
   private long id;
   private long deadlineNanos;
   private final long periodNanos;
   private int queueIndex = -1;

   ScheduledFutureTask(AbstractScheduledEventExecutor executor, Runnable runnable, long nanoTime) {
      super(executor, (Runnable)runnable);
      this.deadlineNanos = nanoTime;
      this.periodNanos = 0L;
   }

   ScheduledFutureTask(AbstractScheduledEventExecutor executor, Runnable runnable, long nanoTime, long period) {
      super(executor, (Runnable)runnable);
      this.deadlineNanos = nanoTime;
      this.periodNanos = validatePeriod(period);
   }

   ScheduledFutureTask(AbstractScheduledEventExecutor executor, Callable callable, long nanoTime, long period) {
      super(executor, (Callable)callable);
      this.deadlineNanos = nanoTime;
      this.periodNanos = validatePeriod(period);
   }

   ScheduledFutureTask(AbstractScheduledEventExecutor executor, Callable callable, long nanoTime) {
      super(executor, (Callable)callable);
      this.deadlineNanos = nanoTime;
      this.periodNanos = 0L;
   }

   private static long validatePeriod(long period) {
      if (period == 0L) {
         throw new IllegalArgumentException("period: 0 (expected: != 0)");
      } else {
         return period;
      }
   }

   ScheduledFutureTask setId(long id) {
      if (this.id == 0L) {
         this.id = id;
      }

      return this;
   }

   protected EventExecutor executor() {
      return super.executor();
   }

   public long deadlineNanos() {
      return this.deadlineNanos;
   }

   void setConsumed() {
      if (this.periodNanos == 0L) {
         assert this.scheduledExecutor().getCurrentTimeNanos() >= this.deadlineNanos;

         this.deadlineNanos = 0L;
      }

   }

   public long delayNanos() {
      return this.deadlineNanos == 0L ? 0L : this.delayNanos(this.scheduledExecutor().getCurrentTimeNanos());
   }

   static long deadlineToDelayNanos(long currentTimeNanos, long deadlineNanos) {
      return deadlineNanos == 0L ? 0L : Math.max(0L, deadlineNanos - currentTimeNanos);
   }

   public long delayNanos(long currentTimeNanos) {
      return deadlineToDelayNanos(currentTimeNanos, this.deadlineNanos);
   }

   public long getDelay(TimeUnit unit) {
      return unit.convert(this.delayNanos(), TimeUnit.NANOSECONDS);
   }

   public int compareTo(Delayed o) {
      if (this == o) {
         return 0;
      } else {
         ScheduledFutureTask<?> that = (ScheduledFutureTask)o;
         long d = this.deadlineNanos() - that.deadlineNanos();
         if (d < 0L) {
            return -1;
         } else if (d > 0L) {
            return 1;
         } else if (this.id < that.id) {
            return -1;
         } else {
            assert this.id != that.id;

            return 1;
         }
      }
   }

   public void run() {
      assert this.executor().inEventLoop();

      try {
         if (this.delayNanos() > 0L) {
            if (this.isCancelled()) {
               this.scheduledExecutor().scheduledTaskQueue().removeTyped(this);
            } else {
               this.scheduledExecutor().scheduleFromEventLoop(this);
            }

            return;
         }

         if (this.periodNanos == 0L) {
            if (this.setUncancellableInternal()) {
               V result = (V)this.runTask();
               this.setSuccessInternal(result);
            }
         } else if (!this.isCancelled()) {
            this.runTask();
            if (!this.executor().isShutdown()) {
               if (this.periodNanos > 0L) {
                  this.deadlineNanos += this.periodNanos;
               } else {
                  this.deadlineNanos = this.scheduledExecutor().getCurrentTimeNanos() - this.periodNanos;
               }

               if (!this.isCancelled()) {
                  this.scheduledExecutor().scheduledTaskQueue().add(this);
               }
            }
         }
      } catch (Throwable cause) {
         this.setFailureInternal(cause);
      }

   }

   private AbstractScheduledEventExecutor scheduledExecutor() {
      return (AbstractScheduledEventExecutor)this.executor();
   }

   public boolean cancel(boolean mayInterruptIfRunning) {
      boolean canceled = super.cancel(mayInterruptIfRunning);
      if (canceled) {
         this.scheduledExecutor().removeScheduled(this);
      }

      return canceled;
   }

   boolean cancelWithoutRemove(boolean mayInterruptIfRunning) {
      return super.cancel(mayInterruptIfRunning);
   }

   protected StringBuilder toStringBuilder() {
      StringBuilder buf = super.toStringBuilder();
      buf.setCharAt(buf.length() - 1, ',');
      return buf.append(" deadline: ").append(this.deadlineNanos).append(", period: ").append(this.periodNanos).append(')');
   }

   public int priorityQueueIndex(DefaultPriorityQueue queue) {
      return this.queueIndex;
   }

   public void priorityQueueIndex(DefaultPriorityQueue queue, int i) {
      this.queueIndex = i;
   }
}
