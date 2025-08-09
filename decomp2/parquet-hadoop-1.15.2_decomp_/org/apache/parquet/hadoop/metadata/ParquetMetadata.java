package org.apache.parquet.hadoop.metadata;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;
import shaded.parquet.com.fasterxml.jackson.databind.ObjectMapper;
import shaded.parquet.com.fasterxml.jackson.databind.ObjectWriter;
import shaded.parquet.com.fasterxml.jackson.databind.SerializationFeature;

public class ParquetMetadata {
   private static final ObjectMapper objectMapper;
   private final FileMetaData fileMetaData;
   private final List blocks;

   public static String toJSON(ParquetMetadata parquetMetaData) {
      return toJSON(parquetMetaData, false);
   }

   public static String toPrettyJSON(ParquetMetadata parquetMetaData) {
      return toJSON(parquetMetaData, true);
   }

   private static String toJSON(ParquetMetadata parquetMetaData, boolean isPrettyPrint) {
      try {
         StringWriter stringWriter = new StringWriter();
         Throwable var3 = null;

         String var6;
         try {
            Object objectToPrint;
            if (parquetMetaData.getFileMetaData() != null && parquetMetaData.getFileMetaData().getEncryptionType() != FileMetaData.EncryptionType.UNENCRYPTED) {
               objectToPrint = parquetMetaData.getFileMetaData();
            } else {
               objectToPrint = parquetMetaData;
            }

            ObjectWriter writer;
            if (isPrettyPrint) {
               writer = objectMapper.writerWithDefaultPrettyPrinter();
            } else {
               writer = objectMapper.writer();
            }

            writer.writeValue(stringWriter, objectToPrint);
            var6 = stringWriter.toString();
         } catch (Throwable var16) {
            var3 = var16;
            throw var16;
         } finally {
            if (stringWriter != null) {
               if (var3 != null) {
                  try {
                     stringWriter.close();
                  } catch (Throwable var15) {
                     var3.addSuppressed(var15);
                  }
               } else {
                  stringWriter.close();
               }
            }

         }

         return var6;
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   public static ParquetMetadata fromJSON(String json) {
      try {
         return (ParquetMetadata)objectMapper.readValue(new StringReader(json), ParquetMetadata.class);
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   public ParquetMetadata(FileMetaData fileMetaData, List blocks) {
      this.fileMetaData = fileMetaData;
      this.blocks = blocks;
   }

   public List getBlocks() {
      return this.blocks;
   }

   public FileMetaData getFileMetaData() {
      return this.fileMetaData;
   }

   public String toString() {
      return "ParquetMetaData{" + this.fileMetaData + ", blocks: " + this.blocks + "}";
   }

   static {
      objectMapper = (new ObjectMapper()).configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
   }
}
