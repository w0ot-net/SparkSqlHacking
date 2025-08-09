package org.apache.commons.compress.archivers.cpio;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

final class CpioUtil {
   static final String DEFAULT_CHARSET_NAME;

   static long byteArray2long(byte[] number, boolean swapHalfWord) {
      if (number.length % 2 != 0) {
         throw new UnsupportedOperationException();
      } else {
         int pos = 0;
         byte[] tmpNumber = Arrays.copyOf(number, number.length);
         if (!swapHalfWord) {
            byte tmp = 0;

            for(int var8 = 0; var8 < tmpNumber.length; ++var8) {
               tmp = tmpNumber[var8];
               tmpNumber[var8++] = tmpNumber[var8];
               tmpNumber[var8] = tmp;
            }
         }

         long ret = (long)(tmpNumber[0] & 255);

         for(int var10 = 1; var10 < tmpNumber.length; ++var10) {
            ret <<= 8;
            ret |= (long)(tmpNumber[var10] & 255);
         }

         return ret;
      }
   }

   static long fileType(long mode) {
      return mode & 61440L;
   }

   static byte[] long2byteArray(long number, int length, boolean swapHalfWord) {
      byte[] ret = new byte[length];
      int pos = 0;
      if (length % 2 == 0 && length >= 2) {
         long tmp_number = number;

         for(int var9 = length - 1; var9 >= 0; --var9) {
            ret[var9] = (byte)((int)(tmp_number & 255L));
            tmp_number >>= 8;
         }

         if (!swapHalfWord) {
            byte tmp = 0;

            for(int var10 = 0; var10 < length; ++var10) {
               tmp = ret[var10];
               ret[var10++] = ret[var10];
               ret[var10] = tmp;
            }
         }

         return ret;
      } else {
         throw new UnsupportedOperationException();
      }
   }

   static {
      DEFAULT_CHARSET_NAME = StandardCharsets.US_ASCII.name();
   }
}
