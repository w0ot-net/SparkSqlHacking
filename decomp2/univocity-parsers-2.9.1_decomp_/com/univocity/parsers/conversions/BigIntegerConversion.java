package com.univocity.parsers.conversions;

import java.math.BigInteger;

public class BigIntegerConversion extends ObjectConversion {
   public BigIntegerConversion() {
   }

   public BigIntegerConversion(BigInteger valueIfStringIsNull, String valueIfObjectIsNull) {
      super(valueIfStringIsNull, valueIfObjectIsNull);
   }

   protected BigInteger fromString(String input) {
      return new BigInteger(input);
   }
}
