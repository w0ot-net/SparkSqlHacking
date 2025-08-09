package org.apache.orc;

import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

public enum EncryptionAlgorithm {
   AES_CTR_128("AES", "CTR/NoPadding", 16, 1),
   AES_CTR_256("AES", "CTR/NoPadding", 32, 2);

   private final String algorithm;
   private final String mode;
   private final int keyLength;
   private final int serialization;
   private final byte[] zero;

   private EncryptionAlgorithm(String algorithm, String mode, int keyLength, int serialization) {
      this.algorithm = algorithm;
      this.mode = mode;
      this.keyLength = keyLength;
      this.serialization = serialization;
      this.zero = new byte[keyLength];
   }

   public String getAlgorithm() {
      return this.algorithm;
   }

   public int getIvLength() {
      return 16;
   }

   public Cipher createCipher() {
      try {
         return Cipher.getInstance(this.algorithm + "/" + this.mode);
      } catch (NoSuchAlgorithmException var2) {
         throw new IllegalArgumentException("Bad algorithm " + this.algorithm);
      } catch (NoSuchPaddingException var3) {
         throw new IllegalArgumentException("Bad padding " + this.mode);
      }
   }

   public int keyLength() {
      return this.keyLength;
   }

   public byte[] getZeroKey() {
      return this.zero;
   }

   public int getSerialization() {
      return this.serialization;
   }

   public static EncryptionAlgorithm fromSerialization(int serialization) {
      for(EncryptionAlgorithm algorithm : values()) {
         if (algorithm.serialization == serialization) {
            return algorithm;
         }
      }

      throw new IllegalArgumentException("Unknown code in encryption algorithm " + serialization);
   }

   public String toString() {
      return this.algorithm + this.keyLength * 8;
   }

   // $FF: synthetic method
   private static EncryptionAlgorithm[] $values() {
      return new EncryptionAlgorithm[]{AES_CTR_128, AES_CTR_256};
   }
}
