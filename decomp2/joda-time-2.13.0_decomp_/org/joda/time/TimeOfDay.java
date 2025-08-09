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
public final class TimeOfDay extends BasePartial implements ReadablePartial, Serializable {
   private static final long serialVersionUID = 3633353405803318660L;
   private static final DateTimeFieldType[] FIELD_TYPES = new DateTimeFieldType[]{DateTimeFieldType.hourOfDay(), DateTimeFieldType.minuteOfHour(), DateTimeFieldType.secondOfMinute(), DateTimeFieldType.millisOfSecond()};
   public static final TimeOfDay MIDNIGHT = new TimeOfDay(0, 0, 0, 0);
   public static final int HOUR_OF_DAY = 0;
   public static final int MINUTE_OF_HOUR = 1;
   public static final int SECOND_OF_MINUTE = 2;
   public static final int MILLIS_OF_SECOND = 3;

   public static TimeOfDay fromCalendarFields(Calendar var0) {
      if (var0 == null) {
         throw new IllegalArgumentException("The calendar must not be null");
      } else {
         return new TimeOfDay(var0.get(11), var0.get(12), var0.get(13), var0.get(14));
      }
   }

   public static TimeOfDay fromDateFields(Date var0) {
      if (var0 == null) {
         throw new IllegalArgumentException("The date must not be null");
      } else {
         return new TimeOfDay(var0.getHours(), var0.getMinutes(), var0.getSeconds(), ((int)(var0.getTime() % 1000L) + 1000) % 1000);
      }
   }

   public static TimeOfDay fromMillisOfDay(long var0) {
      return fromMillisOfDay(var0, (Chronology)null);
   }

   public static TimeOfDay fromMillisOfDay(long var0, Chronology var2) {
      var2 = DateTimeUtils.getChronology(var2);
      var2 = var2.withUTC();
      return new TimeOfDay(var0, var2);
   }

   public TimeOfDay() {
   }

   public TimeOfDay(DateTimeZone var1) {
      super(ISOChronology.getInstance(var1));
   }

   public TimeOfDay(Chronology var1) {
      super(var1);
   }

   public TimeOfDay(long var1) {
      super(var1);
   }

   public TimeOfDay(long var1, Chronology var3) {
      super(var1, var3);
   }

   public TimeOfDay(Object var1) {
      super(var1, (Chronology)null, ISODateTimeFormat.timeParser());
   }

   public TimeOfDay(Object var1, Chronology var2) {
      super(var1, DateTimeUtils.getChronology(var2), ISODateTimeFormat.timeParser());
   }

   public TimeOfDay(int var1, int var2) {
      this(var1, var2, 0, 0, (Chronology)null);
   }

   public TimeOfDay(int var1, int var2, Chronology var3) {
      this(var1, var2, 0, 0, var3);
   }

   public TimeOfDay(int var1, int var2, int var3) {
      this(var1, var2, var3, 0, (Chronology)null);
   }

   public TimeOfDay(int var1, int var2, int var3, Chronology var4) {
      this(var1, var2, var3, 0, var4);
   }

   public TimeOfDay(int var1, int var2, int var3, int var4) {
      this(var1, var2, var3, var4, (Chronology)null);
   }

   public TimeOfDay(int var1, int var2, int var3, int var4, Chronology var5) {
      super(new int[]{var1, var2, var3, var4}, var5);
   }

   TimeOfDay(TimeOfDay var1, int[] var2) {
      super((BasePartial)var1, (int[])var2);
   }

   TimeOfDay(TimeOfDay var1, Chronology var2) {
      super((BasePartial)var1, (Chronology)var2);
   }

   public int size() {
      return 4;
   }

