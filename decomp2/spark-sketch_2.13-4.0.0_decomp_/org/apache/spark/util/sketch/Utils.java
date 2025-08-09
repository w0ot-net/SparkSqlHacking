package org.apache.spark.util.sketch;

import java.nio.charset.StandardCharsets;

class Utils {
   public static byte[] getBytesFromUTF8String(String str) {
      return str.getBytes(StandardCharsets.UTF_8);
   }

   public static long integralToLong(Object i) {
      long longValue;
      if (i instanceof Long longVal) {
         longValue = longVal;
      } else if (i instanceof Integer integer) {
         longValue = integer.longValue();
      } else if (i instanceof Short shortVal) {
         longValue = shortVal.longValue();
      } else {
         if (!(i instanceof Byte)) {
            throw new IllegalArgumentException("Unsupported data type " + i.getClass().getName());
         }

         Byte byteVal = (Byte)i;
         longValue = byteVal.longValue();
      }

      return longValue;
   }
}
