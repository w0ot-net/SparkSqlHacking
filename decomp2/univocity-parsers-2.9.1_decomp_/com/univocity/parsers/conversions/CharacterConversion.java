package com.univocity.parsers.conversions;

import com.univocity.parsers.common.DataProcessingException;

public class CharacterConversion extends ObjectConversion {
   public CharacterConversion() {
   }

   public CharacterConversion(Character valueIfStringIsNull, String valueIfObjectIsNull) {
      super(valueIfStringIsNull, valueIfObjectIsNull);
   }

   protected Character fromString(String input) {
      if (input.length() != 1) {
         DataProcessingException exception = new DataProcessingException("'{value}' is not a character");
         exception.setValue(input);
         throw exception;
      } else {
         return input.charAt(0);
      }
   }
}
