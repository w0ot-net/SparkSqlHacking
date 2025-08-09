package org.apache.parquet.format;

import shaded.parquet.org.apache.thrift.TEnum;
import shaded.parquet.org.apache.thrift.annotation.Nullable;

public enum Encoding implements TEnum {
   PLAIN(0),
   PLAIN_DICTIONARY(2),
   RLE(3),
   BIT_PACKED(4),
   DELTA_BINARY_PACKED(5),
   DELTA_LENGTH_BYTE_ARRAY(6),
   DELTA_BYTE_ARRAY(7),
   RLE_DICTIONARY(8),
   BYTE_STREAM_SPLIT(9);

   private final int value;

   private Encoding(int value) {
      this.value = value;
   }

   public int getValue() {
      return this.value;
   }

   @Nullable
   public static Encoding findByValue(int value) {
      switch (value) {
         case 0:
            return PLAIN;
         case 1:
         default:
            return null;
         case 2:
            return PLAIN_DICTIONARY;
         case 3:
            return RLE;
         case 4:
            return BIT_PACKED;
         case 5:
            return DELTA_BINARY_PACKED;
         case 6:
            return DELTA_LENGTH_BYTE_ARRAY;
         case 7:
            return DELTA_BYTE_ARRAY;
         case 8:
            return RLE_DICTIONARY;
         case 9:
            return BYTE_STREAM_SPLIT;
      }
   }
}
