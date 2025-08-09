package io.jsonwebtoken.impl;

import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.JwtVisitor;

public class DefaultJws extends DefaultProtectedJwt implements Jws {
   private static final String DIGEST_NAME = "signature";
   private final String signature;

   public DefaultJws(JwsHeader header, Object payload, byte[] signature, String b64UrlSig) {
      super(header, payload, signature, "signature");
      this.signature = b64UrlSig;
   }

   public String getSignature() {
      return this.signature;
   }

   public Object accept(JwtVisitor v) {
      return v.visit(this);
   }
}
