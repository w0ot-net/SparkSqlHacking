package io.vertx.core.impl.future;

import io.vertx.core.Future;
import io.vertx.core.impl.ContextInternal;
import java.util.function.Function;

class Eventually extends Operation implements Listener {
   private final Function mapper;

   Eventually(ContextInternal context, Function mapper) {
      super(context);
      this.mapper = mapper;
   }

   public void onSuccess(final Object value) {
      FutureInternal<U> future;
      try {
         future = (FutureInternal)this.mapper.apply((Object)null);
      } catch (Throwable e) {
         this.tryFail(e);
         return;
      }

      future.addListener(new Listener() {
         public void onSuccess(Object ignore) {
            Eventually.this.tryComplete(value);
         }

         public void onFailure(Throwable ignore) {
            Eventually.this.tryComplete(value);
         }
      });
   }

   public void onFailure(final Throwable failure) {
      FutureInternal<U> future;
      try {
         future = (FutureInternal)this.mapper.apply((Object)null);
      } catch (Throwable e) {
         this.tryFail(e);
         return;
      }

      future.addListener(new Listener() {
         public void onSuccess(Object ignore) {
            Eventually.this.tryFail(failure);
         }

         public void onFailure(Throwable ignore) {
            Eventually.this.tryFail(failure);
         }
      });
   }
}
