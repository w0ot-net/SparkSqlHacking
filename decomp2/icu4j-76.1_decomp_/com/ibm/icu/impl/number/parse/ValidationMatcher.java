package com.ibm.icu.impl.number.parse;

import com.ibm.icu.impl.StringSegment;

public abstract class ValidationMatcher implements NumberParseMatcher {
   public boolean match(StringSegment segment, ParsedNumber result) {
      return false;
   }

   public boolean smokeTest(StringSegment segment) {
      return false;
   }
}
