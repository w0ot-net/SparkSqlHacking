package io.vertx.core.net.impl.pool;

import io.vertx.core.Handler;
import io.vertx.core.impl.ContextInternal;
import io.vertx.core.impl.VertxInternal;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public interface ConnectionPool {
   Function EVENT_LOOP_CONTEXT_PROVIDER = (ctx) -> {
      ctx = ctx.unwrap();
      if (ctx.isEventLoopContext()) {
         return ctx;
      } else {
         VertxInternal vertx = ctx.owner();
         return vertx.createEventLoopContext(ctx.nettyEventLoop(), ctx.workerPool(), ctx.classLoader());
      }
   };

   static ConnectionPool pool(PoolConnector connector, int[] maxSizes) {
      return new SimpleConnectionPool(connector, maxSizes);
   }

   static ConnectionPool pool(PoolConnector connector, int[] maxSizes, int maxWaiters) {
      return new SimpleConnectionPool(connector, maxSizes, maxWaiters);
   }

   ConnectionPool connectionSelector(BiFunction var1);

   ConnectionPool contextProvider(Function var1);

   void acquire(ContextInternal var1, int var2, Handler var3);

   void acquire(ContextInternal var1, PoolWaiter.Listener var2, int var3, Handler var4);

   void cancel(PoolWaiter var1, Handler var2);

   void evict(Predicate var1, Handler var2);

   void close(Handler var1);

   int size();

   int waiters();

   int capacity();

   int requests();
}
