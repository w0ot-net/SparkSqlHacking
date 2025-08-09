package org.apache.commons.compress.utils;

import java.io.IOException;

public final class ParsingUtils {
   public static int parseIntValue(String value) throws IOException {
      return parseIntValue(value, 10);
   }

   public static int parseIntValue(String value, int radix) throws IOException {
      try {
         return Integer.parseInt(value, radix);
      } catch (NumberFormatException var3) {
         throw new IOException("Unable to parse int from string value: " + value);
      }
   }

   public static long parseLongValue(String value) throws IOException {
      return parseLongValue(value, 10);
   }

   public static long parseLongValue(String value, int radix) throws IOException {
      try {
         return Long.parseLong(value, radix);
      } catch (NumberFormatException var3) {
         throw new IOException("Unable to parse long from string value: " + value);
      }
   }

   private ParsingUtils() {
   }
}
