package shaded.parquet.net.openhft.hashing;

import java.nio.ByteOrder;

final class Primitives {
   static final boolean NATIVE_LITTLE_ENDIAN;
   private static final ByteOrderHelper H2LE;
   private static final ByteOrderHelper H2BE;

   private Primitives() {
   }

   static long unsignedInt(int i) {
      return (long)i & 4294967295L;
   }

   static int unsignedShort(int s) {
      return s & '\uffff';
   }

   static int unsignedByte(int b) {
      return b & 255;
   }

   static long nativeToLittleEndian(long v) {
      return H2LE.adjustByteOrder(v);
   }

   static int nativeToLittleEndian(int v) {
      return H2LE.adjustByteOrder(v);
   }

   static short nativeToLittleEndian(short v) {
      return H2LE.adjustByteOrder(v);
   }

   static char nativeToLittleEndian(char v) {
      return H2LE.adjustByteOrder(v);
   }

   static long nativeToBigEndian(long v) {
      return H2BE.adjustByteOrder(v);
   }

   static int nativeToBigEndian(int v) {
      return H2BE.adjustByteOrder(v);
   }

   static short nativeToBigEndian(short v) {
      return H2BE.adjustByteOrder(v);
   }

   static char nativeToBigEndian(char v) {
      return H2BE.adjustByteOrder(v);
   }

   static {
      NATIVE_LITTLE_ENDIAN = ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN;
      H2LE = (ByteOrderHelper)(NATIVE_LITTLE_ENDIAN ? new ByteOrderHelper() : new ByteOrderHelperReverse());
      H2BE = (ByteOrderHelper)(NATIVE_LITTLE_ENDIAN ? new ByteOrderHelperReverse() : new ByteOrderHelper());
   }

   private static class ByteOrderHelper {
      private ByteOrderHelper() {
      }

      long adjustByteOrder(long v) {
         return v;
      }

      int adjustByteOrder(int v) {
         return v;
      }

      short adjustByteOrder(short v) {
         return v;
      }

      char adjustByteOrder(char v) {
         return v;
      }
   }

   private static class ByteOrderHelperReverse extends ByteOrderHelper {
      private ByteOrderHelperReverse() {
      }

      long adjustByteOrder(long v) {
         return Long.reverseBytes(v);
      }

      int adjustByteOrder(int v) {
         return Integer.reverseBytes(v);
      }

      short adjustByteOrder(short v) {
         return Short.reverseBytes(v);
      }

      char adjustByteOrder(char v) {
         return Character.reverseBytes(v);
      }
   }
}
