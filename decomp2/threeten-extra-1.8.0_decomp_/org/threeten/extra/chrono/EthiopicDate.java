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

public final class EthiopicDate extends AbstractNileDate implements ChronoLocalDate, Serializable {
   private static final long serialVersionUID = -268768729L;
   private static final int EPOCH_DAY_DIFFERENCE = 716367;
   private final int prolepticYear;
   private final short month;
   private final short day;

   public static EthiopicDate now() {
      return now(Clock.systemDefaultZone());
   }

   public static EthiopicDate now(ZoneId zone) {
      return now(Clock.system(zone));
   }

   public static EthiopicDate now(Clock clock) {
      LocalDate now = LocalDate.now(clock);
      return ofEpochDay(now.toEpochDay());
   }

   public static EthiopicDate of(int prolepticYear, int month, int dayOfMonth) {
      return create(prolepticYear, month, dayOfMonth);
   }

   public static EthiopicDate from(TemporalAccessor temporal) {
      return temporal instanceof EthiopicDate ? (EthiopicDate)temporal : ofEpochDay(temporal.getLong(ChronoField.EPOCH_DAY));
   }

   static EthiopicDate ofYearDay(int prolepticYear, int dayOfYear) {
      EthiopicChronology.YEAR_RANGE.checkValidValue((long)prolepticYear, ChronoField.YEAR);
      ChronoField.DAY_OF_YEAR.range().checkValidValue((long)dayOfYear, ChronoField.DAY_OF_YEAR);
      if (dayOfYear == 366 && !EthiopicChronology.INSTANCE.isLeapYear((long)prolepticYear)) {
         throw new DateTimeException("Invalid date 'Pagumen 6' as '" + prolepticYear + "' is not a leap year");
      } else {
         return new EthiopicDate(prolepticYear, (dayOfYear - 1) / 30 + 1, (dayOfYear - 1) % 30 + 1);
      }
   }

   static EthiopicDate ofEpochDay(long epochDay) {
      ChronoField.EPOCH_DAY.range().checkValidValue(epochDay, ChronoField.EPOCH_DAY);
      long ethiopicED = epochDay + 716367L;
      int adjustment = 0;
      if (ethiopicED < 0L) {
         ethiopicED += 365250000L;
         adjustment = -1000000;
      }

      int prolepticYear = (int)((ethiopicED * 4L + 1463L) / 1461L);
      int startYearEpochDay = (prolepticYear - 1) * 365 + prolepticYear / 4;
      int doy0 = (int)(ethiopicED - (long)startYearEpochDay);
      int month = doy0 / 30 + 1;
      int dom = doy0 % 30 + 1;
      return new EthiopicDate(prolepticYear + adjustment, month, dom);
   }

   private static EthiopicDate resolvePreviousValid(int prolepticYear, int month, int day) {
      if (month == 13 && day > 5) {
         day = EthiopicChronology.INSTANCE.isLeapYear((long)prolepticYear) ? 6 : 5;
      }

      return new EthiopicDate(prolepticYear, month, day);
   }

   static EthiopicDate create(int prolepticYear, int month, int dayOfMonth) {
      EthiopicChronology.YEAR_RANGE.checkValidValue((long)prolepticYear, ChronoField.YEAR);
      EthiopicChronology.MOY_RANGE.checkValidValue((long)month, ChronoField.MONTH_OF_YEAR);
      EthiopicChronology.DOM_RANGE.checkValidValue((long)dayOfMonth, ChronoField.DAY_OF_MONTH);
      if (month == 13 && dayOfMonth > 5) {
         if (!EthiopicChronology.INSTANCE.isLeapYear((long)prolepticYear)) {
            if (dayOfMonth == 6) {
               throw new DateTimeException("Invalid date 'Pagumen 6' as '" + prolepticYear + "' is not a leap year");
            }

            throw new DateTimeException("Invalid date 'Pagumen " + dayOfMonth + "', valid range from 1 to 5, or 1 to 6 in a leap year");
         }

         if (dayOfMonth > 6) {
            throw new DateTimeException("Invalid date 'Pagumen " + dayOfMonth + "', valid range from 1 to 5, or 1 to 6 in a leap year");
         }
      }

      return new EthiopicDate(prolepticYear, month, dayOfMonth);
   }

   private EthiopicDate(int prolepticYear, int month, int dayOfMonth) {
      this.prolepticYear = prolepticYear;
      this.month = (short)month;
      this.day = (short)dayOfMonth;
   }

   private Object readResolve() {
      return create(this.prolepticYear, this.month, this.day);
   }

   int getEpochDayDifference() {
      return 716367;
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

   EthiopicDate resolvePrevious(int newYear, int newMonth, int dayOfMonth) {
      return resolvePreviousValid(newYear, newMonth, dayOfMonth);
   }

   public EthiopicChronology getChronology() {
      return EthiopicChronology.INSTANCE;
   }

   public EthiopicEra getEra() {
      return this.prolepticYear >= 1 ? EthiopicEra.INCARNATION : EthiopicEra.BEFORE_INCARNATION;
   }

   public EthiopicDate with(TemporalAdjuster adjuster) {
      return (EthiopicDate)adjuster.adjustInto(this);
   }

   public EthiopicDate with(TemporalField field, long newValue) {
      return (EthiopicDate)super.with(field, newValue);
   }

   public EthiopicDate plus(TemporalAmount amount) {
      return (EthiopicDate)amount.addTo(this);
   }

   public EthiopicDate plus(long amountToAdd, TemporalUnit unit) {
      return (EthiopicDate)super.plus(amountToAdd, unit);
   }

   public EthiopicDate minus(TemporalAmount amount) {
      return (EthiopicDate)amount.subtractFrom(this);
   }

   public EthiopicDate minus(long amountToSubtract, TemporalUnit unit) {
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
