package com.univocity.parsers.conversions;

public class UpperCaseConversion implements Conversion {
   public String execute(String input) {
      return input == null ? null : input.toUpperCase();
   }

   public String revert(String input) {
      return this.execute(input);
   }
}
