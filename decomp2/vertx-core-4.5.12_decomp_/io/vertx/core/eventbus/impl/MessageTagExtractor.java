package io.vertx.core.eventbus.impl;

import io.vertx.core.eventbus.Message;
import io.vertx.core.spi.tracing.TagExtractor;

class MessageTagExtractor implements TagExtractor {
   static final MessageTagExtractor INSTANCE = new MessageTagExtractor();

   private MessageTagExtractor() {
   }

   public int len(Message obj) {
      return 3;
   }

   public String name(Message obj, int index) {
      switch (index) {
         case 0:
            return "message_bus.destination";
         case 1:
            return "message_bus.system";
         case 2:
            return "message_bus.operation";
         default:
            throw new IndexOutOfBoundsException("Invalid tag index " + index);
      }
   }

   public String value(Message obj, int index) {
      switch (index) {
         case 0:
            return obj.address();
         case 1:
            return "vertx-eventbus";
         case 2:
            return "publish";
         default:
            throw new IndexOutOfBoundsException("Invalid tag index " + index);
      }
   }
}
