package org.threeten.extra;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.Chronology;
import java.time.format.ResolverStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalUnit;
import java.time.temporal.ValueRange;
import java.util.Map;

public final class PackedFields {
   public static final TemporalField PACKED_DATE;
   public static final TemporalField PACKED_HOUR_MIN;
   public static final TemporalField PACKED_TIME;

   private PackedFields() {
   }

   private static void updateCheckConflict(Map fieldValues, TemporalField targetField, TemporalField changeField, long changeValue) {
      Long old = (Long)fieldValues.put(changeField, changeValue);
      if (old != null && changeValue != old) {
         throw new DateTimeException("Conflict found: " + changeField + " " + old + " differs from " + changeField + " " + changeValue + " while resolving  " + targetField);
      }
   }

   static {
      PACKED_DATE = PackedFields.PackedDate.INSTANCE;
      PACKED_HOUR_MIN = PackedFields.PackedHourMin.INSTANCE;
      PACKED_TIME = PackedFields.PackedTime.INSTANCE;
   }

   private static enum PackedDate implements TemporalField {
      INSTANCE;

      private static final ValueRange RANGE = ValueRange.of(10000101L, 99991231L);
      private static final long serialVersionUID = -38752465672576L;

      public TemporalUnit getBaseUnit() {
         return ChronoUnit.DAYS;
      }

      public TemporalUnit getRangeUnit() {
         return ChronoUnit.FOREVER;
      }

      public boolean isDateBased() {
         return true;
      }

      public boolean isTimeBased() {
         return false;
      }

      public ValueRange range() {
         return RANGE;
      }

      public boolean isSupportedBy(TemporalAccessor temporal) {
         return temporal.isSupported(ChronoField.EPOCH_DAY);
      }

      public ValueRange rangeRefinedBy(TemporalAccessor temporal) {
         if (!temporal.isSupported(this)) {
            throw new DateTimeException("Unsupported field: " + this);
         } else {
            return this.range();
         }
      }

      public long getFrom(TemporalAccessor temporal) {
         LocalDate date = LocalDate.ofEpochDay(temporal.getLong(ChronoField.EPOCH_DAY));
         int year = date.getYear();
         if (year >= 1000 && year <= 9999) {
            int moy = date.getMonthValue();
            int dom = date.getDayOfMonth();
            return (long)(year * 10000 + moy * 100 + dom);
         } else {
            throw new DateTimeException("Unable to obtain PackedDate from LocalDate: " + date);
         }
      }

      public Temporal adjustInto(Temporal temporal, long newValue) {
         LocalDate date = this.toDate(newValue);
         return temporal.with(date);
      }

      private LocalDate toDate(long newValue) {
         if (!this.range().isValidValue(newValue)) {
            throw new DateTimeException("Invalid value: PackedDate " + newValue);
         } else {
            int val = (int)newValue;
            int year = val / 10000;
            int moy = val % 10000 / 100;
            int dom = val % 100;
            return LocalDate.of(year, moy, dom);
         }
      }

      public ChronoLocalDate resolve(Map fieldValues, TemporalAccessor partialTemporal, ResolverStyle resolverStyle) {
         long value = (Long)fieldValues.remove(this);
         LocalDate date;
         if (resolverStyle == ResolverStyle.LENIENT) {
            int year = Math.toIntExact(value / 10000L);
            int moy = (int)(value % 10000L / 100L);
            long dom = value % 100L;
            date = LocalDate.of(year, 1, 1).plusMonths((long)(moy - 1)).plusDays(dom - 1L);
         } else {
            date = this.toDate(value);
         }

         Chronology chrono = Chronology.from(partialTemporal);
         return chrono.date(date);
      }

      public String toString() {
         return "PackedDate";
      }
   }

   private static enum PackedHourMin implements TemporalField {
      INSTANCE;

      private static final ValueRange RANGE = ValueRange.of(0L, 2359L);
      private static final long serialVersionUID = -871357658587L;

      public TemporalUnit getBaseUnit() {
         return ChronoUnit.MINUTES;
      }

      public TemporalUnit getRangeUnit() {
         return ChronoUnit.DAYS;
      }

      public boolean isDateBased() {
         return false;
      }

      public boolean isTimeBased() {
         return true;
      }

      public ValueRange range() {
         return RANGE;
      }

      public boolean isSupportedBy(TemporalAccessor temporal) {
         return temporal.isSupported(ChronoField.MINUTE_OF_DAY);
      }

