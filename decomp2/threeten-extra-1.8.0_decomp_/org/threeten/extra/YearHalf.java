package org.threeten.extra;

import java.io.Serializable;
import java.time.Clock;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.ZoneId;
import java.time.chrono.Chronology;
import java.time.chrono.IsoChronology;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.SignStyle;
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
import java.util.stream.LongStream;
import java.util.stream.Stream;
import org.joda.convert.FromString;
import org.joda.convert.ToString;

public final class YearHalf implements Temporal, TemporalAdjuster, Comparable, Serializable {
   private static final long serialVersionUID = 782467825761518L;
   private static final DateTimeFormatter PARSER;
   private final int year;
   private final Half half;

   public static YearHalf now() {
      return now(Clock.systemDefaultZone());
   }

   public static YearHalf now(ZoneId zone) {
      return now(Clock.system(zone));
   }

   public static YearHalf now(Clock clock) {
      LocalDate now = LocalDate.now(clock);
      return of(now.getYear(), Half.from(now.getMonth()));
   }

   public static YearHalf of(Year year, Half half) {
      return of(year.getValue(), half);
   }

   public static YearHalf of(Year year, int half) {
      return of(year.getValue(), Half.of(half));
   }

   public static YearHalf of(int year, Half half) {
      ChronoField.YEAR.checkValidValue((long)year);
      Objects.requireNonNull(half, "half");
      return new YearHalf(year, half);
   }

   public static YearHalf of(int year, int half) {
      ChronoField.YEAR.checkValidValue((long)year);
      return new YearHalf(year, Half.of(half));
   }

   public static YearHalf from(TemporalAccessor temporal) {
      if (temporal instanceof YearHalf) {
         return (YearHalf)temporal;
      } else {
         Objects.requireNonNull(temporal, "temporal");

         try {
            TemporalAccessor adjusted = (TemporalAccessor)(!IsoChronology.INSTANCE.equals(Chronology.from(temporal)) ? LocalDate.from(temporal) : temporal);
            int year = Math.toIntExact(adjusted.getLong(ChronoField.YEAR));
            int hoy = Math.toIntExact(adjusted.getLong(TemporalFields.HALF_OF_YEAR));
            return of(year, hoy);
         } catch (DateTimeException ex) {
            throw new DateTimeException("Unable to obtain YearHalf from TemporalAccessor: " + temporal + " of type " + temporal.getClass().getName(), ex);
         }
      }
   }

   @FromString
   public static YearHalf parse(CharSequence text) {
      return parse(text, PARSER);
   }

   public static YearHalf parse(CharSequence text, DateTimeFormatter formatter) {
      Objects.requireNonNull(formatter, "formatter");
      return (YearHalf)formatter.parse(text, YearHalf::from);
   }

   private YearHalf(int year, Half half) {
      this.year = year;
      this.half = half;
   }

   private Object readResolve() {
      return of(this.year, this.half);
   }

   private YearHalf with(int newYear, Half newHalf) {
      return this.year == newYear && this.half == newHalf ? this : new YearHalf(newYear, newHalf);
   }

   public boolean isSupported(TemporalField field) {
      if (field == TemporalFields.HALF_OF_YEAR) {
         return true;
      } else if (field instanceof ChronoField) {
         return field == ChronoField.YEAR || field == ChronoField.YEAR_OF_ERA || field == ChronoField.ERA;
      } else {
         return field != null && field.isSupportedBy(this);
      }
   }

   public boolean isSupported(TemporalUnit unit) {
      if (unit == TemporalFields.HALF_YEARS) {
         return true;
      } else if (unit instanceof ChronoUnit) {
         return unit == ChronoUnit.YEARS || unit == ChronoUnit.DECADES || unit == ChronoUnit.CENTURIES || unit == ChronoUnit.MILLENNIA || unit == ChronoUnit.ERAS;
      } else {
         return unit != null && unit.isSupportedBy(this);
      }
   }

   public ValueRange range(TemporalField field) {
      if (field == TemporalFields.HALF_OF_YEAR) {
         return TemporalFields.HALF_OF_YEAR.range();
      } else if (field == ChronoField.YEAR_OF_ERA) {
         return this.getYear() <= 0 ? ValueRange.of(1L, 1000000000L) : ValueRange.of(1L, 999999999L);
      } else {
         return super.range(field);
      }
   }

   public int get(TemporalField field) {
      if (field == TemporalFields.HALF_OF_YEAR) {
         return this.half.getValue();
      } else if (field instanceof ChronoField) {
         switch ((ChronoField)field) {
            case YEAR_OF_ERA:
               return this.year < 1 ? 1 - this.year : this.year;
            case YEAR:
               return this.year;
            case ERA:
               return this.year < 1 ? 0 : 1;
            default:
               throw new UnsupportedTemporalTypeException("Unsupported field: " + field);
         }
      } else {
         return super.get(field);
      }
   }

