package org.apache.commons.lang3;

import java.util.Comparator;

public class NumberRange extends Range {
   private static final long serialVersionUID = 1L;

   public NumberRange(Number number1, Number number2, Comparator comp) {
      super(number1, number2, comp);
   }
}
