package org.apache.orc.impl;

import java.nio.charset.StandardCharsets;

public final class Utf8Utils {
   public static int charLength(byte[] data, int offset, int length) {
      int chars = 0;

      for(int i = 0; i < length; ++i) {
         if (isUtfStartByte(data[offset + i])) {
            ++chars;
         }
      }

      return chars;
   }

   public static int truncateBytesTo(int maxCharLength, byte[] data, int offset, int length) {
      int chars = 0;
      if (length <= maxCharLength) {
         return length;
      } else {
         for(int i = 0; i < length; ++i) {
            if (isUtfStartByte(data[offset + i])) {
               ++chars;
            }

            if (chars > maxCharLength) {
               return i;
            }
         }

         return length;
      }
   }

   public static boolean isUtfStartByte(byte b) {
      return (b & 192) != 128;
   }

   public static int findLastCharacter(byte[] text, int from, int until) {
      for(int posn = until; posn >= from; --posn) {
         if (isUtfStartByte(text[posn])) {
            return posn;
         }
      }

      throw new IllegalArgumentException("Could not truncate string, beginning of a valid char not found");
   }

   public static int getCodePoint(byte[] source, int from, int len) {
      return (new String(source, from, len, StandardCharsets.UTF_8)).codePointAt(0);
   }
}
