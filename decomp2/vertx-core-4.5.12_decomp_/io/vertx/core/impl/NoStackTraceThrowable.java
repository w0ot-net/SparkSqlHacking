package io.vertx.core.impl;

public class NoStackTraceThrowable extends Throwable {
   public NoStackTraceThrowable(String message) {
      super(message, (Throwable)null, false, false);
   }
}