   protected DateTimeField getField(int var1, Chronology var2) {
      switch (var1) {
         case 0:
            return var2.hourOfDay();
         case 1:
            return var2.minuteOfHour();
         case 2:
            return var2.secondOfMinute();
         case 3:
            return var2.millisOfSecond();
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

   public TimeOfDay withChronologyRetainFields(Chronology var1) {
      var1 = DateTimeUtils.getChronology(var1);
      var1 = var1.withUTC();
      if (var1 == this.getChronology()) {
         return this;
      } else {
         TimeOfDay var2 = new TimeOfDay(this, var1);
         var1.validate(var2, this.getValues());
         return var2;
      }
   }

   public TimeOfDay withField(DateTimeFieldType var1, int var2) {
      int var3 = this.indexOfSupported(var1);
      if (var2 == this.getValue(var3)) {
         return this;
      } else {
         int[] var4 = this.getValues();
         var4 = this.getField(var3).set(this, var3, var4, var2);
         return new TimeOfDay(this, var4);
      }
   }

   public TimeOfDay withFieldAdded(DurationFieldType var1, int var2) {
      int var3 = this.indexOfSupported(var1);
      if (var2 == 0) {
         return this;
      } else {
         int[] var4 = this.getValues();
         var4 = this.getField(var3).addWrapPartial(this, var3, var4, var2);
         return new TimeOfDay(this, var4);
      }
   }

   public TimeOfDay withPeriodAdded(ReadablePeriod var1, int var2) {
      if (var1 != null && var2 != 0) {
         int[] var3 = this.getValues();

         for(int var4 = 0; var4 < var1.size(); ++var4) {
            DurationFieldType var5 = var1.getFieldType(var4);
            int var6 = this.indexOf(var5);
            if (var6 >= 0) {
               var3 = this.getField(var6).addWrapPartial(this, var6, var3, FieldUtils.safeMultiply(var1.getValue(var4), var2));
            }
         }

         return new TimeOfDay(this, var3);
      } else {
         return this;
      }
   }

   public TimeOfDay plus(ReadablePeriod var1) {
      return this.withPeriodAdded(var1, 1);
   }

   public TimeOfDay plusHours(int var1) {
      return this.withFieldAdded(DurationFieldType.hours(), var1);
   }

   public TimeOfDay plusMinutes(int var1) {
      return this.withFieldAdded(DurationFieldType.minutes(), var1);
   }

   public TimeOfDay plusSeconds(int var1) {
      return this.withFieldAdded(DurationFieldType.seconds(), var1);
   }

   public TimeOfDay plusMillis(int var1) {
      return this.withFieldAdded(DurationFieldType.millis(), var1);
   }

   public TimeOfDay minus(ReadablePeriod var1) {
      return this.withPeriodAdded(var1, -1);
   }

   public TimeOfDay minusHours(int var1) {
      return this.withFieldAdded(DurationFieldType.hours(), FieldUtils.safeNegate(var1));
   }

   public TimeOfDay minusMinutes(int var1) {
      return this.withFieldAdded(DurationFieldType.minutes(), FieldUtils.safeNegate(var1));
   }

   public TimeOfDay minusSeconds(int var1) {
      return this.withFieldAdded(DurationFieldType.seconds(), FieldUtils.safeNegate(var1));
   }

   public TimeOfDay minusMillis(int var1) {
      return this.withFieldAdded(DurationFieldType.millis(), FieldUtils.safeNegate(var1));
   }

   public Property property(DateTimeFieldType var1) {
      return new Property(this, this.indexOfSupported(var1));
   }

   public LocalTime toLocalTime() {
      return new LocalTime(this.getHourOfDay(), this.getMinuteOfHour(), this.getSecondOfMinute(), this.getMillisOfSecond(), this.getChronology());
   }

   public DateTime toDateTimeToday() {
      return this.toDateTimeToday((DateTimeZone)null);
   }

   public DateTime toDateTimeToday(DateTimeZone var1) {
      Chronology var2 = this.getChronology().withZone(var1);
      long var3 = DateTimeUtils.currentTimeMillis();
      long var5 = var2.set(this, var3);
      return new DateTime(var5, var2);
   }

   public int getHourOfDay() {
      return this.getValue(0);
   }

   public int getMinuteOfHour() {
      return this.getValue(1);
   }

   public int getSecondOfMinute() {
      return this.getValue(2);
   }

   public int getMillisOfSecond() {
      return this.getValue(3);
   }

   public TimeOfDay withHourOfDay(int var1) {
      int[] var2 = this.getValues();
      var2 = this.getChronology().hourOfDay().set(this, 0, var2, var1);
      return new TimeOfDay(this, var2);
   }

   public TimeOfDay withMinuteOfHour(int var1) {
      int[] var2 = this.getValues();
      var2 = this.getChronology().minuteOfHour().set(this, 1, var2, var1);
      return new TimeOfDay(this, var2);
   }

   public TimeOfDay withSecondOfMinute(int var1) {
      int[] var2 = this.getValues();
      var2 = this.getChronology().secondOfMinute().set(this, 2, var2, var1);
      return new TimeOfDay(this, var2);
   }

   public TimeOfDay withMillisOfSecond(int var1) {
      int[] var2 = this.getValues();
      var2 = this.getChronology().millisOfSecond().set(this, 3, var2, var1);
      return new TimeOfDay(this, var2);
   }

   public Property hourOfDay() {
      return new Property(this, 0);
   }

   public Property minuteOfHour() {
      return new Property(this, 1);
   }

   public Property secondOfMinute() {
      return new Property(this, 2);
   }

   public Property millisOfSecond() {
      return new Property(this, 3);
   }

   public String toString() {
      return ISODateTimeFormat.tTime().print((ReadablePartial)this);
   }

   /** @deprecated */
   @Deprecated
   public static class Property extends AbstractPartialFieldProperty implements Serializable {
      private static final long serialVersionUID = 5598459141741063833L;
      private final TimeOfDay iTimeOfDay;
      private final int iFieldIndex;

      Property(TimeOfDay var1, int var2) {
         this.iTimeOfDay = var1;
         this.iFieldIndex = var2;
      }

      public DateTimeField getField() {
         return this.iTimeOfDay.getField(this.iFieldIndex);
      }

      protected ReadablePartial getReadablePartial() {
         return this.iTimeOfDay;
      }

      public TimeOfDay getTimeOfDay() {
         return this.iTimeOfDay;
      }

      public int get() {
         return this.iTimeOfDay.getValue(this.iFieldIndex);
      }

      public TimeOfDay addToCopy(int var1) {
         int[] var2 = this.iTimeOfDay.getValues();
         var2 = this.getField().addWrapPartial(this.iTimeOfDay, this.iFieldIndex, var2, var1);
         return new TimeOfDay(this.iTimeOfDay, var2);
      }

      public TimeOfDay addNoWrapToCopy(int var1) {
         int[] var2 = this.iTimeOfDay.getValues();
         var2 = this.getField().add(this.iTimeOfDay, this.iFieldIndex, var2, var1);
         return new TimeOfDay(this.iTimeOfDay, var2);
      }

      public TimeOfDay addWrapFieldToCopy(int var1) {
         int[] var2 = this.iTimeOfDay.getValues();
         var2 = this.getField().addWrapField(this.iTimeOfDay, this.iFieldIndex, var2, var1);
         return new TimeOfDay(this.iTimeOfDay, var2);
      }

      public TimeOfDay setCopy(int var1) {
         int[] var2 = this.iTimeOfDay.getValues();
         var2 = this.getField().set(this.iTimeOfDay, this.iFieldIndex, var2, var1);
         return new TimeOfDay(this.iTimeOfDay, var2);
      }

      public TimeOfDay setCopy(String var1, Locale var2) {
         int[] var3 = this.iTimeOfDay.getValues();
         var3 = this.getField().set(this.iTimeOfDay, this.iFieldIndex, var3, var1, var2);
         return new TimeOfDay(this.iTimeOfDay, var3);
      }

      public TimeOfDay setCopy(String var1) {
         return this.setCopy(var1, (Locale)null);
      }

      public TimeOfDay withMaximumValue() {
         return this.setCopy(this.getMaximumValue());
      }

      public TimeOfDay withMinimumValue() {
         return this.setCopy(this.getMinimumValue());
      }
   }
}
