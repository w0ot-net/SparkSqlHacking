package org.threeten.extra;

import java.io.Serializable;
import java.time.Clock;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Month;
import java.time.MonthDay;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.chrono.Chronology;
import java.time.chrono.IsoChronology;
import java.time.temporal.ChronoField;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalQueries;
import java.time.temporal.TemporalQuery;
import java.time.temporal.UnsupportedTemporalTypeException;
import java.time.temporal.ValueRange;
import java.util.Objects;

public final class DayOfMonth implements TemporalAccessor, TemporalAdjuster, Comparable, Serializable {
   private static final long serialVersionUID = -8840172642009917873L;
   private static final DayOfMonth[] VALUES = new DayOfMonth[31];
   private final int day;

   public static DayOfMonth now() {
      return now(Clock.systemDefaultZone());
   }

   public static DayOfMonth now(ZoneId zone) {
      return now(Clock.system(zone));
   }

   public static DayOfMonth now(Clock clock) {
      LocalDate now = LocalDate.now(clock);
      return of(now.getDayOfMonth());
   }

   public static DayOfMonth of(int dayOfMonth) {
      try {
         return VALUES[dayOfMonth - 1];
      } catch (IndexOutOfBoundsException var2) {
         throw new DateTimeException("Invalid value for DayOfMonth: " + dayOfMonth);
      }
   }

   public static DayOfMonth from(TemporalAccessor temporal) {
      if (temporal instanceof DayOfMonth) {
         return (DayOfMonth)temporal;
      } else {
         Objects.requireNonNull(temporal, "temporal");

         try {
            if (!IsoChronology.INSTANCE.equals(Chronology.from(temporal))) {
               temporal = LocalDate.from(temporal);
            }

            return of(temporal.get(ChronoField.DAY_OF_MONTH));
         } catch (DateTimeException ex) {
            throw new DateTimeException("Unable to obtain DayOfMonth from TemporalAccessor: " + temporal + " of type " + temporal.getClass().getName(), ex);
         }
      }
   }

   private DayOfMonth(int dayOfMonth) {
      this.day = dayOfMonth;
   }

   private Object readResolve() {
      return of(this.day);
   }

   public int getValue() {
      return this.day;
   }

   public boolean isSupported(TemporalField field) {
      if (field instanceof ChronoField) {
         return field == ChronoField.DAY_OF_MONTH;
      } else {
         return field != null && field.isSupportedBy(this);
      }
   }

   public ValueRange range(TemporalField field) {
      return super.range(field);
   }

   public int get(TemporalField field) {
      return super.get(field);
   }

   public long getLong(TemporalField field) {
      if (field == ChronoField.DAY_OF_MONTH) {
         return (long)this.day;
      } else if (field instanceof ChronoField) {
         throw new UnsupportedTemporalTypeException("Unsupported field: " + field);
      } else {
         return field.getFrom(this);
      }
   }

   public boolean isValidYearMonth(YearMonth yearMonth) {
      return yearMonth != null && yearMonth.isValidDay(this.day);
   }

   public Object query(TemporalQuery query) {
      return query == TemporalQueries.chronology() ? IsoChronology.INSTANCE : super.query(query);
   }

   public Temporal adjustInto(Temporal temporal) {
      if (!Chronology.from(temporal).equals(IsoChronology.INSTANCE)) {
         throw new DateTimeException("Adjustment only supported on ISO date-time");
      } else {
         return temporal.with(ChronoField.DAY_OF_MONTH, (long)this.day);
      }
   }

   public MonthDay atMonth(Month month) {
      return MonthDay.of(month, Math.min(this.day, month.maxLength()));
   }

   public MonthDay atMonth(int month) {
      return this.atMonth(Month.of(month));
   }

   public LocalDate atYearMonth(YearMonth yearMonth) {
      return yearMonth.atDay(Math.min(this.day, yearMonth.lengthOfMonth()));
   }

   public int compareTo(DayOfMonth other) {
      return this.day - other.day;
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (obj instanceof DayOfMonth) {
         return this.day == ((DayOfMonth)obj).day;
      } else {
         return false;
      }
   }

   public int hashCode() {
      return this.day;
   }

   public String toString() {
      return "DayOfMonth:" + this.day;
   }

   static {
      for(int i = 0; i < 31; ++i) {
         VALUES[i] = new DayOfMonth(i + 1);
      }

   }
}
