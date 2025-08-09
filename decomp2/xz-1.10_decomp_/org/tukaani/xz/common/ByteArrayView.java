package org.tukaani.xz.common;

public final class ByteArrayView {
   public static final int ALIGN_SHORT = 0;
   public static final int ALIGN_INT = 0;
   public static final int ALIGN_LONG = 0;

   public static short getShortBE(byte[] buf, int index) {
      return (short)(buf[index] << 8 | buf[index + 1] & 255);
   }

   public static short getShortLE(byte[] buf, int index) {
      return (short)(buf[index] & 255 | buf[index + 1] << 8);
   }

   public static int getIntBE(byte[] buf, int index) {
      return buf[index] << 24 | (buf[index + 1] & 255) << 16 | (buf[index + 2] & 255) << 8 | buf[index + 3] & 255;
   }

   public static int getIntLE(byte[] buf, int index) {
      return buf[index] & 255 | (buf[index + 1] & 255) << 8 | (buf[index + 2] & 255) << 16 | buf[index + 3] << 24;
   }

   public static long getLongBE(byte[] buf, int index) {
      return (long)buf[index] << 56 | (long)(buf[index + 1] & 255) << 48 | (long)(buf[index + 2] & 255) << 40 | (long)(buf[index + 3] & 255) << 32 | (long)(buf[index + 4] & 255) << 24 | (long)(buf[index + 5] & 255) << 16 | (long)(buf[index + 6] & 255) << 8 | (long)buf[index + 7] & 255L;
   }

   public static long getLongLE(byte[] buf, int index) {
      return (long)buf[index] & 255L | (long)(buf[index + 1] & 255) << 8 | (long)(buf[index + 2] & 255) << 16 | (long)(buf[index + 3] & 255) << 24 | (long)(buf[index + 4] & 255) << 32 | (long)(buf[index + 5] & 255) << 40 | (long)(buf[index + 6] & 255) << 48 | (long)buf[index + 7] << 56;
   }

   public static void setShortBE(byte[] buf, int index, short value) {
      buf[index] = (byte)(value >>> 8);
      buf[index + 1] = (byte)value;
   }

   public static void setShortLE(byte[] buf, int index, short value) {
      buf[index] = (byte)value;
      buf[index + 1] = (byte)(value >>> 8);
   }

   public static void setIntBE(byte[] buf, int index, int value) {
      buf[index] = (byte)(value >>> 24);
      buf[index + 1] = (byte)(value >>> 16);
      buf[index + 2] = (byte)(value >>> 8);
      buf[index + 3] = (byte)value;
   }

   public static void setIntLE(byte[] buf, int index, int value) {
      buf[index] = (byte)value;
      buf[index + 1] = (byte)(value >>> 8);
      buf[index + 2] = (byte)(value >>> 16);
      buf[index + 3] = (byte)(value >>> 24);
   }

   public static void setLongBE(byte[] buf, int index, long value) {
      buf[index] = (byte)((int)(value >>> 56));
      buf[index + 1] = (byte)((int)(value >>> 48));
      buf[index + 2] = (byte)((int)(value >>> 40));
      buf[index + 3] = (byte)((int)(value >>> 32));
      buf[index + 4] = (byte)((int)(value >>> 24));
      buf[index + 5] = (byte)((int)(value >>> 16));
      buf[index + 6] = (byte)((int)(value >>> 8));
      buf[index + 7] = (byte)((int)value);
   }

   public static void setLongLE(byte[] buf, int index, long value) {
      buf[index] = (byte)((int)value);
      buf[index + 1] = (byte)((int)(value >>> 8));
      buf[index + 2] = (byte)((int)(value >>> 16));
      buf[index + 3] = (byte)((int)(value >>> 24));
      buf[index + 4] = (byte)((int)(value >>> 32));
      buf[index + 5] = (byte)((int)(value >>> 40));
      buf[index + 6] = (byte)((int)(value >>> 48));
      buf[index + 7] = (byte)((int)(value >>> 56));
   }

   private ByteArrayView() {
   }
}
