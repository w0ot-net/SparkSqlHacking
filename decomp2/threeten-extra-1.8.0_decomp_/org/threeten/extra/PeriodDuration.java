package org.threeten.extra;

import java.io.Serializable;
import java.time.DateTimeException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.chrono.ChronoPeriod;
import java.time.chrono.IsoChronology;
import java.time.temporal.ChronoUnit;
import java.time.temporal.IsoFields;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAmount;
import java.time.temporal.TemporalQueries;
import java.time.temporal.TemporalUnit;
import java.time.temporal.UnsupportedTemporalTypeException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import org.joda.convert.FromString;
import org.joda.convert.ToString;

public final class PeriodDuration implements TemporalAmount, Serializable {
   public static final PeriodDuration ZERO;
   private static final long serialVersionUID = 8815521625671589L;
   private static final List SUPPORTED_UNITS;
   private static final long SECONDS_PER_DAY = 86400L;
   private final Period period;
   private final Duration duration;

   public static PeriodDuration of(Period period, Duration duration) {
      Objects.requireNonNull(period, "The period must not be null");
      Objects.requireNonNull(duration, "The duration must not be null");
      return new PeriodDuration(period, duration);
   }

   public static PeriodDuration of(Period period) {
      Objects.requireNonNull(period, "The period must not be null");
      return new PeriodDuration(period, Duration.ZERO);
   }

   public static PeriodDuration of(Duration duration) {
      Objects.requireNonNull(duration, "The duration must not be null");
      return new PeriodDuration(Period.ZERO, duration);
   }

   public static PeriodDuration from(TemporalAmount amount) {
      if (amount instanceof PeriodDuration) {
         return (PeriodDuration)amount;
      } else if (amount instanceof Period) {
         return of((Period)amount);
      } else if (amount instanceof Duration) {
         return of((Duration)amount);
      } else if (amount instanceof ChronoPeriod && !IsoChronology.INSTANCE.equals(((ChronoPeriod)amount).getChronology())) {
         throw new DateTimeException("Period requires ISO chronology: " + amount);
      } else {
         Objects.requireNonNull(amount, "amount");
         int years = 0;
         int months = 0;
         int days = 0;
         Duration duration = Duration.ZERO;

         for(TemporalUnit unit : amount.getUnits()) {
            long value = amount.get(unit);
            if (value != 0L) {
               if (unit.isDurationEstimated()) {
                  if (unit == ChronoUnit.DAYS) {
                     days = Math.addExact(days, Math.toIntExact(value));
                  } else if (unit == ChronoUnit.WEEKS) {
                     days = Math.addExact(days, Math.toIntExact(Math.multiplyExact(value, 7L)));
                  } else if (unit == ChronoUnit.MONTHS) {
                     months = Math.addExact(months, Math.toIntExact(value));
                  } else if (unit == IsoFields.QUARTER_YEARS) {
                     months = Math.addExact(months, Math.toIntExact(Math.multiplyExact(value, 3L)));
                  } else if (unit == ChronoUnit.YEARS) {
                     years = Math.addExact(years, Math.toIntExact(value));
                  } else if (unit == ChronoUnit.DECADES) {
                     years = Math.addExact(years, Math.toIntExact(Math.multiplyExact(value, 10L)));
                  } else if (unit == ChronoUnit.CENTURIES) {
                     years = Math.addExact(years, Math.toIntExact(Math.multiplyExact(value, 100L)));
                  } else {
                     if (unit != ChronoUnit.MILLENNIA) {
                        throw new DateTimeException("Unknown unit: " + unit);
                     }

                     years = Math.addExact(years, Math.toIntExact(Math.multiplyExact(value, 1000L)));
                  }
               } else {
                  duration = duration.plus(amount.get(unit), unit);
               }
            }
         }

         return of(Period.of(years, months, days), duration);
      }
   }

   @FromString
   public static PeriodDuration parse(CharSequence text) {
      Objects.requireNonNull(text, "text");
      String upper = text.toString().toUpperCase(Locale.ENGLISH);
      String negate = "";
      if (upper.startsWith("+")) {
         upper = upper.substring(1);
      } else if (upper.startsWith("-")) {
         upper = upper.substring(1);
         negate = "-";
      }

      if (upper.startsWith("PT")) {
         return of(Duration.parse(text));
      } else {
         int tpos = upper.indexOf(84);
         if (tpos < 0) {
            return of(Period.parse(text));
         } else {
            Period period = Period.parse(negate + upper.substring(0, tpos));
            Duration duration = Duration.parse(negate + "P" + upper.substring(tpos));
            return of(period, duration);
         }
      }
   }

