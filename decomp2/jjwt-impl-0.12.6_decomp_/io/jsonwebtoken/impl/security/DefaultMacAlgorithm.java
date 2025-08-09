package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.impl.lang.Bytes;
import io.jsonwebtoken.impl.lang.CheckedFunction;
import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.lang.Collections;
import io.jsonwebtoken.lang.Strings;
import io.jsonwebtoken.security.InvalidKeyException;
import io.jsonwebtoken.security.MacAlgorithm;
import io.jsonwebtoken.security.Password;
import io.jsonwebtoken.security.SecretKeyBuilder;
import io.jsonwebtoken.security.SecureRequest;
import io.jsonwebtoken.security.VerifySecureDigestRequest;
import io.jsonwebtoken.security.WeakKeyException;
import java.io.InputStream;
import java.security.Key;
import java.security.MessageDigest;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import javax.crypto.Mac;
import javax.crypto.SecretKey;

final class DefaultMacAlgorithm extends AbstractSecureDigestAlgorithm implements MacAlgorithm {
   private static final String HS256_OID = "1.2.840.113549.2.9";
   private static final String HS384_OID = "1.2.840.113549.2.10";
   private static final String HS512_OID = "1.2.840.113549.2.11";
   private static final Set JWA_STANDARD_IDS = new LinkedHashSet(Collections.of(new String[]{"HS256", "HS384", "HS512"}));
   static final DefaultMacAlgorithm HS256 = new DefaultMacAlgorithm(256);
   static final DefaultMacAlgorithm HS384 = new DefaultMacAlgorithm(384);
   static final DefaultMacAlgorithm HS512 = new DefaultMacAlgorithm(512);
   private static final Map JCA_NAME_MAP = new LinkedHashMap(6);
   private final int minKeyBitLength;

   private DefaultMacAlgorithm(int digestBitLength) {
      this("HS" + digestBitLength, "HmacSHA" + digestBitLength, digestBitLength);
   }

   DefaultMacAlgorithm(String id, String jcaName, int minKeyBitLength) {
      super(id, jcaName);
      Assert.isTrue(minKeyBitLength > 0, "minKeyLength must be greater than zero.");
      this.minKeyBitLength = minKeyBitLength;
   }

   public int getKeyBitLength() {
      return this.minKeyBitLength;
   }

   private boolean isJwaStandard() {
      return JWA_STANDARD_IDS.contains(this.getId());
   }

   private static boolean isJwaStandardJcaName(String jcaName) {
      String key = jcaName.toUpperCase(Locale.ENGLISH);
      return JCA_NAME_MAP.containsKey(key);
   }

   static DefaultMacAlgorithm findByKey(Key key) {
      String alg = KeysBridge.findAlgorithm(key);
      if (!Strings.hasText(alg)) {
         return null;
      } else {
         String upper = alg.toUpperCase(Locale.ENGLISH);
         DefaultMacAlgorithm mac = (DefaultMacAlgorithm)JCA_NAME_MAP.get(upper);
         if (mac == null) {
            return null;
         } else {
            byte[] encoded = KeysBridge.findEncoded(key);
            long size = Bytes.bitLength(encoded);
            return size >= (long)mac.getKeyBitLength() ? mac : null;
         }
      }
   }

   public SecretKeyBuilder key() {
      return new DefaultSecretKeyBuilder(this.getJcaName(), this.getKeyBitLength());
   }

   private void assertAlgorithmName(SecretKey key, boolean signing) {
      String name = key.getAlgorithm();
      if (!Strings.hasText(name)) {
         String msg = "The " + keyType(signing) + " key's algorithm cannot be null or empty.";
         throw new InvalidKeyException(msg);
      } else {
         boolean generic = KeysBridge.isGenericSecret(key);
         if (!generic && this.isJwaStandard() && !isJwaStandardJcaName(name)) {
            throw new InvalidKeyException("The " + keyType(signing) + " key's algorithm '" + name + "' does not equal a valid HmacSHA* algorithm name or PKCS12 OID and cannot be used with " + this.getId() + ".");
         }
      }
   }

   protected void validateKey(Key k, boolean signing) {
      String keyType = keyType(signing);
      if (k == null) {
         throw new IllegalArgumentException("MAC " + keyType + " key cannot be null.");
      } else if (!(k instanceof SecretKey)) {
         String msg = "MAC " + keyType + " keys must be SecretKey instances.  Specified key is of type " + k.getClass().getName();
         throw new InvalidKeyException(msg);
      } else if (k instanceof Password) {
         String msg = "Passwords are intended for use with key derivation algorithms only.";
         throw new InvalidKeyException(msg);
      } else {
         SecretKey key = (SecretKey)k;
         String id = this.getId();
         this.assertAlgorithmName(key, signing);
         int size = KeysBridge.findBitLength(key);
         if (size >= 0) {
            if (size < this.minKeyBitLength) {
               String msg = "The " + keyType + " key's size is " + size + " bits which " + "is not secure enough for the " + id + " algorithm.";
               if (this.isJwaStandard() && isJwaStandardJcaName(this.getJcaName())) {
                  msg = msg + " The JWT JWA Specification (RFC 7518, Section 3.2) states that keys used with " + id + " MUST have a " + "size >= " + this.minKeyBitLength + " bits (the key size must be greater than or equal to the hash " + "output size). Consider using the Jwts.SIG." + id + ".key() " + "builder to create a key guaranteed to be secure enough for " + id + ".  See " + "https://tools.ietf.org/html/rfc7518#section-3.2 for more information.";
               } else {
                  msg = msg + " The " + id + " algorithm requires keys to have a size >= " + this.minKeyBitLength + " bits.";
               }

               throw new WeakKeyException(msg);
            }
         }
      }
   }

   public byte[] doDigest(final SecureRequest request) {
      return (byte[])this.jca(request).withMac(new CheckedFunction() {
         public byte[] apply(Mac mac) throws Exception {
            mac.init(request.getKey());
            InputStream payload = (InputStream)request.getPayload();
            byte[] buf = new byte[1024];
            int len = 0;

            while(len != -1) {
               len = payload.read(buf);
               if (len > 0) {
                  mac.update(buf, 0, len);
               }
            }

            return mac.doFinal();
         }
      });
   }

   protected boolean doVerify(VerifySecureDigestRequest request) {
      byte[] providedSignature = request.getDigest();
      Assert.notEmpty(providedSignature, "Request signature byte array cannot be null or empty.");
      byte[] computedSignature = this.digest(request);
      return MessageDigest.isEqual(providedSignature, computedSignature);
   }

   static {
      JCA_NAME_MAP.put(HS256.getJcaName().toUpperCase(Locale.ENGLISH), HS256);
      JCA_NAME_MAP.put("1.2.840.113549.2.9", HS256);
      JCA_NAME_MAP.put(HS384.getJcaName().toUpperCase(Locale.ENGLISH), HS384);
      JCA_NAME_MAP.put("1.2.840.113549.2.10", HS384);
      JCA_NAME_MAP.put(HS512.getJcaName().toUpperCase(Locale.ENGLISH), HS512);
      JCA_NAME_MAP.put("1.2.840.113549.2.11", HS512);
   }
}
