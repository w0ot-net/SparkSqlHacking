package com.univocity.parsers.conversions;

import com.univocity.parsers.common.ArgumentUtils;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class NullStringConversion implements Conversion {
   private final Set nullStrings = new HashSet();
   private final String defaultNullString;

   public NullStringConversion(String... nullRepresentations) {
      ArgumentUtils.noNulls("Null representation strings", nullRepresentations);
      Collections.addAll(this.nullStrings, nullRepresentations);
      this.defaultNullString = nullRepresentations[0];
   }

   public Object execute(Object input) {
      if (input == null) {
         return null;
      } else {
         return this.nullStrings.contains(String.valueOf(input)) ? null : input;
      }
   }

   public Object revert(Object input) {
      return input == null ? this.defaultNullString : input;
   }
}
