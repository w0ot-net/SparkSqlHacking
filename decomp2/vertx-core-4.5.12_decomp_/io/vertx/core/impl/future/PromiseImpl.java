package io.vertx.core.impl.future;

import io.netty.util.concurrent.Future;
import io.vertx.core.AsyncResult;
import io.vertx.core.impl.ContextInternal;

public final class PromiseImpl extends FutureImpl implements PromiseInternal, Listener {
   public PromiseImpl() {
   }

   public PromiseImpl(ContextInternal context) {
      super(context);
   }

   public void handle(AsyncResult ar) {
      if (ar.succeeded()) {
         this.onSuccess(ar.result());
      } else {
         this.onFailure(ar.cause());
      }

   }

   public void onSuccess(Object value) {
      this.tryComplete(value);
   }

   public void onFailure(Throwable failure) {
      this.tryFail(failure);
   }

   public io.vertx.core.Future future() {
      return this;
   }

   public void operationComplete(Future future) {
      if (future.isSuccess()) {
         this.complete(future.getNow());
      } else {
         this.fail(future.cause());
      }

   }
}
