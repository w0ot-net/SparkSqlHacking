package org.threeten.extra.chrono;

import java.time.chrono.AbstractChronology;
import java.time.temporal.ChronoField;
import java.time.temporal.ValueRange;

abstract class AbstractNileChronology extends AbstractChronology {
   static final ValueRange YEAR_RANGE = ValueRange.of(-999998L, 999999L);
   static final ValueRange YOE_RANGE = ValueRange.of(1L, 999999L);
   static final ValueRange PROLEPTIC_MONTH_RANGE = ValueRange.of(-12999974L, 12999999L);
   static final ValueRange MOY_RANGE = ValueRange.of(1L, 13L);
   static final ValueRange ALIGNED_WOM_RANGE = ValueRange.of(1L, 1L, 5L);
   static final ValueRange DOM_RANGE = ValueRange.of(1L, 5L, 30L);
   static final ValueRange DOM_RANGE_NONLEAP = ValueRange.of(1L, 5L);
   static final ValueRange DOM_RANGE_LEAP = ValueRange.of(1L, 6L);

   public boolean isLeapYear(long prolepticYear) {
      return Math.floorMod(prolepticYear, 4L) == 3L;
   }

   public ValueRange range(ChronoField field) {
      switch (field) {
         case DAY_OF_MONTH:
            return DOM_RANGE;
         case ALIGNED_WEEK_OF_MONTH:
            return ALIGNED_WOM_RANGE;
         case MONTH_OF_YEAR:
            return MOY_RANGE;
         case PROLEPTIC_MONTH:
            return PROLEPTIC_MONTH_RANGE;
         case YEAR_OF_ERA:
            return YOE_RANGE;
         case YEAR:
            return YEAR_RANGE;
         default:
            return field.range();
      }
   }
}
