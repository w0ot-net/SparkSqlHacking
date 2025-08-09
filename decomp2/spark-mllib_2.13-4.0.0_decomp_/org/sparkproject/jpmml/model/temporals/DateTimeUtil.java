package org.sparkproject.jpmml.model.temporals;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class DateTimeUtil {
   private DateTimeUtil() {
   }

   public static Date parseDate(String value) {
      try {
         return Date.parse(value);
      } catch (DateTimeException dte) {
         throw new IllegalArgumentException(value, dte);
      }
   }

   public static Time parseTime(String value) {
      try {
         return Time.parse(value);
      } catch (DateTimeException dte) {
         throw new IllegalArgumentException(value, dte);
      }
   }

   public static DateTime parseDateTime(String value) {
      try {
         return DateTime.parse(value);
      } catch (DateTimeException dte) {
         throw new IllegalArgumentException(value, dte);
      }
   }

   public static DaysSinceDate parseDaysSinceDate(Date epoch, String value) {
      try {
         return new DaysSinceDate(epoch, LocalDate.parse(value));
      } catch (DateTimeException dte) {
         throw new IllegalArgumentException(value, dte);
      }
   }

   public static SecondsSinceMidnight parseSecondsSinceMidnight(String value) {
      try {
         return SecondsSinceMidnight.parse(value);
      } catch (DateTimeException dte) {
         throw new IllegalArgumentException(value, dte);
      }
   }

   public static SecondsSinceDate parseSecondsSinceDate(Date epoch, String value) {
      try {
         return new SecondsSinceDate(epoch, LocalDateTime.parse(value));
      } catch (DateTimeException dte) {
         throw new IllegalArgumentException(value, dte);
      }
   }
}
