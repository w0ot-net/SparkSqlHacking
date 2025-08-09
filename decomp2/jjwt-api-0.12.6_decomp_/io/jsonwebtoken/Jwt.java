package io.jsonwebtoken;

public interface Jwt {
   JwtVisitor UNSECURED_CONTENT = new SupportedJwtVisitor() {
      public Jwt onUnsecuredContent(Jwt jwt) {
         return jwt;
      }
   };
   JwtVisitor UNSECURED_CLAIMS = new SupportedJwtVisitor() {
      public Jwt onUnsecuredClaims(Jwt jwt) {
         return jwt;
      }
   };

   Header getHeader();

   /** @deprecated */
   @Deprecated
   Object getBody();

   Object getPayload();

   Object accept(JwtVisitor var1);
}
