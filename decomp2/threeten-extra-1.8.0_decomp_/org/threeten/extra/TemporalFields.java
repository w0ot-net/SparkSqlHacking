package org.threeten.extra;

import java.time.DateTimeException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.IsoChronology;
import java.time.format.ResolverStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.IsoFields;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalUnit;
import java.time.temporal.UnsupportedTemporalTypeException;
import java.time.temporal.ValueRange;
import java.util.Map;

public final class TemporalFields {
   public static final TemporalField DAY_OF_HALF;
   public static final TemporalField HALF_OF_YEAR;
   public static final TemporalUnit HALF_YEARS;

   private TemporalFields() {
   }

   static {
      DAY_OF_HALF = TemporalFields.DayOfHalfField.INSTANCE;
      HALF_OF_YEAR = TemporalFields.HalfOfYearField.INSTANCE;
      HALF_YEARS = TemporalFields.HalfUnit.INSTANCE;
   }

   private static enum DayOfHalfField implements TemporalField {
      INSTANCE;

      private static final ValueRange RANGE = ValueRange.of(1L, 181L, 184L);
      private static final long serialVersionUID = 262362728L;

      public TemporalUnit getBaseUnit() {
         return ChronoUnit.DAYS;
      }

      public TemporalUnit getRangeUnit() {
         return TemporalFields.HALF_YEARS;
      }

      public boolean isDateBased() {
         return true;
      }

      public boolean isTimeBased() {
         return false;
      }

      public ValueRange range() {
         return RANGE;
      }

      public boolean isSupportedBy(TemporalAccessor temporal) {
         return temporal.isSupported(ChronoField.DAY_OF_YEAR) && temporal.isSupported(ChronoField.MONTH_OF_YEAR) && temporal.isSupported(ChronoField.YEAR);
      }

      public ValueRange rangeRefinedBy(TemporalAccessor temporal) {
         if (!temporal.isSupported(this)) {
            throw new DateTimeException("Unsupported field: DayOfHalf");
         } else {
            long hoy = temporal.getLong(TemporalFields.HALF_OF_YEAR);
            if (hoy == 1L) {
               long year = temporal.getLong(ChronoField.YEAR);
               return IsoChronology.INSTANCE.isLeapYear(year) ? ValueRange.of(1L, 182L) : ValueRange.of(1L, 181L);
            } else {
               return hoy == 2L ? ValueRange.of(1L, 184L) : this.range();
            }
         }
      }

      public long getFrom(TemporalAccessor temporal) {
         if (!this.isSupportedBy(temporal)) {
            throw new UnsupportedTemporalTypeException("Unsupported field: DayOfHalf");
         } else {
            int doy = temporal.get(ChronoField.DAY_OF_YEAR);
            int moy = temporal.get(ChronoField.MONTH_OF_YEAR);
            long year = temporal.getLong(ChronoField.YEAR);
            return moy <= 6 ? (long)doy : (long)(doy - 181 - (IsoChronology.INSTANCE.isLeapYear(year) ? 1 : 0));
         }
      }

      public Temporal adjustInto(Temporal temporal, long newValue) {
         long curValue = this.getFrom(temporal);
         this.range().checkValidValue(newValue, this);
         return temporal.with(ChronoField.DAY_OF_YEAR, temporal.getLong(ChronoField.DAY_OF_YEAR) + (newValue - curValue));
      }

      public ChronoLocalDate resolve(Map fieldValues, TemporalAccessor partialTemporal, ResolverStyle resolverStyle) {
         Long yearLong = (Long)fieldValues.get(ChronoField.YEAR);
         Long hoyLong = (Long)fieldValues.get(TemporalFields.HALF_OF_YEAR);
         if (yearLong != null && hoyLong != null) {
            int y = ChronoField.YEAR.checkValidIntValue(yearLong);
            long doh = (Long)fieldValues.get(TemporalFields.DAY_OF_HALF);
            LocalDate date;
            if (resolverStyle == ResolverStyle.LENIENT) {
               date = LocalDate.of(y, 1, 1).plusMonths(Math.multiplyExact(Math.subtractExact(hoyLong, 1L), 6L));
               doh = Math.subtractExact(doh, 1L);
            } else {
               int qoy = TemporalFields.HALF_OF_YEAR.range().checkValidIntValue(hoyLong, TemporalFields.HALF_OF_YEAR);
               date = LocalDate.of(y, (qoy - 1) * 6 + 1, 1);
               if (doh < 1L || doh > 181L) {
                  if (resolverStyle == ResolverStyle.STRICT) {
                     this.rangeRefinedBy(date).checkValidValue(doh, this);
                  } else {
                     this.range().checkValidValue(doh, this);
                  }
               }

               --doh;
            }

            fieldValues.remove(this);
            fieldValues.remove(ChronoField.YEAR);
            fieldValues.remove(TemporalFields.HALF_OF_YEAR);
            return date.plusDays(doh);
         } else {
            return null;
         }
      }

      public String toString() {
         return "DayOfHalf";
      }
   }

   private static enum HalfOfYearField implements TemporalField {
      INSTANCE;

      private static final long serialVersionUID = -29115701L;

      public TemporalUnit getBaseUnit() {
         return TemporalFields.HALF_YEARS;
      }

      public TemporalUnit getRangeUnit() {
         return ChronoUnit.YEARS;
      }

      public boolean isDateBased() {
         return true;
      }

      public boolean isTimeBased() {
         return false;
      }

      public ValueRange range() {
         return ValueRange.of(1L, 2L);
      }

      public boolean isSupportedBy(TemporalAccessor temporal) {
         return temporal.isSupported(IsoFields.QUARTER_OF_YEAR);
      }

      public ValueRange rangeRefinedBy(TemporalAccessor temporal) {
         return this.range();
      }

      public long getFrom(TemporalAccessor temporal) {
         if (!this.isSupportedBy(temporal)) {
            throw new UnsupportedTemporalTypeException("Unsupported field: HalfOfYear");
         } else {
            long qoy = (long)temporal.get(IsoFields.QUARTER_OF_YEAR);
            return qoy <= 2L ? 1L : 2L;
         }
      }

      public Temporal adjustInto(Temporal temporal, long newValue) {
         long curValue = this.getFrom(temporal);
         this.range().checkValidValue(newValue, this);
         return temporal.with(ChronoField.MONTH_OF_YEAR, temporal.getLong(ChronoField.MONTH_OF_YEAR) + (newValue - curValue) * 6L);
      }

      public String toString() {
         return "HalfOfYear";
      }
   }

   private static enum HalfUnit implements TemporalUnit {
      INSTANCE;

      public Duration getDuration() {
         return Duration.ofSeconds(15778476L);
      }

      public boolean isDurationEstimated() {
         return true;
      }

      public boolean isDateBased() {
         return true;
      }

      public boolean isTimeBased() {
         return false;
      }

      public boolean isSupportedBy(Temporal temporal) {
         return temporal.isSupported(IsoFields.QUARTER_OF_YEAR);
      }

      public Temporal addTo(Temporal temporal, long amount) {
         return temporal.plus(Math.multiplyExact(amount, 2L), IsoFields.QUARTER_YEARS);
      }

      public long between(Temporal temporal1Inclusive, Temporal temporal2Exclusive) {
         return temporal1Inclusive.getClass() != temporal2Exclusive.getClass() ? temporal1Inclusive.until(temporal2Exclusive, this) : temporal1Inclusive.until(temporal2Exclusive, IsoFields.QUARTER_YEARS) / 2L;
      }

      public String toString() {
         return "HalfYears";
      }
   }
}
