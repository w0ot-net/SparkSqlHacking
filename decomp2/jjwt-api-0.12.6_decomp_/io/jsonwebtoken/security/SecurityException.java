package io.jsonwebtoken.security;

import io.jsonwebtoken.JwtException;

public class SecurityException extends JwtException {
   public SecurityException(String message) {
      super(message);
   }

   public SecurityException(String message, Throwable cause) {
      super(message, cause);
   }
}
