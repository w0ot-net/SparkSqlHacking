package org.sparkproject.jetty.http;

public class BadMessageException extends RuntimeException {
   final int _code;
   final String _reason;

   public BadMessageException() {
      this(400, (String)null);
   }

   public BadMessageException(int code) {
      this(code, (String)null);
   }

   public BadMessageException(String reason) {
      this(400, reason);
   }

   public BadMessageException(String reason, Throwable cause) {
      this(400, reason, cause);
   }

   public BadMessageException(int code, String reason) {
      this(code, reason, (Throwable)null);
   }

   public BadMessageException(int code, String reason, Throwable cause) {
      super(code + ": " + reason, cause);
      this._code = code;
      this._reason = reason;
   }

   public int getCode() {
      return this._code;
   }

   public String getReason() {
      return this._reason;
   }
}
