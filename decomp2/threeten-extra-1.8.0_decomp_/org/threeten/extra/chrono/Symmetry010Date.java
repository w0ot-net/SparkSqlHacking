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
import java.time.chrono.IsoEra;
import java.time.temporal.ChronoField;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAmount;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalUnit;
import java.time.temporal.UnsupportedTemporalTypeException;
import java.time.temporal.ValueRange;

public final class Symmetry010Date extends AbstractDate implements ChronoLocalDate, Serializable {
   private static final long serialVersionUID = -8275627894629629L;
   private final int prolepticYear;
   private final int month;
   private final int day;
   private final transient int dayOfYear;
   private static final int[] dayOfMonthOffset = new int[]{5, 0, 2};

   public static Symmetry010Date now() {
      return now(Clock.systemDefaultZone());
   }

   public static Symmetry010Date now(ZoneId zone) {
      return now(Clock.system(zone));
   }

   public static Symmetry010Date now(Clock clock) {
      LocalDate now = LocalDate.now(clock);
      return ofEpochDay(now.toEpochDay());
   }

   public static Symmetry010Date of(int prolepticYear, int month, int dayOfMonth) {
      return create(prolepticYear, month, dayOfMonth);
   }

   public static Symmetry010Date from(TemporalAccessor temporal) {
      return temporal instanceof Symmetry010Date ? (Symmetry010Date)temporal : ofEpochDay(temporal.getLong(ChronoField.EPOCH_DAY));
   }

   static Symmetry010Date ofYearDay(int prolepticYear, int dayOfYear) {
      Symmetry010Chronology.YEAR_RANGE.checkValidValue((long)prolepticYear, ChronoField.YEAR_OF_ERA);
      Symmetry010Chronology.DAY_OF_YEAR_RANGE.checkValidValue((long)dayOfYear, ChronoField.DAY_OF_YEAR);
      boolean leap = Symmetry010Chronology.INSTANCE.isLeapYear((long)prolepticYear);
      if (dayOfYear > 364 && !leap) {
         throw new DateTimeException("Invalid date 'DayOfYear " + dayOfYear + "' as '" + prolepticYear + "' is not a leap year");
      } else {
         int offset = Math.min(dayOfYear, 364) - 1;
         int quarter = offset / 91;
         int day = dayOfYear - 1 - quarter * 91 + 1;
         int month = 1 + quarter * 3;
         if (day > 61) {
            month += 2;
            day -= 61;
         } else if (day > 30) {
            ++month;
            day -= 30;
         }

         return new Symmetry010Date(prolepticYear, month, day);
      }
   }

   static Symmetry010Date ofEpochDay(long epochDay) {
      Symmetry010Chronology.EPOCH_DAY_RANGE.checkValidValue(epochDay + 3L, ChronoField.EPOCH_DAY);
      long zeroDay = epochDay + 719162L + 1L;
      long year = 1L + 293L * zeroDay / 107016L;
      long doy = zeroDay - (364L * (year - 1L) + Symmetry010Chronology.getLeapYearsBefore(year) * 7L);
      if (doy < 1L) {
         --year;
         doy += Symmetry010Chronology.INSTANCE.isLeapYear(year) ? 371L : 364L;
      }

      int diy = Symmetry010Chronology.INSTANCE.isLeapYear(year) ? 371 : 364;
      if (doy > (long)diy) {
         doy -= (long)diy;
         ++year;
      }

      return ofYearDay((int)year, (int)doy);
   }

   private static Symmetry010Date resolvePreviousValid(int prolepticYear, int month, int dayOfMonth) {
      int monthR = Math.min(month, 12);
      int dayR = Math.min(dayOfMonth, monthR == 12 && Symmetry010Chronology.INSTANCE.isLeapYear((long)prolepticYear) ? 37 : (monthR % 3 == 2 ? 31 : 30));
      return create(prolepticYear, monthR, dayR);
   }

   static Symmetry010Date create(int prolepticYear, int month, int dayOfMonth) {
      Symmetry010Chronology.YEAR_RANGE.checkValidValue((long)prolepticYear, ChronoField.YEAR_OF_ERA);
      Symmetry010Chronology.MONTH_OF_YEAR_RANGE.checkValidValue((long)month, ChronoField.MONTH_OF_YEAR);
      Symmetry010Chronology.DAY_OF_MONTH_RANGE.checkValidValue((long)dayOfMonth, ChronoField.DAY_OF_MONTH);
      if (dayOfMonth > 30) {
         if (month == 12) {
            if (!Symmetry010Chronology.INSTANCE.isLeapYear((long)prolepticYear)) {
               throw new DateTimeException("Invalid Leap Day as '" + prolepticYear + "' is not a leap year");
            }
         } else if (month % 3 == 2 && dayOfMonth > 31 || month % 3 != 2) {
            throw new DateTimeException("Invalid date: " + prolepticYear + '/' + month + '/' + dayOfMonth);
         }
      }

      return new Symmetry010Date(prolepticYear, month, dayOfMonth);
   }

   private Symmetry010Date(int prolepticYear, int month, int dayOfMonth) {
      this.prolepticYear = prolepticYear;
      this.month = month;
      this.day = dayOfMonth;
      this.dayOfYear = 30 * (month - 1) + month / 3 + dayOfMonth;
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
      return 12;
   }

   int getDayOfWeek() {
      return (this.dayOfYear - 1 + this.getDayOfMonthOffset()) % 7 + 1;
   }

   long getProlepticWeek() {
      return (long)(this.prolepticYear * 52) + Symmetry010Chronology.getLeapYearsBefore((long)this.prolepticYear) + (long)((this.dayOfYear - 1) / 7) - 1L;
   }

