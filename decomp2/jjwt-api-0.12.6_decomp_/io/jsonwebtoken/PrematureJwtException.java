package io.jsonwebtoken;

public class PrematureJwtException extends ClaimJwtException {
   public PrematureJwtException(Header header, Claims claims, String message) {
      super(header, claims, message);
   }

   /** @deprecated */
   @Deprecated
   public PrematureJwtException(Header header, Claims claims, String message, Throwable cause) {
      super(header, claims, message, cause);
   }
}
