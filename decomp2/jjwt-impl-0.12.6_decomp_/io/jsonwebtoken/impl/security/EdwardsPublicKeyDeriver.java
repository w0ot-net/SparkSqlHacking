package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.impl.lang.Function;
import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.security.InvalidKeyException;
import io.jsonwebtoken.security.KeyPairBuilder;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;

final class EdwardsPublicKeyDeriver implements Function {
   public static final Function INSTANCE = new EdwardsPublicKeyDeriver();

   private EdwardsPublicKeyDeriver() {
   }

   public PublicKey apply(PrivateKey privateKey) {
      EdwardsCurve curve = EdwardsCurve.findByKey(privateKey);
      if (curve == null) {
         String msg = "Unable to derive Edwards-curve PublicKey for specified PrivateKey: " + KeysBridge.toString(privateKey);
         throw new InvalidKeyException(msg);
      } else {
         byte[] pkBytes = curve.getKeyMaterial(privateKey);
         SecureRandom random = new ConstantRandom(pkBytes);
         KeyPair pair = (KeyPair)((KeyPairBuilder)curve.keyPair().random(random)).build();
         Assert.stateNotNull(pair, "Edwards curve generated keypair cannot be null.");
         return (PublicKey)Assert.stateNotNull(pair.getPublic(), "Edwards curve KeyPair must have a PublicKey");
      }
   }

   private static final class ConstantRandom extends SecureRandom {
      private final byte[] value;

      public ConstantRandom(byte[] value) {
         this.value = (byte[])(([B)value).clone();
      }

      public void nextBytes(byte[] bytes) {
         System.arraycopy(this.value, 0, bytes, 0, this.value.length);
      }
   }
}
