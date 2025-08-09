package io.vertx.core.net.impl.pool;

import io.vertx.core.impl.ContextInternal;

public interface PoolConnection {
   ContextInternal context();

   Object get();

   int usage();

   long available();

   long concurrency();
}
