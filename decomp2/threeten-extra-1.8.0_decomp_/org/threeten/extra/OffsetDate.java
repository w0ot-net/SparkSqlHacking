package org.threeten.extra;

import java.io.Serializable;
import java.time.Clock;
import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.chrono.IsoChronology;
import java.time.format.DateTimeFormatter;
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
import java.time.temporal.ValueRange;
import java.time.zone.ZoneRules;
import java.util.Objects;
import org.joda.convert.FromString;
import org.joda.convert.ToString;

public final class OffsetDate implements Temporal, TemporalAdjuster, Comparable, Serializable {
   public static final OffsetDate MIN;
   public static final OffsetDate MAX;
   private static final long serialVersionUID = -4382054179074397774L;
   private static final long SECONDS_PER_DAY = 86400L;
   private final LocalDate date;
   private final ZoneOffset offset;

   public static OffsetDate now() {
      return now(Clock.systemDefaultZone());
   }

   public static OffsetDate now(ZoneId zone) {
      return now(Clock.system(zone));
   }

   public static OffsetDate now(Clock clock) {
      Objects.requireNonNull(clock, "clock");
      Instant now = clock.instant();
      return ofInstant(now, clock.getZone().getRules().getOffset(now));
   }

   public static OffsetDate of(LocalDate date, ZoneOffset offset) {
      return new OffsetDate(date, offset);
   }

   public static OffsetDate of(int year, int month, int dayOfMonth, ZoneOffset offset) {
      LocalDate d = LocalDate.of(year, month, dayOfMonth);
      return new OffsetDate(d, offset);
   }

   public static OffsetDate ofInstant(Instant instant, ZoneId zone) {
      Objects.requireNonNull(instant, "instant");
      Objects.requireNonNull(zone, "zone");
      ZoneRules rules = zone.getRules();
      ZoneOffset offset = rules.getOffset(instant);
      long epochSec = instant.getEpochSecond() + (long)offset.getTotalSeconds();
      long epochDay = Math.floorDiv(epochSec, 86400L);
      LocalDate date = LocalDate.ofEpochDay(epochDay);
      return new OffsetDate(date, offset);
   }

   public static OffsetDate from(TemporalAccessor temporal) {
      if (temporal instanceof OffsetDate) {
         return (OffsetDate)temporal;
      } else {
         try {
            LocalDate date = LocalDate.from(temporal);
            ZoneOffset offset = ZoneOffset.from(temporal);
            return new OffsetDate(date, offset);
         } catch (DateTimeException ex) {
            throw new DateTimeException("Unable to obtain OffsetDate from TemporalAccessor: " + temporal.getClass(), ex);
         }
      }
   }

   @FromString
   public static OffsetDate parse(CharSequence text) {
      return parse(text, DateTimeFormatter.ISO_OFFSET_DATE);
   }

   public static OffsetDate parse(CharSequence text, DateTimeFormatter formatter) {
      Objects.requireNonNull(formatter, "formatter");
      return (OffsetDate)formatter.parse(text, OffsetDate::from);
   }

   private OffsetDate(LocalDate date, ZoneOffset offset) {
      this.date = (LocalDate)Objects.requireNonNull(date, "date");
      this.offset = (ZoneOffset)Objects.requireNonNull(offset, "offset");
   }

   private Object readResolve() {
      return of(this.date, this.offset);
   }

   private OffsetDate with(LocalDate date, ZoneOffset offset) {
      return this.date == date && this.offset.equals(offset) ? this : new OffsetDate(date, offset);
   }

   public boolean isSupported(TemporalField field) {
      if (field instanceof ChronoField) {
         return field.isDateBased() || field == ChronoField.OFFSET_SECONDS;
      } else {
         return field != null && field.isSupportedBy(this);
      }
   }

   public boolean isSupported(TemporalUnit unit) {
      if (unit instanceof ChronoUnit) {
         return unit.isDateBased();
      } else {
         return unit != null && unit.isSupportedBy(this);
      }
   }

   public ValueRange range(TemporalField field) {
      if (field instanceof ChronoField) {
         return field == ChronoField.OFFSET_SECONDS ? field.range() : this.date.range(field);
      } else {
         return field.rangeRefinedBy(this);
      }
   }

   public int get(TemporalField field) {
      return super.get(field);
   }

   public long getLong(TemporalField field) {
      if (field instanceof ChronoField) {
         return field == ChronoField.OFFSET_SECONDS ? (long)this.getOffset().getTotalSeconds() : this.date.getLong(field);
      } else {
         return field.getFrom(this);
      }
   }

   public ZoneOffset getOffset() {
      return this.offset;
   }

   public OffsetDate withOffsetSameLocal(ZoneOffset offset) {
      Objects.requireNonNull(offset, "offset");
      return this.with(this.date, offset);
   }

   public LocalDate toLocalDate() {
      return this.date;
   }

   public int getYear() {
      return this.date.getYear();
   }

   public int getMonthValue() {
      return this.date.getMonthValue();
   }

   public Month getMonth() {
      return this.date.getMonth();
   }

   public int getDayOfMonth() {
      return this.date.getDayOfMonth();
   }

   public int getDayOfYear() {
      return this.date.getDayOfYear();
   }

   public DayOfWeek getDayOfWeek() {
      return this.date.getDayOfWeek();
   }

   public OffsetDate with(TemporalAdjuster adjuster) {
      if (adjuster instanceof LocalDate) {
         return this.with((LocalDate)adjuster, this.offset);
      } else if (adjuster instanceof ZoneOffset) {
         return this.with(this.date, (ZoneOffset)adjuster);
      } else {
         return adjuster instanceof OffsetDate ? (OffsetDate)adjuster : (OffsetDate)adjuster.adjustInto(this);
      }
   }

