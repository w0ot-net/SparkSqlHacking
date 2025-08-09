package com.univocity.parsers.conversions;

public class LongConversion extends ObjectConversion {
   public LongConversion() {
   }

   public LongConversion(Long valueIfStringIsNull, String valueIfObjectIsNull) {
      super(valueIfStringIsNull, valueIfObjectIsNull);
   }

   protected Long fromString(String input) {
      return Long.valueOf(input);
   }
}
