package io.vertx.core.impl.future;

import io.vertx.core.impl.ContextInternal;

class FixedOtherwise extends Operation implements Listener {
   private final Object value;

   FixedOtherwise(ContextInternal context, Object value) {
      super(context);
      this.value = value;
   }

   public void onSuccess(Object value) {
      this.tryComplete(value);
   }

   public void onFailure(Throwable failure) {
      this.tryComplete(this.value);
   }
}
