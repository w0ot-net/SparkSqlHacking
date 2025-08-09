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

public final class Years implements TemporalAmount, Comparable, Serializable {
   public static final Years ZERO = new Years(0);
   public static final Years ONE = new Years(1);
   private static final long serialVersionUID = -8903767091325669093L;
   private static final Pattern PATTERN = Pattern.compile("([-+]?)P([-+]?[0-9]+)Y", 2);
   private final int years;

   public static Years of(int years) {
      if (years == 0) {
         return ZERO;
      } else {
         return years == 1 ? ONE : new Years(years);
      }
   }

   public static Years from(TemporalAmount amount) {
      if (amount instanceof Years) {
         return (Years)amount;
      } else {
         Objects.requireNonNull(amount, "amount");
         int years = 0;

         for(TemporalUnit unit : amount.getUnits()) {
            long value = amount.get(unit);
            if (value != 0L) {
               long[] converted = Temporals.convertAmount(value, unit, ChronoUnit.YEARS);
               if (converted[1] != 0L) {
                  throw new DateTimeException("Amount could not be converted to a whole number of years: " + value + " " + unit);
               }

               years = Math.addExact(years, Math.toIntExact(converted[0]));
            }
         }

         return of(years);
      }
   }

   @FromString
   public static Years parse(CharSequence text) {
      Objects.requireNonNull(text, "text");
      Matcher matcher = PATTERN.matcher(text);
      if (matcher.matches()) {
         int negate = "-".equals(matcher.group(1)) ? -1 : 1;
         String str = matcher.group(2);

         try {
            int val = Integer.parseInt(str);
            return of(Math.multiplyExact(val, negate));
         } catch (NumberFormatException ex) {
            throw new DateTimeParseException("Text cannot be parsed to a Years", text, 0, ex);
         }
      } else {
         throw new DateTimeParseException("Text cannot be parsed to a Years", text, 0);
      }
   }

   public static Years between(Temporal startDateInclusive, Temporal endDateExclusive) {
      return of(Math.toIntExact(ChronoUnit.YEARS.between(startDateInclusive, endDateExclusive)));
   }

   private Years(int years) {
      this.years = years;
   }

   private Object readResolve() {
      return of(this.years);
   }

   public long get(TemporalUnit unit) {
      if (unit == ChronoUnit.YEARS) {
         return (long)this.years;
      } else {
         throw new UnsupportedTemporalTypeException("Unsupported unit: " + unit);
      }
   }

   public List getUnits() {
      return Collections.singletonList(ChronoUnit.YEARS);
   }

   public int getAmount() {
      return this.years;
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

   public Years plus(TemporalAmount amountToAdd) {
      return this.plus(from(amountToAdd).getAmount());
   }

   public Years plus(int years) {
      return years == 0 ? this : of(Math.addExact(this.years, years));
   }

   public Years minus(TemporalAmount amountToSubtract) {
      return this.minus(from(amountToSubtract).getAmount());
   }

   public Years minus(int years) {
      return years == 0 ? this : of(Math.subtractExact(this.years, years));
   }

   public Years multipliedBy(int scalar) {
      return scalar == 1 ? this : of(Math.multiplyExact(this.years, scalar));
   }

   public Years dividedBy(int divisor) {
      return divisor == 1 ? this : of(this.years / divisor);
   }

   public Years negated() {
      return this.multipliedBy(-1);
   }

   public Years abs() {
      return this.years < 0 ? this.negated() : this;
   }

   public Period toPeriod() {
      return Period.ofYears(this.years);
   }

   public Temporal addTo(Temporal temporal) {
      if (this.years != 0) {
         temporal = temporal.plus((long)this.years, ChronoUnit.YEARS);
      }

      return temporal;
   }

   public Temporal subtractFrom(Temporal temporal) {
      if (this.years != 0) {
         temporal = temporal.minus((long)this.years, ChronoUnit.YEARS);
      }

      return temporal;
   }

   public int compareTo(Years otherAmount) {
      int thisValue = this.years;
      int otherValue = otherAmount.years;
      return Integer.compare(thisValue, otherValue);
   }

   public boolean equals(Object otherAmount) {
      if (this == otherAmount) {
         return true;
      } else if (otherAmount instanceof Years) {
         Years other = (Years)otherAmount;
         return this.years == other.years;
      } else {
         return false;
      }
   }

   public int hashCode() {
      return this.years;
   }

   @ToString
   public String toString() {
      return "P" + this.years + "Y";
   }
}
