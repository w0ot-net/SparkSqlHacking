package org.apache.hadoop.hive.common.type;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.hive.common.util.IntervalDayTimeUtils;
import org.apache.hive.common.util.SuppressFBWarnings;

public class HiveIntervalDayTime implements Comparable {
   protected long totalSeconds;
   protected int nanos;
   private static final String PARSE_PATTERN = "([+|-])?(\\d+) (\\d+):(\\d+):((\\d+)(\\.(\\d+))?)";
   private static final ThreadLocal PATTERN_MATCHER = new ThreadLocal() {
      protected Matcher initialValue() {
         return Pattern.compile("([+|-])?(\\d+) (\\d+):(\\d+):((\\d+)(\\.(\\d+))?)").matcher("");
      }
   };

   public HiveIntervalDayTime() {
   }

   public HiveIntervalDayTime(int days, int hours, int minutes, int seconds, int nanos) {
      this.set(days, hours, minutes, seconds, nanos);
   }

   public HiveIntervalDayTime(long seconds, int nanos) {
      this.set(seconds, nanos);
   }

   public HiveIntervalDayTime(BigDecimal seconds) {
      this.set(seconds);
   }

   public HiveIntervalDayTime(HiveIntervalDayTime other) {
      this.set(other.totalSeconds, other.nanos);
   }

   public int getDays() {
      return (int)TimeUnit.SECONDS.toDays(this.totalSeconds);
   }

   public int getHours() {
      return (int)(TimeUnit.SECONDS.toHours(this.totalSeconds) % TimeUnit.DAYS.toHours(1L));
   }

   public int getMinutes() {
      return (int)(TimeUnit.SECONDS.toMinutes(this.totalSeconds) % TimeUnit.HOURS.toMinutes(1L));
   }

   public int getSeconds() {
      return (int)(this.totalSeconds % TimeUnit.MINUTES.toSeconds(1L));
   }

   public int getNanos() {
      return this.nanos;
   }

   public long getTotalSeconds() {
      return this.totalSeconds;
   }

   public double getDouble() {
      return (double)(this.totalSeconds + (long)(this.nanos / 1000000000));
   }

   protected void normalizeSecondsAndNanos() {
      if (this.totalSeconds > 0L && this.nanos < 0) {
         --this.totalSeconds;
         this.nanos += 1000000000;
      } else if (this.totalSeconds < 0L && this.nanos > 0) {
         ++this.totalSeconds;
         this.nanos -= 1000000000;
      }

   }

   public void set(int days, int hours, int minutes, int seconds, int nanos) {
      long totalSeconds = (long)seconds;
      totalSeconds += TimeUnit.DAYS.toSeconds((long)days);
      totalSeconds += TimeUnit.HOURS.toSeconds((long)hours);
      totalSeconds += TimeUnit.MINUTES.toSeconds((long)minutes);
      totalSeconds += TimeUnit.NANOSECONDS.toSeconds((long)nanos);
      nanos %= 1000000000;
      this.totalSeconds = totalSeconds;
      this.nanos = nanos;
      this.normalizeSecondsAndNanos();
   }

   public void set(long seconds, int nanos) {
      this.totalSeconds = seconds;
      this.nanos = nanos;
      this.normalizeSecondsAndNanos();
   }

   public void set(BigDecimal totalSecondsBd) {
      long totalSeconds = totalSecondsBd.longValue();
      BigDecimal fractionalSecs = totalSecondsBd.remainder(BigDecimal.ONE);
      int nanos = fractionalSecs.multiply(IntervalDayTimeUtils.NANOS_PER_SEC_BD).intValue();
      this.set(totalSeconds, nanos);
   }

   public void set(HiveIntervalDayTime other) {
      this.set(other.getTotalSeconds(), other.getNanos());
   }

   public HiveIntervalDayTime negate() {
      return new HiveIntervalDayTime(-this.getTotalSeconds(), -this.getNanos());
   }

   public int compareTo(HiveIntervalDayTime other) {
      long cmp = this.totalSeconds - other.totalSeconds;
      if (cmp == 0L) {
         cmp = (long)(this.nanos - other.nanos);
      }

      if (cmp != 0L) {
         cmp = cmp > 0L ? 1L : -1L;
      }

      return (int)cmp;
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (!(obj instanceof HiveIntervalDayTime)) {
         return false;
      } else {
         return 0 == this.compareTo((HiveIntervalDayTime)obj);
      }
   }

   @SuppressFBWarnings(
      value = {"CN_IMPLEMENTS_CLONE_BUT_NOT_CLONEABLE"},
      justification = "Intended"
   )
   public Object clone() {
      return new HiveIntervalDayTime(this.totalSeconds, this.nanos);
   }

   public int hashCode() {
      return (new HashCodeBuilder()).append(this.totalSeconds).append(this.nanos).toHashCode();
   }

   public String toString() {
      boolean isNegative = this.totalSeconds < 0L || this.nanos < 0;
      String daySecondSignStr = isNegative ? "-" : "";
      return String.format("%s%d %02d:%02d:%02d.%09d", daySecondSignStr, Math.abs(this.getDays()), Math.abs(this.getHours()), Math.abs(this.getMinutes()), Math.abs(this.getSeconds()), Math.abs(this.getNanos()));
   }

   public static HiveIntervalDayTime valueOf(String strVal) {
      HiveIntervalDayTime result = null;
      if (strVal == null) {
         throw new IllegalArgumentException("Interval day-time string was null");
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

               int days = sign * IntervalDayTimeUtils.parseNumericValueWithRange("day", patternMatcher.group(2), 0, Integer.MAX_VALUE);
               byte hours = (byte)(sign * IntervalDayTimeUtils.parseNumericValueWithRange("hour", patternMatcher.group(3), 0, 23));
               byte minutes = (byte)(sign * IntervalDayTimeUtils.parseNumericValueWithRange("minute", patternMatcher.group(4), 0, 59));
               int seconds = 0;
               int nanos = 0;
               field = patternMatcher.group(5);
               if (field != null) {
                  BigDecimal bdSeconds = new BigDecimal(field);
                  if (bdSeconds.compareTo(IntervalDayTimeUtils.MAX_INT_BD) > 0) {
                     throw new IllegalArgumentException("seconds value of " + bdSeconds + " too large");
                  }

                  seconds = sign * bdSeconds.intValue();
                  nanos = sign * bdSeconds.subtract(new BigDecimal(bdSeconds.toBigInteger())).multiply(IntervalDayTimeUtils.NANOS_PER_SEC_BD).intValue();
               }

               result = new HiveIntervalDayTime(days, hours, minutes, seconds, nanos);
               return result;
            } catch (Exception err) {
               throw new IllegalArgumentException("Error parsing interval day-time string: " + strVal, err);
            }
         } else {
            throw new IllegalArgumentException("Interval string does not match day-time format of 'd h:m:s.n': " + strVal);
         }
      }
   }
}
