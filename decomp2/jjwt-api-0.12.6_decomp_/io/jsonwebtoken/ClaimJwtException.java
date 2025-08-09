package io.jsonwebtoken;

public abstract class ClaimJwtException extends JwtException {
   /** @deprecated */
   @Deprecated
   public static final String INCORRECT_EXPECTED_CLAIM_MESSAGE_TEMPLATE = "Expected %s claim to be: %s, but was: %s.";
   /** @deprecated */
   @Deprecated
   public static final String MISSING_EXPECTED_CLAIM_MESSAGE_TEMPLATE = "Expected %s claim to be: %s, but was not present in the JWT claims.";
   private final Header header;
   private final Claims claims;

   protected ClaimJwtException(Header header, Claims claims, String message) {
      super(message);
      this.header = header;
      this.claims = claims;
   }

   protected ClaimJwtException(Header header, Claims claims, String message, Throwable cause) {
      super(message, cause);
      this.header = header;
      this.claims = claims;
   }

   public Claims getClaims() {
      return this.claims;
   }

   public Header getHeader() {
      return this.header;
   }
}
