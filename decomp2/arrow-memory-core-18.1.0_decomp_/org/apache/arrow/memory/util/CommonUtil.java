package org.apache.arrow.memory.util;

import java.util.Arrays;

public final class CommonUtil {
   private CommonUtil() {
   }

   public static int nextPowerOfTwo(int val) {
      if (val != 0 && val != 1) {
         int highestBit = Integer.highestOneBit(val);
         return highestBit == val ? val : highestBit << 1;
      } else {
         return val + 1;
      }
   }

   public static long nextPowerOfTwo(long val) {
      if (val != 0L && val != 1L) {
         long highestBit = Long.highestOneBit(val);
         return highestBit == val ? val : highestBit << 1;
      } else {
         return val + 1L;
      }
   }

   public static StringBuilder indent(StringBuilder sb, int indent) {
      char[] indentation = new char[indent * 2];
      Arrays.fill(indentation, ' ');
      sb.append(indentation);
      return sb;
   }
}
