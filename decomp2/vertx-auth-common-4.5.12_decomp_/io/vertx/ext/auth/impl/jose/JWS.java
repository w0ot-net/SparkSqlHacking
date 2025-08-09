package io.vertx.ext.auth.impl.jose;

import io.vertx.codegen.annotations.Nullable;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.ext.auth.impl.asn.ASN1;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.CRLException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509CRL;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAKey;
import java.security.spec.MGF1ParameterSpec;
import java.security.spec.PSSParameterSpec;
import javax.crypto.Mac;

public final class JWS {
   private static final Logger LOG = LoggerFactory.getLogger(JWS.class);
   public static final String EdDSA = "EdDSA";
   public static final String ES256 = "ES256";
   public static final String ES384 = "ES384";
   public static final String ES512 = "ES512";
   public static final String PS256 = "PS256";
   public static final String PS384 = "PS384";
   public static final String PS512 = "PS512";
   public static final String ES256K = "ES256K";
   public static final String RS256 = "RS256";
   public static final String RS384 = "RS384";
   public static final String RS512 = "RS512";
   public static final String RS1 = "RS1";
   public static final String HS256 = "HS256";
   public static final String HS384 = "HS384";
   public static final String HS512 = "HS512";
   private static final CertificateFactory X509;
   private final JWK jwk;
   private final Signature signature;
   private final int len;

   public JWS(JWK jwk) {
      if (jwk.use() != null && !"sig".equals(jwk.use())) {
         throw new IllegalArgumentException("JWK isn't meant to perform JWS operations");
      } else {
         try {
            this.signature = getSignature(jwk.getAlgorithm());
            this.len = getSignatureLength(jwk.getAlgorithm(), jwk.publicKey());
         } catch (InvalidAlgorithmParameterException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
         }

         this.jwk = jwk;
      }
   }

   public byte[] sign(byte[] payload) {
      if (payload == null) {
         throw new NullPointerException("payload is missing");
      } else {
         Mac mac = this.jwk.mac();
         if (mac != null) {
            synchronized(this.jwk) {
               return mac.doFinal(payload);
            }
         } else {
            PrivateKey privateKey = this.jwk.privateKey();
            String kty = this.jwk.kty();
            if (privateKey == null) {
               throw new IllegalStateException("JWK doesn't contain secKey material");
            } else {
               try {
                  synchronized(this.signature) {
                     this.signature.initSign(privateKey);
                     this.signature.update(payload);
                     byte[] sig = this.signature.sign();
                     switch (kty) {
                        case "EC":
                           return toJWS(sig, this.len);
                        default:
                           return sig;
                     }
                  }
               } catch (InvalidKeyException | SignatureException e) {
                  throw new RuntimeException(e);
               }
            }
         }
      }
   }

   public boolean verify(byte[] expected, byte[] payload) {
      if (expected == null) {
         throw new NullPointerException("signature is missing");
      } else if (payload == null) {
         throw new NullPointerException("payload is missing");
      } else {
         Mac mac = this.jwk.mac();
         if (mac != null) {
            synchronized(this.jwk) {
               return MessageDigest.isEqual(expected, this.sign(payload));
            }
         } else {
            try {
               PublicKey publicKey = this.jwk.publicKey();
               String kty = this.jwk.kty();
               if (publicKey == null) {
                  throw new IllegalStateException("JWK doesn't contain pubKey material");
               } else {
                  synchronized(this.signature) {
                     this.signature.initVerify(publicKey);
                     this.signature.update(payload);
                     switch (kty) {
                        case "EC":
                           if (!isASN1(expected)) {
                              expected = toASN1(expected);
                           }
                        default:
                           if (expected.length < this.len) {
                              byte[] normalized = new byte[this.len];
                              System.arraycopy(expected, 0, normalized, 0, expected.length);
                              return this.signature.verify(normalized);
                           } else {
                              return this.signature.verify(expected);
                           }
                     }
                  }
               }
            } catch (InvalidKeyException | SignatureException e) {
               throw new RuntimeException(e);
            }
         }
      }
   }

   public JWK jwk() {
      return this.jwk;
   }

