package org.apache.parquet.crypto.keytools;

import java.io.IOException;
import java.util.Base64;
import java.util.concurrent.ConcurrentMap;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.parquet.crypto.DecryptionKeyRetriever;
import org.apache.parquet.crypto.ParquetCryptoRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileKeyUnwrapper implements DecryptionKeyRetriever {
   private static final Logger LOG = LoggerFactory.getLogger(FileKeyUnwrapper.class);
   private final ConcurrentMap kekPerKekID;
   private KeyToolkit.KmsClientAndDetails kmsClientAndDetails;
   private FileKeyMaterialStore keyMaterialStore;
   private boolean checkedKeyMaterialInternalStorage;
   private final Configuration hadoopConfiguration;
   private final Path parquetFilePath;
   private final String accessToken;
   private final long cacheEntryLifetime;

   FileKeyUnwrapper(Configuration hadoopConfiguration, Path filePath) {
      this.kmsClientAndDetails = null;
      this.keyMaterialStore = null;
      this.checkedKeyMaterialInternalStorage = false;
      this.hadoopConfiguration = hadoopConfiguration;
      this.parquetFilePath = filePath;
      this.cacheEntryLifetime = 1000L * hadoopConfiguration.getLong("parquet.encryption.cache.lifetime.seconds", 600L);
      this.accessToken = hadoopConfiguration.getTrimmed("parquet.encryption.key.access.token", "DEFAULT");
      KeyToolkit.KMS_CLIENT_CACHE_PER_TOKEN.checkCacheForExpiredTokens(this.cacheEntryLifetime);
      KeyToolkit.KEK_READ_CACHE_PER_TOKEN.checkCacheForExpiredTokens(this.cacheEntryLifetime);
      this.kekPerKekID = KeyToolkit.KEK_READ_CACHE_PER_TOKEN.getOrCreateInternalCache(this.accessToken, this.cacheEntryLifetime);
      if (LOG.isDebugEnabled()) {
         LOG.debug("Creating file key unwrapper. KeyMaterialStore: {}; token snippet: {}", this.keyMaterialStore, KeyToolkit.formatTokenForLog(this.accessToken));
      }

   }

   FileKeyUnwrapper(Configuration hadoopConfiguration, Path filePath, FileKeyMaterialStore keyMaterialStore) {
      this(hadoopConfiguration, filePath);
      this.keyMaterialStore = keyMaterialStore;
      this.checkedKeyMaterialInternalStorage = true;
   }

   public byte[] getKey(byte[] keyMetadataBytes) {
      KeyMetadata keyMetadata = KeyMetadata.parse(keyMetadataBytes);
      if (!this.checkedKeyMaterialInternalStorage) {
         if (!keyMetadata.keyMaterialStoredInternally()) {
            try {
               this.keyMaterialStore = new HadoopFSKeyMaterialStore(this.parquetFilePath.getFileSystem(this.hadoopConfiguration));
               this.keyMaterialStore.initialize(this.parquetFilePath, this.hadoopConfiguration, false);
            } catch (IOException e) {
               throw new ParquetCryptoRuntimeException("Failed to open key material store", e);
            }
         }

         this.checkedKeyMaterialInternalStorage = true;
      }

      KeyMaterial keyMaterial;
      if (keyMetadata.keyMaterialStoredInternally()) {
         keyMaterial = keyMetadata.getKeyMaterial();
      } else {
         String keyIDinFile = keyMetadata.getKeyReference();
         String keyMaterialString = this.keyMaterialStore.getKeyMaterial(keyIDinFile);
         if (null == keyMaterialString) {
            throw new ParquetCryptoRuntimeException("Null key material for keyIDinFile: " + keyIDinFile);
         }

         keyMaterial = KeyMaterial.parse(keyMaterialString);
      }

      return this.getDEKandMasterID(keyMaterial).getDataKey();
   }

   KeyToolkit.KeyWithMasterID getDEKandMasterID(KeyMaterial keyMaterial) {
      if (null == this.kmsClientAndDetails) {
         this.kmsClientAndDetails = this.getKmsClientFromConfigOrKeyMaterial(keyMaterial);
      }

      boolean doubleWrapping = keyMaterial.isDoubleWrapped();
      String masterKeyID = keyMaterial.getMasterKeyID();
      String encodedWrappedDEK = keyMaterial.getWrappedDEK();
      KmsClient kmsClient = this.kmsClientAndDetails.getKmsClient();
      byte[] dataKey;
      if (!doubleWrapping) {
         dataKey = kmsClient.unwrapKey(encodedWrappedDEK, masterKeyID);
      } else {
         String encodedKekID = keyMaterial.getKekID();
         String encodedWrappedKEK = keyMaterial.getWrappedKEK();
         byte[] kekBytes = (byte[])this.kekPerKekID.computeIfAbsent(encodedKekID, (k) -> kmsClient.unwrapKey(encodedWrappedKEK, masterKeyID));
         if (null == kekBytes) {
            throw new ParquetCryptoRuntimeException("Null KEK, after unwrapping in KMS with master key " + masterKeyID);
         }

         byte[] AAD = Base64.getDecoder().decode(encodedKekID);
         dataKey = KeyToolkit.decryptKeyLocally(encodedWrappedDEK, kekBytes, AAD);
      }

      return new KeyToolkit.KeyWithMasterID(dataKey, masterKeyID);
   }

   KeyToolkit.KmsClientAndDetails getKmsClientFromConfigOrKeyMaterial(KeyMaterial keyMaterial) {
      String kmsInstanceID = this.hadoopConfiguration.getTrimmed("parquet.encryption.kms.instance.id");
      if (KeyToolkit.stringIsEmpty(kmsInstanceID)) {
         kmsInstanceID = keyMaterial.getKmsInstanceID();
         if (null == kmsInstanceID) {
            throw new ParquetCryptoRuntimeException("KMS instance ID is missing both in properties and file key material");
         }
      }

      String kmsInstanceURL = this.hadoopConfiguration.getTrimmed("parquet.encryption.kms.instance.url");
      if (KeyToolkit.stringIsEmpty(kmsInstanceURL)) {
         kmsInstanceURL = keyMaterial.getKmsInstanceURL();
         if (null == kmsInstanceURL) {
            throw new ParquetCryptoRuntimeException("KMS instance URL is missing both in properties and file key material");
         }
      }

      KmsClient kmsClient = KeyToolkit.getKmsClient(kmsInstanceID, kmsInstanceURL, this.hadoopConfiguration, this.accessToken, this.cacheEntryLifetime);
      if (null == kmsClient) {
         throw new ParquetCryptoRuntimeException("KMSClient was not successfully created for reading encrypted data.");
      } else {
         if (LOG.isDebugEnabled()) {
            LOG.debug("File unwrapper - KmsClient: {}; InstanceId: {}; InstanceURL: {}", new Object[]{kmsClient, kmsInstanceID, kmsInstanceURL});
         }

         return new KeyToolkit.KmsClientAndDetails(kmsClient, kmsInstanceID, kmsInstanceURL);
      }
   }

   KeyToolkit.KmsClientAndDetails getKmsClientAndDetails() {
      return this.kmsClientAndDetails;
   }
}
