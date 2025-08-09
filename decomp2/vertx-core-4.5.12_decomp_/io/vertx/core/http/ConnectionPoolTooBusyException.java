package io.vertx.core.http;

import io.vertx.core.VertxException;

public class ConnectionPoolTooBusyException extends VertxException {
   public ConnectionPoolTooBusyException(String message) {
      super(message);
   }
}
