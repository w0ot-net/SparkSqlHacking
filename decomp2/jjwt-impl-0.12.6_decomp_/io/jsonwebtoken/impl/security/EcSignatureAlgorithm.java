package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.impl.lang.Bytes;
import io.jsonwebtoken.impl.lang.CheckedFunction;
import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.lang.Collections;
import io.jsonwebtoken.lang.Strings;
import io.jsonwebtoken.security.InvalidKeyException;
import io.jsonwebtoken.security.KeyPairBuilder;
import io.jsonwebtoken.security.KeySupplier;
import io.jsonwebtoken.security.SecureRequest;
import io.jsonwebtoken.security.SignatureAlgorithm;
import io.jsonwebtoken.security.SignatureException;
import io.jsonwebtoken.security.VerifySecureDigestRequest;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.ECKey;
import java.security.spec.ECGenParameterSpec;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

final class EcSignatureAlgorithm extends AbstractSignatureAlgorithm {
   private static final String REQD_ORDER_BIT_LENGTH_MSG = "orderBitLength must equal 256, 384, or 521.";
   private static final String DER_ENCODING_SYS_PROPERTY_NAME = "io.jsonwebtoken.impl.crypto.EllipticCurveSignatureValidator.derEncodingSupported";
   private static final String ES256_OID = "1.2.840.10045.4.3.2";
   private static final String ES384_OID = "1.2.840.10045.4.3.3";
   private static final String ES512_OID = "1.2.840.10045.4.3.4";
   private static final Set KEY_ALG_NAMES = Collections.setOf(new String[]{"EC", "ECDSA", "1.2.840.10045.4.3.2", "1.2.840.10045.4.3.3", "1.2.840.10045.4.3.4"});
   private final ECGenParameterSpec KEY_PAIR_GEN_PARAMS;
   private final int orderBitLength;
   private final String OID;
   private final int signatureByteLength;
   private final int sigFieldByteLength;
   static final EcSignatureAlgorithm ES256 = new EcSignatureAlgorithm(256, "1.2.840.10045.4.3.2");
   static final EcSignatureAlgorithm ES384 = new EcSignatureAlgorithm(384, "1.2.840.10045.4.3.3");
   static final EcSignatureAlgorithm ES512 = new EcSignatureAlgorithm(521, "1.2.840.10045.4.3.4");
   private static final Map BY_OID = new LinkedHashMap(3);

   private static int shaSize(int orderBitLength) {
      return orderBitLength == 521 ? 512 : orderBitLength;
   }

   private static boolean isSupportedOrderBitLength(int orderBitLength) {
      return orderBitLength == 256 || orderBitLength == 384 || orderBitLength == 521;
   }

   static SignatureAlgorithm findByKey(Key key) {
      String algName = KeysBridge.findAlgorithm(key);
      if (!Strings.hasText(algName)) {
         return null;
      } else {
         algName = algName.toUpperCase(Locale.ENGLISH);
         SignatureAlgorithm alg = (SignatureAlgorithm)BY_OID.get(algName);
         if (alg != null) {
            return alg;
         } else {
            if ("EC".equalsIgnoreCase(algName) || "ECDSA".equalsIgnoreCase(algName)) {
               int bitLength = KeysBridge.findBitLength(key);
               if (bitLength == ES512.orderBitLength) {
                  return ES512;
               }

               if (bitLength == ES384.orderBitLength) {
                  return ES384;
               }

               if (bitLength == ES256.orderBitLength) {
                  return ES256;
               }
            }

            return null;
         }
      }
   }

   private EcSignatureAlgorithm(int orderBitLength, String oid) {
      super("ES" + shaSize(orderBitLength), "SHA" + shaSize(orderBitLength) + "withECDSA");
      Assert.isTrue(isSupportedOrderBitLength(orderBitLength), "orderBitLength must equal 256, 384, or 521.");
      this.OID = (String)Assert.hasText(oid, "Invalid OID.");
      String curveName = "secp" + orderBitLength + "r1";
      this.KEY_PAIR_GEN_PARAMS = new ECGenParameterSpec(curveName);
      this.orderBitLength = orderBitLength;
      this.sigFieldByteLength = Bytes.length(this.orderBitLength);
      this.signatureByteLength = this.sigFieldByteLength * 2;
   }

