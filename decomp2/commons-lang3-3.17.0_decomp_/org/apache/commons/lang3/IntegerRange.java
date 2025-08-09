package org.apache.commons.lang3;

import java.util.Comparator;

public final class IntegerRange extends NumberRange {
   private static final long serialVersionUID = 1L;

   public static IntegerRange of(int fromInclusive, int toInclusive) {
      return of(fromInclusive, toInclusive);
   }

   public static IntegerRange of(Integer fromInclusive, Integer toInclusive) {
      return new IntegerRange(fromInclusive, toInclusive);
   }

   private IntegerRange(Integer number1, Integer number2) {
      super(number1, number2, (Comparator)null);
   }
}
