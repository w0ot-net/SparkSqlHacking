package io.jsonwebtoken;

public class UnsupportedJwtException extends JwtException {
   public UnsupportedJwtException(String message) {
      super(message);
   }

   public UnsupportedJwtException(String message, Throwable cause) {
      super(message, cause);
   }
}
