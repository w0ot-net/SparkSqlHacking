package com.univocity.parsers.conversions;

public class DoubleConversion extends ObjectConversion {
   public DoubleConversion() {
   }

   public DoubleConversion(Double valueIfStringIsNull, String valueIfObjectIsNull) {
      super(valueIfStringIsNull, valueIfObjectIsNull);
   }

   protected Double fromString(String input) {
      return Double.valueOf(input);
   }
}
