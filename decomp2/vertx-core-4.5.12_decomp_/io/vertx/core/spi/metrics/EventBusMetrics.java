package io.vertx.core.spi.metrics;

import io.vertx.core.eventbus.Message;
import io.vertx.core.eventbus.ReplyFailure;

public interface EventBusMetrics extends Metrics {
   default Object handlerRegistered(String address, String repliedAddress) {
      return null;
   }

   default void handlerUnregistered(Object handler) {
   }

   default void scheduleMessage(Object handler, boolean local) {
   }

   default void discardMessage(Object handler, boolean local, Message msg) {
   }

   default void messageDelivered(Object handler, boolean local) {
   }

   default void messageSent(String address, boolean publish, boolean local, boolean remote) {
   }

   default void messageReceived(String address, boolean publish, boolean local, int handlers) {
   }

   default void messageWritten(String address, int numberOfBytes) {
   }

   default void messageRead(String address, int numberOfBytes) {
   }

   default void replyFailure(String address, ReplyFailure failure) {
   }
}
