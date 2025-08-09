package org.apache.arrow.vector.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoUnit;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class DateUtility {
   private static final String UTC = "UTC";
   public static final DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
   public static final DateTimeFormatter formatTimeStampMilli = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
   public static final DateTimeFormatter formatTimeStampTZ = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS ZZZ");
   public static final DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");
   public static DateTimeFormatter dateTimeTZFormat = null;
   public static DateTimeFormatter timeFormat = null;
   public static final int yearsToMonths = 12;
   public static final int hoursToMillis = 3600000;
   public static final int minutesToMillis = 60000;
   public static final int secondsToMillis = 1000;
   public static final int monthToStandardDays = 30;
   public static final long monthsToMillis = 2592000000L;
   public static final int daysToStandardMillis = 86400000;

   private DateUtility() {
   }

   public static DateTimeFormatter getDateTimeFormatter() {
      if (dateTimeTZFormat == null) {
         DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
         DateTimeFormatter optionalTime = DateTimeFormatter.ofPattern(" HH:mm:ss");
         DateTimeFormatter optionalSec = DateTimeFormatter.ofPattern(".SSS");
         DateTimeFormatter optionalZone = DateTimeFormatter.ofPattern(" ZZZ");
         dateTimeTZFormat = (new DateTimeFormatterBuilder()).append(dateFormatter).appendOptional(optionalTime).appendOptional(optionalSec).appendOptional(optionalZone).toFormatter();
      }

      return dateTimeTZFormat;
   }

   public static DateTimeFormatter getTimeFormatter() {
      if (timeFormat == null) {
         DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
         DateTimeFormatter optionalSec = DateTimeFormatter.ofPattern(".SSS");
         timeFormat = (new DateTimeFormatterBuilder()).append(timeFormatter).appendOptional(optionalSec).toFormatter();
      }

      return timeFormat;
   }

   public static LocalDateTime getLocalDateTimeFromEpochMilli(long epochMillis, String timeZone) {
      LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(epochMillis), TimeZone.getTimeZone(timeZone).toZoneId());
      return localDateTime;
   }

   public static LocalDateTime getLocalDateTimeFromEpochMilli(long epochMillis) {
      return getLocalDateTimeFromEpochMilli(epochMillis, "UTC");
   }

   public static LocalDateTime getLocalDateTimeFromEpochMicro(long epochMicros, String timeZone) {
      long millis = TimeUnit.MICROSECONDS.toMillis(epochMicros);
      long addl_micros = epochMicros - millis * 1000L;
      return getLocalDateTimeFromEpochMilli(millis, timeZone).plus(addl_micros, ChronoUnit.MICROS);
   }

   public static LocalDateTime getLocalDateTimeFromEpochMicro(long epochMicros) {
      return getLocalDateTimeFromEpochMicro(epochMicros, "UTC");
   }

   public static LocalDateTime getLocalDateTimeFromEpochNano(long epochNanos, String timeZone) {
      long millis = TimeUnit.NANOSECONDS.toMillis(epochNanos);
      long addl_nanos = epochNanos - millis * 1000L * 1000L;
      return getLocalDateTimeFromEpochMilli(millis, timeZone).plusNanos(addl_nanos);
   }

   public static LocalDateTime getLocalDateTimeFromEpochNano(long epochNanos) {
      return getLocalDateTimeFromEpochNano(epochNanos, "UTC");
   }
}
