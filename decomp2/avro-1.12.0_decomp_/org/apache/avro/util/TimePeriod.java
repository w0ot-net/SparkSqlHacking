package org.apache.avro.util;

import java.io.Serializable;
import java.time.DateTimeException;
import java.time.Duration;
import java.time.Period;
import java.time.chrono.ChronoPeriod;
import java.time.chrono.IsoChronology;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAmount;
import java.time.temporal.TemporalUnit;
import java.time.temporal.UnsupportedTemporalTypeException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class TimePeriod implements TemporalAmount, Serializable {
   private static final long MAX_UNSIGNED_INT = 4294967295L;
   private static final long MONTHS_PER_YEAR = 12L;
   private static final long MONTHS_PER_DECADE = 120L;
   private static final long MONTHS_PER_CENTURY = 1200L;
   private static final long MONTHS_PER_MILLENNIUM = 12000L;
   private static final long MILLIS_PER_SECOND = 1000L;
   private static final long MILLIS_PER_MINUTE = 60000L;
   private static final long MILLIS_PER_HOUR = 3600000L;
   private static final long MILLIS_IN_HALF_DAY = 43200000L;
   private static final long MICROS_PER_MILLI = 1000L;
   private static final long NANOS_PER_MILLI = 1000000L;
   private final long months;
   private final long days;
   private final long millis;

   public static TimePeriod from(TemporalAmount amount) {
      if (Objects.requireNonNull(amount, "amount") instanceof TimePeriod) {
         return (TimePeriod)amount;
      } else if (amount instanceof ChronoPeriod && !IsoChronology.INSTANCE.equals(((ChronoPeriod)amount).getChronology())) {
         throw new DateTimeException("TimePeriod requires ISO chronology: " + String.valueOf(amount));
      } else {
         long months = 0L;
         long days = 0L;
         long millis = 0L;

         for(TemporalUnit unit : amount.getUnits()) {
            if (!(unit instanceof ChronoUnit)) {
               throw new UnsupportedTemporalTypeException("Unsupported unit: " + String.valueOf(unit));
            }

            long unitAmount = amount.get(unit);
            switch ((ChronoUnit)unit) {
               case MILLENNIA:
                  months = unsignedInt(months + unitAmount * 12000L);
                  break;
               case CENTURIES:
                  months = unsignedInt(months + unitAmount * 1200L);
                  break;
               case DECADES:
                  months = unsignedInt(months + unitAmount * 120L);
                  break;
               case YEARS:
                  months = unsignedInt(months + unitAmount * 12L);
                  break;
               case MONTHS:
                  months = unsignedInt(months + unitAmount);
                  break;
               case WEEKS:
                  days = unsignedInt(days + unitAmount * 7L);
                  break;
               case DAYS:
                  days = unsignedInt(days + unitAmount);
                  break;
               case HALF_DAYS:
                  days = unsignedInt(days + unitAmount / 2L);
                  if (unitAmount % 2L != 0L) {
                     millis = unsignedInt(millis + 43200000L);
                  }
                  break;
               case HOURS:
                  millis = unsignedInt(millis + unitAmount * 3600000L);
                  break;
               case MINUTES:
                  millis = unsignedInt(millis + unitAmount * 60000L);
                  break;
               case SECONDS:
                  millis = unsignedInt(millis + unitAmount * 1000L);
                  break;
               case MILLIS:
                  millis = unsignedInt(millis + unitAmount);
                  break;
               case MICROS:
                  if (unitAmount % 1000L != 0L) {
                     throw new DateTimeException("Cannot add " + unitAmount + " microseconds: not a whole number of milliseconds");
                  }

                  millis = unsignedInt(millis + unitAmount / 1000L);
                  break;
               case NANOS:
                  if (unitAmount % 1000000L != 0L) {
                     throw new DateTimeException("Cannot add " + unitAmount + " nanoseconds: not a whole number of milliseconds");
                  }

                  millis = unsignedInt(millis + unitAmount / 1000000L);
                  break;
               default:
                  throw new UnsupportedTemporalTypeException("Unsupported unit: " + String.valueOf(unit));
            }
         }

         return new TimePeriod(months, days, millis);
      }
   }

   public static TimePeriod of(long months, long days, long millis) {
      return new TimePeriod(unsignedInt(months), unsignedInt(days), unsignedInt(millis));
   }

   private static long unsignedInt(long number) {
      if (number != (number & 4294967295L)) {
         throw new ArithmeticException("Overflow/underflow of unsigned int");
      } else {
         return number;
      }
   }

   private TimePeriod(long months, long days, long millis) {
      this.months = months;
      this.days = days;
      this.millis = millis;
   }

   public Duration toDuration() {
      return Duration.from(this);
   }

   public Period toPeriod() {
      if (this.isDateBased()) {
         int yearsAsInt = (int)(this.months / 12L);
         int monthsAsInt = (int)(this.months % 12L);
         int daysAsInt = (int)this.days;
         if (this.days != (long)daysAsInt) {
            throw new DateTimeException("Too many days: a Period can contain at most 2147483647 days.");
         } else {
            return Period.ofYears(yearsAsInt).withMonths(monthsAsInt).withDays(daysAsInt);
         }
      } else {
         throw new DateTimeException("Cannot convert this TimePeriod to a Period: is not date based");
      }
   }

   public boolean isDateBased() {
      return this.millis == 0L;
   }

   public boolean isTimeBased() {
      return this.months == 0L && this.days == 0L;
   }

   public long getMonths() {
      return this.months;
   }

   public long getDays() {
      return this.days;
   }

   public long getMillis() {
      return this.millis;
   }

   public long get(TemporalUnit unit) {
      if (unit == ChronoUnit.MONTHS) {
         return this.months;
      } else if (unit == ChronoUnit.DAYS) {
         return this.days;
      } else if (unit == ChronoUnit.MILLIS) {
         return this.millis;
      } else {
         throw new UnsupportedTemporalTypeException("Unsupported unit: " + String.valueOf(unit));
      }
   }

   public List getUnits() {
      List<TemporalUnit> units = new ArrayList();
      if (this.months != 0L) {
         units.add(ChronoUnit.MONTHS);
      }

      if (this.days != 0L) {
         units.add(ChronoUnit.DAYS);
      }

      if (this.millis != 0L) {
         units.add(ChronoUnit.MILLIS);
      }

      return Collections.unmodifiableList(units);
   }

   public Temporal addTo(Temporal temporal) {
      return this.addTo(temporal, this.months, this.days, this.millis);
   }

   public Temporal subtractFrom(Temporal temporal) {
      return this.addTo(temporal, -this.months, -this.days, -this.millis);
   }

   private Temporal addTo(Temporal temporal, long months, long days, long millis) {
      if (months != 0L) {
         temporal = temporal.plus(months, ChronoUnit.MONTHS);
      }

      if (days != 0L) {
         temporal = temporal.plus(days, ChronoUnit.DAYS);
      }

      if (millis != 0L) {
         temporal = temporal.plus(millis, ChronoUnit.MILLIS);
      }

      return temporal;
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         TimePeriod that = (TimePeriod)o;
         return this.months == that.months && this.days == that.days && this.millis == that.millis;
      } else {
         return false;
      }
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.months, this.days, this.millis});
   }

   public String toString() {
      StringBuilder buffer = new StringBuilder();
      buffer.append("P");
      if (this.months != 0L) {
         int years = (int)(this.months / 12L);
         int monthsLeft = (int)(this.months % 12L);
         if (years != 0) {
            buffer.append(years).append("Y");
         }

         if (monthsLeft != 0) {
            buffer.append(monthsLeft).append("M");
         }
      }

      if (this.days != 0L || this.months == 0L && this.millis == 0L) {
         buffer.append(this.days);
      }

      if (this.millis != 0L) {
         long millisLeft = this.millis;
         int hours = (int)(millisLeft / 3600000L);
         millisLeft -= 3600000L * (long)hours;
         int minutes = (int)(millisLeft / 60000L);
         millisLeft -= 60000L * (long)minutes;
         int seconds = (int)(millisLeft / 1000L);
         millisLeft -= 1000L * (long)seconds;
         if (millisLeft != 0L) {
            buffer.append(String.format("T%02d:%02d:%02d.%03d", hours, minutes, seconds, millisLeft));
         } else if (seconds != 0) {
            buffer.append(String.format("T%02d:%02d:%02d", hours, minutes, seconds));
         } else if (minutes != 0) {
            buffer.append(String.format("T%02d:%02d", hours, minutes));
         } else {
            buffer.append(String.format("T%02d", hours));
         }
      }

      return buffer.toString();
   }
}
