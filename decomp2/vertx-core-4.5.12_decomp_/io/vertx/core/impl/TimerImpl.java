package io.vertx.core.impl;

import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.FutureListener;
import io.netty.util.concurrent.ScheduledFuture;
import io.vertx.core.Timer;
import io.vertx.core.impl.future.FutureImpl;

class TimerImpl extends FutureImpl implements FutureListener, Timer {
   private final ScheduledFuture delegate;

   TimerImpl(ContextInternal ctx, ScheduledFuture delegate) {
      super(ctx);
      this.delegate = delegate;
   }

   public boolean cancel() {
      return this.delegate.cancel(false);
   }

   public void operationComplete(Future future) {
      if (future.isSuccess()) {
         this.tryComplete((Object)null);
      } else {
         this.tryFail(future.cause());
      }

   }
}
