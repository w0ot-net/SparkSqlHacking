package org.datanucleus.store.rdbms.adapter;

public class DerbySQLFunction {
   public static int ascii(String code) {
      return code.charAt(0);
   }

   public static int matches(String text, String pattern) {
      return text.matches(pattern) ? 1 : 0;
   }
}
