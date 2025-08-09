package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.impl.lang.CheckedFunction;
import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.lang.Collections;
import io.jsonwebtoken.lang.Strings;
import io.jsonwebtoken.security.InvalidKeyException;
import io.jsonwebtoken.security.KeyPairBuilder;
import io.jsonwebtoken.security.SecureRequest;
import io.jsonwebtoken.security.SignatureAlgorithm;
import io.jsonwebtoken.security.VerifySecureDigestRequest;
import io.jsonwebtoken.security.WeakKeyException;
import java.io.InputStream;
import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.MGF1ParameterSpec;
import java.security.spec.PSSParameterSpec;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

final class RsaSignatureAlgorithm extends AbstractSignatureAlgorithm {
   static final String PSS_JCA_NAME = "RSASSA-PSS";
   static final String PSS_OID = "1.2.840.113549.1.1.10";
   private static final String RS256_OID = "1.2.840.113549.1.1.11";
   private static final String RS384_OID = "1.2.840.113549.1.1.12";
   private static final String RS512_OID = "1.2.840.113549.1.1.13";
   private static final Set PSS_ALG_NAMES = Collections.setOf(new String[]{"RSASSA-PSS", "1.2.840.113549.1.1.10"});
   private static final Set KEY_ALG_NAMES = Collections.setOf(new String[]{"RSA", "RSASSA-PSS", "1.2.840.113549.1.1.10", "1.2.840.113549.1.1.11", "1.2.840.113549.1.1.12", "1.2.840.113549.1.1.13"});
   private static final int MIN_KEY_BIT_LENGTH = 2048;
   static final SignatureAlgorithm RS256 = new RsaSignatureAlgorithm(256);
   static final SignatureAlgorithm RS384 = new RsaSignatureAlgorithm(384);
   static final SignatureAlgorithm RS512 = new RsaSignatureAlgorithm(512);
   static final SignatureAlgorithm PS256 = rsaSsaPss(256);
   static final SignatureAlgorithm PS384 = rsaSsaPss(384);
   static final SignatureAlgorithm PS512 = rsaSsaPss(512);
   private static final Map PKCSv15_ALGS = new LinkedHashMap();
   private final int preferredKeyBitLength;
   private final AlgorithmParameterSpec algorithmParameterSpec;

   private static AlgorithmParameterSpec pssParamSpec(int digestBitLength) {
      MGF1ParameterSpec ps = new MGF1ParameterSpec("SHA-" + digestBitLength);
      int saltByteLength = digestBitLength / 8;
      return new PSSParameterSpec(ps.getDigestAlgorithm(), "MGF1", ps, saltByteLength, 1);
   }

   private static SignatureAlgorithm rsaSsaPss(int digestBitLength) {
      return new RsaSignatureAlgorithm(digestBitLength, pssParamSpec(digestBitLength));
   }

   private RsaSignatureAlgorithm(String name, String jcaName, int digestBitLength, AlgorithmParameterSpec paramSpec) {
      super(name, jcaName);
      this.preferredKeyBitLength = digestBitLength * 8;
      Assert.state(this.preferredKeyBitLength >= 2048);
      this.algorithmParameterSpec = paramSpec;
   }

   private RsaSignatureAlgorithm(int digestBitLength) {
      this("RS" + digestBitLength, "SHA" + digestBitLength + "withRSA", digestBitLength, (AlgorithmParameterSpec)null);
   }

   private RsaSignatureAlgorithm(int digestBitLength, AlgorithmParameterSpec paramSpec) {
      this("PS" + digestBitLength, "RSASSA-PSS", digestBitLength, paramSpec);
   }

