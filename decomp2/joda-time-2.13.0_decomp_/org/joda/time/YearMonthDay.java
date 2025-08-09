package org.joda.time;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import org.joda.time.base.BasePartial;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.field.AbstractPartialFieldProperty;
import org.joda.time.field.FieldUtils;
import org.joda.time.format.ISODateTimeFormat;

/** @deprecated */
@Deprecated
public final class YearMonthDay extends BasePartial implements ReadablePartial, Serializable {
   private static final long serialVersionUID = 797544782896179L;
   private static final DateTimeFieldType[] FIELD_TYPES = new DateTimeFieldType[]{DateTimeFieldType.year(), DateTimeFieldType.monthOfYear(), DateTimeFieldType.dayOfMonth()};
   public static final int YEAR = 0;
   public static final int MONTH_OF_YEAR = 1;
   public static final int DAY_OF_MONTH = 2;

   public static YearMonthDay fromCalendarFields(Calendar var0) {
      if (var0 == null) {
         throw new IllegalArgumentException("The calendar must not be null");
      } else {
         return new YearMonthDay(var0.get(1), var0.get(2) + 1, var0.get(5));
      }
   }

   public static YearMonthDay fromDateFields(Date var0) {
      if (var0 == null) {
         throw new IllegalArgumentException("The date must not be null");
      } else {
         return new YearMonthDay(var0.getYear() + 1900, var0.getMonth() + 1, var0.getDate());
      }
   }

   public YearMonthDay() {
   }

   public YearMonthDay(DateTimeZone var1) {
      super(ISOChronology.getInstance(var1));
   }

   public YearMonthDay(Chronology var1) {
      super(var1);
   }

   public YearMonthDay(long var1) {
      super(var1);
   }

   public YearMonthDay(long var1, Chronology var3) {
      super(var1, var3);
   }

   public YearMonthDay(Object var1) {
      super(var1, (Chronology)null, ISODateTimeFormat.dateOptionalTimeParser());
   }

   public YearMonthDay(Object var1, Chronology var2) {
      super(var1, DateTimeUtils.getChronology(var2), ISODateTimeFormat.dateOptionalTimeParser());
   }

   public YearMonthDay(int var1, int var2, int var3) {
      this(var1, var2, var3, (Chronology)null);
   }

   public YearMonthDay(int var1, int var2, int var3, Chronology var4) {
      super(new int[]{var1, var2, var3}, var4);
   }

   YearMonthDay(YearMonthDay var1, int[] var2) {
      super((BasePartial)var1, (int[])var2);
   }

   YearMonthDay(YearMonthDay var1, Chronology var2) {
      super((BasePartial)var1, (Chronology)var2);
   }

   public int size() {
      return 3;
   }

   protected DateTimeField getField(int var1, Chronology var2) {
      switch (var1) {
         case 0:
            return var2.year();
         case 1:
            return var2.monthOfYear();
         case 2:
            return var2.dayOfMonth();
         default:
            throw new IndexOutOfBoundsException("Invalid index: " + var1);
      }
   }

   public DateTimeFieldType getFieldType(int var1) {
      return FIELD_TYPES[var1];
   }

   public DateTimeFieldType[] getFieldTypes() {
      return (DateTimeFieldType[])FIELD_TYPES.clone();
   }

   public YearMonthDay withChronologyRetainFields(Chronology var1) {
      var1 = DateTimeUtils.getChronology(var1);
      var1 = var1.withUTC();
      if (var1 == this.getChronology()) {
         return this;
      } else {
         YearMonthDay var2 = new YearMonthDay(this, var1);
         var1.validate(var2, this.getValues());
         return var2;
      }
   }

   public YearMonthDay withField(DateTimeFieldType var1, int var2) {
      int var3 = this.indexOfSupported(var1);
      if (var2 == this.getValue(var3)) {
         return this;
      } else {
         int[] var4 = this.getValues();
         var4 = this.getField(var3).set(this, var3, var4, var2);
         return new YearMonthDay(this, var4);
      }
   }

