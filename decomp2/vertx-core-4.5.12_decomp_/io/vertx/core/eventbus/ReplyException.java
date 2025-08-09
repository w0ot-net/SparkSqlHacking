package io.vertx.core.eventbus;

import io.vertx.core.VertxException;

public class ReplyException extends VertxException {
   private final ReplyFailure failureType;
   private final int failureCode;

   public ReplyException(ReplyFailure failureType, int failureCode, String message, boolean noStackTrace) {
      super(message, noStackTrace);
      this.failureType = failureType;
      this.failureCode = failureCode;
   }

   protected ReplyException(ReplyFailure failureType, int failureCode, String message, Throwable cause, boolean noStackTrace) {
      super(message, cause, noStackTrace);
      this.failureType = failureType;
      this.failureCode = failureCode;
   }

   public ReplyException(ReplyFailure failureType, int failureCode, String message) {
      this(failureType, failureCode, message, true);
   }

   public ReplyException(ReplyFailure failureType, String message) {
      this(failureType, -1, message);
   }

   public ReplyException(ReplyFailure failureType) {
      this(failureType, -1, (String)null);
   }

   public ReplyFailure failureType() {
      return this.failureType;
   }

   public int failureCode() {
      return this.failureCode;
   }

   public String toString() {
      String message = this.getMessage();
      return "(" + this.failureType + "," + this.failureCode + ") " + (message != null ? message : "");
   }
}
