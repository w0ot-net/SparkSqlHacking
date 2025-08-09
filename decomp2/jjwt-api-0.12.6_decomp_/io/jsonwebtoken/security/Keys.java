package io.jsonwebtoken.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.lang.Classes;
import java.security.PrivateKey;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public final class Keys {
   private static final String BRIDGE_CLASSNAME = "io.jsonwebtoken.impl.security.KeysBridge";
   private static final Class BRIDGE_CLASS = Classes.forName("io.jsonwebtoken.impl.security.KeysBridge");
   private static final Class[] FOR_PASSWORD_ARG_TYPES = new Class[]{char[].class};
   private static final Class[] SECRET_BUILDER_ARG_TYPES = new Class[]{SecretKey.class};
   private static final Class[] PRIVATE_BUILDER_ARG_TYPES = new Class[]{PrivateKey.class};

   private static Object invokeStatic(String method, Class[] argTypes, Object... args) {
      return Classes.invokeStatic(BRIDGE_CLASS, method, argTypes, args);
   }

   private Keys() {
   }

   public static SecretKey hmacShaKeyFor(byte[] bytes) throws WeakKeyException {
      if (bytes == null) {
         throw new InvalidKeyException("SecretKey byte array cannot be null.");
      } else {
         int bitLength = bytes.length * 8;
         if (bitLength >= 512) {
            return new SecretKeySpec(bytes, "HmacSHA512");
         } else if (bitLength >= 384) {
            return new SecretKeySpec(bytes, "HmacSHA384");
         } else if (bitLength >= 256) {
            return new SecretKeySpec(bytes, "HmacSHA256");
         } else {
            String msg = "The specified key byte array is " + bitLength + " bits which " + "is not secure enough for any JWT HMAC-SHA algorithm.  The JWT " + "JWA Specification (RFC 7518, Section 3.2) states that keys used with HMAC-SHA algorithms MUST have a " + "size >= 256 bits (the key size must be greater than or equal to the hash " + "output size).  Consider using the Jwts.SIG.HS256.key() builder (or HS384.key() " + "or HS512.key()) to create a key guaranteed to be secure enough for your preferred HMAC-SHA " + "algorithm.  See https://tools.ietf.org/html/rfc7518#section-3.2 for more information.";
            throw new WeakKeyException(msg);
         }
      }
   }

   /** @deprecated */
   @Deprecated
   public static SecretKey secretKeyFor(io.jsonwebtoken.SignatureAlgorithm alg) throws IllegalArgumentException {
      Assert.notNull(alg, "SignatureAlgorithm cannot be null.");
      SecureDigestAlgorithm<?, ?> salg = (SecureDigestAlgorithm)Jwts.SIG.get().get(alg.name());
      if (!(salg instanceof MacAlgorithm)) {
         String msg = "The " + alg.name() + " algorithm does not support shared secret keys.";
         throw new IllegalArgumentException(msg);
      } else {
         return (SecretKey)((SecretKeyBuilder)((MacAlgorithm)salg).key()).build();
      }
   }

   /** @deprecated */
   @Deprecated
   public static java.security.KeyPair keyPairFor(io.jsonwebtoken.SignatureAlgorithm alg) throws IllegalArgumentException {
      Assert.notNull(alg, "SignatureAlgorithm cannot be null.");
      SecureDigestAlgorithm<?, ?> salg = (SecureDigestAlgorithm)Jwts.SIG.get().get(alg.name());
      if (!(salg instanceof SignatureAlgorithm)) {
         String msg = "The " + alg.name() + " algorithm does not support Key Pairs.";
         throw new IllegalArgumentException(msg);
      } else {
         SignatureAlgorithm asalg = (SignatureAlgorithm)salg;
         return (java.security.KeyPair)asalg.keyPair().build();
      }
   }

   public static Password password(char[] password) {
      return (Password)invokeStatic("password", FOR_PASSWORD_ARG_TYPES, password);
   }

   public static SecretKeyBuilder builder(SecretKey key) {
      Assert.notNull(key, "SecretKey cannot be null.");
      return (SecretKeyBuilder)invokeStatic("builder", SECRET_BUILDER_ARG_TYPES, key);
   }

   public static PrivateKeyBuilder builder(PrivateKey key) {
      Assert.notNull(key, "PrivateKey cannot be null.");
      return (PrivateKeyBuilder)invokeStatic("builder", PRIVATE_BUILDER_ARG_TYPES, key);
   }
}
