package org.apache.parquet.crypto.keytools;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import org.apache.parquet.crypto.ParquetCryptoRuntimeException;
import shaded.parquet.com.fasterxml.jackson.core.type.TypeReference;
import shaded.parquet.com.fasterxml.jackson.databind.ObjectMapper;

public class KeyMaterial {
   static final String KEY_MATERIAL_TYPE_FIELD = "keyMaterialType";
   static final String KEY_MATERIAL_TYPE1 = "PKMT1";
   static final String FOOTER_KEY_ID_IN_FILE = "footerKey";
   static final String COLUMN_KEY_ID_IN_FILE_PREFIX = "columnKey";
   private static final String IS_FOOTER_KEY_FIELD = "isFooterKey";
   private static final String DOUBLE_WRAPPING_FIELD = "doubleWrapping";
   private static final String KMS_INSTANCE_ID_FIELD = "kmsInstanceID";
   private static final String KMS_INSTANCE_URL_FIELD = "kmsInstanceURL";
   private static final String MASTER_KEY_ID_FIELD = "masterKeyID";
   private static final String WRAPPED_DEK_FIELD = "wrappedDEK";
   private static final String KEK_ID_FIELD = "keyEncryptionKeyID";
   private static final String WRAPPED_KEK_FIELD = "wrappedKEK";
   private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
   private final boolean isFooterKey;
   private final String kmsInstanceID;
   private final String kmsInstanceURL;
   private final String masterKeyID;
   private final boolean isDoubleWrapped;
   private final String kekID;
   private final String encodedWrappedKEK;
   private final String encodedWrappedDEK;

   private KeyMaterial(boolean isFooterKey, String kmsInstanceID, String kmsInstanceURL, String masterKeyID, boolean isDoubleWrapped, String kekID, String encodedWrappedKEK, String encodedWrappedDEK) {
      this.isFooterKey = isFooterKey;
      this.kmsInstanceID = kmsInstanceID;
      this.kmsInstanceURL = kmsInstanceURL;
      this.masterKeyID = masterKeyID;
      this.isDoubleWrapped = isDoubleWrapped;
      this.kekID = kekID;
      this.encodedWrappedKEK = encodedWrappedKEK;
      this.encodedWrappedDEK = encodedWrappedDEK;
   }

   static KeyMaterial parse(String keyMaterialString) {
      Map<String, Object> keyMaterialJson = null;

      try {
         keyMaterialJson = (Map)OBJECT_MAPPER.readValue(new StringReader(keyMaterialString), new TypeReference() {
         });
      } catch (IOException e) {
         throw new ParquetCryptoRuntimeException("Failed to parse key metadata " + keyMaterialString, e);
      }

      String keyMaterialType = (String)keyMaterialJson.get("keyMaterialType");
      if (!"PKMT1".equals(keyMaterialType)) {
         throw new ParquetCryptoRuntimeException("Wrong key material type: " + keyMaterialType + " vs " + "PKMT1");
      } else {
         return parse(keyMaterialJson);
      }
   }

   static KeyMaterial parse(Map keyMaterialJson) {
      Boolean isFooterKey = (Boolean)keyMaterialJson.get("isFooterKey");
      String kmsInstanceID = null;
      String kmsInstanceURL = null;
      if (isFooterKey) {
         kmsInstanceID = (String)keyMaterialJson.get("kmsInstanceID");
         kmsInstanceURL = (String)keyMaterialJson.get("kmsInstanceURL");
      }

      String masterKeyID = (String)keyMaterialJson.get("masterKeyID");
      String encodedWrappedDEK = (String)keyMaterialJson.get("wrappedDEK");
      String kekID = null;
      String encodedWrappedKEK = null;
      Boolean isDoubleWrapped = (Boolean)keyMaterialJson.get("doubleWrapping");
      if (isDoubleWrapped) {
         kekID = (String)keyMaterialJson.get("keyEncryptionKeyID");
         encodedWrappedKEK = (String)keyMaterialJson.get("wrappedKEK");
      }

      return new KeyMaterial(isFooterKey, kmsInstanceID, kmsInstanceURL, masterKeyID, isDoubleWrapped, kekID, encodedWrappedKEK, encodedWrappedDEK);
   }

   static String createSerialized(boolean isFooterKey, String kmsInstanceID, String kmsInstanceURL, String masterKeyID, boolean isDoubleWrapped, String kekID, String encodedWrappedKEK, String encodedWrappedDEK, boolean isInternalStorage) {
      Map<String, Object> keyMaterialMap = new HashMap(10);
      keyMaterialMap.put("keyMaterialType", "PKMT1");
      if (isInternalStorage) {
         keyMaterialMap.put("internalStorage", Boolean.TRUE);
      }

      keyMaterialMap.put("isFooterKey", isFooterKey);
      if (isFooterKey) {
         keyMaterialMap.put("kmsInstanceID", kmsInstanceID);
         keyMaterialMap.put("kmsInstanceURL", kmsInstanceURL);
      }

      keyMaterialMap.put("masterKeyID", masterKeyID);
      keyMaterialMap.put("wrappedDEK", encodedWrappedDEK);
      keyMaterialMap.put("doubleWrapping", isDoubleWrapped);
      if (isDoubleWrapped) {
         keyMaterialMap.put("keyEncryptionKeyID", kekID);
         keyMaterialMap.put("wrappedKEK", encodedWrappedKEK);
      }

      try {
         return OBJECT_MAPPER.writeValueAsString(keyMaterialMap);
      } catch (IOException e) {
         throw new ParquetCryptoRuntimeException("Failed to serialize key material", e);
      }
   }

   boolean isFooterKey() {
      return this.isFooterKey;
   }

   boolean isDoubleWrapped() {
      return this.isDoubleWrapped;
   }

   String getMasterKeyID() {
      return this.masterKeyID;
   }

   String getWrappedDEK() {
      return this.encodedWrappedDEK;
   }

   String getKekID() {
      return this.kekID;
   }

   String getWrappedKEK() {
      return this.encodedWrappedKEK;
   }

   String getKmsInstanceID() {
      return this.kmsInstanceID;
   }

   String getKmsInstanceURL() {
      return this.kmsInstanceURL;
   }
}
