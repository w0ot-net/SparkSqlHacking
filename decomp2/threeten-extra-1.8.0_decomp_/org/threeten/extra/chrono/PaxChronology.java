package org.threeten.extra.chrono;

import java.io.Serializable;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.time.chrono.AbstractChronology;
import java.time.chrono.ChronoLocalDateTime;
import java.time.chrono.ChronoZonedDateTime;
import java.time.chrono.Era;
import java.time.format.ResolverStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalField;
import java.time.temporal.ValueRange;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public final class PaxChronology extends AbstractChronology implements Serializable {
   public static final PaxChronology INSTANCE = new PaxChronology();
   private static final long serialVersionUID = -7021464635577802085L;
   static final int WEEKS_IN_LEAP_MONTH = 1;
   static final int DAYS_IN_WEEK = 7;
   static final int WEEKS_IN_MONTH = 4;
   static final int MONTHS_IN_YEAR = 13;
   static final int DAYS_IN_MONTH = 28;
   static final int DAYS_IN_YEAR = 364;
   static final int WEEKS_IN_YEAR = 52;
   static final ValueRange ALIGNED_WEEK_OF_MONTH_RANGE = ValueRange.of(1L, 1L, 4L);
   static final ValueRange ALIGNED_WEEK_OF_YEAR_RANGE = ValueRange.of(1L, 52L, 53L);
   static final ValueRange DAY_OF_MONTH_RANGE = ValueRange.of(1L, 7L, 28L);
   static final ValueRange DAY_OF_YEAR_RANGE = ValueRange.of(1L, 364L, 371L);
   static final ValueRange MONTH_OF_YEAR_RANGE = ValueRange.of(1L, 13L, 14L);

   private Object readResolve() {
      return INSTANCE;
   }

   public String getId() {
      return "Pax";
   }

   public String getCalendarType() {
      return "pax";
   }

   public PaxDate date(Era era, int yearOfEra, int month, int dayOfMonth) {
      return this.date(this.prolepticYear(era, yearOfEra), month, dayOfMonth);
   }

   public PaxDate date(int prolepticYear, int month, int dayOfMonth) {
      return PaxDate.of(prolepticYear, month, dayOfMonth);
   }

   public PaxDate dateYearDay(Era era, int yearOfEra, int dayOfYear) {
      return this.dateYearDay(this.prolepticYear(era, yearOfEra), dayOfYear);
   }

   public PaxDate dateYearDay(int prolepticYear, int dayOfYear) {
      return PaxDate.ofYearDay(prolepticYear, dayOfYear);
   }

   public PaxDate dateEpochDay(long epochDay) {
      return PaxDate.ofEpochDay(epochDay);
   }

   public PaxDate dateNow() {
      return PaxDate.now();
   }

   public PaxDate dateNow(ZoneId zone) {
      return PaxDate.now(zone);
   }

   public PaxDate dateNow(Clock clock) {
      return PaxDate.now(clock);
   }

   public PaxDate date(TemporalAccessor temporal) {
      return PaxDate.from(temporal);
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

   public boolean isLeapYear(long prolepticYear) {
      long lastTwoDigits = prolepticYear % 100L;
      return Math.abs(lastTwoDigits) == 99L || prolepticYear % 400L != 0L && (lastTwoDigits == 0L || lastTwoDigits % 6L == 0L);
   }

   public int prolepticYear(Era era, int yearOfEra) {
      if (!(era instanceof PaxEra)) {
         throw new ClassCastException("Era must be PaxEra");
      } else {
         return era == PaxEra.CE ? yearOfEra : 1 - yearOfEra;
      }
   }

   public PaxEra eraOf(int eraValue) {
      return PaxEra.of(eraValue);
   }

   public List eras() {
      return Arrays.asList(PaxEra.values());
   }

   public ValueRange range(ChronoField field) {
      switch (field) {
         case ALIGNED_WEEK_OF_MONTH:
            return ALIGNED_WEEK_OF_MONTH_RANGE;
         case ALIGNED_WEEK_OF_YEAR:
            return ALIGNED_WEEK_OF_YEAR_RANGE;
         case DAY_OF_MONTH:
            return DAY_OF_MONTH_RANGE;
         case DAY_OF_YEAR:
            return DAY_OF_YEAR_RANGE;
         case MONTH_OF_YEAR:
            return MONTH_OF_YEAR_RANGE;
         default:
            return field.range();
      }
   }

   public PaxDate resolveDate(Map fieldValues, ResolverStyle resolverStyle) {
      return (PaxDate)super.resolveDate(fieldValues, resolverStyle);
   }
}
