package io.vertx.core.eventbus;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.Future;
import io.vertx.core.Handler;

@VertxGen
public interface MessageProducer {
   @Fluent
   MessageProducer deliveryOptions(DeliveryOptions var1);

   String address();

   void write(Object var1, Handler var2);

   Future write(Object var1);

   Future close();

   void close(Handler var1);
}
