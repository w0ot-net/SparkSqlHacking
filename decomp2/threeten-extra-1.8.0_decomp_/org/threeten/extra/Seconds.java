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

public final class Seconds implements TemporalAmount, Comparable, Serializable {
   public static final Seconds ZERO = new Seconds(0);
   private static final long serialVersionUID = 2602801843170589407L;
   private static final int SECONDS_PER_DAY = 86400;
   private static final int SECONDS_PER_HOUR = 3600;
   private static final int SECONDS_PER_MINUTE = 60;
   private static final Pattern PATTERN = Pattern.compile("([-+]?)P(?:([-+]?[0-9]+)D)?(?:T(?:([-+]?[0-9]+)H)?(?:([-+]?[0-9]+)M)?(?:([-+]?[0-9]+)S)?)?", 2);
   private final int seconds;

   public static Seconds of(int seconds) {
      return seconds == 0 ? ZERO : new Seconds(seconds);
   }

   public static Seconds ofHours(int hours) {
      return hours == 0 ? ZERO : new Seconds(Math.multiplyExact(hours, 3600));
   }

   public static Seconds ofMinutes(int minutes) {
      return minutes == 0 ? ZERO : new Seconds(Math.multiplyExact(minutes, 60));
   }

   public static Seconds from(TemporalAmount amount) {
      if (amount instanceof Seconds) {
         return (Seconds)amount;
      } else {
         Objects.requireNonNull(amount, "amount");
         int seconds = 0;

         for(TemporalUnit unit : amount.getUnits()) {
            long value = amount.get(unit);
            if (value != 0L) {
               long[] converted = Temporals.convertAmount(value, unit, ChronoUnit.SECONDS);
               if (converted[1] != 0L) {
                  throw new DateTimeException("Amount could not be converted to a whole number of seconds: " + value + " " + unit);
               }

               seconds = Math.addExact(seconds, Math.toIntExact(converted[0]));
            }
         }

         return of(seconds);
      }
   }

   @FromString
   public static Seconds parse(CharSequence text) {
      Objects.requireNonNull(text, "text");
      Matcher matcher = PATTERN.matcher(text);
      if (matcher.matches()) {
         int negate = "-".equals(matcher.group(1)) ? -1 : 1;
         String daysStr = matcher.group(2);
         String hoursStr = matcher.group(3);
         String minutesStr = matcher.group(4);
         String secondsStr = matcher.group(5);
         if (daysStr != null || hoursStr != null || minutesStr != null || secondsStr != null) {
            int seconds = 0;
            if (secondsStr != null) {
               try {
                  seconds = Integer.parseInt(secondsStr);
               } catch (NumberFormatException ex) {
                  throw new DateTimeParseException("Text cannot be parsed to Seconds, non-numeric seconds", text, 0, ex);
               }
            }

            if (minutesStr != null) {
               try {
                  int minutesAsSecs = Math.multiplyExact(Integer.parseInt(minutesStr), 60);
                  seconds = Math.addExact(seconds, minutesAsSecs);
               } catch (NumberFormatException ex) {
                  throw new DateTimeParseException("Text cannot be parsed to Seconds, non-numeric minutes", text, 0, ex);
               }
            }

            if (hoursStr != null) {
               try {
                  int hoursAsSecs = Math.multiplyExact(Integer.parseInt(hoursStr), 3600);
                  seconds = Math.addExact(seconds, hoursAsSecs);
               } catch (NumberFormatException ex) {
                  throw new DateTimeParseException("Text cannot be parsed to Seconds, non-numeric hours", text, 0, ex);
               }
            }

            if (daysStr != null) {
               try {
                  int daysAsSecs = Math.multiplyExact(Integer.parseInt(daysStr), 86400);
                  seconds = Math.addExact(seconds, daysAsSecs);
               } catch (NumberFormatException ex) {
                  throw new DateTimeParseException("Text cannot be parsed to Seconds, non-numeric days", text, 0, ex);
               }
            }

            return of(Math.multiplyExact(seconds, negate));
         }
      }

      throw new DateTimeParseException("Text cannot be parsed to Seconds", text, 0);
   }

   public static Seconds between(Temporal startInclusive, Temporal endExclusive) {
      return of(Math.toIntExact(ChronoUnit.SECONDS.between(startInclusive, endExclusive)));
   }

   private Seconds(int seconds) {
      this.seconds = seconds;
   }

   private Object readResolve() {
      return of(this.seconds);
   }

   public long get(TemporalUnit unit) {
      if (unit == ChronoUnit.SECONDS) {
         return (long)this.seconds;
      } else {
         throw new UnsupportedTemporalTypeException("Unsupported unit: " + unit);
      }
   }

   public List getUnits() {
      return Collections.singletonList(ChronoUnit.SECONDS);
   }

   public int getAmount() {
      return this.seconds;
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

   public Seconds plus(TemporalAmount amountToAdd) {
      return this.plus(from(amountToAdd).getAmount());
   }

   public Seconds plus(int seconds) {
      return seconds == 0 ? this : of(Math.addExact(this.seconds, seconds));
   }

   public Seconds minus(TemporalAmount amountToSubtract) {
      return this.minus(from(amountToSubtract).getAmount());
   }

   public Seconds minus(int seconds) {
      return seconds == 0 ? this : of(Math.subtractExact(this.seconds, seconds));
   }

   public Seconds multipliedBy(int scalar) {
      return scalar == 1 ? this : of(Math.multiplyExact(this.seconds, scalar));
   }

   public Seconds dividedBy(int divisor) {
      return divisor == 1 ? this : of(this.seconds / divisor);
   }

   public Seconds negated() {
      return this.multipliedBy(-1);
   }

   public Seconds abs() {
      return this.seconds < 0 ? this.negated() : this;
   }

   public Duration toDuration() {
      return Duration.ofSeconds((long)this.seconds);
   }

   public Temporal addTo(Temporal temporal) {
      if (this.seconds != 0) {
         temporal = temporal.plus((long)this.seconds, ChronoUnit.SECONDS);
      }

      return temporal;
   }

   public Temporal subtractFrom(Temporal temporal) {
      if (this.seconds != 0) {
         temporal = temporal.minus((long)this.seconds, ChronoUnit.SECONDS);
      }

      return temporal;
   }

   public int compareTo(Seconds otherAmount) {
      int thisValue = this.seconds;
      int otherValue = otherAmount.seconds;
      return Integer.compare(thisValue, otherValue);
   }

   public boolean equals(Object otherAmount) {
      if (this == otherAmount) {
         return true;
      } else if (otherAmount instanceof Seconds) {
         Seconds other = (Seconds)otherAmount;
         return this.seconds == other.seconds;
      } else {
         return false;
      }
   }

   public int hashCode() {
      return this.seconds;
   }

   @ToString
   public String toString() {
      return "PT" + this.seconds + "S";
   }
}
