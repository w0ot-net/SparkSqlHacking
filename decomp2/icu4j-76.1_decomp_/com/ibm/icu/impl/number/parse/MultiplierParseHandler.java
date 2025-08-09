package com.ibm.icu.impl.number.parse;

import com.ibm.icu.number.Scale;

public class MultiplierParseHandler extends ValidationMatcher {
   private final Scale multiplier;

   public MultiplierParseHandler(Scale multiplier) {
      this.multiplier = multiplier;
   }

   public void postProcess(ParsedNumber result) {
      if (result.quantity != null) {
         this.multiplier.applyReciprocalTo(result.quantity);
      }

   }

   public String toString() {
      return "<MultiplierHandler " + this.multiplier + ">";
   }
}
