package io.vertx.core.eventbus;

import io.vertx.codegen.annotations.CacheReturn;
import io.vertx.codegen.annotations.Nullable;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;

@VertxGen
public interface Message {
   String address();

   MultiMap headers();

   @CacheReturn
   Object body();

   @Nullable String replyAddress();

   boolean isSend();

   default void reply(@Nullable Object message) {
      this.reply(message, new DeliveryOptions());
   }

   void reply(@Nullable Object var1, DeliveryOptions var2);

   default void replyAndRequest(@Nullable Object message, Handler replyHandler) {
      this.replyAndRequest(message, new DeliveryOptions(), replyHandler);
   }

   default Future replyAndRequest(@Nullable Object message) {
      return this.replyAndRequest(message, new DeliveryOptions());
   }

   default void replyAndRequest(@Nullable Object message, DeliveryOptions options, Handler replyHandler) {
      this.replyAndRequest(message, options).onComplete(replyHandler);
   }

   Future replyAndRequest(@Nullable Object var1, DeliveryOptions var2);

   default void fail(int failureCode, String message) {
      this.reply(new ReplyException(ReplyFailure.RECIPIENT_FAILURE, failureCode, message));
   }
}
