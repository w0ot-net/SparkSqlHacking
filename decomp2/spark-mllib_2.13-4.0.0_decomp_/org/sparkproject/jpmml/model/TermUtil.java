package org.sparkproject.jpmml.model;

public class TermUtil {
   private TermUtil() {
   }

   public static String trimPunctuation(String string) {
      int begin = 0;

      int end;
      for(end = string.length(); begin < end; ++begin) {
         char c = string.charAt(begin);
         if (!isPunctuation(c)) {
            break;
         }
      }

      while(end > begin) {
         char c = string.charAt(end - 1);
         if (!isPunctuation(c)) {
            break;
         }

         --end;
      }

      if (begin > 0 || end < string.length()) {
         string = string.substring(begin, end);
      }

      return string;
   }

   public static boolean isPunctuation(char c) {
      int type = Character.getType(c);
      switch (type) {
         case 20:
         case 21:
         case 22:
         case 23:
         case 24:
         case 29:
         case 30:
            return true;
         case 25:
         case 26:
         case 27:
         case 28:
         default:
            return false;
      }
   }
}
