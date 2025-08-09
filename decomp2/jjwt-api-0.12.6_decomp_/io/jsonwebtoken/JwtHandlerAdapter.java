package io.jsonwebtoken;

public abstract class JwtHandlerAdapter extends SupportedJwtVisitor implements JwtHandler {
   public Object onUnsecuredContent(Jwt jwt) {
      return this.onContentJwt(jwt);
   }

   public Object onUnsecuredClaims(Jwt jwt) {
      return this.onClaimsJwt(jwt);
   }

   public Object onVerifiedContent(Jws jws) {
      return this.onContentJws(jws);
   }

   public Object onVerifiedClaims(Jws jws) {
      return this.onClaimsJws(jws);
   }

   public Object onDecryptedContent(Jwe jwe) {
      return this.onContentJwe(jwe);
   }

   public Object onDecryptedClaims(Jwe jwe) {
      return this.onClaimsJwe(jwe);
   }

   public Object onContentJwt(Jwt jwt) {
      return super.onUnsecuredContent(jwt);
   }

   public Object onClaimsJwt(Jwt jwt) {
      return super.onUnsecuredClaims(jwt);
   }

   public Object onContentJws(Jws jws) {
      return super.onVerifiedContent(jws);
   }

   public Object onClaimsJws(Jws jws) {
      return super.onVerifiedClaims(jws);
   }

   public Object onContentJwe(Jwe jwe) {
      return super.onDecryptedContent(jwe);
   }

   public Object onClaimsJwe(Jwe jwe) {
      return super.onDecryptedClaims(jwe);
   }
}
