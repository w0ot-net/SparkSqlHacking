package org.threeten.extra.chrono;

import java.io.Serializable;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.time.chrono.AbstractChronology;
import java.time.chrono.ChronoLocalDateTime;
import java.time.chrono.ChronoZonedDateTime;
import java.time.chrono.Era;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.ValueRange;
import java.util.Arrays;
import java.util.List;

public final class InternationalFixedChronology extends AbstractChronology implements Serializable {
   public static final InternationalFixedChronology INSTANCE = new InternationalFixedChronology();
   private static final long serialVersionUID = -8252657100538813526L;
   static final int DAYS_IN_WEEK = 7;
   static final int WEEKS_IN_MONTH = 4;
   static final int MONTHS_IN_YEAR = 13;
   static final int DAYS_IN_MONTH = 28;
   static final int DAYS_IN_LONG_MONTH = 29;
   static final int DAYS_IN_YEAR = 365;
   static final int WEEKS_IN_YEAR = 52;
   static final int DAYS_PER_CYCLE = 146097;
   static final long DAYS_0000_TO_1970 = 719528L;
   static final ValueRange YEAR_RANGE = ValueRange.of(1L, 1000000L);
   static final ValueRange EPOCH_DAY_RANGE = ValueRange.of(-719528L, 365000000L + getLeapYearsBefore(1000000L) - 719528L);
   private static final ValueRange PROLEPTIC_MONTH_RANGE = ValueRange.of(13L, 12999999L);
   static final ValueRange DAY_OF_MONTH_RANGE = ValueRange.of(1L, 29L);
   static final ValueRange DAY_OF_YEAR_NORMAL_RANGE = ValueRange.of(1L, 365L);
   static final ValueRange DAY_OF_YEAR_LEAP_RANGE = ValueRange.of(1L, 366L);
   static final ValueRange MONTH_OF_YEAR_RANGE = ValueRange.of(1L, 13L);
   static final ValueRange ERA_RANGE = ValueRange.of(1L, 1L);
   static final ValueRange EMPTY_RANGE = ValueRange.of(0L, 0L);

   private Object readResolve() {
      return INSTANCE;
   }

   public String getId() {
      return "Ifc";
   }

   public String getCalendarType() {
      return null;
   }

   public InternationalFixedDate date(Era era, int yearOfEra, int month, int dayOfMonth) {
      return this.date(this.prolepticYear(era, yearOfEra), month, dayOfMonth);
   }

   public InternationalFixedDate date(int prolepticYear, int month, int dayOfMonth) {
      return InternationalFixedDate.of(prolepticYear, month, dayOfMonth);
   }

   public InternationalFixedDate dateYearDay(Era era, int yearOfEra, int dayOfYear) {
      return this.dateYearDay(this.prolepticYear(era, yearOfEra), dayOfYear);
   }

   public InternationalFixedDate dateYearDay(int prolepticYear, int dayOfYear) {
      return InternationalFixedDate.ofYearDay(prolepticYear, dayOfYear);
   }

   public InternationalFixedDate dateEpochDay(long epochDay) {
      return InternationalFixedDate.ofEpochDay(epochDay);
   }

   public InternationalFixedDate dateNow() {
      return InternationalFixedDate.now();
   }

   public InternationalFixedDate dateNow(ZoneId zone) {
      return InternationalFixedDate.now(zone);
   }

   public InternationalFixedDate dateNow(Clock clock) {
      return InternationalFixedDate.now(clock);
   }

   public InternationalFixedDate date(TemporalAccessor temporal) {
      return InternationalFixedDate.from(temporal);
   }

   public ChronoLocalDateTime localDateTime(TemporalAccessor temporal) {
      return super.localDateTime(temporal);
   }

   public ChronoZonedDateTime zonedDateTime(TemporalAccessor temporal) {
      return super.zonedDateTime(temporal);
   }

   public ChronoZonedDateTime zonedDateTime(Instant instant, ZoneId zone) {
      return super.zonedDateTime(instant, zone);
   }

   public boolean isLeapYear(long year) {
      return (year & 3L) == 0L && (year % 100L != 0L || year % 400L == 0L);
   }

   public InternationalFixedEra eraOf(int eraValue) {
      return InternationalFixedEra.of(eraValue);
   }

   public List eras() {
      return Arrays.asList(InternationalFixedEra.values());
   }

   public ValueRange range(ChronoField field) {
      switch (field) {
         case ALIGNED_DAY_OF_WEEK_IN_YEAR:
         case ALIGNED_DAY_OF_WEEK_IN_MONTH:
         case DAY_OF_WEEK:
            return ValueRange.of(0L, 1L, 0L, 7L);
         case ALIGNED_WEEK_OF_MONTH:
            return ValueRange.of(0L, 1L, 0L, 4L);
         case ALIGNED_WEEK_OF_YEAR:
            return ValueRange.of(0L, 1L, 0L, 52L);
         case DAY_OF_MONTH:
            return DAY_OF_MONTH_RANGE;
         case DAY_OF_YEAR:
            return ChronoField.DAY_OF_YEAR.range();
         case EPOCH_DAY:
            return EPOCH_DAY_RANGE;
         case ERA:
            return ERA_RANGE;
         case MONTH_OF_YEAR:
            return MONTH_OF_YEAR_RANGE;
         case PROLEPTIC_MONTH:
            return PROLEPTIC_MONTH_RANGE;
         case YEAR_OF_ERA:
         case YEAR:
            return YEAR_RANGE;
         default:
            return field.range();
      }
   }

   public int prolepticYear(Era era, int yearOfEra) {
      if (!(era instanceof InternationalFixedEra)) {
         throw new ClassCastException("Invalid era: " + era);
      } else {
         return YEAR_RANGE.checkValidIntValue((long)yearOfEra, ChronoField.YEAR_OF_ERA);
      }
   }

   static long getLeapYearsBefore(long prolepticYear) {
      long yearBefore = prolepticYear - 1L;
      return yearBefore / 4L - yearBefore / 100L + yearBefore / 400L;
   }
}
