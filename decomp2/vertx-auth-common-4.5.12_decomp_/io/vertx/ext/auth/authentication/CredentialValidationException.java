package io.vertx.ext.auth.authentication;

public class CredentialValidationException extends RuntimeException {
   public CredentialValidationException(String message) {
      super(message);
   }

   public CredentialValidationException(String message, Throwable cause) {
      super(message, cause);
   }
}
