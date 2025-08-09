package io.vertx.core.impl.future;

import io.vertx.core.impl.ContextInternal;

class FixedMapping extends Operation implements Listener {
   private final Object value;

   FixedMapping(ContextInternal context, Object value) {
      super(context);
      this.value = value;
   }

   public void onSuccess(Object value) {
      this.tryComplete(this.value);
   }

   public void onFailure(Throwable failure) {
      this.tryFail(failure);
   }
}
