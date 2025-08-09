package io.vertx.core.net.impl;

import java.nio.charset.StandardCharsets;

public final class URIDecoder {
   private URIDecoder() {
      throw new RuntimeException("Static Class");
   }

   public static String decodeURIComponent(String s) {
      return decodeURIComponent(s, true);
   }

   private static int indexOfPercentOrPlus(String s) {
      int i = 0;

      for(int size = s.length(); i < size; ++i) {
         char c = s.charAt(i);
         if (c == '%' || c == '+') {
            return i;
         }
      }

      return -1;
   }

   public static String decodeURIComponent(String s, boolean plus) {
      if (s == null) {
         return null;
      } else {
         int i = !plus ? s.indexOf(37) : indexOfPercentOrPlus(s);
         return i == -1 ? s : decodeAndTransformURIComponent(s, i, plus);
      }
   }

   private static String decodeAndTransformURIComponent(String s, int i, boolean plus) {
      byte[] buf = s.getBytes(StandardCharsets.UTF_8);
      int pos = i;
      int size = s.length();

      while(true) {
         label45: {
            if (i < size) {
               char c = s.charAt(i);
               if (c != '%') {
                  buf[pos++] = (byte)(plus && c == '+' ? 32 : c);
                  break label45;
               }

               if (i == size - 1) {
                  throw new IllegalArgumentException("unterminated escape sequence at end of string: " + s);
               }

               ++i;
               c = s.charAt(i);
               if (c != '%') {
                  if (i >= size - 1) {
                     throw new IllegalArgumentException("partial escape sequence at end of string: " + s);
                  }

                  c = decodeHexNibble(c);
                  ++i;
                  char c2 = decodeHexNibble(s.charAt(i));
                  if (c == '\uffff' || c2 == '\uffff') {
                     throw new IllegalArgumentException("invalid escape sequence `%" + s.charAt(i - 1) + s.charAt(i) + "' at index " + (i - 2) + " of: " + s);
                  }

                  c = (char)(c * 16 + c2);
                  buf[pos++] = (byte)c;
                  break label45;
               }

               buf[pos++] = 37;
            }

            return new String(buf, 0, pos, StandardCharsets.UTF_8);
         }

         ++i;
      }
   }

   private static char decodeHexNibble(char c) {
      if ('0' <= c && c <= '9') {
         return (char)(c - 48);
      } else if ('a' <= c && c <= 'f') {
         return (char)(c - 97 + 10);
      } else {
         return 'A' <= c && c <= 'F' ? (char)(c - 65 + 10) : '\uffff';
      }
   }
}
