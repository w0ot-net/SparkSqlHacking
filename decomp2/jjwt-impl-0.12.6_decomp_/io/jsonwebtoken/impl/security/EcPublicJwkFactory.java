package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.impl.lang.CheckedFunction;
import io.jsonwebtoken.impl.lang.ParameterReadable;
import io.jsonwebtoken.impl.lang.RequiredParameterReader;
import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.security.EcPublicJwk;
import io.jsonwebtoken.security.InvalidKeyException;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;
import java.security.spec.ECPublicKeySpec;
import java.security.spec.EllipticCurve;
import java.util.Map;

class EcPublicJwkFactory extends AbstractEcJwkFactory {
   private static final String UNSUPPORTED_CURVE_MSG = "The specified ECKey curve does not match a JWA standard curve id.";
   static final EcPublicJwkFactory INSTANCE = new EcPublicJwkFactory();

   EcPublicJwkFactory() {
      super(ECPublicKey.class, DefaultEcPublicJwk.PARAMS);
   }

   protected static String keyContainsErrorMessage(String curveId) {
      Assert.hasText(curveId, "curveId cannot be null or empty.");
      String fmt = "ECPublicKey's ECPoint does not exist on elliptic curve '%s' and may not be used to create '%s' JWKs.";
      return String.format(fmt, curveId, curveId);
   }

   protected static String jwkContainsErrorMessage(String curveId, Map jwk) {
      Assert.hasText(curveId, "curveId cannot be null or empty.");
      String fmt = "EC JWK x,y coordinates do not exist on elliptic curve '%s'. This could be due simply to an incorrectly-created JWK or possibly an attempted Invalid Curve Attack (see https://safecurves.cr.yp.to/twist.html for more information).";
      return String.format(fmt, curveId, jwk);
   }

   protected static String getJwaIdByCurve(EllipticCurve curve) {
      ECCurve c = ECCurve.findByJcaCurve(curve);
      if (c == null) {
         throw new InvalidKeyException("The specified ECKey curve does not match a JWA standard curve id.");
      } else {
         return c.getId();
      }
   }

   protected EcPublicJwk createJwkFromKey(JwkContext ctx) {
      ECPublicKey key = (ECPublicKey)ctx.getKey();
      ECParameterSpec spec = key.getParams();
      EllipticCurve curve = spec.getCurve();
      ECPoint point = key.getW();
      String curveId = getJwaIdByCurve(curve);
      if (!ECCurve.contains(curve, point)) {
         String msg = keyContainsErrorMessage(curveId);
         throw new InvalidKeyException(msg);
      } else {
         ctx.put(DefaultEcPublicJwk.CRV.getId(), curveId);
         String x = toOctetString(curve, point.getAffineX());
         ctx.put(DefaultEcPublicJwk.X.getId(), x);
         String y = toOctetString(curve, point.getAffineY());
         ctx.put(DefaultEcPublicJwk.Y.getId(), y);
         return new DefaultEcPublicJwk(ctx);
      }
   }

   protected EcPublicJwk createJwkFromValues(JwkContext ctx) {
      ParameterReadable reader = new RequiredParameterReader(ctx);
      String curveId = (String)reader.get(DefaultEcPublicJwk.CRV);
      BigInteger x = (BigInteger)reader.get(DefaultEcPublicJwk.X);
      BigInteger y = (BigInteger)reader.get(DefaultEcPublicJwk.Y);
      ECCurve curve = getCurveByJwaId(curveId);
      ECPoint point = new ECPoint(x, y);
      if (!curve.contains(point)) {
         String msg = jwkContainsErrorMessage(curveId, ctx);
         throw new InvalidKeyException(msg);
      } else {
         final ECPublicKeySpec pubSpec = new ECPublicKeySpec(point, curve.toParameterSpec());
         ECPublicKey key = (ECPublicKey)this.generateKey(ctx, new CheckedFunction() {
            public ECPublicKey apply(KeyFactory kf) throws Exception {
               return (ECPublicKey)kf.generatePublic(pubSpec);
            }
         });
         ctx.setKey(key);
         return new DefaultEcPublicJwk(ctx);
      }
   }
}
