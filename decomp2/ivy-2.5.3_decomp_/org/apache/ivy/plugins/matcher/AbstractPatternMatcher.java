package org.apache.ivy.plugins.matcher;

public abstract class AbstractPatternMatcher implements PatternMatcher {
   private final String name;

   public AbstractPatternMatcher(String name) {
      this.name = name;
   }

   public Matcher getMatcher(String expression) {
      if (expression == null) {
         throw new NullPointerException();
      } else {
         return "*".equals(expression) ? AnyMatcher.INSTANCE : this.newMatcher(expression);
      }
   }

   public String getName() {
      return this.name;
   }

   protected abstract Matcher newMatcher(String var1);

   public String toString() {
      return this.getName();
   }
}
