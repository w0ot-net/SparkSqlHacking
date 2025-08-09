package com.ibm.icu.impl.number.parse;

import com.ibm.icu.impl.StaticUnicodeSets;
import com.ibm.icu.impl.StringSegment;
import com.ibm.icu.impl.number.DecimalQuantity_DualStorageBCD;
import com.ibm.icu.impl.number.Grouper;
import com.ibm.icu.text.DecimalFormatSymbols;
import com.ibm.icu.text.UnicodeSet;

public class ScientificMatcher implements NumberParseMatcher {
   private final String exponentSeparatorString;
   private final DecimalMatcher exponentMatcher;
   private final IgnorablesMatcher ignorablesMatcher;
   private final String customMinusSign;
   private final String customPlusSign;

   public static ScientificMatcher getInstance(DecimalFormatSymbols symbols, Grouper grouper) {
      return new ScientificMatcher(symbols, grouper);
   }

   private ScientificMatcher(DecimalFormatSymbols symbols, Grouper grouper) {
      this.exponentSeparatorString = symbols.getExponentSeparator();
      this.exponentMatcher = DecimalMatcher.getInstance(symbols, grouper, 48);
      this.ignorablesMatcher = IgnorablesMatcher.getInstance(32768);
      String minusSign = symbols.getMinusSignString();
      this.customMinusSign = minusSignSet().contains(minusSign) ? null : minusSign;
      String plusSign = symbols.getPlusSignString();
      this.customPlusSign = plusSignSet().contains(plusSign) ? null : plusSign;
   }

   private static UnicodeSet minusSignSet() {
      return StaticUnicodeSets.get(StaticUnicodeSets.Key.MINUS_SIGN);
   }

   private static UnicodeSet plusSignSet() {
      return StaticUnicodeSets.get(StaticUnicodeSets.Key.PLUS_SIGN);
   }

   public boolean match(StringSegment segment, ParsedNumber result) {
      if (!result.seenNumber()) {
         return false;
      } else if (0 != (result.flags & 8)) {
         return false;
      } else {
         int initialOffset = segment.getOffset();
         int overlap = segment.getCommonPrefixLength(this.exponentSeparatorString);
         if (overlap == this.exponentSeparatorString.length()) {
            if (segment.length() == overlap) {
               return true;
            } else {
               segment.adjustOffset(overlap);
               this.ignorablesMatcher.match(segment, (ParsedNumber)null);
               if (segment.length() == 0) {
                  segment.setOffset(initialOffset);
                  return true;
               } else {
                  int exponentSign = 1;
                  if (segment.startsWith(minusSignSet())) {
                     exponentSign = -1;
                     segment.adjustOffsetByCodePoint();
                  } else if (segment.startsWith(plusSignSet())) {
                     segment.adjustOffsetByCodePoint();
                  } else if (segment.startsWith((CharSequence)this.customMinusSign)) {
                     overlap = segment.getCommonPrefixLength(this.customMinusSign);
                     if (overlap != this.customMinusSign.length()) {
                        segment.setOffset(initialOffset);
                        return true;
                     }

                     exponentSign = -1;
                     segment.adjustOffset(overlap);
                  } else if (segment.startsWith((CharSequence)this.customPlusSign)) {
                     overlap = segment.getCommonPrefixLength(this.customPlusSign);
                     if (overlap != this.customPlusSign.length()) {
                        segment.setOffset(initialOffset);
                        return true;
                     }

                     segment.adjustOffset(overlap);
                  }

                  if (segment.length() == 0) {
                     segment.setOffset(initialOffset);
                     return true;
                  } else {
                     this.ignorablesMatcher.match(segment, (ParsedNumber)null);
                     if (segment.length() == 0) {
                        segment.setOffset(initialOffset);
                        return true;
                     } else {
                        boolean wasNull = result.quantity == null;
                        if (wasNull) {
                           result.quantity = new DecimalQuantity_DualStorageBCD();
                        }

                        int digitsOffset = segment.getOffset();
                        boolean digitsReturnValue = this.exponentMatcher.match(segment, result, exponentSign);
                        if (wasNull) {
                           result.quantity = null;
                        }

                        if (segment.getOffset() != digitsOffset) {
                           result.flags |= 8;
                        } else {
                           segment.setOffset(initialOffset);
                        }

                        return digitsReturnValue;
                     }
                  }
               }
            }
         } else {
            return overlap == segment.length();
         }
      }
   }

   public boolean smokeTest(StringSegment segment) {
      return segment.startsWith((CharSequence)this.exponentSeparatorString);
   }

   public void postProcess(ParsedNumber result) {
   }

   public String toString() {
      return "<ScientificMatcher " + this.exponentSeparatorString + ">";
   }
}
