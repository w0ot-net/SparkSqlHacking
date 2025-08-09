package io.jsonwebtoken;

public class IncorrectClaimException extends InvalidClaimException {
   public IncorrectClaimException(Header header, Claims claims, String claimName, Object claimValue, String message) {
      super(header, claims, claimName, claimValue, message);
   }

   public IncorrectClaimException(Header header, Claims claims, String claimName, Object claimValue, String message, Throwable cause) {
      super(header, claims, claimName, claimValue, message, cause);
   }
}