   public KeyPairBuilder keyPair() {
      return (KeyPairBuilder)(new DefaultKeyPairBuilder("EC", this.KEY_PAIR_GEN_PARAMS)).random(Randoms.secureRandom());
   }

   protected void validateKey(Key key, boolean signing) {
      super.validateKey(key, signing);
      if (!KEY_ALG_NAMES.contains(KeysBridge.findAlgorithm(key))) {
         throw new InvalidKeyException("Unrecognized EC key algorithm name.");
      } else {
         int size = KeysBridge.findBitLength(key);
         if (size >= 0) {
            int sigFieldByteLength = Bytes.length(size);
            int concatByteLength = sigFieldByteLength * 2;
            if (concatByteLength != this.signatureByteLength) {
               String msg = "The provided Elliptic Curve " + keyType(signing) + " key size (aka order bit length) is " + Bytes.bitsMsg((long)size) + ", but the '" + this.getId() + "' algorithm requires EC Keys with " + Bytes.bitsMsg((long)this.orderBitLength) + " per [RFC 7518, Section 3.4](https://www.rfc-editor.org/rfc/rfc7518.html#section-3.4).";
               throw new InvalidKeyException(msg);
            }
         }
      }
   }

   protected byte[] doDigest(final SecureRequest request) {
      return (byte[])this.jca(request).withSignature(new CheckedFunction() {
         public byte[] apply(Signature sig) throws Exception {
            sig.initSign((PrivateKey)KeysBridge.root((KeySupplier)request));
            byte[] signature = EcSignatureAlgorithm.this.sign(sig, (InputStream)request.getPayload());
            return EcSignatureAlgorithm.transcodeDERToConcat(signature, EcSignatureAlgorithm.this.signatureByteLength);
         }
      });
   }

   boolean isValidRAndS(PublicKey key, byte[] concatSignature) {
      if (!(key instanceof ECKey)) {
         return true;
      } else {
         ECKey ecKey = (ECKey)key;
         BigInteger order = ecKey.getParams().getOrder();
         BigInteger r = new BigInteger(1, Arrays.copyOfRange(concatSignature, 0, this.sigFieldByteLength));
         BigInteger s = new BigInteger(1, Arrays.copyOfRange(concatSignature, this.sigFieldByteLength, concatSignature.length));
         return r.signum() >= 1 && s.signum() >= 1 && r.compareTo(order) < 0 && s.compareTo(order) < 0;
      }
   }

   protected boolean doVerify(final VerifySecureDigestRequest request) {
      final PublicKey key = (PublicKey)request.getKey();
      return (Boolean)this.jca(request).withSignature(new CheckedFunction() {
         public Boolean apply(Signature sig) {
            byte[] concatSignature = request.getDigest();

            try {
               byte[] derSignature;
               if (EcSignatureAlgorithm.this.signatureByteLength != concatSignature.length) {
                  if (concatSignature[0] != 48 || !"true".equalsIgnoreCase(System.getProperty("io.jsonwebtoken.impl.crypto.EllipticCurveSignatureValidator.derEncodingSupported"))) {
                     String msg = "Provided signature is " + Bytes.bytesMsg(concatSignature.length) + " but " + EcSignatureAlgorithm.this.getId() + " signatures must be exactly " + Bytes.bytesMsg(EcSignatureAlgorithm.this.signatureByteLength) + " per [RFC 7518, Section 3.4 (validation)]" + "(https://www.rfc-editor.org/rfc/rfc7518.html#section-3.4).";
                     throw new SignatureException(msg);
                  }

                  derSignature = concatSignature;
               } else {
                  if (!EcSignatureAlgorithm.this.isValidRAndS(key, concatSignature)) {
                     return false;
                  }

                  derSignature = EcSignatureAlgorithm.transcodeConcatToDER(concatSignature);
               }

               sig.initVerify(key);
               return EcSignatureAlgorithm.this.verify(sig, (InputStream)request.getPayload(), derSignature);
            } catch (Exception e) {
               String msg = "Unable to verify Elliptic Curve signature using provided ECPublicKey: " + e.getMessage();
               throw new SignatureException(msg, e);
            }
         }
      });
   }

