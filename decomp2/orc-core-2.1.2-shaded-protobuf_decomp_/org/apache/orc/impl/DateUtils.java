package org.apache.orc.impl;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.chrono.IsoChronology;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;
import java.util.concurrent.TimeUnit;
import org.threeten.extra.chrono.HybridChronology;

public class DateUtils {
   private static final ZoneId UTC = ZoneId.of("UTC");
   private static final ZoneId LOCAL = ZoneId.systemDefault();
   private static final long SWITCHOVER_MILLIS;
   private static final long SWITCHOVER_DAYS;
   private static final DateTimeFormatter HYBRID_DATE_FORMAT;
   private static final DateTimeFormatter PROLEPTIC_DATE_FORMAT;
   private static final DateTimeFormatter HYBRID_UTC_TIME_FORMAT;
   private static final DateTimeFormatter HYBRID_LOCAL_TIME_FORMAT;
   private static final DateTimeFormatter PROLEPTIC_UTC_TIME_FORMAT;
   private static final DateTimeFormatter PROLEPTIC_LOCAL_TIME_FORMAT;

   public static int convertDateToProleptic(int hybrid) {
      int proleptic = hybrid;
      if ((long)hybrid < SWITCHOVER_DAYS) {
         String dateStr = HYBRID_DATE_FORMAT.format(LocalDate.ofEpochDay((long)hybrid));
         proleptic = (int)LocalDate.from(PROLEPTIC_DATE_FORMAT.parse(dateStr)).toEpochDay();
      }

      return proleptic;
   }

   public static int convertDateToHybrid(int proleptic) {
      int hybrid = proleptic;
      if ((long)proleptic < SWITCHOVER_DAYS) {
         String dateStr = PROLEPTIC_DATE_FORMAT.format(LocalDate.ofEpochDay((long)proleptic));
         hybrid = (int)LocalDate.from(HYBRID_DATE_FORMAT.parse(dateStr)).toEpochDay();
      }

      return hybrid;
   }

   public static long convertTimeToProleptic(long hybrid, boolean useUtc) {
      long proleptic = hybrid;
      if (hybrid < SWITCHOVER_MILLIS) {
         if (useUtc) {
            String dateStr = HYBRID_UTC_TIME_FORMAT.format(Instant.ofEpochMilli(hybrid));
            proleptic = Instant.from(PROLEPTIC_UTC_TIME_FORMAT.parse(dateStr)).toEpochMilli();
         } else {
            String dateStr = HYBRID_LOCAL_TIME_FORMAT.format(Instant.ofEpochMilli(hybrid));
            proleptic = Instant.from(PROLEPTIC_LOCAL_TIME_FORMAT.parse(dateStr)).toEpochMilli();
         }
      }

      return proleptic;
   }

   public static long convertTimeToHybrid(long proleptic, boolean useUtc) {
      long hybrid = proleptic;
      if (proleptic < SWITCHOVER_MILLIS) {
         if (useUtc) {
            String dateStr = PROLEPTIC_UTC_TIME_FORMAT.format(Instant.ofEpochMilli(proleptic));
            hybrid = Instant.from(HYBRID_UTC_TIME_FORMAT.parse(dateStr)).toEpochMilli();
         } else {
            String dateStr = PROLEPTIC_LOCAL_TIME_FORMAT.format(Instant.ofEpochMilli(proleptic));
            hybrid = Instant.from(HYBRID_LOCAL_TIME_FORMAT.parse(dateStr)).toEpochMilli();
         }
      }

      return hybrid;
   }

   public static int convertDate(int original, boolean fromProleptic, boolean toProleptic) {
      if (fromProleptic != toProleptic) {
         return toProleptic ? convertDateToProleptic(original) : convertDateToHybrid(original);
      } else {
         return original;
      }
   }

   public static long convertTime(long original, boolean fromProleptic, boolean toProleptic, boolean useUtc) {
      if (fromProleptic != toProleptic) {
         return toProleptic ? convertTimeToProleptic(original, useUtc) : convertTimeToHybrid(original, useUtc);
      } else {
         return original;
      }
   }

   public static Integer parseDate(String date, boolean fromProleptic) {
      try {
         TemporalAccessor time = (fromProleptic ? PROLEPTIC_DATE_FORMAT : HYBRID_DATE_FORMAT).parse(date);
         return (int)LocalDate.from(time).toEpochDay();
      } catch (DateTimeParseException var3) {
         return null;
      }
   }

   public static String printDate(int date, boolean fromProleptic) {
      return (fromProleptic ? PROLEPTIC_DATE_FORMAT : HYBRID_DATE_FORMAT).format(LocalDate.ofEpochDay((long)date));
   }

   public static DateTimeFormatter getTimeFormat(boolean useProleptic, boolean useUtc) {
      if (useProleptic) {
         return useUtc ? PROLEPTIC_UTC_TIME_FORMAT : PROLEPTIC_LOCAL_TIME_FORMAT;
      } else {
         return useUtc ? HYBRID_UTC_TIME_FORMAT : HYBRID_LOCAL_TIME_FORMAT;
      }
   }

   public static Long parseTime(String date, boolean fromProleptic, boolean useUtc) {
      try {
         TemporalAccessor time = getTimeFormat(fromProleptic, useUtc).parse(date);
         return Instant.from(time).toEpochMilli();
      } catch (DateTimeParseException var4) {
         return null;
      }
   }

   public static String printTime(long millis, boolean fromProleptic, boolean useUtc) {
      return getTimeFormat(fromProleptic, useUtc).format(Instant.ofEpochMilli(millis));
   }

   private DateUtils() {
      throw new UnsupportedOperationException();
   }

   static {
      HYBRID_DATE_FORMAT = ConvertTreeReaderFactory.DATE_FORMAT.withChronology(HybridChronology.INSTANCE).withZone(UTC);
      PROLEPTIC_DATE_FORMAT = DateTimeFormatter.ISO_LOCAL_DATE.withChronology(IsoChronology.INSTANCE).withZone(UTC);
      HYBRID_UTC_TIME_FORMAT = ConvertTreeReaderFactory.TIMESTAMP_FORMAT.withChronology(HybridChronology.INSTANCE).withZone(UTC);
      HYBRID_LOCAL_TIME_FORMAT = ConvertTreeReaderFactory.TIMESTAMP_FORMAT.withChronology(HybridChronology.INSTANCE).withZone(LOCAL);
      PROLEPTIC_UTC_TIME_FORMAT = ConvertTreeReaderFactory.TIMESTAMP_FORMAT.withChronology(IsoChronology.INSTANCE).withZone(UTC);
      PROLEPTIC_LOCAL_TIME_FORMAT = ConvertTreeReaderFactory.TIMESTAMP_FORMAT.withChronology(IsoChronology.INSTANCE).withZone(LOCAL);
      SWITCHOVER_DAYS = LocalDate.from(HYBRID_DATE_FORMAT.parse("1582-10-15")).toEpochDay();
      SWITCHOVER_MILLIS = TimeUnit.DAYS.toMillis(SWITCHOVER_DAYS);
   }
}
