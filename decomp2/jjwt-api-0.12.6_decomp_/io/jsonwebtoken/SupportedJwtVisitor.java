package io.jsonwebtoken;

import io.jsonwebtoken.lang.Assert;

public class SupportedJwtVisitor implements JwtVisitor {
   public Object visit(Jwt jwt) {
      Assert.notNull(jwt, "JWT cannot be null.");
      Object payload = jwt.getPayload();
      if (payload instanceof byte[]) {
         return this.onUnsecuredContent(jwt);
      } else {
         Assert.stateIsInstance(Claims.class, payload, "Unexpected payload data type: ");
         return this.onUnsecuredClaims(jwt);
      }
   }

   public Object onUnsecuredContent(Jwt jwt) throws UnsupportedJwtException {
      throw new UnsupportedJwtException("Unexpected unsecured content JWT.");
   }

   public Object onUnsecuredClaims(Jwt jwt) {
      throw new UnsupportedJwtException("Unexpected unsecured Claims JWT.");
   }

   public Object visit(Jws jws) {
      Assert.notNull(jws, "JWS cannot be null.");
      Object payload = jws.getPayload();
      if (payload instanceof byte[]) {
         return this.onVerifiedContent(jws);
      } else {
         Assert.stateIsInstance(Claims.class, payload, "Unexpected payload data type: ");
         return this.onVerifiedClaims(jws);
      }
   }

   public Object onVerifiedContent(Jws jws) {
      throw new UnsupportedJwtException("Unexpected content JWS.");
   }

   public Object onVerifiedClaims(Jws jws) {
      throw new UnsupportedJwtException("Unexpected Claims JWS.");
   }

   public Object visit(Jwe jwe) {
      Assert.notNull(jwe, "JWE cannot be null.");
      Object payload = jwe.getPayload();
      if (payload instanceof byte[]) {
         return this.onDecryptedContent(jwe);
      } else {
         Assert.stateIsInstance(Claims.class, payload, "Unexpected payload data type: ");
         return this.onDecryptedClaims(jwe);
      }
   }

   public Object onDecryptedContent(Jwe jwe) {
      throw new UnsupportedJwtException("Unexpected content JWE.");
   }

   public Object onDecryptedClaims(Jwe jwe) {
      throw new UnsupportedJwtException("Unexpected Claims JWE.");
   }
}
