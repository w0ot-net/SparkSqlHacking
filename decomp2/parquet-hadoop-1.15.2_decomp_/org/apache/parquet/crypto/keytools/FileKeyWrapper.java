package org.apache.parquet.crypto.keytools;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.concurrent.ConcurrentMap;
import org.apache.hadoop.conf.Configuration;
import org.apache.parquet.crypto.ParquetCryptoRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileKeyWrapper {
   private static final Logger LOG = LoggerFactory.getLogger(FileKeyWrapper.class);
   private static final int[] ACCEPTABLE_KEK_LENGTHS = new int[]{128, 192, 256};
   public static final int KEK_ID_LENGTH = 16;
   private final ConcurrentMap KEKPerMasterKeyID;
   private final long cacheEntryLifetime;
   private final KmsClient kmsClient;
   private final String kmsInstanceID;
   private final String kmsInstanceURL;
   private final FileKeyMaterialStore keyMaterialStore;
   private final Configuration hadoopConfiguration;
   private final SecureRandom random;
   private final boolean doubleWrapping;
   private final int kekLength;
   private short keyCounter;
   private String accessToken;

   FileKeyWrapper(Configuration configuration, FileKeyMaterialStore keyMaterialStore, KeyToolkit.KmsClientAndDetails kmsClientAndDetails) {
      this.hadoopConfiguration = configuration;
      this.keyMaterialStore = keyMaterialStore;
      this.random = new SecureRandom();
      this.keyCounter = 0;
      this.cacheEntryLifetime = 1000L * this.hadoopConfiguration.getLong("parquet.encryption.cache.lifetime.seconds", 600L);
      this.doubleWrapping = this.hadoopConfiguration.getBoolean("parquet.encryption.double.wrapping", true);
      this.accessToken = this.hadoopConfiguration.getTrimmed("parquet.encryption.key.access.token", "DEFAULT");
      KeyToolkit.KMS_CLIENT_CACHE_PER_TOKEN.checkCacheForExpiredTokens(this.cacheEntryLifetime);
      if (null == kmsClientAndDetails) {
         this.kmsInstanceID = this.hadoopConfiguration.getTrimmed("parquet.encryption.kms.instance.id", "DEFAULT");
         this.kmsInstanceURL = this.hadoopConfiguration.getTrimmed("parquet.encryption.kms.instance.url", "DEFAULT");
         this.kmsClient = KeyToolkit.getKmsClient(this.kmsInstanceID, this.kmsInstanceURL, configuration, this.accessToken, this.cacheEntryLifetime);
      } else {
         this.kmsInstanceID = kmsClientAndDetails.getKmsInstanceID();
         this.kmsInstanceURL = kmsClientAndDetails.getKmsInstanceURL();
         this.kmsClient = kmsClientAndDetails.getKmsClient();
      }

      if (this.doubleWrapping) {
         KeyToolkit.KEK_WRITE_CACHE_PER_TOKEN.checkCacheForExpiredTokens(this.cacheEntryLifetime);
         this.KEKPerMasterKeyID = KeyToolkit.KEK_WRITE_CACHE_PER_TOKEN.getOrCreateInternalCache(this.accessToken, this.cacheEntryLifetime);
         int kekLengthBits = configuration.getInt("parquet.encryption.kek.length.bits", 128);
         if (Arrays.binarySearch(ACCEPTABLE_KEK_LENGTHS, kekLengthBits) < 0) {
            throw new ParquetCryptoRuntimeException("Wrong key encryption key (KEK) length : " + kekLengthBits);
         }

         this.kekLength = kekLengthBits / 8;
      } else {
         this.KEKPerMasterKeyID = null;
         this.kekLength = 0;
      }

      if (LOG.isDebugEnabled()) {
         LOG.debug("Creating file key wrapper. KmsClient: {}; KmsInstanceId: {}; KmsInstanceURL: {}; doubleWrapping: {}; keyMaterialStore: {}; token snippet: {}", new Object[]{this.kmsClient, this.kmsInstanceID, this.kmsInstanceURL, this.doubleWrapping, keyMaterialStore, KeyToolkit.formatTokenForLog(this.accessToken)});
      }

   }

   FileKeyWrapper(Configuration configuration, FileKeyMaterialStore keyMaterialStore) {
      this(configuration, keyMaterialStore, (KeyToolkit.KmsClientAndDetails)null);
   }

   byte[] getEncryptionKeyMetadata(byte[] dataKey, String masterKeyID, boolean isFooterKey) {
      return this.getEncryptionKeyMetadata(dataKey, masterKeyID, isFooterKey, (String)null);
   }

   byte[] getEncryptionKeyMetadata(byte[] dataKey, String masterKeyID, boolean isFooterKey, String keyIdInFile) {
      if (null == this.kmsClient) {
         throw new ParquetCryptoRuntimeException("No KMS client available. See previous errors.");
      } else {
         String encodedKekID = null;
         String encodedWrappedKEK = null;
         String encodedWrappedDEK = null;
         if (!this.doubleWrapping) {
            encodedWrappedDEK = this.kmsClient.wrapKey(dataKey, masterKeyID);
         } else {
            KeyToolkit.KeyEncryptionKey keyEncryptionKey = (KeyToolkit.KeyEncryptionKey)this.KEKPerMasterKeyID.computeIfAbsent(masterKeyID, (k) -> this.createKeyEncryptionKey(masterKeyID));
            byte[] AAD = keyEncryptionKey.getID();
            encodedWrappedDEK = KeyToolkit.encryptKeyLocally(dataKey, keyEncryptionKey.getBytes(), AAD);
            encodedKekID = keyEncryptionKey.getEncodedID();
            encodedWrappedKEK = keyEncryptionKey.getEncodedWrappedKEK();
         }

         boolean storeKeyMaterialInternally = null == this.keyMaterialStore;
         String serializedKeyMaterial = KeyMaterial.createSerialized(isFooterKey, this.kmsInstanceID, this.kmsInstanceURL, masterKeyID, this.doubleWrapping, encodedKekID, encodedWrappedKEK, encodedWrappedDEK, storeKeyMaterialInternally);
         if (storeKeyMaterialInternally) {
            return serializedKeyMaterial.getBytes(StandardCharsets.UTF_8);
         } else {
            if (null == keyIdInFile) {
               if (isFooterKey) {
                  keyIdInFile = "footerKey";
               } else {
                  keyIdInFile = "columnKey" + this.keyCounter;
                  ++this.keyCounter;
               }
            }

            this.keyMaterialStore.addKeyMaterial(keyIdInFile, serializedKeyMaterial);
            String serializedKeyMetadata = KeyMetadata.createSerializedForExternalMaterial(keyIdInFile);
            return serializedKeyMetadata.getBytes(StandardCharsets.UTF_8);
         }
      }
   }

   private KeyToolkit.KeyEncryptionKey createKeyEncryptionKey(String masterKeyID) {
      byte[] kekBytes = new byte[this.kekLength];
      this.random.nextBytes(kekBytes);
      byte[] kekID = new byte[16];
      this.random.nextBytes(kekID);
      String encodedWrappedKEK = null;
      encodedWrappedKEK = this.kmsClient.wrapKey(kekBytes, masterKeyID);
      return new KeyToolkit.KeyEncryptionKey(kekBytes, kekID, encodedWrappedKEK);
   }
}
