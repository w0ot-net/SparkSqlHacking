package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.impl.lang.Parameter;
import io.jsonwebtoken.impl.lang.ParameterReadable;
import io.jsonwebtoken.impl.lang.Parameters;
import io.jsonwebtoken.lang.Collections;
import io.jsonwebtoken.security.EcPublicJwk;
import io.jsonwebtoken.security.PublicJwk;
import java.security.interfaces.ECPublicKey;
import java.util.List;
import java.util.Set;

class DefaultEcPublicJwk extends AbstractPublicJwk implements EcPublicJwk {
   static final String TYPE_VALUE = "EC";
   static final Parameter CRV = Parameters.string("crv", "Curve");
   static final Parameter X;
   static final Parameter Y;
   static final Set PARAMS;
   static final List THUMBPRINT_PARAMS;

   DefaultEcPublicJwk(JwkContext ctx) {
      super(ctx, THUMBPRINT_PARAMS);
   }

   static boolean equalsPublic(ParameterReadable self, Object candidate) {
      return Parameters.equals(self, candidate, CRV) && Parameters.equals(self, candidate, X) && Parameters.equals(self, candidate, Y);
   }

   protected boolean equals(PublicJwk jwk) {
      return jwk instanceof EcPublicJwk && equalsPublic(this, jwk);
   }

   static {
      X = (Parameter)Parameters.bigInt("x", "X Coordinate").setConverter(FieldElementConverter.B64URL_CONVERTER).build();
      Y = (Parameter)Parameters.bigInt("y", "Y Coordinate").setConverter(FieldElementConverter.B64URL_CONVERTER).build();
      PARAMS = Collections.concat(AbstractAsymmetricJwk.PARAMS, new Parameter[]{CRV, X, Y});
      THUMBPRINT_PARAMS = Collections.of(new Parameter[]{CRV, KTY, X, Y});
   }
}
