package com.ibm.icu.impl;

import com.ibm.icu.util.Calendar;
import com.ibm.icu.util.GregorianCalendar;
import com.ibm.icu.util.SimpleTimeZone;
import com.ibm.icu.util.TimeZone;
import com.ibm.icu.util.ULocale;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.ChronoLocalDateTime;
import java.time.temporal.ChronoField;
import java.time.temporal.Temporal;
import java.util.Date;

/** @deprecated */
@Deprecated
public class JavaTimeConverters {
   private static final long MILLIS_PER_DAY = 86400000L;

   private JavaTimeConverters() {
   }

   /** @deprecated */
   @Deprecated
   public static Calendar temporalToCalendar(ZonedDateTime dateTime) {
      long epochMillis = dateTime.toEpochSecond() * 1000L + (long)dateTime.get(ChronoField.MILLI_OF_SECOND);
      TimeZone icuTimeZone = zoneIdToTimeZone(dateTime.getZone());
      return millisToCalendar(epochMillis, icuTimeZone);
   }

   /** @deprecated */
   @Deprecated
   public static Calendar temporalToCalendar(OffsetTime time) {
      return temporalToCalendar(time.atDate(LocalDate.now()));
   }

   /** @deprecated */
   @Deprecated
   public static Calendar temporalToCalendar(OffsetDateTime dateTime) {
      long epochMillis = dateTime.toEpochSecond() * 1000L + (long)dateTime.get(ChronoField.MILLI_OF_SECOND);
      TimeZone icuTimeZone = zoneOffsetToTimeZone(dateTime.getOffset());
      return millisToCalendar(epochMillis, icuTimeZone);
   }

   /** @deprecated */
   @Deprecated
   static Calendar temporalToCalendar(ChronoLocalDate date) {
      long epochMillis = date.toEpochDay() * 86400000L;
      return millisToCalendar(epochMillis);
   }

   /** @deprecated */
   @Deprecated
   public static Calendar temporalToCalendar(LocalTime time) {
      long epochMillis = time.toNanoOfDay() / 1000000L;
      return millisToCalendar(epochMillis);
   }

   /** @deprecated */
   @Deprecated
   public static Calendar temporalToCalendar(LocalDateTime dateTime) {
      ZoneOffset zoneOffset = ZoneId.systemDefault().getRules().getOffset(dateTime);
      long epochMillis = dateTime.toEpochSecond(zoneOffset) * 1000L + (long)dateTime.get(ChronoField.MILLI_OF_SECOND);
      return millisToCalendar(epochMillis, TimeZone.getDefault());
   }

   /** @deprecated */
   @Deprecated
   public static Calendar temporalToCalendar(Temporal temp) {
      if (temp instanceof Instant) {
         throw new IllegalArgumentException("java.time.Instant cannot be formatted, it does not have enough information");
      } else if (temp instanceof ZonedDateTime) {
         return temporalToCalendar((ZonedDateTime)temp);
      } else if (temp instanceof OffsetDateTime) {
         return temporalToCalendar((OffsetDateTime)temp);
      } else if (temp instanceof OffsetTime) {
         return temporalToCalendar((OffsetTime)temp);
      } else if (temp instanceof LocalDate) {
         return temporalToCalendar((ChronoLocalDate)((LocalDate)temp));
      } else if (temp instanceof LocalDateTime) {
         return temporalToCalendar((LocalDateTime)temp);
      } else if (temp instanceof LocalTime) {
         return temporalToCalendar((LocalTime)temp);
      } else if (temp instanceof ChronoLocalDate) {
         return temporalToCalendar((ChronoLocalDate)temp);
      } else if (temp instanceof ChronoLocalDateTime) {
         return temporalToCalendar((Temporal)((ChronoLocalDateTime)temp));
      } else {
         throw new IllegalArgumentException("This type cannot be formatted: " + temp.getClass().getName());
      }
   }

   /** @deprecated */
   @Deprecated
   public static TimeZone zoneIdToTimeZone(ZoneId zoneId) {
      return TimeZone.getTimeZone(zoneId.getId());
   }

   /** @deprecated */
   @Deprecated
   public static TimeZone zoneOffsetToTimeZone(ZoneOffset zoneOffset) {
      return new SimpleTimeZone(zoneOffset.getTotalSeconds() * 1000, zoneOffset.getId());
   }

   private static Calendar millisToCalendar(long epochMillis) {
      return millisToCalendar(epochMillis, TimeZone.GMT_ZONE);
   }

   private static Calendar millisToCalendar(long epochMillis, TimeZone timeZone) {
      GregorianCalendar calendar = new GregorianCalendar(timeZone, ULocale.US);
      calendar.setGregorianChange(new Date(Long.MIN_VALUE));
      calendar.setTimeInMillis(epochMillis);
      return calendar;
   }
}
