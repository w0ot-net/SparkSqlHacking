package org.apache.parquet.crypto.keytools;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.parquet.crypto.ParquetCryptoRuntimeException;
import shaded.parquet.com.fasterxml.jackson.core.type.TypeReference;
import shaded.parquet.com.fasterxml.jackson.databind.JsonNode;
import shaded.parquet.com.fasterxml.jackson.databind.ObjectMapper;

public class HadoopFSKeyMaterialStore implements FileKeyMaterialStore {
   public static final String KEY_MATERIAL_FILE_PREFIX = "_KEY_MATERIAL_FOR_";
   public static final String TEMP_FILE_PREFIX = "_TMP";
   public static final String KEY_MATERIAL_FILE_SUFFFIX = ".json";
   private static final ObjectMapper objectMapper = new ObjectMapper();
   private FileSystem hadoopFileSystem;
   private Map keyMaterialMap;
   private Path keyMaterialFile;

   HadoopFSKeyMaterialStore(FileSystem hadoopFileSystem) {
      this.hadoopFileSystem = hadoopFileSystem;
   }

   public void initialize(Path parquetFilePath, Configuration hadoopConfig, boolean tempStore) {
      String fullPrefix = tempStore ? "_TMP" : "";
      fullPrefix = fullPrefix + "_KEY_MATERIAL_FOR_";
      this.keyMaterialFile = new Path(parquetFilePath.getParent(), fullPrefix + parquetFilePath.getName() + ".json");
   }

   public void addKeyMaterial(String keyIDInFile, String keyMaterial) throws ParquetCryptoRuntimeException {
      if (null == this.keyMaterialMap) {
         this.keyMaterialMap = new HashMap();
      }

      this.keyMaterialMap.put(keyIDInFile, keyMaterial);
   }

   public String getKeyMaterial(String keyIDInFile) throws ParquetCryptoRuntimeException {
      if (null == this.keyMaterialMap) {
         this.loadKeyMaterialMap();
      }

      return (String)this.keyMaterialMap.get(keyIDInFile);
   }

   private void loadKeyMaterialMap() {
      try {
         FSDataInputStream keyMaterialStream = this.hadoopFileSystem.open(this.keyMaterialFile);
         Throwable var2 = null;

         try {
            JsonNode keyMaterialJson = objectMapper.readTree(keyMaterialStream);
            this.keyMaterialMap = (Map)objectMapper.readValue(keyMaterialJson.traverse(), new TypeReference() {
            });
         } catch (Throwable var13) {
            var2 = var13;
            throw var13;
         } finally {
            if (keyMaterialStream != null) {
               if (var2 != null) {
                  try {
                     keyMaterialStream.close();
                  } catch (Throwable var12) {
                     var2.addSuppressed(var12);
                  }
               } else {
                  keyMaterialStream.close();
               }
            }

         }

      } catch (FileNotFoundException e) {
         throw new ParquetCryptoRuntimeException("External key material not found at " + this.keyMaterialFile, e);
      } catch (IOException e) {
         throw new ParquetCryptoRuntimeException("Failed to get key material from " + this.keyMaterialFile, e);
      }
   }

   public void saveMaterial() throws ParquetCryptoRuntimeException {
      try {
         FSDataOutputStream keyMaterialStream = this.hadoopFileSystem.create(this.keyMaterialFile);
         Throwable var2 = null;

         try {
            objectMapper.writeValue(keyMaterialStream, this.keyMaterialMap);
         } catch (Throwable var12) {
            var2 = var12;
            throw var12;
         } finally {
            if (keyMaterialStream != null) {
               if (var2 != null) {
                  try {
                     keyMaterialStream.close();
                  } catch (Throwable var11) {
                     var2.addSuppressed(var11);
                  }
               } else {
                  keyMaterialStream.close();
               }
            }

         }

      } catch (IOException e) {
         throw new ParquetCryptoRuntimeException("Failed to save key material in " + this.keyMaterialFile, e);
      }
   }

   public Set getKeyIDSet() throws ParquetCryptoRuntimeException {
      if (null == this.keyMaterialMap) {
         this.loadKeyMaterialMap();
      }

      return this.keyMaterialMap.keySet();
   }

   public void removeMaterial() throws ParquetCryptoRuntimeException {
      try {
         this.hadoopFileSystem.delete(this.keyMaterialFile, false);
      } catch (IOException e) {
         throw new ParquetCryptoRuntimeException("Failed to delete key material file " + this.keyMaterialFile, e);
      }
   }

   public void moveMaterialTo(FileKeyMaterialStore keyMaterialStore) throws ParquetCryptoRuntimeException {
      HadoopFSKeyMaterialStore targetStore;
      try {
         targetStore = (HadoopFSKeyMaterialStore)keyMaterialStore;
      } catch (ClassCastException e) {
         throw new IllegalArgumentException("Currently supports only moving to HadoopFSKeyMaterialStore, not to " + keyMaterialStore.getClass(), e);
      }

      Path targetKeyMaterialFile = targetStore.getStorageFilePath();

      try {
         this.hadoopFileSystem.rename(this.keyMaterialFile, targetKeyMaterialFile);
      } catch (IOException e) {
         throw new ParquetCryptoRuntimeException("Failed to rename file " + this.keyMaterialFile, e);
      }
   }

   private Path getStorageFilePath() {
      return this.keyMaterialFile;
   }
}
