package org.threeten.extra.chrono;

import java.io.Serializable;
import java.time.Clock;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.ChronoLocalDateTime;
import java.time.chrono.ChronoPeriod;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAmount;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalUnit;
import java.time.temporal.ValueRange;

public final class PaxDate extends AbstractDate implements ChronoLocalDate, Serializable {
   private static final long serialVersionUID = -2229133057743750072L;
   private static final int PAX_0001_TO_ISO_1970 = 719163;
   private static final int DAYS_PER_LONG_CYCLE = 146097;
   private static final int DAYS_PER_CYCLE = 36526;
   private static final int DAYS_PER_SIX_CYCLE = 2191;
   private static final int YEARS_IN_DECADE = 10;
   private static final int YEARS_IN_CENTURY = 100;
   private static final int YEARS_IN_MILLENNIUM = 1000;
   private final int prolepticYear;
   private final short month;
   private final short day;

   public static PaxDate now() {
      return now(Clock.systemDefaultZone());
   }

   public static PaxDate now(ZoneId zone) {
      return now(Clock.system(zone));
   }

   public static PaxDate now(Clock clock) {
      LocalDate now = LocalDate.now(clock);
      return ofEpochDay(now.toEpochDay());
   }

   public static PaxDate of(int prolepticYear, int month, int dayOfMonth) {
      ChronoField.YEAR.checkValidValue((long)prolepticYear);
      PaxChronology.MONTH_OF_YEAR_RANGE.checkValidValue((long)month, ChronoField.MONTH_OF_YEAR);
      PaxChronology.DAY_OF_MONTH_RANGE.checkValidValue((long)dayOfMonth, ChronoField.DAY_OF_MONTH);
      if (month == 14 && !PaxChronology.INSTANCE.isLeapYear((long)prolepticYear)) {
         throw new DateTimeException("Invalid month 14 as " + prolepticYear + "is not a leap year");
      } else if (dayOfMonth > 7 && month == 13 && PaxChronology.INSTANCE.isLeapYear((long)prolepticYear)) {
         throw new DateTimeException("Invalid date during Pax as " + prolepticYear + " is a leap year");
      } else {
         return new PaxDate(prolepticYear, month, dayOfMonth);
      }
   }

   public static PaxDate from(TemporalAccessor temporal) {
      return temporal instanceof PaxDate ? (PaxDate)temporal : ofEpochDay(temporal.getLong(ChronoField.EPOCH_DAY));
   }

   static PaxDate ofYearDay(int prolepticYear, int dayOfYear) {
      ChronoField.YEAR.checkValidValue((long)prolepticYear);
      PaxChronology.DAY_OF_YEAR_RANGE.checkValidValue((long)dayOfYear, ChronoField.DAY_OF_YEAR);
      boolean leap = PaxChronology.INSTANCE.isLeapYear((long)prolepticYear);
      if (dayOfYear > 364 && !leap) {
         throw new DateTimeException("Invalid date 'DayOfYear " + dayOfYear + "' as '" + prolepticYear + "' is not a leap year");
      } else {
         int month = (dayOfYear - 1) / 28 + 1;
         if (leap && month == 13 && dayOfYear >= 344) {
            ++month;
         }

         int dayOfMonth = dayOfYear - (month - 1) * 28;
         if (month == 14) {
            dayOfMonth += 21;
         }

         return of(prolepticYear, month, dayOfMonth);
      }
   }

   static PaxDate ofEpochDay(long epochDay) {
      ChronoField.EPOCH_DAY.range().checkValidValue(epochDay, ChronoField.EPOCH_DAY);
      long paxEpochDay = epochDay + 719163L;
      int longCycle = (int)Math.floorDiv(paxEpochDay, 146097L);
      int cycle = (int)(paxEpochDay - (long)(longCycle * 146097)) / '躮';
      int dayOfCycle = (int)Math.floorMod(paxEpochDay - (long)(longCycle * 146097), 36526L);
      if (dayOfCycle >= 36155) {
         int dayOfYear = dayOfCycle - '贻' + 1;
         return ofYearDay(longCycle * 400 + cycle * 100 + 100, dayOfYear);
      } else if (paxEpochDay >= 0L) {
         if (dayOfCycle >= 35784) {
            int dayOfYear = dayOfCycle - '诈' + 1;
            return ofYearDay(longCycle * 400 + cycle * 100 + 99, dayOfYear);
         } else {
            int sixCycle = dayOfCycle / 2191;
            int dayOfSixCycle = dayOfCycle % 2191;
            int year = dayOfSixCycle / 364 + 1;
            int dayOfYear = dayOfSixCycle % 364 + 1;
            if (year == 7) {
               --year;
               dayOfYear += 364;
            }

            return ofYearDay(longCycle * 400 + cycle * 100 + sixCycle * 6 + year, dayOfYear);
         }
      } else if (dayOfCycle < 371) {
         return ofYearDay(longCycle * 400 + cycle * 100 + 1, dayOfCycle + 1);
      } else {
         int offsetCycle = dayOfCycle + 728 - 7;
         int sixCycle = offsetCycle / 2191;
         int dayOfSixCycle = offsetCycle % 2191;
         int year = dayOfSixCycle / 364 + 1;
         int dayOfYear = dayOfSixCycle % 364 + 1;
         if (year == 7) {
            --year;
            dayOfYear += 364;
         }

         return ofYearDay(longCycle * 400 + cycle * 100 - 2 + sixCycle * 6 + year, dayOfYear);
      }
   }

