package org.sparkproject.jetty.util.thread.strategy;

import java.io.Closeable;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.LongAdder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.util.IO;
import org.sparkproject.jetty.util.VirtualThreads;
import org.sparkproject.jetty.util.annotation.ManagedAttribute;
import org.sparkproject.jetty.util.annotation.ManagedObject;
import org.sparkproject.jetty.util.annotation.ManagedOperation;
import org.sparkproject.jetty.util.component.ContainerLifeCycle;
import org.sparkproject.jetty.util.thread.AutoLock;
import org.sparkproject.jetty.util.thread.ExecutionStrategy;
import org.sparkproject.jetty.util.thread.Invocable;
import org.sparkproject.jetty.util.thread.TryExecutor;

@ManagedObject("Adaptive execution strategy")
public class AdaptiveExecutionStrategy extends ContainerLifeCycle implements ExecutionStrategy, Runnable {
   private static final Logger LOG = LoggerFactory.getLogger(AdaptiveExecutionStrategy.class);
   private final AutoLock _lock = new AutoLock();
   private final LongAdder _pcMode = new LongAdder();
   private final LongAdder _picMode = new LongAdder();
   private final LongAdder _pecMode = new LongAdder();
   private final LongAdder _epcMode = new LongAdder();
   private final ExecutionStrategy.Producer _producer;
   private final Executor _executor;
   private final TryExecutor _tryExecutor;
   private final Executor _virtualExecutor;
   private State _state;
   private boolean _pending;

   public AdaptiveExecutionStrategy(ExecutionStrategy.Producer producer, Executor executor) {
      this._state = AdaptiveExecutionStrategy.State.IDLE;
      this._producer = producer;
      this._executor = executor;
      this._tryExecutor = TryExecutor.asTryExecutor(executor);
      this._virtualExecutor = VirtualThreads.getVirtualThreadsExecutor(this._executor);
      this.addBean(this._producer);
      this.addBean(this._tryExecutor);
      this.addBean(this._virtualExecutor);
      if (LOG.isDebugEnabled()) {
         LOG.debug("{} created", this);
      }

   }

   public void dispatch() {
      boolean execute = false;

      try (AutoLock l = this._lock.lock()) {
         switch (this._state.ordinal()) {
            case 0:
               if (!this._pending) {
                  this._pending = true;
                  execute = true;
               }
               break;
            case 1:
               this._state = AdaptiveExecutionStrategy.State.REPRODUCING;
         }
      }

      if (LOG.isDebugEnabled()) {
         LOG.debug("{} dispatch {}", this, execute);
      }

      if (execute) {
         this._executor.execute(this);
      }

   }

   public void produce() {
      this.tryProduce(false);
   }

   public void run() {
      this.tryProduce(true);
   }

   private void tryProduce(boolean wasPending) {
      if (LOG.isDebugEnabled()) {
         LOG.debug("{} tryProduce {}", this, wasPending);
      }

      try (AutoLock l = this._lock.lock()) {
         if (wasPending) {
            this._pending = false;
         }

         switch (this._state.ordinal()) {
            case 0:
               this._state = AdaptiveExecutionStrategy.State.PRODUCING;
               break;
            case 1:
               this._state = AdaptiveExecutionStrategy.State.REPRODUCING;
               return;
            case 2:
               return;
            default:
               throw new IllegalStateException(this.toStringLocked());
         }
      }

      boolean nonBlocking = Invocable.isNonBlockingInvocation();

      while(this.isRunning()) {
         try {
            Runnable task = this.produceTask();
            if (task == null) {
               try (AutoLock l = this._lock.lock()) {
                  switch (this._state.ordinal()) {
                     case 1:
                        this._state = AdaptiveExecutionStrategy.State.IDLE;
                        return;
                     case 2:
                        this._state = AdaptiveExecutionStrategy.State.PRODUCING;
                        break;
                     default:
                        throw new IllegalStateException(this.toStringLocked());
                  }
               }
            } else if (!this.consumeTask(task, this.selectSubStrategy(task, nonBlocking))) {
               return;
            }
         } catch (Throwable th) {
            LOG.warn("Unable to produce", th);
         }
      }

   }

   private SubStrategy selectSubStrategy(Runnable task, boolean nonBlocking) {
      Invocable.InvocationType taskType = Invocable.getInvocationType(task);
      switch (taskType) {
         case NON_BLOCKING:
            return AdaptiveExecutionStrategy.SubStrategy.PRODUCE_CONSUME;
         case EITHER:
            if (nonBlocking) {
               return AdaptiveExecutionStrategy.SubStrategy.PRODUCE_CONSUME;
            } else {
               try (AutoLock l = this._lock.lock()) {
                  if (!this._pending && !this._tryExecutor.tryExecute(this)) {
                     return AdaptiveExecutionStrategy.SubStrategy.PRODUCE_INVOKE_CONSUME;
                  } else {
                     this._pending = true;
                     this._state = AdaptiveExecutionStrategy.State.IDLE;
                     return AdaptiveExecutionStrategy.SubStrategy.EXECUTE_PRODUCE_CONSUME;
                  }
               }
            }
         case BLOCKING:
            if (!nonBlocking) {
               try (AutoLock l = this._lock.lock()) {
                  if (this._pending || this._tryExecutor.tryExecute(this)) {
                     this._pending = true;
                     this._state = AdaptiveExecutionStrategy.State.IDLE;
                     return AdaptiveExecutionStrategy.SubStrategy.EXECUTE_PRODUCE_CONSUME;
                  }
               }
            }

            return AdaptiveExecutionStrategy.SubStrategy.PRODUCE_EXECUTE_CONSUME;
         default:
            throw new IllegalStateException(String.format("taskType=%s %s", taskType, this));
      }
   }

