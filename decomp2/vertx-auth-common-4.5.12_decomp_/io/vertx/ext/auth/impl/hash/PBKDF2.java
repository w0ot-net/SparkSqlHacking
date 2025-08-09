package io.vertx.ext.auth.impl.hash;

import io.vertx.ext.auth.HashString;
import io.vertx.ext.auth.HashingAlgorithm;
import io.vertx.ext.auth.impl.Codec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Collections;
import java.util.Set;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class PBKDF2 implements HashingAlgorithm {
   private static final int DEFAULT_ITERATIONS = 10000;
   private static final Set DEFAULT_CONFIG = Collections.singleton("it");
   private final SecretKeyFactory skf;

   public PBKDF2() {
      try {
         this.skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
      } catch (NoSuchAlgorithmException nsae) {
         throw new RuntimeException("PBKDF2 is not available", nsae);
      }
   }

   public String id() {
      return "pbkdf2";
   }

   public Set params() {
      return DEFAULT_CONFIG;
   }

   public String hash(HashString hashString, String password) {
      int iterations;
      try {
         if (hashString.params() != null) {
            iterations = Integer.parseInt((String)hashString.params().get("it"));
         } else {
            iterations = 10000;
         }
      } catch (RuntimeException var8) {
         iterations = 10000;
      }

      if (hashString.salt() == null) {
         throw new RuntimeException("hashString salt is null");
      } else {
         byte[] salt = Codec.base64Decode(hashString.salt());
         PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterations, 512);

         try {
            return Codec.base64EncodeWithoutPadding(this.skf.generateSecret(spec).getEncoded());
         } catch (InvalidKeySpecException ikse) {
            throw new RuntimeException(ikse);
         }
      }
   }
}
