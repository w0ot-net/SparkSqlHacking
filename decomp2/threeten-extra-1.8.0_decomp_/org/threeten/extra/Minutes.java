package org.threeten.extra;

import java.io.Serializable;
import java.time.DateTimeException;
import java.time.Duration;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAmount;
import java.time.temporal.TemporalUnit;
import java.time.temporal.UnsupportedTemporalTypeException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.joda.convert.FromString;
import org.joda.convert.ToString;

public final class Minutes implements TemporalAmount, Comparable, Serializable {
   public static final Minutes ZERO = new Minutes(0);
   private static final long serialVersionUID = 2602801843170589407L;
   private static final int MINUTES_PER_DAY = 1440;
   private static final int MINUTES_PER_HOUR = 60;
   private static final Pattern PATTERN = Pattern.compile("([-+]?)P(?:([-+]?[0-9]+)D)?(?:T(?:([-+]?[0-9]+)H)?(?:([-+]?[0-9]+)M)?)?", 2);
   private final int minutes;

   public static Minutes of(int minutes) {
      return minutes == 0 ? ZERO : new Minutes(minutes);
   }

   public static Minutes ofHours(int hours) {
      return hours == 0 ? ZERO : new Minutes(Math.multiplyExact(hours, 60));
   }

   public static Minutes from(TemporalAmount amount) {
      if (amount instanceof Minutes) {
         return (Minutes)amount;
      } else {
         Objects.requireNonNull(amount, "amount");
         int minutes = 0;

         for(TemporalUnit unit : amount.getUnits()) {
            long value = amount.get(unit);
            if (value != 0L) {
               long[] converted = Temporals.convertAmount(value, unit, ChronoUnit.MINUTES);
               if (converted[1] != 0L) {
                  throw new DateTimeException("Amount could not be converted to a whole number of minutes: " + value + " " + unit);
               }

               minutes = Math.addExact(minutes, Math.toIntExact(converted[0]));
            }
         }

         return of(minutes);
      }
   }

   @FromString
   public static Minutes parse(CharSequence text) {
      Objects.requireNonNull(text, "text");
      Matcher matcher = PATTERN.matcher(text);
      if (matcher.matches()) {
         int negate = "-".equals(matcher.group(1)) ? -1 : 1;
         String daysStr = matcher.group(2);
         String hoursStr = matcher.group(3);
         String minutesStr = matcher.group(4);
         if (daysStr != null || hoursStr != null || minutesStr != null) {
            int minutes = 0;
            if (minutesStr != null) {
               try {
                  minutes = Integer.parseInt(minutesStr);
               } catch (NumberFormatException ex) {
                  throw new DateTimeParseException("Text cannot be parsed to Minutes, non-numeric minutes", text, 0, ex);
               }
            }

            if (hoursStr != null) {
               try {
                  int hoursAsMins = Math.multiplyExact(Integer.parseInt(hoursStr), 60);
                  minutes = Math.addExact(minutes, hoursAsMins);
               } catch (NumberFormatException ex) {
                  throw new DateTimeParseException("Text cannot be parsed to Minutes, non-numeric hours", text, 0, ex);
               }
            }

            if (daysStr != null) {
               try {
                  int daysAsMins = Math.multiplyExact(Integer.parseInt(daysStr), 1440);
                  minutes = Math.addExact(minutes, daysAsMins);
               } catch (NumberFormatException ex) {
                  throw new DateTimeParseException("Text cannot be parsed to Minutes, non-numeric days", text, 0, ex);
               }
            }

            return of(Math.multiplyExact(minutes, negate));
         }
      }

      throw new DateTimeParseException("Text cannot be parsed to Minutes", text, 0);
   }

   public static Minutes between(Temporal startInclusive, Temporal endExclusive) {
      return of(Math.toIntExact(ChronoUnit.MINUTES.between(startInclusive, endExclusive)));
   }

   private Minutes(int minutes) {
      this.minutes = minutes;
   }

   private Object readResolve() {
      return of(this.minutes);
   }

   public long get(TemporalUnit unit) {
      if (unit == ChronoUnit.MINUTES) {
         return (long)this.minutes;
      } else {
         throw new UnsupportedTemporalTypeException("Unsupported unit: " + unit);
      }
   }

   public List getUnits() {
      return Collections.singletonList(ChronoUnit.MINUTES);
   }

   public int getAmount() {
      return this.minutes;
   }

   public boolean isNegative() {
      return this.getAmount() < 0;
   }

   public boolean isZero() {
      return this.getAmount() == 0;
   }

   public boolean isPositive() {
      return this.getAmount() > 0;
   }

   public Minutes plus(TemporalAmount amountToAdd) {
      return this.plus(from(amountToAdd).getAmount());
   }

   public Minutes plus(int minutes) {
      return minutes == 0 ? this : of(Math.addExact(this.minutes, minutes));
   }

   public Minutes minus(TemporalAmount amountToSubtract) {
      return this.minus(from(amountToSubtract).getAmount());
   }

   public Minutes minus(int minutes) {
      return minutes == 0 ? this : of(Math.subtractExact(this.minutes, minutes));
   }

   public Minutes multipliedBy(int scalar) {
      return scalar == 1 ? this : of(Math.multiplyExact(this.minutes, scalar));
   }

   public Minutes dividedBy(int divisor) {
      return divisor == 1 ? this : of(this.minutes / divisor);
   }

   public Minutes negated() {
      return this.multipliedBy(-1);
   }

   public Minutes abs() {
      return this.minutes < 0 ? this.negated() : this;
   }

   public Duration toDuration() {
      return Duration.ofMinutes((long)this.minutes);
   }

   public Temporal addTo(Temporal temporal) {
      if (this.minutes != 0) {
         temporal = temporal.plus((long)this.minutes, ChronoUnit.MINUTES);
      }

      return temporal;
   }

   public Temporal subtractFrom(Temporal temporal) {
      if (this.minutes != 0) {
         temporal = temporal.minus((long)this.minutes, ChronoUnit.MINUTES);
      }

      return temporal;
   }

   public int compareTo(Minutes otherAmount) {
      int thisValue = this.minutes;
      int otherValue = otherAmount.minutes;
      return Integer.compare(thisValue, otherValue);
   }

   public boolean equals(Object otherAmount) {
      if (this == otherAmount) {
         return true;
      } else if (otherAmount instanceof Minutes) {
         Minutes other = (Minutes)otherAmount;
         return this.minutes == other.minutes;
      } else {
         return false;
      }
   }

   public int hashCode() {
      return this.minutes;
   }

   @ToString
   public String toString() {
      return "PT" + this.minutes + "M";
   }
}
