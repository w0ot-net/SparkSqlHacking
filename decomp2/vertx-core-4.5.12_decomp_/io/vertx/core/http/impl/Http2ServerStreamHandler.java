package io.vertx.core.http.impl;

import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpFrame;
import io.vertx.core.http.StreamPriority;

interface Http2ServerStreamHandler {
   Http2ServerResponse response();

   void dispatch(Handler var1);

   void handleReset(long var1);

   void handleException(Throwable var1);

   void handleClose();

   default void handleData(Buffer data) {
   }

   default void handleEnd(MultiMap trailers) {
   }

   default void handleCustomFrame(HttpFrame frame) {
   }

   default void handlePriorityChange(StreamPriority streamPriority) {
   }

   default void onException(Throwable t) {
   }

   default void onClose() {
   }
}
