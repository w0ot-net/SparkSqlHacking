package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.impl.lang.Parameter;
import io.jsonwebtoken.impl.lang.ParameterReadable;
import io.jsonwebtoken.security.UnsupportedKeyException;
import java.security.Key;
import java.util.Set;

public abstract class OctetJwkFactory extends AbstractFamilyJwkFactory {
   OctetJwkFactory(Class keyType, Set params) {
      super("OKP", keyType, params);
   }

   public boolean supports(Key key) {
      return super.supports(key) && EdwardsCurve.isEdwards(key);
   }

   protected static EdwardsCurve getCurve(ParameterReadable reader) throws UnsupportedKeyException {
      Parameter<String> param = DefaultOctetPublicJwk.CRV;
      String crvId = (String)reader.get(param);
      EdwardsCurve curve = EdwardsCurve.findById(crvId);
      if (curve == null) {
         String msg = "Unrecognized OKP JWK " + param + " value '" + crvId + "'";
         throw new UnsupportedKeyException(msg);
      } else {
         return curve;
      }
   }
}
