package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.impl.lang.Parameter;
import io.jsonwebtoken.impl.lang.ParameterReadable;
import io.jsonwebtoken.impl.lang.Parameters;
import io.jsonwebtoken.lang.Collections;
import io.jsonwebtoken.security.Jwk;
import io.jsonwebtoken.security.SecretJwk;
import java.util.List;
import java.util.Set;
import javax.crypto.SecretKey;

class DefaultSecretJwk extends AbstractJwk implements SecretJwk {
   static final String TYPE_VALUE = "oct";
   static final Parameter K = (Parameter)Parameters.bytes("k", "Key Value").setSecret(true).build();
   static final Set PARAMS;
   static final List THUMBPRINT_PARAMS;

   DefaultSecretJwk(JwkContext ctx) {
      super(ctx, THUMBPRINT_PARAMS);
   }

   protected boolean equals(Jwk jwk) {
      return jwk instanceof SecretJwk && Parameters.equals((ParameterReadable)this, jwk, K);
   }

   static {
      PARAMS = Collections.concat(AbstractJwk.PARAMS, new Parameter[]{K});
      THUMBPRINT_PARAMS = Collections.of(new Parameter[]{K, KTY});
   }
}
