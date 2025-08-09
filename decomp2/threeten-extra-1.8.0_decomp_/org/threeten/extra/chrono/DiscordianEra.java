package org.threeten.extra.chrono;

import java.time.DateTimeException;
import java.time.chrono.Era;

public enum DiscordianEra implements Era {
   YOLD;

   public static DiscordianEra of(int era) {
      switch (era) {
         case 1:
            return YOLD;
         default:
            throw new DateTimeException("Invalid era: " + era);
      }
   }

   public int getValue() {
      return this.ordinal();
   }
}
