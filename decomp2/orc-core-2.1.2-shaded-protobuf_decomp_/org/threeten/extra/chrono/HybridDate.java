package org.threeten.extra.chrono;

import java.io.Serializable;
import java.time.Clock;
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
import java.time.temporal.TemporalQueries;
import java.time.temporal.TemporalQuery;
import java.time.temporal.TemporalUnit;
import java.time.temporal.ValueRange;
import java.util.Objects;

public final class HybridDate extends AbstractDate implements ChronoLocalDate, Serializable {
   private static final long serialVersionUID = -9626278512674L;
   private final LocalDate isoDate;
   private final transient JulianDate julianDate;

   public static HybridDate now() {
      return now(Clock.systemDefaultZone());
   }

   public static HybridDate now(ZoneId zone) {
      return now(Clock.system(zone));
   }

   public static HybridDate now(Clock clock) {
      return new HybridDate(LocalDate.now(clock));
   }

   public static HybridDate of(int prolepticYear, int month, int dayOfMonth) {
      return create(prolepticYear, month, dayOfMonth);
   }

   public static HybridDate from(TemporalAccessor temporal) {
      return temporal instanceof HybridDate ? (HybridDate)temporal : new HybridDate(LocalDate.from(temporal));
   }

   static HybridDate ofYearDay(int prolepticYear, int dayOfYear) {
      if (prolepticYear >= 1582 && (prolepticYear != 1582 || dayOfYear > 246)) {
         if (prolepticYear == 1582) {
            LocalDate iso = LocalDate.ofYearDay(prolepticYear, dayOfYear + 10);
            return new HybridDate(iso);
         } else {
            LocalDate iso = LocalDate.ofYearDay(prolepticYear, dayOfYear);
            return new HybridDate(iso);
         }
      } else {
         JulianDate julian = JulianDate.ofYearDay(prolepticYear, dayOfYear);
         return new HybridDate(julian);
      }
   }

   static HybridDate ofEpochDay(long epochDay) {
      return new HybridDate(LocalDate.ofEpochDay(epochDay));
   }

   static HybridDate create(int prolepticYear, int month, int dayOfMonth) {
      if (prolepticYear < 1582) {
         JulianDate julian = JulianDate.of(prolepticYear, month, dayOfMonth);
         return new HybridDate(julian);
      } else {
         LocalDate iso = LocalDate.of(prolepticYear, month, dayOfMonth);
         if (iso.isBefore(HybridChronology.CUTOVER)) {
            JulianDate julian = JulianDate.of(prolepticYear, month, dayOfMonth);
            return new HybridDate(julian);
         } else {
            return new HybridDate(iso);
         }
      }
   }

   HybridDate(LocalDate isoDate) {
      Objects.requireNonNull(isoDate, "isoDate");
      this.isoDate = isoDate;
      this.julianDate = isoDate.isBefore(HybridChronology.CUTOVER) ? JulianDate.from(isoDate) : null;
   }

   HybridDate(JulianDate julianDate) {
      Objects.requireNonNull(julianDate, "julianDate");
      this.isoDate = LocalDate.from(julianDate);
      this.julianDate = this.isoDate.isBefore(HybridChronology.CUTOVER) ? julianDate : null;
   }

   private Object readResolve() {
      return new HybridDate(this.isoDate);
   }

   private boolean isCutoverYear() {
      return this.isoDate.getYear() == 1582 && this.isoDate.getDayOfYear() > 10;
   }

   private boolean isCutoverMonth() {
      return this.isoDate.getYear() == 1582 && this.isoDate.getMonthValue() == 9 && this.isoDate.getDayOfMonth() > 10;
   }

   int getAlignedDayOfWeekInMonth() {
      return this.isCutoverMonth() && this.julianDate == null ? (this.getDayOfMonth() - 1 - 10) % this.lengthOfWeek() + 1 : super.getAlignedDayOfWeekInMonth();
   }

   int getAlignedWeekOfMonth() {
      return this.isCutoverMonth() && this.julianDate == null ? (this.getDayOfMonth() - 1 - 10) / this.lengthOfWeek() + 1 : super.getAlignedWeekOfMonth();
   }

   int getProlepticYear() {
      return this.julianDate != null ? this.julianDate.getProlepticYear() : this.isoDate.getYear();
   }

   int getMonth() {
      return this.julianDate != null ? this.julianDate.getMonth() : this.isoDate.getMonthValue();
   }

   int getDayOfMonth() {
      return this.julianDate != null ? this.julianDate.getDayOfMonth() : this.isoDate.getDayOfMonth();
   }

   int getDayOfYear() {
      if (this.julianDate != null) {
         return this.julianDate.getDayOfYear();
      } else {
         return this.isoDate.getYear() == 1582 ? this.isoDate.getDayOfYear() - 10 : this.isoDate.getDayOfYear();
      }
   }

