package org.joda.time.field;

import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DurationField;

public class RemainderDateTimeField extends DecoratedDateTimeField {
   private static final long serialVersionUID = 5708241235177666790L;
   final int iDivisor;
   final DurationField iDurationField;
   final DurationField iRangeField;

   public RemainderDateTimeField(DateTimeField var1, DateTimeFieldType var2, int var3) {
      super(var1, var2);
      if (var3 < 2) {
         throw new IllegalArgumentException("The divisor must be at least 2");
      } else {
         DurationField var4 = var1.getDurationField();
         if (var4 == null) {
            this.iRangeField = null;
         } else {
            this.iRangeField = new ScaledDurationField(var4, var2.getRangeDurationType(), var3);
         }

         this.iDurationField = var1.getDurationField();
         this.iDivisor = var3;
      }
   }

   public RemainderDateTimeField(DateTimeField var1, DurationField var2, DateTimeFieldType var3, int var4) {
      super(var1, var3);
      if (var4 < 2) {
         throw new IllegalArgumentException("The divisor must be at least 2");
      } else {
         this.iRangeField = var2;
         this.iDurationField = var1.getDurationField();
         this.iDivisor = var4;
      }
   }

   public RemainderDateTimeField(DividedDateTimeField var1) {
      this(var1, var1.getType());
   }

   public RemainderDateTimeField(DividedDateTimeField var1, DateTimeFieldType var2) {
      this(var1, var1.getWrappedField().getDurationField(), var2);
   }

   public RemainderDateTimeField(DividedDateTimeField var1, DurationField var2, DateTimeFieldType var3) {
      super(var1.getWrappedField(), var3);
      this.iDivisor = var1.iDivisor;
      this.iDurationField = var2;
      this.iRangeField = var1.iDurationField;
   }

   public int get(long var1) {
      int var3 = this.getWrappedField().get(var1);
      return var3 >= 0 ? var3 % this.iDivisor : this.iDivisor - 1 + (var3 + 1) % this.iDivisor;
   }

   public long addWrapField(long var1, int var3) {
      return this.set(var1, FieldUtils.getWrappedValue(this.get(var1), var3, 0, this.iDivisor - 1));
   }

   public long set(long var1, int var3) {
      FieldUtils.verifyValueBounds((DateTimeField)this, var3, 0, this.iDivisor - 1);
      int var4 = this.getDivided(this.getWrappedField().get(var1));
      return this.getWrappedField().set(var1, var4 * this.iDivisor + var3);
   }

   public DurationField getDurationField() {
      return this.iDurationField;
   }

   public DurationField getRangeDurationField() {
      return this.iRangeField;
   }

   public int getMinimumValue() {
      return 0;
   }

   public int getMaximumValue() {
      return this.iDivisor - 1;
   }

   public long roundFloor(long var1) {
      return this.getWrappedField().roundFloor(var1);
   }

   public long roundCeiling(long var1) {
      return this.getWrappedField().roundCeiling(var1);
   }

   public long roundHalfFloor(long var1) {
      return this.getWrappedField().roundHalfFloor(var1);
   }

   public long roundHalfCeiling(long var1) {
      return this.getWrappedField().roundHalfCeiling(var1);
   }

   public long roundHalfEven(long var1) {
      return this.getWrappedField().roundHalfEven(var1);
   }

   public long remainder(long var1) {
      return this.getWrappedField().remainder(var1);
   }

   public int getDivisor() {
      return this.iDivisor;
   }

   private int getDivided(int var1) {
      return var1 >= 0 ? var1 / this.iDivisor : (var1 + 1) / this.iDivisor - 1;
   }
}
