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

public final class Days implements TemporalAmount, Comparable, Serializable {
   public static final Days ZERO = new Days(0);
   public static final Days ONE = new Days(1);
   private static final long serialVersionUID = -8903767091325669093L;
   private static final int DAYS_PER_WEEK = 7;
   private static final Pattern PATTERN = Pattern.compile("([-+]?)P(?:([-+]?[0-9]+)W)?(?:([-+]?[0-9]+)D)?", 2);
   private final int days;

   public static Days of(int days) {
      if (days == 0) {
         return ZERO;
      } else {
         return days == 1 ? ONE : new Days(days);
      }
   }

   public static Days ofWeeks(int weeks) {
      return weeks == 0 ? ZERO : new Days(Math.multiplyExact(weeks, 7));
   }

   public static Days from(TemporalAmount amount) {
      if (amount instanceof Days) {
         return (Days)amount;
      } else {
         Objects.requireNonNull(amount, "amount");
         int days = 0;

         for(TemporalUnit unit : amount.getUnits()) {
            long value = amount.get(unit);
            if (value != 0L) {
               long[] converted = Temporals.convertAmount(value, unit, ChronoUnit.DAYS);
               if (converted[1] != 0L) {
                  throw new DateTimeException("Amount could not be converted to a whole number of days: " + value + " " + unit);
               }

               days = Math.addExact(days, Math.toIntExact(converted[0]));
            }
         }

         return of(days);
      }
   }

   @FromString
   public static Days parse(CharSequence text) {
      Objects.requireNonNull(text, "text");
      Matcher matcher = PATTERN.matcher(text);
      if (matcher.matches()) {
         int negate = "-".equals(matcher.group(1)) ? -1 : 1;
         String weeksStr = matcher.group(2);
         String daysStr = matcher.group(3);
         if (weeksStr != null || daysStr != null) {
            int days = 0;
            if (daysStr != null) {
               try {
                  days = Integer.parseInt(daysStr);
               } catch (NumberFormatException ex) {
                  throw new DateTimeParseException("Text cannot be parsed to a Days, non-numeric days", text, 0, ex);
               }
            }

            if (weeksStr != null) {
               try {
                  int weeks = Math.multiplyExact(Integer.parseInt(weeksStr), 7);
                  days = Math.addExact(days, weeks);
               } catch (NumberFormatException ex) {
                  throw new DateTimeParseException("Text cannot be parsed to a Days, non-numeric weeks", text, 0, ex);
               }
            }

            return of(Math.multiplyExact(days, negate));
         }
      }

      throw new DateTimeParseException("Text cannot be parsed to a Days", text, 0);
   }

   public static Days between(Temporal startDateInclusive, Temporal endDateExclusive) {
      return of(Math.toIntExact(ChronoUnit.DAYS.between(startDateInclusive, endDateExclusive)));
   }

   private Days(int days) {
      this.days = days;
   }

   private Object readResolve() {
      return of(this.days);
   }

   public long get(TemporalUnit unit) {
      if (unit == ChronoUnit.DAYS) {
         return (long)this.days;
      } else {
         throw new UnsupportedTemporalTypeException("Unsupported unit: " + unit);
      }
   }

   public List getUnits() {
      return Collections.singletonList(ChronoUnit.DAYS);
   }

   public int getAmount() {
      return this.days;
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

   public Days plus(TemporalAmount amountToAdd) {
      return this.plus(from(amountToAdd).getAmount());
   }

   public Days plus(int days) {
      return days == 0 ? this : of(Math.addExact(this.days, days));
   }

   public Days minus(TemporalAmount amountToSubtract) {
      return this.minus(from(amountToSubtract).getAmount());
   }

   public Days minus(int days) {
      return days == 0 ? this : of(Math.subtractExact(this.days, days));
   }

   public Days multipliedBy(int scalar) {
      return scalar == 1 ? this : of(Math.multiplyExact(this.days, scalar));
   }

   public Days dividedBy(int divisor) {
      return divisor == 1 ? this : of(this.days / divisor);
   }

   public Days negated() {
      return this.multipliedBy(-1);
   }

   public Days abs() {
      return this.days < 0 ? this.negated() : this;
   }

   public Period toPeriod() {
      return Period.ofDays(this.days);
   }

   public Temporal addTo(Temporal temporal) {
      if (this.days != 0) {
         temporal = temporal.plus((long)this.days, ChronoUnit.DAYS);
      }

      return temporal;
   }

   public Temporal subtractFrom(Temporal temporal) {
      if (this.days != 0) {
         temporal = temporal.minus((long)this.days, ChronoUnit.DAYS);
      }

      return temporal;
   }

   public int compareTo(Days otherAmount) {
      int thisValue = this.days;
      int otherValue = otherAmount.days;
      return Integer.compare(thisValue, otherValue);
   }

   public boolean equals(Object otherAmount) {
      if (this == otherAmount) {
         return true;
      } else if (otherAmount instanceof Days) {
         Days other = (Days)otherAmount;
         return this.days == other.days;
      } else {
         return false;
      }
   }

   public int hashCode() {
      return this.days;
   }

   @ToString
   public String toString() {
      return "P" + this.days + "D";
   }
}
