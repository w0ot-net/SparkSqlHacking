package org.threeten.extra.chrono;

import java.io.Serializable;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.time.chrono.ChronoLocalDateTime;
import java.time.chrono.ChronoZonedDateTime;
import java.time.chrono.Era;
import java.time.format.ResolverStyle;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalField;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public final class CopticChronology extends AbstractNileChronology implements Serializable {
   public static final CopticChronology INSTANCE = new CopticChronology();
   private static final long serialVersionUID = 7291205177830286973L;

   private Object readResolve() {
      return INSTANCE;
   }

   public String getId() {
      return "Coptic";
   }

   public String getCalendarType() {
      return "coptic";
   }

   public CopticDate date(Era era, int yearOfEra, int month, int dayOfMonth) {
      return this.date(this.prolepticYear(era, yearOfEra), month, dayOfMonth);
   }

   public CopticDate date(int prolepticYear, int month, int dayOfMonth) {
      return CopticDate.of(prolepticYear, month, dayOfMonth);
   }

   public CopticDate dateYearDay(Era era, int yearOfEra, int dayOfYear) {
      return this.dateYearDay(this.prolepticYear(era, yearOfEra), dayOfYear);
   }

   public CopticDate dateYearDay(int prolepticYear, int dayOfYear) {
      return CopticDate.ofYearDay(prolepticYear, dayOfYear);
   }

   public CopticDate dateEpochDay(long epochDay) {
      return CopticDate.ofEpochDay(epochDay);
   }

   public CopticDate dateNow() {
      return CopticDate.now();
   }

   public CopticDate dateNow(ZoneId zone) {
      return CopticDate.now(zone);
   }

   public CopticDate dateNow(Clock clock) {
      return CopticDate.now(clock);
   }

   public CopticDate date(TemporalAccessor temporal) {
      return CopticDate.from(temporal);
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

   public int prolepticYear(Era era, int yearOfEra) {
      if (!(era instanceof CopticEra)) {
         throw new ClassCastException("Era must be CopticEra");
      } else {
         return era == CopticEra.AM ? yearOfEra : 1 - yearOfEra;
      }
   }

   public CopticEra eraOf(int eraValue) {
      return CopticEra.of(eraValue);
   }

   public List eras() {
      return Arrays.asList(CopticEra.values());
   }

   public CopticDate resolveDate(Map fieldValues, ResolverStyle resolverStyle) {
      return (CopticDate)super.resolveDate(fieldValues, resolverStyle);
   }
}
