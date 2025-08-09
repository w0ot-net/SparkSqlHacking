package org.apache.parquet.format;

import shaded.parquet.org.apache.thrift.TEnum;
import shaded.parquet.org.apache.thrift.annotation.Nullable;

public enum Type implements TEnum {
   BOOLEAN(0),
   INT32(1),
   INT64(2),
   INT96(3),
   FLOAT(4),
   DOUBLE(5),
   BYTE_ARRAY(6),
   FIXED_LEN_BYTE_ARRAY(7);

   private final int value;

   private Type(int value) {
      this.value = value;
   }

   public int getValue() {
      return this.value;
   }

   @Nullable
   public static Type findByValue(int value) {
      switch (value) {
         case 0:
            return BOOLEAN;
         case 1:
            return INT32;
         case 2:
            return INT64;
         case 3:
            return INT96;
         case 4:
            return FLOAT;
         case 5:
            return DOUBLE;
         case 6:
            return BYTE_ARRAY;
         case 7:
            return FIXED_LEN_BYTE_ARRAY;
         default:
            return null;
      }
   }
}
