package org.apache.ivy.osgi.util;

import java.util.ArrayList;
import java.util.List;

public class ParseUtil {
   public static String[] parseDelimitedString(String value, String delim) {
      if (value == null) {
         value = "";
      }

      List<String> list = new ArrayList();
      int CHAR = 1;
      int DELIMITER = 2;
      int STARTQUOTE = 4;
      int ENDQUOTE = 8;
      StringBuilder sb = new StringBuilder();
      int expecting = 7;

      for(char c : value.toCharArray()) {
         boolean isDelimiter = delim.indexOf(c) >= 0;
         boolean isQuote = c == '"';
         if (isDelimiter && (expecting & 2) > 0) {
            list.add(sb.toString().trim());
            sb.delete(0, sb.length());
            expecting = 7;
         } else if (isQuote && (expecting & 4) > 0) {
            sb.append(c);
            expecting = 9;
         } else if (isQuote && (expecting & 8) > 0) {
            sb.append(c);
            expecting = 7;
         } else {
            if ((expecting & 1) <= 0) {
               throw new IllegalArgumentException("Invalid delimited string: " + value);
            }

            sb.append(c);
         }
      }

      if (sb.length() > 0) {
         list.add(sb.toString().trim());
      }

      return (String[])list.toArray(new String[list.size()]);
   }
}
