package com.ibm.icu.impl.number.parse;

import com.ibm.icu.impl.StringSegment;
import com.ibm.icu.text.DecimalFormatSymbols;
import com.ibm.icu.text.UnicodeSet;

public class NanMatcher extends SymbolMatcher {
   private static final NanMatcher DEFAULT = new NanMatcher("NaN");

   public static NanMatcher getInstance(DecimalFormatSymbols symbols, int parseFlags) {
      String symbolString = symbols.getNaN();
      return DEFAULT.string.equals(symbolString) ? DEFAULT : new NanMatcher(symbolString);
   }

   private NanMatcher(String symbolString) {
      super(symbolString, UnicodeSet.EMPTY);
   }

   protected boolean isDisabled(ParsedNumber result) {
      return result.seenNumber();
   }

   protected void accept(StringSegment segment, ParsedNumber result) {
      result.flags |= 64;
      result.setCharsConsumed(segment);
   }

   public String toString() {
      return "<NanMatcher>";
   }
}
