package org.apache.parquet.crypto;

import java.util.HashMap;
import java.util.Map;
import org.apache.parquet.format.BlockCipher;
import org.apache.parquet.format.EncryptionAlgorithm;
import org.apache.parquet.format.FileCryptoMetaData;
import org.apache.parquet.hadoop.metadata.ColumnPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InternalFileEncryptor {
   private static final Logger LOG = LoggerFactory.getLogger(InternalFileEncryptor.class);
   private final EncryptionAlgorithm algorithm;
   private final FileEncryptionProperties fileEncryptionProperties;
   private final byte[] footerKey;
   private final byte[] footerKeyMetadata;
   private final HashMap columnMap;
   private final byte[] fileAAD;
   private final boolean encryptFooter;
   private BlockCipher.Encryptor aesGcmEncryptorWithFooterKey;
   private BlockCipher.Encryptor aesCtrEncryptorWithFooterKey;
   private boolean fileCryptoMetaDataCreated;

   public InternalFileEncryptor(FileEncryptionProperties fileEncryptionProperties) {
      this.fileEncryptionProperties = fileEncryptionProperties;
      if (LOG.isDebugEnabled()) {
         this.fileEncryptorLog();
      }

      this.algorithm = fileEncryptionProperties.getAlgorithm();
      this.footerKey = fileEncryptionProperties.getFooterKey();
      this.encryptFooter = fileEncryptionProperties.encryptedFooter();
      this.footerKeyMetadata = fileEncryptionProperties.getFooterKeyMetadata();
      this.fileAAD = fileEncryptionProperties.getFileAAD();
      this.columnMap = new HashMap();
      this.fileCryptoMetaDataCreated = false;
   }

   private BlockCipher.Encryptor getThriftModuleEncryptor(byte[] columnKey) {
      if (null == columnKey) {
         if (null == this.aesGcmEncryptorWithFooterKey) {
            this.aesGcmEncryptorWithFooterKey = ModuleCipherFactory.getEncryptor(AesMode.GCM, this.footerKey);
         }

         return this.aesGcmEncryptorWithFooterKey;
      } else {
         return ModuleCipherFactory.getEncryptor(AesMode.GCM, columnKey);
      }
   }

   private BlockCipher.Encryptor getDataModuleEncryptor(byte[] columnKey) {
      if (this.algorithm.isSetAES_GCM_V1()) {
         return this.getThriftModuleEncryptor(columnKey);
      } else if (null == columnKey) {
         if (null == this.aesCtrEncryptorWithFooterKey) {
            this.aesCtrEncryptorWithFooterKey = ModuleCipherFactory.getEncryptor(AesMode.CTR, this.footerKey);
         }

         return this.aesCtrEncryptorWithFooterKey;
      } else {
         return ModuleCipherFactory.getEncryptor(AesMode.CTR, columnKey);
      }
   }

   public InternalColumnEncryptionSetup getColumnSetup(ColumnPath columnPath, boolean createIfNull, int ordinal) {
      InternalColumnEncryptionSetup internalColumnProperties = (InternalColumnEncryptionSetup)this.columnMap.get(columnPath);
      if (null != internalColumnProperties) {
         if (ordinal != internalColumnProperties.getOrdinal()) {
            throw new ParquetCryptoRuntimeException("Column ordinal doesnt match " + columnPath + ": " + ordinal + ", " + internalColumnProperties.getOrdinal());
         } else {
            return internalColumnProperties;
         }
      } else if (!createIfNull) {
         throw new ParquetCryptoRuntimeException("No encryption setup found for column " + columnPath);
      } else if (this.fileCryptoMetaDataCreated) {
         throw new ParquetCryptoRuntimeException("Re-use: No encryption setup for column " + columnPath);
      } else {
         ColumnEncryptionProperties columnProperties = this.fileEncryptionProperties.getColumnProperties(columnPath);
         if (null == columnProperties) {
            throw new ParquetCryptoRuntimeException("No encryption properties for column " + columnPath);
         } else {
            if (columnProperties.isEncrypted()) {
               if (columnProperties.isEncryptedWithFooterKey()) {
                  internalColumnProperties = new InternalColumnEncryptionSetup(columnProperties, ordinal, this.getDataModuleEncryptor((byte[])null), this.getThriftModuleEncryptor((byte[])null));
               } else {
                  internalColumnProperties = new InternalColumnEncryptionSetup(columnProperties, ordinal, this.getDataModuleEncryptor(columnProperties.getKeyBytes()), this.getThriftModuleEncryptor(columnProperties.getKeyBytes()));
               }
            } else {
               internalColumnProperties = new InternalColumnEncryptionSetup(columnProperties, ordinal, (BlockCipher.Encryptor)null, (BlockCipher.Encryptor)null);
            }

            this.columnMap.put(columnPath, internalColumnProperties);
            return internalColumnProperties;
         }
      }
   }

   public BlockCipher.Encryptor getFooterEncryptor() {
      return !this.encryptFooter ? null : this.getThriftModuleEncryptor((byte[])null);
   }

   public FileCryptoMetaData getFileCryptoMetaData() {
      if (!this.encryptFooter) {
         throw new ParquetCryptoRuntimeException("Requesting FileCryptoMetaData in file with unencrypted footer");
      } else {
         FileCryptoMetaData fileCryptoMetaData = new FileCryptoMetaData(this.algorithm);
         if (null != this.footerKeyMetadata) {
            fileCryptoMetaData.setKey_metadata(this.footerKeyMetadata);
         }

         this.fileCryptoMetaDataCreated = true;
         return fileCryptoMetaData;
      }
   }

   public boolean encryptColumnMetaData(InternalColumnEncryptionSetup columnSetup) {
      if (!columnSetup.isEncrypted()) {
         return false;
      } else if (!this.encryptFooter) {
         return true;
      } else {
         return !columnSetup.isEncryptedWithFooterKey();
      }
   }

   public boolean isFooterEncrypted() {
      return this.encryptFooter;
   }

   public EncryptionAlgorithm getEncryptionAlgorithm() {
      return this.algorithm;
   }

   public byte[] getFileAAD() {
      return this.fileAAD;
   }

   public byte[] getFooterSigningKeyMetaData() {
      if (this.encryptFooter) {
         throw new ParquetCryptoRuntimeException("Requesting signing footer key metadata in file with encrypted footer");
      } else {
         return this.footerKeyMetadata;
      }
   }

   public AesGcmEncryptor getSignedFooterEncryptor() {
      if (this.encryptFooter) {
         throw new ParquetCryptoRuntimeException("Requesting signed footer encryptor in file with encrypted footer");
      } else {
         return (AesGcmEncryptor)ModuleCipherFactory.getEncryptor(AesMode.GCM, this.footerKey);
      }
   }

   public FileEncryptionProperties getEncryptionProperties() {
      return this.fileEncryptionProperties;
   }

   private void fileEncryptorLog() {
      Map<ColumnPath, ColumnEncryptionProperties> columnPropertyMap = this.fileEncryptionProperties.getEncryptedColumns();
      String encryptedColumnList;
      if (null != columnPropertyMap) {
         encryptedColumnList = "";

         for(Map.Entry entry : columnPropertyMap.entrySet()) {
            encryptedColumnList = encryptedColumnList + entry.getKey() + "; ";
         }
      } else {
         encryptedColumnList = "Every column will be encrypted with footer key.";
      }

      LOG.debug("File Encryptor. Algo: {}. Encrypted footer: {}.  Encrypted columns: {}", new Object[]{this.fileEncryptionProperties.getAlgorithm(), this.fileEncryptionProperties.encryptedFooter(), encryptedColumnList});
   }
}
