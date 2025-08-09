package org.apache.logging.log4j.core.time;

import java.io.Serializable;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalQueries;
import java.time.temporal.TemporalQuery;
import java.time.temporal.UnsupportedTemporalTypeException;
import java.time.temporal.ValueRange;
import org.apache.logging.log4j.core.util.Clock;
import org.apache.logging.log4j.util.PerformanceSensitive;

@PerformanceSensitive({"allocation"})
public class MutableInstant implements Instant, Serializable, TemporalAccessor {
   private static final int MILLIS_PER_SECOND = 1000;
   private static final int NANOS_PER_MILLI = 1000000;
   private static final int NANOS_PER_SECOND = 1000000000;
   private long epochSecond;
   private int nanoOfSecond;

   public long getEpochSecond() {
      return this.epochSecond;
   }

   public int getNanoOfSecond() {
      return this.nanoOfSecond;
   }

   public long getEpochMillisecond() {
      int millis = this.nanoOfSecond / 1000000;
      long epochMillisecond = this.epochSecond * 1000L + (long)millis;
      return epochMillisecond;
   }

   public int getNanoOfMillisecond() {
      int millis = this.nanoOfSecond / 1000000;
      int nanoOfMillisecond = this.nanoOfSecond - millis * 1000000;
      return nanoOfMillisecond;
   }

   public void initFrom(final Instant other) {
      this.epochSecond = other.getEpochSecond();
      this.nanoOfSecond = other.getNanoOfSecond();
   }

   public void initFromEpochMilli(final long epochMilli, final int nanoOfMillisecond) {
      this.validateNanoOfMillisecond(nanoOfMillisecond);
      this.epochSecond = epochMilli / 1000L;
      this.nanoOfSecond = (int)(epochMilli - this.epochSecond * 1000L) * 1000000 + nanoOfMillisecond;
   }

   private void validateNanoOfMillisecond(final int nanoOfMillisecond) {
      if (nanoOfMillisecond < 0 || nanoOfMillisecond >= 1000000) {
         throw new IllegalArgumentException("Invalid nanoOfMillisecond " + nanoOfMillisecond);
      }
   }

   public void initFrom(final Clock clock) {
      if (clock instanceof PreciseClock) {
         ((PreciseClock)clock).init(this);
      } else {
         this.initFromEpochMilli(clock.currentTimeMillis(), 0);
      }

   }

   public void initFromEpochSecond(final long epochSecond, final int nano) {
      this.validateNanoOfSecond(nano);
      this.epochSecond = epochSecond;
      this.nanoOfSecond = nano;
   }

   private void validateNanoOfSecond(final int nano) {
      if (nano < 0 || nano >= 1000000000) {
         throw new IllegalArgumentException("Invalid nanoOfSecond " + nano);
      }
   }

   public static void instantToMillisAndNanos(final long epochSecond, final int nano, final long[] result) {
      int millis = nano / 1000000;
      result[0] = epochSecond * 1000L + (long)millis;
      result[1] = (long)(nano - millis * 1000000);
   }

   public boolean isSupported(final TemporalField field) {
      if (field instanceof ChronoField) {
         return field == ChronoField.INSTANT_SECONDS || field == ChronoField.NANO_OF_SECOND || field == ChronoField.MICRO_OF_SECOND || field == ChronoField.MILLI_OF_SECOND;
      } else {
         return field != null && field.isSupportedBy(this);
      }
   }

   public long getLong(final TemporalField field) {
      if (field instanceof ChronoField) {
         switch ((ChronoField)field) {
            case NANO_OF_SECOND:
               return (long)this.nanoOfSecond;
            case MICRO_OF_SECOND:
               return (long)(this.nanoOfSecond / 1000);
            case MILLI_OF_SECOND:
               return (long)(this.nanoOfSecond / 1000000);
            case INSTANT_SECONDS:
               return this.epochSecond;
            default:
               throw new UnsupportedTemporalTypeException("Unsupported field: " + field);
         }
      } else {
         return field.getFrom(this);
      }
   }

   public ValueRange range(final TemporalField field) {
      return super.range(field);
   }

   public int get(final TemporalField field) {
      if (field instanceof ChronoField) {
         switch ((ChronoField)field) {
            case NANO_OF_SECOND:
               return this.nanoOfSecond;
            case MICRO_OF_SECOND:
               return this.nanoOfSecond / 1000;
            case MILLI_OF_SECOND:
               return this.nanoOfSecond / 1000000;
            case INSTANT_SECONDS:
               ChronoField.INSTANT_SECONDS.checkValidIntValue(this.epochSecond);
            default:
               throw new UnsupportedTemporalTypeException("Unsupported field: " + field);
         }
      } else {
         return this.range(field).checkValidIntValue(field.getFrom(this), field);
      }
   }

   public Object query(final TemporalQuery query) {
      if (query == TemporalQueries.precision()) {
         return ChronoUnit.NANOS;
      } else {
         return query != TemporalQueries.chronology() && query != TemporalQueries.zoneId() && query != TemporalQueries.zone() && query != TemporalQueries.offset() && query != TemporalQueries.localDate() && query != TemporalQueries.localTime() ? query.queryFrom(this) : null;
      }
   }

   public boolean equals(final Object object) {
      if (object == this) {
         return true;
      } else if (!(object instanceof MutableInstant)) {
         return false;
      } else {
         MutableInstant other = (MutableInstant)object;
         return this.epochSecond == other.epochSecond && this.nanoOfSecond == other.nanoOfSecond;
      }
   }

   public int hashCode() {
      int result = 17;
      result = 31 * result + (int)(this.epochSecond ^ this.epochSecond >>> 32);
      result = 31 * result + this.nanoOfSecond;
      return result;
   }

   public String toString() {
      StringBuilder sb = new StringBuilder(64);
      this.formatTo(sb);
      return sb.toString();
   }

   public void formatTo(final StringBuilder buffer) {
      buffer.append("MutableInstant[epochSecond=").append(this.epochSecond).append(", nano=").append(this.nanoOfSecond).append("]");
   }
}
