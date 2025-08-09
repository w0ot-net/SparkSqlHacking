package io.vertx.core.impl.future;

import io.vertx.core.impl.ContextInternal;
import java.util.function.Function;

class Mapping extends Operation implements Listener {
   private final Function successMapper;

   Mapping(ContextInternal context, Function successMapper) {
      super(context);
      this.successMapper = successMapper;
   }

   public void onSuccess(Object value) {
      U result;
      try {
         result = (U)this.successMapper.apply(value);
      } catch (Throwable e) {
         this.tryFail(e);
         return;
      }

      this.tryComplete(result);
   }

   public void onFailure(Throwable failure) {
      this.tryFail(failure);
   }
}
