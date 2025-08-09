package org.apache.parquet.format;

import shaded.parquet.org.apache.thrift.TEnum;
import shaded.parquet.org.apache.thrift.annotation.Nullable;

public enum ConvertedType implements TEnum {
   UTF8(0),
   MAP(1),
   MAP_KEY_VALUE(2),
   LIST(3),
   ENUM(4),
   DECIMAL(5),
   DATE(6),
   TIME_MILLIS(7),
   TIME_MICROS(8),
   TIMESTAMP_MILLIS(9),
   TIMESTAMP_MICROS(10),
   UINT_8(11),
   UINT_16(12),
   UINT_32(13),
   UINT_64(14),
   INT_8(15),
   INT_16(16),
   INT_32(17),
   INT_64(18),
   JSON(19),
   BSON(20),
   INTERVAL(21);

   private final int value;

   private ConvertedType(int value) {
      this.value = value;
   }

   public int getValue() {
      return this.value;
   }

   @Nullable
   public static ConvertedType findByValue(int value) {
      switch (value) {
         case 0:
            return UTF8;
         case 1:
            return MAP;
         case 2:
            return MAP_KEY_VALUE;
         case 3:
            return LIST;
         case 4:
            return ENUM;
         case 5:
            return DECIMAL;
         case 6:
            return DATE;
         case 7:
            return TIME_MILLIS;
         case 8:
            return TIME_MICROS;
         case 9:
            return TIMESTAMP_MILLIS;
         case 10:
            return TIMESTAMP_MICROS;
         case 11:
            return UINT_8;
         case 12:
            return UINT_16;
         case 13:
            return UINT_32;
         case 14:
            return UINT_64;
         case 15:
            return INT_8;
         case 16:
            return INT_16;
         case 17:
            return INT_32;
         case 18:
            return INT_64;
         case 19:
            return JSON;
         case 20:
            return BSON;
         case 21:
            return INTERVAL;
         default:
            return null;
      }
   }
}
