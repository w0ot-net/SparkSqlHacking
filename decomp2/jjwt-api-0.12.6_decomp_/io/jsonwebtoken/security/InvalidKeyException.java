package io.jsonwebtoken.security;

public class InvalidKeyException extends KeyException {
   public InvalidKeyException(String message) {
      super(message);
   }

   public InvalidKeyException(String message, Throwable cause) {
      super(message, cause);
   }
}
