package org.threeten.extra.chrono;

import java.time.temporal.ValueRange;

abstract class AbstractNileDate extends AbstractDate {
   abstract int getEpochDayDifference();

   int getDayOfYear() {
      return (this.getMonth() - 1) * 30 + this.getDayOfMonth();
   }

   AbstractDate withDayOfYear(int value) {
      return this.resolvePrevious(this.getProlepticYear(), (value - 1) / 30 + 1, (value - 1) % 30 + 1);
   }

   int lengthOfYearInMonths() {
      return 13;
   }

   ValueRange rangeAlignedWeekOfMonth() {
      return ValueRange.of(1L, this.getMonth() == 13 ? 1L : 5L);
   }

   public int lengthOfMonth() {
      if (this.getMonth() == 13) {
         return this.isLeapYear() ? 6 : 5;
      } else {
         return 30;
      }
   }

   public long toEpochDay() {
      long year = (long)this.getProlepticYear();
      long calendarEpochDay = (year - 1L) * 365L + Math.floorDiv(year, 4L) + (long)(this.getDayOfYear() - 1);
      return calendarEpochDay - (long)this.getEpochDayDifference();
   }
}
