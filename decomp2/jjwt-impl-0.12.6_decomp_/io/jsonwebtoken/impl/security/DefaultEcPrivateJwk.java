package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.impl.lang.Parameter;
import io.jsonwebtoken.impl.lang.ParameterReadable;
import io.jsonwebtoken.impl.lang.Parameters;
import io.jsonwebtoken.lang.Collections;
import io.jsonwebtoken.security.EcPrivateJwk;
import io.jsonwebtoken.security.EcPublicJwk;
import io.jsonwebtoken.security.PrivateJwk;
import java.security.interfaces.ECPrivateKey;
import java.util.Set;

class DefaultEcPrivateJwk extends AbstractPrivateJwk implements EcPrivateJwk {
   static final Parameter D;
   static final Set PARAMS;

   DefaultEcPrivateJwk(JwkContext ctx, EcPublicJwk pubJwk) {
      super(ctx, DefaultEcPublicJwk.THUMBPRINT_PARAMS, pubJwk);
   }

   protected boolean equals(PrivateJwk jwk) {
      return jwk instanceof EcPrivateJwk && DefaultEcPublicJwk.equalsPublic(this, jwk) && Parameters.equals((ParameterReadable)this, jwk, D);
   }

   static {
      D = (Parameter)Parameters.bigInt("d", "ECC Private Key").setConverter(FieldElementConverter.B64URL_CONVERTER).setSecret(true).build();
      PARAMS = Collections.concat(DefaultEcPublicJwk.PARAMS, new Parameter[]{D});
   }
}
