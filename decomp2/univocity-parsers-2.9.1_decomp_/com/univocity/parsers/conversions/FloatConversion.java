package com.univocity.parsers.conversions;

public class FloatConversion extends ObjectConversion {
   public FloatConversion() {
   }

   public FloatConversion(Float valueIfStringIsNull, String valueIfObjectIsNull) {
      super(valueIfStringIsNull, valueIfObjectIsNull);
   }

   protected Float fromString(String input) {
      return Float.valueOf(input);
   }
}
