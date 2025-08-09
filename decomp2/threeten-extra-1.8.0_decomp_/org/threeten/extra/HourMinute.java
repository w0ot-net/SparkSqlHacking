package org.threeten.extra;

import java.io.Serializable;
import java.time.Clock;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAmount;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalQueries;
import java.time.temporal.TemporalQuery;
import java.time.temporal.TemporalUnit;
import java.time.temporal.UnsupportedTemporalTypeException;
import java.time.temporal.ValueRange;
import java.util.Objects;
import org.joda.convert.FromString;
import org.joda.convert.ToString;

public final class HourMinute implements Temporal, TemporalAdjuster, Comparable, Serializable {
   public static final HourMinute MIDNIGHT = new HourMinute(0, 0);
   private static final long serialVersionUID = -2532872925L;
   private static final DateTimeFormatter PARSER;
   private static final int HOURS_PER_DAY = 24;
   private static final int MINUTES_PER_HOUR = 60;
   private static final int MINUTES_PER_DAY = 1440;
   private final int hour;
   private final int minute;

   public static HourMinute now() {
      return now(Clock.systemDefaultZone());
   }

   public static HourMinute now(ZoneId zone) {
      return now(Clock.system(zone));
   }

   public static HourMinute now(Clock clock) {
      LocalTime now = LocalTime.now(clock);
      return of(now.getHour(), now.getMinute());
   }

   public static HourMinute of(int hour, int minute) {
      ChronoField.HOUR_OF_DAY.checkValidValue((long)hour);
      ChronoField.MINUTE_OF_HOUR.checkValidValue((long)minute);
      return new HourMinute(hour, minute);
   }

   public static HourMinute from(TemporalAccessor temporal) {
      if (temporal instanceof HourMinute) {
         return (HourMinute)temporal;
      } else {
         Objects.requireNonNull(temporal, "temporal");

         try {
            int hour = Math.toIntExact(temporal.getLong(ChronoField.HOUR_OF_DAY));
            int minute = Math.toIntExact(temporal.getLong(ChronoField.MINUTE_OF_HOUR));
            return of(hour, minute);
         } catch (DateTimeException ex) {
            throw new DateTimeException("Unable to obtain HourMinute from TemporalAccessor: " + temporal + " of type " + temporal.getClass().getName(), ex);
         }
      }
   }

   @FromString
   public static HourMinute parse(CharSequence text) {
      return parse(text, PARSER);
   }

   public static HourMinute parse(CharSequence text, DateTimeFormatter formatter) {
      Objects.requireNonNull(formatter, "formatter");
      return (HourMinute)formatter.parse(text, HourMinute::from);
   }

   private HourMinute(int hour, int minute) {
      this.hour = hour;
      this.minute = minute;
   }

   private Object readResolve() {
      return of(this.hour, this.minute);
   }

   private HourMinute with(int newYear, int newMinute) {
      return this.hour == newYear && this.minute == newMinute ? this : new HourMinute(newYear, newMinute);
   }

   public boolean isSupported(TemporalField field) {
      if (field instanceof ChronoField) {
         return field == ChronoField.MINUTE_OF_HOUR || field == ChronoField.MINUTE_OF_DAY || field == ChronoField.HOUR_OF_AMPM || field == ChronoField.CLOCK_HOUR_OF_AMPM || field == ChronoField.HOUR_OF_DAY || field == ChronoField.CLOCK_HOUR_OF_DAY || field == ChronoField.AMPM_OF_DAY;
      } else {
         return field != null && field.isSupportedBy(this);
      }
   }

   public boolean isSupported(TemporalUnit unit) {
      if (unit instanceof ChronoUnit) {
         return unit == ChronoUnit.MINUTES || unit == ChronoUnit.HOURS || unit == ChronoUnit.HALF_DAYS;
      } else {
         return unit != null && unit.isSupportedBy(this);
      }
   }

   public ValueRange range(TemporalField field) {
      return super.range(field);
   }

