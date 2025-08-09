package com.ibm.icu.impl.number.parse;

public class RequireNumberValidator extends ValidationMatcher {
   public void postProcess(ParsedNumber result) {
      if (!result.seenNumber()) {
         result.flags |= 256;
      }

   }

   public String toString() {
      return "<RequireNumber>";
   }
}