   private int getDayOfMonthOffset() {
      return dayOfMonthOffset[this.month % 3];
   }

   public boolean isLeapWeek() {
      return this.isLeapYear() && this.dayOfYear > 364;
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
               return ValueRange.of(1L, 7L);
            case ALIGNED_WEEK_OF_MONTH:
               return ValueRange.of(1L, (long)(this.lengthOfMonth() / 7));
            case ALIGNED_WEEK_OF_YEAR:
               return ValueRange.of(1L, (long)(52 + (this.isLeapYear() ? 1 : 0)));
            case DAY_OF_MONTH:
               return ValueRange.of(1L, (long)this.lengthOfMonth());
            case DAY_OF_YEAR:
               return ValueRange.of(1L, (long)this.lengthOfYear());
            case EPOCH_DAY:
               return Symmetry010Chronology.EPOCH_DAY_RANGE;
            case ERA:
               return Symmetry010Chronology.ERA_RANGE;
            case MONTH_OF_YEAR:
               return Symmetry010Chronology.MONTH_OF_YEAR_RANGE;
         }
      }

      return super.range(field);
   }

   ValueRange rangeAlignedWeekOfMonth() {
      return ValueRange.of(1L, 4L);
   }

   Symmetry010Date resolvePrevious(int newYear, int newMonth, int dayOfMonth) {
      return resolvePreviousValid(newYear, newMonth, dayOfMonth);
   }

   public Symmetry010Chronology getChronology() {
      return Symmetry010Chronology.INSTANCE;
   }

   public IsoEra getEra() {
      return this.prolepticYear >= 1 ? IsoEra.CE : IsoEra.BCE;
   }

   public int lengthOfMonth() {
      return this.isLeapYear() && this.month == 12 ? 37 : (this.month % 3 == 2 ? 31 : 30);
   }

   public int lengthOfYear() {
      return this.isLeapYear() ? 371 : 364;
   }

   public Symmetry010Date with(TemporalAdjuster adjuster) {
      return (Symmetry010Date)adjuster.adjustInto(this);
   }

   public Symmetry010Date with(TemporalField field, long newValue) {
      if (field instanceof ChronoField) {
         if (newValue == 0L) {
            return this;
         }

         ChronoField f = (ChronoField)field;
         this.getChronology().range(f).checkValidValue(newValue, f);
         int nval = (int)newValue;
         switch (f) {
            case DAY_OF_WEEK:
               int week = (this.dayOfYear - 1) / 7;
               int yd = 7 * week + nval;
               return ofYearDay(this.prolepticYear, yd);
            case DAY_OF_MONTH:
               return create(this.prolepticYear, this.month, nval);
         }
      }

      return (Symmetry010Date)super.with(field, newValue);
   }

   Symmetry010Date withDayOfYear(int value) {
      return ofYearDay(this.prolepticYear, value);
   }

   public Symmetry010Date plus(TemporalAmount amount) {
      return (Symmetry010Date)amount.addTo(this);
   }

   public Symmetry010Date plus(long amountToAdd, TemporalUnit unit) {
      return (Symmetry010Date)super.plus(amountToAdd, unit);
   }

   public Symmetry010Date minus(TemporalAmount amount) {
      return (Symmetry010Date)amount.subtractFrom(this);
   }

   public Symmetry010Date minus(long amountToSubtract, TemporalUnit unit) {
      return (Symmetry010Date)super.minus(amountToSubtract, unit);
   }

   public ChronoLocalDateTime atTime(LocalTime localTime) {
      return super.atTime(localTime);
   }

   public long until(Temporal endExclusive, TemporalUnit unit) {
      return this.until(from(endExclusive), unit);
   }

   long yearsUntil(Symmetry010Date end) {
      long startYear = (long)this.prolepticYear * 512L + (long)this.getDayOfYear();
      long endYear = (long)end.prolepticYear * 512L + (long)end.getDayOfYear();
      return (endYear - startYear) / 512L;
   }

   public ChronoPeriod until(ChronoLocalDate endDateExclusive) {
      Symmetry010Date end = from(endDateExclusive);
      int years = Math.toIntExact(this.yearsUntil(end));
      Symmetry010Date sameYearEnd = (Symmetry010Date)this.plusYears((long)years);
      int months = (int)sameYearEnd.monthsUntil(end);
      int days = (int)sameYearEnd.plusMonths((long)months).daysUntil(end);
      return this.getChronology().period(years, months, days);
   }

   long weeksUntil(AbstractDate end) {
      Symmetry010Date endDate = from(end);
      long startWeek = this.getProlepticWeek() * 8L + (long)this.getDayOfWeek();
      long endWeek = endDate.getProlepticWeek() * 8L + (long)endDate.getDayOfWeek();
      return (endWeek - startWeek) / 8L;
   }

   long monthsUntil(AbstractDate end) {
      Symmetry010Date date = from(end);
      long monthStart = this.getProlepticMonth() * 64L + (long)this.getDayOfMonth();
      long monthEnd = date.getProlepticMonth() * 64L + (long)date.getDayOfMonth();
      return (monthEnd - monthStart) / 64L;
   }

   public long toEpochDay() {
      long epochDay = (long)(this.prolepticYear - 1) * 364L + Symmetry010Chronology.getLeapYearsBefore((long)this.prolepticYear) * 7L + (long)this.dayOfYear - 719162L - 1L;
      return epochDay;
   }

   public String toString() {
      StringBuilder buf = new StringBuilder(30);
      return buf.append(this.getChronology().toString()).append(' ').append(this.getEra()).append(' ').append(this.getYearOfEra()).append(this.month < 10 && this.month > 0 ? "/0" : '/').append(this.month).append(this.day < 10 ? "/0" : '/').append(this.day).toString();
   }
}
