package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.impl.lang.Parameter;
import io.jsonwebtoken.security.Jwk;
import io.jsonwebtoken.security.PublicJwk;
import java.util.List;

abstract class AbstractPublicJwk extends AbstractAsymmetricJwk implements PublicJwk {
   AbstractPublicJwk(JwkContext ctx, List thumbprintParams) {
      super(ctx, thumbprintParams);
   }

   protected final boolean equals(Jwk jwk) {
      return jwk instanceof PublicJwk && this.equals((PublicJwk)jwk);
   }

   protected abstract boolean equals(PublicJwk var1);
}