   private static @Nullable Signature getSignature(String alg) throws NoSuchAlgorithmException, InvalidAlgorithmParameterException {
      switch (alg) {
         case "HS256":
         case "HS384":
         case "HS512":
            return null;
         case "ES256":
         case "ES256K":
            return Signature.getInstance("SHA256withECDSA");
         case "ES384":
            return Signature.getInstance("SHA384withECDSA");
         case "ES512":
            return Signature.getInstance("SHA512withECDSA");
         case "RS256":
            return Signature.getInstance("SHA256withRSA");
         case "RS384":
            return Signature.getInstance("SHA384withRSA");
         case "RS512":
            return Signature.getInstance("SHA512withRSA");
         case "RS1":
            return Signature.getInstance("SHA1withRSA");
         case "PS256":
            Signature sig = Signature.getInstance("RSASSA-PSS");
            sig.setParameter(new PSSParameterSpec("SHA-256", "MGF1", MGF1ParameterSpec.SHA256, 32, 1));
            return sig;
         case "PS384":
            Signature sig = Signature.getInstance("RSASSA-PSS");
            sig.setParameter(new PSSParameterSpec("SHA-384", "MGF1", MGF1ParameterSpec.SHA384, 48, 1));
            return sig;
         case "PS512":
            Signature sig = Signature.getInstance("RSASSA-PSS");
            sig.setParameter(new PSSParameterSpec("SHA-512", "MGF1", MGF1ParameterSpec.SHA512, 64, 1));
            return sig;
         case "EdDSA":
            return Signature.getInstance("EdDSA");
         default:
            throw new NoSuchAlgorithmException();
      }
   }

   public static boolean verifySignature(String alg, X509Certificate certificate, byte[] signature, byte[] data) throws InvalidKeyException, SignatureException, InvalidAlgorithmParameterException, NoSuchAlgorithmException {
      if (alg != null && certificate != null && signature != null && data != null) {
         switch (alg) {
            case "ES256":
            case "ES384":
            case "ES512":
            case "ES256K":
               if (!isASN1(signature)) {
                  signature = toASN1(signature);
               }
            default:
               Signature sig = getSignature(alg);
               if (sig == null) {
                  throw new SignatureException("Cannot get a signature for: " + alg);
               } else {
                  sig.initVerify(certificate);
                  sig.update(data);
                  return sig.verify(signature);
               }
         }
      } else {
         throw new SignatureException("Cannot validate signature, one of {alg, certificate, signature, data} is null");
      }
   }

   private static int getSignatureLength(String alg, PublicKey publicKey) throws NoSuchAlgorithmException {
      if (publicKey instanceof RSAKey) {
         return ((RSAKey)publicKey).getModulus().bitLength() + 7 >> 3;
      } else {
         switch (alg) {
            case "EdDSA":
            case "ES256":
            case "ES256K":
               return 64;
            case "ES384":
               return 96;
            case "ES512":
               return 132;
            case "HS256":
            case "RS1":
            case "RS256":
            case "PS256":
               return 256;
            case "HS384":
            case "RS384":
            case "PS384":
               return 384;
            case "HS512":
            case "RS512":
            case "PS512":
               return 512;
            default:
               throw new NoSuchAlgorithmException();
         }
      }
   }

   public static X509Certificate parseX5c(String data) throws CertificateException {
      X509Certificate certificate = (X509Certificate)X509.generateCertificate(new ByteArrayInputStream(addBoundaries(data, "CERTIFICATE").getBytes(StandardCharsets.UTF_8)));
      if (LOG.isDebugEnabled()) {
         String crl = extractCRLs(certificate);
         if (crl != null) {
            LOG.debug("CRL Distribution Point: " + crl);
         }
      }

      return certificate;
   }

   public static X509Certificate parseX5c(byte[] data) throws CertificateException {
      X509Certificate certificate = (X509Certificate)X509.generateCertificate(new ByteArrayInputStream(data));
      if (LOG.isDebugEnabled()) {
         String crl = extractCRLs(certificate);
         if (crl != null) {
            LOG.debug("CRL Distribution Point: " + crl);
         }
      }

      return certificate;
   }

   public static String extractCRLs(X509Certificate certificate) throws CertificateException {
      if (certificate != null) {
         byte[] crlExtension = certificate.getExtensionValue("2.5.29.31");
         if (crlExtension != null) {
            ASN1.ASN extension = ASN1.parseASN1(crlExtension);
            if (!extension.is(4)) {
               throw new CertificateException("2.5.29.31 Extension is not an ASN.1 OCTET STRING!");
            }

            extension = ASN1.parseASN1(extension.binary(0));
            if (!extension.is(16)) {
               throw new CertificateException("2.5.29.31 Extension is not an ASN.1 SEQUENCE!");
            }

            for(int i = 0; i < extension.length(); ++i) {
               ASN1.ASN crlDistributionPoint = extension.object(i, 16);
               ASN1.ASN crlIssuer = crlDistributionPoint.object(0).object(0).object(0, 134);
               if (crlIssuer != null) {
                  return new String(crlIssuer.binary(0), StandardCharsets.US_ASCII);
               }
            }
         }
      }

      return null;
   }

