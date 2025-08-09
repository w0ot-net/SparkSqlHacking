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
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAmount;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalUnit;
import java.time.temporal.UnsupportedTemporalTypeException;
import java.time.temporal.ValueRange;

public final class InternationalFixedDate extends AbstractDate implements ChronoLocalDate, Serializable {
   private static final long serialVersionUID = -5501342824322148215L;
   private static final int LEAP_DAY_AS_DAY_OF_YEAR = 169;
   private final int prolepticYear;
   private final int month;
   private final int day;
   private final transient int dayOfYear;
   private final transient boolean isLeapYear;
   private final transient boolean isLeapDay;
   private final transient boolean isYearDay;

   public static InternationalFixedDate now() {
      return now(Clock.systemDefaultZone());
   }

   public static InternationalFixedDate now(ZoneId zone) {
      return now(Clock.system(zone));
   }

   public static InternationalFixedDate now(Clock clock) {
      LocalDate now = LocalDate.now(clock);
      return ofEpochDay(now.toEpochDay());
   }

   public static InternationalFixedDate of(int prolepticYear, int month, int dayOfMonth) {
      return create(prolepticYear, month, dayOfMonth);
   }

   public static InternationalFixedDate from(TemporalAccessor temporal) {
      return temporal instanceof InternationalFixedDate ? (InternationalFixedDate)temporal : ofEpochDay(temporal.getLong(ChronoField.EPOCH_DAY));
   }

   static InternationalFixedDate ofYearDay(int prolepticYear, int dayOfYear) {
      InternationalFixedChronology.YEAR_RANGE.checkValidValue((long)prolepticYear, ChronoField.YEAR_OF_ERA);
      ChronoField.DAY_OF_YEAR.checkValidValue((long)dayOfYear);
      boolean isLeapYear = InternationalFixedChronology.INSTANCE.isLeapYear((long)prolepticYear);
      int lastDoy = 365 + (isLeapYear ? 1 : 0);
      if (dayOfYear > lastDoy) {
         throw new DateTimeException("Invalid date 'DayOfYear 366' as '" + prolepticYear + "' is not a leap year");
      } else if (dayOfYear == lastDoy) {
         return new InternationalFixedDate(prolepticYear, 13, 29);
      } else if (dayOfYear == 169 && isLeapYear) {
         return new InternationalFixedDate(prolepticYear, 6, 29);
      } else {
         int doy0 = dayOfYear - 1;
         if (dayOfYear >= 169 && isLeapYear) {
            --doy0;
         }

         int month = doy0 / 28 + 1;
         int day = doy0 % 28 + 1;
         return new InternationalFixedDate(prolepticYear, month, day);
      }
   }

   static InternationalFixedDate ofEpochDay(long epochDay) {
      InternationalFixedChronology.EPOCH_DAY_RANGE.checkValidValue(epochDay, ChronoField.EPOCH_DAY);
      long zeroDay = epochDay + 719528L;
      long year = 400L * zeroDay / 146097L;
      long doy = zeroDay - (365L * year + InternationalFixedChronology.getLeapYearsBefore(year));
      boolean isLeapYear = InternationalFixedChronology.INSTANCE.isLeapYear(year);
      if (doy == 366L && !isLeapYear) {
         ++year;
         doy = 1L;
      }

      if (doy == 0L) {
         --year;
         doy = (long)(365 + (isLeapYear ? 1 : 0));
      }

      return ofYearDay((int)year, (int)doy);
   }

   private static InternationalFixedDate resolvePreviousValid(int prolepticYear, int month, int day) {
      int monthR = Math.min(month, 13);
      int dayR = Math.min(day, monthR != 13 && (monthR != 6 || !InternationalFixedChronology.INSTANCE.isLeapYear((long)prolepticYear)) ? 28 : 29);
      return create(prolepticYear, monthR, dayR);
   }

