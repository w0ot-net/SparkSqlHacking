package org.apache.orc.impl;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.orc.Reader;

public class OrcAcidUtils {
   public static final String ACID_STATS = "hive.acid.stats";
   public static final String DELTA_SIDE_FILE_SUFFIX = "_flush_length";
   private static final Charset utf8;
   private static final CharsetDecoder utf8Decoder;

   public static Path getSideFile(Path main) {
      return new Path(String.valueOf(main) + "_flush_length");
   }

   public static long getLastFlushLength(FileSystem fs, Path deltaFile) throws IOException {
      Path lengths = getSideFile(deltaFile);
      long result = Long.MAX_VALUE;
      if (!fs.exists(lengths)) {
         return result;
      } else {
         try {
            FSDataInputStream stream = fs.open(lengths);

            long var6;
            try {
               for(result = -1L; stream.available() > 0; result = stream.readLong()) {
               }

               var6 = result;
            } catch (Throwable var9) {
               if (stream != null) {
                  try {
                     stream.close();
                  } catch (Throwable var8) {
                     var9.addSuppressed(var8);
                  }
               }

               throw var9;
            }

            if (stream != null) {
               stream.close();
            }

            return var6;
         } catch (IOException var10) {
            return result;
         }
      }
   }

   public static AcidStats parseAcidStats(Reader reader) {
      if (reader.hasMetadataValue("hive.acid.stats")) {
         try {
            ByteBuffer val = reader.getMetadataValue("hive.acid.stats").duplicate();
            return new AcidStats(utf8Decoder.decode(val).toString());
         } catch (CharacterCodingException e) {
            throw new IllegalArgumentException("Bad string encoding for hive.acid.stats", e);
         }
      } else {
         return null;
      }
   }

   static {
      utf8 = StandardCharsets.UTF_8;
      utf8Decoder = utf8.newDecoder();
   }
}
