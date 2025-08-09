package org.apache.parquet.crypto;

import java.util.Arrays;
import java.util.HashMap;
import org.apache.parquet.format.BlockCipher;
import org.apache.parquet.format.EncryptionAlgorithm;
import org.apache.parquet.hadoop.metadata.ColumnPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InternalFileDecryptor {
   private static final Logger LOG = LoggerFactory.getLogger(InternalFileDecryptor.class);
   private final FileDecryptionProperties fileDecryptionProperties;
   private final DecryptionKeyRetriever keyRetriever;
   private final boolean checkPlaintextFooterIntegrity;
   private final byte[] aadPrefixInProperties;
   private final AADPrefixVerifier aadPrefixVerifier;
   private byte[] footerKey;
   private HashMap columnMap;
   private EncryptionAlgorithm algorithm;
   private byte[] fileAAD;
   private boolean encryptedFooter;
   private byte[] footerKeyMetaData;
   private boolean fileCryptoMetaDataProcessed = false;
   private BlockCipher.Decryptor aesGcmDecryptorWithFooterKey;
   private BlockCipher.Decryptor aesCtrDecryptorWithFooterKey;
   private boolean plaintextFile;

   public InternalFileDecryptor(FileDecryptionProperties fileDecryptionProperties) {
      this.fileDecryptionProperties = fileDecryptionProperties;
      this.checkPlaintextFooterIntegrity = fileDecryptionProperties.checkFooterIntegrity();
      this.footerKey = fileDecryptionProperties.getFooterKey();
      this.keyRetriever = fileDecryptionProperties.getKeyRetriever();
      this.aadPrefixInProperties = fileDecryptionProperties.getAADPrefix();
      this.columnMap = new HashMap();
      this.aadPrefixVerifier = fileDecryptionProperties.getAADPrefixVerifier();
      this.plaintextFile = false;
   }

   private BlockCipher.Decryptor getThriftModuleDecryptor(byte[] columnKey) {
      if (null == columnKey) {
         if (null == this.aesGcmDecryptorWithFooterKey) {
            this.aesGcmDecryptorWithFooterKey = ModuleCipherFactory.getDecryptor(AesMode.GCM, this.footerKey);
         }

         return this.aesGcmDecryptorWithFooterKey;
      } else {
         return ModuleCipherFactory.getDecryptor(AesMode.GCM, columnKey);
      }
   }

   private BlockCipher.Decryptor getDataModuleDecryptor(byte[] columnKey) {
      if (this.algorithm.isSetAES_GCM_V1()) {
         return this.getThriftModuleDecryptor(columnKey);
      } else if (null == columnKey) {
         if (null == this.aesCtrDecryptorWithFooterKey) {
            this.aesCtrDecryptorWithFooterKey = ModuleCipherFactory.getDecryptor(AesMode.CTR, this.footerKey);
         }

         return this.aesCtrDecryptorWithFooterKey;
      } else {
         return ModuleCipherFactory.getDecryptor(AesMode.CTR, columnKey);
      }
   }

   public InternalColumnDecryptionSetup getColumnSetup(ColumnPath path) {
      if (!this.fileCryptoMetaDataProcessed) {
         throw new ParquetCryptoRuntimeException("Haven't parsed the file crypto metadata yet");
      } else {
         InternalColumnDecryptionSetup columnDecryptionSetup = (InternalColumnDecryptionSetup)this.columnMap.get(path);
         if (null == columnDecryptionSetup) {
            throw new ParquetCryptoRuntimeException("Failed to find decryption setup for column " + path);
         } else {
            return columnDecryptionSetup;
         }
      }
   }

   public BlockCipher.Decryptor fetchFooterDecryptor() {
      if (!this.fileCryptoMetaDataProcessed) {
         throw new ParquetCryptoRuntimeException("Haven't parsed the file crypto metadata yet");
      } else {
         return this.getThriftModuleDecryptor((byte[])null);
      }
   }

   public void setFileCryptoMetaData(EncryptionAlgorithm algorithm, boolean encryptedFooter, byte[] footerKeyMetaData) {
      if (!this.fileCryptoMetaDataProcessed) {
         this.fileCryptoMetaDataProcessed = true;
         this.encryptedFooter = encryptedFooter;
         this.algorithm = algorithm;
         this.footerKeyMetaData = footerKeyMetaData;
         boolean fileHasAadPrefix = false;
         byte[] aadPrefixInFile = null;
         byte[] aadFileUnique;
         boolean mustSupplyAadPrefix;
         if (algorithm.isSetAES_GCM_V1()) {
            if (algorithm.getAES_GCM_V1().isSetAad_prefix()) {
               fileHasAadPrefix = true;
               aadPrefixInFile = algorithm.getAES_GCM_V1().getAad_prefix();
            }

            mustSupplyAadPrefix = algorithm.getAES_GCM_V1().isSupply_aad_prefix();
            aadFileUnique = algorithm.getAES_GCM_V1().getAad_file_unique();
         } else {
            if (!algorithm.isSetAES_GCM_CTR_V1()) {
               throw new ParquetCryptoRuntimeException("Unsupported algorithm: " + algorithm);
            }

            if (algorithm.getAES_GCM_CTR_V1().isSetAad_prefix()) {
               fileHasAadPrefix = true;
               aadPrefixInFile = algorithm.getAES_GCM_CTR_V1().getAad_prefix();
            }

            mustSupplyAadPrefix = algorithm.getAES_GCM_CTR_V1().isSupply_aad_prefix();
            aadFileUnique = algorithm.getAES_GCM_CTR_V1().getAad_file_unique();
         }

         byte[] aadPrefix = this.aadPrefixInProperties;
         if (mustSupplyAadPrefix && null == this.aadPrefixInProperties) {
            throw new ParquetCryptoRuntimeException("AAD prefix used for file encryption, but not stored in file and not supplied in decryption properties");
         }

         if (fileHasAadPrefix) {
            if (null != this.aadPrefixInProperties && !Arrays.equals(this.aadPrefixInProperties, aadPrefixInFile)) {
               throw new ParquetCryptoRuntimeException("AAD Prefix in file and in decryption properties is not the same");
            }

            if (null != this.aadPrefixVerifier) {
               this.aadPrefixVerifier.verify(aadPrefixInFile);
            }

            aadPrefix = aadPrefixInFile;
         } else {
            if (!mustSupplyAadPrefix && null != this.aadPrefixInProperties) {
               throw new ParquetCryptoRuntimeException("AAD Prefix set in decryption properties, but was not used for file encryption");
            }

            if (null != this.aadPrefixVerifier) {
               throw new ParquetCryptoRuntimeException("AAD Prefix Verifier is set, but AAD Prefix not found in file");
            }
         }

         if (null == aadPrefix) {
            this.fileAAD = aadFileUnique;
         } else {
            this.fileAAD = AesCipher.concatByteArrays(aadPrefix, aadFileUnique);
         }

         if (null == this.footerKey && (encryptedFooter || this.checkPlaintextFooterIntegrity)) {
            if (null == footerKeyMetaData) {
               throw new ParquetCryptoRuntimeException("No footer key or key metadata");
            }

            if (null == this.keyRetriever) {
               throw new ParquetCryptoRuntimeException("No footer key or key retriever");
            }

            try {
               this.footerKey = this.keyRetriever.getKey(footerKeyMetaData);
            } catch (KeyAccessDeniedException e) {
               throw new KeyAccessDeniedException("Footer key: access denied", e);
            }

            if (null == this.footerKey) {
               throw new ParquetCryptoRuntimeException("Footer key unavailable");
            }
         }
      } else {
         if (!this.algorithm.equals(algorithm)) {
            throw new ParquetCryptoRuntimeException("Decryptor re-use: Different algorithm");
         }

         if (encryptedFooter != this.encryptedFooter) {
            throw new ParquetCryptoRuntimeException("Decryptor re-use: Different footer encryption");
         }

         if (!Arrays.equals(this.footerKeyMetaData, footerKeyMetaData)) {
            throw new ParquetCryptoRuntimeException("Decryptor re-use: Different footer key metadata");
         }
      }

      if (LOG.isDebugEnabled()) {
         LOG.debug("File Decryptor. Algo: {}. Encrypted footer: {}", algorithm, encryptedFooter);
      }

   }

   public InternalColumnDecryptionSetup setColumnCryptoMetadata(ColumnPath path, boolean encrypted, boolean encryptedWithFooterKey, byte[] keyMetadata, int columnOrdinal) {
      if (!this.fileCryptoMetaDataProcessed) {
         throw new ParquetCryptoRuntimeException("Haven't parsed the file crypto metadata yet");
      } else {
         InternalColumnDecryptionSetup columnDecryptionSetup = (InternalColumnDecryptionSetup)this.columnMap.get(path);
         if (null != columnDecryptionSetup) {
            if (columnDecryptionSetup.isEncrypted() != encrypted) {
               throw new ParquetCryptoRuntimeException("Re-use: wrong encrypted flag. Column: " + path);
            } else {
               if (encrypted) {
                  if (encryptedWithFooterKey != columnDecryptionSetup.isEncryptedWithFooterKey()) {
                     throw new ParquetCryptoRuntimeException("Re-use: wrong encryption key (column vs footer). Column: " + path);
                  }

                  if (!encryptedWithFooterKey && !Arrays.equals(columnDecryptionSetup.getKeyMetadata(), keyMetadata)) {
                     throw new ParquetCryptoRuntimeException("Decryptor re-use: Different footer key metadata ");
                  }
               }

               return columnDecryptionSetup;
            }
         } else {
            if (!encrypted) {
               columnDecryptionSetup = new InternalColumnDecryptionSetup(path, false, false, (BlockCipher.Decryptor)null, (BlockCipher.Decryptor)null, columnOrdinal, (byte[])null);
            } else if (encryptedWithFooterKey) {
               if (null == this.footerKey) {
                  throw new ParquetCryptoRuntimeException("Column " + path + " is encrypted with NULL footer key");
               }

               columnDecryptionSetup = new InternalColumnDecryptionSetup(path, true, true, this.getDataModuleDecryptor((byte[])null), this.getThriftModuleDecryptor((byte[])null), columnOrdinal, (byte[])null);
               if (LOG.isDebugEnabled()) {
                  LOG.debug("Column decryption (footer key): {}", path);
               }
            } else {
               byte[] columnKeyBytes = this.fileDecryptionProperties.getColumnKey(path);
               if (null == columnKeyBytes && null != keyMetadata && null != this.keyRetriever) {
                  try {
                     columnKeyBytes = this.keyRetriever.getKey(keyMetadata);
                  } catch (KeyAccessDeniedException e) {
                     throw new KeyAccessDeniedException("Column " + path + ": key access denied", e);
                  }
               }

               if (null == columnKeyBytes) {
                  throw new ParquetCryptoRuntimeException("Column " + path + "is encrypted with NULL column key");
               }

               columnDecryptionSetup = new InternalColumnDecryptionSetup(path, true, false, this.getDataModuleDecryptor(columnKeyBytes), this.getThriftModuleDecryptor(columnKeyBytes), columnOrdinal, keyMetadata);
               if (LOG.isDebugEnabled()) {
                  LOG.debug("Column decryption (column key): {}", path);
               }
            }

            this.columnMap.put(path, columnDecryptionSetup);
            return columnDecryptionSetup;
         }
      }
   }

   public byte[] getFileAAD() {
      return this.fileAAD;
   }

   public AesGcmEncryptor createSignedFooterEncryptor() {
      if (!this.fileCryptoMetaDataProcessed) {
         throw new ParquetCryptoRuntimeException("Haven't parsed the file crypto metadata yet");
      } else if (this.encryptedFooter) {
         throw new ParquetCryptoRuntimeException("Requesting signed footer encryptor in file with encrypted footer");
      } else {
         return (AesGcmEncryptor)ModuleCipherFactory.getEncryptor(AesMode.GCM, this.footerKey);
      }
   }

   public boolean checkFooterIntegrity() {
      return this.checkPlaintextFooterIntegrity;
   }

   public boolean plaintextFilesAllowed() {
      return this.fileDecryptionProperties.plaintextFilesAllowed();
   }

   public void setPlaintextFile() {
      this.plaintextFile = true;
   }

   public boolean plaintextFile() {
      return this.plaintextFile;
   }

   public FileDecryptionProperties getDecryptionProperties() {
      return this.fileDecryptionProperties;
   }
}