   static InternationalFixedDate create(int prolepticYear, int month, int dayOfMonth) {
      InternationalFixedChronology.YEAR_RANGE.checkValidValue((long)prolepticYear, ChronoField.YEAR_OF_ERA);
      InternationalFixedChronology.MONTH_OF_YEAR_RANGE.checkValidValue((long)month, ChronoField.MONTH_OF_YEAR);
      InternationalFixedChronology.DAY_OF_MONTH_RANGE.checkValidValue((long)dayOfMonth, ChronoField.DAY_OF_MONTH);
      if (dayOfMonth == 29 && month != 6 && month != 13) {
         throw new DateTimeException("Invalid date: " + prolepticYear + '/' + month + '/' + dayOfMonth);
      } else if (month == 6 && dayOfMonth == 29 && !InternationalFixedChronology.INSTANCE.isLeapYear((long)prolepticYear)) {
         throw new DateTimeException("Invalid Leap Day as '" + prolepticYear + "' is not a leap year");
      } else {
         return new InternationalFixedDate(prolepticYear, month, dayOfMonth);
      }
   }

   private InternationalFixedDate(int prolepticYear, int month, int dayOfMonth) {
      this.prolepticYear = prolepticYear;
      this.month = month;
      this.day = dayOfMonth;
      this.isLeapYear = InternationalFixedChronology.INSTANCE.isLeapYear((long)prolepticYear);
      this.isLeapDay = this.month == 6 && this.day == 29;
      this.isYearDay = this.month == 13 && this.day == 29;
      this.dayOfYear = (month - 1) * 28 + this.day + (month > 6 && this.isLeapYear ? 1 : 0);
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
      return this.dayOfYear;
   }

   int lengthOfYearInMonths() {
      return 13;
   }

   int getAlignedDayOfWeekInMonth() {
      return this.getDayOfWeek();
   }

   int getAlignedDayOfWeekInYear() {
      return this.getDayOfWeek();
   }

   int getAlignedWeekOfMonth() {
      return this.isSpecialDay() ? 0 : (this.day - 1) / 7 + 1;
   }

   int getAlignedWeekOfYear() {
      return this.isSpecialDay() ? 0 : (this.month - 1) * 4 + (this.day - 1) / 7 + 1;
   }

   int getDayOfWeek() {
      return this.isSpecialDay() ? 0 : (this.day - 1) % 7 + 1;
   }

   long getProlepticWeek() {
      return this.getProlepticMonth() * 4L + (long)((this.getDayOfMonth() - 1) / 7) - 1L;
   }

   private boolean isSpecialDay() {
      return this.day == 29;
   }

   public ValueRange range(TemporalField field) {
      if (field instanceof ChronoField) {
         if (!this.isSupported(field)) {
            throw new UnsupportedTemporalTypeException("Unsupported field: " + field);
         }

         ChronoField f = (ChronoField)field;
         switch (f) {
            case ALIGNED_DAY_OF_WEEK_IN_MONTH:
            case ALIGNED_DAY_OF_WEEK_IN_YEAR:
            case DAY_OF_WEEK:
               return this.isSpecialDay() ? InternationalFixedChronology.EMPTY_RANGE : ValueRange.of(1L, 7L);
            case ALIGNED_WEEK_OF_MONTH:
               return this.isSpecialDay() ? InternationalFixedChronology.EMPTY_RANGE : ValueRange.of(1L, 4L);
            case ALIGNED_WEEK_OF_YEAR:
               return this.isSpecialDay() ? InternationalFixedChronology.EMPTY_RANGE : ValueRange.of(1L, 52L);
            case DAY_OF_MONTH:
               return ValueRange.of(1L, (long)this.lengthOfMonth());
            case DAY_OF_YEAR:
               return this.isLeapYear ? InternationalFixedChronology.DAY_OF_YEAR_LEAP_RANGE : InternationalFixedChronology.DAY_OF_YEAR_NORMAL_RANGE;
            case EPOCH_DAY:
               return InternationalFixedChronology.EPOCH_DAY_RANGE;
            case ERA:
               return InternationalFixedChronology.ERA_RANGE;
            case MONTH_OF_YEAR:
               return InternationalFixedChronology.MONTH_OF_YEAR_RANGE;
         }
      }

      return super.range(field);
   }

