package org.apache.parquet.crypto;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import org.apache.parquet.format.EncryptionAlgorithm;
import org.apache.parquet.hadoop.metadata.ColumnPath;

public class FileEncryptionProperties {
   private static final ParquetCipher ALGORITHM_DEFAULT;
   private static final boolean ENCRYPTED_FOOTER_DEFAULT = true;
   private static final boolean COMPLETE_COLUMN_ENCRYPTION_DEFAULT = false;
   private final EncryptionAlgorithm algorithm;
   private final boolean encryptedFooter;
   private final byte[] footerKey;
   private final byte[] footerKeyMetadata;
   private final byte[] fileAAD;
   private final Map columnPropertyMap;
   private final boolean completeColumnEncryption;

   private FileEncryptionProperties(ParquetCipher cipher, byte[] footerKey, byte[] footerKeyMetadata, boolean encryptedFooter, byte[] aadPrefix, boolean storeAadPrefixInFile, Map columnPropertyMap, boolean completeColumnEncryption) {
      if (null == footerKey) {
         throw new IllegalArgumentException("Footer key is null");
      } else if (footerKey.length != 16 && footerKey.length != 24 && footerKey.length != 32) {
         throw new IllegalArgumentException("Wrong footer key length " + footerKey.length);
      } else {
         if (null != columnPropertyMap) {
            if (columnPropertyMap.isEmpty()) {
               throw new IllegalArgumentException("No encrypted columns");
            }
         } else if (completeColumnEncryption) {
            throw new IllegalArgumentException("Encrypted columns are not specified, cannot complete");
         }

         SecureRandom random = new SecureRandom();
         byte[] aadFileUnique = new byte[8];
         random.nextBytes(aadFileUnique);
         boolean supplyAadPrefix = false;
         if (null == aadPrefix) {
            this.fileAAD = aadFileUnique;
         } else {
            this.fileAAD = AesCipher.concatByteArrays(aadPrefix, aadFileUnique);
            if (!storeAadPrefixInFile) {
               supplyAadPrefix = true;
            }
         }

         this.algorithm = cipher.getEncryptionAlgorithm();
         if (this.algorithm.isSetAES_GCM_V1()) {
            this.algorithm.getAES_GCM_V1().setAad_file_unique(aadFileUnique);
            this.algorithm.getAES_GCM_V1().setSupply_aad_prefix(supplyAadPrefix);
            if (null != aadPrefix && storeAadPrefixInFile) {
               this.algorithm.getAES_GCM_V1().setAad_prefix(aadPrefix);
            }
         } else {
            this.algorithm.getAES_GCM_CTR_V1().setAad_file_unique(aadFileUnique);
            this.algorithm.getAES_GCM_CTR_V1().setSupply_aad_prefix(supplyAadPrefix);
            if (null != aadPrefix && storeAadPrefixInFile) {
               this.algorithm.getAES_GCM_CTR_V1().setAad_prefix(aadPrefix);
            }
         }

         this.footerKey = footerKey;
         this.footerKeyMetadata = footerKeyMetadata;
         this.encryptedFooter = encryptedFooter;
         this.columnPropertyMap = columnPropertyMap;
         this.completeColumnEncryption = completeColumnEncryption;
      }
   }

   public static Builder builder(byte[] footerKey) {
      return new Builder(footerKey);
   }

   public EncryptionAlgorithm getAlgorithm() {
      return this.algorithm;
   }

   public byte[] getFooterKey() {
      return this.footerKey;
   }

   public byte[] getFooterKeyMetadata() {
      return this.footerKeyMetadata;
   }

   public Map getEncryptedColumns() {
      return this.columnPropertyMap;
   }

   public ColumnEncryptionProperties getColumnProperties(ColumnPath columnPath) {
      if (null == this.columnPropertyMap) {
         return ColumnEncryptionProperties.builder(columnPath, true).build();
      } else {
         ColumnEncryptionProperties columnProperties = (ColumnEncryptionProperties)this.columnPropertyMap.get(columnPath);
         if (null != columnProperties) {
            return columnProperties;
         } else {
            return this.completeColumnEncryption ? ColumnEncryptionProperties.builder(columnPath, true).build() : ColumnEncryptionProperties.builder(columnPath, false).build();
         }
      }
   }

   public byte[] getFileAAD() {
      return this.fileAAD;
   }

   public boolean encryptedFooter() {
      return this.encryptedFooter;
   }

   static {
      ALGORITHM_DEFAULT = ParquetCipher.AES_GCM_V1;
   }

   public static class Builder {
      private byte[] footerKeyBytes;
      private boolean encryptedFooter;
      private ParquetCipher parquetCipher;
      private byte[] footerKeyMetadata;
      private byte[] aadPrefix;
      private Map columnPropertyMap;
      private boolean storeAadPrefixInFile;
      private boolean completeColumnEncryption;

      private Builder(byte[] footerKey) {
         this.parquetCipher = FileEncryptionProperties.ALGORITHM_DEFAULT;
         this.encryptedFooter = true;
         this.completeColumnEncryption = false;
         this.footerKeyBytes = new byte[footerKey.length];
         System.arraycopy(footerKey, 0, this.footerKeyBytes, 0, footerKey.length);
      }

      public Builder withPlaintextFooter() {
         this.encryptedFooter = false;
         return this;
      }

      public Builder withAlgorithm(ParquetCipher parquetCipher) {
         this.parquetCipher = parquetCipher;
         return this;
      }

      public Builder withFooterKeyID(String keyID) {
         return null == keyID ? this : this.withFooterKeyMetadata(keyID.getBytes(StandardCharsets.UTF_8));
      }

      public Builder withFooterKeyMetadata(byte[] footerKeyMetadata) {
         if (null == footerKeyMetadata) {
            return this;
         } else if (null != this.footerKeyMetadata) {
            throw new IllegalStateException("Footer key metadata already set");
         } else {
            this.footerKeyMetadata = footerKeyMetadata;
            return this;
         }
      }

      public Builder withAADPrefix(byte[] aadPrefixBytes) {
         if (null == aadPrefixBytes) {
            return this;
         } else if (null != this.aadPrefix) {
            throw new IllegalStateException("AAD Prefix already set");
         } else {
            this.aadPrefix = aadPrefixBytes;
            this.storeAadPrefixInFile = true;
            return this;
         }
      }

      public Builder withoutAADPrefixStorage() {
         if (null == this.aadPrefix) {
            throw new IllegalStateException("AAD Prefix not yet set");
         } else {
            this.storeAadPrefixInFile = false;
            return this;
         }
      }

      public Builder withEncryptedColumns(Map encryptedColumns) {
         if (null == encryptedColumns) {
            return this;
         } else if (null != this.columnPropertyMap) {
            throw new IllegalStateException("Column properties already set");
         } else {
            this.columnPropertyMap = new HashMap(encryptedColumns);
            return this;
         }
      }

      public Builder withCompleteColumnEncryption() {
         this.completeColumnEncryption = true;
         return this;
      }

      public FileEncryptionProperties build() {
         return new FileEncryptionProperties(this.parquetCipher, this.footerKeyBytes, this.footerKeyMetadata, this.encryptedFooter, this.aadPrefix, this.storeAadPrefixInFile, this.columnPropertyMap, this.completeColumnEncryption);
      }
   }
}
