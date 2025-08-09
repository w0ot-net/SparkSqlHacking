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

public final class EthiopicChronology extends AbstractNileChronology implements Serializable {
   public static final EthiopicChronology INSTANCE = new EthiopicChronology();
   private static final long serialVersionUID = 53287687268768L;

   private Object readResolve() {
      return INSTANCE;
   }

   public String getId() {
      return "Ethiopic";
   }

   public String getCalendarType() {
      return "ethiopic";
   }

   public EthiopicDate date(Era era, int yearOfEra, int month, int dayOfMonth) {
      return this.date(this.prolepticYear(era, yearOfEra), month, dayOfMonth);
   }

   public EthiopicDate date(int prolepticYear, int month, int dayOfMonth) {
      return EthiopicDate.of(prolepticYear, month, dayOfMonth);
   }

   public EthiopicDate dateYearDay(Era era, int yearOfEra, int dayOfYear) {
      return this.dateYearDay(this.prolepticYear(era, yearOfEra), dayOfYear);
   }

   public EthiopicDate dateYearDay(int prolepticYear, int dayOfYear) {
      return EthiopicDate.ofYearDay(prolepticYear, dayOfYear);
   }

   public EthiopicDate dateEpochDay(long epochDay) {
      return EthiopicDate.ofEpochDay(epochDay);
   }

   public EthiopicDate dateNow() {
      return EthiopicDate.now();
   }

   public EthiopicDate dateNow(ZoneId zone) {
      return EthiopicDate.now(zone);
   }

   public EthiopicDate dateNow(Clock clock) {
      return EthiopicDate.now(clock);
   }

   public EthiopicDate date(TemporalAccessor temporal) {
      return EthiopicDate.from(temporal);
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
      if (!(era instanceof EthiopicEra)) {
         throw new ClassCastException("Era must be EthiopicEra");
      } else {
         return era == EthiopicEra.INCARNATION ? yearOfEra : 1 - yearOfEra;
      }
   }

   public EthiopicEra eraOf(int eraValue) {
      return EthiopicEra.of(eraValue);
   }

   public List eras() {
      return Arrays.asList(EthiopicEra.values());
   }

   public EthiopicDate resolveDate(Map fieldValues, ResolverStyle resolverStyle) {
      return (EthiopicDate)super.resolveDate(fieldValues, resolverStyle);
   }
}
