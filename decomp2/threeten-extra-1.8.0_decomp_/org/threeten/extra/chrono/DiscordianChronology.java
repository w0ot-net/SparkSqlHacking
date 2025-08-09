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

public final class DiscordianChronology extends AbstractChronology implements Serializable {
   public static final DiscordianChronology INSTANCE = new DiscordianChronology();
   private static final long serialVersionUID = 1075529146344250850L;
   static final int OFFSET_FROM_ISO_0000 = 1166;
   static final int DAYS_IN_MONTH = 73;
   static final int DAYS_IN_WEEK = 5;
   static final int MONTHS_IN_YEAR = 5;
   static final int WEEKS_IN_YEAR = 73;
   static final ValueRange YEAR_RANGE = ValueRange.of(1L, 999999L);
   static final ValueRange MONTH_OF_YEAR_RANGE = ValueRange.of(0L, 1L, 5L, 5L);
   static final ValueRange DAY_OF_MONTH_RANGE = ValueRange.of(0L, 1L, 0L, 73L);
   static final ValueRange EPOCH_DAY_RANGE = ValueRange.of(-1145400L, 365242134L);
   private static final ValueRange PROLEPTIC_MONTH_RANGE = ValueRange.of(0L, 4999999L);
   private static final ValueRange DAY_OF_WEEK_RANGE = ValueRange.of(0L, 1L, 0L, 5L);
   private static final ValueRange ALIGNED_DOW_OF_YEAR_RANGE = ValueRange.of(0L, 1L, 5L, 5L);
   private static final ValueRange WEEK_OF_MONTH_RANGE = ValueRange.of(0L, 1L, 0L, 15L);
   private static final ValueRange WEEK_OF_YEAR_RANGE = ValueRange.of(0L, 1L, 73L, 73L);
   private static final ValueRange ERA_RANGE = ValueRange.of(1L, 1L);

   private Object readResolve() {
      return INSTANCE;
   }

   public String getId() {
      return "Discordian";
   }

   public String getCalendarType() {
      return "discordian";
   }

   public DiscordianDate date(Era era, int yearOfEra, int month, int dayOfMonth) {
      return this.date(this.prolepticYear(era, yearOfEra), month, dayOfMonth);
   }

   public DiscordianDate date(int prolepticYear, int month, int dayOfMonth) {
      return DiscordianDate.of(prolepticYear, month, dayOfMonth);
   }

   public DiscordianDate dateYearDay(Era era, int yearOfEra, int dayOfYear) {
      return this.dateYearDay(this.prolepticYear(era, yearOfEra), dayOfYear);
   }

   public DiscordianDate dateYearDay(int prolepticYear, int dayOfYear) {
      return DiscordianDate.ofYearDay(prolepticYear, dayOfYear);
   }

   public DiscordianDate dateEpochDay(long epochDay) {
      return DiscordianDate.ofEpochDay(epochDay);
   }

   public DiscordianDate dateNow() {
      return DiscordianDate.now();
   }

   public DiscordianDate dateNow(ZoneId zone) {
      return DiscordianDate.now(zone);
   }

   public DiscordianDate dateNow(Clock clock) {
      return DiscordianDate.now(clock);
   }

   public DiscordianDate date(TemporalAccessor temporal) {
      return DiscordianDate.from(temporal);
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
      long offsetYear = prolepticYear - 1166L;
      return offsetYear % 4L == 0L && (offsetYear % 400L == 0L || offsetYear % 100L != 0L);
   }

   public int prolepticYear(Era era, int yearOfEra) {
      if (!DiscordianEra.YOLD.equals(era)) {
         throw new ClassCastException("Era must be DiscordianEra.YOLD");
      } else {
         return YEAR_RANGE.checkValidIntValue((long)yearOfEra, ChronoField.YEAR_OF_ERA);
      }
   }

   public DiscordianEra eraOf(int era) {
      return DiscordianEra.of(era);
   }

   public List eras() {
      return Arrays.asList(DiscordianEra.values());
   }

   public ValueRange range(ChronoField field) {
      switch (field) {
         case ALIGNED_DAY_OF_WEEK_IN_YEAR:
            return ALIGNED_DOW_OF_YEAR_RANGE;
         case ALIGNED_DAY_OF_WEEK_IN_MONTH:
         case DAY_OF_WEEK:
            return DAY_OF_WEEK_RANGE;
         case ALIGNED_WEEK_OF_MONTH:
            return WEEK_OF_MONTH_RANGE;
         case ALIGNED_WEEK_OF_YEAR:
            return WEEK_OF_YEAR_RANGE;
         case DAY_OF_MONTH:
            return DAY_OF_MONTH_RANGE;
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

   public DiscordianDate resolveDate(Map fieldValues, ResolverStyle resolverStyle) {
      return (DiscordianDate)super.resolveDate(fieldValues, resolverStyle);
   }
}