   private boolean consumeTask(Runnable task, SubStrategy subStrategy) {
      if (LOG.isDebugEnabled()) {
         LOG.debug("ss={} t={}/{} {}", new Object[]{subStrategy, task, Invocable.getInvocationType(task), this});
      }

      switch (subStrategy.ordinal()) {
         case 0:
            this._pcMode.increment();
            this.runTask(task);
            return true;
         case 1:
            this._picMode.increment();
            this.invokeAsNonBlocking(task);
            return true;
         case 2:
            this._pecMode.increment();
            this.execute(task);
            return true;
         case 3:
            this._epcMode.increment();
            this.runTask(task);

            try (AutoLock l = this._lock.lock()) {
               if (this._state == AdaptiveExecutionStrategy.State.IDLE) {
                  this._state = AdaptiveExecutionStrategy.State.PRODUCING;
                  return true;
               }
            }

            return false;
         default:
            throw new IllegalStateException(String.format("ss=%s %s", subStrategy, this));
      }
   }

   private void runTask(Runnable task) {
      try {
         task.run();
      } catch (Throwable x) {
         LOG.warn("Task run failed", x);
      }

   }

   private void invokeAsNonBlocking(Runnable task) {
      try {
         Invocable.invokeNonBlocking(task);
      } catch (Throwable x) {
         LOG.warn("Task invoke failed", x);
      }

   }

   private Runnable produceTask() {
      try {
         return this._producer.produce();
      } catch (Throwable e) {
         LOG.warn("Task produce failed", e);
         return null;
      }
   }

   private void execute(Runnable task) {
      try {
         Executor executor = this._virtualExecutor;
         if (executor == null) {
            executor = this._executor;
         }

         executor.execute(task);
      } catch (RejectedExecutionException e) {
         if (this.isRunning()) {
            LOG.warn("Execute failed", e);
         } else {
            LOG.trace("IGNORED", e);
         }

         if (task instanceof Closeable) {
            IO.close((Closeable)task);
         }
      }

   }

   @ManagedAttribute(
      value = "whether this execution strategy uses virtual threads",
      readonly = true
   )
   public boolean isUseVirtualThreads() {
      return this._virtualExecutor != null;
   }

   @ManagedAttribute(
      value = "number of tasks consumed with PC mode",
      readonly = true
   )
   public long getPCTasksConsumed() {
      return this._pcMode.longValue();
   }

   @ManagedAttribute(
      value = "number of tasks executed with PIC mode",
      readonly = true
   )
   public long getPICTasksExecuted() {
      return this._picMode.longValue();
   }

   @ManagedAttribute(
      value = "number of tasks executed with PEC mode",
      readonly = true
   )
   public long getPECTasksExecuted() {
      return this._pecMode.longValue();
   }

   @ManagedAttribute(
      value = "number of tasks consumed with EPC mode",
      readonly = true
   )
   public long getEPCTasksConsumed() {
      return this._epcMode.longValue();
   }

   @ManagedAttribute(
      value = "whether this execution strategy is idle",
      readonly = true
   )
   public boolean isIdle() {
      boolean var2;
      try (AutoLock l = this._lock.lock()) {
         var2 = this._state == AdaptiveExecutionStrategy.State.IDLE;
      }

      return var2;
   }

   @ManagedOperation(
      value = "resets the task counts",
      impact = "ACTION"
   )
   public void reset() {
      this._pcMode.reset();
      this._epcMode.reset();
      this._pecMode.reset();
      this._picMode.reset();
   }

   public String toString() {
      try (AutoLock l = this._lock.lock()) {
         return this.toStringLocked();
      }
   }

   public String toStringLocked() {
      StringBuilder builder = new StringBuilder();
      this.getString(builder);
      this.getState(builder);
      return builder.toString();
   }

   private void getString(StringBuilder builder) {
      builder.append(this.getClass().getSimpleName());
      builder.append('@');
      builder.append(Integer.toHexString(this.hashCode()));
      builder.append('/');
      builder.append(this._producer);
      builder.append('/');
   }

   private void getState(StringBuilder builder) {
      builder.append(this._state);
      builder.append("/p=");
      builder.append(this._pending);
      builder.append('/');
      builder.append(this._tryExecutor);
      builder.append("[pc=");
      builder.append(this.getPCTasksConsumed());
      builder.append(",pic=");
      builder.append(this.getPICTasksExecuted());
      builder.append(",pec=");
      builder.append(this.getPECTasksExecuted());
      builder.append(",epc=");
      builder.append(this.getEPCTasksConsumed());
      builder.append("]");
      builder.append("@");
      builder.append(DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(ZonedDateTime.now()));
   }

   private static enum State {
      IDLE,
      PRODUCING,
      REPRODUCING;

      // $FF: synthetic method
      private static State[] $values() {
         return new State[]{IDLE, PRODUCING, REPRODUCING};
      }
   }

   private static enum SubStrategy {
      PRODUCE_CONSUME,
      PRODUCE_INVOKE_CONSUME,
      PRODUCE_EXECUTE_CONSUME,
      EXECUTE_PRODUCE_CONSUME;

      // $FF: synthetic method
      private static SubStrategy[] $values() {
         return new SubStrategy[]{PRODUCE_CONSUME, PRODUCE_INVOKE_CONSUME, PRODUCE_EXECUTE_CONSUME, EXECUTE_PRODUCE_CONSUME};
      }
   }
}
