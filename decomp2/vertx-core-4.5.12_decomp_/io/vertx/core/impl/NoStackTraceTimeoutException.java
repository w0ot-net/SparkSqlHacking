package io.vertx.core.impl;

import java.util.concurrent.TimeoutException;

public class NoStackTraceTimeoutException extends TimeoutException {
   public NoStackTraceTimeoutException(String message) {
      super(message);
   }

   public synchronized Throwable fillInStackTrace() {
      return this;
   }
}
