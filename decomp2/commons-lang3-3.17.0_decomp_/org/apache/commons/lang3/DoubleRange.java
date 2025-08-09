package org.apache.commons.lang3;

import java.util.Comparator;

public final class DoubleRange extends NumberRange {
   private static final long serialVersionUID = 1L;

   public static DoubleRange of(double fromInclusive, double toInclusive) {
      return of(fromInclusive, toInclusive);
   }

   public static DoubleRange of(Double fromInclusive, Double toInclusive) {
      return new DoubleRange(fromInclusive, toInclusive);
   }

   private DoubleRange(Double number1, Double number2) {
      super(number1, number2, (Comparator)null);
   }
}