   public static PeriodDuration between(Temporal startInclusive, Temporal endExclusive) {
      LocalDate startDate = (LocalDate)startInclusive.query(TemporalQueries.localDate());
      LocalDate endDate = (LocalDate)endExclusive.query(TemporalQueries.localDate());
      Period period = Period.ZERO;
      if (startDate != null && endDate != null) {
         period = Period.between(startDate, endDate);
      }

      LocalTime startTime = (LocalTime)startInclusive.query(TemporalQueries.localTime());
      LocalTime endTime = (LocalTime)endExclusive.query(TemporalQueries.localTime());
      startTime = startTime != null ? startTime : LocalTime.MIDNIGHT;
      endTime = endTime != null ? endTime : LocalTime.MIDNIGHT;
      Duration duration = Duration.between(startTime, endTime);
      return of(period, duration);
   }

   private PeriodDuration(Period period, Duration duration) {
      this.period = period;
      this.duration = duration;
   }

   private Object readResolve() {
      return of(this.period, this.duration);
   }

   public long get(TemporalUnit unit) {
      if (unit instanceof ChronoUnit) {
         switch ((ChronoUnit)unit) {
            case YEARS:
               return (long)this.period.getYears();
            case MONTHS:
               return (long)this.period.getMonths();
            case DAYS:
               return (long)this.period.getDays();
            case SECONDS:
               return this.duration.getSeconds();
            case NANOS:
               return (long)this.duration.getNano();
         }
      }

      throw new UnsupportedTemporalTypeException("Unsupported unit: " + unit);
   }

   public List getUnits() {
      return SUPPORTED_UNITS;
   }

   public Period getPeriod() {
      return this.period;
   }

   public PeriodDuration withPeriod(Period period) {
      return of(period, this.duration);
   }

   public Duration getDuration() {
      return this.duration;
   }

   public PeriodDuration withDuration(Duration duration) {
      return of(this.period, duration);
   }

   public boolean isZero() {
      return this.period.isZero() && this.duration.isZero();
   }

   public PeriodDuration plus(TemporalAmount amountToAdd) {
      PeriodDuration other = from(amountToAdd);
      return of(this.period.plus(other.period), this.duration.plus(other.duration));
   }

   public PeriodDuration minus(TemporalAmount amountToAdd) {
      PeriodDuration other = from(amountToAdd);
      return of(this.period.minus(other.period), this.duration.minus(other.duration));
   }

   public PeriodDuration multipliedBy(int scalar) {
      return scalar == 1 ? this : of(this.period.multipliedBy(scalar), this.duration.multipliedBy((long)scalar));
   }

   public PeriodDuration negated() {
      return this.multipliedBy(-1);
   }

   public PeriodDuration normalizedYears() {
      return this.withPeriod(this.period.normalized());
   }

   public PeriodDuration normalizedStandardDays() {
      long totalSecs = (long)this.period.getDays() * 86400L + this.duration.getSeconds();
      int splitDays = Math.toIntExact(totalSecs / 86400L);
      long splitSecs = totalSecs % 86400L;
      return splitDays == this.period.getDays() && splitSecs == this.duration.getSeconds() ? this : of(this.period.withDays(splitDays), this.duration.withSeconds(splitSecs));
   }

   public Temporal addTo(Temporal temporal) {
      return temporal.plus(this.period).plus(this.duration);
   }

   public Temporal subtractFrom(Temporal temporal) {
      return temporal.minus(this.period).minus(this.duration);
   }

   public boolean equals(Object otherAmount) {
      if (this == otherAmount) {
         return true;
      } else if (!(otherAmount instanceof PeriodDuration)) {
         return false;
      } else {
         PeriodDuration other = (PeriodDuration)otherAmount;
         return this.period.equals(other.period) && this.duration.equals(other.duration);
      }
   }

   public int hashCode() {
      return this.period.hashCode() ^ this.duration.hashCode();
   }

   @ToString
   public String toString() {
      if (this.period.isZero()) {
         return this.duration.toString();
      } else {
         return this.duration.isZero() ? this.period.toString() : this.period.toString() + this.duration.toString().substring(1);
      }
   }

   static {
      ZERO = new PeriodDuration(Period.ZERO, Duration.ZERO);
      SUPPORTED_UNITS = Collections.unmodifiableList(Arrays.asList(ChronoUnit.YEARS, ChronoUnit.MONTHS, ChronoUnit.DAYS, ChronoUnit.SECONDS, ChronoUnit.NANOS));
   }
}
