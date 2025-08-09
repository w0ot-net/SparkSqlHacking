package io.jsonwebtoken.security;

public class MalformedKeySetException extends SecurityException {
   public MalformedKeySetException(String message) {
      super(message);
   }

   public MalformedKeySetException(String message, Throwable cause) {
      super(message, cause);
   }
}
