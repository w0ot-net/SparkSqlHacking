package org.apache.parquet.crypto.keytools;

import java.io.IOException;
import java.util.Base64;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.parquet.crypto.AesGcmDecryptor;
import org.apache.parquet.crypto.AesGcmEncryptor;
import org.apache.parquet.crypto.AesMode;
import org.apache.parquet.crypto.KeyAccessDeniedException;
import org.apache.parquet.crypto.ModuleCipherFactory;
import org.apache.parquet.crypto.ParquetCryptoRuntimeException;
import org.apache.parquet.hadoop.BadConfigurationException;
import org.apache.parquet.hadoop.util.ConfigurationUtil;
import org.apache.parquet.hadoop.util.HiddenFileFilter;

public class KeyToolkit {
   public static final String KMS_CLIENT_CLASS_PROPERTY_NAME = "parquet.encryption.kms.client.class";
   public static final String KMS_INSTANCE_ID_PROPERTY_NAME = "parquet.encryption.kms.instance.id";
   public static final String KMS_INSTANCE_URL_PROPERTY_NAME = "parquet.encryption.kms.instance.url";
   public static final String KEY_ACCESS_TOKEN_PROPERTY_NAME = "parquet.encryption.key.access.token";
   public static final String DOUBLE_WRAPPING_PROPERTY_NAME = "parquet.encryption.double.wrapping";
   public static final String CACHE_LIFETIME_PROPERTY_NAME = "parquet.encryption.cache.lifetime.seconds";
   public static final String KEY_MATERIAL_INTERNAL_PROPERTY_NAME = "parquet.encryption.key.material.store.internally";
   public static final String DATA_KEY_LENGTH_PROPERTY_NAME = "parquet.encryption.data.key.length.bits";
   public static final String KEK_LENGTH_PROPERTY_NAME = "parquet.encryption.kek.length.bits";
   public static final boolean DOUBLE_WRAPPING_DEFAULT = true;
   public static final long CACHE_LIFETIME_DEFAULT_SECONDS = 600L;
   public static final boolean KEY_MATERIAL_INTERNAL_DEFAULT = true;
   public static final int DATA_KEY_LENGTH_DEFAULT = 128;
   public static final int KEK_LENGTH_DEFAULT = 128;
   private static long lastCacheCleanForKeyRotationTime = 0L;
   private static final Object lastCacheCleanForKeyRotationTimeLock = new Object();
   private static final int CACHE_CLEAN_PERIOD_FOR_KEY_ROTATION = 3600000;
   static final TwoLevelCacheWithExpiration KMS_CLIENT_CACHE_PER_TOKEN;
   static final TwoLevelCacheWithExpiration KEK_WRITE_CACHE_PER_TOKEN;
   static final TwoLevelCacheWithExpiration KEK_READ_CACHE_PER_TOKEN;

