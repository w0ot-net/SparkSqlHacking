package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.impl.lang.Bytes;
import io.jsonwebtoken.impl.lang.Converters;
import io.jsonwebtoken.impl.lang.Parameter;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.UnsupportedKeyException;
import java.math.BigInteger;
import java.security.spec.EllipticCurve;
import java.util.Set;

abstract class AbstractEcJwkFactory extends AbstractFamilyJwkFactory {
   protected static ECCurve getCurveByJwaId(String jwaCurveId) {
      ECCurve curve = ECCurve.findById(jwaCurveId);
      if (curve == null) {
         String msg = "Unrecognized JWA EC curve id '" + jwaCurveId + "'";
         throw new UnsupportedKeyException(msg);
      } else {
         return curve;
      }
   }

   static String toOctetString(EllipticCurve curve, BigInteger coordinate) {
      byte[] bytes = (byte[])Converters.BIGINT_UBYTES.applyTo(coordinate);
      int fieldSizeInBits = curve.getField().getFieldSize();
      int mlen = Bytes.length(fieldSizeInBits);
      bytes = Bytes.prepad(bytes, mlen);
      return (String)Encoders.BASE64URL.encode(bytes);
   }

   AbstractEcJwkFactory(Class keyType, Set params) {
      super("EC", keyType, params);
   }
}
