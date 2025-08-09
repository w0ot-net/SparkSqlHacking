package io.vertx.core.net.impl.pool;

public interface Lease {
   Object get();

   void recycle();
}