   public static void rotateMasterKeys(String folderPath, Configuration hadoopConfig) throws IOException, ParquetCryptoRuntimeException, KeyAccessDeniedException, UnsupportedOperationException {
      if (hadoopConfig.getBoolean("parquet.encryption.key.material.store.internally", false)) {
         throw new UnsupportedOperationException("Key rotation is not supported for internal key material");
      } else {
         long currentTime = System.currentTimeMillis();
         synchronized(lastCacheCleanForKeyRotationTimeLock) {
            if (currentTime - lastCacheCleanForKeyRotationTime > 3600000L) {
               KEK_WRITE_CACHE_PER_TOKEN.clear();
               lastCacheCleanForKeyRotationTime = currentTime;
            }
         }

         Path parentPath = new Path(folderPath);
         FileSystem hadoopFileSystem = parentPath.getFileSystem(hadoopConfig);
         if (hadoopFileSystem.exists(parentPath) && hadoopFileSystem.isDirectory(parentPath)) {
            FileStatus[] parquetFilesInFolder = hadoopFileSystem.listStatus(parentPath, HiddenFileFilter.INSTANCE);
            if (parquetFilesInFolder.length == 0) {
               throw new ParquetCryptoRuntimeException("Couldn't rotate keys - no parquet files in folder " + folderPath);
            } else {
               for(FileStatus fs : parquetFilesInFolder) {
                  Path parquetFile = fs.getPath();
                  FileKeyMaterialStore keyMaterialStore = new HadoopFSKeyMaterialStore(hadoopFileSystem);
                  keyMaterialStore.initialize(parquetFile, hadoopConfig, false);
                  FileKeyMaterialStore tempKeyMaterialStore = new HadoopFSKeyMaterialStore(hadoopFileSystem);
                  tempKeyMaterialStore.initialize(parquetFile, hadoopConfig, true);
                  Set<String> fileKeyIdSet = keyMaterialStore.getKeyIDSet();
                  FileKeyUnwrapper fileKeyUnwrapper = new FileKeyUnwrapper(hadoopConfig, parquetFile, keyMaterialStore);
                  String keyMaterialString = keyMaterialStore.getKeyMaterial("footerKey");
                  KeyWithMasterID key = fileKeyUnwrapper.getDEKandMasterID(KeyMaterial.parse(keyMaterialString));
                  KmsClientAndDetails kmsClientAndDetails = fileKeyUnwrapper.getKmsClientAndDetails();
                  FileKeyWrapper fileKeyWrapper = new FileKeyWrapper(hadoopConfig, tempKeyMaterialStore, kmsClientAndDetails);
                  fileKeyWrapper.getEncryptionKeyMetadata(key.getDataKey(), key.getMasterID(), true, "footerKey");
                  fileKeyIdSet.remove("footerKey");

                  for(String keyIdInFile : fileKeyIdSet) {
                     keyMaterialString = keyMaterialStore.getKeyMaterial(keyIdInFile);
                     key = fileKeyUnwrapper.getDEKandMasterID(KeyMaterial.parse(keyMaterialString));
                     fileKeyWrapper.getEncryptionKeyMetadata(key.getDataKey(), key.getMasterID(), false, keyIdInFile);
                  }

                  tempKeyMaterialStore.saveMaterial();
                  keyMaterialStore.removeMaterial();
                  tempKeyMaterialStore.moveMaterialTo(keyMaterialStore);
               }

            }
         } else {
            throw new ParquetCryptoRuntimeException("Couldn't rotate keys - folder doesn't exist or is not a directory: " + folderPath);
         }
      }
   }

   public static void removeCacheEntriesForToken(String accessToken) {
      KMS_CLIENT_CACHE_PER_TOKEN.removeCacheEntriesForToken(accessToken);
      KEK_WRITE_CACHE_PER_TOKEN.removeCacheEntriesForToken(accessToken);
      KEK_READ_CACHE_PER_TOKEN.removeCacheEntriesForToken(accessToken);
   }

   public static void removeCacheEntriesForAllTokens() {
      KMS_CLIENT_CACHE_PER_TOKEN.clear();
      KEK_WRITE_CACHE_PER_TOKEN.clear();
      KEK_READ_CACHE_PER_TOKEN.clear();
   }

   public static String encryptKeyLocally(byte[] keyBytes, byte[] masterKeyBytes, byte[] AAD) {
      AesGcmEncryptor keyEncryptor = (AesGcmEncryptor)ModuleCipherFactory.getEncryptor(AesMode.GCM, masterKeyBytes);
      byte[] encryptedKey = keyEncryptor.encrypt(false, keyBytes, AAD);
      return Base64.getEncoder().encodeToString(encryptedKey);
   }

   public static byte[] decryptKeyLocally(String encodedEncryptedKey, byte[] masterKeyBytes, byte[] AAD) {
      byte[] encryptedKey = Base64.getDecoder().decode(encodedEncryptedKey);
      AesGcmDecryptor keyDecryptor = (AesGcmDecryptor)ModuleCipherFactory.getDecryptor(AesMode.GCM, masterKeyBytes);
      return keyDecryptor.decrypt(encryptedKey, 0, encryptedKey.length, AAD);
   }

   static KmsClient getKmsClient(String kmsInstanceID, String kmsInstanceURL, Configuration configuration, String accessToken, long cacheEntryLifetime) {
      ConcurrentMap<String, KmsClient> kmsClientPerKmsInstanceCache = KMS_CLIENT_CACHE_PER_TOKEN.getOrCreateInternalCache(accessToken, cacheEntryLifetime);
      KmsClient kmsClient = (KmsClient)kmsClientPerKmsInstanceCache.computeIfAbsent(kmsInstanceID, (k) -> createAndInitKmsClient(configuration, kmsInstanceID, kmsInstanceURL, accessToken));
      return kmsClient;
   }