   public YearMonthDay withFieldAdded(DurationFieldType var1, int var2) {
      int var3 = this.indexOfSupported(var1);
      if (var2 == 0) {
         return this;
      } else {
         int[] var4 = this.getValues();
         var4 = this.getField(var3).add(this, var3, var4, var2);
         return new YearMonthDay(this, var4);
      }
   }

   public YearMonthDay withPeriodAdded(ReadablePeriod var1, int var2) {
      if (var1 != null && var2 != 0) {
         int[] var3 = this.getValues();

         for(int var4 = 0; var4 < var1.size(); ++var4) {
            DurationFieldType var5 = var1.getFieldType(var4);
            int var6 = this.indexOf(var5);
            if (var6 >= 0) {
               var3 = this.getField(var6).add(this, var6, var3, FieldUtils.safeMultiply(var1.getValue(var4), var2));
            }
         }

         return new YearMonthDay(this, var3);
      } else {
         return this;
      }
   }

   public YearMonthDay plus(ReadablePeriod var1) {
      return this.withPeriodAdded(var1, 1);
   }

   public YearMonthDay plusYears(int var1) {
      return this.withFieldAdded(DurationFieldType.years(), var1);
   }

   public YearMonthDay plusMonths(int var1) {
      return this.withFieldAdded(DurationFieldType.months(), var1);
   }

   public YearMonthDay plusDays(int var1) {
      return this.withFieldAdded(DurationFieldType.days(), var1);
   }

   public YearMonthDay minus(ReadablePeriod var1) {
      return this.withPeriodAdded(var1, -1);
   }

   public YearMonthDay minusYears(int var1) {
      return this.withFieldAdded(DurationFieldType.years(), FieldUtils.safeNegate(var1));
   }

   public YearMonthDay minusMonths(int var1) {
      return this.withFieldAdded(DurationFieldType.months(), FieldUtils.safeNegate(var1));
   }

   public YearMonthDay minusDays(int var1) {
      return this.withFieldAdded(DurationFieldType.days(), FieldUtils.safeNegate(var1));
   }

   public Property property(DateTimeFieldType var1) {
      return new Property(this, this.indexOfSupported(var1));
   }

   public LocalDate toLocalDate() {
      return new LocalDate(this.getYear(), this.getMonthOfYear(), this.getDayOfMonth(), this.getChronology());
   }

   public DateTime toDateTimeAtMidnight() {
      return this.toDateTimeAtMidnight((DateTimeZone)null);
   }

   public DateTime toDateTimeAtMidnight(DateTimeZone var1) {
      Chronology var2 = this.getChronology().withZone(var1);
      return new DateTime(this.getYear(), this.getMonthOfYear(), this.getDayOfMonth(), 0, 0, 0, 0, var2);
   }

   public DateTime toDateTimeAtCurrentTime() {
      return this.toDateTimeAtCurrentTime((DateTimeZone)null);
   }

   public DateTime toDateTimeAtCurrentTime(DateTimeZone var1) {
      Chronology var2 = this.getChronology().withZone(var1);
      long var3 = DateTimeUtils.currentTimeMillis();
      long var5 = var2.set(this, var3);
      return new DateTime(var5, var2);
   }

   public DateMidnight toDateMidnight() {
      return this.toDateMidnight((DateTimeZone)null);
   }

   public DateMidnight toDateMidnight(DateTimeZone var1) {
      Chronology var2 = this.getChronology().withZone(var1);
      return new DateMidnight(this.getYear(), this.getMonthOfYear(), this.getDayOfMonth(), var2);
   }

   public DateTime toDateTime(TimeOfDay var1) {
      return this.toDateTime(var1, (DateTimeZone)null);
   }

   public DateTime toDateTime(TimeOfDay var1, DateTimeZone var2) {
      Chronology var3 = this.getChronology().withZone(var2);
      long var4 = DateTimeUtils.currentTimeMillis();
      var4 = var3.set(this, var4);
      if (var1 != null) {
         var4 = var3.set(var1, var4);
      }

      return new DateTime(var4, var3);
   }