   public ValueRange rangeChrono(ChronoField field) {
      switch (field) {
         case DAY_OF_MONTH:
            if (this.isCutoverMonth()) {
               return ValueRange.of(1L, 30L);
            }

            return ValueRange.of(1L, (long)this.lengthOfMonth());
         case DAY_OF_YEAR:
            return ValueRange.of(1L, (long)this.lengthOfYear());
         case ALIGNED_WEEK_OF_MONTH:
            return this.rangeAlignedWeekOfMonth();
         case ALIGNED_WEEK_OF_YEAR:
            if (this.isCutoverYear()) {
               return ValueRange.of(1L, 51L);
            }

            return ChronoField.ALIGNED_WEEK_OF_YEAR.range();
         default:
            return this.getChronology().range(field);
      }
   }

   ValueRange rangeAlignedWeekOfMonth() {
      return this.isCutoverMonth() ? ValueRange.of(1L, 3L) : ValueRange.of(1L, this.getMonth() == 2 && !this.isLeapYear() ? 4L : 5L);
   }

   HybridDate resolvePrevious(int year, int month, int dayOfMonth) {
      switch (month) {
         case 2:
            dayOfMonth = Math.min(dayOfMonth, this.getChronology().isLeapYear((long)year) ? 29 : 28);
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
            dayOfMonth = Math.min(dayOfMonth, 30);
      }

      return create(year, month, dayOfMonth);
   }

   public HybridChronology getChronology() {
      return HybridChronology.INSTANCE;
   }

   public JulianEra getEra() {
      return this.getProlepticYear() >= 1 ? JulianEra.AD : JulianEra.BC;
   }

   public int lengthOfMonth() {
      if (this.isCutoverMonth()) {
         return 19;
      } else {
         return this.julianDate != null ? this.julianDate.lengthOfMonth() : this.isoDate.lengthOfMonth();
      }
   }

   public int lengthOfYear() {
      if (this.isCutoverYear()) {
         return 355;
      } else {
         return this.julianDate != null ? this.julianDate.lengthOfYear() : this.isoDate.lengthOfYear();
      }
   }

   public HybridDate with(TemporalAdjuster adjuster) {
      return (HybridDate)adjuster.adjustInto(this);
   }

   public HybridDate with(TemporalField field, long newValue) {
      return (HybridDate)super.with(field, newValue);
   }

   public HybridDate plus(TemporalAmount amount) {
      return (HybridDate)amount.addTo(this);
   }

   public HybridDate plus(long amountToAdd, TemporalUnit unit) {
      return (HybridDate)super.plus(amountToAdd, unit);
   }

   public HybridDate minus(TemporalAmount amount) {
      return (HybridDate)amount.subtractFrom(this);
   }

   public HybridDate minus(long amountToSubtract, TemporalUnit unit) {
      return amountToSubtract == Long.MIN_VALUE ? this.plus(Long.MAX_VALUE, unit).plus(1L, unit) : this.plus(-amountToSubtract, unit);
   }

   public ChronoLocalDateTime atTime(LocalTime localTime) {
      return super.atTime(localTime);
   }

   public long until(Temporal endExclusive, TemporalUnit unit) {
      return super.until(from(endExclusive), unit);
   }

   public ChronoPeriod until(ChronoLocalDate endDateExclusive) {
      HybridDate end = from(endDateExclusive);
      long totalMonths = end.getProlepticMonth() - this.getProlepticMonth();
      int days = end.getDayOfMonth() - this.getDayOfMonth();
      if (totalMonths == 0L && this.isCutoverMonth()) {
         if (this.julianDate != null && end.julianDate == null) {
            days -= 10;
         } else if (this.julianDate == null && end.julianDate != null) {
            days += 10;
         }
      } else if (totalMonths > 0L) {
         if (this.julianDate != null && end.julianDate == null) {
            AbstractDate calcDate = this.plusMonths(totalMonths);
            days = (int)(end.toEpochDay() - calcDate.toEpochDay());
         }

         if (days < 0) {
            --totalMonths;
            AbstractDate calcDate = this.plusMonths(totalMonths);
            days = (int)(end.toEpochDay() - calcDate.toEpochDay());
         }
      } else if (totalMonths < 0L && days > 0) {
         ++totalMonths;
         AbstractDate calcDate = this.plusMonths(totalMonths);
         days = (int)(end.toEpochDay() - calcDate.toEpochDay());
      }

      int years = Math.toIntExact(totalMonths / (long)this.lengthOfYearInMonths());
      int months = (int)(totalMonths % (long)this.lengthOfYearInMonths());
      return this.getChronology().period(years, months, days);
   }

   public long toEpochDay() {
      return this.isoDate.toEpochDay();
   }

   public Object query(TemporalQuery query) {
      return query == TemporalQueries.localDate() ? this.isoDate : super.query(query);
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (obj instanceof HybridDate) {
         HybridDate otherDate = (HybridDate)obj;
         return this.isoDate.equals(otherDate.isoDate);
      } else {
         return false;
      }
   }

   public int hashCode() {
      return this.getChronology().getId().hashCode() ^ this.isoDate.hashCode();
   }
}
