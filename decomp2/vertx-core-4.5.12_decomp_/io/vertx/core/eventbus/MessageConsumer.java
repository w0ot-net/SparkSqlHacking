package io.vertx.core.eventbus;

import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.streams.ReadStream;

@VertxGen
public interface MessageConsumer extends ReadStream {
   MessageConsumer exceptionHandler(Handler var1);

   MessageConsumer handler(Handler var1);

   MessageConsumer pause();

   MessageConsumer resume();

   MessageConsumer fetch(long var1);

   MessageConsumer endHandler(Handler var1);

   ReadStream bodyStream();

   boolean isRegistered();

   String address();

   MessageConsumer setMaxBufferedMessages(int var1);

   int getMaxBufferedMessages();

   void completionHandler(Handler var1);

   Future unregister();

   void unregister(Handler var1);
}
