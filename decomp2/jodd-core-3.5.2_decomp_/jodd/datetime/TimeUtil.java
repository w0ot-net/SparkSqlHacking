package jodd.datetime;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeUtil {
   public static final int SECONDS_IN_DAY = 86400;
   public static final long MILLIS_IN_DAY = 86400000L;
   private static final int[] MONTH_LENGTH = new int[]{0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
   private static final int[] LEAP_MONTH_LENGTH = new int[]{0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
   public static final SimpleDateFormat HTTP_DATE_FORMAT;

   public static int dayOfYear(int year, int month, int day) {
      int day_of_year;
      if (isLeapYear(year)) {
         day_of_year = 275 * month / 9 - (month + 9) / 12 + day - 30;
      } else {
         day_of_year = 275 * month / 9 - ((month + 9) / 12 << 1) + day - 30;
      }

      return day_of_year;
   }

   public static boolean isLeapYear(int y) {
      boolean result = false;
      if (y % 4 == 0 && (y < 1582 || y % 100 != 0 || y % 400 == 0)) {
         result = true;
      }

      return result;
   }

   public static int getMonthLength(int year, int m) {
      if (m >= 1 && m <= 12) {
         return isLeapYear(year) ? LEAP_MONTH_LENGTH[m] : MONTH_LENGTH[m];
      } else {
         return -1;
      }
   }

   public static boolean isValidDate(int year, int month, int day) {
      if (month >= 1 && month <= 12) {
         int ml = getMonthLength(year, month);
         return day >= 1 && day <= ml;
      } else {
         return false;
      }
   }

   public static boolean isValidTime(int hour, int minute, int second, int millisecond) {
      if (hour >= 0 && hour < 24) {
         if (minute >= 0 && minute < 60) {
            if (second >= 0 && second < 60) {
               return millisecond >= 0 && millisecond < 1000;
            } else {
               return false;
            }
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   public static boolean isValidDateTime(int year, int month, int day, int hour, int minute, int second, int millisecond) {
      return isValidDate(year, month, day) && isValidTime(hour, minute, second, millisecond);
   }

   public static boolean isValidDateTime(DateTimeStamp dts) {
      return isValidDate(dts.year, dts.month, dts.day) && isValidTime(dts.hour, dts.minute, dts.second, dts.millisecond);
   }

   public static JulianDateStamp toJulianDate(DateTimeStamp time) {
      return toJulianDate(time.year, time.month, time.day, time.hour, time.minute, time.second, time.millisecond);
   }

   public static JulianDateStamp toJulianDate(int param0, int param1, int param2, int param3, int param4, int param5, int param6) {
      // $FF: Couldn't be decompiled
   }

   public static DateTimeStamp fromJulianDate(double JD) {
      return fromJulianDate(new JulianDateStamp(JD));
   }

   public static DateTimeStamp fromJulianDate(JulianDateStamp jds) {
      DateTimeStamp time = new DateTimeStamp();
      int ka = (int)(jds.fraction + (double)0.5F);
      int jd = jds.integer + ka;
      double frac = jds.fraction + (double)0.5F - (double)ka + 1.0E-10;
      ka = jd;
      if (jd >= 2299161) {
         int ialp = (int)(((double)jd - 1867216.25) / (double)36524.25F);
         ka = jd + 1 + ialp - (ialp >> 2);
      }

      int kb = ka + 1524;
      int kc = (int)(((double)kb - 122.1) / (double)365.25F);
      int kd = (int)((double)kc * (double)365.25F);
      int ke = (int)((double)(kb - kd) / 30.6001);
      int day = kb - kd - (int)((double)ke * 30.6001);
      int month;
      if (ke > 13) {
         month = ke - 13;
      } else {
         month = ke - 1;
      }

      if (month == 2 && day > 28) {
         day = 29;
      }

      int year;
      if (month == 2 && day == 29 && ke == 3) {
         year = kc - 4716;
      } else if (month > 2) {
         year = kc - 4716;
      } else {
         year = kc - 4715;
      }

      time.year = year;
      time.month = month;
      time.day = day;
      double d_hour = frac * (double)24.0F;
      time.hour = (int)d_hour;
      double d_minute = (d_hour - (double)time.hour) * (double)60.0F;
      time.minute = (int)d_minute;
      double d_second = (d_minute - (double)time.minute) * (double)60.0F;
      time.second = (int)d_second;
      double d_millis = (d_second - (double)time.second) * (double)1000.0F;
      time.millisecond = (int)((d_millis * (double)10.0F + (double)0.5F) / (double)10.0F);
      return time;
   }

   public static int toCalendarMonth(int month) {
      return month - 1;
   }

   public static int toCalendarDayOfWeek(int dayOfWeek) {
      return dayOfWeek % 7 + 1;
   }

   public static String formatHttpDate(long millis) {
      Date date = new Date(millis);
      return HTTP_DATE_FORMAT.format(date);
   }

   static {
      HTTP_DATE_FORMAT = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
   }
}
