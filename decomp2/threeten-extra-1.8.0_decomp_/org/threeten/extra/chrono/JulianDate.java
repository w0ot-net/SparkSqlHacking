package org.threeten.extra.chrono;

import java.io.Serializable;
import java.time.Clock;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
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
import java.time.temporal.ValueRange;

public final class JulianDate extends AbstractDate implements ChronoLocalDate, Serializable {
   private static final long serialVersionUID = -7920528871688876868L;
   private static final int JULIAN_0001_TO_ISO_1970 = 719164;
   private static final int DAYS_PER_CYCLE = 1461;
   private final int prolepticYear;
   private final short month;
   private final short day;

   public static JulianDate now() {
      return now(Clock.systemDefaultZone());
   }

   public static JulianDate now(ZoneId zone) {
      return now(Clock.system(zone));
   }

   public static JulianDate now(Clock clock) {
      LocalDate now = LocalDate.now(clock);
      return ofEpochDay(now.toEpochDay());
   }

   public static JulianDate of(int prolepticYear, int month, int dayOfMonth) {
      return create(prolepticYear, month, dayOfMonth);
   }

   public static JulianDate from(TemporalAccessor temporal) {
      return temporal instanceof JulianDate ? (JulianDate)temporal : ofEpochDay(temporal.getLong(ChronoField.EPOCH_DAY));
   }

   static JulianDate ofYearDay(int prolepticYear, int dayOfYear) {
      JulianChronology.YEAR_RANGE.checkValidValue((long)prolepticYear, ChronoField.YEAR);
      ChronoField.DAY_OF_YEAR.checkValidValue((long)dayOfYear);
      boolean leap = JulianChronology.INSTANCE.isLeapYear((long)prolepticYear);
      if (dayOfYear == 366 && !leap) {
         throw new DateTimeException("Invalid date 'DayOfYear 366' as '" + prolepticYear + "' is not a leap year");
      } else {
         Month moy = Month.of((dayOfYear - 1) / 31 + 1);
         int monthEnd = moy.firstDayOfYear(leap) + moy.length(leap) - 1;
         if (dayOfYear > monthEnd) {
            moy = moy.plus(1L);
         }

         int dom = dayOfYear - moy.firstDayOfYear(leap) + 1;
         return new JulianDate(prolepticYear, moy.getValue(), dom);
      }
   }

   static JulianDate ofEpochDay(long epochDay) {
      ChronoField.EPOCH_DAY.range().checkValidValue(epochDay, ChronoField.EPOCH_DAY);
      long julianEpochDay = epochDay + 719164L;
      long cycle = Math.floorDiv(julianEpochDay, 1461L);
      long daysInCycle = Math.floorMod(julianEpochDay, 1461L);
      if (daysInCycle == 1460L) {
         int year = (int)(cycle * 4L + 3L + 1L);
         return ofYearDay(year, 366);
      } else {
         int year = (int)(cycle * 4L + daysInCycle / 365L + 1L);
         int doy = (int)(daysInCycle % 365L + 1L);
         return ofYearDay(year, doy);
      }
   }

   private static JulianDate resolvePreviousValid(int prolepticYear, int month, int day) {
      switch (month) {
         case 2:
            day = Math.min(day, JulianChronology.INSTANCE.isLeapYear((long)prolepticYear) ? 29 : 28);
         case 3:
         case 5:
         case 7:
         case 8:
         case 10:
         default:
            break;
         case 4:
         case 6:
         case 9:
         case 11:
            day = Math.min(day, 30);
      }

      return new JulianDate(prolepticYear, month, day);
   }

   static JulianDate create(int prolepticYear, int month, int dayOfMonth) {
      JulianChronology.YEAR_RANGE.checkValidValue((long)prolepticYear, ChronoField.YEAR);
      ChronoField.MONTH_OF_YEAR.checkValidValue((long)month);
      ChronoField.DAY_OF_MONTH.checkValidValue((long)dayOfMonth);
      if (dayOfMonth > 28) {
         int dom = 31;
         switch (month) {
            case 2:
               dom = JulianChronology.INSTANCE.isLeapYear((long)prolepticYear) ? 29 : 28;
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            default:
               break;
            case 4:
            case 6:
            case 9:
            case 11:
               dom = 30;
         }

         if (dayOfMonth > dom) {
            if (dayOfMonth == 29) {
               throw new DateTimeException("Invalid date 'February 29' as '" + prolepticYear + "' is not a leap year");
            }

            throw new DateTimeException("Invalid date '" + Month.of(month).name() + " " + dayOfMonth + "'");
         }
      }

      return new JulianDate(prolepticYear, month, dayOfMonth);
   }

   private JulianDate(int prolepticYear, int month, int dayOfMonth) {
      this.prolepticYear = prolepticYear;
      this.month = (short)month;
      this.day = (short)dayOfMonth;
   }

   private Object readResolve() {
      return create(this.prolepticYear, this.month, this.day);
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
      return Month.of(this.month).firstDayOfYear(this.isLeapYear()) + this.day - 1;
   }

   ValueRange rangeAlignedWeekOfMonth() {
      return ValueRange.of(1L, this.month == 2 && !this.isLeapYear() ? 4L : 5L);
   }

   JulianDate resolvePrevious(int newYear, int newMonth, int dayOfMonth) {
      return resolvePreviousValid(newYear, newMonth, dayOfMonth);
   }

   public JulianChronology getChronology() {
      return JulianChronology.INSTANCE;
   }

   public JulianEra getEra() {
      return this.prolepticYear >= 1 ? JulianEra.AD : JulianEra.BC;
   }

   public int lengthOfMonth() {
      switch (this.month) {
         case 2:
            return this.isLeapYear() ? 29 : 28;
         case 3:
         case 5:
         case 7:
         case 8:
         case 10:
         default:
            return 31;
         case 4:
         case 6:
         case 9:
         case 11:
            return 30;
      }
   }

   public JulianDate with(TemporalAdjuster adjuster) {
      return (JulianDate)adjuster.adjustInto(this);
   }

   public JulianDate with(TemporalField field, long newValue) {
      return (JulianDate)super.with(field, newValue);
   }

   public JulianDate plus(TemporalAmount amount) {
      return (JulianDate)amount.addTo(this);
   }

   public JulianDate plus(long amountToAdd, TemporalUnit unit) {
      return (JulianDate)super.plus(amountToAdd, unit);
   }

   public JulianDate minus(TemporalAmount amount) {
      return (JulianDate)amount.subtractFrom(this);
   }

   public JulianDate minus(long amountToSubtract, TemporalUnit unit) {
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

   public long toEpochDay() {
      long year = (long)this.prolepticYear;
      long julianEpochDay = (year - 1L) * 365L + Math.floorDiv(year - 1L, 4L) + (long)(this.getDayOfYear() - 1);
      return julianEpochDay - 719164L;
   }
}
