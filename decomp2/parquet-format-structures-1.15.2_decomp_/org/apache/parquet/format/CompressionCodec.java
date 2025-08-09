package org.apache.parquet.format;

import shaded.parquet.org.apache.thrift.TEnum;
import shaded.parquet.org.apache.thrift.annotation.Nullable;

public enum CompressionCodec implements TEnum {
   UNCOMPRESSED(0),
   SNAPPY(1),
   GZIP(2),
   LZO(3),
   BROTLI(4),
   LZ4(5),
   ZSTD(6),
   LZ4_RAW(7);

   private final int value;

   private CompressionCodec(int value) {
      this.value = value;
   }

   public int getValue() {
      return this.value;
   }

   @Nullable
   public static CompressionCodec findByValue(int value) {
      switch (value) {
         case 0:
            return UNCOMPRESSED;
         case 1:
            return SNAPPY;
         case 2:
            return GZIP;
         case 3:
            return LZO;
         case 4:
            return BROTLI;
         case 5:
            return LZ4;
         case 6:
            return ZSTD;
         case 7:
            return LZ4_RAW;
         default:
            return null;
      }
   }
}
