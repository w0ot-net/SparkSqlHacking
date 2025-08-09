package org.apache.ivy.plugins.matcher;

import java.util.regex.PatternSyntaxException;
import org.apache.oro.text.GlobCompiler;
import org.apache.oro.text.regex.MalformedPatternException;
import org.apache.oro.text.regex.Pattern;
import org.apache.oro.text.regex.Perl5Matcher;

public final class GlobPatternMatcher extends AbstractPatternMatcher {
   public static final GlobPatternMatcher INSTANCE = new GlobPatternMatcher();

   public GlobPatternMatcher() {
      super("glob");
   }

   protected Matcher newMatcher(String expression) {
      return new GlobMatcher(expression);
   }

   private static class GlobMatcher implements Matcher {
      private Pattern pattern;
      private String expression;
      private Boolean exact;

      public GlobMatcher(String expression) throws PatternSyntaxException {
         this.expression = expression;

         try {
            this.pattern = (new GlobCompiler()).compile(expression);
         } catch (MalformedPatternException e) {
            throw new PatternSyntaxException(e.getMessage(), expression, 0);
         }
      }

      public boolean matches(String input) {
         if (input == null) {
            throw new NullPointerException();
         } else {
            return (new Perl5Matcher()).matches(input, this.pattern);
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
            if (ch == '*' || ch == '?' || ch == '[' || ch == ']') {
               result = Boolean.FALSE;
               break;
            }
         }

         return result;
      }
   }
}
