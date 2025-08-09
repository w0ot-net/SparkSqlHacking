package org.apache.ivy.plugins.matcher;

public final class ExactOrRegexpPatternMatcher extends AbstractPatternMatcher {
   public static final ExactOrRegexpPatternMatcher INSTANCE = new ExactOrRegexpPatternMatcher();

   public ExactOrRegexpPatternMatcher() {
      super("exactOrRegexp");
   }

   protected Matcher newMatcher(String expression) {
      return new ExactOrRegexpMatcher(expression);
   }

   private static final class ExactOrRegexpMatcher implements Matcher {
      private Matcher exact;
      private Matcher regexp;

      public ExactOrRegexpMatcher(String expression) {
         this.exact = ExactPatternMatcher.INSTANCE.getMatcher(expression);
         this.regexp = RegexpPatternMatcher.INSTANCE.getMatcher(expression);
      }

      public boolean matches(String input) {
         if (input == null) {
            throw new NullPointerException();
         } else {
            return this.exact.matches(input) || this.regexp.matches(input);
         }
      }

      public boolean isExact() {
         return this.regexp.isExact();
      }
   }
}
