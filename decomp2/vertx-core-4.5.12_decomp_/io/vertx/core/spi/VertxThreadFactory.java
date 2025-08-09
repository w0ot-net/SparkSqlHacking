package io.vertx.core.spi;

import io.vertx.core.impl.VertxBuilder;
import io.vertx.core.impl.VertxThread;
import java.util.concurrent.TimeUnit;

public interface VertxThreadFactory extends VertxServiceProvider {
   VertxThreadFactory INSTANCE = new VertxThreadFactory() {
   };

   default void init(VertxBuilder builder) {
      if (builder.threadFactory() == null) {
         builder.threadFactory(this);
      }

   }

   default VertxThread newVertxThread(Runnable target, String name, boolean worker, long maxExecTime, TimeUnit maxExecTimeUnit) {
      return new VertxThread(target, name, worker, maxExecTime, maxExecTimeUnit);
   }
}
