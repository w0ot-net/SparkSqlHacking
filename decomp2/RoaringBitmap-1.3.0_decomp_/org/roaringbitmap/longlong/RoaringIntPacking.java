package org.roaringbitmap.longlong;

import java.math.BigInteger;
import java.util.Comparator;

class RoaringIntPacking {
   private static final BigInteger TWO_64;

   public static int high(long id) {
      return (int)(id >> 32);
   }

   public static int low(long id) {
      return (int)id;
   }

   public static long pack(int high, int low) {
      return (long)high << 32 | (long)low & 4294967295L;
   }

   public static int highestHigh(boolean signedLongs) {
      return signedLongs ? Integer.MAX_VALUE : -1;
   }

   public static Comparator unsignedComparator() {
      return new Comparator() {
         public int compare(Integer o1, Integer o2) {
            return RoaringIntPacking.compareUnsigned(o1, o2);
         }
      };
   }

   public static int compareUnsigned(int x, int y) {
      return Integer.compare(x + Integer.MIN_VALUE, y + Integer.MIN_VALUE);
   }

   static String toUnsignedString(long l) {
      BigInteger b = BigInteger.valueOf(l);
      if (b.signum() < 0) {
         b = b.add(TWO_64);
      }

      return b.toString();
   }

   static {
      TWO_64 = BigInteger.ONE.shiftLeft(64);
   }
}
