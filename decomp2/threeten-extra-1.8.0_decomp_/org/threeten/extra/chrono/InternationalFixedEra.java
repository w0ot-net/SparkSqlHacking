package org.threeten.extra.chrono;

import java.time.DateTimeException;
import java.time.chrono.Era;

public enum InternationalFixedEra implements Era {
   CE;

   public static InternationalFixedEra of(int era) {
      if (era == 1) {
         return CE;
      } else {
         throw new DateTimeException("Invalid era: " + era);
      }
   }

   public int getValue() {
      return 1;
   }
}
