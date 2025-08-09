package org.sparkproject.jetty.util.thread.strategy;

import java.util.concurrent.Executor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.util.thread.AutoLock;
import org.sparkproject.jetty.util.thread.ExecutionStrategy;
import org.sparkproject.jetty.util.thread.Invocable;

public class ProduceExecuteConsume implements ExecutionStrategy {
   private static final Logger LOG = LoggerFactory.getLogger(ProduceExecuteConsume.class);
   private final AutoLock _lock = new AutoLock();
   private final ExecutionStrategy.Producer _producer;
   private final Executor _executor;
   private State _state;

   public ProduceExecuteConsume(ExecutionStrategy.Producer producer, Executor executor) {
      this._state = ProduceExecuteConsume.State.IDLE;
      this._producer = producer;
      this._executor = executor;
   }

   public void produce() {
      try (AutoLock lock = this._lock.lock()) {
         switch (this._state.ordinal()) {
            case 0:
               this._state = ProduceExecuteConsume.State.PRODUCE;
               break;
            case 1:
            case 2:
               this._state = ProduceExecuteConsume.State.EXECUTE;
               return;
            default:
               throw new IllegalStateException(this._state.toString());
         }
      }

      while(true) {
         Runnable task = this._producer.produce();
         if (LOG.isDebugEnabled()) {
            LOG.debug("{} produced {}", this._producer, task);
         }

         if (task == null) {
            try (AutoLock lock = this._lock.lock()) {
               switch (this._state.ordinal()) {
                  case 0:
                     throw new IllegalStateException();
                  case 1:
                     this._state = ProduceExecuteConsume.State.IDLE;
                     return;
                  case 2:
                     this._state = ProduceExecuteConsume.State.PRODUCE;
                     break;
                  default:
                     throw new IllegalStateException(this._state.toString());
               }
            }
         } else if (Invocable.getInvocationType(task) == Invocable.InvocationType.NON_BLOCKING) {
            task.run();
         } else {
            this._executor.execute(task);
         }
      }
   }

   public void dispatch() {
      this._executor.execute(this::produce);
   }

   private static enum State {
      IDLE,
      PRODUCE,
      EXECUTE;

      // $FF: synthetic method
      private static State[] $values() {
         return new State[]{IDLE, PRODUCE, EXECUTE};
      }
   }
}
