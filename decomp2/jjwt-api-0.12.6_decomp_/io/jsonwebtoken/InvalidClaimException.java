package io.jsonwebtoken;

public class InvalidClaimException extends ClaimJwtException {
   private final String claimName;
   private final Object claimValue;

   protected InvalidClaimException(Header header, Claims claims, String claimName, Object claimValue, String message) {
      super(header, claims, message);
      this.claimName = claimName;
      this.claimValue = claimValue;
   }

   protected InvalidClaimException(Header header, Claims claims, String claimName, Object claimValue, String message, Throwable cause) {
      super(header, claims, message, cause);
      this.claimName = claimName;
      this.claimValue = claimValue;
   }

   public String getClaimName() {
      return this.claimName;
   }

   public Object getClaimValue() {
      return this.claimValue;
   }
}
