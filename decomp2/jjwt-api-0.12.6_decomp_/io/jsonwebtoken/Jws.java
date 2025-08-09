package io.jsonwebtoken;

public interface Jws extends ProtectedJwt {
   JwtVisitor CONTENT = new SupportedJwtVisitor() {
      public Jws onVerifiedContent(Jws jws) {
         return jws;
      }
   };
   JwtVisitor CLAIMS = new SupportedJwtVisitor() {
      public Jws onVerifiedClaims(Jws jws) {
         return jws;
      }
   };

   /** @deprecated */
   @Deprecated
   String getSignature();
}
