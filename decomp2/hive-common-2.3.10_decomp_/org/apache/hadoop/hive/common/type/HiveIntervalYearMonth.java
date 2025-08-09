package org.apache.hadoop.hive.common.type;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.hive.common.util.DateUtils;

public class HiveIntervalYearMonth implements Comparable {
   protected int totalMonths;
   protected static final int MONTHS_PER_YEAR = 12;
   private static final String PARSE_PATTERN = "([+|-])?(\\d+)-(\\d+)";
   private static final ThreadLocal PATTERN_MATCHER = new ThreadLocal() {
      protected Matcher initialValue() {
         return Pattern.compile("([+|-])?(\\d+)-(\\d+)").matcher("");
      }
   };

   public HiveIntervalYearMonth() {
   }

   public HiveIntervalYearMonth(int years, int months) {
      this.set(years, months);
   }

   public HiveIntervalYearMonth(int totalMonths) {
      this.set(totalMonths);
   }

   public HiveIntervalYearMonth(HiveIntervalYearMonth hiveInterval) {
      this.set(hiveInterval.getTotalMonths());
   }

   public int getYears() {
      return this.totalMonths / 12;
   }

   public int getMonths() {
      return this.totalMonths % 12;
   }

   public int getTotalMonths() {
      return this.totalMonths;
   }

   public void set(int years, int months) {
      this.totalMonths = months;
      this.totalMonths += years * 12;
   }

   public void set(int totalMonths) {
      this.totalMonths = totalMonths;
   }

   public void set(HiveIntervalYearMonth other) {
      this.set(other.getTotalMonths());
   }

   public HiveIntervalYearMonth negate() {
      return new HiveIntervalYearMonth(-this.getTotalMonths());
   }

   public int compareTo(HiveIntervalYearMonth other) {
      int cmp = this.getTotalMonths() - other.getTotalMonths();
      if (cmp != 0) {
         cmp = cmp > 0 ? 1 : -1;
      }

      return cmp;
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (!(obj instanceof HiveIntervalYearMonth)) {
         return false;
      } else {
         return 0 == this.compareTo((HiveIntervalYearMonth)obj);
      }
   }

   public int hashCode() {
      return this.totalMonths;
   }

   public String toString() {
      String yearMonthSignStr = this.totalMonths >= 0 ? "" : "-";
      return String.format("%s%d-%d", yearMonthSignStr, Math.abs(this.getYears()), Math.abs(this.getMonths()));
   }

   public static HiveIntervalYearMonth valueOf(String strVal) {
      HiveIntervalYearMonth result = null;
      if (strVal == null) {
         throw new IllegalArgumentException("Interval year-month string was null");
      } else {
         Matcher patternMatcher = (Matcher)PATTERN_MATCHER.get();
         patternMatcher.reset(strVal);
         if (patternMatcher.matches()) {
            try {
               int sign = 1;
               String field = patternMatcher.group(1);
               if (field != null && field.equals("-")) {
                  sign = -1;
               }

               int years = sign * DateUtils.parseNumericValueWithRange("year", patternMatcher.group(2), 0, Integer.MAX_VALUE);
               byte months = (byte)(sign * DateUtils.parseNumericValueWithRange("month", patternMatcher.group(3), 0, 11));
               result = new HiveIntervalYearMonth(years, months);
               return result;
            } catch (Exception err) {
               throw new IllegalArgumentException("Error parsing interval year-month string: " + strVal, err);
            }
         } else {
            throw new IllegalArgumentException("Interval string does not match year-month format of 'y-m': " + strVal);
         }
      }
   }
}
