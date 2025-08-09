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

public final class JulianChronology extends AbstractChronology implements Serializable {
   public static final JulianChronology INSTANCE = new JulianChronology();
   private static final long serialVersionUID = 7291205177830286973L;
   static final ValueRange YEAR_RANGE = ValueRange.of(-999998L, 999999L);
   static final ValueRange YOE_RANGE = ValueRange.of(1L, 999999L);
   static final ValueRange PROLEPTIC_MONTH_RANGE = ValueRange.of(-11999976L, 11999999L);

   private Object readResolve() {
      return INSTANCE;
   }

   public String getId() {
      return "Julian";
   }

   public String getCalendarType() {
      return "julian";
   }

   public JulianDate date(Era era, int yearOfEra, int month, int dayOfMonth) {
      return this.date(this.prolepticYear(era, yearOfEra), month, dayOfMonth);
   }

   public JulianDate date(int prolepticYear, int month, int dayOfMonth) {
      return JulianDate.of(prolepticYear, month, dayOfMonth);
   }

   public JulianDate dateYearDay(Era era, int yearOfEra, int dayOfYear) {
      return this.dateYearDay(this.prolepticYear(era, yearOfEra), dayOfYear);
   }

   public JulianDate dateYearDay(int prolepticYear, int dayOfYear) {
      return JulianDate.ofYearDay(prolepticYear, dayOfYear);
   }

   public JulianDate dateEpochDay(long epochDay) {
      return JulianDate.ofEpochDay(epochDay);
   }

   public JulianDate dateNow() {
      return JulianDate.now();
   }

   public JulianDate dateNow(ZoneId zone) {
      return JulianDate.now(zone);
   }

   public JulianDate dateNow(Clock clock) {
      return JulianDate.now(clock);
   }

   public JulianDate date(TemporalAccessor temporal) {
      return JulianDate.from(temporal);
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
      return prolepticYear % 4L == 0L;
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
         case PROLEPTIC_MONTH:
            return PROLEPTIC_MONTH_RANGE;
         case YEAR_OF_ERA:
            return YOE_RANGE;
         case YEAR:
            return YEAR_RANGE;
         default:
            return field.range();
      }
   }

   public JulianDate resolveDate(Map fieldValues, ResolverStyle resolverStyle) {
      return (JulianDate)super.resolveDate(fieldValues, resolverStyle);
   }
}
