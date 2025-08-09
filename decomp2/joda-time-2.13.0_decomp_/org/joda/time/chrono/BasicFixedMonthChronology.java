package org.joda.time.chrono;

import org.joda.time.Chronology;

abstract class BasicFixedMonthChronology extends BasicChronology {
   private static final long serialVersionUID = 261387371998L;
   static final int MONTH_LENGTH = 30;
   static final long MILLIS_PER_YEAR = 31557600000L;
   static final long MILLIS_PER_MONTH = 2592000000L;

   BasicFixedMonthChronology(Chronology var1, Object var2, int var3) {
      super(var1, var2, var3);
   }

   long setYear(long var1, int var3) {
      int var4 = this.getYear(var1);
      int var5 = this.getDayOfYear(var1, var4);
      int var6 = this.getMillisOfDay(var1);
      if (var5 > 365 && !this.isLeapYear(var3)) {
         --var5;
      }

      var1 = this.getYearMonthDayMillis(var3, 1, var5);
      var1 += (long)var6;
      return var1;
   }

   long getYearDifference(long var1, long var3) {
      int var5 = this.getYear(var1);
      int var6 = this.getYear(var3);
      long var7 = var1 - this.getYearMillis(var5);
      long var9 = var3 - this.getYearMillis(var6);
      int var11 = var5 - var6;
      if (var7 < var9) {
         --var11;
      }

      return (long)var11;
   }

   long getTotalMillisByYearMonth(int var1, int var2) {
      return (long)(var2 - 1) * 2592000000L;
   }

   int getDayOfMonth(long var1) {
      return (this.getDayOfYear(var1) - 1) % 30 + 1;
   }

   boolean isLeapYear(int var1) {
      return (var1 & 3) == 3;
   }

   int getDaysInYearMonth(int var1, int var2) {
      return var2 != 13 ? 30 : (this.isLeapYear(var1) ? 6 : 5);
   }

   int getDaysInMonthMax() {
      return 30;
   }

   int getDaysInMonthMax(int var1) {
      return var1 != 13 ? 30 : 6;
   }

   int getMonthOfYear(long var1) {
      return (this.getDayOfYear(var1) - 1) / 30 + 1;
   }

   int getMonthOfYear(long var1, int var3) {
      long var4 = (var1 - this.getYearMillis(var3)) / 2592000000L;
      return (int)var4 + 1;
   }

   int getMaxMonth() {
      return 13;
   }

   long getAverageMillisPerYear() {
      return 31557600000L;
   }

   long getAverageMillisPerYearDividedByTwo() {
      return 15778800000L;
   }

   long getAverageMillisPerMonth() {
      return 2592000000L;
   }
}
