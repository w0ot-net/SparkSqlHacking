package org.apache.ivy.plugins.matcher;

public class AnyMatcher implements Matcher {
   public static final Matcher INSTANCE = new AnyMatcher();

   public boolean matches(String input) {
      if (input == null) {
         throw new NullPointerException();
      } else {
         return true;
      }
   }

   public boolean isExact() {
      return false;
   }
}
