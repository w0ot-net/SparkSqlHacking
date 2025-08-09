package org.apache.commons.cli;

final class Util {
   static final String[] EMPTY_STRING_ARRAY = new String[0];

   static boolean isEmpty(Object[] array) {
      return array == null || array.length == 0;
   }

   static boolean isEmpty(String str) {
      return str == null || str.isEmpty();
   }

   static String stripLeadingAndTrailingQuotes(String str) {
      if (isEmpty(str)) {
         return str;
      } else {
         int length = str.length();
         return length > 1 && str.startsWith("\"") && str.endsWith("\"") && str.substring(1, length - 1).indexOf(34) == -1 ? str.substring(1, length - 1) : str;
      }
   }

   static String stripLeadingHyphens(String str) {
      if (isEmpty(str)) {
         return str;
      } else if (str.startsWith("--")) {
         return str.substring(2);
      } else {
         return str.startsWith("-") ? str.substring(1) : str;
      }
   }

   private Util() {
   }
}
