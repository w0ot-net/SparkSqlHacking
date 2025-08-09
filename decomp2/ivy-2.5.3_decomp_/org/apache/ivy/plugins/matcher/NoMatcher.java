package org.apache.ivy.plugins.matcher;

public final class NoMatcher implements Matcher {
   public static final Matcher INSTANCE = new NoMatcher();

   public boolean matches(String input) {
      if (input == null) {
         throw new NullPointerException();
      } else {
         return false;
      }
   }

   public boolean isExact() {
      return true;
   }
}
