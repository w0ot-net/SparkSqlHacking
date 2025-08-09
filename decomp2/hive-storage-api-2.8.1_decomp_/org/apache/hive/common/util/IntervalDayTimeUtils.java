package org.apache.hive.common.util;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import org.apache.hadoop.hive.common.type.HiveIntervalDayTime;

public class IntervalDayTimeUtils {
   private static final ThreadLocal dateFormatLocal = new ThreadLocal() {
      protected SimpleDateFormat initialValue() {
         return new SimpleDateFormat("yyyy-MM-dd");
      }
   };
   public static final int NANOS_PER_SEC = 1000000000;
   public static final BigDecimal MAX_INT_BD = new BigDecimal(Integer.MAX_VALUE);
   public static final BigDecimal NANOS_PER_SEC_BD = new BigDecimal(1000000000);

   public static SimpleDateFormat getDateFormat() {
      return (SimpleDateFormat)dateFormatLocal.get();
   }

   public static int parseNumericValueWithRange(String fieldName, String strVal, int minValue, int maxValue) throws IllegalArgumentException {
      int result = 0;
      if (strVal != null) {
         result = Integer.parseInt(strVal);
         if (result < minValue || result > maxValue) {
            throw new IllegalArgumentException(String.format("%s value %d outside range [%d, %d]", fieldName, result, minValue, maxValue));
         }
      }

      return result;
   }

   public static long getIntervalDayTimeTotalNanos(HiveIntervalDayTime intervalDayTime) {
      return intervalDayTime.getTotalSeconds() * 1000000000L + (long)intervalDayTime.getNanos();
   }

   public static void setIntervalDayTimeTotalNanos(HiveIntervalDayTime intervalDayTime, long totalNanos) {
      intervalDayTime.set(totalNanos / 1000000000L, (int)(totalNanos % 1000000000L));
   }

   public static long getIntervalDayTimeTotalSecondsFromTotalNanos(long totalNanos) {
      return totalNanos / 1000000000L;
   }

   public static int getIntervalDayTimeNanosFromTotalNanos(long totalNanos) {
      return (int)(totalNanos % 1000000000L);
   }
}
