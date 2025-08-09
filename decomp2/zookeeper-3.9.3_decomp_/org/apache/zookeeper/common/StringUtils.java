package org.apache.zookeeper.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class StringUtils {
   private StringUtils() {
   }

   public static List split(String value, String separator) {
      String[] splits = value.split(separator);
      List<String> results = new ArrayList();

      for(int i = 0; i < splits.length; ++i) {
         splits[i] = splits[i].trim();
         if (splits[i].length() > 0) {
            results.add(splits[i]);
         }
      }

      return Collections.unmodifiableList(results);
   }

   public static String joinStrings(List list, String delim) {
      Objects.requireNonNull(delim);
      return list == null ? null : String.join(delim, list);
   }

   public static boolean isBlank(String s) {
      return s == null || s.trim().isEmpty();
   }

   public static boolean isEmpty(String str) {
      return str == null || str.length() == 0;
   }
}
