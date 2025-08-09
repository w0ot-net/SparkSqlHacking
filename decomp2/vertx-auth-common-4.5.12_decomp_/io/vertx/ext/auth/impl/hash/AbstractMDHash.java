package io.vertx.ext.auth.impl.hash;

import io.vertx.ext.auth.HashString;
import io.vertx.ext.auth.HashingAlgorithm;
import io.vertx.ext.auth.impl.Codec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public abstract class AbstractMDHash implements HashingAlgorithm {
   private final MessageDigest md;

   AbstractMDHash(String algorithm) {
      try {
         this.md = MessageDigest.getInstance(algorithm);
      } catch (NoSuchAlgorithmException nsae) {
         throw new RuntimeException(algorithm + " is not available", nsae);
      }
   }

   public String hash(HashString hashString, String password) {
      return Codec.base64EncodeWithoutPadding(this.md.digest(password.getBytes(StandardCharsets.UTF_8)));
   }
}
