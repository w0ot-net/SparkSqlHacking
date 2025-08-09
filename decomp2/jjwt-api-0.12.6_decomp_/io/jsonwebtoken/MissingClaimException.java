package io.jsonwebtoken;

public class MissingClaimException extends InvalidClaimException {
   public MissingClaimException(Header header, Claims claims, String claimName, Object claimValue, String message) {
      super(header, claims, claimName, claimValue, message);
   }

   /** @deprecated */
   @Deprecated
   public MissingClaimException(Header header, Claims claims, String claimName, Object claimValue, String message, Throwable cause) {
      super(header, claims, claimName, claimValue, message, cause);
   }
}
