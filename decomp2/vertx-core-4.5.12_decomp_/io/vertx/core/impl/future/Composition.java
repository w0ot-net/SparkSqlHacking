package io.vertx.core.impl.future;

import io.vertx.core.Future;
import io.vertx.core.impl.ContextInternal;
import java.util.function.Function;

class Composition extends Operation implements Listener {
   private final Function successMapper;
   private final Function failureMapper;

   Composition(ContextInternal context, Function successMapper, Function failureMapper) {
      super(context);
      this.successMapper = successMapper;
      this.failureMapper = failureMapper;
   }

   public void onSuccess(Object value) {
      FutureInternal<U> future;
      try {
         future = (FutureInternal)this.successMapper.apply(value);
      } catch (Throwable e) {
         this.tryFail(e);
         return;
      }

      future.addListener(this.newListener());
   }

   public void onFailure(Throwable failure) {
      FutureInternal<U> future;
      try {
         future = (FutureInternal)this.failureMapper.apply(failure);
      } catch (Throwable e) {
         this.tryFail(e);
         return;
      }

      future.addListener(this.newListener());
   }

   private Listener newListener() {
      return new Listener() {
         public void onSuccess(Object value) {
            Composition.this.tryComplete(value);
         }

         public void onFailure(Throwable failure) {
            Composition.this.tryFail(failure);
         }
      };
   }
}
