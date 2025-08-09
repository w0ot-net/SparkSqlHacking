package io.jsonwebtoken;

import io.jsonwebtoken.security.SecurityException;

/** @deprecated */
@Deprecated
public class SignatureException extends SecurityException {
   public SignatureException(String message) {
      super(message);
   }

   public SignatureException(String message, Throwable cause) {
      super(message, cause);
   }
}
