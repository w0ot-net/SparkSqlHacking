package io.vertx.core.eventbus;

import io.vertx.codegen.annotations.VertxGen;

@VertxGen
public interface DeliveryContext {
   Message message();

   void next();

   boolean send();

   Object body();
}
