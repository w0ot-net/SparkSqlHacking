package io.vertx.core.http;

import io.vertx.core.VertxException;

public class HttpClosedException extends VertxException {
   private final GoAway goAway;

   private static String formatErrorMessage(GoAway goAway) {
      return goAway == null ? "Connection was closed" : "Stream was closed (GOAWAY error code = " + goAway.getErrorCode() + ")";
   }

   public HttpClosedException(String message) {
      super(message, true);
      this.goAway = null;
   }

   public HttpClosedException(GoAway goAway) {
      super(formatErrorMessage(goAway), true);
      this.goAway = goAway;
   }

   public GoAway goAway() {
      return this.goAway != null ? new GoAway(this.goAway) : null;
   }
}
