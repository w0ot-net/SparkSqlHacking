package io.vertx.core.eventbus;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.Nullable;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.impl.DefaultSerializableChecker;
import io.vertx.core.metrics.Measured;
import java.util.function.Function;

@VertxGen
public interface EventBus extends Measured {
   @GenIgnore
   Function DEFAULT_SERIALIZABLE_CHECKER;

   @Fluent
   EventBus send(String var1, @Nullable Object var2);

   @Fluent
   EventBus send(String var1, @Nullable Object var2, DeliveryOptions var3);

   @Fluent
   default EventBus request(String address, @Nullable Object message, Handler replyHandler) {
      return this.request(address, message, new DeliveryOptions(), replyHandler);
   }

   default Future request(String address, @Nullable Object message) {
      return this.request(address, message, new DeliveryOptions());
   }

   @Fluent
   default EventBus request(String address, @Nullable Object message, DeliveryOptions options, Handler replyHandler) {
      Future<Message<T>> reply = this.request(address, message, options);
      reply.onComplete(replyHandler);
      return this;
   }

   Future request(String var1, @Nullable Object var2, DeliveryOptions var3);

   @Fluent
   EventBus publish(String var1, @Nullable Object var2);

   @Fluent
   EventBus publish(String var1, @Nullable Object var2, DeliveryOptions var3);

   MessageConsumer consumer(String var1);

   MessageConsumer consumer(String var1, Handler var2);

   MessageConsumer localConsumer(String var1);

   MessageConsumer localConsumer(String var1, Handler var2);

   MessageProducer sender(String var1);

   MessageProducer sender(String var1, DeliveryOptions var2);

   MessageProducer publisher(String var1);

   MessageProducer publisher(String var1, DeliveryOptions var2);

   @Fluent
   @GenIgnore({"permitted-type"})
   EventBus registerCodec(MessageCodec var1);

   @Fluent
   @GenIgnore({"permitted-type"})
   EventBus unregisterCodec(String var1);

   @Fluent
   @GenIgnore
   EventBus registerDefaultCodec(Class var1, MessageCodec var2);

   @Fluent
   @GenIgnore
   EventBus unregisterDefaultCodec(Class var1);

   @Fluent
   EventBus codecSelector(Function var1);

   @Fluent
   EventBus addOutboundInterceptor(Handler var1);

   @Fluent
   EventBus removeOutboundInterceptor(Handler var1);

   @Fluent
   EventBus addInboundInterceptor(Handler var1);

   @Fluent
   EventBus removeInboundInterceptor(Handler var1);

   @Fluent
   EventBus clusterSerializableChecker(Function var1);

   @Fluent
   EventBus serializableChecker(Function var1);

   static {
      DefaultSerializableChecker var10000 = DefaultSerializableChecker.INSTANCE;
      DEFAULT_SERIALIZABLE_CHECKER = var10000::check;
   }
}
