package org.apache.parquet.crypto;

public enum AesMode {
   GCM("AES/GCM/NoPadding"),
   CTR("AES/CTR/NoPadding");

   private final String cipherName;

   private AesMode(String cipherName) {
      this.cipherName = cipherName;
   }

   public String getCipherName() {
      return this.cipherName;
   }
}
