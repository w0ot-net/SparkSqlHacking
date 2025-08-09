package io.vertx.core.spi.cluster;

public interface NodeListener {
   void nodeAdded(String var1);

   void nodeLeft(String var1);
}
