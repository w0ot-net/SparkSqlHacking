package org.apache.thrift.utils;

public final class StringUtils {
   private static final char[] HEX_CHARS = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

   private StringUtils() {
   }

   public static String bytesToHexString(byte[] bytes) {
      return bytes == null ? null : bytesToHexString(bytes, 0, bytes.length);
   }

   public static String bytesToHexString(byte[] bytes, int offset, int length) {
      if (length < 0) {
         throw new IllegalArgumentException("Negative length " + length);
      } else if (offset < 0) {
         throw new IndexOutOfBoundsException("Negative start offset " + offset);
      } else if (length > bytes.length - offset) {
         throw new IndexOutOfBoundsException("Invalid range, bytes.length: " + bytes.length + " offset: " + offset + " length: " + length);
      } else {
         char[] chars = new char[length * 2];

         for(int i = 0; i < length; ++i) {
            int unsignedInt = bytes[i + offset] & 255;
            chars[2 * i] = HEX_CHARS[unsignedInt >>> 4];
            chars[2 * i + 1] = HEX_CHARS[unsignedInt & 15];
         }

         return new String(chars);
      }
   }
}
