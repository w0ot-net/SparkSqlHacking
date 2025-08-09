package io.vertx.core.impl;

import io.vertx.core.VertxException;

public class NoStackTraceException extends VertxException {
   public NoStackTraceException(String message) {
      super(message, (Throwable)null, true);
   }

   public NoStackTraceException(Throwable cause) {
      super(cause, true);
   }
}
