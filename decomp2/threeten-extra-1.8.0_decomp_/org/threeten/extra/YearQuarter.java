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
import java.util.stream.LongStream;
import java.util.stream.Stream;
import org.joda.convert.FromString;
import org.joda.convert.ToString;

public final class YearQuarter implements Temporal, TemporalAdjuster, Comparable, Serializable {
   private static final long serialVersionUID = 4183400860270640070L;
   private static final DateTimeFormatter PARSER;
   private final int year;
   private final Quarter quarter;

   public static YearQuarter now() {
      return now(Clock.systemDefaultZone());
   }

   public static YearQuarter now(ZoneId zone) {
      return now(Clock.system(zone));
   }

   public static YearQuarter now(Clock clock) {
      LocalDate now = LocalDate.now(clock);
      return of(now.getYear(), Quarter.from(now.getMonth()));
   }

   public static YearQuarter of(Year year, Quarter quarter) {
      return of(year.getValue(), quarter);
   }

   public static YearQuarter of(Year year, int quarter) {
      return of(year.getValue(), Quarter.of(quarter));
   }

   public static YearQuarter of(int year, Quarter quarter) {
      ChronoField.YEAR.checkValidValue((long)year);
      Objects.requireNonNull(quarter, "quarter");
      return new YearQuarter(year, quarter);
   }

   public static YearQuarter of(int year, int quarter) {
      ChronoField.YEAR.checkValidValue((long)year);
      return new YearQuarter(year, Quarter.of(quarter));
   }

   public static YearQuarter from(TemporalAccessor temporal) {
      if (temporal instanceof YearQuarter) {
         return (YearQuarter)temporal;
      } else {
         Objects.requireNonNull(temporal, "temporal");

         try {
            TemporalAccessor adjusted = (TemporalAccessor)(!IsoChronology.INSTANCE.equals(Chronology.from(temporal)) ? LocalDate.from(temporal) : temporal);
            int year = Math.toIntExact(adjusted.getLong(ChronoField.YEAR));
            int qoy = Math.toIntExact(adjusted.getLong(IsoFields.QUARTER_OF_YEAR));
            return of(year, qoy);
         } catch (DateTimeException ex) {
            throw new DateTimeException("Unable to obtain YearQuarter from TemporalAccessor: " + temporal + " of type " + temporal.getClass().getName(), ex);
         }
      }
   }

   @FromString
   public static YearQuarter parse(CharSequence text) {
      return parse(text, PARSER);
   }

   public static YearQuarter parse(CharSequence text, DateTimeFormatter formatter) {
      Objects.requireNonNull(formatter, "formatter");
      return (YearQuarter)formatter.parse(text, YearQuarter::from);
   }

   private YearQuarter(int year, Quarter quarter) {
      this.year = year;
      this.quarter = quarter;
   }

   private Object readResolve() {
      return of(this.year, this.quarter);
   }

   private YearQuarter with(int newYear, Quarter newQuarter) {
      return this.year == newYear && this.quarter == newQuarter ? this : new YearQuarter(newYear, newQuarter);
   }

   public boolean isSupported(TemporalField field) {
      if (field == IsoFields.QUARTER_OF_YEAR) {
         return true;
      } else if (field instanceof ChronoField) {
         return field == ChronoField.YEAR || field == ChronoField.YEAR_OF_ERA || field == ChronoField.ERA;
      } else {
         return field != null && field.isSupportedBy(this);
      }
   }

   public boolean isSupported(TemporalUnit unit) {
      if (unit == IsoFields.QUARTER_YEARS) {
         return true;
      } else if (unit instanceof ChronoUnit) {
         return unit == ChronoUnit.YEARS || unit == ChronoUnit.DECADES || unit == ChronoUnit.CENTURIES || unit == ChronoUnit.MILLENNIA || unit == ChronoUnit.ERAS;
      } else {
         return unit != null && unit.isSupportedBy(this);
      }
   }

   public ValueRange range(TemporalField field) {
      if (field == IsoFields.QUARTER_OF_YEAR) {
         return IsoFields.QUARTER_OF_YEAR.range();
      } else if (field == ChronoField.YEAR_OF_ERA) {
         return this.getYear() <= 0 ? ValueRange.of(1L, 1000000000L) : ValueRange.of(1L, 999999999L);
      } else {
         return super.range(field);
      }
   }

