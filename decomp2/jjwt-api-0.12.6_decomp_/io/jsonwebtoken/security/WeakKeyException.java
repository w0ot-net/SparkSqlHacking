package io.jsonwebtoken.security;

public class WeakKeyException extends InvalidKeyException {
   public WeakKeyException(String message) {
      super(message);
   }
}
