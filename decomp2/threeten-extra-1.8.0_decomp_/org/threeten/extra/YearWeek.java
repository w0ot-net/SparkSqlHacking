package org.threeten.extra;

import java.io.Serializable;
import java.time.Clock;
import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Year;
import java.time.ZoneId;
import java.time.chrono.Chronology;
import java.time.chrono.IsoChronology;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.SignStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.IsoFields;
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

public final class YearWeek implements Temporal, TemporalAdjuster, Comparable, Serializable {
   private static final long serialVersionUID = 3381384054271883921L;
   private static final DateTimeFormatter PARSER;
   private final int year;
   private final int week;

   public static YearWeek now() {
      return now(Clock.systemDefaultZone());
   }

   public static YearWeek now(ZoneId zone) {
      return now(Clock.system(zone));
   }

   public static YearWeek now(Clock clock) {
      LocalDate now = LocalDate.now(clock);
      return of(now.get(IsoFields.WEEK_BASED_YEAR), now.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR));
   }

   public static YearWeek of(Year year, int week) {
      return of(year.getValue(), week);
   }

   public static YearWeek of(int weekBasedYear, int week) {
      IsoFields.WEEK_BASED_YEAR.range().checkValidValue((long)weekBasedYear, IsoFields.WEEK_BASED_YEAR);
      IsoFields.WEEK_OF_WEEK_BASED_YEAR.range().checkValidValue((long)week, IsoFields.WEEK_OF_WEEK_BASED_YEAR);
      if (week == 53 && weekRange(weekBasedYear) < 53) {
         week = 1;
         ++weekBasedYear;
         IsoFields.WEEK_BASED_YEAR.range().checkValidValue((long)weekBasedYear, IsoFields.WEEK_BASED_YEAR);
      }

      return new YearWeek(weekBasedYear, week);
   }

   private static int weekRange(int weekBasedYear) {
      LocalDate date = LocalDate.of(weekBasedYear, 1, 1);
      return date.getDayOfWeek() != DayOfWeek.THURSDAY && (date.getDayOfWeek() != DayOfWeek.WEDNESDAY || !date.isLeapYear()) ? 52 : 53;
   }

   public static YearWeek from(TemporalAccessor temporal) {
      if (temporal instanceof YearWeek) {
         return (YearWeek)temporal;
      } else {
         Objects.requireNonNull(temporal, "temporal");

         try {
            if (!IsoChronology.INSTANCE.equals(Chronology.from(temporal))) {
               temporal = LocalDate.from(temporal);
            }

            int year = Math.toIntExact(temporal.getLong(IsoFields.WEEK_BASED_YEAR));
            int week = Math.toIntExact(temporal.getLong(IsoFields.WEEK_OF_WEEK_BASED_YEAR));
            return of(year, week);
         } catch (DateTimeException ex) {
            throw new DateTimeException("Unable to obtain YearWeek from TemporalAccessor: " + temporal + " of type " + temporal.getClass().getName(), ex);
         }
      }
   }

   @FromString
   public static YearWeek parse(CharSequence text) {
      return parse(text, PARSER);
   }

   public static YearWeek parse(CharSequence text, DateTimeFormatter formatter) {
      Objects.requireNonNull(formatter, "formatter");
      return (YearWeek)formatter.parse(text, YearWeek::from);
   }

   private YearWeek(int weekBasedYear, int week) {
      this.year = weekBasedYear;
      this.week = week;
   }

   private Object readResolve() {
      return of(this.year, this.week);
   }

   private YearWeek with(int newYear, int newWeek) {
      return this.year == newYear && this.week == newWeek ? this : of(newYear, newWeek);
   }

   public boolean isSupported(TemporalField field) {
      if (field != IsoFields.WEEK_OF_WEEK_BASED_YEAR && field != IsoFields.WEEK_BASED_YEAR) {
         if (field instanceof ChronoField) {
            return false;
         } else {
            return field != null && field.isSupportedBy(this);
         }
      } else {
         return true;
      }
   }

   public boolean isSupported(TemporalUnit unit) {
      if (unit != ChronoUnit.WEEKS && unit != IsoFields.WEEK_BASED_YEARS) {
         if (unit instanceof ChronoUnit) {
            return false;
         } else {
            return unit != null && unit.isSupportedBy(this);
         }
      } else {
         return true;
      }
   }

   public ValueRange range(TemporalField field) {
      if (field == IsoFields.WEEK_BASED_YEAR) {
         return IsoFields.WEEK_BASED_YEAR.range();
      } else {
         return field == IsoFields.WEEK_OF_WEEK_BASED_YEAR ? ValueRange.of(1L, (long)weekRange(this.year)) : super.range(field);
      }
   }

   public int get(TemporalField field) {
      if (field == IsoFields.WEEK_BASED_YEAR) {
         return this.year;
      } else {
         return field == IsoFields.WEEK_OF_WEEK_BASED_YEAR ? this.week : super.get(field);
      }
   }

   public long getLong(TemporalField field) {
      if (field == IsoFields.WEEK_BASED_YEAR) {
         return (long)this.year;
      } else if (field == IsoFields.WEEK_OF_WEEK_BASED_YEAR) {
         return (long)this.week;
      } else if (field instanceof ChronoField) {
         throw new UnsupportedTemporalTypeException("Unsupported field: " + field);
      } else {
         return field.getFrom(this);
      }
   }

   public int getYear() {
      return this.year;
   }

   public int getWeek() {
      return this.week;
   }

   public boolean is53WeekYear() {
      return weekRange(this.year) == 53;
   }

   public int lengthOfYear() {
      return this.is53WeekYear() ? 371 : 364;
   }

   public YearWeek with(TemporalAdjuster adjuster) {
      return (YearWeek)adjuster.adjustInto(this);
   }

   public YearWeek with(TemporalField field, long newValue) {
      if (field == IsoFields.WEEK_OF_WEEK_BASED_YEAR) {
         return this.withWeek(IsoFields.WEEK_OF_WEEK_BASED_YEAR.range().checkValidIntValue(newValue, IsoFields.WEEK_OF_WEEK_BASED_YEAR));
      } else if (field == IsoFields.WEEK_BASED_YEAR) {
         return this.withYear(IsoFields.WEEK_BASED_YEAR.range().checkValidIntValue(newValue, IsoFields.WEEK_BASED_YEAR));
      } else if (field instanceof ChronoField) {
         throw new UnsupportedTemporalTypeException("Unsupported field: " + field);
      } else {
         return (YearWeek)field.adjustInto(this, newValue);
      }
   }

   public YearWeek withYear(int weekBasedYear) {
      return this.week == 53 && weekRange(weekBasedYear) < 53 ? of(weekBasedYear, 52) : this.with(weekBasedYear, this.week);
   }

   public YearWeek withWeek(int week) {
      return this.with(this.year, week);
   }

   public YearWeek plus(TemporalAmount amountToAdd) {
      return (YearWeek)amountToAdd.addTo(this);
   }

   public YearWeek plus(long amountToAdd, TemporalUnit unit) {
      if (unit == ChronoUnit.WEEKS) {
         return this.plusWeeks(amountToAdd);
      } else if (unit == IsoFields.WEEK_BASED_YEARS) {
         return this.plusYears(amountToAdd);
      } else if (unit instanceof ChronoUnit) {
         throw new UnsupportedTemporalTypeException("Unsupported unit: " + unit);
      } else {
         return (YearWeek)unit.addTo(this, amountToAdd);
      }
   }

   public YearWeek plusYears(long yearsToAdd) {
      if (yearsToAdd == 0L) {
         return this;
      } else {
         int newYear = Math.toIntExact(Math.addExact((long)this.year, yearsToAdd));
         return this.withYear(newYear);
      }
   }

   public YearWeek plusWeeks(long weeksToAdd) {
      if (weeksToAdd == 0L) {
         return this;
      } else {
         LocalDate mondayOfWeek = this.atDay(DayOfWeek.MONDAY).plusWeeks(weeksToAdd);
         return from(mondayOfWeek);
      }
   }

   public YearWeek minus(TemporalAmount amountToSubtract) {
      return (YearWeek)amountToSubtract.subtractFrom(this);
   }

   public YearWeek minus(long amountToSubtract, TemporalUnit unit) {
      return amountToSubtract == Long.MIN_VALUE ? this.plus(Long.MAX_VALUE, unit).plus(1L, unit) : this.plus(-amountToSubtract, unit);
   }

   public YearWeek minusYears(long yearsToSubtract) {
      if (yearsToSubtract == 0L) {
         return this;
      } else {
         int newYear = Math.toIntExact(Math.subtractExact((long)this.year, yearsToSubtract));
         return this.withYear(newYear);
      }
   }

   public YearWeek minusWeeks(long weeksToSubtract) {
      if (weeksToSubtract == 0L) {
         return this;
      } else {
         LocalDate mondayOfWeek = this.atDay(DayOfWeek.MONDAY).minusWeeks(weeksToSubtract);
         return from(mondayOfWeek);
      }
   }

   public Object query(TemporalQuery query) {
      return query == TemporalQueries.chronology() ? IsoChronology.INSTANCE : super.query(query);
   }

   public Temporal adjustInto(Temporal temporal) {
      if (!Chronology.from(temporal).equals(IsoChronology.INSTANCE)) {
         throw new DateTimeException("Adjustment only supported on ISO date-time");
      } else {
         return temporal.with(IsoFields.WEEK_BASED_YEAR, (long)this.year).with(IsoFields.WEEK_OF_WEEK_BASED_YEAR, (long)this.week);
      }
   }

   public long until(Temporal endExclusive, TemporalUnit unit) {
      YearWeek end = from(endExclusive);
      if (unit == ChronoUnit.WEEKS) {
         return this.daysUntil(end);
      } else if (unit == IsoFields.WEEK_BASED_YEARS) {
         return this.yearsUntil(end);
      } else if (unit instanceof ChronoUnit) {
         throw new UnsupportedTemporalTypeException("Unsupported unit: " + unit);
      } else {
         return unit.between(this, end);
      }
   }

   private long daysUntil(YearWeek end) {
      LocalDate startDate = this.atDay(DayOfWeek.MONDAY);
      LocalDate endDate = end.atDay(DayOfWeek.MONDAY);
      long days = endDate.toEpochDay() - startDate.toEpochDay();
      return days / 7L;
   }

   private long yearsUntil(YearWeek end) {
      long yearsDiff = (long)(end.year - this.year);
      if (yearsDiff > 0L && end.week < this.week) {
         return yearsDiff - 1L;
      } else {
         return yearsDiff < 0L && end.week > this.week ? yearsDiff + 1L : yearsDiff;
      }
   }

   public String format(DateTimeFormatter formatter) {
      Objects.requireNonNull(formatter, "formatter");
      return formatter.format(this);
   }

   public LocalDate atDay(DayOfWeek dayOfWeek) {
      Objects.requireNonNull(dayOfWeek, "dayOfWeek");
      int correction = LocalDate.of(this.year, 1, 4).getDayOfWeek().getValue() + 3;
      int dayOfYear = this.week * 7 + dayOfWeek.getValue() - correction;
      int maxDaysOfYear = Year.isLeap((long)this.year) ? 366 : 365;
      if (dayOfYear > maxDaysOfYear) {
         return LocalDate.ofYearDay(this.year + 1, dayOfYear - maxDaysOfYear);
      } else if (dayOfYear > 0) {
         return LocalDate.ofYearDay(this.year, dayOfYear);
      } else {
         int daysOfPreviousYear = Year.isLeap((long)(this.year - 1)) ? 366 : 365;
         return LocalDate.ofYearDay(this.year - 1, daysOfPreviousYear + dayOfYear);
      }
   }

   public int compareTo(YearWeek other) {
      int cmp = this.year - other.year;
      if (cmp == 0) {
         cmp = this.week - other.week;
      }

      return cmp;
   }

   public boolean isAfter(YearWeek other) {
      return this.compareTo(other) > 0;
   }

   public boolean isBefore(YearWeek other) {
      return this.compareTo(other) < 0;
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (!(obj instanceof YearWeek)) {
         return false;
      } else {
         YearWeek other = (YearWeek)obj;
         return this.year == other.year && this.week == other.week;
      }
   }

   public int hashCode() {
      return this.year ^ this.week << 25;
   }

   @ToString
   public String toString() {
      int absYear = Math.abs(this.year);
      StringBuilder buf = new StringBuilder(10);
      if (absYear < 1000) {
         if (this.year < 0) {
            buf.append(this.year - 10000).deleteCharAt(1);
         } else {
            buf.append(this.year + 10000).deleteCharAt(0);
         }
      } else {
         if (this.year > 9999) {
            buf.append('+');
         }

         buf.append(this.year);
      }

      return buf.append(this.week < 10 ? "-W0" : "-W").append(this.week).toString();
   }

   static {
      PARSER = (new DateTimeFormatterBuilder()).parseCaseInsensitive().appendValue(IsoFields.WEEK_BASED_YEAR, 4, 10, SignStyle.EXCEEDS_PAD).appendLiteral("-W").appendValue(IsoFields.WEEK_OF_WEEK_BASED_YEAR, 2).toFormatter();
   }
}
