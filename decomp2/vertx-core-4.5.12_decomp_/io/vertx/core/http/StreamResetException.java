package io.vertx.core.http;

import io.vertx.core.VertxException;

public class StreamResetException extends VertxException {
   private final long code;

   public StreamResetException(long code) {
      super("Stream reset: " + code, true);
      this.code = code;
   }

   public StreamResetException(long code, Throwable cause) {
      super("Stream reset: " + code, cause, true);
      this.code = code;
   }

   public long getCode() {
      return this.code;
   }
}
