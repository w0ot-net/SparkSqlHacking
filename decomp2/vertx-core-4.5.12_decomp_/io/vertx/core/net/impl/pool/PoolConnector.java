package io.vertx.core.net.impl.pool;

import io.vertx.core.Handler;
import io.vertx.core.impl.ContextInternal;

public interface PoolConnector {
   void connect(ContextInternal var1, Listener var2, Handler var3);

   boolean isValid(Object var1);

   public interface Listener {
      void onRemove();

      void onConcurrencyChange(long var1);
   }
}
