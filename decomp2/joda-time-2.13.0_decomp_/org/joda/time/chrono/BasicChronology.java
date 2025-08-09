package org.joda.time.chrono;

import java.util.Locale;
import org.joda.time.Chronology;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DateTimeZone;
import org.joda.time.DurationField;
import org.joda.time.DurationFieldType;
import org.joda.time.IllegalFieldValueException;
import org.joda.time.field.DividedDateTimeField;
import org.joda.time.field.FieldUtils;
import org.joda.time.field.MillisDurationField;
import org.joda.time.field.OffsetDateTimeField;
import org.joda.time.field.PreciseDateTimeField;
import org.joda.time.field.PreciseDurationField;
import org.joda.time.field.RemainderDateTimeField;
import org.joda.time.field.ZeroIsMaxDateTimeField;

abstract class BasicChronology extends AssembledChronology {
   private static final long serialVersionUID = 8283225332206808863L;
   private static final DurationField cMillisField;
   private static final DurationField cSecondsField;
   private static final DurationField cMinutesField;
   private static final DurationField cHoursField;
   private static final DurationField cHalfdaysField;
   private static final DurationField cDaysField;
   private static final DurationField cWeeksField;
   private static final DateTimeField cMillisOfSecondField;
   private static final DateTimeField cMillisOfDayField;
   private static final DateTimeField cSecondOfMinuteField;
   private static final DateTimeField cSecondOfDayField;
   private static final DateTimeField cMinuteOfHourField;
   private static final DateTimeField cMinuteOfDayField;
   private static final DateTimeField cHourOfDayField;
   private static final DateTimeField cHourOfHalfdayField;
   private static final DateTimeField cClockhourOfDayField;
   private static final DateTimeField cClockhourOfHalfdayField;
   private static final DateTimeField cHalfdayOfDayField;
   private static final int CACHE_SIZE = 1024;
   private static final int CACHE_MASK = 1023;
   private final transient YearInfo[] iYearInfoCache = new YearInfo[1024];
   private final int iMinDaysInFirstWeek;

   BasicChronology(Chronology var1, Object var2, int var3) {
      super(var1, var2);
      if (var3 >= 1 && var3 <= 7) {
         this.iMinDaysInFirstWeek = var3;
      } else {
         throw new IllegalArgumentException("Invalid min days in first week: " + var3);
      }
   }

   public DateTimeZone getZone() {
      Chronology var1;
      return (var1 = this.getBase()) != null ? var1.getZone() : DateTimeZone.UTC;
   }

   public long getDateTimeMillis(int var1, int var2, int var3, int var4) throws IllegalArgumentException {
      Chronology var5;
      if ((var5 = this.getBase()) != null) {
         return var5.getDateTimeMillis(var1, var2, var3, var4);
      } else {
         FieldUtils.verifyValueBounds((DateTimeFieldType)DateTimeFieldType.millisOfDay(), var4, 0, 86399999);
         return this.getDateTimeMillis0(var1, var2, var3, var4);
      }
   }

   public long getDateTimeMillis(int var1, int var2, int var3, int var4, int var5, int var6, int var7) throws IllegalArgumentException {
      Chronology var8;
      if ((var8 = this.getBase()) != null) {
         return var8.getDateTimeMillis(var1, var2, var3, var4, var5, var6, var7);
      } else {
         FieldUtils.verifyValueBounds((DateTimeFieldType)DateTimeFieldType.hourOfDay(), var4, 0, 23);
         FieldUtils.verifyValueBounds((DateTimeFieldType)DateTimeFieldType.minuteOfHour(), var5, 0, 59);
         FieldUtils.verifyValueBounds((DateTimeFieldType)DateTimeFieldType.secondOfMinute(), var6, 0, 59);
         FieldUtils.verifyValueBounds((DateTimeFieldType)DateTimeFieldType.millisOfSecond(), var7, 0, 999);
         long var9 = (long)var4 * 3600000L + (long)var5 * 60000L + (long)var6 * 1000L + (long)var7;
         return this.getDateTimeMillis0(var1, var2, var3, (int)var9);
      }
   }

   private long getDateTimeMillis0(int var1, int var2, int var3, int var4) {
      long var5 = this.getDateMidnightMillis(var1, var2, var3);
      if (var5 == Long.MIN_VALUE) {
         var5 = this.getDateMidnightMillis(var1, var2, var3 + 1);
         var4 -= 86400000;
      }

      long var7 = var5 + (long)var4;
      if (var7 < 0L && var5 > 0L) {
         return Long.MAX_VALUE;
      } else {
         return var7 > 0L && var5 < 0L ? Long.MIN_VALUE : var7;
      }
   }