   ValueRange rangeAlignedWeekOfMonth() {
      return this.isSpecialDay() ? InternationalFixedChronology.EMPTY_RANGE : ValueRange.of(1L, 4L);
   }

   InternationalFixedDate resolvePrevious(int newYear, int newMonth, int dayOfMonth) {
      return resolvePreviousValid(newYear, newMonth, dayOfMonth);
   }

   public InternationalFixedChronology getChronology() {
      return InternationalFixedChronology.INSTANCE;
   }

   public InternationalFixedEra getEra() {
      return InternationalFixedEra.CE;
   }

   public int lengthOfMonth() {
      return this.isLongMonth() ? 29 : 28;
   }

   private boolean isLongMonth() {
      return this.month == 13 || this.month == 6 && this.isLeapYear;
   }

   public int lengthOfYear() {
      return 365 + (this.isLeapYear ? 1 : 0);
   }

   public InternationalFixedDate with(TemporalAdjuster adjuster) {
      return (InternationalFixedDate)adjuster.adjustInto(this);
   }

   public InternationalFixedDate with(TemporalField field, long newValue) {
      if (field instanceof ChronoField) {
         if (newValue == 0L && this.isSpecialDay()) {
            return this;
         }

         ChronoField f = (ChronoField)field;
         this.getChronology().range(f).checkValidValue(newValue, f);
         int nval = (int)newValue;
         switch (f) {
            case ALIGNED_DAY_OF_WEEK_IN_MONTH:
            case ALIGNED_DAY_OF_WEEK_IN_YEAR:
            case DAY_OF_WEEK:
               if (newValue == 0L && !this.isSpecialDay()) {
                  this.range(f).checkValidValue(newValue, field);
               }

               int dom = this.isSpecialDay() ? 21 : (this.getDayOfMonth() - 1) / 7 * 7;
               return resolvePreviousValid(this.prolepticYear, this.month, dom + nval);
            case ALIGNED_WEEK_OF_MONTH:
               if (newValue == 0L && !this.isSpecialDay()) {
                  this.range(f).checkValidValue(newValue, field);
               }

               int d = this.isSpecialDay() ? 1 : this.day % 7;
               return resolvePreviousValid(this.prolepticYear, this.month, (nval - 1) * 7 + d);
            case ALIGNED_WEEK_OF_YEAR:
               if (newValue == 0L && !this.isSpecialDay()) {
                  this.range(f).checkValidValue(newValue, field);
               }

               int newMonth = 1 + (nval - 1) / 4;
               int newDay = (nval - 1) % 4 * 7 + 1 + (this.day - 1) % 7;
               return resolvePreviousValid(this.prolepticYear, newMonth, newDay);
            case DAY_OF_MONTH:
               return create(this.prolepticYear, this.month, nval);
         }
      }

      return (InternationalFixedDate)super.with(field, newValue);
   }

   InternationalFixedDate withDayOfYear(int value) {
      return ofYearDay(this.prolepticYear, value);
   }

   public InternationalFixedDate plus(TemporalAmount amount) {
      return (InternationalFixedDate)amount.addTo(this);
   }

   public InternationalFixedDate plus(long amountToAdd, TemporalUnit unit) {
      return (InternationalFixedDate)super.plus(amountToAdd, unit);
   }

   InternationalFixedDate plusWeeks(long weeks) {
      if (weeks == 0L) {
         return this;
      } else if (weeks % 4L == 0L) {
         return this.plusMonths(weeks / 4L);
      } else {
         long calcEm = Math.addExact(this.getProlepticWeek(), weeks);
         int newYear = Math.toIntExact(Math.floorDiv(calcEm, 52L));
         int newWeek = Math.toIntExact(Math.floorMod(calcEm, 52L));
         int newMonth = 1 + Math.floorDiv(newWeek, 4);
         int newDay = 1 + (newWeek * 7 + 8 + (this.isLeapDay ? 0 : (this.isYearDay ? -1 : (this.day - 1) % 7)) - 1) % 28;
         return create(newYear, newMonth, newDay);
      }
   }