   public int get(TemporalField field) {
      return field instanceof ChronoField ? this.get0(field) : super.get(field);
   }

   public long getLong(TemporalField field) {
      return field instanceof ChronoField ? (long)this.get0(field) : field.getFrom(this);
   }

   private int get0(TemporalField field) {
      switch ((ChronoField)field) {
         case MINUTE_OF_HOUR:
            return this.minute;
         case MINUTE_OF_DAY:
            return this.hour * 60 + this.minute;
         case HOUR_OF_AMPM:
            return this.hour % 12;
         case CLOCK_HOUR_OF_AMPM:
            int ham = this.hour % 12;
            return ham % 12 == 0 ? 12 : ham;
         case HOUR_OF_DAY:
            return this.hour;
         case CLOCK_HOUR_OF_DAY:
            return this.hour == 0 ? 24 : this.hour;
         case AMPM_OF_DAY:
            return this.hour / 12;
         default:
            throw new UnsupportedTemporalTypeException("Unsupported field: " + field);
      }
   }

   public int getHour() {
      return this.hour;
   }

   public int getMinute() {
      return this.minute;
   }

   public HourMinute with(TemporalAdjuster adjuster) {
      return (HourMinute)adjuster.adjustInto(this);
   }

   public HourMinute with(TemporalField field, long newValue) {
      if (field instanceof ChronoField) {
         ChronoField f = (ChronoField)field;
         f.checkValidValue(newValue);
         switch (f) {
            case MINUTE_OF_HOUR:
               return this.withMinute((int)newValue);
            case MINUTE_OF_DAY:
               return this.plusMinutes(newValue - (long)(this.hour * 60 + this.minute));
            case HOUR_OF_AMPM:
               return this.plusHours(newValue - (long)(this.hour % 12));
            case CLOCK_HOUR_OF_AMPM:
               return this.plusHours((newValue == 12L ? 0L : newValue) - (long)(this.hour % 12));
            case HOUR_OF_DAY:
               return this.withHour((int)newValue);
            case CLOCK_HOUR_OF_DAY:
               return this.withHour((int)(newValue == 24L ? 0L : newValue));
            case AMPM_OF_DAY:
               return this.plusHours((newValue - (long)(this.hour / 12)) * 12L);
            default:
               throw new UnsupportedTemporalTypeException("Unsupported field: " + field);
         }
      } else {
         return (HourMinute)field.adjustInto(this, newValue);
      }
   }

   public HourMinute withHour(int hour) {
      ChronoField.HOUR_OF_DAY.checkValidValue((long)hour);
      return this.with(hour, this.minute);
   }

   public HourMinute withMinute(int minute) {
      ChronoField.MINUTE_OF_HOUR.checkValidValue((long)minute);
      return this.with(this.hour, minute);
   }

   public HourMinute plus(TemporalAmount amountToAdd) {
      return (HourMinute)amountToAdd.addTo(this);
   }

   public HourMinute plus(long amountToAdd, TemporalUnit unit) {
      if (unit instanceof ChronoUnit) {
         switch ((ChronoUnit)unit) {
            case MINUTES:
               return this.plusMinutes(amountToAdd);
            case HOURS:
               return this.plusHours(amountToAdd);
            case HALF_DAYS:
               return this.plusHours(amountToAdd % 2L * 12L);
            default:
               throw new UnsupportedTemporalTypeException("Unsupported unit: " + unit);
         }
      } else {
         return (HourMinute)unit.addTo(this, amountToAdd);
      }
   }

   public HourMinute plusHours(long hoursToAdd) {
      if (hoursToAdd == 0L) {
         return this;
      } else {
         int newHour = ((int)(hoursToAdd % 24L) + this.hour + 24) % 24;
         return this.with(newHour, this.minute);
      }
   }

