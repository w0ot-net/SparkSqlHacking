package org.apache.ivy.plugins.matcher;

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public final class RegexpPatternMatcher extends AbstractPatternMatcher {
   public static final RegexpPatternMatcher INSTANCE = new RegexpPatternMatcher();

   public RegexpPatternMatcher() {
      super("regexp");
   }

   protected Matcher newMatcher(String expression) {
      return new RegexpMatcher(expression);
   }

   private static class RegexpMatcher implements Matcher {
      private Pattern pattern;
      private String expression;
      private Boolean exact;

      public RegexpMatcher(String expression) throws PatternSyntaxException {
         if (expression == null) {
            throw new NullPointerException();
         } else {
            this.expression = expression;
            this.pattern = Pattern.compile(expression);
         }
      }

      public boolean matches(String input) {
         if (input == null) {
            throw new NullPointerException();
         } else {
            return this.pattern.matcher(input).matches();
         }
      }

      public boolean isExact() {
         if (this.exact == null) {
            this.exact = this.calculateExact();
         }

         return this.exact;
      }

      private Boolean calculateExact() {
         Boolean result = Boolean.TRUE;

         for(char ch : this.expression.toCharArray()) {
            if (!Character.isLetterOrDigit(ch) && !Character.isWhitespace(ch) && '-' != ch && '_' != ch) {
               result = Boolean.FALSE;
               break;
            }
         }

         return result;
      }
   }
}
