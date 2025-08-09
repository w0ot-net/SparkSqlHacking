package com.ibm.icu.impl.number.parse;

import com.ibm.icu.impl.StaticUnicodeSets;
import com.ibm.icu.impl.StringSegment;
import com.ibm.icu.text.UnicodeSet;

public class IgnorablesMatcher extends SymbolMatcher implements NumberParseMatcher.Flexible {
   private static final IgnorablesMatcher DEFAULT;
   private static final IgnorablesMatcher STRICT;
   private static final IgnorablesMatcher JAVA_COMPATIBILITY;

   public static IgnorablesMatcher getInstance(int parseFlags) {
      if (0 != (parseFlags & 65536)) {
         return JAVA_COMPATIBILITY;
      } else {
         return 0 != (parseFlags & '耀') ? STRICT : DEFAULT;
      }
   }

   private IgnorablesMatcher(UnicodeSet ignorables) {
      super("", ignorables);
   }

   protected boolean isDisabled(ParsedNumber result) {
      return false;
   }

   protected void accept(StringSegment segment, ParsedNumber result) {
   }

   public String toString() {
      return "<IgnorablesMatcher>";
   }

   static {
      DEFAULT = new IgnorablesMatcher(StaticUnicodeSets.get(StaticUnicodeSets.Key.DEFAULT_IGNORABLES));
      STRICT = new IgnorablesMatcher(StaticUnicodeSets.get(StaticUnicodeSets.Key.STRICT_IGNORABLES));
      JAVA_COMPATIBILITY = new IgnorablesMatcher(StaticUnicodeSets.get(StaticUnicodeSets.Key.EMPTY));
   }
}