   public int get(TemporalField field) {
      if (field == IsoFields.QUARTER_OF_YEAR) {
         return this.quarter.getValue();
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
      if (field == IsoFields.QUARTER_OF_YEAR) {
         return (long)this.quarter.getValue();
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

   private long getProlepticQuarter() {
      return (long)this.year * 4L + (long)(this.quarter.getValue() - 1);
   }

   public int getYear() {
      return this.year;
   }

   public int getQuarterValue() {
      return this.quarter.getValue();
   }

   public Quarter getQuarter() {
      return this.quarter;
   }

   public boolean isLeapYear() {
      return IsoChronology.INSTANCE.isLeapYear((long)this.year);
   }

   public boolean isValidDay(int dayOfQuarter) {
      return dayOfQuarter >= 1 && dayOfQuarter <= this.lengthOfQuarter();
   }

   public int lengthOfQuarter() {
      return this.quarter.length(this.isLeapYear());
   }

   public int lengthOfYear() {
      return this.isLeapYear() ? 366 : 365;
   }

   public YearQuarter with(TemporalAdjuster adjuster) {
      return (YearQuarter)adjuster.adjustInto(this);
   }

   public YearQuarter with(TemporalField field, long newValue) {
      if (field == IsoFields.QUARTER_OF_YEAR) {
         return this.withQuarter(IsoFields.QUARTER_OF_YEAR.range().checkValidIntValue(newValue, IsoFields.QUARTER_OF_YEAR));
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
         return (YearQuarter)field.adjustInto(this, newValue);
      }
   }

   public YearQuarter withYear(int year) {
      ChronoField.YEAR.checkValidValue((long)year);
      return this.with(year, this.quarter);
   }

   public YearQuarter withQuarter(int quarter) {
      IsoFields.QUARTER_OF_YEAR.range().checkValidValue((long)quarter, IsoFields.QUARTER_OF_YEAR);
      return this.with(this.year, Quarter.of(quarter));
   }

   public YearQuarter plus(TemporalAmount amountToAdd) {
      return (YearQuarter)amountToAdd.addTo(this);
   }

   public YearQuarter plus(long amountToAdd, TemporalUnit unit) {
      if (unit == IsoFields.QUARTER_YEARS) {
         return this.plusQuarters(amountToAdd);
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
         return (YearQuarter)unit.addTo(this, amountToAdd);
      }
   }

   public YearQuarter plusYears(long yearsToAdd) {
      if (yearsToAdd == 0L) {
         return this;
      } else {
         int newYear = ChronoField.YEAR.checkValidIntValue((long)this.year + yearsToAdd);
         return this.with(newYear, this.quarter);
      }
   }

   public YearQuarter plusQuarters(long quartersToAdd) {
      if (quartersToAdd == 0L) {
         return this;
      } else {
         long quarterCount = (long)this.year * 4L + (long)(this.quarter.getValue() - 1);
         long calcQuarters = quarterCount + quartersToAdd;
         int newYear = ChronoField.YEAR.checkValidIntValue(Math.floorDiv(calcQuarters, 4L));
         int newQuarter = (int)Math.floorMod(calcQuarters, 4L) + 1;
         return this.with(newYear, Quarter.of(newQuarter));
      }
   }

   public YearQuarter minus(TemporalAmount amountToSubtract) {
      return (YearQuarter)amountToSubtract.subtractFrom(this);
   }

   public YearQuarter minus(long amountToSubtract, TemporalUnit unit) {
      return amountToSubtract == Long.MIN_VALUE ? this.plus(Long.MAX_VALUE, unit).plus(1L, unit) : this.plus(-amountToSubtract, unit);
   }

   public YearQuarter minusYears(long yearsToSubtract) {
      return yearsToSubtract == Long.MIN_VALUE ? this.plusYears(Long.MAX_VALUE).plusYears(1L) : this.plusYears(-yearsToSubtract);
   }

   public YearQuarter minusQuarters(long quartersToSubtract) {
      return quartersToSubtract == Long.MIN_VALUE ? this.plusQuarters(Long.MAX_VALUE).plusQuarters(1L) : this.plusQuarters(-quartersToSubtract);
   }

   public Object query(TemporalQuery query) {
      if (query == TemporalQueries.chronology()) {
         return IsoChronology.INSTANCE;
      } else {
         return query == TemporalQueries.precision() ? IsoFields.QUARTER_YEARS : super.query(query);
      }
   }

   public Temporal adjustInto(Temporal temporal) {
      if (!Chronology.from(temporal).equals(IsoChronology.INSTANCE)) {
         throw new DateTimeException("Adjustment only supported on ISO date-time");
      } else {
         long newProlepticQuarter = this.getProlepticQuarter();
         long oldProlepticQuarter = (long)temporal.get(ChronoField.YEAR) * 4L + (long)(temporal.get(IsoFields.QUARTER_OF_YEAR) - 1);
         return temporal.plus(newProlepticQuarter - oldProlepticQuarter, IsoFields.QUARTER_YEARS);
      }
   }

   public long until(Temporal endExclusive, TemporalUnit unit) {
      YearQuarter end = from(endExclusive);
      long quartersUntil = end.getProlepticQuarter() - this.getProlepticQuarter();
      if (unit == IsoFields.QUARTER_YEARS) {
         return quartersUntil;
      } else if (unit instanceof ChronoUnit) {
         switch ((ChronoUnit)unit) {
            case YEARS:
               return quartersUntil / 4L;
            case DECADES:
               return quartersUntil / 40L;
            case CENTURIES:
               return quartersUntil / 400L;
            case MILLENNIA:
               return quartersUntil / 4000L;
            case ERAS:
               return end.getLong(ChronoField.ERA) - this.getLong(ChronoField.ERA);
            default:
               throw new UnsupportedTemporalTypeException("Unsupported unit: " + unit);
         }
      } else {
         return unit.between(this, end);
      }
   }

   public Stream quartersUntil(YearQuarter endExclusive) {
      if (endExclusive.isBefore(this)) {
         throw new IllegalArgumentException(endExclusive + " < " + this);
      } else {
         long intervalLength = this.until(endExclusive, IsoFields.QUARTER_YEARS);
         return LongStream.range(0L, intervalLength).mapToObj((n) -> this.plusQuarters(n));
      }
   }

   public String format(DateTimeFormatter formatter) {
      Objects.requireNonNull(formatter, "formatter");
      return formatter.format(this);
   }

   public LocalDate atDay(int dayOfQuarter) {
      ValueRange.of(1L, (long)this.lengthOfQuarter()).checkValidValue((long)dayOfQuarter, IsoFields.DAY_OF_QUARTER);
      boolean leap = Year.isLeap((long)this.year);
      Month month = this.quarter.firstMonth();

      int dom;
      for(dom = dayOfQuarter; dom > month.length(leap); month = month.plus(1L)) {
         dom -= month.length(leap);
      }

      return LocalDate.of(this.year, month, dom);
   }

   public LocalDate atEndOfQuarter() {
      Month month = this.quarter.firstMonth().plus(2L);
      return LocalDate.of(this.year, month, month.maxLength());
   }

   public int compareTo(YearQuarter other) {
      int cmp = this.year - other.year;
      if (cmp == 0) {
         cmp = this.quarter.compareTo(other.quarter);
      }

      return cmp;
   }

   public boolean isAfter(YearQuarter other) {
      return this.compareTo(other) > 0;
   }

   public boolean isBefore(YearQuarter other) {
      return this.compareTo(other) < 0;
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (!(obj instanceof YearQuarter)) {
         return false;
      } else {
         YearQuarter other = (YearQuarter)obj;
         return this.year == other.year && this.quarter == other.quarter;
      }
   }

   public int hashCode() {
      return this.year ^ this.quarter.getValue() << 27;
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

      return buf.append('-').append(this.quarter).toString();
   }

   static {
      PARSER = (new DateTimeFormatterBuilder()).parseCaseInsensitive().appendValue(ChronoField.YEAR, 4, 10, SignStyle.EXCEEDS_PAD).appendLiteral('-').appendLiteral('Q').appendValue(IsoFields.QUARTER_OF_YEAR, 1).toFormatter();
   }
}
