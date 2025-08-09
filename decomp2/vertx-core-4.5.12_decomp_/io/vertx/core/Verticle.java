package io.vertx.core;

public interface Verticle {
   Vertx getVertx();

   void init(Vertx var1, Context var2);

   void start(Promise var1) throws Exception;

   void stop(Promise var1) throws Exception;
}
