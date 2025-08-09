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

public final class CopticDate extends AbstractNileDate implements ChronoLocalDate, Serializable {
   private static final long serialVersionUID = -7920528871688876868L;
   private static final int EPOCH_DAY_DIFFERENCE = 615558;
   private final int prolepticYear;
   private final short month;
   private final short day;

   public static CopticDate now() {
      return now(Clock.systemDefaultZone());
   }

   public static CopticDate now(ZoneId zone) {
      return now(Clock.system(zone));
   }

   public static CopticDate now(Clock clock) {
      LocalDate now = LocalDate.now(clock);
      return ofEpochDay(now.toEpochDay());
   }

   public static CopticDate of(int prolepticYear, int month, int dayOfMonth) {
      return create(prolepticYear, month, dayOfMonth);
   }

   public static CopticDate from(TemporalAccessor temporal) {
      return temporal instanceof CopticDate ? (CopticDate)temporal : ofEpochDay(temporal.getLong(ChronoField.EPOCH_DAY));
   }

   static CopticDate ofYearDay(int prolepticYear, int dayOfYear) {
      CopticChronology.YEAR_RANGE.checkValidValue((long)prolepticYear, ChronoField.YEAR);
      ChronoField.DAY_OF_YEAR.range().checkValidValue((long)dayOfYear, ChronoField.DAY_OF_YEAR);
      if (dayOfYear == 366 && !CopticChronology.INSTANCE.isLeapYear((long)prolepticYear)) {
         throw new DateTimeException("Invalid date 'Nasie 6' as '" + prolepticYear + "' is not a leap year");
      } else {
         return new CopticDate(prolepticYear, (dayOfYear - 1) / 30 + 1, (dayOfYear - 1) % 30 + 1);
      }
   }

   static CopticDate ofEpochDay(long epochDay) {
      ChronoField.EPOCH_DAY.range().checkValidValue(epochDay, ChronoField.EPOCH_DAY);
      long copticED = epochDay + 615558L;
      int adjustment = 0;
      if (copticED < 0L) {
         copticED += 365250000L;
         adjustment = -1000000;
      }

      int prolepticYear = (int)((copticED * 4L + 1463L) / 1461L);
      int startYearEpochDay = (prolepticYear - 1) * 365 + prolepticYear / 4;
      int doy0 = (int)(copticED - (long)startYearEpochDay);
      int month = doy0 / 30 + 1;
      int dom = doy0 % 30 + 1;
      return new CopticDate(prolepticYear + adjustment, month, dom);
   }

   private static CopticDate resolvePreviousValid(int prolepticYear, int month, int day) {
      if (month == 13 && day > 5) {
         day = CopticChronology.INSTANCE.isLeapYear((long)prolepticYear) ? 6 : 5;
      }

      return new CopticDate(prolepticYear, month, day);
   }

   static CopticDate create(int prolepticYear, int month, int dayOfMonth) {
      CopticChronology.YEAR_RANGE.checkValidValue((long)prolepticYear, ChronoField.YEAR);
      CopticChronology.MOY_RANGE.checkValidValue((long)month, ChronoField.MONTH_OF_YEAR);
      CopticChronology.DOM_RANGE.checkValidValue((long)dayOfMonth, ChronoField.DAY_OF_MONTH);
      if (month == 13 && dayOfMonth > 5) {
         if (!CopticChronology.INSTANCE.isLeapYear((long)prolepticYear)) {
            if (dayOfMonth == 6) {
               throw new DateTimeException("Invalid date 'Nasie 6' as '" + prolepticYear + "' is not a leap year");
            }

            throw new DateTimeException("Invalid date 'Nasie " + dayOfMonth + "', valid range from 1 to 5, or 1 to 6 in a leap year");
         }

         if (dayOfMonth > 6) {
            throw new DateTimeException("Invalid date 'Nasie " + dayOfMonth + "', valid range from 1 to 5, or 1 to 6 in a leap year");
         }
      }

      return new CopticDate(prolepticYear, month, dayOfMonth);
   }

   private CopticDate(int prolepticYear, int month, int dayOfMonth) {
      this.prolepticYear = prolepticYear;
      this.month = (short)month;
      this.day = (short)dayOfMonth;
   }

   private Object readResolve() {
      return create(this.prolepticYear, this.month, this.day);
   }

   int getEpochDayDifference() {
      return 615558;
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

   CopticDate resolvePrevious(int newYear, int newMonth, int dayOfMonth) {
      return resolvePreviousValid(newYear, newMonth, dayOfMonth);
   }

   public CopticChronology getChronology() {
      return CopticChronology.INSTANCE;
   }

   public CopticEra getEra() {
      return this.prolepticYear >= 1 ? CopticEra.AM : CopticEra.BEFORE_AM;
   }

   public CopticDate with(TemporalAdjuster adjuster) {
      return (CopticDate)adjuster.adjustInto(this);
   }

   public CopticDate with(TemporalField field, long newValue) {
      return (CopticDate)super.with(field, newValue);
   }

   public CopticDate plus(TemporalAmount amount) {
      return (CopticDate)amount.addTo(this);
   }

   public CopticDate plus(long amountToAdd, TemporalUnit unit) {
      return (CopticDate)super.plus(amountToAdd, unit);
   }

   public CopticDate minus(TemporalAmount amount) {
      return (CopticDate)amount.subtractFrom(this);
   }

   public CopticDate minus(long amountToSubtract, TemporalUnit unit) {
      return amountToSubtract == Long.MIN_VALUE ? this.plus(Long.MAX_VALUE, unit).plus(1L, unit) : this.plus(-amountToSubtract, unit);
   }

   public ChronoLocalDateTime atTime(LocalTime localTime) {
      return super.atTime(localTime);
   }

   public long until(Temporal endExclusive, TemporalUnit unit) {
      return super.until(from(endExclusive), unit);
   }

   public ChronoPeriod until(ChronoLocalDate endDateExclusive) {
      return super.doUntil(from(endDateExclusive));
   }
}
