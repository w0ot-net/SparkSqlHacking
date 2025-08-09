package com.univocity.parsers.conversions;

public class ShortConversion extends ObjectConversion {
   public ShortConversion() {
   }

   public ShortConversion(Short valueIfStringIsNull, String valueIfObjectIsNull) {
      super(valueIfStringIsNull, valueIfObjectIsNull);
   }

   protected Short fromString(String input) {
      return Short.valueOf(input);
   }
}
