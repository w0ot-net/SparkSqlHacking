package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.impl.lang.Parameter;
import io.jsonwebtoken.impl.lang.ParameterReadable;
import io.jsonwebtoken.impl.lang.Parameters;
import io.jsonwebtoken.lang.Collections;
import io.jsonwebtoken.security.PublicJwk;
import io.jsonwebtoken.security.RsaPublicJwk;
import java.security.interfaces.RSAPublicKey;
import java.util.List;
import java.util.Set;

class DefaultRsaPublicJwk extends AbstractPublicJwk implements RsaPublicJwk {
   static final String TYPE_VALUE = "RSA";
   static final Parameter MODULUS = (Parameter)Parameters.bigInt("n", "Modulus").build();
   static final Parameter PUBLIC_EXPONENT = (Parameter)Parameters.bigInt("e", "Public Exponent").build();
   static final Set PARAMS;
   static final List THUMBPRINT_PARAMS;

   DefaultRsaPublicJwk(JwkContext ctx) {
      super(ctx, THUMBPRINT_PARAMS);
   }

   static boolean equalsPublic(ParameterReadable self, Object candidate) {
      return Parameters.equals(self, candidate, MODULUS) && Parameters.equals(self, candidate, PUBLIC_EXPONENT);
   }

   protected boolean equals(PublicJwk jwk) {
      return jwk instanceof RsaPublicJwk && equalsPublic(this, jwk);
   }

   static {
      PARAMS = Collections.concat(AbstractAsymmetricJwk.PARAMS, new Parameter[]{MODULUS, PUBLIC_EXPONENT});
      THUMBPRINT_PARAMS = Collections.of(new Parameter[]{PUBLIC_EXPONENT, KTY, MODULUS});
   }
}
