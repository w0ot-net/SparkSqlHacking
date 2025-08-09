package io.vertx.core.impl.future;

import io.vertx.core.impl.ContextInternal;

abstract class Operation extends FutureImpl {
   protected Operation(ContextInternal context) {
      super(context);
   }
}
