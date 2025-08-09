package org.threeten.extra;

import java.io.Serializable;
import java.time.Clock;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Year;
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

public final class DayOfYear implements TemporalAccessor, TemporalAdjuster, Comparable, Serializable {
   private static final long serialVersionUID = -8789692114017384034L;
   private static final DayOfYear[] VALUES = new DayOfYear[366];
   private final int day;

   public static DayOfYear now() {
      return now(Clock.systemDefaultZone());
   }

   public static DayOfYear now(ZoneId zone) {
      return now(Clock.system(zone));
   }

   public static DayOfYear now(Clock clock) {
      LocalDate now = LocalDate.now(clock);
      return of(now.getDayOfYear());
   }

   public static DayOfYear of(int dayOfYear) {
      try {
         return VALUES[dayOfYear - 1];
      } catch (IndexOutOfBoundsException var2) {
         throw new DateTimeException("Invalid value for DayOfYear: " + dayOfYear);
      }
   }

   public static DayOfYear from(TemporalAccessor temporal) {
      if (temporal instanceof DayOfYear) {
         return (DayOfYear)temporal;
      } else {
         Objects.requireNonNull(temporal, "temporal");

         try {
            if (!IsoChronology.INSTANCE.equals(Chronology.from(temporal))) {
               temporal = LocalDate.from(temporal);
            }

            return of(temporal.get(ChronoField.DAY_OF_YEAR));
         } catch (DateTimeException ex) {
            throw new DateTimeException("Unable to obtain DayOfYear from TemporalAccessor: " + temporal + " of type " + temporal.getClass().getName(), ex);
         }
      }
   }

   private DayOfYear(int dayOfYear) {
      this.day = dayOfYear;
   }

   private Object readResolve() {
      return of(this.day);
   }

   public int getValue() {
      return this.day;
   }

   public boolean isSupported(TemporalField field) {
      if (field instanceof ChronoField) {
         return field == ChronoField.DAY_OF_YEAR;
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
      if (field == ChronoField.DAY_OF_YEAR) {
         return (long)this.day;
      } else if (field instanceof ChronoField) {
         throw new UnsupportedTemporalTypeException("Unsupported field: " + field);
      } else {
         return field.getFrom(this);
      }
   }

   public boolean isValidYear(int year) {
      return this.day < 366 || Year.isLeap((long)year);
   }

   public Object query(TemporalQuery query) {
      return query == TemporalQueries.chronology() ? IsoChronology.INSTANCE : super.query(query);
   }

   public Temporal adjustInto(Temporal temporal) {
      if (!Chronology.from(temporal).equals(IsoChronology.INSTANCE)) {
         throw new DateTimeException("Adjustment only supported on ISO date-time");
      } else {
         return temporal.with(ChronoField.DAY_OF_YEAR, (long)this.day);
      }
   }

   public LocalDate atYear(Year year) {
      Objects.requireNonNull(year, "year");
      return year.atDay(this.day);
   }

   public LocalDate atYear(int year) {
      return LocalDate.ofYearDay(year, this.day);
   }

   public int compareTo(DayOfYear other) {
      return this.day - other.day;
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (obj instanceof DayOfYear) {
         return this.day == ((DayOfYear)obj).day;
      } else {
         return false;
      }
   }

   public int hashCode() {
      return this.day;
   }

   public String toString() {
      return "DayOfYear:" + this.day;
   }

   static {
      for(int i = 0; i < 366; ++i) {
         VALUES[i] = new DayOfYear(i + 1);
      }

   }
}
