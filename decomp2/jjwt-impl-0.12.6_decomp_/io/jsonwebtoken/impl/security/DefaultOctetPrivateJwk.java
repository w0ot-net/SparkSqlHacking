package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.impl.lang.Parameter;
import io.jsonwebtoken.impl.lang.ParameterReadable;
import io.jsonwebtoken.impl.lang.Parameters;
import io.jsonwebtoken.lang.Collections;
import io.jsonwebtoken.security.OctetPrivateJwk;
import io.jsonwebtoken.security.OctetPublicJwk;
import io.jsonwebtoken.security.PrivateJwk;
import java.util.Set;

public class DefaultOctetPrivateJwk extends AbstractPrivateJwk implements OctetPrivateJwk {
   static final Parameter D = (Parameter)Parameters.bytes("d", "The private key").setSecret(true).build();
   static final Set PARAMS;

   DefaultOctetPrivateJwk(JwkContext ctx, OctetPublicJwk pubJwk) {
      super(ctx, DefaultOctetPublicJwk.THUMBPRINT_PARAMS, pubJwk);
   }

   protected boolean equals(PrivateJwk jwk) {
      return jwk instanceof OctetPrivateJwk && DefaultOctetPublicJwk.equalsPublic(this, jwk) && Parameters.equals((ParameterReadable)this, jwk, D);
   }

   static {
      PARAMS = Collections.concat(DefaultOctetPublicJwk.PARAMS, new Parameter[]{D});
   }
}
