package org.apache.parquet.crypto;

import org.apache.parquet.format.BlockCipher;

public class ModuleCipherFactory {
   public static final int SIZE_LENGTH = 4;

   public static BlockCipher.Encryptor getEncryptor(AesMode mode, byte[] keyBytes) {
      switch (mode) {
         case GCM:
            return new AesGcmEncryptor(keyBytes);
         case CTR:
            return new AesCtrEncryptor(keyBytes);
         default:
            throw new IllegalArgumentException("AesMode not supported in ModuleCipherFactory: " + mode);
      }
   }

   public static BlockCipher.Decryptor getDecryptor(AesMode mode, byte[] keyBytes) {
      switch (mode) {
         case GCM:
            return new AesGcmDecryptor(keyBytes);
         case CTR:
            return new AesCtrDecryptor(keyBytes);
         default:
            throw new IllegalArgumentException("AesMode not supported in ModuleCipherFactory: " + mode);
      }
   }

   public static enum ModuleType {
      Footer((byte)0),
      ColumnMetaData((byte)1),
      DataPage((byte)2),
      DictionaryPage((byte)3),
      DataPageHeader((byte)4),
      DictionaryPageHeader((byte)5),
      ColumnIndex((byte)6),
      OffsetIndex((byte)7),
      BloomFilterHeader((byte)8),
      BloomFilterBitset((byte)9);

      private final byte value;

      private ModuleType(byte value) {
         this.value = value;
      }

      public byte getValue() {
         return this.value;
      }
   }
}
