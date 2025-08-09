package org.apache.hive.service.cli;

import org.apache.logging.log4j.core.StringLayout;
import org.apache.logging.log4j.core.layout.PatternLayout;

public class CLIServiceUtils {
   private static final char SEARCH_STRING_ESCAPE = '\\';
   public static final StringLayout verboseLayout = PatternLayout.newBuilder().withPattern("%d{yy/MM/dd HH:mm:ss} %p %c{2}: %m%n%ex").build();
   public static final StringLayout nonVerboseLayout = PatternLayout.newBuilder().withPattern("%-5p : %m%n%ex").build();

   public static String patternToRegex(String pattern) {
      if (pattern == null) {
         return ".*";
      } else {
         StringBuilder result = new StringBuilder(pattern.length());
         boolean escaped = false;
         int i = 0;

         for(int len = pattern.length(); i < len; ++i) {
            char c = pattern.charAt(i);
            if (escaped) {
               if (c != '\\') {
                  escaped = false;
               }

               result.append(c);
            } else if (c == '\\') {
               escaped = true;
            } else if (c == '%') {
               result.append(".*");
            } else if (c == '_') {
               result.append('.');
            } else {
               result.append(Character.toLowerCase(c));
            }
         }

         return result.toString();
      }
   }
}
