package org.apache.avro.hadoop.file;

import java.util.HashMap;
import java.util.Map;
import org.apache.avro.AvroRuntimeException;
import org.apache.avro.file.CodecFactory;

public class HadoopCodecFactory {
   private static final Map HADOOP_AVRO_NAME_MAP = new HashMap();

   public static CodecFactory fromHadoopString(String hadoopCodecClass) {
      CodecFactory o = null;

      try {
         String avroCodec = (String)HADOOP_AVRO_NAME_MAP.get(hadoopCodecClass);
         if (avroCodec != null) {
            o = CodecFactory.fromString(avroCodec);
         }

         return o;
      } catch (Exception e) {
         throw new AvroRuntimeException("Unrecognized hadoop codec: " + hadoopCodecClass, e);
      }
   }

   public static String getAvroCodecName(String hadoopCodecClass) {
      return (String)HADOOP_AVRO_NAME_MAP.get(hadoopCodecClass);
   }

   static {
      HADOOP_AVRO_NAME_MAP.put("org.apache.hadoop.io.compress.DeflateCodec", "deflate");
      HADOOP_AVRO_NAME_MAP.put("org.apache.hadoop.io.compress.SnappyCodec", "snappy");
      HADOOP_AVRO_NAME_MAP.put("org.apache.hadoop.io.compress.BZip2Codec", "bzip2");
      HADOOP_AVRO_NAME_MAP.put("org.apache.hadoop.io.compress.GZipCodec", "deflate");
      HADOOP_AVRO_NAME_MAP.put("org.apache.hadoop.io.compress.ZStandardCodec", "zstandard");
   }
}
