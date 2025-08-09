package org.apache.datasketches.common;

public final class ByteArrayUtil {
   public static void copyBytes(byte[] source, int srcStart, byte[] target, int tgtStart, int numBytes) {
      Util.checkBounds((long)srcStart, (long)numBytes, (long)source.length);
      Util.checkBounds((long)tgtStart, (long)numBytes, (long)target.length);
      int i = 0;
      int j = srcStart;

      for(int k = tgtStart; i < numBytes; ++i) {
         target[k++] = source[j++];
      }

   }

   public static short getShortLE(byte[] array, int offset) {
      return (short)(array[offset] & 255 | (array[offset + 1] & 255) << 8);
   }

   public static void putShortLE(byte[] array, int offset, short value) {
      array[offset] = (byte)value;
      array[offset + 1] = (byte)(value >>> 8);
   }

   public static short getShortBE(byte[] array, int offset) {
      return (short)(array[offset + 1] & 255 | (array[offset] & 255) << 8);
   }

   public static void putShortBE(byte[] array, int offset, short value) {
      array[offset + 1] = (byte)value;
      array[offset] = (byte)(value >>> 8);
   }

   public static int getIntLE(byte[] array, int offset) {
      return array[offset] & 255 | (array[offset + 1] & 255) << 8 | (array[offset + 2] & 255) << 16 | (array[offset + 3] & 255) << 24;
   }

   public static void putIntLE(byte[] array, int offset, int value) {
      array[offset] = (byte)value;
      array[offset + 1] = (byte)(value >>> 8);
      array[offset + 2] = (byte)(value >>> 16);
      array[offset + 3] = (byte)(value >>> 24);
   }

   public static int getIntBE(byte[] array, int offset) {
      return array[offset + 3] & 255 | (array[offset + 2] & 255) << 8 | (array[offset + 1] & 255) << 16 | (array[offset] & 255) << 24;
   }

   public static void putIntBE(byte[] array, int offset, int value) {
      array[offset + 3] = (byte)value;
      array[offset + 2] = (byte)(value >>> 8);
      array[offset + 1] = (byte)(value >>> 16);
      array[offset] = (byte)(value >>> 24);
   }

   public static long getLongLE(byte[] array, int offset) {
      return (long)array[offset] & 255L | ((long)array[offset + 1] & 255L) << 8 | ((long)array[offset + 2] & 255L) << 16 | ((long)array[offset + 3] & 255L) << 24 | ((long)array[offset + 4] & 255L) << 32 | ((long)array[offset + 5] & 255L) << 40 | ((long)array[offset + 6] & 255L) << 48 | ((long)array[offset + 7] & 255L) << 56;
   }

   public static void putLongLE(byte[] array, int offset, long value) {
      array[offset] = (byte)((int)value);
      array[offset + 1] = (byte)((int)(value >>> 8));
      array[offset + 2] = (byte)((int)(value >>> 16));
      array[offset + 3] = (byte)((int)(value >>> 24));
      array[offset + 4] = (byte)((int)(value >>> 32));
      array[offset + 5] = (byte)((int)(value >>> 40));
      array[offset + 6] = (byte)((int)(value >>> 48));
      array[offset + 7] = (byte)((int)(value >>> 56));
   }

   public static long getLongBE(byte[] array, int offset) {
      return (long)array[offset + 7] & 255L | ((long)array[offset + 6] & 255L) << 8 | ((long)array[offset + 5] & 255L) << 16 | ((long)array[offset + 4] & 255L) << 24 | ((long)array[offset + 3] & 255L) << 32 | ((long)array[offset + 2] & 255L) << 40 | ((long)array[offset + 1] & 255L) << 48 | ((long)array[offset] & 255L) << 56;
   }

   public static void putLongBE(byte[] array, int offset, long value) {
      array[offset + 7] = (byte)((int)value);
      array[offset + 6] = (byte)((int)(value >>> 8));
      array[offset + 5] = (byte)((int)(value >>> 16));
      array[offset + 4] = (byte)((int)(value >>> 24));
      array[offset + 3] = (byte)((int)(value >>> 32));
      array[offset + 2] = (byte)((int)(value >>> 40));
      array[offset + 1] = (byte)((int)(value >>> 48));
      array[offset] = (byte)((int)(value >>> 56));
   }

   public static float getFloatLE(byte[] array, int offset) {
      return Float.intBitsToFloat(getIntLE(array, offset));
   }

   public static void putFloatLE(byte[] array, int offset, float value) {
      putIntLE(array, offset, Float.floatToRawIntBits(value));
   }

   public static float getFloatBE(byte[] array, int offset) {
      return Float.intBitsToFloat(getIntBE(array, offset));
   }

   public static void putFloatBE(byte[] array, int offset, float value) {
      putIntBE(array, offset, Float.floatToRawIntBits(value));
   }

   public static double getDoubleLE(byte[] array, int offset) {
      return Double.longBitsToDouble(getLongLE(array, offset));
   }

   public static void putDoubleLE(byte[] array, int offset, double value) {
      putLongLE(array, offset, Double.doubleToRawLongBits(value));
   }

   public static double getDoubleBE(byte[] array, int offset) {
      return Double.longBitsToDouble(getLongBE(array, offset));
   }

   public static void putDoubleBE(byte[] array, int offset, double value) {
      putLongBE(array, offset, Double.doubleToRawLongBits(value));
   }
}
