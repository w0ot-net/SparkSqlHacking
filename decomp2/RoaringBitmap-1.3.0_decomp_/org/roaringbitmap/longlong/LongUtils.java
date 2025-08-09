package org.roaringbitmap.longlong;

public class LongUtils {
   public static final long MAX_UNSIGNED_INT = Integer.toUnsignedLong(-1);

   public static byte[] highPart(long num) {
      return new byte[]{(byte)((int)(num >>> 56 & 255L)), (byte)((int)(num >>> 48 & 255L)), (byte)((int)(num >>> 40 & 255L)), (byte)((int)(num >>> 32 & 255L)), (byte)((int)(num >>> 24 & 255L)), (byte)((int)(num >>> 16 & 255L))};
   }

   public static char lowPart(long num) {
      return (char)((int)num);
   }

   public static long toLong(byte[] high, char low) {
      return toLong(high) << 16 | (long)low;
   }

   public static long toLong(byte[] high) {
      return ((long)high[0] & 255L) << 40 | ((long)high[1] & 255L) << 32 | ((long)high[2] & 255L) << 24 | ((long)high[3] & 255L) << 16 | ((long)high[4] & 255L) << 8 | (long)high[5] & 255L;
   }

   public static long toLong(long high, char low) {
      return high << 16 | (long)low;
   }

   public static byte[] toBDBytes(long v) {
      byte[] work = new byte[8];
      work[7] = (byte)((int)v);
      work[6] = (byte)((int)(v >> 8));
      work[5] = (byte)((int)(v >> 16));
      work[4] = (byte)((int)(v >> 24));
      work[3] = (byte)((int)(v >> 32));
      work[2] = (byte)((int)(v >> 40));
      work[1] = (byte)((int)(v >> 48));
      work[0] = (byte)((int)(v >> 56));
      return work;
   }

   public static long fromBDBytes(byte[] work) {
      return (long)work[0] << 56 | (long)(work[1] & 255) << 48 | (long)(work[2] & 255) << 40 | (long)(work[3] & 255) << 32 | (long)(work[4] & 255) << 24 | (long)(work[5] & 255) << 16 | (long)(work[6] & 255) << 8 | (long)(work[7] & 255);
   }

   public static long initWithFirst4Byte(int v) {
      return (long)v << 32;
   }

   public static long rightShiftHighPart(long num) {
      return num >>> 16;
   }

   public static long leftShiftHighPart(long num) {
      return num << 16;
   }

   public static int maxLowBitAsInteger() {
      return 65535;
   }

   public static byte[] highPartInPlace(long num, byte[] high48) {
      high48[0] = (byte)((int)(num >>> 56 & 255L));
      high48[1] = (byte)((int)(num >>> 48 & 255L));
      high48[2] = (byte)((int)(num >>> 40 & 255L));
      high48[3] = (byte)((int)(num >>> 32 & 255L));
      high48[4] = (byte)((int)(num >>> 24 & 255L));
      high48[5] = (byte)((int)(num >>> 16 & 255L));
      return high48;
   }

   public static boolean isMaxHigh(long key) {
      return (key & 281474976710655L) == 281474976710655L;
   }
}
