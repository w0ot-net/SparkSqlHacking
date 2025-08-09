package io.jsonwebtoken;

public interface Jwe extends ProtectedJwt {
   JwtVisitor CONTENT = new SupportedJwtVisitor() {
      public Jwe onDecryptedContent(Jwe jwe) {
         return jwe;
      }
   };
   JwtVisitor CLAIMS = new SupportedJwtVisitor() {
      public Jwe onDecryptedClaims(Jwe jwe) {
         return jwe;
      }
   };

   byte[] getInitializationVector();
}
