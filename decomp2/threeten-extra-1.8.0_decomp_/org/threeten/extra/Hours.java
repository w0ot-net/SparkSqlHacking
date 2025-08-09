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

public final class Hours implements TemporalAmount, Comparable, Serializable {
   public static final Hours ZERO = new Hours(0);
   private static final long serialVersionUID = -8494096666041369608L;
   private static final int HOURS_PER_DAY = 24;
   private static final Pattern PATTERN = Pattern.compile("([-+]?)P(?:([-+]?[0-9]+)D)?(?:T(?:([-+]?[0-9]+)H)?)?", 2);
   private final int hours;

   public static Hours of(int hours) {
      return hours == 0 ? ZERO : new Hours(hours);
   }

   public static Hours from(TemporalAmount amount) {
      if (amount instanceof Hours) {
         return (Hours)amount;
      } else {
         Objects.requireNonNull(amount, "amount");
         int hours = 0;

         for(TemporalUnit unit : amount.getUnits()) {
            long value = amount.get(unit);
            if (value != 0L) {
               long[] converted = Temporals.convertAmount(value, unit, ChronoUnit.HOURS);
               if (converted[1] != 0L) {
                  throw new DateTimeException("Amount could not be converted to a whole number of hours: " + value + " " + unit);
               }

               hours = Math.addExact(hours, Math.toIntExact(converted[0]));
            }
         }

         return of(hours);
      }
   }

   @FromString
   public static Hours parse(CharSequence text) {
      Objects.requireNonNull(text, "text");
      Matcher matcher = PATTERN.matcher(text);
      if (matcher.matches()) {
         int negate = "-".equals(matcher.group(1)) ? -1 : 1;
         String daysStr = matcher.group(2);
         String hoursStr = matcher.group(3);
         if (daysStr != null || hoursStr != null) {
            int hours = 0;
            if (hoursStr != null) {
               try {
                  hours = Integer.parseInt(hoursStr);
               } catch (NumberFormatException ex) {
                  throw new DateTimeParseException("Text cannot be parsed to Hours, non-numeric hours", text, 0, ex);
               }
            }

            if (daysStr != null) {
               try {
                  int daysAsHours = Math.multiplyExact(Integer.parseInt(daysStr), 24);
                  hours = Math.addExact(hours, daysAsHours);
               } catch (NumberFormatException ex) {
                  throw new DateTimeParseException("Text cannot be parsed to Hours, non-numeric days", text, 0, ex);
               }
            }

            return of(Math.multiplyExact(hours, negate));
         }
      }

      throw new DateTimeParseException("Text cannot be parsed to Hours", text, 0);
   }

   public static Hours between(Temporal startInclusive, Temporal endExclusive) {
      return of(Math.toIntExact(ChronoUnit.HOURS.between(startInclusive, endExclusive)));
   }

   private Hours(int hours) {
      this.hours = hours;
   }

   private Object readResolve() {
      return of(this.hours);
   }

   public long get(TemporalUnit unit) {
      if (unit == ChronoUnit.HOURS) {
         return (long)this.hours;
      } else {
         throw new UnsupportedTemporalTypeException("Unsupported unit: " + unit);
      }
   }

   public List getUnits() {
      return Collections.singletonList(ChronoUnit.HOURS);
   }

   public int getAmount() {
      return this.hours;
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

   public Hours plus(TemporalAmount amountToAdd) {
      return this.plus(from(amountToAdd).getAmount());
   }

   public Hours plus(int hours) {
      return hours == 0 ? this : of(Math.addExact(this.hours, hours));
   }

   public Hours minus(TemporalAmount amountToSubtract) {
      return this.minus(from(amountToSubtract).getAmount());
   }

   public Hours minus(int hours) {
      return hours == 0 ? this : of(Math.subtractExact(this.hours, hours));
   }

   public Hours multipliedBy(int scalar) {
      return scalar == 1 ? this : of(Math.multiplyExact(this.hours, scalar));
   }

   public Hours dividedBy(int divisor) {
      return divisor == 1 ? this : of(this.hours / divisor);
   }

   public Hours negated() {
      return this.multipliedBy(-1);
   }

   public Hours abs() {
      return this.hours < 0 ? this.negated() : this;
   }

   /** @deprecated */
   @Deprecated
   public Duration toPeriod() {
      return Duration.ofHours((long)this.hours);
   }

   public Duration toDuration() {
      return Duration.ofHours((long)this.hours);
   }

   public Temporal addTo(Temporal temporal) {
      if (this.hours != 0) {
         temporal = temporal.plus((long)this.hours, ChronoUnit.HOURS);
      }

      return temporal;
   }

   public Temporal subtractFrom(Temporal temporal) {
      if (this.hours != 0) {
         temporal = temporal.minus((long)this.hours, ChronoUnit.HOURS);
      }

      return temporal;
   }

   public int compareTo(Hours otherAmount) {
      int thisValue = this.hours;
      int otherValue = otherAmount.hours;
      return Integer.compare(thisValue, otherValue);
   }

   public boolean equals(Object otherAmount) {
      if (this == otherAmount) {
         return true;
      } else if (otherAmount instanceof Hours) {
         Hours other = (Hours)otherAmount;
         return this.hours == other.hours;
      } else {
         return false;
      }
   }

   public int hashCode() {
      return this.hours;
   }

   @ToString
   public String toString() {
      return "PT" + this.hours + "H";
   }
}
