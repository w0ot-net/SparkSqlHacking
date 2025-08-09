package org.joda.time.chrono;

import org.joda.time.Chronology;

abstract class BasicGJChronology extends BasicChronology {
   private static final long serialVersionUID = 538276888268L;
   private static final int[] MIN_DAYS_PER_MONTH_ARRAY = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
   private static final int[] MAX_DAYS_PER_MONTH_ARRAY = new int[]{31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
   private static final long[] MIN_TOTAL_MILLIS_BY_MONTH_ARRAY = new long[12];
   private static final long[] MAX_TOTAL_MILLIS_BY_MONTH_ARRAY = new long[12];
   private static final long FEB_29 = 5097600000L;

   BasicGJChronology(Chronology var1, Object var2, int var3) {
      super(var1, var2, var3);
   }

   boolean isLeapDay(long var1) {
      return this.dayOfMonth().get(var1) == 29 && this.monthOfYear().isLeap(var1);
   }

   int getMonthOfYear(long var1, int var3) {
      int var4 = (int)(var1 - this.getYearMillis(var3) >> 10);
      return this.isLeapYear(var3) ? (var4 < 15356250 ? (var4 < 7678125 ? (var4 < 2615625 ? 1 : (var4 < 5062500 ? 2 : 3)) : (var4 < 10209375 ? 4 : (var4 < 12825000 ? 5 : 6))) : (var4 < 23118750 ? (var4 < 17971875 ? 7 : (var4 < 20587500 ? 8 : 9)) : (var4 < 25734375 ? 10 : (var4 < 28265625 ? 11 : 12)))) : (var4 < 15271875 ? (var4 < 7593750 ? (var4 < 2615625 ? 1 : (var4 < 4978125 ? 2 : 3)) : (var4 < 10125000 ? 4 : (var4 < 12740625 ? 5 : 6))) : (var4 < 23034375 ? (var4 < 17887500 ? 7 : (var4 < 20503125 ? 8 : 9)) : (var4 < 25650000 ? 10 : (var4 < 28181250 ? 11 : 12))));
   }

   int getDaysInYearMonth(int var1, int var2) {
      return this.isLeapYear(var1) ? MAX_DAYS_PER_MONTH_ARRAY[var2 - 1] : MIN_DAYS_PER_MONTH_ARRAY[var2 - 1];
   }

   int getDaysInMonthMax(int var1) {
      return MAX_DAYS_PER_MONTH_ARRAY[var1 - 1];
   }

   int getDaysInMonthMaxForSet(long var1, int var3) {
      return var3 <= 28 && var3 >= 1 ? 28 : this.getDaysInMonthMax(var1);
   }

   long getTotalMillisByYearMonth(int var1, int var2) {
      return this.isLeapYear(var1) ? MAX_TOTAL_MILLIS_BY_MONTH_ARRAY[var2 - 1] : MIN_TOTAL_MILLIS_BY_MONTH_ARRAY[var2 - 1];
   }

   long getYearDifference(long var1, long var3) {
      int var5 = this.getYear(var1);
      int var6 = this.getYear(var3);
      long var7 = var1 - this.getYearMillis(var5);
      long var9 = var3 - this.getYearMillis(var6);
      if (var9 >= 5097600000L) {
         if (this.isLeapYear(var6)) {
            if (!this.isLeapYear(var5)) {
               var9 -= 86400000L;
            }
         } else if (var7 >= 5097600000L && this.isLeapYear(var5)) {
            var7 -= 86400000L;
         }
      }

      int var11 = var5 - var6;
      if (var7 < var9) {
         --var11;
      }

      return (long)var11;
   }

   long setYear(long var1, int var3) {
      int var4 = this.getYear(var1);
      int var5 = this.getDayOfYear(var1, var4);
      int var6 = this.getMillisOfDay(var1);
      if (var5 > 59) {
         if (this.isLeapYear(var4)) {
            if (!this.isLeapYear(var3)) {
               --var5;
            }
         } else if (this.isLeapYear(var3)) {
            ++var5;
         }
      }

      var1 = this.getYearMonthDayMillis(var3, 1, var5);
      var1 += (long)var6;
      return var1;
   }

   static {
      long var0 = 0L;
      long var2 = 0L;

      for(int var4 = 0; var4 < 11; ++var4) {
         long var5 = (long)MIN_DAYS_PER_MONTH_ARRAY[var4] * 86400000L;
         var0 += var5;
         MIN_TOTAL_MILLIS_BY_MONTH_ARRAY[var4 + 1] = var0;
         var5 = (long)MAX_DAYS_PER_MONTH_ARRAY[var4] * 86400000L;
         var2 += var5;
         MAX_TOTAL_MILLIS_BY_MONTH_ARRAY[var4 + 1] = var2;
      }

   }
}