   public HourMinute plusMinutes(long minutesToAdd) {
      if (minutesToAdd == 0L) {
         return this;
      } else {
         int mofd = this.hour * 60 + this.minute;
         int newMofd = ((int)(minutesToAdd % 1440L) + mofd + 1440) % 1440;
         if (mofd == newMofd) {
            return this;
         } else {
            int newHour = newMofd / 60;
            int newMinute = newMofd % 60;
            return this.with(newHour, newMinute);
         }
      }
   }

   public HourMinute minus(TemporalAmount amountToSubtract) {
      return (HourMinute)amountToSubtract.subtractFrom(this);
   }

   public HourMinute minus(long amountToSubtract, TemporalUnit unit) {
      return amountToSubtract == Long.MIN_VALUE ? this.plus(Long.MAX_VALUE, unit).plus(1L, unit) : this.plus(-amountToSubtract, unit);
   }

   public HourMinute minusHours(long hoursToSubtract) {
      return hoursToSubtract == Long.MIN_VALUE ? this.plusHours(Long.MAX_VALUE).plusHours(1L) : this.plusHours(-hoursToSubtract);
   }

   public HourMinute minusMinutes(long minutesToSubtract) {
      return minutesToSubtract == Long.MIN_VALUE ? this.plusMinutes(Long.MAX_VALUE).plusMinutes(1L) : this.plusMinutes(-minutesToSubtract);
   }

   public Object query(TemporalQuery query) {
      if (query == TemporalQueries.localTime()) {
         return this.toLocalTime();
      } else {
         return query == TemporalQueries.precision() ? ChronoUnit.MINUTES : super.query(query);
      }
   }

   public Temporal adjustInto(Temporal temporal) {
      return temporal.with(ChronoField.MINUTE_OF_DAY, (long)(this.hour * 60 + this.minute));
   }

   public long until(Temporal endExclusive, TemporalUnit unit) {
      HourMinute end = from(endExclusive);
      long minutesUntil = (long)(end.hour * 60 + end.minute - (this.hour * 60 + this.minute));
      if (unit instanceof ChronoUnit) {
         switch ((ChronoUnit)unit) {
            case MINUTES:
               return minutesUntil;
            case HOURS:
               return minutesUntil / 60L;
            case HALF_DAYS:
               return minutesUntil / 720L;
            default:
               throw new UnsupportedTemporalTypeException("Unsupported unit: " + unit);
         }
      } else {
         return unit.between(this, end);
      }
   }

   public String format(DateTimeFormatter formatter) {
      Objects.requireNonNull(formatter, "formatter");
      return formatter.format(this);
   }

   public LocalDateTime atDate(LocalDate date) {
      return LocalDateTime.of(date, this.toLocalTime());
   }

   public OffsetTime atOffset(ZoneOffset offset) {
      return OffsetTime.of(this.toLocalTime(), offset);
   }

   public LocalTime toLocalTime() {
      return LocalTime.of(this.hour, this.minute);
   }

   public int compareTo(HourMinute other) {
      int cmp = this.hour - other.hour;
      if (cmp == 0) {
         cmp = this.minute - other.minute;
      }

      return cmp;
   }

   public boolean isAfter(HourMinute other) {
      return this.compareTo(other) > 0;
   }

   public boolean isBefore(HourMinute other) {
      return this.compareTo(other) < 0;
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (!(obj instanceof HourMinute)) {
         return false;
      } else {
         HourMinute other = (HourMinute)obj;
         return this.hour == other.hour && this.minute == other.minute;
      }
   }

   public int hashCode() {
      return this.hour * 60 + this.minute;
   }

   @ToString
   public String toString() {
      return (new StringBuilder(5)).append(this.hour < 10 ? "0" : "").append(this.hour).append(this.minute < 10 ? ":0" : ":").append(this.minute).toString();
   }

   static {
      PARSER = (new DateTimeFormatterBuilder()).appendValue(ChronoField.HOUR_OF_DAY, 2).appendLiteral(':').appendValue(ChronoField.MINUTE_OF_HOUR, 2).toFormatter();
   }
}
