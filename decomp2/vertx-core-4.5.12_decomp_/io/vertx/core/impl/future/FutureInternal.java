package io.vertx.core.impl.future;

import io.vertx.core.Future;
import io.vertx.core.impl.ContextInternal;

public interface FutureInternal extends Future {
   ContextInternal context();

   void addListener(Listener var1);

   void removeListener(Listener var1);
}
