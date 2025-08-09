package io.vertx.core.impl.future;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.impl.ContextInternal;
import java.util.function.Function;

class Transformation extends Operation implements Listener {
   private final Function mapper;

   Transformation(ContextInternal context, Function mapper) {
      super(context);
      this.mapper = mapper;
   }

   public void onSuccess(Object value) {
      FutureInternal<U> future;
      try {
         future = (FutureInternal)this.mapper.apply(Future.succeededFuture(value));
      } catch (Throwable e) {
         this.tryFail(e);
         return;
      }

      future.addListener(this.newListener());
   }

   public void onFailure(Throwable failure) {
      FutureInternal<U> future;
      try {
         future = (FutureInternal)this.mapper.apply(Future.failedFuture(failure));
      } catch (Throwable e) {
         this.tryFail(e);
         return;
      }

      future.addListener(this.newListener());
   }

   private Listener newListener() {
      return new Listener() {
         public void onSuccess(Object value) {
            Transformation.this.tryComplete(value);
         }

         public void onFailure(Throwable failure) {
            Transformation.this.tryFail(failure);
         }
      };
   }
}
