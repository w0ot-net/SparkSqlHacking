package com.univocity.parsers.conversions;

import java.math.BigDecimal;

public class BigDecimalConversion extends ObjectConversion {
   public BigDecimalConversion() {
   }

   public BigDecimalConversion(BigDecimal valueIfStringIsNull, String valueIfObjectIsNull) {
      super(valueIfStringIsNull, valueIfObjectIsNull);
   }

   protected BigDecimal fromString(String input) {
      return new BigDecimal(input);
   }
}