      public ValueRange rangeRefinedBy(TemporalAccessor temporal) {
         if (!temporal.isSupported(this)) {
            throw new DateTimeException("Unsupported field: " + this);
         } else {
            return this.range();
         }
      }

      public long getFrom(TemporalAccessor temporal) {
         int mod = temporal.get(ChronoField.MINUTE_OF_DAY);
         int hour = mod / 60;
         int min = mod % 60;
         return (long)(hour * 100 + min);
      }

      public Temporal adjustInto(Temporal temporal, long newValue) {
         long hour = newValue / 100L;
         long min = newValue % 100L;
         ChronoField.HOUR_OF_DAY.checkValidValue(hour);
         ChronoField.MINUTE_OF_HOUR.checkValidValue(min);
         return temporal.with(ChronoField.HOUR_OF_DAY, hour).with(ChronoField.MINUTE_OF_HOUR, min);
      }

      public ChronoLocalDate resolve(Map fieldValues, TemporalAccessor partialTemporal, ResolverStyle resolverStyle) {
         long value = (Long)fieldValues.remove(this);
         long hour = value / 100L;
         long min = value % 100L;
         if (resolverStyle != ResolverStyle.LENIENT) {
            ChronoField.HOUR_OF_DAY.checkValidValue(hour);
            ChronoField.MINUTE_OF_HOUR.checkValidValue(min);
         }

         long mod = hour * 60L + min;
         PackedFields.updateCheckConflict(fieldValues, this, ChronoField.MINUTE_OF_DAY, mod);
         return null;
      }

      public String toString() {
         return "PackedHourMin";
      }
   }

   private static enum PackedTime implements TemporalField {
      INSTANCE;

      private static final ValueRange RANGE = ValueRange.of(0L, 235959L);
      private static final long serialVersionUID = -98266827687L;

      public TemporalUnit getBaseUnit() {
         return ChronoUnit.SECONDS;
      }

      public TemporalUnit getRangeUnit() {
         return ChronoUnit.DAYS;
      }

      public boolean isDateBased() {
         return false;
      }

      public boolean isTimeBased() {
         return true;
      }

      public ValueRange range() {
         return RANGE;
      }

      public boolean isSupportedBy(TemporalAccessor temporal) {
         return temporal.isSupported(ChronoField.SECOND_OF_DAY);
      }

      public ValueRange rangeRefinedBy(TemporalAccessor temporal) {
         if (!temporal.isSupported(this)) {
            throw new DateTimeException("Unsupported field: " + this);
         } else {
            return this.range();
         }
      }

      public long getFrom(TemporalAccessor temporal) {
         int sod = temporal.get(ChronoField.SECOND_OF_DAY);
         int hour = sod / 3600;
         int min = sod / 60 % 60;
         int sec = sod % 60;
         return (long)(hour * 10000 + min * 100 + sec);
      }

      public Temporal adjustInto(Temporal temporal, long newValue) {
         RANGE.checkValidValue(newValue, INSTANCE);
         long hour = newValue / 10000L;
         long min = newValue % 10000L / 100L;
         long sec = newValue % 100L;
         ChronoField.HOUR_OF_DAY.checkValidValue(hour);
         ChronoField.MINUTE_OF_HOUR.checkValidValue(min);
         ChronoField.SECOND_OF_MINUTE.checkValidValue(sec);
         long sod = 3600L * hour + 60L * min + sec;
         return temporal.with(ChronoField.SECOND_OF_DAY, sod);
      }

      public ChronoLocalDate resolve(Map fieldValues, TemporalAccessor partialTemporal, ResolverStyle resolverStyle) {
         long value = (Long)fieldValues.remove(this);
         long hour = value / 10000L;
         long min = value % 10000L / 100L;
         long sec = value % 100L;
         if (resolverStyle != ResolverStyle.LENIENT) {
            ChronoField.HOUR_OF_DAY.checkValidValue(hour);
            ChronoField.MINUTE_OF_HOUR.checkValidValue(min);
            ChronoField.SECOND_OF_MINUTE.checkValidValue(sec);
         }

         long sod = 3600L * hour + 60L * min + sec;
         PackedFields.updateCheckConflict(fieldValues, this, ChronoField.SECOND_OF_DAY, sod);
         return null;
      }

      public String toString() {
         return "PackedTime";
      }
   }
}
