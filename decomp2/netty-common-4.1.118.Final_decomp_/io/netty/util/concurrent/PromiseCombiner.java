package io.netty.util.concurrent;

import io.netty.util.internal.ObjectUtil;

public final class PromiseCombiner {
   private int expectedCount;
   private int doneCount;
   private Promise aggregatePromise;
   private Throwable cause;
   private final GenericFutureListener listener;
   private final EventExecutor executor;

   /** @deprecated */
   @Deprecated
   public PromiseCombiner() {
      this(ImmediateEventExecutor.INSTANCE);
   }

   public PromiseCombiner(EventExecutor executor) {
      this.listener = new GenericFutureListener() {
         public void operationComplete(final Future future) {
            if (PromiseCombiner.this.executor.inEventLoop()) {
               this.operationComplete0(future);
            } else {
               PromiseCombiner.this.executor.execute(new Runnable() {
                  public void run() {
                     operationComplete0(future);
                  }
               });
            }

         }

         private void operationComplete0(Future future) {
            assert PromiseCombiner.this.executor.inEventLoop();

            ++PromiseCombiner.this.doneCount;
            if (!future.isSuccess() && PromiseCombiner.this.cause == null) {
               PromiseCombiner.this.cause = future.cause();
            }

            if (PromiseCombiner.this.doneCount == PromiseCombiner.this.expectedCount && PromiseCombiner.this.aggregatePromise != null) {
               PromiseCombiner.this.tryPromise();
            }

         }
      };
      this.executor = (EventExecutor)ObjectUtil.checkNotNull(executor, "executor");
   }

   /** @deprecated */
   @Deprecated
   public void add(Promise promise) {
      this.add((Future)promise);
   }

   public void add(Future future) {
      this.checkAddAllowed();
      this.checkInEventLoop();
      ++this.expectedCount;
      future.addListener(this.listener);
   }

   /** @deprecated */
   @Deprecated
   public void addAll(Promise... promises) {
      this.addAll(promises);
   }

   public void addAll(Future... futures) {
      for(Future future : futures) {
         this.add(future);
      }

   }

   public void finish(Promise aggregatePromise) {
      ObjectUtil.checkNotNull(aggregatePromise, "aggregatePromise");
      this.checkInEventLoop();
      if (this.aggregatePromise != null) {
         throw new IllegalStateException("Already finished");
      } else {
         this.aggregatePromise = aggregatePromise;
         if (this.doneCount == this.expectedCount) {
            this.tryPromise();
         }

      }
   }

   private void checkInEventLoop() {
      if (!this.executor.inEventLoop()) {
         throw new IllegalStateException("Must be called from EventExecutor thread");
      }
   }

   private boolean tryPromise() {
      return this.cause == null ? this.aggregatePromise.trySuccess((Object)null) : this.aggregatePromise.tryFailure(this.cause);
   }

   private void checkAddAllowed() {
      if (this.aggregatePromise != null) {
         throw new IllegalStateException("Adding promises is not allowed after finished adding");
      }
   }
}