   private static PaxDate resolvePreviousValid(int prolepticYear, int month, int day) {
      int monthR = Math.min(month, 13 + (PaxChronology.INSTANCE.isLeapYear((long)prolepticYear) ? 1 : 0));
      int dayR = Math.min(day, month == 13 && PaxChronology.INSTANCE.isLeapYear((long)prolepticYear) ? 7 : 28);
      return of(prolepticYear, monthR, dayR);
   }

   private static long getLeapMonthsBefore(long prolepticMonth) {
      long offsetMonth = prolepticMonth - (long)(prolepticMonth <= 0L ? 13 : 12);
      return 18L * Math.floorDiv(offsetMonth, 1318L) - Math.floorDiv(offsetMonth, 5272L) + (Math.floorMod(offsetMonth, 1318L) - (long)(offsetMonth <= 0L ? 1317 : 0)) / 1304L + (long)(offsetMonth <= 0L ? 1 : 0) + (Math.floorMod(offsetMonth, 1318L) + (long)(offsetMonth <= 0L ? 25 : 0)) / 79L;
   }

   private static long getLeapYearsBefore(long prolepticYear) {
      return 18L * Math.floorDiv(prolepticYear - 1L, 100L) - Math.floorDiv(prolepticYear - 1L, 400L) + (Math.floorMod(prolepticYear - 1L, 100L) - (long)(prolepticYear <= 0L ? 99 : 0)) / 99L + (long)(prolepticYear <= 0L ? 1 : 0) + (Math.floorMod(prolepticYear - 1L, 100L) + (long)(prolepticYear <= 0L ? 2 : 0)) / 6L;
   }

   private PaxDate(int prolepticYear, int month, int dayOfMonth) {
      this.prolepticYear = prolepticYear;
      this.month = (short)month;
      this.day = (short)dayOfMonth;
   }

   private Object readResolve() {
      return of(this.prolepticYear, this.month, this.day);
   }

   int getProlepticYear() {
      return this.prolepticYear;
   }

   int getMonth() {
      return this.month;
   }

   int getDayOfMonth() {
      return this.day;
   }

   int getDayOfYear() {
      return (this.month - 1) * 28 - (this.month == 14 ? 21 : 0) + this.getDayOfMonth();
   }

   int lengthOfYearInMonths() {
      return 13 + (this.isLeapYear() ? 1 : 0);
   }

   ValueRange rangeAlignedWeekOfMonth() {
      return ValueRange.of(1L, this.month == 13 && this.isLeapYear() ? 1L : 4L);
   }

   PaxDate resolvePrevious(int newYear, int newMonth, int dayOfMonth) {
      return resolvePreviousValid(newYear, newMonth, dayOfMonth);
   }

   public ValueRange range(TemporalField field) {
      if (field == ChronoField.ALIGNED_WEEK_OF_YEAR) {
         return ValueRange.of(1L, (long)(52 + (this.isLeapYear() ? 1 : 0)));
      } else {
         return field == ChronoField.MONTH_OF_YEAR ? ValueRange.of(1L, (long)(13 + (this.isLeapYear() ? 1 : 0))) : super.range(field);
      }
   }

   long getProlepticMonth() {
      return (long)this.getProlepticYear() * 13L + getLeapYearsBefore((long)this.getProlepticYear()) + (long)this.month - 1L;
   }

   public PaxChronology getChronology() {
      return PaxChronology.INSTANCE;
   }

   public PaxEra getEra() {
      return this.prolepticYear >= 1 ? PaxEra.CE : PaxEra.BCE;
   }

