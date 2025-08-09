package com.ibm.icu.impl.number.parse;

import com.ibm.icu.impl.StaticUnicodeSets;
import com.ibm.icu.impl.StringSegment;
import com.ibm.icu.text.UnicodeSet;

public abstract class SymbolMatcher implements NumberParseMatcher {
   protected final String string;
   protected final UnicodeSet uniSet;

   protected SymbolMatcher(String symbolString, UnicodeSet symbolUniSet) {
      this.string = symbolString;
      this.uniSet = symbolUniSet;
   }

   protected SymbolMatcher(StaticUnicodeSets.Key key) {
      this.string = "";
      this.uniSet = StaticUnicodeSets.get(key);
   }

   public UnicodeSet getSet() {
      return this.uniSet;
   }

   public boolean match(StringSegment segment, ParsedNumber result) {
      if (this.isDisabled(result)) {
         return false;
      } else {
         int overlap = 0;
         if (!this.string.isEmpty()) {
            overlap = segment.getCommonPrefixLength(this.string);
            if (overlap == this.string.length()) {
               segment.adjustOffset(this.string.length());
               this.accept(segment, result);
               return false;
            }
         }

         if (segment.startsWith(this.uniSet)) {
            segment.adjustOffsetByCodePoint();
            this.accept(segment, result);
            return false;
         } else {
            return overlap == segment.length();
         }
      }
   }

   public boolean smokeTest(StringSegment segment) {
      return segment.startsWith(this.uniSet) || segment.startsWith((CharSequence)this.string);
   }

   public void postProcess(ParsedNumber result) {
   }

   protected abstract boolean isDisabled(ParsedNumber var1);

   protected abstract void accept(StringSegment var1, ParsedNumber var2);
}
