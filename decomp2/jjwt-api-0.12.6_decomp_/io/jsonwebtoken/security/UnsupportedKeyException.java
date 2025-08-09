package io.jsonwebtoken.security;

public class UnsupportedKeyException extends KeyException {
   public UnsupportedKeyException(String message) {
      super(message);
   }

   public UnsupportedKeyException(String msg, Throwable cause) {
      super(msg, cause);
   }
}
