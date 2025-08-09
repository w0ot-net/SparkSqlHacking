package com.ibm.icu.impl.number.parse;

public class RequireCurrencyValidator extends ValidationMatcher {
   public void postProcess(ParsedNumber result) {
      if (result.currencyCode == null) {
         result.flags |= 256;
      }

   }

   public String toString() {
      return "<RequireCurrency>";
   }
}
