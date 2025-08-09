package org.apache.hadoop.hive.common.type;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class CalendarUtils {
   public static final long SWITCHOVER_MILLIS;
   public static final long SWITCHOVER_DAYS;
   private static final String DATE = "yyyy-MM-dd";
   private static final String TIME = "yyyy-MM-dd HH:mm:ss.SSS";
   private static final TimeZone UTC = TimeZone.getTimeZone("UTC");
   private static final ThreadLocal HYBRID_DATE_FORMAT = ThreadLocal.withInitial(() -> createFormatter("yyyy-MM-dd", false));
   private static final ThreadLocal HYBRID_TIME_FORMAT = ThreadLocal.withInitial(() -> createFormatter("yyyy-MM-dd HH:mm:ss.SSS", false));
   private static final ThreadLocal PROLEPTIC_DATE_FORMAT = ThreadLocal.withInitial(() -> createFormatter("yyyy-MM-dd", true));
   private static final ThreadLocal PROLEPTIC_TIME_FORMAT = ThreadLocal.withInitial(() -> createFormatter("yyyy-MM-dd HH:mm:ss.SSS", true));

   private static SimpleDateFormat createFormatter(String fmt, boolean proleptic) {
      SimpleDateFormat result = new SimpleDateFormat(fmt);
      GregorianCalendar calendar = new GregorianCalendar(UTC);
      if (proleptic) {
         calendar.setGregorianChange(new Date(Long.MIN_VALUE));
      }

      result.setCalendar(calendar);
      return result;
   }

   public static int convertDateToProleptic(int hybrid) {
      int proleptic = hybrid;
      if ((long)hybrid < SWITCHOVER_DAYS) {
         String dateStr = ((SimpleDateFormat)HYBRID_DATE_FORMAT.get()).format(new Date(TimeUnit.DAYS.toMillis((long)hybrid)));

         try {
            proleptic = (int)TimeUnit.MILLISECONDS.toDays(((SimpleDateFormat)PROLEPTIC_DATE_FORMAT.get()).parse(dateStr).getTime());
         } catch (ParseException e) {
            throw new IllegalArgumentException("Can't parse " + dateStr, e);
         }
      }

      return proleptic;
   }

   public static int convertDateToHybrid(int proleptic) {
      int hyrbid = proleptic;
      if ((long)proleptic < SWITCHOVER_DAYS) {
         String dateStr = ((SimpleDateFormat)PROLEPTIC_DATE_FORMAT.get()).format(new Date(TimeUnit.DAYS.toMillis((long)proleptic)));

         try {
            hyrbid = (int)TimeUnit.MILLISECONDS.toDays(((SimpleDateFormat)HYBRID_DATE_FORMAT.get()).parse(dateStr).getTime());
         } catch (ParseException e) {
            throw new IllegalArgumentException("Can't parse " + dateStr, e);
         }
      }

      return hyrbid;
   }

   public static int convertDate(int original, boolean fromProleptic, boolean toProleptic) {
      if (fromProleptic != toProleptic) {
         return toProleptic ? convertDateToProleptic(original) : convertDateToHybrid(original);
      } else {
         return original;
      }
   }

   public static long convertTime(long original, boolean fromProleptic, boolean toProleptic) {
      if (fromProleptic != toProleptic) {
         return toProleptic ? convertTimeToProleptic(original) : convertTimeToHybrid(original);
      } else {
         return original;
      }
   }

   public static long convertTimeToProleptic(long hybrid) {
      long proleptic = hybrid;
      if (hybrid < SWITCHOVER_MILLIS) {
         String dateStr = ((SimpleDateFormat)HYBRID_TIME_FORMAT.get()).format(new Date(hybrid));

         try {
            proleptic = ((SimpleDateFormat)PROLEPTIC_TIME_FORMAT.get()).parse(dateStr).getTime();
         } catch (ParseException e) {
            throw new IllegalArgumentException("Can't parse " + dateStr, e);
         }
      }

      return proleptic;
   }

   public static long convertTimeToHybrid(long proleptic) {
      long hybrid = proleptic;
      if (proleptic < SWITCHOVER_MILLIS) {
         String dateStr = ((SimpleDateFormat)PROLEPTIC_TIME_FORMAT.get()).format(new Date(proleptic));

         try {
            hybrid = ((SimpleDateFormat)HYBRID_TIME_FORMAT.get()).parse(dateStr).getTime();
         } catch (ParseException e) {
            throw new IllegalArgumentException("Can't parse " + dateStr, e);
         }
      }

      return hybrid;
   }

   public static String formatDate(long epochDay, boolean useProleptic) {
      long millis = TimeUnit.DAYS.toMillis(epochDay);
      return useProleptic ? ((SimpleDateFormat)PROLEPTIC_DATE_FORMAT.get()).format(millis) : ((SimpleDateFormat)HYBRID_DATE_FORMAT.get()).format(millis);
   }

   public static String formatTimestamp(long epochMillis, boolean useProleptic) {
      Date date = new Date(epochMillis);
      return useProleptic ? ((SimpleDateFormat)PROLEPTIC_TIME_FORMAT.get()).format(date) : ((SimpleDateFormat)HYBRID_TIME_FORMAT.get()).format(date);
   }

   private CalendarUtils() {
      throw new UnsupportedOperationException();
   }

   static {
      try {
         SWITCHOVER_MILLIS = ((SimpleDateFormat)HYBRID_DATE_FORMAT.get()).parse("1582-10-15").getTime();
         SWITCHOVER_DAYS = TimeUnit.MILLISECONDS.toDays(SWITCHOVER_MILLIS);
      } catch (ParseException e) {
         throw new IllegalArgumentException("Can't parse switch over date", e);
      }
   }
}