   private static KmsClient createAndInitKmsClient(Configuration configuration, String kmsInstanceID, String kmsInstanceURL, String accessToken) {
      Class<?> kmsClientClass = null;
      KmsClient kmsClient = null;

      try {
         kmsClientClass = ConfigurationUtil.getClassFromConfig(configuration, "parquet.encryption.kms.client.class", KmsClient.class);
         if (null == kmsClientClass) {
            throw new ParquetCryptoRuntimeException("Unspecified parquet.encryption.kms.client.class");
         }

         kmsClient = (KmsClient)kmsClientClass.newInstance();
      } catch (IllegalAccessException | BadConfigurationException | InstantiationException e) {
         throw new ParquetCryptoRuntimeException("Could not instantiate KmsClient class: " + kmsClientClass, e);
      }

      kmsClient.initialize(configuration, kmsInstanceID, kmsInstanceURL, accessToken);
      return kmsClient;
   }

   static String formatTokenForLog(String accessToken) {
      int maxTokenDisplayLength = 5;
      return accessToken.length() <= maxTokenDisplayLength ? accessToken : accessToken.substring(accessToken.length() - maxTokenDisplayLength);
   }

   static boolean stringIsEmpty(String str) {
      return null == str || str.isEmpty();
   }

   static {
      KMS_CLIENT_CACHE_PER_TOKEN = KeyToolkit.KmsClientCache.INSTANCE.getCache();
      KEK_WRITE_CACHE_PER_TOKEN = KeyToolkit.KEKWriteCache.INSTANCE.getCache();
      KEK_READ_CACHE_PER_TOKEN = KeyToolkit.KEKReadCache.INSTANCE.getCache();
   }

   private static enum KmsClientCache {
      INSTANCE;

      private final TwoLevelCacheWithExpiration cache = new TwoLevelCacheWithExpiration();

      private TwoLevelCacheWithExpiration getCache() {
         return this.cache;
      }
   }

   private static enum KEKWriteCache {
      INSTANCE;

      private final TwoLevelCacheWithExpiration cache = new TwoLevelCacheWithExpiration();

      private TwoLevelCacheWithExpiration getCache() {
         return this.cache;
      }
   }

   private static enum KEKReadCache {
      INSTANCE;

      private final TwoLevelCacheWithExpiration cache = new TwoLevelCacheWithExpiration();

      private TwoLevelCacheWithExpiration getCache() {
         return this.cache;
      }
   }

   static class KeyWithMasterID {
      private final byte[] keyBytes;
      private final String masterID;

      KeyWithMasterID(byte[] keyBytes, String masterID) {
         this.keyBytes = keyBytes;
         this.masterID = masterID;
      }

      byte[] getDataKey() {
         return this.keyBytes;
      }

      String getMasterID() {
         return this.masterID;
      }
   }

   static class KeyEncryptionKey {
      private final byte[] kekBytes;
      private final byte[] kekID;
      private String encodedKekID;
      private final String encodedWrappedKEK;

      KeyEncryptionKey(byte[] kekBytes, byte[] kekID, String encodedWrappedKEK) {
         this.kekBytes = kekBytes;
         this.kekID = kekID;
         this.encodedWrappedKEK = encodedWrappedKEK;
      }

      byte[] getBytes() {
         return this.kekBytes;
      }

      byte[] getID() {
         return this.kekID;
      }

      String getEncodedID() {
         if (null == this.encodedKekID) {
            this.encodedKekID = Base64.getEncoder().encodeToString(this.kekID);
         }

         return this.encodedKekID;
      }

      String getEncodedWrappedKEK() {
         return this.encodedWrappedKEK;
      }
   }

   static class KmsClientAndDetails {
      private KmsClient kmsClient;
      private String kmsInstanceID;
      private String kmsInstanceURL;

      public KmsClientAndDetails(KmsClient kmsClient, String kmsInstanceID, String kmsInstanceURL) {
         this.kmsClient = kmsClient;
         this.kmsInstanceID = kmsInstanceID;
         this.kmsInstanceURL = kmsInstanceURL;
      }

      public KmsClient getKmsClient() {
         return this.kmsClient;
      }

      public String getKmsInstanceID() {
         return this.kmsInstanceID;
      }

      public String getKmsInstanceURL() {
         return this.kmsInstanceURL;
      }
   }
}
