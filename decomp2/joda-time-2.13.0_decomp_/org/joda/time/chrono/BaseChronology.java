package org.joda.time.chrono;

import java.io.Serializable;
import org.joda.time.Chronology;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DateTimeZone;
import org.joda.time.DurationField;
import org.joda.time.DurationFieldType;
import org.joda.time.IllegalFieldValueException;
import org.joda.time.ReadablePartial;
import org.joda.time.ReadablePeriod;
import org.joda.time.field.FieldUtils;
import org.joda.time.field.UnsupportedDateTimeField;
import org.joda.time.field.UnsupportedDurationField;

public abstract class BaseChronology extends Chronology implements Serializable {
   private static final long serialVersionUID = -7310865996721419676L;

   protected BaseChronology() {
   }

   public abstract DateTimeZone getZone();

   public abstract Chronology withUTC();

   public abstract Chronology withZone(DateTimeZone var1);

   public long getDateTimeMillis(int var1, int var2, int var3, int var4) throws IllegalArgumentException {
      long var5 = this.year().set(0L, var1);
      var5 = this.monthOfYear().set(var5, var2);
      var5 = this.dayOfMonth().set(var5, var3);
      return this.millisOfDay().set(var5, var4);
   }

   public long getDateTimeMillis(int var1, int var2, int var3, int var4, int var5, int var6, int var7) throws IllegalArgumentException {
      long var8 = this.year().set(0L, var1);
      var8 = this.monthOfYear().set(var8, var2);
      var8 = this.dayOfMonth().set(var8, var3);
      var8 = this.hourOfDay().set(var8, var4);
      var8 = this.minuteOfHour().set(var8, var5);
      var8 = this.secondOfMinute().set(var8, var6);
      return this.millisOfSecond().set(var8, var7);
   }

   public long getDateTimeMillis(long var1, int var3, int var4, int var5, int var6) throws IllegalArgumentException {
      var1 = this.hourOfDay().set(var1, var3);
      var1 = this.minuteOfHour().set(var1, var4);
      var1 = this.secondOfMinute().set(var1, var5);
      return this.millisOfSecond().set(var1, var6);
   }

   public void validate(ReadablePartial var1, int[] var2) {
      int var3 = var1.size();

      for(int var4 = 0; var4 < var3; ++var4) {
         int var5 = var2[var4];
         DateTimeField var6 = var1.getField(var4);
         if (var5 < var6.getMinimumValue()) {
            throw new IllegalFieldValueException(var6.getType(), var5, var6.getMinimumValue(), (Number)null);
         }

         if (var5 > var6.getMaximumValue()) {
            throw new IllegalFieldValueException(var6.getType(), var5, (Number)null, var6.getMaximumValue());
         }
      }

      for(int var7 = 0; var7 < var3; ++var7) {
         int var8 = var2[var7];
         DateTimeField var9 = var1.getField(var7);
         if (var8 < var9.getMinimumValue(var1, var2)) {
            throw new IllegalFieldValueException(var9.getType(), var8, var9.getMinimumValue(var1, var2), (Number)null);
         }

         if (var8 > var9.getMaximumValue(var1, var2)) {
            throw new IllegalFieldValueException(var9.getType(), var8, (Number)null, var9.getMaximumValue(var1, var2));
         }
      }

   }

   public int[] get(ReadablePartial var1, long var2) {
      int var4 = var1.size();
      int[] var5 = new int[var4];

      for(int var6 = 0; var6 < var4; ++var6) {
         var5[var6] = var1.getFieldType(var6).getField(this).get(var2);
      }

      return var5;
   }

   public long set(ReadablePartial var1, long var2) {
      int var4 = 0;

      for(int var5 = var1.size(); var4 < var5; ++var4) {
         var2 = var1.getFieldType(var4).getField(this).set(var2, var1.getValue(var4));
      }

      return var2;
   }

   public int[] get(ReadablePeriod var1, long var2, long var4) {
      int var6 = var1.size();
      int[] var7 = new int[var6];
      if (var2 != var4) {
         for(int var8 = 0; var8 < var6; ++var8) {
            DurationField var9 = var1.getFieldType(var8).getField(this);
            int var10 = var9.getDifference(var4, var2);
            if (var10 != 0) {
               var2 = var9.add(var2, var10);
            }

            var7[var8] = var10;
         }
      }

      return var7;
   }

   public int[] get(ReadablePeriod var1, long var2) {
      int var4 = var1.size();
      int[] var5 = new int[var4];
      if (var2 != 0L) {
         long var6 = 0L;

         for(int var8 = 0; var8 < var4; ++var8) {
            DurationField var9 = var1.getFieldType(var8).getField(this);
            if (var9.isPrecise()) {
               int var10 = var9.getDifference(var2, var6);
               var6 = var9.add(var6, var10);
               var5[var8] = var10;
            }
         }
      }

      return var5;
   }

   public long add(ReadablePeriod var1, long var2, int var4) {
      if (var4 != 0 && var1 != null) {
         int var5 = 0;

         for(int var6 = var1.size(); var5 < var6; ++var5) {
            long var7 = (long)var1.getValue(var5);
            if (var7 != 0L) {
               var2 = var1.getFieldType(var5).getField(this).add(var2, var7 * (long)var4);
            }
         }
      }

      return var2;
   }

