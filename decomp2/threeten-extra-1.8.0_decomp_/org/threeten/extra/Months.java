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

public final class Months implements TemporalAmount, Comparable, Serializable {
   public static final Months ZERO = new Months(0);
   public static final Months ONE = new Months(1);
   private static final long serialVersionUID = -8903767091325669093L;
   private static final int MONTHS_PER_YEAR = 12;
   private static final Pattern PATTERN = Pattern.compile("([-+]?)P(?:([-+]?[0-9]+)Y)?(?:([-+]?[0-9]+)M)?", 2);
   private final int months;

   public static Months of(int months) {
      if (months == 0) {
         return ZERO;
      } else {
         return months == 1 ? ONE : new Months(months);
      }
   }

   public static Months ofYears(int years) {
      return years == 0 ? ZERO : new Months(Math.multiplyExact(years, 12));
   }

   public static Months from(TemporalAmount amount) {
      if (amount instanceof Months) {
         return (Months)amount;
      } else {
         Objects.requireNonNull(amount, "amount");
         int months = 0;

         for(TemporalUnit unit : amount.getUnits()) {
            long value = amount.get(unit);
            if (value != 0L) {
               long[] converted = Temporals.convertAmount(value, unit, ChronoUnit.MONTHS);
               if (converted[1] != 0L) {
                  throw new DateTimeException("Amount could not be converted to a whole number of months: " + value + " " + unit);
               }

               months = Math.addExact(months, Math.toIntExact(converted[0]));
            }
         }

         return of(months);
      }
   }

   @FromString
   public static Months parse(CharSequence text) {
      Objects.requireNonNull(text, "text");
      Matcher matcher = PATTERN.matcher(text);
      if (matcher.matches()) {
         int negate = "-".equals(matcher.group(1)) ? -1 : 1;
         String weeksStr = matcher.group(2);
         String daysStr = matcher.group(3);
         if (weeksStr != null || daysStr != null) {
            int months = 0;
            if (daysStr != null) {
               try {
                  months = Integer.parseInt(daysStr);
               } catch (NumberFormatException ex) {
                  throw new DateTimeParseException("Text cannot be parsed to a Months, non-numeric months", text, 0, ex);
               }
            }

            if (weeksStr != null) {
               try {
                  int years = Math.multiplyExact(Integer.parseInt(weeksStr), 12);
                  months = Math.addExact(months, years);
               } catch (NumberFormatException ex) {
                  throw new DateTimeParseException("Text cannot be parsed to a Months, non-numeric years", text, 0, ex);
               }
            }

            return of(Math.multiplyExact(months, negate));
         }
      }

      throw new DateTimeParseException("Text cannot be parsed to a Months", text, 0);
   }

   public static Months between(Temporal startDateInclusive, Temporal endDateExclusive) {
      return of(Math.toIntExact(ChronoUnit.MONTHS.between(startDateInclusive, endDateExclusive)));
   }

   private Months(int months) {
      this.months = months;
   }

   private Object readResolve() {
      return of(this.months);
   }

   public long get(TemporalUnit unit) {
      if (unit == ChronoUnit.MONTHS) {
         return (long)this.months;
      } else {
         throw new UnsupportedTemporalTypeException("Unsupported unit: " + unit);
      }
   }

   public List getUnits() {
      return Collections.singletonList(ChronoUnit.MONTHS);
   }

   public int getAmount() {
      return this.months;
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

   public Months plus(TemporalAmount amountToAdd) {
      return this.plus(from(amountToAdd).getAmount());
   }

   public Months plus(int months) {
      return months == 0 ? this : of(Math.addExact(this.months, months));
   }

   public Months minus(TemporalAmount amountToSubtract) {
      return this.minus(from(amountToSubtract).getAmount());
   }

   public Months minus(int months) {
      return months == 0 ? this : of(Math.subtractExact(this.months, months));
   }

   public Months multipliedBy(int scalar) {
      return scalar == 1 ? this : of(Math.multiplyExact(this.months, scalar));
   }

   public Months dividedBy(int divisor) {
      return divisor == 1 ? this : of(this.months / divisor);
   }

   public Months negated() {
      return this.multipliedBy(-1);
   }

   public Months abs() {
      return this.months < 0 ? this.negated() : this;
   }

   public Period toPeriod() {
      return Period.ofMonths(this.months);
   }

   public Temporal addTo(Temporal temporal) {
      if (this.months != 0) {
         temporal = temporal.plus((long)this.months, ChronoUnit.MONTHS);
      }

      return temporal;
   }

   public Temporal subtractFrom(Temporal temporal) {
      if (this.months != 0) {
         temporal = temporal.minus((long)this.months, ChronoUnit.MONTHS);
      }

      return temporal;
   }

   public int compareTo(Months otherAmount) {
      int thisValue = this.months;
      int otherValue = otherAmount.months;
      return Integer.compare(thisValue, otherValue);
   }

   public boolean equals(Object otherAmount) {
      if (this == otherAmount) {
         return true;
      } else if (otherAmount instanceof Months) {
         Months other = (Months)otherAmount;
         return this.months == other.months;
      } else {
         return false;
      }
   }

   public int hashCode() {
      return this.months;
   }

   @ToString
   public String toString() {
      return "P" + this.months + "M";
   }
}
