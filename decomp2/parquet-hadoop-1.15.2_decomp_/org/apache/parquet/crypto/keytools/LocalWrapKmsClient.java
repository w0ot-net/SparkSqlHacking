package org.apache.parquet.crypto.keytools;

import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.apache.hadoop.conf.Configuration;
import org.apache.parquet.crypto.KeyAccessDeniedException;
import org.apache.parquet.crypto.ParquetCryptoRuntimeException;
import shaded.parquet.com.fasterxml.jackson.core.type.TypeReference;
import shaded.parquet.com.fasterxml.jackson.databind.ObjectMapper;

public abstract class LocalWrapKmsClient implements KmsClient {
   public static final String LOCAL_WRAP_NO_KEY_VERSION = "NO_VERSION";
   protected String kmsInstanceID;
   protected String kmsInstanceURL;
   protected String kmsToken;
   protected Configuration hadoopConfiguration;
   private ConcurrentMap masterKeyCache;

   public void initialize(Configuration configuration, String kmsInstanceID, String kmsInstanceURL, String accessToken) {
      this.kmsInstanceID = kmsInstanceID;
      this.kmsInstanceURL = kmsInstanceURL;
      this.masterKeyCache = new ConcurrentHashMap();
      this.hadoopConfiguration = configuration;
      this.kmsToken = accessToken;
      this.initializeInternal();
   }

   public String wrapKey(byte[] key, String masterKeyIdentifier) throws KeyAccessDeniedException {
      byte[] masterKey = (byte[])this.masterKeyCache.computeIfAbsent(masterKeyIdentifier, (k) -> this.getKeyFromServer(masterKeyIdentifier));
      byte[] AAD = masterKeyIdentifier.getBytes(StandardCharsets.UTF_8);
      String encryptedEncodedKey = KeyToolkit.encryptKeyLocally(key, masterKey, AAD);
      return LocalWrapKmsClient.LocalKeyWrap.createSerialized(encryptedEncodedKey);
   }

   public byte[] unwrapKey(String wrappedKey, String masterKeyIdentifier) throws KeyAccessDeniedException {
      LocalKeyWrap keyWrap = LocalWrapKmsClient.LocalKeyWrap.parse(wrappedKey);
      String masterKeyVersion = keyWrap.getMasterKeyVersion();
      if (!"NO_VERSION".equals(masterKeyVersion)) {
         throw new ParquetCryptoRuntimeException("Master key versions are not supported for local wrapping: " + masterKeyVersion);
      } else {
         String encryptedEncodedKey = keyWrap.getEncryptedKey();
         byte[] masterKey = (byte[])this.masterKeyCache.computeIfAbsent(masterKeyIdentifier, (k) -> this.getKeyFromServer(masterKeyIdentifier));
         byte[] AAD = masterKeyIdentifier.getBytes(StandardCharsets.UTF_8);
         return KeyToolkit.decryptKeyLocally(encryptedEncodedKey, masterKey, AAD);
      }
   }

   private byte[] getKeyFromServer(String keyIdentifier) {
      this.kmsToken = this.hadoopConfiguration.getTrimmed("parquet.encryption.key.access.token");
      byte[] key = this.getMasterKeyFromServer(keyIdentifier);
      int keyLength = key.length;
      if (16 != keyLength && 24 != keyLength && 32 != keyLength) {
         throw new ParquetCryptoRuntimeException("Wrong length: " + keyLength + " of AES key: " + keyIdentifier);
      } else {
         return key;
      }
   }

   protected abstract byte[] getMasterKeyFromServer(String var1) throws KeyAccessDeniedException;

   protected abstract void initializeInternal() throws KeyAccessDeniedException;

   static class LocalKeyWrap {
      public static final String LOCAL_WRAP_KEY_VERSION_FIELD = "masterKeyVersion";
      public static final String LOCAL_WRAP_ENCRYPTED_KEY_FIELD = "encryptedKey";
      private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
      private String encryptedEncodedKey;
      private String masterKeyVersion;

      private LocalKeyWrap(String masterKeyVersion, String encryptedEncodedKey) {
         this.masterKeyVersion = masterKeyVersion;
         this.encryptedEncodedKey = encryptedEncodedKey;
      }

      private static String createSerialized(String encryptedEncodedKey) {
         Map<String, String> keyWrapMap = new HashMap(2);
         keyWrapMap.put("masterKeyVersion", "NO_VERSION");
         keyWrapMap.put("encryptedKey", encryptedEncodedKey);

         try {
            return OBJECT_MAPPER.writeValueAsString(keyWrapMap);
         } catch (IOException e) {
            throw new ParquetCryptoRuntimeException("Failed to serialize local key wrap map", e);
         }
      }

      private static LocalKeyWrap parse(String wrappedKey) {
         Map<String, String> keyWrapMap = null;

         try {
            keyWrapMap = (Map)OBJECT_MAPPER.readValue(new StringReader(wrappedKey), new TypeReference() {
            });
         } catch (IOException e) {
            throw new ParquetCryptoRuntimeException("Failed to parse local key wrap json " + wrappedKey, e);
         }

         String encryptedEncodedKey = (String)keyWrapMap.get("encryptedKey");
         String masterKeyVersion = (String)keyWrapMap.get("masterKeyVersion");
         return new LocalKeyWrap(masterKeyVersion, encryptedEncodedKey);
      }

      private String getMasterKeyVersion() {
         return this.masterKeyVersion;
      }

      private String getEncryptedKey() {
         return this.encryptedEncodedKey;
      }
   }
}
