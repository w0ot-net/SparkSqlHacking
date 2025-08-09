package org.joda.time.chrono;

import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;
import org.joda.time.Chronology;
import org.joda.time.DateTime;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeZone;
import org.joda.time.ReadableDateTime;

public final class IslamicChronology extends BasicChronology {
   private static final long serialVersionUID = -3663823829888L;
   public static final int AH = 1;
   private static final DateTimeField ERA_FIELD = new BasicSingleEraDateTimeField("AH");
   public static final LeapYearPatternType LEAP_YEAR_15_BASED = new LeapYearPatternType(0, 623158436);
   public static final LeapYearPatternType LEAP_YEAR_16_BASED = new LeapYearPatternType(1, 623191204);
   public static final LeapYearPatternType LEAP_YEAR_INDIAN = new LeapYearPatternType(2, 690562340);
   public static final LeapYearPatternType LEAP_YEAR_HABASH_AL_HASIB = new LeapYearPatternType(3, 153692453);
   private static final int MIN_YEAR = -292269337;
   private static final int MAX_YEAR = 292271022;
   private static final int MONTH_PAIR_LENGTH = 59;
   private static final int LONG_MONTH_LENGTH = 30;
   private static final int SHORT_MONTH_LENGTH = 29;
   private static final long MILLIS_PER_MONTH_PAIR = 5097600000L;
   private static final long MILLIS_PER_MONTH = 2551440384L;
   private static final long MILLIS_PER_LONG_MONTH = 2592000000L;
   private static final long MILLIS_PER_YEAR = 30617280288L;
   private static final long MILLIS_PER_SHORT_YEAR = 30585600000L;
   private static final long MILLIS_PER_LONG_YEAR = 30672000000L;
   private static final long MILLIS_YEAR_1 = -42521587200000L;
   private static final int CYCLE = 30;
   private static final long MILLIS_PER_CYCLE = 918518400000L;
   private static final ConcurrentHashMap cCache = new ConcurrentHashMap();
   private static final IslamicChronology INSTANCE_UTC;
   private final LeapYearPatternType iLeapYears;

   public static IslamicChronology getInstanceUTC() {
      return INSTANCE_UTC;
   }

   public static IslamicChronology getInstance() {
      return getInstance(DateTimeZone.getDefault(), LEAP_YEAR_16_BASED);
   }

   public static IslamicChronology getInstance(DateTimeZone var0) {
      return getInstance(var0, LEAP_YEAR_16_BASED);
   }

   public static IslamicChronology getInstance(DateTimeZone var0, LeapYearPatternType var1) {
      if (var0 == null) {
         var0 = DateTimeZone.getDefault();
      }

      IslamicChronology[] var3 = (IslamicChronology[])cCache.get(var0);
      if (var3 == null) {
         var3 = new IslamicChronology[4];
         IslamicChronology[] var4 = (IslamicChronology[])cCache.putIfAbsent(var0, var3);
         if (var4 != null) {
            var3 = var4;
         }
      }

      IslamicChronology var2 = var3[var1.index];
      if (var2 == null) {
         synchronized(var3) {
            var2 = var3[var1.index];
            if (var2 == null) {
               if (var0 == DateTimeZone.UTC) {
                  var2 = new IslamicChronology((Chronology)null, (Object)null, var1);
                  DateTime var5 = new DateTime(1, 1, 1, 0, 0, 0, 0, var2);
                  var2 = new IslamicChronology(LimitChronology.getInstance(var2, var5, (ReadableDateTime)null), (Object)null, var1);
               } else {
                  var2 = getInstance(DateTimeZone.UTC, var1);
                  var2 = new IslamicChronology(ZonedChronology.getInstance(var2, var0), (Object)null, var1);
               }

               var3[var1.index] = var2;
            }
         }
      }

      return var2;
   }

   IslamicChronology(Chronology var1, Object var2, LeapYearPatternType var3) {
      super(var1, var2, 4);
      this.iLeapYears = var3;
   }

   private Object readResolve() {
      Chronology var1 = this.getBase();
      return var1 == null ? getInstanceUTC() : getInstance(var1.getZone());
   }

   public LeapYearPatternType getLeapYearPatternType() {
      return this.iLeapYears;
   }

   public Chronology withUTC() {
      return INSTANCE_UTC;
   }

