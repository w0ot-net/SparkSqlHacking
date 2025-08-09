package io.vertx.core;

public class VertxException extends RuntimeException {
   public VertxException(String message) {
      super(message);
   }

   public VertxException(String message, Throwable cause) {
      super(message, cause);
   }

   public VertxException(Throwable cause) {
      super(cause);
   }

   public VertxException(String message, boolean noStackTrace) {
      super(message, (Throwable)null, !noStackTrace, !noStackTrace);
   }

   public VertxException(String message, Throwable cause, boolean noStackTrace) {
      super(message, cause, !noStackTrace, !noStackTrace);
   }

   public VertxException(Throwable cause, boolean noStackTrace) {
      super((String)null, cause, !noStackTrace, !noStackTrace);
   }
}