   static SignatureAlgorithm findByKey(Key key) {
      String algName = KeysBridge.findAlgorithm(key);
      if (!Strings.hasText(algName)) {
         return null;
      } else {
         algName = algName.toUpperCase(Locale.ENGLISH);
         int bitLength = KeysBridge.findBitLength(key);
         if (PSS_ALG_NAMES.contains(algName)) {
            if (bitLength >= 4096) {
               return PS512;
            }

            if (bitLength >= 3072) {
               return PS384;
            }

            if (bitLength >= 2048) {
               return PS256;
            }
         }

         SignatureAlgorithm alg = (SignatureAlgorithm)PKCSv15_ALGS.get(algName);
         if (alg != null) {
            return alg;
         } else {
            if ("RSA".equals(algName)) {
               if (bitLength >= 4096) {
                  return RS512;
               }

               if (bitLength >= 3072) {
                  return RS384;
               }

               if (bitLength >= 2048) {
                  return RS256;
               }
            }

            return null;
         }
      }
   }

   static boolean isPss(Key key) {
      String alg = KeysBridge.findAlgorithm(key);
      return PSS_ALG_NAMES.contains(alg);
   }

   static boolean isRsaAlgorithmName(Key key) {
      String alg = KeysBridge.findAlgorithm(key);
      return KEY_ALG_NAMES.contains(alg);
   }

   public KeyPairBuilder keyPair() {
      String jcaName = this.algorithmParameterSpec != null ? "RSASSA-PSS" : "RSA";
      return (KeyPairBuilder)(new DefaultKeyPairBuilder(jcaName, this.preferredKeyBitLength)).random(Randoms.secureRandom());
   }

   protected void validateKey(Key key, boolean signing) {
      super.validateKey(key, signing);
      if (!isRsaAlgorithmName(key)) {
         throw new InvalidKeyException("Unrecognized RSA or RSASSA-PSS key algorithm name.");
      } else {
         int size = KeysBridge.findBitLength(key);
         if (size >= 0) {
            if (size < 2048) {
               String id = this.getId();
               String section = id.startsWith("PS") ? "3.5" : "3.3";
               String msg = "The RSA " + keyType(signing) + " key size (aka modulus bit length) is " + size + " bits " + "which is not secure enough for the " + id + " algorithm.  The JWT JWA Specification " + "(RFC 7518, Section " + section + ") states that RSA keys MUST have a size >= " + 2048 + " bits.  Consider using the Jwts.SIG." + id + ".keyPair() builder to create a KeyPair guaranteed to be secure enough for " + id + ".  See " + "https://tools.ietf.org/html/rfc7518#section-" + section + " for more information.";
               throw new WeakKeyException(msg);
            }
         }
      }
   }

   protected byte[] doDigest(final SecureRequest request) {
      return (byte[])this.jca(request).withSignature(new CheckedFunction() {
         public byte[] apply(Signature sig) throws Exception {
            if (RsaSignatureAlgorithm.this.algorithmParameterSpec != null) {
               sig.setParameter(RsaSignatureAlgorithm.this.algorithmParameterSpec);
            }

            sig.initSign((PrivateKey)request.getKey());
            return RsaSignatureAlgorithm.this.sign(sig, (InputStream)request.getPayload());
         }
      });
   }

   protected boolean doVerify(final VerifySecureDigestRequest request) {
      return (Boolean)this.jca(request).withSignature(new CheckedFunction() {
         public Boolean apply(Signature sig) throws Exception {
            if (RsaSignatureAlgorithm.this.algorithmParameterSpec != null) {
               sig.setParameter(RsaSignatureAlgorithm.this.algorithmParameterSpec);
            }

            sig.initVerify((PublicKey)request.getKey());
            return RsaSignatureAlgorithm.this.verify(sig, (InputStream)request.getPayload(), request.getDigest());
         }
      });
   }

   static {
      PKCSv15_ALGS.put("1.2.840.113549.1.1.11", RS256);
      PKCSv15_ALGS.put("1.2.840.113549.1.1.12", RS384);
      PKCSv15_ALGS.put("1.2.840.113549.1.1.13", RS512);
   }
}
