package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.impl.lang.Bytes;
import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.lang.Strings;
import io.jsonwebtoken.security.InvalidKeyException;
import io.jsonwebtoken.security.KeySupplier;
import io.jsonwebtoken.security.Password;
import io.jsonwebtoken.security.PrivateKeyBuilder;
import io.jsonwebtoken.security.SecretKeyBuilder;
import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.ECKey;
import java.security.interfaces.RSAKey;
import javax.crypto.SecretKey;

public final class KeysBridge {
   private static final String GENERIC_SECRET_ALG_PREFIX = "Generic";

   private KeysBridge() {
   }

   public static Password password(char[] password) {
      return new PasswordSpec(password);
   }

   public static SecretKeyBuilder builder(SecretKey key) {
      return new ProvidedSecretKeyBuilder(key);
   }

   public static PrivateKeyBuilder builder(PrivateKey key) {
      return new ProvidedPrivateKeyBuilder(key);
   }

   public static Key root(Key key) {
      return key instanceof KeySupplier ? root((KeySupplier)key) : key;
   }

   public static Key root(KeySupplier supplier) {
      Assert.notNull(supplier, "KeySupplier canot be null.");
      return (Key)Assert.notNull(root(supplier.getKey()), "KeySupplier key cannot be null.");
   }

   public static String findAlgorithm(Key key) {
      return key != null ? Strings.clean(key.getAlgorithm()) : null;
   }

   public static byte[] findEncoded(Key key) {
      Assert.notNull(key, "Key cannot be null.");
      byte[] encoded = null;

      try {
         encoded = key.getEncoded();
      } catch (Throwable var3) {
      }

      return encoded;
   }

   public static boolean isGenericSecret(Key key) {
      if (!(key instanceof SecretKey)) {
         return false;
      } else {
         String algName = (String)Assert.hasText(key.getAlgorithm(), "Key algorithm cannot be null or empty.");
         return algName.startsWith("Generic");
      }
   }

   public static int findBitLength(Key key) {
      int bitlen = -1;
      if (key instanceof SecretKey) {
         SecretKey secretKey = (SecretKey)key;
         if ("RAW".equals(secretKey.getFormat())) {
            byte[] encoded = findEncoded(secretKey);
            if (!Bytes.isEmpty(encoded)) {
               bitlen = (int)Bytes.bitLength(encoded);
               Bytes.clear(encoded);
            }
         }
      } else if (key instanceof RSAKey) {
         RSAKey rsaKey = (RSAKey)key;
         bitlen = rsaKey.getModulus().bitLength();
      } else if (key instanceof ECKey) {
         ECKey ecKey = (ECKey)key;
         bitlen = ecKey.getParams().getOrder().bitLength();
      } else {
         EdwardsCurve curve = EdwardsCurve.findByKey(key);
         if (curve != null) {
            bitlen = curve.getKeyBitLength();
         }
      }

      return bitlen;
   }

   public static byte[] getEncoded(Key key) {
      Assert.notNull(key, "Key cannot be null.");

      byte[] encoded;
      try {
         encoded = key.getEncoded();
      } catch (Throwable t) {
         String msg = "Cannot obtain required encoded bytes from key [" + toString(key) + "]: " + t.getMessage();
         throw new InvalidKeyException(msg, t);
      }

      if (Bytes.isEmpty(encoded)) {
         String msg = "Missing required encoded bytes for key [" + toString(key) + "].";
         throw new InvalidKeyException(msg);
      } else {
         return encoded;
      }
   }

   public static String toString(Key key) {
      if (key == null) {
         return "null";
      } else {
         return key instanceof PublicKey ? key.toString() : "class: " + key.getClass().getName() + ", algorithm: " + key.getAlgorithm() + ", format: " + key.getFormat();
      }
   }
}
