package org.joda.time.chrono;

import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DurationField;
import org.joda.time.field.FieldUtils;
import org.joda.time.field.ImpreciseDateTimeField;

class BasicYearDateTimeField extends ImpreciseDateTimeField {
   private static final long serialVersionUID = -98628754872287L;
   protected final BasicChronology iChronology;

   BasicYearDateTimeField(BasicChronology var1) {
      super(DateTimeFieldType.year(), var1.getAverageMillisPerYear());
      this.iChronology = var1;
   }

   public boolean isLenient() {
      return false;
   }

   public int get(long var1) {
      return this.iChronology.getYear(var1);
   }

   public long add(long var1, int var3) {
      if (var3 == 0) {
         return var1;
      } else {
         int var4 = this.get(var1);
         int var5 = FieldUtils.safeAdd(var4, var3);
         return this.set(var1, var5);
      }
   }

   public long add(long var1, long var3) {
      return this.add(var1, FieldUtils.safeToInt(var3));
   }

   public long addWrapField(long var1, int var3) {
      if (var3 == 0) {
         return var1;
      } else {
         int var4 = this.iChronology.getYear(var1);
         int var5 = FieldUtils.getWrappedValue(var4, var3, this.iChronology.getMinYear(), this.iChronology.getMaxYear());
         return this.set(var1, var5);
      }
   }

   public long set(long var1, int var3) {
      FieldUtils.verifyValueBounds((DateTimeField)this, var3, this.iChronology.getMinYear(), this.iChronology.getMaxYear());
      return this.iChronology.setYear(var1, var3);
   }

   public long setExtended(long var1, int var3) {
      FieldUtils.verifyValueBounds((DateTimeField)this, var3, this.iChronology.getMinYear() - 1, this.iChronology.getMaxYear() + 1);
      return this.iChronology.setYear(var1, var3);
   }

   public long getDifferenceAsLong(long var1, long var3) {
      return var1 < var3 ? -this.iChronology.getYearDifference(var3, var1) : this.iChronology.getYearDifference(var1, var3);
   }

   public DurationField getRangeDurationField() {
      return null;
   }

   public boolean isLeap(long var1) {
      return this.iChronology.isLeapYear(this.get(var1));
   }

   public int getLeapAmount(long var1) {
      return this.iChronology.isLeapYear(this.get(var1)) ? 1 : 0;
   }

   public DurationField getLeapDurationField() {
      return this.iChronology.days();
   }

   public int getMinimumValue() {
      return this.iChronology.getMinYear();
   }

   public int getMaximumValue() {
      return this.iChronology.getMaxYear();
   }

   public long roundFloor(long var1) {
      return this.iChronology.getYearMillis(this.get(var1));
   }

   public long roundCeiling(long var1) {
      int var3 = this.get(var1);
      long var4 = this.iChronology.getYearMillis(var3);
      if (var1 != var4) {
         var1 = this.iChronology.getYearMillis(var3 + 1);
      }

      return var1;
   }

   public long remainder(long var1) {
      return var1 - this.roundFloor(var1);
   }

   private Object readResolve() {
      return this.iChronology.year();
   }
}
