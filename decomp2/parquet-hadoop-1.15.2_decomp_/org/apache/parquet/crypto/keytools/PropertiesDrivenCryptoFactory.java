package org.apache.parquet.crypto.keytools;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.parquet.crypto.ColumnEncryptionProperties;
import org.apache.parquet.crypto.DecryptionKeyRetriever;
import org.apache.parquet.crypto.DecryptionPropertiesFactory;
import org.apache.parquet.crypto.EncryptionPropertiesFactory;
import org.apache.parquet.crypto.FileDecryptionProperties;
import org.apache.parquet.crypto.FileEncryptionProperties;
import org.apache.parquet.crypto.ParquetCipher;
import org.apache.parquet.crypto.ParquetCryptoRuntimeException;
import org.apache.parquet.hadoop.api.WriteSupport;
import org.apache.parquet.hadoop.metadata.ColumnPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertiesDrivenCryptoFactory implements EncryptionPropertiesFactory, DecryptionPropertiesFactory {
   private static final Logger LOG = LoggerFactory.getLogger(PropertiesDrivenCryptoFactory.class);
   private static final int[] ACCEPTABLE_DATA_KEY_LENGTHS = new int[]{128, 192, 256};
   public static final String COLUMN_KEYS_PROPERTY_NAME = "parquet.encryption.column.keys";
   public static final String FOOTER_KEY_PROPERTY_NAME = "parquet.encryption.footer.key";
   public static final String COMPLETE_COLUMN_ENCRYPTION_PROPERTY_NAME = "parquet.encryption.complete.columns";
   public static final String UNIFORM_KEY_PROPERTY_NAME = "parquet.encryption.uniform.key";
   public static final String ENCRYPTION_ALGORITHM_PROPERTY_NAME = "parquet.encryption.algorithm";
   public static final String PLAINTEXT_FOOTER_PROPERTY_NAME = "parquet.encryption.plaintext.footer";
   public static final String ENCRYPTION_ALGORITHM_DEFAULT;
   public static final boolean PLAINTEXT_FOOTER_DEFAULT = false;
   public static final boolean COMPLETE_COLUMN_ENCRYPTION_DEFAULT = false;
   private static final SecureRandom RANDOM;

   public FileEncryptionProperties getFileEncryptionProperties(Configuration fileHadoopConfig, Path tempFilePath, WriteSupport.WriteContext fileWriteContext) throws ParquetCryptoRuntimeException {
      String footerKeyId = fileHadoopConfig.getTrimmed("parquet.encryption.footer.key");
      String columnKeysStr = fileHadoopConfig.getTrimmed("parquet.encryption.column.keys");
      String uniformKeyId = fileHadoopConfig.getTrimmed("parquet.encryption.uniform.key");
      boolean completeColumnEncryption = fileHadoopConfig.getBoolean("parquet.encryption.complete.columns", false);
      boolean emptyFooterKeyId = KeyToolkit.stringIsEmpty(footerKeyId);
      boolean emptyColumnKeyIds = KeyToolkit.stringIsEmpty(columnKeysStr);
      boolean emptyUniformKeyId = KeyToolkit.stringIsEmpty(uniformKeyId);
      if (emptyFooterKeyId && emptyColumnKeyIds && emptyUniformKeyId) {
         LOG.debug("Unencrypted file: {}", tempFilePath);
         return null;
      } else {
         if (emptyUniformKeyId) {
            if (emptyFooterKeyId) {
               throw new ParquetCryptoRuntimeException("No footer key configured in parquet.encryption.footer.key");
            }

            if (emptyColumnKeyIds) {
               throw new ParquetCryptoRuntimeException("No column keys configured in parquet.encryption.column.keys");
            }
         } else {
            if (!emptyFooterKeyId) {
               throw new ParquetCryptoRuntimeException("Uniform encryption. Cant have footer key configured in parquet.encryption.footer.key");
            }

            if (!emptyColumnKeyIds) {
               throw new ParquetCryptoRuntimeException("Uniform encryption. Cant have column keys configured in parquet.encryption.column.keys");
            }

            if (completeColumnEncryption) {
               throw new ParquetCryptoRuntimeException("Complete column encryption cant be applied in uniform encryption mode");
            }

            footerKeyId = uniformKeyId;
         }

         FileKeyMaterialStore keyMaterialStore = null;
         boolean keyMaterialInternalStorage = fileHadoopConfig.getBoolean("parquet.encryption.key.material.store.internally", true);
         if (!keyMaterialInternalStorage) {
            if (tempFilePath == null) {
               throw new ParquetCryptoRuntimeException("Output file path cannot be null");
            }

            try {
               keyMaterialStore = new HadoopFSKeyMaterialStore(tempFilePath.getFileSystem(fileHadoopConfig));
               keyMaterialStore.initialize(tempFilePath, fileHadoopConfig, false);
            } catch (IOException e) {
               throw new ParquetCryptoRuntimeException("Failed to get key material store", e);
            }
         }

         FileKeyWrapper keyWrapper = new FileKeyWrapper(fileHadoopConfig, keyMaterialStore);
         String algo = fileHadoopConfig.getTrimmed("parquet.encryption.algorithm", ENCRYPTION_ALGORITHM_DEFAULT);

         ParquetCipher cipher;
         try {
            cipher = ParquetCipher.valueOf(algo);
         } catch (IllegalArgumentException var23) {
            throw new ParquetCryptoRuntimeException("Wrong encryption algorithm: " + algo);
         }

         int dekLengthBits = fileHadoopConfig.getInt("parquet.encryption.data.key.length.bits", 128);
         if (Arrays.binarySearch(ACCEPTABLE_DATA_KEY_LENGTHS, dekLengthBits) < 0) {
            throw new ParquetCryptoRuntimeException("Wrong data key length : " + dekLengthBits);
         } else {
            int dekLength = dekLengthBits / 8;
            byte[] footerKeyBytes = new byte[dekLength];
            RANDOM.nextBytes(footerKeyBytes);
            byte[] footerKeyMetadata = keyWrapper.getEncryptionKeyMetadata(footerKeyBytes, footerKeyId, true);
            boolean plaintextFooter = fileHadoopConfig.getBoolean("parquet.encryption.plaintext.footer", false);
            FileEncryptionProperties.Builder propertiesBuilder = FileEncryptionProperties.builder(footerKeyBytes).withFooterKeyMetadata(footerKeyMetadata).withAlgorithm(cipher);
            if (emptyUniformKeyId) {
               Map<ColumnPath, ColumnEncryptionProperties> encryptedColumns = this.getColumnEncryptionProperties(dekLength, columnKeysStr, keyWrapper);
               propertiesBuilder = propertiesBuilder.withEncryptedColumns(encryptedColumns);
               if (completeColumnEncryption) {
                  propertiesBuilder = propertiesBuilder.withCompleteColumnEncryption();
               }
            }

            if (plaintextFooter) {
               propertiesBuilder = propertiesBuilder.withPlaintextFooter();
            }

            if (null != keyMaterialStore) {
               keyMaterialStore.saveMaterial();
            }

            if (LOG.isDebugEnabled()) {
               LOG.debug("File encryption properties for {} - algo: {}; footer key id: {}; uniform key id: {}; plaintext footer: {}; internal key material: {}; encrypted columns: {}", new Object[]{tempFilePath, cipher, footerKeyId, uniformKeyId, plaintextFooter, keyMaterialInternalStorage, columnKeysStr});
            }

            return propertiesBuilder.build();
         }
      }
   }

   private Map getColumnEncryptionProperties(int dekLength, String columnKeys, FileKeyWrapper keyWrapper) throws ParquetCryptoRuntimeException {
      Map<ColumnPath, ColumnEncryptionProperties> encryptedColumns = new HashMap();
      String[] keyToColumns = columnKeys.split(";");

      for(int i = 0; i < keyToColumns.length; ++i) {
         String curKeyToColumns = keyToColumns[i].trim();
         if (!curKeyToColumns.isEmpty()) {
            String[] parts = curKeyToColumns.split(":");
            if (parts.length != 2) {
               throw new ParquetCryptoRuntimeException("Incorrect key to columns mapping in parquet.encryption.column.keys: [" + curKeyToColumns + "]");
            }

            String columnKeyId = parts[0].trim();
            if (columnKeyId.isEmpty()) {
               throw new ParquetCryptoRuntimeException("Empty key name in parquet.encryption.column.keys");
            }

            String columnNamesStr = parts[1].trim();
            String[] columnNames = columnNamesStr.split(",");
            if (0 == columnNames.length) {
               throw new ParquetCryptoRuntimeException("No columns to encrypt defined for key: " + columnKeyId);
            }

            for(int j = 0; j < columnNames.length; ++j) {
               String columnName = columnNames[j].trim();
               if (columnName.isEmpty()) {
                  throw new ParquetCryptoRuntimeException("Empty column name in parquet.encryption.column.keys for key: " + columnKeyId);
               }

               ColumnPath columnPath = ColumnPath.fromDotString(columnName);
               if (encryptedColumns.containsKey(columnPath)) {
                  throw new ParquetCryptoRuntimeException("Multiple keys defined for the same column: " + columnName);
               }

               byte[] columnKeyBytes = new byte[dekLength];
               RANDOM.nextBytes(columnKeyBytes);
               byte[] columnKeyKeyMetadata = keyWrapper.getEncryptionKeyMetadata(columnKeyBytes, columnKeyId, false);
               ColumnEncryptionProperties cmd = ColumnEncryptionProperties.builder(columnPath).withKey(columnKeyBytes).withKeyMetaData(columnKeyKeyMetadata).build();
               encryptedColumns.put(columnPath, cmd);
            }
         }
      }

      if (encryptedColumns.isEmpty()) {
         throw new ParquetCryptoRuntimeException("No column keys configured in parquet.encryption.column.keys");
      } else {
         return encryptedColumns;
      }
   }

   public FileDecryptionProperties getFileDecryptionProperties(Configuration hadoopConfig, Path filePath) throws ParquetCryptoRuntimeException {
      DecryptionKeyRetriever keyRetriever = new FileKeyUnwrapper(hadoopConfig, filePath);
      if (LOG.isDebugEnabled()) {
         LOG.debug("File decryption properties for {}", filePath);
      }

      return FileDecryptionProperties.builder().withKeyRetriever(keyRetriever).withPlaintextFilesAllowed().build();
   }

   static {
      ENCRYPTION_ALGORITHM_DEFAULT = ParquetCipher.AES_GCM_V1.toString();
      RANDOM = new SecureRandom();
   }
}
