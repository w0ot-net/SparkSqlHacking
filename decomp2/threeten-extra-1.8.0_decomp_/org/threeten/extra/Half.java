package org.threeten.extra;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Month;
import java.time.chrono.Chronology;
import java.time.chrono.IsoChronology;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.TextStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalQueries;
import java.time.temporal.TemporalQuery;
import java.time.temporal.UnsupportedTemporalTypeException;
import java.time.temporal.ValueRange;
import java.util.Locale;

public enum Half implements TemporalAccessor, TemporalAdjuster {
   H1,
   H2;

   public static Half of(int halfOfYear) {
      switch (halfOfYear) {
         case 1:
            return H1;
         case 2:
            return H2;
         default:
            throw new DateTimeException("Invalid value for Half: " + halfOfYear);
      }
   }

   public static Half ofMonth(int monthOfYear) {
      ChronoField.MONTH_OF_YEAR.range().checkValidValue((long)monthOfYear, ChronoField.MONTH_OF_YEAR);
      return of(monthOfYear <= 6 ? 1 : 2);
   }

   public static Half from(TemporalAccessor temporal) {
      if (temporal instanceof Half) {
         return (Half)temporal;
      } else if (temporal instanceof Month) {
         Month month = (Month)temporal;
         return of(month.ordinal() / 6 + 1);
      } else {
         try {
            TemporalAccessor adjusted = (TemporalAccessor)(!IsoChronology.INSTANCE.equals(Chronology.from(temporal)) ? LocalDate.from(temporal) : temporal);
            int qoy = Math.toIntExact(adjusted.getLong(TemporalFields.HALF_OF_YEAR));
            return of(qoy);
         } catch (DateTimeException ex) {
            throw new DateTimeException("Unable to obtain Half from TemporalAccessor: " + temporal + " of type " + temporal.getClass().getName(), ex);
         }
      }
   }

   public int getValue() {
      return this.ordinal() + 1;
   }

   public String getDisplayName(TextStyle style, Locale locale) {
      return (new DateTimeFormatterBuilder()).appendText(TemporalFields.HALF_OF_YEAR, style).toFormatter(locale).format(this);
   }

   public boolean isSupported(TemporalField field) {
      if (field == TemporalFields.HALF_OF_YEAR) {
         return true;
      } else if (field instanceof ChronoField) {
         return false;
      } else {
         return field != null && field.isSupportedBy(this);
      }
   }

   public ValueRange range(TemporalField field) {
      if (field == TemporalFields.HALF_OF_YEAR) {
         return field.range();
      } else if (field instanceof ChronoField) {
         throw new UnsupportedTemporalTypeException("Unsupported field: " + field);
      } else {
         return super.range(field);
      }
   }

   public int get(TemporalField field) {
      if (field == TemporalFields.HALF_OF_YEAR) {
         return this.getValue();
      } else if (field instanceof ChronoField) {
         throw new UnsupportedTemporalTypeException("Unsupported field: " + field);
      } else {
         return super.get(field);
      }
   }

   public long getLong(TemporalField field) {
      if (field == TemporalFields.HALF_OF_YEAR) {
         return (long)this.getValue();
      } else if (field instanceof ChronoField) {
         throw new UnsupportedTemporalTypeException("Unsupported field: " + field);
      } else {
         return field.getFrom(this);
      }
   }

   public Half plus(long halves) {
      int amount = (int)halves % 2;
      return values()[(this.ordinal() + amount + 2) % 2];
   }

   public Half minus(long halves) {
      return this.plus(-(halves % 2L));
   }

   public int length(boolean leapYear) {
      return this == H1 ? (leapYear ? 182 : 181) : 184;
   }

   public Month firstMonth() {
      return this == H1 ? Month.JANUARY : Month.JULY;
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
         return temporal.with(TemporalFields.HALF_OF_YEAR, (long)this.getValue());
      }
   }
}