   public Chronology withZone(DateTimeZone var1) {
      if (var1 == null) {
         var1 = DateTimeZone.getDefault();
      }

      return var1 == this.getZone() ? this : getInstance(var1);
   }

   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof IslamicChronology)) {
         return false;
      } else {
         IslamicChronology var2 = (IslamicChronology)var1;
         return this.getLeapYearPatternType().index == var2.getLeapYearPatternType().index && super.equals(var1);
      }
   }

   public int hashCode() {
      return super.hashCode() * 13 + this.getLeapYearPatternType().hashCode();
   }

   int getYear(long var1) {
      long var3 = var1 - -42521587200000L;
      long var5 = var3 / 918518400000L;
      long var7 = var3 % 918518400000L;
      int var9 = (int)(var5 * 30L + 1L);

      for(long var10 = this.isLeapYear(var9) ? 30672000000L : 30585600000L; var7 >= var10; var10 = this.isLeapYear(var9) ? 30672000000L : 30585600000L) {
         var7 -= var10;
         ++var9;
      }

      return var9;
   }

   long setYear(long var1, int var3) {
      int var4 = this.getYear(var1);
      int var5 = this.getDayOfYear(var1, var4);
      int var6 = this.getMillisOfDay(var1);
      if (var5 > 354 && !this.isLeapYear(var3)) {
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
      --var2;
      if (var2 % 2 == 1) {
         var2 /= 2;
         return (long)var2 * 5097600000L + 2592000000L;
      } else {
         var2 /= 2;
         return (long)var2 * 5097600000L;
      }
   }

   int getDayOfMonth(long var1) {
      int var3 = this.getDayOfYear(var1) - 1;
      return var3 == 354 ? 30 : var3 % 59 % 30 + 1;
   }

   boolean isLeapYear(int var1) {
      return this.iLeapYears.isLeapYear(var1);
   }

   int getDaysInYearMax() {
      return 355;
   }

   int getDaysInYear(int var1) {
      return this.isLeapYear(var1) ? 355 : 354;
   }

   int getDaysInYearMonth(int var1, int var2) {
      if (var2 == 12 && this.isLeapYear(var1)) {
         return 30;
      } else {
         --var2;
         return var2 % 2 == 0 ? 30 : 29;
      }
   }

   int getDaysInMonthMax() {
      return 30;
   }

   int getDaysInMonthMax(int var1) {
      if (var1 == 12) {
         return 30;
      } else {
         --var1;
         return var1 % 2 == 0 ? 30 : 29;
      }
   }

   int getMonthOfYear(long var1, int var3) {
      int var4 = (int)((var1 - this.getYearMillis(var3)) / 86400000L);
      return var4 == 354 ? 12 : var4 * 2 / 59 + 1;
   }

   long getAverageMillisPerYear() {
      return 30617280288L;
   }

   long getAverageMillisPerYearDividedByTwo() {
      return 15308640144L;
   }

   long getAverageMillisPerMonth() {
      return 2551440384L;
   }

   long calculateFirstDayOfYearMillis(int var1) {
      if (var1 > 292271022) {
         throw new ArithmeticException("Year is too large: " + var1 + " > " + 292271022);
      } else if (var1 < -292269337) {
         throw new ArithmeticException("Year is too small: " + var1 + " < " + -292269337);
      } else {
         --var1;
         long var2 = (long)(var1 / 30);
         long var4 = -42521587200000L + var2 * 918518400000L;
         int var6 = var1 % 30 + 1;

         for(int var7 = 1; var7 < var6; ++var7) {
            var4 += this.isLeapYear(var7) ? 30672000000L : 30585600000L;
         }

         return var4;
      }
   }

   int getMinYear() {
      return 1;
   }

   int getMaxYear() {
      return 292271022;
   }

   long getApproxMillisAtEpochDividedByTwo() {
      return 21260793600000L;
   }

   protected void assemble(AssembledChronology.Fields var1) {
      if (this.getBase() == null) {
         super.assemble(var1);
         var1.era = ERA_FIELD;
         var1.monthOfYear = new BasicMonthOfYearDateTimeField(this, 12);
         var1.months = var1.monthOfYear.getDurationField();
      }

   }

   static {
      INSTANCE_UTC = getInstance(DateTimeZone.UTC);
   }

   public static class LeapYearPatternType implements Serializable {
      private static final long serialVersionUID = 26581275372698L;
      final byte index;
      final int pattern;

      LeapYearPatternType(int var1, int var2) {
         this.index = (byte)var1;
         this.pattern = var2;
      }

      boolean isLeapYear(int var1) {
         int var2 = 1 << var1 % 30;
         return (this.pattern & var2) > 0;
      }

      private Object readResolve() {
         switch (this.index) {
            case 0:
               return IslamicChronology.LEAP_YEAR_15_BASED;
            case 1:
               return IslamicChronology.LEAP_YEAR_16_BASED;
            case 2:
               return IslamicChronology.LEAP_YEAR_INDIAN;
            case 3:
               return IslamicChronology.LEAP_YEAR_HABASH_AL_HASIB;
            default:
               return this;
         }
      }

      public boolean equals(Object var1) {
         if (var1 instanceof LeapYearPatternType) {
            return this.index == ((LeapYearPatternType)var1).index;
         } else {
            return false;
         }
      }

      public int hashCode() {
         return this.index;
      }
   }
}
