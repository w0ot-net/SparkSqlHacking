package com.ibm.icu.impl.number.parse;

import com.ibm.icu.impl.StaticUnicodeSets;
import com.ibm.icu.impl.StringSegment;
import com.ibm.icu.text.DecimalFormatSymbols;

public class PermilleMatcher extends SymbolMatcher {
   private static final PermilleMatcher DEFAULT = new PermilleMatcher();

   public static PermilleMatcher getInstance(DecimalFormatSymbols symbols) {
      String symbolString = symbols.getPerMillString();
      return DEFAULT.uniSet.contains(symbolString) ? DEFAULT : new PermilleMatcher(symbolString);
   }

   private PermilleMatcher(String symbolString) {
      super(symbolString, DEFAULT.uniSet);
   }

   private PermilleMatcher() {
      super(StaticUnicodeSets.Key.PERMILLE_SIGN);
   }

   protected boolean isDisabled(ParsedNumber result) {
      return 0 != (result.flags & 4);
   }

   protected void accept(StringSegment segment, ParsedNumber result) {
      result.flags |= 4;
      result.setCharsConsumed(segment);
   }

   public String toString() {
      return "<PermilleMatcher>";
   }
}