   public long getLong(TemporalField field) {
      if (field == TemporalFields.HALF_OF_YEAR) {
         return (long)this.half.getValue();
      } else if (field instanceof ChronoField) {
         switch ((ChronoField)field) {
            case YEAR_OF_ERA:
               return (long)(this.year < 1 ? 1 - this.year : this.year);
            case YEAR:
               return (long)this.year;
            case ERA:
               return (long)(this.year < 1 ? 0 : 1);
            default:
               throw new UnsupportedTemporalTypeException("Unsupported field: " + field);
         }
      } else {
         return field.getFrom(this);
      }
   }

   private long getProlepticHalf() {
      return (long)this.year * 2L + (long)(this.half.getValue() - 1);
   }

   public int getYear() {
      return this.year;
   }

   public int getHalfValue() {
      return this.half.getValue();
   }

   public Half getHalf() {
      return this.half;
   }

   public boolean isLeapYear() {
      return IsoChronology.INSTANCE.isLeapYear((long)this.year);
   }

   public boolean isValidDay(int dayOfHalf) {
      return dayOfHalf >= 1 && dayOfHalf <= this.lengthOfHalf();
   }

   public int lengthOfHalf() {
      return this.half.length(this.isLeapYear());
   }

   public int lengthOfYear() {
      return this.isLeapYear() ? 366 : 365;
   }

   public YearHalf with(TemporalAdjuster adjuster) {
      return (YearHalf)adjuster.adjustInto(this);
   }

   public YearHalf with(TemporalField field, long newValue) {
      if (field == TemporalFields.HALF_OF_YEAR) {
         return this.withHalf(TemporalFields.HALF_OF_YEAR.range().checkValidIntValue(newValue, TemporalFields.HALF_OF_YEAR));
      } else if (field instanceof ChronoField) {
         ChronoField f = (ChronoField)field;
         f.checkValidValue(newValue);
         switch (f) {
            case YEAR_OF_ERA:
               return this.withYear((int)(this.year < 1 ? 1L - newValue : newValue));
            case YEAR:
               return this.withYear((int)newValue);
            case ERA:
               return this.getLong(ChronoField.ERA) == newValue ? this : this.withYear(1 - this.year);
            default:
               throw new UnsupportedTemporalTypeException("Unsupported field: " + field);
         }
      } else {
         return (YearHalf)field.adjustInto(this, newValue);
      }
   }

   public YearHalf withYear(int year) {
      ChronoField.YEAR.checkValidValue((long)year);
      return this.with(year, this.half);
   }

   public YearHalf withHalf(int half) {
      TemporalFields.HALF_OF_YEAR.range().checkValidValue((long)half, TemporalFields.HALF_OF_YEAR);
      return this.with(this.year, Half.of(half));
   }

   public YearHalf plus(TemporalAmount amountToAdd) {
      return (YearHalf)amountToAdd.addTo(this);
   }

   public YearHalf plus(long amountToAdd, TemporalUnit unit) {
      if (unit == TemporalFields.HALF_YEARS) {
         return this.plusHalves(amountToAdd);
      } else if (unit instanceof ChronoUnit) {
         switch ((ChronoUnit)unit) {
            case YEARS:
               return this.plusYears(amountToAdd);
            case DECADES:
               return this.plusYears(Math.multiplyExact(amountToAdd, 10L));
            case CENTURIES:
               return this.plusYears(Math.multiplyExact(amountToAdd, 100L));
            case MILLENNIA:
               return this.plusYears(Math.multiplyExact(amountToAdd, 1000L));
            case ERAS:
               return this.with(ChronoField.ERA, Math.addExact(this.getLong(ChronoField.ERA), amountToAdd));
            default:
               throw new UnsupportedTemporalTypeException("Unsupported unit: " + unit);
         }
      } else {
         return (YearHalf)unit.addTo(this, amountToAdd);
      }
   }

   public YearHalf plusYears(long yearsToAdd) {
      if (yearsToAdd == 0L) {
         return this;
      } else {
         int newYear = ChronoField.YEAR.checkValidIntValue((long)this.year + yearsToAdd);
         return this.with(newYear, this.half);
      }
   }

   public YearHalf plusHalves(long halvesToAdd) {
      if (halvesToAdd == 0L) {
         return this;
      } else {
         long halfCount = (long)this.year * 2L + (long)(this.half.getValue() - 1);
         long calcHalves = halfCount + halvesToAdd;
         int newYear = ChronoField.YEAR.checkValidIntValue(Math.floorDiv(calcHalves, 2L));
         int newHalf = (int)Math.floorMod(calcHalves, 2L) + 1;
         return this.with(newYear, Half.of(newHalf));
      }
   }

