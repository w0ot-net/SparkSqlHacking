package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.impl.lang.Parameter;
import io.jsonwebtoken.impl.lang.ParameterReadable;
import io.jsonwebtoken.impl.lang.Parameters;
import io.jsonwebtoken.lang.Collections;
import io.jsonwebtoken.security.OctetPublicJwk;
import io.jsonwebtoken.security.PublicJwk;
import java.util.List;
import java.util.Set;

public class DefaultOctetPublicJwk extends AbstractPublicJwk implements OctetPublicJwk {
   static final String TYPE_VALUE = "OKP";
   static final Parameter CRV;
   static final Parameter X;
   static final Set PARAMS;
   static final List THUMBPRINT_PARAMS;

   DefaultOctetPublicJwk(JwkContext ctx) {
      super(ctx, THUMBPRINT_PARAMS);
   }

   static boolean equalsPublic(ParameterReadable self, Object candidate) {
      return Parameters.equals(self, candidate, CRV) && Parameters.equals(self, candidate, X);
   }

   protected boolean equals(PublicJwk jwk) {
      return jwk instanceof OctetPublicJwk && equalsPublic(this, jwk);
   }

   static {
      CRV = DefaultEcPublicJwk.CRV;
      X = (Parameter)Parameters.bytes("x", "The public key").build();
      PARAMS = Collections.concat(AbstractAsymmetricJwk.PARAMS, new Parameter[]{CRV, X});
      THUMBPRINT_PARAMS = Collections.of(new Parameter[]{CRV, KTY, X});
   }
}