   public int lengthOfMonth() {
      switch (this.month) {
         case 13:
            return this.isLeapYear() ? 7 : 28;
         default:
            return 28;
      }
   }

   public int lengthOfYear() {
      return 364 + (this.isLeapYear() ? 7 : 0);
   }

   public PaxDate with(TemporalAdjuster adjuster) {
      return (PaxDate)adjuster.adjustInto(this);
   }

   public PaxDate with(TemporalField field, long newValue) {
      return field == ChronoField.YEAR ? this.plusYears(Math.subtractExact(newValue, (long)this.getProlepticYear())) : (PaxDate)super.with(field, newValue);
   }

   public PaxDate plus(TemporalAmount amount) {
      return (PaxDate)amount.addTo(this);
   }

   public PaxDate plus(long amountToAdd, TemporalUnit unit) {
      return (PaxDate)super.plus(amountToAdd, unit);
   }

   PaxDate plusYears(long yearsToAdd) {
      if (yearsToAdd == 0L) {
         return this;
      } else {
         int newYear = ChronoField.YEAR.checkValidIntValue((long)this.getProlepticYear() + yearsToAdd);
         return this.month == 13 && !this.isLeapYear() && PaxChronology.INSTANCE.isLeapYear((long)newYear) ? of(newYear, 14, this.getDayOfMonth()) : resolvePreviousValid(newYear, this.month, this.day);
      }
   }

   PaxDate plusMonths(long monthsToAdd) {
      if (monthsToAdd == 0L) {
         return this;
      } else {
         long calcMonths = Math.addExact(this.getProlepticMonth(), monthsToAdd);
         long monthsRegularized = calcMonths - getLeapMonthsBefore(calcMonths);
         int newYear = ChronoField.YEAR.checkValidIntValue(Math.floorDiv(monthsRegularized, 13L));
         int newMonth = Math.toIntExact(calcMonths - ((long)newYear * 13L + getLeapYearsBefore((long)newYear)) + 1L);
         return resolvePreviousValid(newYear, newMonth, this.getDayOfMonth());
      }
   }

   public PaxDate minus(TemporalAmount amount) {
      return (PaxDate)amount.subtractFrom(this);
   }

   public PaxDate minus(long amountToSubtract, TemporalUnit unit) {
      return amountToSubtract == Long.MIN_VALUE ? this.plus(Long.MAX_VALUE, unit).plus(1L, unit) : this.plus(-amountToSubtract, unit);
   }

   public ChronoLocalDateTime atTime(LocalTime localTime) {
      return super.atTime(localTime);
   }

   public long until(Temporal endExclusive, TemporalUnit unit) {
      return this.until((AbstractDate)from(endExclusive), unit);
   }

   long until(AbstractDate end, TemporalUnit unit) {
      if (unit instanceof ChronoUnit) {
         PaxDate paxEnd = from(end);
         switch ((ChronoUnit)unit) {
            case YEARS:
               return this.yearsUntil(paxEnd);
            case DECADES:
               return this.yearsUntil(paxEnd) / 10L;
            case CENTURIES:
               return this.yearsUntil(paxEnd) / 100L;
            case MILLENNIA:
               return this.yearsUntil(paxEnd) / 1000L;
         }
      }

      return super.until(end, unit);
   }

   long yearsUntil(PaxDate end) {
      long startYear = (long)this.getProlepticYear() * 512L + (long)this.getDayOfYear() + (long)(this.month == 13 && !this.isLeapYear() && end.isLeapYear() ? 7 : 0);
      long endYear = (long)end.getProlepticYear() * 512L + (long)end.getDayOfYear() + (long)(end.month == 13 && !end.isLeapYear() && this.isLeapYear() ? 7 : 0);
      return (endYear - startYear) / 512L;
   }

   public ChronoPeriod until(ChronoLocalDate endDateExclusive) {
      PaxDate end = from(endDateExclusive);
      int years = Math.toIntExact(this.yearsUntil(end));
      PaxDate sameYearEnd = this.plusYears((long)years);
      int months = (int)sameYearEnd.monthsUntil(end);
      int days = (int)sameYearEnd.plusMonths((long)months).daysUntil(end);
      return this.getChronology().period(years, months, days);
   }

   public long toEpochDay() {
      long paxEpochDay = ((long)this.getProlepticYear() - 1L) * 364L + getLeapYearsBefore((long)this.getProlepticYear()) * 7L + (long)this.getDayOfYear() - 1L;
      return paxEpochDay - 719163L;
   }
}
