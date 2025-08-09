package jodd.util;

public class Bits {
   public static boolean getBoolean(byte[] b, int off) {
      return b[off] != 0;
   }

   public static char getChar(byte[] b, int off) {
      return (char)((b[off + 1] & 255) + (b[off] << 8));
   }

   public static short getShort(byte[] b, int off) {
      return (short)((b[off + 1] & 255) + (b[off] << 8));
   }

   public static int getInt(byte[] b, int off) {
      return (b[off + 3] & 255) + ((b[off + 2] & 255) << 8) + ((b[off + 1] & 255) << 16) + (b[off] << 24);
   }

   public static float getFloat(byte[] b, int off) {
      int i = getInt(b, off);
      return Float.intBitsToFloat(i);
   }

   public static long getLong(byte[] b, int off) {
      return ((long)b[off + 7] & 255L) + (((long)b[off + 6] & 255L) << 8) + (((long)b[off + 5] & 255L) << 16) + (((long)b[off + 4] & 255L) << 24) + (((long)b[off + 3] & 255L) << 32) + (((long)b[off + 2] & 255L) << 40) + (((long)b[off + 1] & 255L) << 48) + ((long)b[off] << 56);
   }

   public static double getDouble(byte[] b, int off) {
      long j = getLong(b, off);
      return Double.longBitsToDouble(j);
   }

   public static void putBoolean(byte[] b, int off, boolean val) {
      b[off] = (byte)(val ? 1 : 0);
   }

   public static void putChar(byte[] b, int off, char val) {
      b[off + 1] = (byte)val;
      b[off] = (byte)(val >>> 8);
   }

   public static void putShort(byte[] b, int off, short val) {
      b[off + 1] = (byte)val;
      b[off] = (byte)(val >>> 8);
   }

   public static void putInt(byte[] b, int off, int val) {
      b[off + 3] = (byte)val;
      b[off + 2] = (byte)(val >>> 8);
      b[off + 1] = (byte)(val >>> 16);
      b[off] = (byte)(val >>> 24);
   }

   public static void putFloat(byte[] b, int off, float val) {
      int i = Float.floatToIntBits(val);
      putInt(b, off, i);
   }

   public static void putLong(byte[] b, int off, long val) {
      b[off + 7] = (byte)((int)val);
      b[off + 6] = (byte)((int)(val >>> 8));
      b[off + 5] = (byte)((int)(val >>> 16));
      b[off + 4] = (byte)((int)(val >>> 24));
      b[off + 3] = (byte)((int)(val >>> 32));
      b[off + 2] = (byte)((int)(val >>> 40));
      b[off + 1] = (byte)((int)(val >>> 48));
      b[off] = (byte)((int)(val >>> 56));
   }

   public static void putDouble(byte[] b, int off, double val) {
      long j = Double.doubleToLongBits(val);
      putLong(b, off, j);
   }

   public static boolean isSet(byte value, byte mask) {
      return (value & mask) == mask;
   }

   public static boolean isSet(int value, int mask) {
      return (value & mask) == mask;
   }

   public static boolean notSet(int value, int mask) {
      return (value & mask) != mask;
   }

   public static int set(int value, int mask, boolean setBit) {
      return setBit ? value | mask : value & ~mask;
   }

   public static byte set(byte value, byte mask, boolean setBit) {
      return (byte)(setBit ? value | mask : value & ~mask);
   }
}
