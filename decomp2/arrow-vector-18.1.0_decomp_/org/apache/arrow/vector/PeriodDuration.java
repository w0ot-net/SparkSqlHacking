package org.apache.arrow.vector;

import java.time.Duration;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAmount;
import java.time.temporal.TemporalUnit;
import java.time.temporal.UnsupportedTemporalTypeException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.apache.arrow.util.Preconditions;

public class PeriodDuration implements TemporalAmount {
   private static final List SUPPORTED_UNITS;
   private final Period period;
   private final Duration duration;

   public PeriodDuration(Period period, Duration duration) {
      this.period = (Period)Preconditions.checkNotNull(period);
      this.duration = (Duration)Preconditions.checkNotNull(duration);
   }

   public Period getPeriod() {
      return this.period;
   }

   public Duration getDuration() {
      return this.duration;
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

      throw new UnsupportedTemporalTypeException("Unsupported TemporalUnit: " + String.valueOf(unit));
   }

   public List getUnits() {
      return SUPPORTED_UNITS;
   }

   public Temporal addTo(Temporal temporal) {
      return temporal.plus(this.period).plus(this.duration);
   }

   public Temporal subtractFrom(Temporal temporal) {
      return temporal.minus(this.period).minus(this.duration);
   }

   public String toISO8601IntervalString() {
      if (this.duration.isZero()) {
         return this.period.toString();
      } else {
         String durationString = this.duration.toString();
         if (this.period.isZero()) {
            return durationString;
         } else {
            String var10000 = String.valueOf(this.period);
            return var10000 + durationString.substring(1);
         }
      }
   }

   public String toString() {
      String var10000 = this.period.toString();
      return var10000 + " " + this.duration.toString();
   }

   public boolean equals(Object o) {
      if (!(o instanceof PeriodDuration)) {
         return false;
      } else {
         PeriodDuration other = (PeriodDuration)o;
         return this.period.equals(other.period) && this.duration.equals(other.duration);
      }
   }

   public int hashCode() {
      return this.period.hashCode() * 31 + this.duration.hashCode();
   }

   static {
      SUPPORTED_UNITS = Collections.unmodifiableList(Arrays.asList(ChronoUnit.YEARS, ChronoUnit.MONTHS, ChronoUnit.DAYS, ChronoUnit.SECONDS, ChronoUnit.NANOS));
   }
}
