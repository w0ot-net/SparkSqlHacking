package shaded.parquet.org.apache.thrift;

public class EncodingUtils {
   public static final void encodeBigEndian(int integer, byte[] buf) {
      encodeBigEndian(integer, buf, 0);
   }

   public static final void encodeBigEndian(int integer, byte[] buf, int offset) {
      buf[offset] = (byte)(255 & integer >> 24);
      buf[offset + 1] = (byte)(255 & integer >> 16);
      buf[offset + 2] = (byte)(255 & integer >> 8);
      buf[offset + 3] = (byte)(255 & integer);
   }

   public static final int decodeBigEndian(byte[] buf) {
      return decodeBigEndian(buf, 0);
   }

   public static final int decodeBigEndian(byte[] buf, int offset) {
      return (buf[offset] & 255) << 24 | (buf[offset + 1] & 255) << 16 | (buf[offset + 2] & 255) << 8 | buf[offset + 3] & 255;
   }

   public static final boolean testBit(byte v, int position) {
      return testBit((int)v, position);
   }

   public static final boolean testBit(short v, int position) {
      return testBit((int)v, position);
   }

   public static final boolean testBit(int v, int position) {
      return (v & 1 << position) != 0;
   }

   public static final boolean testBit(long v, int position) {
      return (v & 1L << position) != 0L;
   }

   public static final byte clearBit(byte v, int position) {
      return (byte)clearBit((int)v, position);
   }

   public static final short clearBit(short v, int position) {
      return (short)clearBit((int)v, position);
   }

   public static final int clearBit(int v, int position) {
      return v & ~(1 << position);
   }

   public static final long clearBit(long v, int position) {
      return v & ~(1L << position);
   }

   public static final byte setBit(byte v, int position, boolean value) {
      return (byte)setBit((int)v, position, value);
   }

   public static final short setBit(short v, int position, boolean value) {
      return (short)setBit((int)v, position, value);
   }

   public static final int setBit(int v, int position, boolean value) {
      return value ? v | 1 << position : clearBit(v, position);
   }

   public static final long setBit(long v, int position, boolean value) {
      return value ? v | 1L << position : clearBit(v, position);
   }
}
