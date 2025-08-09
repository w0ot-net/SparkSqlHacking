package org.sparkproject.jpmml.model.temporals;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.SignStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalQuery;
import java.time.temporal.TemporalUnit;
import java.time.temporal.ValueRange;
import java.util.Objects;
import org.sparkproject.dmg.pmml.DataType;

public class SecondsSinceMidnight extends SimplePeriod {
   private long seconds = 0L;
   private static final TemporalField HOURS_OF_EPOCH = new TemporalField() {
      private ValueRange range = ValueRange.of(0L, Long.MAX_VALUE);

      public TemporalUnit getBaseUnit() {
         return ChronoUnit.HOURS;
      }

      public TemporalUnit getRangeUnit() {
         return ChronoUnit.FOREVER;
      }

      public ValueRange range() {
         return this.range;
      }

      public boolean isDateBased() {
         return false;
      }

      public boolean isTimeBased() {
         return false;
      }

      public boolean isSupportedBy(TemporalAccessor temporal) {
         return temporal.isSupported(this);
      }

      public ValueRange rangeRefinedBy(TemporalAccessor temporal) {
         return temporal.range(this);
      }

      public long getFrom(TemporalAccessor temporal) {
         return temporal.getLong(this);
      }

      public Temporal adjustInto(Temporal temporal, long value) {
         return temporal.with(this, value);
      }

      public String toString() {
         return "HoursOfEpoch";
      }
   };
   private static final DateTimeFormatter FORMATTER = createFormatter();
   private static final TemporalQuery QUERY = new TemporalQuery() {
      public SecondsSinceMidnight queryFrom(TemporalAccessor temporal) {
         long hoursOfEpoch = temporal.getLong(SecondsSinceMidnight.HOURS_OF_EPOCH);
         long minutesOfHour = temporal.getLong(ChronoField.MINUTE_OF_HOUR);
         long secondsOfMinute = temporal.getLong(ChronoField.SECOND_OF_MINUTE);
         long seconds = hoursOfEpoch * 60L * 60L + minutesOfHour * 60L + secondsOfMinute;
         return new SecondsSinceMidnight(seconds);
      }
   };

   private SecondsSinceMidnight() {
   }

   public SecondsSinceMidnight(long seconds) {
      this.setSeconds(seconds);
   }

   public DataType getDataType() {
      return DataType.TIME_SECONDS;
   }

   public long longValue() {
      return this.getSeconds();
   }

   public int compareTo(SecondsSinceMidnight that) {
      return Long.compare(this.getSeconds(), that.getSeconds());
   }

   public int hashCode() {
      return Objects.hashCode(this.getSeconds());
   }

   public boolean equals(Object object) {
      if (object instanceof SecondsSinceMidnight) {
         SecondsSinceMidnight that = (SecondsSinceMidnight)object;
         return this.getSeconds() == that.getSeconds();
      } else {
         return false;
      }
   }

   private SecondsSinceMidnight toNegative() {
      this.setSeconds(-1L * this.getSeconds());
      return this;
   }

   public long getSeconds() {
      return this.seconds;
   }

   private void setSeconds(long seconds) {
      this.seconds = seconds;
   }

   public static SecondsSinceMidnight parse(String string) {
      DateTimeFormatter formatter = FORMATTER;
      if (string.startsWith("-")) {
         SecondsSinceMidnight period = (SecondsSinceMidnight)formatter.parse(string.substring(1), QUERY);
         return period.toNegative();
      } else {
         SecondsSinceMidnight period = (SecondsSinceMidnight)formatter.parse(string, QUERY);
         return period;
      }
   }

   private static DateTimeFormatter createFormatter() {
      DateTimeFormatterBuilder builder = (new DateTimeFormatterBuilder()).appendValue(HOURS_OF_EPOCH, 1, 4, SignStyle.NOT_NEGATIVE).appendLiteral(':').appendValue(ChronoField.MINUTE_OF_HOUR, 2).appendLiteral(':').appendValue(ChronoField.SECOND_OF_MINUTE, 2);
      return builder.toFormatter();
   }
}
