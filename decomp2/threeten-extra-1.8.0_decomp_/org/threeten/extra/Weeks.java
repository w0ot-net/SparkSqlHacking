package org.threeten.extra;

import java.io.Serializable;
import java.time.DateTimeException;
import java.time.Period;
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

public final class Weeks implements TemporalAmount, Comparable, Serializable {
   public static final Weeks ZERO = new Weeks(0);
   public static final Weeks ONE = new Weeks(1);
   private static final long serialVersionUID = -8903767091325669093L;
   private static final Pattern PATTERN = Pattern.compile("([-+]?)P([-+]?[0-9]+)W", 2);
   private final int weeks;

   public static Weeks of(int weeks) {
      if (weeks == 0) {
         return ZERO;
      } else {
         return weeks == 1 ? ONE : new Weeks(weeks);
      }
   }

   public static Weeks from(TemporalAmount amount) {
      if (amount instanceof Weeks) {
         return (Weeks)amount;
      } else {
         Objects.requireNonNull(amount, "amount");
         int weeks = 0;

         for(TemporalUnit unit : amount.getUnits()) {
            long value = amount.get(unit);
            if (value != 0L) {
               long[] converted = Temporals.convertAmount(value, unit, ChronoUnit.WEEKS);
               if (converted[1] != 0L) {
                  throw new DateTimeException("Amount could not be converted to a whole number of weeks: " + value + " " + unit);
               }

               weeks = Math.addExact(weeks, Math.toIntExact(converted[0]));
            }
         }

         return of(weeks);
      }
   }

   @FromString
   public static Weeks parse(CharSequence text) {
      Objects.requireNonNull(text, "text");
      Matcher matcher = PATTERN.matcher(text);
      if (matcher.matches()) {
         int negate = "-".equals(matcher.group(1)) ? -1 : 1;
         String str = matcher.group(2);

         try {
            int val = Integer.parseInt(str);
            return of(Math.multiplyExact(val, negate));
         } catch (NumberFormatException ex) {
            throw new DateTimeParseException("Text cannot be parsed to a Weeks", text, 0, ex);
         }
      } else {
         throw new DateTimeParseException("Text cannot be parsed to a Weeks", text, 0);
      }
   }

   public static Weeks between(Temporal startDateInclusive, Temporal endDateExclusive) {
      return of(Math.toIntExact(ChronoUnit.WEEKS.between(startDateInclusive, endDateExclusive)));
   }

   private Weeks(int weeks) {
      this.weeks = weeks;
   }

   private Object readResolve() {
      return of(this.weeks);
   }

   public long get(TemporalUnit unit) {
      if (unit == ChronoUnit.WEEKS) {
         return (long)this.weeks;
      } else {
         throw new UnsupportedTemporalTypeException("Unsupported unit: " + unit);
      }
   }

   public List getUnits() {
      return Collections.singletonList(ChronoUnit.WEEKS);
   }

   public int getAmount() {
      return this.weeks;
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

   public Weeks plus(TemporalAmount amountToAdd) {
      return this.plus(from(amountToAdd).getAmount());
   }

   public Weeks plus(int weeks) {
      return weeks == 0 ? this : of(Math.addExact(this.weeks, weeks));
   }

   public Weeks minus(TemporalAmount amountToSubtract) {
      return this.minus(from(amountToSubtract).getAmount());
   }

   public Weeks minus(int weeks) {
      return weeks == 0 ? this : of(Math.subtractExact(this.weeks, weeks));
   }

   public Weeks multipliedBy(int scalar) {
      return scalar == 1 ? this : of(Math.multiplyExact(this.weeks, scalar));
   }

   public Weeks dividedBy(int divisor) {
      return divisor == 1 ? this : of(this.weeks / divisor);
   }

   public Weeks negated() {
      return this.multipliedBy(-1);
   }

   public Weeks abs() {
      return this.weeks < 0 ? this.negated() : this;
   }

   public Period toPeriod() {
      return Period.ofWeeks(this.weeks);
   }

   public Temporal addTo(Temporal temporal) {
      if (this.weeks != 0) {
         temporal = temporal.plus((long)this.weeks, ChronoUnit.WEEKS);
      }

      return temporal;
   }

   public Temporal subtractFrom(Temporal temporal) {
      if (this.weeks != 0) {
         temporal = temporal.minus((long)this.weeks, ChronoUnit.WEEKS);
      }

      return temporal;
   }

   public int compareTo(Weeks otherAmount) {
      int thisValue = this.weeks;
      int otherValue = otherAmount.weeks;
      return Integer.compare(thisValue, otherValue);
   }

   public boolean equals(Object otherAmount) {
      if (this == otherAmount) {
         return true;
      } else if (otherAmount instanceof Weeks) {
         Weeks other = (Weeks)otherAmount;
         return this.weeks == other.weeks;
      } else {
         return false;
      }
   }

   public int hashCode() {
      return this.weeks;
   }

   @ToString
   public String toString() {
      return "P" + this.weeks + "W";
   }
}