   public static X509CRL parseX5crl(String data) throws CRLException {
      return (X509CRL)X509.generateCRL(new ByteArrayInputStream(addBoundaries(data, "X509 CRL").getBytes(StandardCharsets.UTF_8)));
   }

   public static X509CRL parseX5crl(byte[] data) throws CRLException {
      return (X509CRL)X509.generateCRL(new ByteArrayInputStream(data));
   }

   private static boolean byteAtIndexIs(byte[] data, int idx, int expected) {
      if (data == null) {
         return false;
      } else if (data.length <= idx) {
         return false;
      } else {
         return Byte.toUnsignedInt(data[idx]) == expected;
      }
   }

   private static boolean byteAtIndexLte(byte[] data, int idx, int expected) {
      if (data == null) {
         return false;
      } else if (data.length <= idx) {
         return false;
      } else if (data[idx] <= 0) {
         return false;
      } else {
         return Byte.toUnsignedInt(data[idx]) <= expected;
      }
   }

   public static boolean isASN1(byte[] sig) {
      if (!byteAtIndexIs(sig, 0, 48)) {
         return false;
      } else {
         int offset;
         if (sig.length < 128) {
            offset = 0;
         } else {
            if (!byteAtIndexIs(sig, 1, 129)) {
               return false;
            }

            offset = 1;
         }

         if (!byteAtIndexIs(sig, offset + 1, sig.length - offset - 2)) {
            return false;
         } else {
            offset += 2;

            for(int i = 0; i < 2; ++i) {
               if (!byteAtIndexIs(sig, offset, 2)) {
                  return false;
               }

               if (!byteAtIndexLte(sig, offset + 1, sig.length - offset - 2)) {
                  return false;
               }

               offset = offset + sig[offset + 1] + 2;
            }

            return offset == sig.length;
         }
      }
   }

   public static byte[] toJWS(byte[] derSignature, int signatureLength) {
      if (derSignature.length >= 8 && derSignature[0] == 48) {
         int offset;
         if (derSignature[1] > 0) {
            offset = 2;
         } else {
            if (derSignature[1] != -127) {
               throw new RuntimeException("Invalid ECDSA signature format");
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
         rawLen = Math.max(rawLen, signatureLength / 2);
         if ((derSignature[offset - 1] & 255) == derSignature.length - offset && (derSignature[offset - 1] & 255) == 2 + rLength + 2 + sLength && derSignature[offset] == 2 && derSignature[offset + 2 + rLength] == 2) {
            byte[] concatSignature = new byte[2 * rawLen];
            System.arraycopy(derSignature, offset + 2 + rLength - i, concatSignature, rawLen - i, i);
            System.arraycopy(derSignature, offset + 2 + rLength + 2 + sLength - j, concatSignature, 2 * rawLen - j, j);
            return concatSignature;
         } else {
            throw new RuntimeException("Invalid ECDSA signature format");
         }
      } else {
         throw new RuntimeException("Invalid ECDSA signature format");
      }
   }

   public static byte[] toASN1(byte[] jwsSignature) {
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

      if (k == 0) {
         throw new RuntimeException("Invalid ECDSA signature");
      } else {
         int l = k;
         if (jwsSignature[2 * rawLen - k] < 0) {
            l = k + 1;
         }

         int len = 2 + j + 2 + l;
         if (len > 255) {
            throw new RuntimeException("Invalid ECDSA signature format");
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
   }

   private static String addBoundaries(String certificate, String boundary) {
      String CERT_BOUNDARY_BEGIN = "-----BEGIN " + boundary + "-----\n";
      String CERT_BOUNDARY_END = "\n-----END " + boundary + "-----\n";
      return certificate.contains(CERT_BOUNDARY_BEGIN) && certificate.contains(CERT_BOUNDARY_END) ? certificate : CERT_BOUNDARY_BEGIN + certificate + CERT_BOUNDARY_END;
   }

   static {
      try {
         X509 = CertificateFactory.getInstance("X.509");
      } catch (CertificateException e) {
         throw new RuntimeException(e);
      }
   }
}
