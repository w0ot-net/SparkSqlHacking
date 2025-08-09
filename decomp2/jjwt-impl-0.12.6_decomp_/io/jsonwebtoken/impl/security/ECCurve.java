package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.impl.lang.CheckedFunction;
import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.lang.Collections;
import io.jsonwebtoken.security.KeyPairBuilder;
import java.math.BigInteger;
import java.security.AlgorithmParameters;
import java.security.Key;
import java.security.interfaces.ECKey;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECFieldFp;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;
import java.security.spec.ECPublicKeySpec;
import java.security.spec.EllipticCurve;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class ECCurve extends AbstractCurve {
   private static final BigInteger TWO = BigInteger.valueOf(2L);
   private static final BigInteger THREE = BigInteger.valueOf(3L);
   static final String KEY_PAIR_GENERATOR_JCA_NAME = "EC";
   public static final ECCurve P256 = new ECCurve("P-256", "secp256r1");
   public static final ECCurve P384 = new ECCurve("P-384", "secp384r1");
   public static final ECCurve P521 = new ECCurve("P-521", "secp521r1");
   public static final Collection VALUES;
   private static final Map BY_ID;
   private static final Map BY_JCA_CURVE;
   private final ECParameterSpec spec;

   static EllipticCurve assertJcaCurve(ECKey key) {
      Assert.notNull(key, "ECKey cannot be null.");
      ECParameterSpec spec = (ECParameterSpec)Assert.notNull(key.getParams(), "ECKey params() cannot be null.");
      return (EllipticCurve)Assert.notNull(spec.getCurve(), "ECKey params().getCurve() cannot be null.");
   }

   static ECCurve findById(String id) {
      return (ECCurve)BY_ID.get(id);
   }

   static ECCurve findByJcaCurve(EllipticCurve curve) {
      return (ECCurve)BY_JCA_CURVE.get(curve);
   }

   static ECCurve findByKey(Key key) {
      if (!(key instanceof ECKey)) {
         return null;
      } else {
         ECKey ecKey = (ECKey)key;
         ECParameterSpec spec = ecKey.getParams();
         if (spec == null) {
            return null;
         } else {
            EllipticCurve jcaCurve = spec.getCurve();
            ECCurve curve = (ECCurve)BY_JCA_CURVE.get(jcaCurve);
            if (curve != null && key instanceof ECPublicKey) {
               ECPublicKey pub = (ECPublicKey)key;
               ECPoint w = pub.getW();
               if (w == null || !curve.contains(w)) {
                  curve = null;
               }
            }

            return curve;
         }
      }
   }

   static ECPublicKeySpec publicKeySpec(ECPrivateKey key) throws IllegalArgumentException {
      EllipticCurve jcaCurve = assertJcaCurve(key);
      ECCurve curve = (ECCurve)BY_JCA_CURVE.get(jcaCurve);
      Assert.notNull(curve, "There is no JWA-standard Elliptic Curve for specified ECPrivateKey.");
      ECPoint w = curve.multiply(key.getS());
      return new ECPublicKeySpec(w, curve.spec);
   }

   public ECCurve(String id, String jcaName) {
      super(id, jcaName);
      JcaTemplate template = new JcaTemplate("EC");
      this.spec = (ECParameterSpec)template.withAlgorithmParameters(new CheckedFunction() {
         public ECParameterSpec apply(AlgorithmParameters params) throws Exception {
            params.init(new ECGenParameterSpec(ECCurve.this.getJcaName()));
            return (ECParameterSpec)params.getParameterSpec(ECParameterSpec.class);
         }
      });
   }

   public ECParameterSpec toParameterSpec() {
      return this.spec;
   }

   public KeyPairBuilder keyPair() {
      return new DefaultKeyPairBuilder("EC", this.toParameterSpec());
   }

   public boolean contains(Key key) {
      if (!(key instanceof ECPublicKey)) {
         return false;
      } else {
         ECPublicKey pub = (ECPublicKey)key;
         ECParameterSpec pubSpec = pub.getParams();
         return pubSpec != null && this.spec.getCurve().equals(pubSpec.getCurve()) && this.contains(pub.getW());
      }
   }

   boolean contains(ECPoint point) {
      return contains(this.spec.getCurve(), point);
   }

   static boolean contains(EllipticCurve curve, ECPoint point) {
      if (point != null && !ECPoint.POINT_INFINITY.equals(point)) {
         BigInteger a = curve.getA();
         BigInteger b = curve.getB();
         BigInteger x = point.getAffineX();
         BigInteger y = point.getAffineY();
         BigInteger p = ((ECFieldFp)curve.getField()).getP();
         if (x.compareTo(BigInteger.ZERO) >= 0 && x.compareTo(p) < 0 && y.compareTo(BigInteger.ZERO) >= 0 && y.compareTo(p) < 0) {
            BigInteger lhs = y.modPow(TWO, p);
            BigInteger rhs = x.modPow(THREE, p).add(a.multiply(x)).add(b).mod(p);
            return lhs.equals(rhs);
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   private ECPoint multiply(BigInteger s) {
      return this.multiply(this.spec.getGenerator(), s);
   }

   private ECPoint multiply(ECPoint p, BigInteger s) {
      if (ECPoint.POINT_INFINITY.equals(p)) {
         return p;
      } else {
         BigInteger n = this.spec.getOrder();
         BigInteger k = s.mod(n);
         ECPoint r0 = ECPoint.POINT_INFINITY;
         ECPoint r1 = p;

         for(int i = k.bitLength() - 1; i >= 0; --i) {
            if (k.testBit(i)) {
               r0 = this.add(r0, r1);
               r1 = this.doublePoint(r1);
            } else {
               r1 = this.add(r0, r1);
               r0 = this.doublePoint(r0);
            }
         }

         return r0;
      }
   }

   private ECPoint add(ECPoint P, ECPoint Q) {
      if (ECPoint.POINT_INFINITY.equals(P)) {
         return Q;
      } else if (ECPoint.POINT_INFINITY.equals(Q)) {
         return P;
      } else if (P.equals(Q)) {
         return this.doublePoint(P);
      } else {
         EllipticCurve curve = this.spec.getCurve();
         BigInteger Px = P.getAffineX();
         BigInteger Py = P.getAffineY();
         BigInteger Qx = Q.getAffineX();
         BigInteger Qy = Q.getAffineY();
         BigInteger prime = ((ECFieldFp)curve.getField()).getP();
         BigInteger slope = Qy.subtract(Py).multiply(Qx.subtract(Px).modInverse(prime)).mod(prime);
         BigInteger Rx = slope.pow(2).subtract(Px).subtract(Qx).mod(prime);
         BigInteger Ry = slope.multiply(Px.subtract(Rx)).subtract(Py).mod(prime);
         return new ECPoint(Rx, Ry);
      }
   }

   private ECPoint doublePoint(ECPoint P) {
      if (ECPoint.POINT_INFINITY.equals(P)) {
         return P;
      } else {
         EllipticCurve curve = this.spec.getCurve();
         BigInteger Px = P.getAffineX();
         BigInteger Py = P.getAffineY();
         BigInteger p = ((ECFieldFp)curve.getField()).getP();
         BigInteger a = curve.getA();
         BigInteger s = THREE.multiply(Px.pow(2)).add(a).mod(p).multiply(TWO.multiply(Py).modInverse(p)).mod(p);
         BigInteger x = s.pow(2).subtract(TWO.multiply(Px)).mod(p);
         BigInteger y = s.multiply(Px.subtract(x)).subtract(Py).mod(p);
         return new ECPoint(x, y);
      }
   }

   static {
      VALUES = Collections.setOf(new ECCurve[]{P256, P384, P521});
      BY_ID = new LinkedHashMap(3);
      BY_JCA_CURVE = new LinkedHashMap(3);

      for(ECCurve curve : VALUES) {
         BY_ID.put(curve.getId(), curve);
      }

      for(ECCurve curve : VALUES) {
         BY_JCA_CURVE.put(curve.spec.getCurve(), curve);
      }

   }
}
