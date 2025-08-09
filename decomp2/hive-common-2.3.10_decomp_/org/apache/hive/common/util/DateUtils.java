package org.apache.hive.common.util;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

public class DateUtils {
   private static final ThreadLocal dateFormatLocal = new ThreadLocal() {
      protected SimpleDateFormat initialValue() {
         return new SimpleDateFormat("yyyy-MM-dd");
      }
   };
   public static final int NANOS_PER_SEC = 1000000000;
   public static final BigDecimal MAX_INT_BD = new BigDecimal(Integer.MAX_VALUE);
   public static final BigDecimal NANOS_PER_SEC_BD = new BigDecimal(1000000000);
   private static final String[] FIELD_NAME = new String[]{"ERA", "YEAR", "MONTH", "WEEK_OF_YEAR", "WEEK_OF_MONTH", "DAY_OF_MONTH", "DAY_OF_YEAR", "DAY_OF_WEEK", "DAY_OF_WEEK_IN_MONTH", "AM_PM", "HOUR", "HOUR_OF_DAY", "MINUTE", "SECOND", "MILLISECOND", "ZONE_OFFSET", "DST_OFFSET"};

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

   public static String getFieldName(int field) {
      return FIELD_NAME[field];
   }
}
