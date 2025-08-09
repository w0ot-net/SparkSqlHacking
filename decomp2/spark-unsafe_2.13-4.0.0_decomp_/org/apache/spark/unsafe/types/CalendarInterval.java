package org.apache.spark.unsafe.types;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import org.apache.spark.annotation.Unstable;

@Unstable
public final class CalendarInterval implements Serializable, Comparable {
   public final int months;
   public final int days;
   public final long microseconds;

   public CalendarInterval(int months, int days, long microseconds) {
      this.months = months;
      this.days = days;
      this.microseconds = microseconds;
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         CalendarInterval that = (CalendarInterval)o;
         return this.months == that.months && this.days == that.days && this.microseconds == that.microseconds;
      } else {
         return false;
      }
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.months, this.days, this.microseconds});
   }

   public String toString() {
      if (this.months == 0 && this.days == 0 && this.microseconds == 0L) {
         return "0 seconds";
      } else {
         StringBuilder sb = new StringBuilder();
         if (this.months != 0) {
            this.appendUnit(sb, (long)(this.months / 12), "years");
            this.appendUnit(sb, (long)(this.months % 12), "months");
         }

         this.appendUnit(sb, (long)this.days, "days");
         if (this.microseconds != 0L) {
            long rest = this.microseconds;
            this.appendUnit(sb, rest / 3600000000L, "hours");
            rest %= 3600000000L;
            this.appendUnit(sb, rest / 60000000L, "minutes");
            rest %= 60000000L;
            if (rest != 0L) {
               String s = BigDecimal.valueOf(rest, 6).stripTrailingZeros().toPlainString();
               sb.append(s).append(" seconds ");
            }
         }

         sb.setLength(sb.length() - 1);
         return sb.toString();
      }
   }

   private void appendUnit(StringBuilder sb, long value, String unit) {
      if (value != 0L) {
         sb.append(value).append(' ').append(unit).append(' ');
      }

   }

   public Period extractAsPeriod() {
      return Period.of(0, this.months, this.days);
   }

   public Duration extractAsDuration() {
      return Duration.of(this.microseconds, ChronoUnit.MICROS);
   }

   public int compareTo(CalendarInterval o) {
      if (this.months != o.months) {
         return Integer.compare(this.months, o.months);
      } else {
         return this.days != o.days ? Integer.compare(this.days, o.days) : Long.compare(this.microseconds, o.microseconds);
      }
   }
}
