package org.threeten.extra.chrono;

import java.time.DateTimeException;
import java.time.chrono.Era;

public enum EthiopicEra implements Era {
   BEFORE_INCARNATION,
   INCARNATION;

   public static EthiopicEra of(int era) {
      switch (era) {
         case 0:
            return BEFORE_INCARNATION;
         case 1:
            return INCARNATION;
         default:
            throw new DateTimeException("Invalid era: " + era);
      }
   }

   public int getValue() {
      return this.ordinal();
   }
}
