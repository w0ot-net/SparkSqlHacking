package io.jsonwebtoken;

public class RequiredTypeException extends JwtException {
   public RequiredTypeException(String message) {
      super(message);
   }

   public RequiredTypeException(String message, Throwable cause) {
      super(message, cause);
   }
}
