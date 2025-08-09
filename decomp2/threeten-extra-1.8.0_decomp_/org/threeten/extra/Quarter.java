package org.threeten.extra;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Month;
import java.time.chrono.Chronology;
import java.time.chrono.IsoChronology;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.TextStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.IsoFields;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalQueries;
import java.time.temporal.TemporalQuery;
import java.time.temporal.UnsupportedTemporalTypeException;
import java.time.temporal.ValueRange;
import java.util.Locale;

public enum Quarter implements TemporalAccessor, TemporalAdjuster {
   Q1,
   Q2,
   Q3,
   Q4;

   public static Quarter of(int quarterOfYear) {
      switch (quarterOfYear) {
         case 1:
            return Q1;
         case 2:
            return Q2;
         case 3:
            return Q3;
         case 4:
            return Q4;
         default:
            throw new DateTimeException("Invalid value for Quarter: " + quarterOfYear);
      }
   }

   public static Quarter ofMonth(int monthOfYear) {
      ChronoField.MONTH_OF_YEAR.range().checkValidValue((long)monthOfYear, ChronoField.MONTH_OF_YEAR);
      return of((monthOfYear - 1) / 3 + 1);
   }

   public static Quarter from(TemporalAccessor temporal) {
      if (temporal instanceof Quarter) {
         return (Quarter)temporal;
      } else if (temporal instanceof Month) {
         Month month = (Month)temporal;
         return of(month.ordinal() / 3 + 1);
      } else {
         try {
            TemporalAccessor adjusted = (TemporalAccessor)(!IsoChronology.INSTANCE.equals(Chronology.from(temporal)) ? LocalDate.from(temporal) : temporal);
            int qoy = Math.toIntExact(adjusted.getLong(IsoFields.QUARTER_OF_YEAR));
            return of(qoy);
         } catch (DateTimeException ex) {
            throw new DateTimeException("Unable to obtain Quarter from TemporalAccessor: " + temporal + " of type " + temporal.getClass().getName(), ex);
         }
      }
   }

   public int getValue() {
      return this.ordinal() + 1;
   }

   public String getDisplayName(TextStyle style, Locale locale) {
      return (new DateTimeFormatterBuilder()).appendText(IsoFields.QUARTER_OF_YEAR, style).toFormatter(locale).format(this);
   }

   public boolean isSupported(TemporalField field) {
      if (field == IsoFields.QUARTER_OF_YEAR) {
         return true;
      } else if (field instanceof ChronoField) {
         return false;
      } else {
         return field != null && field.isSupportedBy(this);
      }
   }

   public ValueRange range(TemporalField field) {
      if (field == IsoFields.QUARTER_OF_YEAR) {
         return field.range();
      } else if (field instanceof ChronoField) {
         throw new UnsupportedTemporalTypeException("Unsupported field: " + field);
      } else {
         return super.range(field);
      }
   }

   public int get(TemporalField field) {
      if (field == IsoFields.QUARTER_OF_YEAR) {
         return this.getValue();
      } else if (field instanceof ChronoField) {
         throw new UnsupportedTemporalTypeException("Unsupported field: " + field);
      } else {
         return super.get(field);
      }
   }

   public long getLong(TemporalField field) {
      if (field == IsoFields.QUARTER_OF_YEAR) {
         return (long)this.getValue();
      } else if (field instanceof ChronoField) {
         throw new UnsupportedTemporalTypeException("Unsupported field: " + field);
      } else {
         return field.getFrom(this);
      }
   }

   public Quarter plus(long quarters) {
      int amount = (int)quarters % 4;
      return values()[(this.ordinal() + amount + 4) % 4];
   }

   public Quarter minus(long quarters) {
      return this.plus(-(quarters % 4L));
   }

   public int length(boolean leapYear) {
      switch (this) {
         case Q1:
            return leapYear ? 91 : 90;
         case Q2:
            return 91;
         default:
            return 92;
      }
   }

   public Month firstMonth() {
      switch (this) {
         case Q1:
            return Month.JANUARY;
         case Q2:
            return Month.APRIL;
         case Q3:
            return Month.JULY;
         case Q4:
            return Month.OCTOBER;
         default:
            throw new IllegalStateException("Unreachable");
      }
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
         return temporal.with(IsoFields.QUARTER_OF_YEAR, (long)this.getValue());
      }
   }
}
