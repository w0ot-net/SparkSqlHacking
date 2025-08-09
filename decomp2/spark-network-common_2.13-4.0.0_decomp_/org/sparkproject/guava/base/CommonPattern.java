package org.sparkproject.guava.base;

import org.sparkproject.guava.annotations.GwtCompatible;

@ElementTypesAreNonnullByDefault
@GwtCompatible
abstract class CommonPattern {
   public abstract CommonMatcher matcher(CharSequence t);

   public abstract String pattern();

   public abstract int flags();

   public abstract String toString();

   public static CommonPattern compile(String pattern) {
      return Platform.compilePattern(pattern);
   }

   public static boolean isPcreLike() {
      return Platform.patternCompilerIsPcreLike();
   }
}
