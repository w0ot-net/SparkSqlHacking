package io.jsonwebtoken.security;

public class KeyException extends SecurityException {
   public KeyException(String message) {
      super(message);
   }

   public KeyException(String msg, Throwable cause) {
      super(msg, cause);
   }
}
