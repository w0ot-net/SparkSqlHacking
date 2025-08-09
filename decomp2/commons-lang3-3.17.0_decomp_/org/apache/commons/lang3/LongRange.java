package org.apache.commons.lang3;

import java.util.Comparator;

public final class LongRange extends NumberRange {
   private static final long serialVersionUID = 1L;

   public static LongRange of(long fromInclusive, long toInclusive) {
      return of(fromInclusive, toInclusive);
   }

   public static LongRange of(Long fromInclusive, Long toInclusive) {
      return new LongRange(fromInclusive, toInclusive);
   }

   private LongRange(Long number1, Long number2) {
      super(number1, number2, (Comparator)null);
   }
}
