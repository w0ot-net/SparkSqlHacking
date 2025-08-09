package org.joda.time;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import org.joda.convert.FromString;
import org.joda.convert.ToString;
import org.joda.time.base.BasePartial;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.field.AbstractPartialFieldProperty;
import org.joda.time.field.FieldUtils;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.joda.time.format.ISODateTimeFormat;

public final class MonthDay extends BasePartial implements ReadablePartial, Serializable {
   private static final long serialVersionUID = 2954560699050434609L;
   private static final DateTimeFieldType[] FIELD_TYPES = new DateTimeFieldType[]{DateTimeFieldType.monthOfYear(), DateTimeFieldType.dayOfMonth()};
   private static final DateTimeFormatter PARSER = (new DateTimeFormatterBuilder()).appendOptional(ISODateTimeFormat.localDateParser().getParser()).appendOptional(DateTimeFormat.forPattern("--MM-dd").getParser()).toFormatter();
   public static final int MONTH_OF_YEAR = 0;
   public static final int DAY_OF_MONTH = 1;

   public static MonthDay now() {
      return new MonthDay();
   }

   public static MonthDay now(DateTimeZone var0) {
      if (var0 == null) {
         throw new NullPointerException("Zone must not be null");
      } else {
         return new MonthDay(var0);
      }
   }

   public static MonthDay now(Chronology var0) {
      if (var0 == null) {
         throw new NullPointerException("Chronology must not be null");
      } else {
         return new MonthDay(var0);
      }
   }

   @FromString
   public static MonthDay parse(String var0) {
      return parse(var0, PARSER);
   }

   public static MonthDay parse(String var0, DateTimeFormatter var1) {
      LocalDate var2 = var1.parseLocalDate(var0);
      return new MonthDay(var2.getMonthOfYear(), var2.getDayOfMonth());
   }

   public static MonthDay fromCalendarFields(Calendar var0) {
      if (var0 == null) {
         throw new IllegalArgumentException("The calendar must not be null");
      } else {
         return new MonthDay(var0.get(2) + 1, var0.get(5));
      }
   }

   public static MonthDay fromDateFields(Date var0) {
      if (var0 == null) {
         throw new IllegalArgumentException("The date must not be null");
      } else {
         return new MonthDay(var0.getMonth() + 1, var0.getDate());
      }
   }

   public MonthDay() {
   }

   public MonthDay(DateTimeZone var1) {
      super(ISOChronology.getInstance(var1));
   }

   public MonthDay(Chronology var1) {
      super(var1);
   }

   public MonthDay(long var1) {
      super(var1);
   }

   public MonthDay(long var1, Chronology var3) {
      super(var1, var3);
   }

   public MonthDay(Object var1) {
      super(var1, (Chronology)null, ISODateTimeFormat.localDateParser());
   }

   public MonthDay(Object var1, Chronology var2) {
      super(var1, DateTimeUtils.getChronology(var2), ISODateTimeFormat.localDateParser());
   }

   public MonthDay(int var1, int var2) {
      this(var1, var2, (Chronology)null);
   }

   public MonthDay(int var1, int var2, Chronology var3) {
      super(new int[]{var1, var2}, var3);
   }

   MonthDay(MonthDay var1, int[] var2) {
      super((BasePartial)var1, (int[])var2);
   }

   MonthDay(MonthDay var1, Chronology var2) {
      super((BasePartial)var1, (Chronology)var2);
   }

   private Object readResolve() {
      return !DateTimeZone.UTC.equals(this.getChronology().getZone()) ? new MonthDay(this, this.getChronology().withUTC()) : this;
   }

   public int size() {
      return 2;
   }

