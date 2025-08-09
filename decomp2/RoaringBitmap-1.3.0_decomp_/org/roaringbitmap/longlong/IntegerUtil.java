package org.roaringbitmap.longlong;

public class IntegerUtil {
   public static byte[] toBDBytes(int v) {
      byte[] bytes = new byte[4];
      bytes[0] = (byte)(v >> 24);
      bytes[1] = (byte)(v >> 16);
      bytes[2] = (byte)(v >> 8);
      bytes[3] = (byte)v;
      return bytes;
   }

   public static int fromBDBytes(byte[] bytes) {
      return (bytes[0] & 255) << 24 | (bytes[1] & 255) << 16 | (bytes[2] & 255) << 8 | bytes[3] & 255;
   }

   public static int setByte(int v, byte bv, int pos) {
      int i = 3 - pos << 3;
      v &= ~(255 << i);
      v |= (bv & 255) << i;
      return v;
   }

   public static int shiftLeftFromSpecifiedPosition(int v, int pos, int count) {
      if (count != 0) {
         int shiftToLeft = 4 - count << 3;
         int shiftToRight = shiftToLeft - (pos << 3);
         int maskShifted = -1 >>> shiftToLeft << shiftToRight;
         v = v & ~maskShifted | v << 8 & maskShifted;
      }

      return v;
   }

   public static byte firstByte(int v) {
      return (byte)(v >> 24);
   }
}