   InternationalFixedDate plusMonths(long months) {
      if (months == 0L) {
         return this;
      } else if (months % 13L == 0L) {
         return this.plusYears(months / 13L);
      } else {
         int newMonth = (int)Math.addExact(this.getProlepticMonth(), months);
         int newYear = newMonth / 13;
         newMonth = 1 + newMonth % 13;
         return resolvePreviousValid(newYear, newMonth, this.day);
      }
   }

   InternationalFixedDate plusYears(long yearsToAdd) {
      if (yearsToAdd == 0L) {
         return this;
      } else {
         int newYear = InternationalFixedChronology.YEAR_RANGE.checkValidIntValue(Math.addExact((long)this.prolepticYear, yearsToAdd), ChronoField.YEAR);
         return resolvePreviousValid(newYear, this.month, this.day);
      }
   }

   public InternationalFixedDate minus(TemporalAmount amount) {
      return (InternationalFixedDate)amount.subtractFrom(this);
   }

   public InternationalFixedDate minus(long amountToSubtract, TemporalUnit unit) {
      return (InternationalFixedDate)super.minus(amountToSubtract, unit);
   }

   public ChronoLocalDateTime atTime(LocalTime localTime) {
      return super.atTime(localTime);
   }

   public long until(Temporal endExclusive, TemporalUnit unit) {
      return this.until(from(endExclusive), unit);
   }

   long yearsUntil(InternationalFixedDate end) {
      long startYear = (long)this.prolepticYear * 512L + (long)this.getInternalDayOfYear();
      long endYear = (long)end.prolepticYear * 512L + (long)end.getInternalDayOfYear();
      return (endYear - startYear) / 512L;
   }

   private int getInternalDayOfYear() {
      return this.isLeapYear && this.month > 6 ? this.dayOfYear - 1 : this.dayOfYear;
   }

   public ChronoPeriod until(ChronoLocalDate endDateExclusive) {
      InternationalFixedDate end = from(endDateExclusive);
      int years = Math.toIntExact(this.yearsUntil(end));
      InternationalFixedDate sameYearEnd = this.plusYears((long)years);
      int months = (int)sameYearEnd.monthsUntil(end);
      int days = (int)sameYearEnd.plusMonths((long)months).daysUntil(end);
      if (!this.isYearDay && !this.isLeapDay && (!end.isYearDay || end.isLeapDay)) {
         if (days == 28) {
            days = 0;
            ++months;
         }

         if (days == -28) {
            days = 0;
            --months;
         }
      }

      return this.getChronology().period(years, months, days);
   }

   long weeksUntil(AbstractDate end) {
      InternationalFixedDate endDate = from(end);
      int offset = (this.day < 1 || endDate.day < 1) && this.day != endDate.day && this.isLeapYear && endDate.isLeapYear ? (this.isBefore(endDate) ? 1 : -1) : 0;
      long startWeek = this.getProlepticWeek() * 8L + (long)this.getDayOfWeek();
      long endWeek = endDate.getProlepticWeek() * 8L + (long)end.getDayOfWeek();
      return (endWeek - startWeek - (long)offset) / 8L;
   }

   long monthsUntil(AbstractDate end) {
      InternationalFixedDate date = from(end);
      long monthStart = this.getProlepticMonth() * 32L + (long)this.getDayOfMonth();
      long monthEnd = date.getProlepticMonth() * 32L + (long)date.getDayOfMonth();
      return (monthEnd - monthStart) / 32L;
   }

   public long toEpochDay() {
      long epochDay = (long)this.prolepticYear * 365L + InternationalFixedChronology.getLeapYearsBefore((long)this.prolepticYear) + (long)this.dayOfYear;
      return epochDay - 719528L;
   }

   public String toString() {
      StringBuilder buf = new StringBuilder(30);
      return buf.append(this.getChronology().toString()).append(' ').append(this.getEra()).append(' ').append(this.getYearOfEra()).append(this.month < 10 && this.month > 0 ? "/0" : '/').append(this.month).append(this.day < 10 ? "/0" : '/').append(this.day).toString();
   }
}