   protected DateTimeField getField(int var1, Chronology var2) {
      switch (var1) {
         case 0:
            return var2.monthOfYear();
         case 1:
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

   public MonthDay withChronologyRetainFields(Chronology var1) {
      var1 = DateTimeUtils.getChronology(var1);
      var1 = var1.withUTC();
      if (var1 == this.getChronology()) {
         return this;
      } else {
         MonthDay var2 = new MonthDay(this, var1);
         var1.validate(var2, this.getValues());
         return var2;
      }
   }

   public MonthDay withField(DateTimeFieldType var1, int var2) {
      int var3 = this.indexOfSupported(var1);
      if (var2 == this.getValue(var3)) {
         return this;
      } else {
         int[] var4 = this.getValues();
         var4 = this.getField(var3).set(this, var3, var4, var2);
         return new MonthDay(this, var4);
      }
   }

   public MonthDay withFieldAdded(DurationFieldType var1, int var2) {
      int var3 = this.indexOfSupported(var1);
      if (var2 == 0) {
         return this;
      } else {
         int[] var4 = this.getValues();
         var4 = this.getField(var3).add(this, var3, var4, var2);
         return new MonthDay(this, var4);
      }
   }

   public MonthDay withPeriodAdded(ReadablePeriod var1, int var2) {
      if (var1 != null && var2 != 0) {
         int[] var3 = this.getValues();

         for(int var4 = 0; var4 < var1.size(); ++var4) {
            DurationFieldType var5 = var1.getFieldType(var4);
            int var6 = this.indexOf(var5);
            if (var6 >= 0) {
               var3 = this.getField(var6).add(this, var6, var3, FieldUtils.safeMultiply(var1.getValue(var4), var2));
            }
         }

         return new MonthDay(this, var3);
      } else {
         return this;
      }
   }

   public MonthDay plus(ReadablePeriod var1) {
      return this.withPeriodAdded(var1, 1);
   }

   public MonthDay plusMonths(int var1) {
      return this.withFieldAdded(DurationFieldType.months(), var1);
   }

   public MonthDay plusDays(int var1) {
      return this.withFieldAdded(DurationFieldType.days(), var1);
   }

   public MonthDay minus(ReadablePeriod var1) {
      return this.withPeriodAdded(var1, -1);
   }

   public MonthDay minusMonths(int var1) {
      return this.withFieldAdded(DurationFieldType.months(), FieldUtils.safeNegate(var1));
   }

   public MonthDay minusDays(int var1) {
      return this.withFieldAdded(DurationFieldType.days(), FieldUtils.safeNegate(var1));
   }

   public LocalDate toLocalDate(int var1) {
      return new LocalDate(var1, this.getMonthOfYear(), this.getDayOfMonth(), this.getChronology());
   }

   public int getMonthOfYear() {
      return this.getValue(0);
   }

   public int getDayOfMonth() {
      return this.getValue(1);
   }

   public MonthDay withMonthOfYear(int var1) {
      int[] var2 = this.getValues();
      var2 = this.getChronology().monthOfYear().set(this, 0, var2, var1);
      return new MonthDay(this, var2);
   }

   public MonthDay withDayOfMonth(int var1) {
      int[] var2 = this.getValues();
      var2 = this.getChronology().dayOfMonth().set(this, 1, var2, var1);
      return new MonthDay(this, var2);
   }

   public Property property(DateTimeFieldType var1) {
      return new Property(this, this.indexOfSupported(var1));
   }

   public Property monthOfYear() {
      return new Property(this, 0);
   }

   public Property dayOfMonth() {
      return new Property(this, 1);
   }

   @ToString
   public String toString() {
      ArrayList var1 = new ArrayList();
      var1.add(DateTimeFieldType.monthOfYear());
      var1.add(DateTimeFieldType.dayOfMonth());
      return ISODateTimeFormat.forFields(var1, true, true).print((ReadablePartial)this);
   }

   public String toString(String var1) {
      return var1 == null ? this.toString() : DateTimeFormat.forPattern(var1).print((ReadablePartial)this);
   }

   public String toString(String var1, Locale var2) throws IllegalArgumentException {
      return var1 == null ? this.toString() : DateTimeFormat.forPattern(var1).withLocale(var2).print((ReadablePartial)this);
   }

   public static class Property extends AbstractPartialFieldProperty implements Serializable {
      private static final long serialVersionUID = 5727734012190224363L;
      private final MonthDay iBase;
      private final int iFieldIndex;

      Property(MonthDay var1, int var2) {
         this.iBase = var1;
         this.iFieldIndex = var2;
      }

      public DateTimeField getField() {
         return this.iBase.getField(this.iFieldIndex);
      }

      protected ReadablePartial getReadablePartial() {
         return this.iBase;
      }

      public MonthDay getMonthDay() {
         return this.iBase;
      }

      public int get() {
         return this.iBase.getValue(this.iFieldIndex);
      }

      public MonthDay addToCopy(int var1) {
         int[] var2 = this.iBase.getValues();
         var2 = this.getField().add(this.iBase, this.iFieldIndex, var2, var1);
         return new MonthDay(this.iBase, var2);
      }

      public MonthDay addWrapFieldToCopy(int var1) {
         int[] var2 = this.iBase.getValues();
         var2 = this.getField().addWrapField(this.iBase, this.iFieldIndex, var2, var1);
         return new MonthDay(this.iBase, var2);
      }

      public MonthDay setCopy(int var1) {
         int[] var2 = this.iBase.getValues();
         var2 = this.getField().set(this.iBase, this.iFieldIndex, var2, var1);
         return new MonthDay(this.iBase, var2);
      }

      public MonthDay setCopy(String var1, Locale var2) {
         int[] var3 = this.iBase.getValues();
         var3 = this.getField().set(this.iBase, this.iFieldIndex, var3, var1, var2);
         return new MonthDay(this.iBase, var3);
      }

      public MonthDay setCopy(String var1) {
         return this.setCopy(var1, (Locale)null);
      }
   }
}
