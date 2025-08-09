package com.ibm.icu.impl.number.parse;

import com.ibm.icu.impl.StaticUnicodeSets;
import com.ibm.icu.impl.StringSegment;
import com.ibm.icu.text.DecimalFormatSymbols;

public class PlusSignMatcher extends SymbolMatcher {
   private static final PlusSignMatcher DEFAULT = new PlusSignMatcher(false);
   private static final PlusSignMatcher DEFAULT_ALLOW_TRAILING = new PlusSignMatcher(true);
   private final boolean allowTrailing;

   public static PlusSignMatcher getInstance(DecimalFormatSymbols symbols, boolean allowTrailing) {
      String symbolString = symbols.getPlusSignString();
      if (DEFAULT.uniSet.contains(symbolString)) {
         return allowTrailing ? DEFAULT_ALLOW_TRAILING : DEFAULT;
      } else {
         return new PlusSignMatcher(symbolString, allowTrailing);
      }
   }

   private PlusSignMatcher(String symbolString, boolean allowTrailing) {
      super(symbolString, DEFAULT.uniSet);
      this.allowTrailing = allowTrailing;
   }

   private PlusSignMatcher(boolean allowTrailing) {
      super(StaticUnicodeSets.Key.PLUS_SIGN);
      this.allowTrailing = allowTrailing;
   }

   protected boolean isDisabled(ParsedNumber result) {
      return !this.allowTrailing && result.seenNumber();
   }

   protected void accept(StringSegment segment, ParsedNumber result) {
      result.setCharsConsumed(segment);
   }

   public String toString() {
      return "<PlusSignMatcher>";
   }
}
