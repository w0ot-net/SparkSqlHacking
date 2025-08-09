package com.univocity.parsers.conversions;

public class LowerCaseConversion implements Conversion {
   public String execute(String input) {
      return input == null ? null : input.toLowerCase();
   }

   public String revert(String input) {
      return this.execute(input);
   }
}
