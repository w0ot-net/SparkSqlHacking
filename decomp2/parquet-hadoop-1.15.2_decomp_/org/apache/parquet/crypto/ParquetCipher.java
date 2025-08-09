package org.apache.parquet.crypto;

import org.apache.parquet.format.AesGcmCtrV1;
import org.apache.parquet.format.AesGcmV1;
import org.apache.parquet.format.EncryptionAlgorithm;

public enum ParquetCipher {
   AES_GCM_V1 {
      public EncryptionAlgorithm getEncryptionAlgorithm() {
         return EncryptionAlgorithm.AES_GCM_V1(new AesGcmV1());
      }
   },
   AES_GCM_CTR_V1 {
      public EncryptionAlgorithm getEncryptionAlgorithm() {
         return EncryptionAlgorithm.AES_GCM_CTR_V1(new AesGcmCtrV1());
      }
   };

   private ParquetCipher() {
   }

   public abstract EncryptionAlgorithm getEncryptionAlgorithm();
}
