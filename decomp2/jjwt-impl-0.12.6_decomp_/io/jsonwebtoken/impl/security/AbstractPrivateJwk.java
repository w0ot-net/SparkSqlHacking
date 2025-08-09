package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.impl.lang.Parameter;
import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.security.Jwk;
import io.jsonwebtoken.security.KeyPair;
import io.jsonwebtoken.security.PrivateJwk;
import io.jsonwebtoken.security.PublicJwk;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.List;

abstract class AbstractPrivateJwk extends AbstractAsymmetricJwk implements PrivateJwk {
   private final PublicJwk publicJwk;
   private final KeyPair keyPair;

   AbstractPrivateJwk(JwkContext ctx, List thumbprintParams, PublicJwk pubJwk) {
      super(ctx, thumbprintParams);
      this.publicJwk = (PublicJwk)Assert.notNull(pubJwk, "PublicJwk instance cannot be null.");
      L publicKey = (L)((PublicKey)Assert.notNull(pubJwk.toKey(), "PublicJwk key instance cannot be null."));
      this.context.setPublicKey(publicKey);
      this.keyPair = new DefaultKeyPair(publicKey, (PrivateKey)this.toKey());
   }

   public PublicJwk toPublicJwk() {
      return this.publicJwk;
   }

   public KeyPair toKeyPair() {
      return this.keyPair;
   }

   protected final boolean equals(Jwk jwk) {
      return jwk instanceof PrivateJwk && this.equals((PrivateJwk)jwk);
   }

   protected abstract boolean equals(PrivateJwk var1);
}
