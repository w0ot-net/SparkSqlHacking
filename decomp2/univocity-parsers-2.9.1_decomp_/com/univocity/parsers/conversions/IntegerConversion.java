package com.univocity.parsers.conversions;

public class IntegerConversion extends ObjectConversion {
   public IntegerConversion() {
   }

   public IntegerConversion(Integer valueIfStringIsNull, String valueIfObjectIsNull) {
      super(valueIfStringIsNull, valueIfObjectIsNull);
   }

   protected Integer fromString(String input) {
      return Integer.valueOf(input);
   }
}
