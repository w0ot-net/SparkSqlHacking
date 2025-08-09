package com.univocity.parsers.conversions;

public class RegexConversion implements Conversion {
   private final String replaceRegex;
   private final String replacement;

   public RegexConversion(String replaceRegex, String replacement) {
      this.replaceRegex = replaceRegex;
      this.replacement = replacement;
   }

   public String execute(String input) {
      return input == null ? null : input.replaceAll(this.replaceRegex, this.replacement);
   }

   public String revert(String input) {
      return this.execute(input);
   }
}