   public static byte[] transcodeDERToConcat(byte[] derSignature, int outputLength) throws JwtException {
      if (derSignature.length >= 8 && derSignature[0] == 48) {
         int offset;
         if (derSignature[1] > 0) {
            offset = 2;
         } else {
            if (derSignature[1] != -127) {
               throw new JwtException("Invalid ECDSA signature format");
            }

            offset = 3;
         }

         byte rLength = derSignature[offset + 1];

         int i;
         for(i = rLength; i > 0 && derSignature[offset + 2 + rLength - i] == 0; --i) {
         }

         byte sLength = derSignature[offset + 2 + rLength + 1];

         int j;
         for(j = sLength; j > 0 && derSignature[offset + 2 + rLength + 2 + sLength - j] == 0; --j) {
         }

         int rawLen = Math.max(i, j);
         rawLen = Math.max(rawLen, outputLength / 2);
         if ((derSignature[offset - 1] & 255) == derSignature.length - offset && (derSignature[offset - 1] & 255) == 2 + rLength + 2 + sLength && derSignature[offset] == 2 && derSignature[offset + 2 + rLength] == 2) {
            byte[] concatSignature = new byte[2 * rawLen];
            System.arraycopy(derSignature, offset + 2 + rLength - i, concatSignature, rawLen - i, i);
            System.arraycopy(derSignature, offset + 2 + rLength + 2 + sLength - j, concatSignature, 2 * rawLen - j, j);
            return concatSignature;
         } else {
            throw new JwtException("Invalid ECDSA signature format");
         }
      } else {
         throw new JwtException("Invalid ECDSA signature format");
      }
   }

   public static byte[] transcodeConcatToDER(byte[] jwsSignature) throws JwtException {
      try {
         return concatToDER(jwsSignature);
      } catch (Exception e) {
         String msg = "Invalid ECDSA signature format.";
         throw new SignatureException(msg, e);
      }
   }

   private static byte[] concatToDER(byte[] jwsSignature) throws ArrayIndexOutOfBoundsException {
      int rawLen = jwsSignature.length / 2;

      int i;
      for(i = rawLen; i > 0 && jwsSignature[rawLen - i] == 0; --i) {
      }

      int j = i;
      if (jwsSignature[rawLen - i] < 0) {
         j = i + 1;
      }

      int k;
      for(k = rawLen; k > 0 && jwsSignature[2 * rawLen - k] == 0; --k) {
      }

      int l = k;
      if (jwsSignature[2 * rawLen - k] < 0) {
         l = k + 1;
      }

      int len = 2 + j + 2 + l;
      if (len > 255) {
         throw new JwtException("Invalid ECDSA signature format");
      } else {
         int offset;
         byte[] derSignature;
         if (len < 128) {
            derSignature = new byte[4 + j + 2 + l];
            offset = 1;
         } else {
            derSignature = new byte[5 + j + 2 + l];
            derSignature[1] = -127;
            offset = 2;
         }

         derSignature[0] = 48;
         derSignature[offset++] = (byte)len;
         derSignature[offset++] = 2;
         derSignature[offset++] = (byte)j;
         System.arraycopy(jwsSignature, rawLen - i, derSignature, offset + j - i, i);
         offset += j;
         derSignature[offset++] = 2;
         derSignature[offset++] = (byte)l;
         System.arraycopy(jwsSignature, 2 * rawLen - k, derSignature, offset + l - k, k);
         return derSignature;
      }
   }

   static {
      for(EcSignatureAlgorithm alg : Collections.of(new EcSignatureAlgorithm[]{ES256, ES384, ES512})) {
         BY_OID.put(alg.OID, alg);
      }

   }
}
