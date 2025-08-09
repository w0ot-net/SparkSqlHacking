package io.vertx.core.eventbus;

import io.vertx.codegen.annotations.VertxGen;

@VertxGen
public enum ReplyFailure {
   TIMEOUT,
   NO_HANDLERS,
   RECIPIENT_FAILURE,
   ERROR;

   public static ReplyFailure fromInt(int i) {
      switch (i) {
         case 0:
            return TIMEOUT;
         case 1:
            return NO_HANDLERS;
         case 2:
            return RECIPIENT_FAILURE;
         case 3:
            return ERROR;
         default:
            throw new IllegalStateException("Invalid index " + i);
      }
   }

   public int toInt() {
      switch (this) {
         case TIMEOUT:
            return 0;
         case NO_HANDLERS:
            return 1;
         case RECIPIENT_FAILURE:
            return 2;
         case ERROR:
            return 3;
         default:
            throw new IllegalStateException("How did we get here?");
      }
   }
}
