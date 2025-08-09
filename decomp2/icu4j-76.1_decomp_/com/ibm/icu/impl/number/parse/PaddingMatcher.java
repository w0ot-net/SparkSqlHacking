package com.ibm.icu.impl.number.parse;

import com.ibm.icu.impl.StringSegment;
import com.ibm.icu.text.UnicodeSet;

public class PaddingMatcher extends SymbolMatcher implements NumberParseMatcher.Flexible {
   public static PaddingMatcher getInstance(String padString) {
      return new PaddingMatcher(padString);
   }

   private PaddingMatcher(String symbolString) {
      super(symbolString, UnicodeSet.EMPTY);
   }

   protected boolean isDisabled(ParsedNumber result) {
      return false;
   }

   protected void accept(StringSegment segment, ParsedNumber result) {
   }

   public String toString() {
      return "<PaddingMatcher>";
   }
}