   public OffsetDate with(TemporalField field, long newValue) {
      if (field instanceof ChronoField) {
         if (field == ChronoField.OFFSET_SECONDS) {
            ChronoField f = (ChronoField)field;
            return this.with(this.date, ZoneOffset.ofTotalSeconds(f.checkValidIntValue(newValue)));
         } else {
            return this.with(this.date.with(field, newValue), this.offset);
         }
      } else {
         return (OffsetDate)field.adjustInto(this, newValue);
      }
   }

   public OffsetDate withYear(int year) {
      return this.with(this.date.withYear(year), this.offset);
   }

   public OffsetDate withMonth(int month) {
      return this.with(this.date.withMonth(month), this.offset);
   }

   public OffsetDate withDayOfMonth(int dayOfMonth) {
      return this.with(this.date.withDayOfMonth(dayOfMonth), this.offset);
   }

   public OffsetDate withDayOfYear(int dayOfYear) {
      return this.with(this.date.withDayOfYear(dayOfYear), this.offset);
   }

   public OffsetDate plus(TemporalAmount amountToAdd) {
      return (OffsetDate)amountToAdd.addTo(this);
   }

   public OffsetDate plus(long amountToAdd, TemporalUnit unit) {
      return unit instanceof ChronoUnit ? this.with(this.date.plus(amountToAdd, unit), this.offset) : (OffsetDate)unit.addTo(this, amountToAdd);
   }

   public OffsetDate plusYears(long years) {
      return this.with(this.date.plusYears(years), this.offset);
   }

   public OffsetDate plusMonths(long months) {
      return this.with(this.date.plusMonths(months), this.offset);
   }

   public OffsetDate plusWeeks(long weeks) {
      return this.with(this.date.plusWeeks(weeks), this.offset);
   }

   public OffsetDate plusDays(long days) {
      return this.with(this.date.plusDays(days), this.offset);
   }

   public OffsetDate minus(TemporalAmount amountToSubtract) {
      return (OffsetDate)amountToSubtract.subtractFrom(this);
   }

   public OffsetDate minus(long amountToSubtract, TemporalUnit unit) {
      return amountToSubtract == Long.MIN_VALUE ? this.plus(Long.MAX_VALUE, unit).plus(1L, unit) : this.plus(-amountToSubtract, unit);
   }

   public OffsetDate minusYears(long years) {
      return this.with(this.date.minusYears(years), this.offset);
   }

   public OffsetDate minusMonths(long months) {
      return this.with(this.date.minusMonths(months), this.offset);
   }

   public OffsetDate minusWeeks(long weeks) {
      return this.with(this.date.minusWeeks(weeks), this.offset);
   }

   public OffsetDate minusDays(long days) {
      return this.with(this.date.minusDays(days), this.offset);
   }

   public Object query(TemporalQuery query) {
      if (query == TemporalQueries.chronology()) {
         return IsoChronology.INSTANCE;
      } else if (query == TemporalQueries.precision()) {
         return ChronoUnit.DAYS;
      } else {
         return query != TemporalQueries.offset() && query != TemporalQueries.zone() ? super.query(query) : this.getOffset();
      }
   }

   public Temporal adjustInto(Temporal temporal) {
      return temporal.with(ChronoField.OFFSET_SECONDS, (long)this.getOffset().getTotalSeconds()).with(ChronoField.EPOCH_DAY, this.toLocalDate().toEpochDay());
   }

   public long until(Temporal endExclusive, TemporalUnit unit) {
      OffsetDate end = from(endExclusive);
      if (unit instanceof ChronoUnit) {
         long offsetDiff = (long)(end.offset.getTotalSeconds() - this.offset.getTotalSeconds());
         LocalDate endLocal = end.date.plusDays(Math.floorDiv(-offsetDiff, 86400L));
         return this.date.until(endLocal, unit);
      } else {
         return unit.between(this, end);
      }
   }

   public String format(DateTimeFormatter formatter) {
      Objects.requireNonNull(formatter, "formatter");
      return formatter.format(this);
   }

   public OffsetDateTime atTime(LocalTime time) {
      return OffsetDateTime.of(this.date, time, this.offset);
   }

   private long toEpochSecond() {
      long epochDay = this.date.toEpochDay();
      long secs = epochDay * 86400L;
      return secs - (long)this.offset.getTotalSeconds();
   }

   public long toEpochSecond(LocalTime time) {
      Objects.requireNonNull(time, "time");
      return this.toEpochSecond() + (long)time.toSecondOfDay();
   }

   public int compareTo(OffsetDate other) {
      if (this.offset.equals(other.offset)) {
         return this.date.compareTo(other.date);
      } else {
         int compare = Long.compare(this.toEpochSecond(), other.toEpochSecond());
         if (compare == 0) {
            compare = this.date.compareTo(other.date);
         }

         return compare;
      }
   }

   public boolean isAfter(OffsetDate other) {
      return this.toEpochSecond() > other.toEpochSecond();
   }

   public boolean isBefore(OffsetDate other) {
      return this.toEpochSecond() < other.toEpochSecond();
   }

   public boolean isEqual(OffsetDate other) {
      return this.toEpochSecond() == other.toEpochSecond();
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (!(obj instanceof OffsetDate)) {
         return false;
      } else {
         OffsetDate other = (OffsetDate)obj;
         return this.date.equals(other.date) && this.offset.equals(other.offset);
      }
   }

   public int hashCode() {
      return this.date.hashCode() ^ this.offset.hashCode();
   }

   @ToString
   public String toString() {
      return this.date.toString() + this.offset.toString();
   }

   static {
      MIN = of(LocalDate.MIN, ZoneOffset.MAX);
      MAX = of(LocalDate.MAX, ZoneOffset.MIN);
   }
}
