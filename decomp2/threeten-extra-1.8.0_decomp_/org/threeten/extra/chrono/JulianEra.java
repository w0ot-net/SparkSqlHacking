package org.threeten.extra.chrono;

import java.time.DateTimeException;
import java.time.chrono.Era;

public enum JulianEra implements Era {
   BC,
   AD;

   public static JulianEra of(int era) {
      switch (era) {
         case 0:
            return BC;
         case 1:
            return AD;
         default:
            throw new DateTimeException("Invalid era: " + era);
      }
   }

   public int getValue() {
      return this.ordinal();
   }
}
