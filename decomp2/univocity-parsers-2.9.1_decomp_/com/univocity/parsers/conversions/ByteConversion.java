package com.univocity.parsers.conversions;

public class ByteConversion extends ObjectConversion {
   public ByteConversion() {
   }

   public ByteConversion(Byte valueIfStringIsNull, String valueIfObjectIsNull) {
      super(valueIfStringIsNull, valueIfObjectIsNull);
   }

   protected Byte fromString(String input) {
      return Byte.valueOf(input);
   }
}
