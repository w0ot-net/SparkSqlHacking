package com.ibm.icu.impl.number.parse;

import com.ibm.icu.impl.StaticUnicodeSets;
import com.ibm.icu.impl.StringSegment;
import com.ibm.icu.text.DecimalFormatSymbols;

public class PercentMatcher extends SymbolMatcher {
   private static final PercentMatcher DEFAULT = new PercentMatcher();

   public static PercentMatcher getInstance(DecimalFormatSymbols symbols) {
      String symbolString = symbols.getPercentString();
      return DEFAULT.uniSet.contains(symbolString) ? DEFAULT : new PercentMatcher(symbolString);
   }

   private PercentMatcher(String symbolString) {
      super(symbolString, DEFAULT.uniSet);
   }

   private PercentMatcher() {
      super(StaticUnicodeSets.Key.PERCENT_SIGN);
   }

   protected boolean isDisabled(ParsedNumber result) {
      return 0 != (result.flags & 2);
   }

   protected void accept(StringSegment segment, ParsedNumber result) {
      result.flags |= 2;
      result.setCharsConsumed(segment);
   }

   public String toString() {
      return "<PercentMatcher>";
   }
}
