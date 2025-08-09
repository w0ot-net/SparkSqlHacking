package com.github.difflib.text;

import java.util.List;
import java.util.stream.Collectors;

final class StringUtils {
   public static String htmlEntites(String str) {
      return str.replace("<", "&lt;").replace(">", "&gt;");
   }

   public static String normalize(String str) {
      return htmlEntites(str).replace("\t", "    ");
   }

   public static List wrapText(List list, int columnWidth) {
      return (List)list.stream().map((line) -> wrapText(line, columnWidth)).collect(Collectors.toList());
   }

   public static String wrapText(String line, int columnWidth) {
      if (columnWidth < 0) {
         throw new IllegalArgumentException("columnWidth may not be less 0");
      } else if (columnWidth == 0) {
         return line;
      } else {
         int length = line.length();
         int delimiter = "<br/>".length();
         int widthIndex = columnWidth;
         StringBuilder b = new StringBuilder(line);

         for(int count = 0; length > widthIndex; ++count) {
            int breakPoint = widthIndex + delimiter * count;
            if (Character.isHighSurrogate(b.charAt(breakPoint - 1)) && Character.isLowSurrogate(b.charAt(breakPoint))) {
               ++breakPoint;
               if (breakPoint == b.length()) {
                  breakPoint -= 2;
               }
            }

            b.insert(breakPoint, "<br/>");
            widthIndex += columnWidth;
         }

         return b.toString();
      }
   }

   private StringUtils() {
   }
}
