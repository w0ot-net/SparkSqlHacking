package org.threeten.extra.chrono;

import java.io.Serializable;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.chrono.AbstractChronology;
import java.time.chrono.ChronoLocalDateTime;
import java.time.chrono.ChronoZonedDateTime;
import java.time.chrono.Era;
import java.time.chrono.IsoChronology;
import java.time.format.ResolverStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalField;
import java.time.temporal.ValueRange;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public final class HybridChronology extends AbstractChronology implements Serializable {
   public static final HybridChronology INSTANCE = new HybridChronology();
   public static final LocalDate CUTOVER = LocalDate.of(1582, 10, 15);
   static final int CUTOVER_DAYS = 10;
   static final int CUTOVER_YEAR = 1582;
   private static final long serialVersionUID = 87235724675472658L;
   static final ValueRange DOY_RANGE = ValueRange.of(1L, 355L, 366L);
   static final ValueRange ALIGNED_WOM_RANGE = ValueRange.of(1L, 3L, 5L);
   static final ValueRange ALIGNED_WOY_RANGE = ValueRange.of(1L, 51L, 53L);
   static final ValueRange YEAR_RANGE = ValueRange.of(-999998L, 999999L);
   static final ValueRange YOE_RANGE = ValueRange.of(1L, 999999L);
   static final ValueRange PROLEPTIC_MONTH_RANGE = ValueRange.of(-11999976L, 11999999L);

   private Object readResolve() {
      return INSTANCE;
   }

   public LocalDate getCutover() {
      return CUTOVER;
   }

   public String getId() {
      return "Hybrid";
   }

   public String getCalendarType() {
      return null;
   }

   public HybridDate date(Era era, int yearOfEra, int month, int dayOfMonth) {
      return this.date(this.prolepticYear(era, yearOfEra), month, dayOfMonth);
   }

   public HybridDate date(int prolepticYear, int month, int dayOfMonth) {
      return HybridDate.of(prolepticYear, month, dayOfMonth);
   }

   public HybridDate dateYearDay(Era era, int yearOfEra, int dayOfYear) {
      return this.dateYearDay(this.prolepticYear(era, yearOfEra), dayOfYear);
   }

   public HybridDate dateYearDay(int prolepticYear, int dayOfYear) {
      return HybridDate.ofYearDay(prolepticYear, dayOfYear);
   }

   public HybridDate dateEpochDay(long epochDay) {
      return HybridDate.ofEpochDay(epochDay);
   }

   public HybridDate dateNow() {
      return HybridDate.now();
   }

   public HybridDate dateNow(ZoneId zone) {
      return HybridDate.now(zone);
   }

   public HybridDate dateNow(Clock clock) {
      return HybridDate.now(clock);
   }

   public HybridDate date(TemporalAccessor temporal) {
      return HybridDate.from(temporal);
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
      return prolepticYear <= 1582L ? JulianChronology.INSTANCE.isLeapYear(prolepticYear) : IsoChronology.INSTANCE.isLeapYear(prolepticYear);
   }

   public int prolepticYear(Era era, int yearOfEra) {
      if (!(era instanceof JulianEra)) {
         throw new ClassCastException("Era must be JulianEra");
      } else {
         return era == JulianEra.AD ? yearOfEra : 1 - yearOfEra;
      }
   }

   public JulianEra eraOf(int eraValue) {
      return JulianEra.of(eraValue);
   }

   public List eras() {
      return Arrays.asList(JulianEra.values());
   }

   public ValueRange range(ChronoField field) {
      switch (field) {
         case DAY_OF_YEAR -> {
            return DOY_RANGE;
         }
         case ALIGNED_WEEK_OF_MONTH -> {
            return ALIGNED_WOM_RANGE;
         }
         case ALIGNED_WEEK_OF_YEAR -> {
            return ALIGNED_WOY_RANGE;
         }
         case PROLEPTIC_MONTH -> {
            return PROLEPTIC_MONTH_RANGE;
         }
         case YEAR_OF_ERA -> {
            return YOE_RANGE;
         }
         case YEAR -> {
            return YEAR_RANGE;
         }
         default -> {
            return field.range();
         }
      }
   }

   public HybridDate resolveDate(Map fieldValues, ResolverStyle resolverStyle) {
      return (HybridDate)super.resolveDate(fieldValues, resolverStyle);
   }
}
