package org.threeten.extra.chrono;

import java.time.DateTimeException;
import java.time.chrono.Era;

public enum AccountingEra implements Era {
   BCE,
   CE;

   public static AccountingEra of(int era) {
      switch (era) {
         case 0:
            return BCE;
         case 1:
            return CE;
         default:
            throw new DateTimeException("Invalid era: " + era);
      }
   }

   public int getValue() {
      return this.ordinal();
   }
}
