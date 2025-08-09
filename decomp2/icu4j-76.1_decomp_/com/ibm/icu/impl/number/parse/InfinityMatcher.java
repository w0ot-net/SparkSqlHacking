package com.ibm.icu.impl.number.parse;

import com.ibm.icu.impl.StaticUnicodeSets;
import com.ibm.icu.impl.StringSegment;
import com.ibm.icu.text.DecimalFormatSymbols;

public class InfinityMatcher extends SymbolMatcher {
   private static final InfinityMatcher DEFAULT = new InfinityMatcher();

   public static InfinityMatcher getInstance(DecimalFormatSymbols symbols) {
      String symbolString = symbols.getInfinity();
      return DEFAULT.uniSet.contains(symbolString) ? DEFAULT : new InfinityMatcher(symbolString);
   }

   private InfinityMatcher(String symbolString) {
      super(symbolString, DEFAULT.uniSet);
   }

   private InfinityMatcher() {
      super(StaticUnicodeSets.Key.INFINITY_SIGN);
   }

   protected boolean isDisabled(ParsedNumber result) {
      return 0 != (result.flags & 128);
   }

   protected void accept(StringSegment segment, ParsedNumber result) {
      result.flags |= 128;
      result.setCharsConsumed(segment);
   }

   public String toString() {
      return "<InfinityMatcher>";
   }
}
