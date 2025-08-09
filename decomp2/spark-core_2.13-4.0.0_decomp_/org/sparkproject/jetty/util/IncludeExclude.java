package org.sparkproject.jetty.util;

import java.util.Set;
import java.util.function.Predicate;

public class IncludeExclude extends IncludeExcludeSet {
   public IncludeExclude() {
   }

   public IncludeExclude(Class setClass) {
      super(setClass);
   }

   public IncludeExclude(Set includeSet, Predicate includePredicate, Set excludeSet, Predicate excludePredicate) {
      super(includeSet, includePredicate, excludeSet, excludePredicate);
   }
}