   public long add(long var1, long var3, int var5) {
      if (var3 != 0L && var5 != 0) {
         long var6 = FieldUtils.safeMultiply(var3, var5);
         return FieldUtils.safeAdd(var1, var6);
      } else {
         return var1;
      }
   }

   public DurationField millis() {
      return UnsupportedDurationField.getInstance(DurationFieldType.millis());
   }

   public DateTimeField millisOfSecond() {
      return UnsupportedDateTimeField.getInstance(DateTimeFieldType.millisOfSecond(), this.millis());
   }

   public DateTimeField millisOfDay() {
      return UnsupportedDateTimeField.getInstance(DateTimeFieldType.millisOfDay(), this.millis());
   }

   public DurationField seconds() {
      return UnsupportedDurationField.getInstance(DurationFieldType.seconds());
   }

   public DateTimeField secondOfMinute() {
      return UnsupportedDateTimeField.getInstance(DateTimeFieldType.secondOfMinute(), this.seconds());
   }

   public DateTimeField secondOfDay() {
      return UnsupportedDateTimeField.getInstance(DateTimeFieldType.secondOfDay(), this.seconds());
   }

   public DurationField minutes() {
      return UnsupportedDurationField.getInstance(DurationFieldType.minutes());
   }

   public DateTimeField minuteOfHour() {
      return UnsupportedDateTimeField.getInstance(DateTimeFieldType.minuteOfHour(), this.minutes());
   }

   public DateTimeField minuteOfDay() {
      return UnsupportedDateTimeField.getInstance(DateTimeFieldType.minuteOfDay(), this.minutes());
   }

   public DurationField hours() {
      return UnsupportedDurationField.getInstance(DurationFieldType.hours());
   }

   public DateTimeField hourOfDay() {
      return UnsupportedDateTimeField.getInstance(DateTimeFieldType.hourOfDay(), this.hours());
   }

   public DateTimeField clockhourOfDay() {
      return UnsupportedDateTimeField.getInstance(DateTimeFieldType.clockhourOfDay(), this.hours());
   }

   public DurationField halfdays() {
      return UnsupportedDurationField.getInstance(DurationFieldType.halfdays());
   }

   public DateTimeField hourOfHalfday() {
      return UnsupportedDateTimeField.getInstance(DateTimeFieldType.hourOfHalfday(), this.hours());
   }

   public DateTimeField clockhourOfHalfday() {
      return UnsupportedDateTimeField.getInstance(DateTimeFieldType.clockhourOfHalfday(), this.hours());
   }

   public DateTimeField halfdayOfDay() {
      return UnsupportedDateTimeField.getInstance(DateTimeFieldType.halfdayOfDay(), this.halfdays());
   }

   public DurationField days() {
      return UnsupportedDurationField.getInstance(DurationFieldType.days());
   }

   public DateTimeField dayOfWeek() {
      return UnsupportedDateTimeField.getInstance(DateTimeFieldType.dayOfWeek(), this.days());
   }

   public DateTimeField dayOfMonth() {
      return UnsupportedDateTimeField.getInstance(DateTimeFieldType.dayOfMonth(), this.days());
   }

   public DateTimeField dayOfYear() {
      return UnsupportedDateTimeField.getInstance(DateTimeFieldType.dayOfYear(), this.days());
   }

   public DurationField weeks() {
      return UnsupportedDurationField.getInstance(DurationFieldType.weeks());
   }

   public DateTimeField weekOfWeekyear() {
      return UnsupportedDateTimeField.getInstance(DateTimeFieldType.weekOfWeekyear(), this.weeks());
   }

   public DurationField weekyears() {
      return UnsupportedDurationField.getInstance(DurationFieldType.weekyears());
   }

   public DateTimeField weekyear() {
      return UnsupportedDateTimeField.getInstance(DateTimeFieldType.weekyear(), this.weekyears());
   }

   public DateTimeField weekyearOfCentury() {
      return UnsupportedDateTimeField.getInstance(DateTimeFieldType.weekyearOfCentury(), this.weekyears());
   }

   public DurationField months() {
      return UnsupportedDurationField.getInstance(DurationFieldType.months());
   }

   public DateTimeField monthOfYear() {
      return UnsupportedDateTimeField.getInstance(DateTimeFieldType.monthOfYear(), this.months());
   }

   public DurationField years() {
      return UnsupportedDurationField.getInstance(DurationFieldType.years());
   }

   public DateTimeField year() {
      return UnsupportedDateTimeField.getInstance(DateTimeFieldType.year(), this.years());
   }

   public DateTimeField yearOfEra() {
      return UnsupportedDateTimeField.getInstance(DateTimeFieldType.yearOfEra(), this.years());
   }

   public DateTimeField yearOfCentury() {
      return UnsupportedDateTimeField.getInstance(DateTimeFieldType.yearOfCentury(), this.years());
   }

   public DurationField centuries() {
      return UnsupportedDurationField.getInstance(DurationFieldType.centuries());
   }

   public DateTimeField centuryOfEra() {
      return UnsupportedDateTimeField.getInstance(DateTimeFieldType.centuryOfEra(), this.centuries());
   }

   public DurationField eras() {
      return UnsupportedDurationField.getInstance(DurationFieldType.eras());
   }

   public DateTimeField era() {
      return UnsupportedDateTimeField.getInstance(DateTimeFieldType.era(), this.eras());
   }

   public abstract String toString();
}
