package org.sparkproject.jpmml.model;

public class StringUtil {
   private StringUtil() {
   }

   public static String trim(String string) {
      int length = string.length();

      int trimmedLength;
      for(trimmedLength = length; trimmedLength > 0; --trimmedLength) {
         char c = string.charAt(trimmedLength - 1);
         if (!Character.isWhitespace(c)) {
            break;
         }
      }

      if (trimmedLength < length) {
         string = string.substring(0, trimmedLength);
      }

      return string;
   }
}
