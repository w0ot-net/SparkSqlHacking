package io.vertx.core.impl.future;

import io.vertx.core.impl.ContextInternal;
import java.util.function.Function;

class Otherwise extends Operation implements Listener {
   private final Function mapper;

   Otherwise(ContextInternal context, Function mapper) {
      super(context);
      this.mapper = mapper;
   }

   public void onSuccess(Object value) {
      this.tryComplete(value);
   }

   public void onFailure(Throwable failure) {
      T result;
      try {
         result = (T)this.mapper.apply(failure);
      } catch (Throwable e) {
         this.tryFail(e);
         return;
      }

      this.tryComplete(result);
   }
}
