package org.apache.orc.impl;

import java.security.Key;
import javax.crypto.spec.SecretKeySpec;
import org.apache.orc.EncryptionAlgorithm;

public class LocalKey {
   private final byte[] encryptedKey;
   private Key decryptedKey;

   public LocalKey(EncryptionAlgorithm algorithm, byte[] decryptedKey, byte[] encryptedKey) {
      this.encryptedKey = encryptedKey;
      if (decryptedKey != null) {
         this.setDecryptedKey(new SecretKeySpec(decryptedKey, algorithm.getAlgorithm()));
      }

   }

   public void setDecryptedKey(Key key) {
      this.decryptedKey = key;
   }

   public Key getDecryptedKey() {
      return this.decryptedKey;
   }

   public byte[] getEncryptedKey() {
      return this.encryptedKey;
   }
}
