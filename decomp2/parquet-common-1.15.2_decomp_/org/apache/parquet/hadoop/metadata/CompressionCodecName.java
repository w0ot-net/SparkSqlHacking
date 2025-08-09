package org.apache.parquet.hadoop.metadata;

import java.util.Locale;
import org.apache.parquet.format.CompressionCodec;
import org.apache.parquet.hadoop.codec.CompressionCodecNotSupportedException;

public enum CompressionCodecName {
   UNCOMPRESSED((String)null, CompressionCodec.UNCOMPRESSED, ""),
   SNAPPY("org.apache.parquet.hadoop.codec.SnappyCodec", CompressionCodec.SNAPPY, ".snappy"),
   GZIP("org.apache.hadoop.io.compress.GzipCodec", CompressionCodec.GZIP, ".gz"),
   LZO("com.hadoop.compression.lzo.LzoCodec", CompressionCodec.LZO, ".lzo"),
   BROTLI("org.apache.hadoop.io.compress.BrotliCodec", CompressionCodec.BROTLI, ".br"),
   LZ4("org.apache.hadoop.io.compress.Lz4Codec", CompressionCodec.LZ4, ".lz4hadoop"),
   ZSTD("org.apache.parquet.hadoop.codec.ZstandardCodec", CompressionCodec.ZSTD, ".zstd"),
   LZ4_RAW("org.apache.parquet.hadoop.codec.Lz4RawCodec", CompressionCodec.LZ4_RAW, ".lz4raw");

   private final String hadoopCompressionCodecClass;
   private final CompressionCodec parquetCompressionCodec;
   private final String extension;

   public static CompressionCodecName fromConf(String name) {
      return name == null ? UNCOMPRESSED : valueOf(name.toUpperCase(Locale.ENGLISH));
   }

   public static CompressionCodecName fromCompressionCodec(Class clazz) {
      if (clazz == null) {
         return UNCOMPRESSED;
      } else {
         String name = clazz.getName();

         for(CompressionCodecName codec : values()) {
            if (name.equals(codec.getHadoopCompressionCodecClassName())) {
               return codec;
            }
         }

         throw new CompressionCodecNotSupportedException(clazz);
      }
   }

   public static CompressionCodecName fromParquet(CompressionCodec codec) {
      for(CompressionCodecName codecName : values()) {
         if (codec.equals(codecName.parquetCompressionCodec)) {
            return codecName;
         }
      }

      throw new IllegalArgumentException("Unknown compression codec " + codec);
   }

   private CompressionCodecName(String hadoopCompressionCodecClass, CompressionCodec parquetCompressionCodec, String extension) {
      this.hadoopCompressionCodecClass = hadoopCompressionCodecClass;
      this.parquetCompressionCodec = parquetCompressionCodec;
      this.extension = extension;
   }

   public String getHadoopCompressionCodecClassName() {
      return this.hadoopCompressionCodecClass;
   }

   public Class getHadoopCompressionCodecClass() {
      String codecClassName = this.getHadoopCompressionCodecClassName();
      if (codecClassName == null) {
         return null;
      } else {
         try {
            return Class.forName(codecClassName);
         } catch (ClassNotFoundException var3) {
            return null;
         }
      }
   }

   public CompressionCodec getParquetCompressionCodec() {
      return this.parquetCompressionCodec;
   }

   public String getExtension() {
      return this.extension;
   }
}