   public YearHalf minus(TemporalAmount amountToSubtract) {
      return (YearHalf)amountToSubtract.subtractFrom(this);
   }

   public YearHalf minus(long amountToSubtract, TemporalUnit unit) {
      return amountToSubtract == Long.MIN_VALUE ? this.plus(Long.MAX_VALUE, unit).plus(1L, unit) : this.plus(-amountToSubtract, unit);
   }

   public YearHalf minusYears(long yearsToSubtract) {
      return yearsToSubtract == Long.MIN_VALUE ? this.plusYears(Long.MAX_VALUE).plusYears(1L) : this.plusYears(-yearsToSubtract);
   }

   public YearHalf minusHalves(long halvesToSubtract) {
      return halvesToSubtract == Long.MIN_VALUE ? this.plusHalves(Long.MAX_VALUE).plusHalves(1L) : this.plusHalves(-halvesToSubtract);
   }

   public Object query(TemporalQuery query) {
      if (query == TemporalQueries.chronology()) {
         return IsoChronology.INSTANCE;
      } else {
         return query == TemporalQueries.precision() ? TemporalFields.HALF_YEARS : super.query(query);
      }
   }

   public Temporal adjustInto(Temporal temporal) {
      if (!Chronology.from(temporal).equals(IsoChronology.INSTANCE)) {
         throw new DateTimeException("Adjustment only supported on ISO date-time");
      } else {
         long newProlepticHalf = this.getProlepticHalf();
         long oldProlepticHalf = (long)temporal.get(ChronoField.YEAR) * 2L + (long)(temporal.get(TemporalFields.HALF_OF_YEAR) - 1);
         return temporal.plus(newProlepticHalf - oldProlepticHalf, TemporalFields.HALF_YEARS);
      }
   }

   public long until(Temporal endExclusive, TemporalUnit unit) {
      YearHalf end = from(endExclusive);
      long halvesUntil = end.getProlepticHalf() - this.getProlepticHalf();
      if (unit == TemporalFields.HALF_YEARS) {
         return halvesUntil;
      } else if (unit instanceof ChronoUnit) {
         switch ((ChronoUnit)unit) {
            case YEARS:
               return halvesUntil / 2L;
            case DECADES:
               return halvesUntil / 20L;
            case CENTURIES:
               return halvesUntil / 200L;
            case MILLENNIA:
               return halvesUntil / 2000L;
            case ERAS:
               return end.getLong(ChronoField.ERA) - this.getLong(ChronoField.ERA);
            default:
               throw new UnsupportedTemporalTypeException("Unsupported unit: " + unit);
         }
      } else {
         return unit.between(this, end);
      }
   }

   public Stream halvesUntil(YearHalf endExclusive) {
      if (endExclusive.isBefore(this)) {
         throw new IllegalArgumentException(endExclusive + " < " + this);
      } else {
         long intervalLength = this.until(endExclusive, TemporalFields.HALF_YEARS);
         return LongStream.range(0L, intervalLength).mapToObj((n) -> this.plusHalves(n));
      }
   }

   public String format(DateTimeFormatter formatter) {
      Objects.requireNonNull(formatter, "formatter");
      return formatter.format(this);
   }

   public LocalDate atDay(int dayOfHalf) {
      ValueRange.of(1L, (long)this.lengthOfHalf()).checkValidValue((long)dayOfHalf, TemporalFields.DAY_OF_HALF);
      boolean leap = Year.isLeap((long)this.year);
      Month month = this.half.firstMonth();

      int dom;
      for(dom = dayOfHalf; dom > month.length(leap); month = month.plus(1L)) {
         dom -= month.length(leap);
      }

      return LocalDate.of(this.year, month, dom);
   }

   public LocalDate atEndOfHalf() {
      Month month = this.half.firstMonth().plus(5L);
      return LocalDate.of(this.year, month, month.maxLength());
   }

   public int compareTo(YearHalf other) {
      int cmp = this.year - other.year;
      if (cmp == 0) {
         cmp = this.half.compareTo(other.half);
      }

      return cmp;
   }

   public boolean isAfter(YearHalf other) {
      return this.compareTo(other) > 0;
   }

   public boolean isBefore(YearHalf other) {
      return this.compareTo(other) < 0;
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (!(obj instanceof YearHalf)) {
         return false;
      } else {
         YearHalf other = (YearHalf)obj;
         return this.year == other.year && this.half == other.half;
      }
   }

   public int hashCode() {
      return this.year ^ this.half.getValue() << 28;
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

      return buf.append('-').append(this.half).toString();
   }

   static {
      PARSER = (new DateTimeFormatterBuilder()).parseCaseInsensitive().appendValue(ChronoField.YEAR, 4, 10, SignStyle.EXCEEDS_PAD).appendLiteral('-').appendLiteral('H').appendValue(TemporalFields.HALF_OF_YEAR, 1).toFormatter();
   }
}
