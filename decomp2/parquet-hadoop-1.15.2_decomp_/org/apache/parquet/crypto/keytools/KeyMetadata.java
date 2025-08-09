package org.apache.parquet.crypto.keytools;

import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import org.apache.parquet.crypto.ParquetCryptoRuntimeException;
import shaded.parquet.com.fasterxml.jackson.core.type.TypeReference;
import shaded.parquet.com.fasterxml.jackson.databind.ObjectMapper;

public class KeyMetadata {
   static final String KEY_MATERIAL_INTERNAL_STORAGE_FIELD = "internalStorage";
   private static final String KEY_REFERENCE_FIELD = "keyReference";
   private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
   private final boolean isInternalStorage;
   private final String keyReference;
   private final KeyMaterial keyMaterial;

   private KeyMetadata(boolean isInternalStorage, String keyReference, KeyMaterial keyMaterial) {
      this.isInternalStorage = isInternalStorage;
      this.keyReference = keyReference;
      this.keyMaterial = keyMaterial;
   }

   static KeyMetadata parse(byte[] keyMetadataBytes) {
      String keyMetaDataString = new String(keyMetadataBytes, StandardCharsets.UTF_8);
      Map<String, Object> keyMetadataJson = null;

      try {
         keyMetadataJson = (Map)OBJECT_MAPPER.readValue(new StringReader(keyMetaDataString), new TypeReference() {
         });
      } catch (IOException e) {
         throw new ParquetCryptoRuntimeException("Failed to parse key metadata " + keyMetaDataString, e);
      }

      String keyMaterialType = (String)keyMetadataJson.get("keyMaterialType");
      if (!"PKMT1".equals(keyMaterialType)) {
         throw new ParquetCryptoRuntimeException("Wrong key material type: " + keyMaterialType + " vs " + "PKMT1");
      } else {
         Boolean isInternalStorage = (Boolean)keyMetadataJson.get("internalStorage");
         String keyReference;
         KeyMaterial keyMaterial;
         if (isInternalStorage) {
            keyMaterial = KeyMaterial.parse(keyMetadataJson);
            keyReference = null;
         } else {
            keyReference = (String)keyMetadataJson.get("keyReference");
            keyMaterial = null;
         }

         return new KeyMetadata(isInternalStorage, keyReference, keyMaterial);
      }
   }

   static String createSerializedForExternalMaterial(String keyReference) {
      Map<String, Object> keyMetadataMap = new HashMap(3);
      keyMetadataMap.put("keyMaterialType", "PKMT1");
      keyMetadataMap.put("internalStorage", Boolean.FALSE);
      keyMetadataMap.put("keyReference", keyReference);

      try {
         return OBJECT_MAPPER.writeValueAsString(keyMetadataMap);
      } catch (IOException e) {
         throw new ParquetCryptoRuntimeException("Failed to serialize key metadata", e);
      }
   }

   boolean keyMaterialStoredInternally() {
      return this.isInternalStorage;
   }

   KeyMaterial getKeyMaterial() {
      return this.keyMaterial;
   }

   String getKeyReference() {
      return this.keyReference;
   }
}
