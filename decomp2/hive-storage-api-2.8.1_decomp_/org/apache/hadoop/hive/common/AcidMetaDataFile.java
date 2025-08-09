package org.apache.hadoop.hive.common;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.codehaus.jackson.map.ObjectMapper;

public class AcidMetaDataFile {
   public static final String METADATA_FILE = "_metadata_acid";
   public static final String CURRENT_VERSION = "0";

   public static void writeToFile(FileSystem fs, Path basePath, DataFormat format) throws IOException {
      Map<String, String> metadata = new HashMap();
      metadata.put(AcidMetaDataFile.Field.VERSION.toString(), "0");
      metadata.put(AcidMetaDataFile.Field.DATA_FORMAT.toString(), format.toString());
      String data = (new ObjectMapper()).writeValueAsString(metadata);
      FSDataOutputStream out = fs.create(new Path(basePath, "_metadata_acid"));
      Throwable var6 = null;

      try {
         OutputStreamWriter writer = new OutputStreamWriter(out, "UTF-8");
         Throwable var8 = null;

         try {
            writer.write(data);
            writer.flush();
         } catch (Throwable var31) {
            var8 = var31;
            throw var31;
         } finally {
            if (writer != null) {
               if (var8 != null) {
                  try {
                     writer.close();
                  } catch (Throwable var30) {
                     var8.addSuppressed(var30);
                  }
               } else {
                  writer.close();
               }
            }

         }
      } catch (Throwable var33) {
         var6 = var33;
         throw var33;
      } finally {
         if (out != null) {
            if (var6 != null) {
               try {
                  out.close();
               } catch (Throwable var29) {
                  var6.addSuppressed(var29);
               }
            } else {
               out.close();
            }
         }

      }

   }

   public static enum Field {
      VERSION("thisFileVersion"),
      DATA_FORMAT("dataFormat");

      private final String fieldName;

      private Field(String fieldName) {
         this.fieldName = fieldName;
      }

      public String toString() {
         return this.fieldName;
      }
   }

   public static enum DataFormat {
      COMPACTED,
      TRUNCATED;

      public String toString() {
         return this.name().toLowerCase();
      }
   }
}