   public Interval toInterval() {
      return this.toInterval((DateTimeZone)null);
   }

   public Interval toInterval(DateTimeZone var1) {
      var1 = DateTimeUtils.getZone(var1);
      return this.toDateMidnight(var1).toInterval();
   }

   public int getYear() {
      return this.getValue(0);
   }

   public int getMonthOfYear() {
      return this.getValue(1);
   }

   public int getDayOfMonth() {
      return this.getValue(2);
   }

   public YearMonthDay withYear(int var1) {
      int[] var2 = this.getValues();
      var2 = this.getChronology().year().set(this, 0, var2, var1);
      return new YearMonthDay(this, var2);
   }

   public YearMonthDay withMonthOfYear(int var1) {
      int[] var2 = this.getValues();
      var2 = this.getChronology().monthOfYear().set(this, 1, var2, var1);
      return new YearMonthDay(this, var2);
   }

   public YearMonthDay withDayOfMonth(int var1) {
      int[] var2 = this.getValues();
      var2 = this.getChronology().dayOfMonth().set(this, 2, var2, var1);
      return new YearMonthDay(this, var2);
   }

   public Property year() {
      return new Property(this, 0);
   }

   public Property monthOfYear() {
      return new Property(this, 1);
   }

   public Property dayOfMonth() {
      return new Property(this, 2);
   }

   public String toString() {
      return ISODateTimeFormat.yearMonthDay().print((ReadablePartial)this);
   }

   /** @deprecated */
   @Deprecated
   public static class Property extends AbstractPartialFieldProperty implements Serializable {
      private static final long serialVersionUID = 5727734012190224363L;
      private final YearMonthDay iYearMonthDay;
      private final int iFieldIndex;

      Property(YearMonthDay var1, int var2) {
         this.iYearMonthDay = var1;
         this.iFieldIndex = var2;
      }

      public DateTimeField getField() {
         return this.iYearMonthDay.getField(this.iFieldIndex);
      }

      protected ReadablePartial getReadablePartial() {
         return this.iYearMonthDay;
      }

      public YearMonthDay getYearMonthDay() {
         return this.iYearMonthDay;
      }

      public int get() {
         return this.iYearMonthDay.getValue(this.iFieldIndex);
      }

      public YearMonthDay addToCopy(int var1) {
         int[] var2 = this.iYearMonthDay.getValues();
         var2 = this.getField().add(this.iYearMonthDay, this.iFieldIndex, var2, var1);
         return new YearMonthDay(this.iYearMonthDay, var2);
      }

      public YearMonthDay addWrapFieldToCopy(int var1) {
         int[] var2 = this.iYearMonthDay.getValues();
         var2 = this.getField().addWrapField(this.iYearMonthDay, this.iFieldIndex, var2, var1);
         return new YearMonthDay(this.iYearMonthDay, var2);
      }

      public YearMonthDay setCopy(int var1) {
         int[] var2 = this.iYearMonthDay.getValues();
         var2 = this.getField().set(this.iYearMonthDay, this.iFieldIndex, var2, var1);
         return new YearMonthDay(this.iYearMonthDay, var2);
      }

      public YearMonthDay setCopy(String var1, Locale var2) {
         int[] var3 = this.iYearMonthDay.getValues();
         var3 = this.getField().set(this.iYearMonthDay, this.iFieldIndex, var3, var1, var2);
         return new YearMonthDay(this.iYearMonthDay, var3);
      }

      public YearMonthDay setCopy(String var1) {
         return this.setCopy(var1, (Locale)null);
      }

      public YearMonthDay withMaximumValue() {
         return this.setCopy(this.getMaximumValue());
      }

      public YearMonthDay withMinimumValue() {
         return this.setCopy(this.getMinimumValue());
      }
   }
}
