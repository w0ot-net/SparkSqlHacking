package org.threeten.extra.chrono;

import java.io.Serializable;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.time.chrono.AbstractChronology;
import java.time.chrono.ChronoLocalDateTime;
import java.time.chrono.ChronoZonedDateTime;
import java.time.chrono.Era;
import java.time.chrono.IsoEra;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.ValueRange;
import java.util.Arrays;
import java.util.List;

public final class Symmetry454Chronology extends AbstractChronology implements Serializable {
   public static final Symmetry454Chronology INSTANCE = new Symmetry454Chronology();
   private static final long serialVersionUID = -1287766365831162587L;
   static final int DAYS_IN_WEEK = 7;
   static final int MONTHS_IN_YEAR = 12;
   static final int WEEKS_IN_MONTH = 4;
   static final int WEEKS_IN_MONTH_LONG = 5;
   static final int DAYS_IN_QUARTER = 91;
   static final int DAYS_IN_YEAR = 364;
   static final int DAYS_IN_YEAR_LONG = 371;
   static final int DAYS_IN_MONTH_LONG = 35;
   static final int DAYS_IN_MONTH = 28;
   static final int WEEKS_IN_YEAR = 52;
   private static final int YEARS_IN_CYCLE = 293;
   static final int DAYS_PER_CYCLE = 107016;
   public static final long DAYS_0001_TO_1970 = 719162L;
   private static final long MAX_YEAR = 1000000L;
   static final ValueRange YEAR_RANGE = ValueRange.of(-1000000L, 1000000L);
   static final ValueRange EPOCH_DAY_RANGE = ValueRange.of(-364000000L - getLeapYearsBefore(1000000L) * 7L - 719162L, 364000000L + getLeapYearsBefore(1000000L) * 7L - 719162L);
   private static final ValueRange PROLEPTIC_MONTH_RANGE = ValueRange.of(-12000000L, 11999999L);
   static final ValueRange DAY_OF_MONTH_RANGE = ValueRange.of(1L, 28L, 35L);
   static final ValueRange DAY_OF_YEAR_RANGE = ValueRange.of(1L, 364L, 371L);
   static final ValueRange MONTH_OF_YEAR_RANGE = ValueRange.of(1L, 12L);
   static final ValueRange ERA_RANGE = ValueRange.of(0L, 1L);
   static final ValueRange EMPTY_RANGE = ValueRange.of(0L, 0L);

   private Object readResolve() {
      return INSTANCE;
   }

   public String getId() {
      return "Sym454";
   }

   public String getCalendarType() {
      return null;
   }

   public Symmetry454Date date(Era era, int yearOfEra, int month, int dayOfMonth) {
      return this.date(this.prolepticYear(era, yearOfEra), month, dayOfMonth);
   }

   public Symmetry454Date date(int prolepticYear, int month, int dayOfMonth) {
      return Symmetry454Date.of(prolepticYear, month, dayOfMonth);
   }

   public Symmetry454Date dateYearDay(Era era, int yearOfEra, int dayOfYear) {
      return this.dateYearDay(this.prolepticYear(era, yearOfEra), dayOfYear);
   }

   public Symmetry454Date dateYearDay(int prolepticYear, int dayOfYear) {
      return Symmetry454Date.ofYearDay(prolepticYear, dayOfYear);
   }

   public Symmetry454Date dateEpochDay(long epochDay) {
      return Symmetry454Date.ofEpochDay(epochDay);
   }

   public Symmetry454Date dateNow() {
      return Symmetry454Date.now();
   }

   public Symmetry454Date dateNow(ZoneId zone) {
      return Symmetry454Date.now(zone);
   }

   public Symmetry454Date dateNow(Clock clock) {
      return Symmetry454Date.now(clock);
   }

   public Symmetry454Date date(TemporalAccessor temporal) {
      return Symmetry454Date.from(temporal);
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
      return 52L > (52L * year + 146L) % 293L;
   }

   public IsoEra eraOf(int eraValue) {
      return IsoEra.of(eraValue);
   }

   public List eras() {
      return Arrays.asList(IsoEra.values());
   }

   public ValueRange range(ChronoField field) {
      switch (field) {
         case ALIGNED_DAY_OF_WEEK_IN_YEAR:
         case ALIGNED_DAY_OF_WEEK_IN_MONTH:
         case DAY_OF_WEEK:
            return ValueRange.of(1L, 7L);
         case ALIGNED_WEEK_OF_MONTH:
            return ValueRange.of(1L, 4L, 5L);
         case ALIGNED_WEEK_OF_YEAR:
            return ValueRange.of(1L, 52L, 53L);
         case DAY_OF_MONTH:
            return DAY_OF_MONTH_RANGE;
         case DAY_OF_YEAR:
            return DAY_OF_YEAR_RANGE;
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
      if (!(era instanceof IsoEra)) {
         throw new ClassCastException("Invalid era: " + era);
      } else {
         return YEAR_RANGE.checkValidIntValue((long)yearOfEra, ChronoField.YEAR_OF_ERA);
      }
   }

   public static long getLeapYearsBefore(long prolepticYear) {
      return Math.floorDiv(52L * (prolepticYear - 1L) + 146L, 293L);
   }
}
