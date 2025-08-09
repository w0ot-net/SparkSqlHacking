package io.jsonwebtoken.security;

public class SignatureException extends io.jsonwebtoken.SignatureException {
   public SignatureException(String message) {
      super(message);
   }

   public SignatureException(String message, Throwable cause) {
      super(message, cause);
   }
}
