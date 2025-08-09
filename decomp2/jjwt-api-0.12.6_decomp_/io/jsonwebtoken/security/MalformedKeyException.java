package io.jsonwebtoken.security;

public class MalformedKeyException extends InvalidKeyException {
   public MalformedKeyException(String message) {
      super(message);
   }

   public MalformedKeyException(String msg, Throwable cause) {
      super(msg, cause);
   }
}
