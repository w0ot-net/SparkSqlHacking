package org.threeten.extra;

import java.time.DateTimeException;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.TextStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalQueries;
import java.time.temporal.TemporalQuery;
import java.time.temporal.UnsupportedTemporalTypeException;
import java.time.temporal.ValueRange;
import java.util.Locale;

public enum AmPm implements TemporalAccessor, TemporalAdjuster {
   AM,
   PM;

   public static AmPm of(int amPmValue) {
      switch (amPmValue) {
         case 0:
            return AM;
         case 1:
            return PM;
         default:
            throw new DateTimeException("Invalid value for AM/PM: " + amPmValue);
      }
   }

   public static AmPm ofHour(int hourOfDay) {
      ChronoField.HOUR_OF_DAY.checkValidValue((long)hourOfDay);
      return hourOfDay < 12 ? AM : PM;
   }

   public static AmPm from(TemporalAccessor temporal) {
      if (temporal instanceof AmPm) {
         return (AmPm)temporal;
      } else {
         try {
            return of(temporal.get(ChronoField.AMPM_OF_DAY));
         } catch (DateTimeException ex) {
            throw new DateTimeException("Unable to obtain AmPm from TemporalAccessor: " + temporal + " of type " + temporal.getClass().getName(), ex);
         }
      }
   }

   public int getValue() {
      return this.ordinal();
   }

   public String getDisplayName(TextStyle style, Locale locale) {
      return (new DateTimeFormatterBuilder()).appendText(ChronoField.AMPM_OF_DAY, style).toFormatter(locale).format(this);
   }

   public boolean isSupported(TemporalField field) {
      if (field instanceof ChronoField) {
         return field == ChronoField.AMPM_OF_DAY;
      } else {
         return field != null && field.isSupportedBy(this);
      }
   }

   public ValueRange range(TemporalField field) {
      if (field == ChronoField.AMPM_OF_DAY) {
         return field.range();
      } else if (field instanceof ChronoField) {
         throw new UnsupportedTemporalTypeException("Unsupported field: " + field);
      } else {
         return field.rangeRefinedBy(this);
      }
   }

   public int get(TemporalField field) {
      return field == ChronoField.AMPM_OF_DAY ? this.getValue() : this.range(field).checkValidIntValue(this.getLong(field), field);
   }

   public long getLong(TemporalField field) {
      if (field == ChronoField.AMPM_OF_DAY) {
         return (long)this.getValue();
      } else if (field instanceof ChronoField) {
         throw new UnsupportedTemporalTypeException("Unsupported field: " + field);
      } else {
         return field.getFrom(this);
      }
   }

   public Object query(TemporalQuery query) {
      return query == TemporalQueries.precision() ? ChronoUnit.HALF_DAYS : super.query(query);
   }

   public Temporal adjustInto(Temporal temporal) {
      return temporal.with(ChronoField.AMPM_OF_DAY, (long)this.getValue());
   }
}