   public int getMinimumDaysInFirstWeek() {
      return this.iMinDaysInFirstWeek;
   }

   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 != null && this.getClass() == var1.getClass()) {
         BasicChronology var2 = (BasicChronology)var1;
         return this.getMinimumDaysInFirstWeek() == var2.getMinimumDaysInFirstWeek() && this.getZone().equals(var2.getZone());
      } else {
         return false;
      }
   }

   public int hashCode() {
      return this.getClass().getName().hashCode() * 11 + this.getZone().hashCode() + this.getMinimumDaysInFirstWeek();
   }

   public String toString() {
      StringBuilder var1 = new StringBuilder(60);
      String var2 = this.getClass().getName();
      int var3 = var2.lastIndexOf(46);
      if (var3 >= 0) {
         var2 = var2.substring(var3 + 1);
      }

      var1.append(var2);
      var1.append('[');
      DateTimeZone var4 = this.getZone();
      if (var4 != null) {
         var1.append(var4.getID());
      }

      if (this.getMinimumDaysInFirstWeek() != 4) {
         var1.append(",mdfw=");
         var1.append(this.getMinimumDaysInFirstWeek());
      }

      var1.append(']');
      return var1.toString();
   }

   protected void assemble(AssembledChronology.Fields var1) {
      var1.millis = cMillisField;
      var1.seconds = cSecondsField;
      var1.minutes = cMinutesField;
      var1.hours = cHoursField;
      var1.halfdays = cHalfdaysField;
      var1.days = cDaysField;
      var1.weeks = cWeeksField;
      var1.millisOfSecond = cMillisOfSecondField;
      var1.millisOfDay = cMillisOfDayField;
      var1.secondOfMinute = cSecondOfMinuteField;
      var1.secondOfDay = cSecondOfDayField;
      var1.minuteOfHour = cMinuteOfHourField;
      var1.minuteOfDay = cMinuteOfDayField;
      var1.hourOfDay = cHourOfDayField;
      var1.hourOfHalfday = cHourOfHalfdayField;
      var1.clockhourOfDay = cClockhourOfDayField;
      var1.clockhourOfHalfday = cClockhourOfHalfdayField;
      var1.halfdayOfDay = cHalfdayOfDayField;
      var1.year = new BasicYearDateTimeField(this);
      var1.yearOfEra = new GJYearOfEraDateTimeField(var1.year, this);
      OffsetDateTimeField var2 = new OffsetDateTimeField(var1.yearOfEra, 99);
      var1.centuryOfEra = new DividedDateTimeField(var2, DateTimeFieldType.centuryOfEra(), 100);
      var1.centuries = var1.centuryOfEra.getDurationField();
      RemainderDateTimeField var3 = new RemainderDateTimeField((DividedDateTimeField)var1.centuryOfEra);
      var1.yearOfCentury = new OffsetDateTimeField(var3, DateTimeFieldType.yearOfCentury(), 1);
      var1.era = new GJEraDateTimeField(this);
      var1.dayOfWeek = new GJDayOfWeekDateTimeField(this, var1.days);
      var1.dayOfMonth = new BasicDayOfMonthDateTimeField(this, var1.days);
      var1.dayOfYear = new BasicDayOfYearDateTimeField(this, var1.days);
      var1.monthOfYear = new GJMonthOfYearDateTimeField(this);
      var1.weekyear = new BasicWeekyearDateTimeField(this);
      var1.weekOfWeekyear = new BasicWeekOfWeekyearDateTimeField(this, var1.weeks);
      var3 = new RemainderDateTimeField(var1.weekyear, var1.centuries, DateTimeFieldType.weekyearOfCentury(), 100);
      var1.weekyearOfCentury = new OffsetDateTimeField(var3, DateTimeFieldType.weekyearOfCentury(), 1);
      var1.years = var1.year.getDurationField();
      var1.months = var1.monthOfYear.getDurationField();
      var1.weekyears = var1.weekyear.getDurationField();
   }

   int getDaysInYearMax() {
      return 366;
   }

   int getDaysInYear(int var1) {
      return this.isLeapYear(var1) ? 366 : 365;
   }

   int getWeeksInYear(int var1) {
      long var2 = this.getFirstWeekOfYearMillis(var1);
      long var4 = this.getFirstWeekOfYearMillis(var1 + 1);
      return (int)((var4 - var2) / 604800000L);
   }

   long getFirstWeekOfYearMillis(int var1) {
      long var2 = this.getYearMillis(var1);
      int var4 = this.getDayOfWeek(var2);
      return var4 > 8 - this.iMinDaysInFirstWeek ? var2 + (long)(8 - var4) * 86400000L : var2 - (long)(var4 - 1) * 86400000L;
   }

   long getYearMillis(int var1) {
      return this.getYearInfo(var1).iFirstDayMillis;
   }

   long getYearMonthMillis(int var1, int var2) {
      long var3 = this.getYearMillis(var1);
      var3 += this.getTotalMillisByYearMonth(var1, var2);
      return var3;
   }

   long getYearMonthDayMillis(int var1, int var2, int var3) {
      long var4 = this.getYearMillis(var1);
      var4 += this.getTotalMillisByYearMonth(var1, var2);
      return var4 + (long)(var3 - 1) * 86400000L;
   }

   int getYear(long var1) {
      long var3 = this.getAverageMillisPerYearDividedByTwo();
      long var5 = (var1 >> 1) + this.getApproxMillisAtEpochDividedByTwo();
      if (var5 < 0L) {
         var5 = var5 - var3 + 1L;
      }

      int var7 = (int)(var5 / var3);
      long var8 = this.getYearMillis(var7);
      long var10 = var1 - var8;
      if (var10 < 0L) {
         --var7;
      } else if (var10 >= 31536000000L) {
         long var12;
         if (this.isLeapYear(var7)) {
            var12 = 31622400000L;
         } else {
            var12 = 31536000000L;
         }

         var8 += var12;
         if (var8 <= var1) {
            ++var7;
         }
      }

      return var7;
   }

   int getMonthOfYear(long var1) {
      return this.getMonthOfYear(var1, this.getYear(var1));
   }

   abstract int getMonthOfYear(long var1, int var3);

   int getDayOfMonth(long var1) {
      int var3 = this.getYear(var1);
      int var4 = this.getMonthOfYear(var1, var3);
      return this.getDayOfMonth(var1, var3, var4);
   }

   int getDayOfMonth(long var1, int var3) {
      int var4 = this.getMonthOfYear(var1, var3);
      return this.getDayOfMonth(var1, var3, var4);
   }

   int getDayOfMonth(long var1, int var3, int var4) {
      long var5 = this.getYearMillis(var3);
      var5 += this.getTotalMillisByYearMonth(var3, var4);
      return (int)((var1 - var5) / 86400000L) + 1;
   }

   int getDayOfYear(long var1) {
      return this.getDayOfYear(var1, this.getYear(var1));
   }

   int getDayOfYear(long var1, int var3) {
      long var4 = this.getYearMillis(var3);
      return (int)((var1 - var4) / 86400000L) + 1;
   }

   int getWeekyear(long var1) {
      int var3 = this.getYear(var1);
      int var4 = this.getWeekOfWeekyear(var1, var3);
      if (var4 == 1) {
         return this.getYear(var1 + 604800000L);
      } else {
         return var4 > 51 ? this.getYear(var1 - 1209600000L) : var3;
      }
   }

   int getWeekOfWeekyear(long var1) {
      return this.getWeekOfWeekyear(var1, this.getYear(var1));
   }

   int getWeekOfWeekyear(long var1, int var3) {
      long var4 = this.getFirstWeekOfYearMillis(var3);
      if (var1 < var4) {
         return this.getWeeksInYear(var3 - 1);
      } else {
         long var6 = this.getFirstWeekOfYearMillis(var3 + 1);
         return var1 >= var6 ? 1 : (int)((var1 - var4) / 604800000L) + 1;
      }
   }

   int getDayOfWeek(long var1) {
      long var3;
      if (var1 >= 0L) {
         var3 = var1 / 86400000L;
      } else {
         var3 = (var1 - 86399999L) / 86400000L;
         if (var3 < -3L) {
            return 7 + (int)((var3 + 4L) % 7L);
         }
      }

      return 1 + (int)((var3 + 3L) % 7L);
   }

   int getMillisOfDay(long var1) {
      return var1 >= 0L ? (int)(var1 % 86400000L) : 86399999 + (int)((var1 + 1L) % 86400000L);
   }

   int getDaysInMonthMax() {
      return 31;
   }

   int getDaysInMonthMax(long var1) {
      int var3 = this.getYear(var1);
      int var4 = this.getMonthOfYear(var1, var3);
      return this.getDaysInYearMonth(var3, var4);
   }

   int getDaysInMonthMaxForSet(long var1, int var3) {
      return this.getDaysInMonthMax(var1);
   }

   long getDateMidnightMillis(int var1, int var2, int var3) {
      FieldUtils.verifyValueBounds(DateTimeFieldType.year(), var1, this.getMinYear() - 1, this.getMaxYear() + 1);
      FieldUtils.verifyValueBounds((DateTimeFieldType)DateTimeFieldType.monthOfYear(), var2, 1, this.getMaxMonth(var1));
      int var4 = this.getDaysInYearMonth(var1, var2);
      if (var3 >= 1 && var3 <= var4) {
         long var5 = this.getYearMonthDayMillis(var1, var2, var3);
         if (var5 < 0L && var1 == this.getMaxYear() + 1) {
            return Long.MAX_VALUE;
         } else {
            return var5 > 0L && var1 == this.getMinYear() - 1 ? Long.MIN_VALUE : var5;
         }
      } else {
         throw new IllegalFieldValueException(DateTimeFieldType.dayOfMonth(), var3, 1, var4, "year: " + var1 + " month: " + var2);
      }
   }

   abstract long getYearDifference(long var1, long var3);

   abstract boolean isLeapYear(int var1);

   boolean isLeapDay(long var1) {
      return false;
   }

   abstract int getDaysInYearMonth(int var1, int var2);

   abstract int getDaysInMonthMax(int var1);

   abstract long getTotalMillisByYearMonth(int var1, int var2);

   abstract long calculateFirstDayOfYearMillis(int var1);

   abstract int getMinYear();

   abstract int getMaxYear();

   int getMaxMonth(int var1) {
      return this.getMaxMonth();
   }

   int getMaxMonth() {
      return 12;
   }

   abstract long getAverageMillisPerYear();

   abstract long getAverageMillisPerYearDividedByTwo();

   abstract long getAverageMillisPerMonth();

   abstract long getApproxMillisAtEpochDividedByTwo();

   abstract long setYear(long var1, int var3);

   private YearInfo getYearInfo(int var1) {
      YearInfo var2 = this.iYearInfoCache[var1 & 1023];
      if (var2 == null || var2.iYear != var1) {
         var2 = new YearInfo(var1, this.calculateFirstDayOfYearMillis(var1));
         this.iYearInfoCache[var1 & 1023] = var2;
      }

      return var2;
   }

   static {
      cMillisField = MillisDurationField.INSTANCE;
      cSecondsField = new PreciseDurationField(DurationFieldType.seconds(), 1000L);
      cMinutesField = new PreciseDurationField(DurationFieldType.minutes(), 60000L);
      cHoursField = new PreciseDurationField(DurationFieldType.hours(), 3600000L);
      cHalfdaysField = new PreciseDurationField(DurationFieldType.halfdays(), 43200000L);
      cDaysField = new PreciseDurationField(DurationFieldType.days(), 86400000L);
      cWeeksField = new PreciseDurationField(DurationFieldType.weeks(), 604800000L);
      cMillisOfSecondField = new PreciseDateTimeField(DateTimeFieldType.millisOfSecond(), cMillisField, cSecondsField);
      cMillisOfDayField = new PreciseDateTimeField(DateTimeFieldType.millisOfDay(), cMillisField, cDaysField);
      cSecondOfMinuteField = new PreciseDateTimeField(DateTimeFieldType.secondOfMinute(), cSecondsField, cMinutesField);
      cSecondOfDayField = new PreciseDateTimeField(DateTimeFieldType.secondOfDay(), cSecondsField, cDaysField);
      cMinuteOfHourField = new PreciseDateTimeField(DateTimeFieldType.minuteOfHour(), cMinutesField, cHoursField);
      cMinuteOfDayField = new PreciseDateTimeField(DateTimeFieldType.minuteOfDay(), cMinutesField, cDaysField);
      cHourOfDayField = new PreciseDateTimeField(DateTimeFieldType.hourOfDay(), cHoursField, cDaysField);
      cHourOfHalfdayField = new PreciseDateTimeField(DateTimeFieldType.hourOfHalfday(), cHoursField, cHalfdaysField);
      cClockhourOfDayField = new ZeroIsMaxDateTimeField(cHourOfDayField, DateTimeFieldType.clockhourOfDay());
      cClockhourOfHalfdayField = new ZeroIsMaxDateTimeField(cHourOfHalfdayField, DateTimeFieldType.clockhourOfHalfday());
      cHalfdayOfDayField = new HalfdayField();
   }

   private static class HalfdayField extends PreciseDateTimeField {
      private static final long serialVersionUID = 581601443656929254L;

      HalfdayField() {
         super(DateTimeFieldType.halfdayOfDay(), BasicChronology.cHalfdaysField, BasicChronology.cDaysField);
      }

      public String getAsText(int var1, Locale var2) {
         return GJLocaleSymbols.forLocale(var2).halfdayValueToText(var1);
      }

      public long set(long var1, String var3, Locale var4) {
         return this.set(var1, GJLocaleSymbols.forLocale(var4).halfdayTextToValue(var3));
      }

      public int getMaximumTextLength(Locale var1) {
         return GJLocaleSymbols.forLocale(var1).getHalfdayMaxTextLength();
      }
   }

   private static class YearInfo {
      public final int iYear;
      public final long iFirstDayMillis;

      YearInfo(int var1, long var2) {
         this.iYear = var1;
         this.iFirstDayMillis = var2;
      }
   }
}
